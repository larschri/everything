package org.pvv.larschri.gameday;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.pvv.larschri.gameday.xml.Grid.Game;
import org.pvv.larschri.gameday.xml.InningAll;
import org.pvv.larschri.gameday.xml.InningAll.Inning;
import org.pvv.larschri.gameday.xml.InningAll.Inning.HalfInning;
import org.pvv.larschri.gameday.xml.InningAll.Inning.HalfInning.Atbat;
import org.pvv.larschri.gameday.xml.InningAll.Inning.HalfInning.Atbat.Pitch;
import org.pvv.larschri.gameday.xml.InningAll.Inning.HalfInning.Atbat.Runner;
import org.pvv.larschri.gameday.xml.Players;
import org.pvv.larschri.gameday.xml.Players.Team.Player;

/**
 * Class for calculating stats per {@link Pitch}. It can be used to build a
 * matrix of {@link Pitch}es and {@link PitchColumn}s.
 */
public class PitchMatrix {

	/**
	 * Interface used to retrieve a value for a {@link Pitch}.
	 */
	public interface PitchColumn<X> {
		public X get(Pitch pitch);
	}

	public static final PitchColumn<Double> START_SPEED = new PitchColumn<Double> () {
		@Override public Double get(Pitch pitch) {
			return pitch.startSpeed;
		}
	};

	/**
	 * Found at http://pitchfx.texasleaguers.com/league-averages.php. Not sure
	 * if all of these are in actual use or if any is missing.
	 */
	public enum PitchType {
		FA, // Fastball
		FF, // 4-seam Fastball
		FT, // 2-seam Fastball
		FC, // Cut Fastball
		FS, // Split-finger Fastball
		FO, // Forkball
		SI, // Sinker
		SL, // Slider
		CU, // Curveball
		KC, // Knuckle Curve
		EP, // Ephuus
		CH, // Change-up
		SC, // Screwball
		KN, // Knuckleball
		UN, // Unknown
		IN, // ?
		PO, // ?
		AB, // ?
	}

	public enum Orientation {
		R,
		L
	}

	public static final PitchColumn<PitchType> PITCH_TYPE = new PitchColumn<PitchType> () {
		@Override public PitchType get(Pitch pitch) {
			return pitch.pitchType == null ? null : PitchType.valueOf(pitch.pitchType);
		}
	};

	enum Type {
		B, // ball
		S, // strike
		X, // in play
	}

	public static final PitchColumn<Type> TYPE = new PitchColumn<Type> () {
		@Override public Type get(Pitch pitch) {
			return Type.valueOf(pitch.type);
		}
	};


	private class PlayerStat {
		final PitchColumn<Player> player;

		PlayerStat(PitchColumn<Player> player) {
			this.player = player;
		}

		PitchColumn<Integer> teamId = new PitchColumn<Integer> () {
			@Override public Integer get(Pitch pitch) {
				return player.get(pitch).teamId;
			}
		};

		private Double parse(String s) {
			try {
				return Double.valueOf(s);
			} catch (NumberFormatException e) {
				return Double.NaN;
			}
		}

		PitchColumn<Double> era = new PitchColumn<Double> () {
			@Override public Double get(Pitch pitch) {
				return parse(pitcherStats.get(pitch).era);
			}
		};

		PitchColumn<Double> avg = new PitchColumn<Double> () {
			@Override public Double get(Pitch pitch) {
				return batterStats.get(pitch).avg;
			}
		};
	}

	private final List<Map<Pitch, ?>> allMaps = new ArrayList<>();

	/**
	 * {@link PitchColumn} for stuff that depends on more than a single
	 * {@link Pitch}, so the stats are stored in a map. Other {@link PitchColumn}
	 * can depend on a {@link MapStat}.
	 */
	class MapStat<X> implements PitchColumn<X> {
		private final Map<Pitch, X> map = new HashMap<>();
		{ allMaps.add(map); }

		@Override public X get(Pitch pitch) {
			return map.get(pitch);
		}
	}

	private final MapStat<Atbat> atbatStats = new MapStat<>();
	private final MapStat<HalfInning> halfInningStats = new MapStat<>();
	private final MapStat<Integer> inningStats = new MapStat<>();
	/** The total pitch count for the pitcher. */
	private final MapStat<Integer> pitchCountStats = new MapStat<>();
	/** Number of balls in the current count for the at bat. */
	private final MapStat<Integer> ballCountStats = new MapStat<>();
	/** Number of strikes in the current count for the at bat. */
	private final MapStat<Integer> strikeCountStats = new MapStat<>();
	/** The bat order number of the current batter. */
	private final MapStat<Integer> batOrderStats = new MapStat<>();
	/** The base reached by the batter. Recorded for the last pitch. */
	private final MapStat<Integer> sluggingStats = new MapStat<>();
	/** Recorded as 1 for the last pitch if the at bat ends in a walk */
	private final MapStat<Integer> walkStats = new MapStat<>();
	/** Number of earned runs made in an at bat. Recorded for the last pitch. */
	private final MapStat<Integer> eraStats = new MapStat<>();
	/** Number of RBIs made in an at bat. Recorded for the last pitch. */
	private final MapStat<Integer> rbiStats = new MapStat<>();
	/** Number of outs made in an at bat. Recorded for the last pitch. */
	private final MapStat<Integer> outStats = new MapStat<>();
	private final MapStat<Orientation> pitcherHandStats = new MapStat<>();
	private final MapStat<Orientation> batterStandStats = new MapStat<>();
	private final MapStat<Player> batterStats = new MapStat<>();
	private final MapStat<Player> pitcherStats = new MapStat<>();
	private final PlayerStat batterStat = new PlayerStat(batterStats);
	private final PlayerStat pitcherStat = new PlayerStat(pitcherStats);

	private final List<Pitch> pitches = new ArrayList<>();

	public void load(Game game, Downloader downloader) throws IOException, JAXBException {
		List<List<HalfInning>> topsAndBottoms = toTopsAndBottoms(downloader.inningAll(game));
		Map<Integer, Player> players = toPlayerMap(downloader.players(game));
		load(topsAndBottoms.get(0), players);
		load(topsAndBottoms.get(1), players);
	}

	/** @param halfInningList {@link List} containing all of either {@link Inning#top} or {@link Inning#bottom} from a full {@link Game}. */
	private void load(List<HalfInning> halfInningList, Map<Integer, Player> players) {
		int pitchCount = 0;
		int pitcher = -1;
		int batCount = 0;
		int inningCount = 1;

		for (HalfInning halfInning : halfInningList) {
			for (Atbat atbat : halfInning.atbat) {
				if (atbat.pitcher != pitcher) {
					pitcher = atbat.pitcher;
					pitchCount = 0;
				}
				pitches.addAll(atbat.pitch);
				for (Pitch pitch : atbat.pitch) {
					inningStats.map.put(pitch, inningCount);
					atbatStats.map.put(pitch, atbat);
					halfInningStats.map.put(pitch, halfInning);
					pitchCountStats.map.put(pitch, pitchCount++);
					batOrderStats.map.put(pitch, batCount % 9);
					batterStats.map.put(pitch, players.get(atbat.batter));
					pitcherStats.map.put(pitch, players.get(atbat.pitcher));
				}

				int ballCount = 0, strikeCount = 0;
				for (Pitch pitch : atbat.pitch) {
					strikeCountStats.map.put(pitch, Math.min(strikeCount, 2));
					ballCountStats.map.put(pitch, ballCount);

					switch(TYPE.get(pitch)) {
						case B: ballCount++; break;
						case S: strikeCount++; break;
						case X: break;
					}
				}

				Orientation pitcherHand = Orientation.valueOf(atbat.pThrows);
				Orientation batterStand = Orientation.valueOf(atbat.stand);
				for (Pitch pitch : atbat.pitch) {
					pitcherHandStats.map.put(pitch, pitcherHand);
					batterStandStats.map.put(pitch, batterStand);
				}
				batCount++;
			}
			inningCount++;
		}

		for (HalfInning halfInning : halfInningList) {
			int outs = 0;
			for (Atbat atbat : halfInning.atbat) {
				Runner batterRunner = null;
				int rbi = 0;
				int earned = 0;
				for (Runner runner : atbat.runner) {
					if (runner.start.equals("")) {
						batterRunner = runner;
					}
					if ("T".equals(runner.rbi)) rbi++;
					if ("T".equals(runner.earned)) earned++;
				}

				// Populate the last pitch with the atbat stats
				if (!atbat.pitch.isEmpty()) {
					Pitch lastPitch = atbat.pitch.get(atbat.pitch.size() - 1);
					eraStats.map.put(lastPitch, earned);
					rbiStats.map.put(lastPitch, rbi);
					int slugging = 0;
					int walk = 0;
					if (batterRunner != null) {
						if (batterRunner.event.toLowerCase().contains("walk")) {
							walk = 1;
						} else {
							slugging = getBase(batterRunner.end, 4);
						}
					}
					sluggingStats.map.put(lastPitch, slugging);
					walkStats.map.put(lastPitch, walk);
					outStats.map.put(lastPitch, atbat.o - outs);
				}
				outs = atbat.o;
			}
		}
	}

	private static int getBase(String s, int n) {
		if (s.equals("1B")) return 1;
		else if (s.equals("2B")) return 2;
		else if (s.equals("3B")) return 3;
		else return n;
	}

	/** @return all values for the given {@link PitchColumn} */
	<X> List<X> getAll(PitchColumn<X> stat) {
		List<X> list = new ArrayList<>();
		for (Pitch p : pitches)
			list.add(stat.get(p));
		return list;
	}

	public PitchColumn<Double> getPitcherERA() { return pitcherStat.era; }
	public PitchColumn<Double> getBatterAVG() { return batterStat.avg; }
	public PitchColumn<Integer> getPitcherTeam() { return pitcherStat.teamId; }
	public PitchColumn<Integer> getBatterTeam() { return batterStat.teamId; }
	public PitchColumn<Double> getPitchSpeed() { return START_SPEED; }
	public PitchColumn<PitchType> getPitchType() { return PITCH_TYPE; }
	public PitchColumn<Type> getType() { return TYPE; }
	/** The inning. */
	public PitchColumn<Integer> getInning() { return inningStats; }
	/** The total pitch count for the pitcher. */
	public PitchColumn<Integer> getPitchCount() { return pitchCountStats; }
	/** Number of balls in the current count for the at bat. */
	public PitchColumn<Integer> getBallScore() { return ballCountStats; }
	/** Number of strikes in the current count for the at bat. */
	public PitchColumn<Integer> getStrikeScore() { return strikeCountStats; }
	public PitchColumn<Player> getBatter() { return batterStats; }
	public PitchColumn<Player> getPitcher() { return pitcherStats; }
	/** Number of earned runs made in an at bat. Recorded for the last pitch. */
	public PitchColumn<Integer> getEarnedRuns() { return eraStats; }
	/** Number of RBIs made in an at bat. Recorded for the last pitch. */
	public PitchColumn<Integer> getRBI() { return rbiStats; }
	/** The base reached by the batter. Recorded for the last pitch. */
	public PitchColumn<Integer> getSlugging() { return sluggingStats; }
	/** Recorded as 1 for the last pitch if the at bat ends in a walk */
	public PitchColumn<Integer> getWalk() { return walkStats; }
	/** Number of outs made in an at bat. Recorded for the last pitch. */
	public PitchColumn<Integer> getOuts() { return outStats; }
	public PitchColumn<Orientation> getPitcherHand(){ return pitcherHandStats; }
	public PitchColumn<Orientation> getBatterStand() { return batterStandStats; }

	public List<Pitch> getPitches() {
		return Collections.unmodifiableList(pitches);
	}

	/** Helper method to organize xml data */
	private static Map<Integer, Player> toPlayerMap(Collection<Player> players) {
		Map<Integer, Player> playerMap = new HashMap<>();
		for (Player player : players)
			playerMap.put(player.id, player);
		return playerMap;
	}

	/** Helper method to organize xml data */
	public static Map<Integer, Player> toPlayerMap(Players players) {
		List<Player> playerList = new ArrayList<>(players.team.get(0).player);
		playerList.addAll(players.team.get(1).player);
		return toPlayerMap(playerList);
	}

	/** Helper method to organize xml data */
	public static List<List<HalfInning>> toTopsAndBottoms(InningAll inningAll) {
		List<HalfInning> topInnings = new ArrayList<>();
		List<HalfInning> bottomInnings = new ArrayList<>();
		for (Inning inning : inningAll.inning) {
			topInnings.add(inning.top);
			if (inning.bottom != null)
				bottomInnings.add(inning.bottom);
		}
		return Arrays.asList(topInnings, bottomInnings);
	}

	/** Load data for the given interval */
	public void load(Calendar from, Calendar to, Downloader downloader) throws IOException, JAXBException {
		for (Calendar c = (Calendar) from.clone(); c.before(to); c.add(Calendar.DAY_OF_MONTH, 1)) {
			for (Game game : downloader.grid(c).game) {
				if (!game.mediaState.equals("media_dead"))
					load(game, downloader);
			}
		}
	}

	/** Test stuff */
	public static void main(String[] args) throws NumberFormatException, IOException, JAXBException {
		Downloader downloader = new Downloader(Paths.get(System.getProperty("user.home") + "/.gameday"), new URL("http://gd2.mlb.com"));
		PitchMatrix pitchMatrix = new PitchMatrix();
		pitchMatrix.load(
				new GregorianCalendar(2012, Calendar.SEPTEMBER, 1),
				new GregorianCalendar(2012, Calendar.OCTOBER, 1),
				downloader);
	}
}
