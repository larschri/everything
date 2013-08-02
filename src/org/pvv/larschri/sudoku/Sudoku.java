package org.pvv.larschri.sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.primitives.Chars;


public class Sudoku {

	private int recursionDepth = 4; // don't care about larger clusters than this

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

		/** Detect smaller groups of cells that has the same candidate symbols. If a group of
		 *  n cells has n candidate symbols, then these candidates can be eliminated from 
		 *  other cells.
		 */
		boolean clusterCells(Set<Character> symbols, Set<Cell> cells, List<Cell> more) {
			boolean changed = false;
			if (symbols.size() == cells.size()) {
				for (Cell cell : this.groupCells)
					if (!cells.contains(cell))
						changed |= cell.eliminateCandidates(symbols);
			} else if (symbols.size() < recursionDepth) {
				for (int i = 0; i < more.size(); i++) {
					Cell c = more.get(i);
					if (!c.isSolved())
						changed |= clusterCells(
								Sets.union(symbols, c.candidates),
								Sets.union(cells, Collections.singleton(c)),
								more.subList(i + 1, more.size()));
				}
			}

			return changed;
		}

		boolean clusterCells() {
			boolean changed = false;
			List<Cell> cellList = Arrays.asList(groupCells);
			for (int i = 0; i < groupCells.length; i++) {
				changed |= clusterCells(groupCells[i].candidates, Collections.singleton(groupCells[i]), cellList.subList(i+1, groupCells.length));
			}
			return changed;
		}

		List<Entry<Character, Collection<Cell>>> cellsByCandidates() {
			Multimap<Character, Cell> result = HashMultimap.create();
			for (Cell cell : groupCells)
				for (Character c : cell.candidates)
					result.put(c, cell);
			return new ArrayList<>(result.asMap().entrySet());
		}

		/**
		 * Detect smaller groups of symbols that are in the same cells. If a group of n symbols
		 * are in n cells, then remove other candidates from those cells.
		 */
		boolean clusterSymbols(Set<Character> symbols, Set<Cell> cells, List<Entry<Character, Collection<Cell>>> more) {
			boolean changed = false;
			if (symbols.size() == cells.size()) {
				for (Cell cell : cells)
					changed |= cell.retainCandidates(symbols);
			} else if (cells.size() < recursionDepth) {
				for (int i = 0; i < more.size(); i++) {
					Entry<Character, Collection<Cell>> first = more.get(0);
					List<Entry<Character, Collection<Cell>>> rest = more.subList(1, more.size());
					changed |= clusterSymbols(
							Sets.union(symbols, Collections.singleton(first.getKey())),
							Sets.union(cells, (Set<Cell>) first.getValue()),
							rest);
				}
			}
			return changed;
		}

		boolean clusterSymbols() {
			boolean changed = false;
			List<Entry<Character, Collection<Cell>>> cellList = cellsByCandidates();
			for (int i = 0; i < cellList.size(); i++) {
				Entry<Character, Collection<Cell>> first = cellList.get(i);
				changed |= clusterSymbols(
						Collections.singleton(first.getKey()),
						(Set<Cell>) first.getValue(),
						cellList.subList(i+1, cellList.size()));
			}
			return changed;
		}

		/** 
		 * Propagates findings from one group to another when possible.
		 * Example: if a box fixes a symbol to a row within the box, then remove the
		 * symbol from the other cells in the row.
		 */
		boolean propagateClusters() {
			boolean changed = false;
			for (Entry<Character, Collection<Cell>> entry : cellsByCandidates()) {
				Set<Group> groups = new HashSet<>(entry.getValue().iterator().next().cellGroups);
				for (Cell cell : entry.getValue())
					groups.retainAll(cell.cellGroups);

				for (Group group : groups)
					if (group != this)
						for (Cell cell : group.groupCells)
							if (!entry.getValue().contains(cell))
								changed |= cell.eliminateCandidates(Collections.singleton(entry.getKey()));
			}
			return changed;
		}
	}

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
		assert rows.length == symbols.size();
		for (int i = 0; i < rows.length; i++) {
			String row = rows[i];
			assert row.length() == symbols.size();
			for (int j = 0; j < row.length(); j++) {
				Character c = row.charAt(j);
				if (symbols.contains(c)) {
					groups[ROW][i].groupCells[j].setCandidates(Collections.singleton(c));
				}
			}
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

	/** See {@link Group#clusterCells()} */
	boolean clusterCells() {
		boolean changed = false;
		for (Group[] gs : groups)
			for (Group g : gs)
				changed |= g.clusterCells();
		return changed;
	}

	/** See {@link Group#clusterSymbols()} */
	boolean clusterSymbols() {
		boolean changed = false;
		for (Group[] gs : groups)
			for (Group g : gs)
				changed |= g.clusterSymbols();
		return changed;
	}

	boolean propagateClusters() {
		boolean changed = false;
		for (Group[] gs : groups)
			for (Group g : gs)
				changed |= g.propagateClusters();
		return changed;
	}

	boolean isDone() {
		for (Cell cell : cells)
			if (cell.candidates.size() > 1)
				return false;
		return true;
	}

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
			while (clusterCells() || clusterSymbols() || propagateClusters()) {
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
		sudoku.init(
				".94...13.",
				".........",
				"....76..2",
				".8..1....",
				".32......",
				"...2...6.",
				"....5.4..",
				".....8..7",
				"..63.4..8");

		long start = System.currentTimeMillis();
		sudoku.solve();
		System.err.println(System.currentTimeMillis() - start);

		System.err.println(sudoku);

		System.err.println(sudoku.groups[ROW][5].groupCells[1].candidates);
	}
}
