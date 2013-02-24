package org.pvv.larschri.gameday.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represent partial xml format for
 * http://gd2.mlb.com/components/game/mlb/year_2012/month_06/day_13/grid.xml
 * <p>
 * The purpose of this file is to derive the directory names of each game, and
 * sub elements in grid.xml are currently ignored.
 */
@XmlRootElement(name = "games")
public class Grid {
	@XmlElement(required = true) public List<Game> game = new ArrayList<>();

	public static class Game {
		@XmlAttribute(name = "game_calendar_event_id") public String gameGalendarEventId;
		@XmlAttribute(name = "id") public String id;
		@XmlAttribute(name = "game_pk") public Integer gamePk;
		@XmlAttribute(name = "group") public String group;
		@XmlAttribute(name = "game_type") public String gameType;
		@XmlAttribute(name = "event_time") public String eventTime;
		@XmlAttribute(name = "venue") public String venue;
		@XmlAttribute(name = "venue_id") public String venueId;
		@XmlAttribute(name = "status") public String status;
		@XmlAttribute(name = "ind") public String ind;
		@XmlAttribute(name = "inning") public String inning;
		@XmlAttribute(name = "top_inning") public String topInning;
		@XmlAttribute(name = "away_code") public String awayCode;
		@XmlAttribute(name = "away_file_code") public String awayFileCode;
		@XmlAttribute(name = "away_team_id") public String awayTeamId;
		@XmlAttribute(name = "away_name_abbrev") public String awayNameAbbrev;
		@XmlAttribute(name = "away_team_name") public String awayTeamName;
		@XmlAttribute(name = "away_score") public String awayScore;
		@XmlAttribute(name = "home_code") public String homeCode;
		@XmlAttribute(name = "home_file_code") public String homeFileCode;
		@XmlAttribute(name = "home_team_id") public String homeTeamId;
		@XmlAttribute(name = "home_name_abbrev") public String homeNameAbbrev;
		@XmlAttribute(name = "home_team_name") public String homeTeamName;
		@XmlAttribute(name = "home_score") public String homeScore;
		@XmlAttribute(name = "gameday_sw") public String gamedaySw;
		@XmlAttribute(name = "media_state") public String mediaState;
	}
}
