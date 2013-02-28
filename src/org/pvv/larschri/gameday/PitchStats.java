package org.pvv.larschri.gameday;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
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
import org.pvv.larschri.gameday.xml.Players;
import org.pvv.larschri.gameday.xml.Players.Team.Player;

/** Class for calculating stats per {@link Pitch} */
public class PitchStats {

	interface PitchStat<X> {
		X get(Pitch pitch);
	}

	public static final PitchStat<Double> START_SPEED = new PitchStat<Double> () {
		@Override public Double get(Pitch pitch) {
			return pitch.startSpeed;
		}
	};

	/**
	 * Found at http://pitchfx.texasleaguers.com/league-averages.php. Not sure
	 * if all of these are in actual use or if any is missing.
	 */
	enum PitchType {
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
	}

	public static final PitchStat<PitchType> PITCH_TYPE = new PitchStat<PitchType> () {
		@Override public PitchType get(Pitch pitch) {
			return PitchType.valueOf(pitch.pitchType);
		}
	};

	enum Type {
		B, // ball
		S, // strike
		X, // in play
	}

	public static final PitchStat<Type> TYPE = new PitchStat<Type> () {
		@Override public Type get(Pitch pitch) {
			return Type.valueOf(pitch.type);
		}
	};

	PitchStat<Player> pitcher = new PitchStat<Player> () {
		@Override public Player get(Pitch pitch) {
			return players.get(atbatStats.get(pitch).pitcher);
		}
	};

	private class PlayerStat {
		final PitchStat<Integer> playerId;

		PlayerStat(PitchStat<Integer> playerId) {
			this.playerId = playerId;
		}

		PitchStat<Player> player = new PitchStat<Player> () {
			@Override public Player get(Pitch pitch) {
				return players.get(playerId.get(pitch));
			}
		};

		PitchStat<Integer> teamId = new PitchStat<Integer> () {
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

		PitchStat<Double> era = new PitchStat<Double> () {
			@Override public Double get(Pitch pitch) {
				return parse(pitcher.get(pitch).era);
			}
		};

		PitchStat<Double> avg = new PitchStat<Double> () {
			@Override public Double get(Pitch pitch) {
				return player.get(pitch).avg;
			}
		};
	}

	PlayerStat batterStat = new PlayerStat(new PitchStat<Integer> () {
		@Override public Integer get(Pitch pitch) {
			return atbatStats.get(pitch).batter;
		}
	});

	PlayerStat pitcherStat = new PlayerStat(new PitchStat<Integer> () {
		@Override public Integer get(Pitch pitch) {
			return atbatStats.get(pitch).pitcher;
		}
	});

	/**
	 * {@link PitchStat} for stuff that depends on more than a single
	 * {@link Pitch}, so the stats are stored in a map. Other {@link PitchStat}
	 * can depend on a {@link MapStat}.
	 */
	static class MapStat<X> implements PitchStat<X> {
		private final Map<Pitch, X> map = new HashMap<>();

		@Override public X get(Pitch pitch) {
			return map.get(pitch);
		}
	}

	private final MapStat<Atbat> atbatStats = new MapStat<>();
	private final MapStat<HalfInning> halfInningStats = new MapStat<>();
	private final MapStat<Integer> pitchCountStats = new MapStat<>();
	private final MapStat<Integer> ballCountStats = new MapStat<>();
	private final MapStat<Integer> batOrderStats = new MapStat<>();
	private final MapStat<Integer> strikeCountStats = new MapStat<>();
	private final List<Pitch> pitches = new ArrayList<>();
	private final Map<Integer, Player> players;

	/** @param halfInningList {@link List} containing all of either {@link Inning#top} or {@link Inning#bottom} from a full {@link Game}. */
	public PitchStats(List<HalfInning> halfInningList, Map<Integer, Player> players) {
		this.players = players;
		int pitchCount = 0;
		int pitcher = -1;
		int batCount = 0;

		for (HalfInning halfInning : halfInningList) {
			for (Atbat atbat : halfInning.atbat) {
				if (atbat.pitcher != pitcher) {
					pitcher = atbat.pitcher;
					pitchCount = 0;
				}
				pitches.addAll(atbat.pitch);
				for (Pitch pitch : atbat.pitch) {
					atbatStats.map.put(pitch, atbat);
					halfInningStats.map.put(pitch, halfInning);
					pitchCountStats.map.put(pitch, pitchCount);
					batOrderStats.map.put(pitch, batCount % 9);
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

				batCount++;
			}
		}
	}

	/** @return all values for the given {@link PitchStat} */
	<X> List<X> getAll(PitchStat<X> stat) {
		List<X> list = new ArrayList<>();
		for (Pitch p : pitches)
			list.add(stat.get(p));
		return list;
	}

	/** Return all stats */
	List<PitchStat<?>> getStats() {
		return Arrays.<PitchStat<?>>asList(
				START_SPEED,
				PITCH_TYPE,
				TYPE,
				pitcherStat.era,
				batterStat.avg,
				pitcherStat.teamId,
				batterStat.teamId,
				pitchCountStats,
				ballCountStats,
				strikeCountStats,
				batOrderStats);
	}

	/** Helper method to organize xml data */
	private static Map<Integer, Player> toPlayerMap(Collection<Player> players) {
		Map<Integer, Player> playerMap = new HashMap<>();
		for (Player player : players)
			playerMap.put(player.id, player);
		return playerMap;
	}

	/** Helper method to organize xml data */
	private static Map<Integer, Player> toPlayerMap(Players players) {
		List<Player> playerList = new ArrayList<>(players.team.get(0).player);
		playerList.addAll(players.team.get(1).player);
		return toPlayerMap(playerList);
	}

	/** Helper method to organize xml data */
	private static List<List<HalfInning>> toTopsAndBottoms(InningAll inningAll) {
		List<HalfInning> topInnings = new ArrayList<>();
		List<HalfInning> bottomInnings = new ArrayList<>();
		for (Inning inning : inningAll.inning) {
			topInnings.add(inning.top);
			if (inning.bottom != null)
				bottomInnings.add(inning.bottom);
		}
		return Arrays.asList(topInnings, bottomInnings);
	}

	/** Test stuff */
	public static void main(String[] args) throws NumberFormatException, IOException, JAXBException {
		Downloader downloader = new Downloader(Paths.get(System.getProperty("user.home") + "/.gameday"), new URL("http://gd2.mlb.com"));
		Game game = downloader.grid(2012, 6, 13).game.get(14);

		List<List<HalfInning>> topsAndBottoms = toTopsAndBottoms(downloader.inningAll(game));
		PitchStats pitchStats = new PitchStats(topsAndBottoms.get(0), toPlayerMap(downloader.players(game)));
		System.err.println(pitchStats.getAll(pitchStats.batterStat.avg));
	}
}
