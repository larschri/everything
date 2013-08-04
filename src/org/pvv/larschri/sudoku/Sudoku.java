package org.pvv.larschri.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.base.Splitter;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.primitives.Chars;


public class Sudoku {

	private static final int RECURSION_DEPTH = 4; // don't care about larger clusters than this

	private class Cell {
		private final Set<Group> cellGroups;
		private Set<Character> candidates;
		Cell(Group ... groups) {
			this.cellGroups = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(groups)));
			this.candidates = new HashSet<>(symbols);
		}

		boolean setCandidates(Set<Character> candidates) {
			if (!this.candidates.equals(candidates)) {
				this.candidates = new HashSet<>(candidates);
				return true;
			} else {
				return false;
			}
		}

		boolean retainCandidates(Set<Character> keep) {
			int preSize = candidates.size();
			this.candidates.retainAll(keep);
			return preSize != candidates.size();
		}

		char toChar(char nullChar) {
			return isSolved() ? candidates.iterator().next() : nullChar;
		}

		boolean isSolved() {
			return candidates.size() == 1;
		}

		boolean eliminateCandidates(Set<Character> eliminated) {
			int preSize = candidates.size();
			candidates.removeAll(eliminated);
			return preSize != candidates.size();
		}
	}

	private class Group {
		private final Cell[] groupCells;
		private final int id;

		Group(int size, int id) {
			this.id = id;
			groupCells = new Cell[size];
		}

		@Override public String toString() {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (Cell cell : groupCells) {
				sb.append(cell.toChar('.'));
				if (i++ % 3 == 2)
					sb.append("  ");
			}
			return sb.toString();
		}

		List<Entry<Character, Collection<Cell>>> cellsByCandidates() {
			Multimap<Character, Cell> result = HashMultimap.create();
			for (Cell cell : groupCells)
				for (Character c : cell.candidates)
					result.put(c, cell);
			return new ArrayList<>(result.asMap().entrySet());
		}
	}

	/** Detect smaller groups of cells that has the same candidate symbols. If a group of
	 *  n cells has n candidate symbols, then these symbols can be eliminated from 
	 *  other cells.
	 */
	private static final Eliminator SYMBOL_ELIMINATOR2 = new CliqueEliminator<Cell, Character>() {

		@Override boolean eliminate(List<Entry<Cell, Collection<Character>>> links, Set<Cell> cells, Set<Character> symbols) {
			boolean changed = false;
			for (Entry<Cell, Collection<Character>> entry : links)
				if (!cells.contains(entry.getKey()))
					changed |= entry.getKey().eliminateCandidates(symbols);
			return changed;
		}

		@Override List<Entry<Cell, Collection<Character>>> getLinks(Group group) {
			List<Entry<Cell, Collection<Character>>> links = new ArrayList<>();
			for (Cell cell : group.groupCells)
				links.add(Maps.<Cell, Collection<Character>>immutableEntry(cell, cell.candidates));
			return links;
		}
	};

	/**
	 * Find cliques and pass them to {@link #eliminate(List, Set, Set)}. The
	 * relation between X and Y objects are define by a List<Entry<X,
	 * Collection<Y>>, and is used to find the cliques. A Clique in this context
	 * is a Set<X> with a corresponding Set<Y> (union of the Ys) of the same size.
	 */
	static abstract class CliqueEliminator<X, Y> implements Eliminator {

		/** Called if a clique is found. */
		abstract boolean eliminate(List<Entry<X, Collection<Y>>> links, Set<X> xs, Set<Y> ys);

		/** Get the relations between objects used to find sets of same size. */
		abstract List<Entry<X, Collection<Y>>> getLinks(Group group);

		private boolean recursion(Set<X> xs, Set<Y> ys, List<Entry<X, Collection<Y>>> links, int offset) {
			Entry<X, Collection<Y>> pair = links.get(offset);

			Set<X> x = Sets.union(xs, Collections.singleton(pair.getKey()));
			Set<Y> y = Sets.union(ys, (Set<Y>) pair.getValue());

			if (x.size() == y.size()) {
				return  eliminate(links, x, y);
			} else if (y.size() > RECURSION_DEPTH) {
				return recursionLoop(x, y, links, offset + 1);
			} else {
				return false;
			}
		}

		private boolean recursionLoop(Set<X> x, Set<Y> y, List<Entry<X, Collection<Y>>> links, int offset) {
			boolean changed = false;
			for (int i = offset; i < links.size(); i++)
				changed |= recursion(x, y, links, i);
			return changed;
		}

		@Override public final boolean eliminate(Group group) {
			return recursionLoop(Collections.<X>emptySet(), Collections.<Y>emptySet(), getLinks(group), 0);
		}
	}

	/**
	 * Detect smaller groups of symbols that are in the same cells. If a group of n symbols
	 * are in n cells, then remove other candidates from those cells.
	 */
	private static final Eliminator CELL_ELIMINATOR2 = new CliqueEliminator<Character, Cell>() {

		@Override boolean eliminate(List<Entry<Character, Collection<Cell>>> links, Set<Character> symbols, Set<Cell> cells) {
			boolean changed = false;
			for (Cell cell : cells)
				changed |= cell.retainCandidates(symbols);
			return changed;
		}

		@Override List<Entry<Character, Collection<Cell>>> getLinks(Group group) {
			return group.cellsByCandidates();
		}
	};

	/** 
	 * Propagates findings from one group to another when possible.
	 * Example: if a box fixes a symbol to a row within the box, then remove the
	 * symbol from the other cells in the row.
	 */
	private static final Eliminator ELIMINTATION_PROPAGATOR = new Eliminator() {
		@Override public boolean eliminate(Group group) {
			boolean changed = false;
			for (Entry<Character, Collection<Cell>> entry : group.cellsByCandidates()) {
				Set<Group> groups = new HashSet<>(entry.getValue().iterator().next().cellGroups);
				for (Cell cell : entry.getValue())
					groups.retainAll(cell.cellGroups);

				for (Group g : groups)
					if (g != group)
						for (Cell cell : group.groupCells)
							if (!entry.getValue().contains(cell))
								changed |= cell.eliminateCandidates(Collections.singleton(entry.getKey()));
			}
			return changed;
		}
	};

	private static final int ROW = 0;
	private static final int COL = 1;
	private static final int BOX = 2;

	private final List<Cell> cells = new ArrayList<>();
	private final Group[][] groups;
	private final Set<Character> symbols;

	public Sudoku() {
		groups = new Group[3][9];
		for (int i = 0; i < 9; i++) {
			groups[ROW][i] = new Group(9, i);
			groups[COL][i] = new Group(9, i);
			groups[BOX][i] = new Group(9, i);
		}

		symbols = new HashSet<>(Chars.asList("123456789".toCharArray()));
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				Group row = groups[ROW][i];
				Group col = groups[COL][j];
				Group box = groups[BOX][3 * (row.id / 3) + col.id / 3];
				Cell cell = new Cell(row, col, box);
				cells.add(cell);
				row.groupCells[j] = cell;
				col.groupCells[i] = cell;
				box.groupCells[3 * (row.id % 3) + col.id % 3] = cell;
			}
		}
	}

	void init(String ... rows) {
		init(Arrays.asList(rows));
	}

	void init(Iterable<String> rows) {
		int i = 0;
		for (String row : rows) {
			assert row.length() == symbols.size();
			for (int j = 0; j < row.length(); j++) {
				Character c = row.charAt(j);
				if (symbols.contains(c)) {
					groups[ROW][i].groupCells[j].setCandidates(Collections.singleton(c));
				}
			}
			i++;
		}
	}

	void init(Sudoku sudoku) {
		assert sudoku.groups[ROW].length == groups[ROW].length;
		for (int i = 0; i < groups[ROW].length; i++) {
			Cell[] cells = sudoku.groups[ROW][i].groupCells;
			for (int j = 0; j < cells.length; j++) {
				groups[ROW][i].groupCells[j].setCandidates(cells[j].candidates);
			}
		}
	}

	@Override public String toString() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Group g : groups[ROW]) {
			sb.append(g + "\n");
			if (i++ % 3 == 2)
				sb.append("\n");
		}
		return sb.toString();
	}

	boolean eliminate(Eliminator eliminator) {
		boolean changed = false;
		for (Group[] gs : groups)
			for (Group g : gs)
				changed |= eliminator.eliminate(g);
		return changed;
	}

	interface Eliminator {
		boolean eliminate(Group group);
	}

	boolean isDone() {
		for (Cell cell : cells)
			if (cell.candidates.size() > 1)
				return false;
		return true;
	}

	// Todo: Make this dynamic
	private static final boolean REQUIRE_UNIQUE_SOLUTION = false;

	boolean guess() {
		for (int i = 0; i < cells.size(); i++) {
			Cell cell = cells.get(i);
			if (cell.candidates.size() > 1) {
				for (Character symbol : cell.candidates) {
					Sudoku guess = new Sudoku();
					guess.init(this);
					guess.cells.get(i).setCandidates(Collections.singleton(symbol));
					if (!guess.solve()) {
						// Eliminate the guessed symbol since it made the sudoku unsolvable.
						cell.candidates.remove(symbol);
						return true;
					} else if (!REQUIRE_UNIQUE_SOLUTION) {
						this.init(guess);
						return true;
					}
				}
			}
		}
		return false;
	}

	boolean solve() {
		while (!isDone()) {
			// Call these repeatedly to eliminate candidates until 
			// they don't have any effect.
			while (eliminate(SYMBOL_ELIMINATOR2) || eliminate(CELL_ELIMINATOR2) || eliminate(ELIMINTATION_PROPAGATOR)) {
			}

			if (!isDone() && !guess()) {
				// Either more than one solution or a bug
				throw new RuntimeException("Couldn't solve");
			}
		}

		// Did we succeed?
		for (Cell cell : cells)
			if (cell.candidates.isEmpty())
				// Unsolvable / incorrect guess.
				return false;
		return true;
	}

	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku();

		sudoku.init(Splitter.fixedLength(9).split("38.6.......6.......2..7.41......5....9..1..7....4......48.2..5.......3.......7.92"));

		long start = System.currentTimeMillis();
		sudoku.solve();
		System.err.println(System.currentTimeMillis() - start);

		System.err.println(sudoku);

		System.err.println(sudoku.groups[ROW][5].groupCells[1].candidates);
	}
}
