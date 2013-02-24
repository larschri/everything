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
	@XmlElement(required = true) protected List<Game> game = new ArrayList<>();

	public static class Game {
		@XmlAttribute(name = "game_calendar_event_id") protected String gameGalendarEventId;
		@XmlAttribute(name = "id") protected String id;
		@XmlAttribute(name = "game_pk") protected Integer gamePk;
		@XmlAttribute(name = "group") protected String group;
		@XmlAttribute(name = "game_type") protected String gameType;
		@XmlAttribute(name = "event_time") protected String eventTime;
		@XmlAttribute(name = "venue") protected String venue;
		@XmlAttribute(name = "venue_id") protected String venueId;
		@XmlAttribute(name = "status") protected String status;
		@XmlAttribute(name = "ind") protected String ind;
		@XmlAttribute(name = "inning") protected String inning;
		@XmlAttribute(name = "top_inning") protected String topInning;
		@XmlAttribute(name = "away_code") protected String awayCode;
		@XmlAttribute(name = "away_file_code") protected String awayFileCode;
		@XmlAttribute(name = "away_team_id") protected String awayTeamId;
		@XmlAttribute(name = "away_name_abbrev") protected String awayNameAbbrev;
		@XmlAttribute(name = "away_team_name") protected String awayTeamName;
		@XmlAttribute(name = "away_score") protected String awayScore;
		@XmlAttribute(name = "home_code") protected String homeCode;
		@XmlAttribute(name = "home_file_code") protected String homeFileCode;
		@XmlAttribute(name = "home_team_id") protected String homeTeamId;
		@XmlAttribute(name = "home_name_abbrev") protected String homeNameAbbrev;
		@XmlAttribute(name = "home_team_name") protected String homeTeamName;
		@XmlAttribute(name = "home_score") protected String homeScore;
		@XmlAttribute(name = "gameday_sw") protected String gamedaySw;
		@XmlAttribute(name = "media_state") protected String mediaState;
	}
}
