package org.pvv.larschri.gameday.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Used to parse http://gd2.mlb.com/components/game/mlb/year_2012/month_06/day_13/gid_2012_06_13_houmlb_sfnmlb_1/players.xml
 */
@XmlRootElement(name = "game")
public class Players {
	@XmlElement(required = true) protected List<Team> team = new ArrayList<>();
	@XmlElement(required = true) protected Umpires umpires;
	@XmlAttribute protected String venue;
	@XmlAttribute protected String date;

	public static class Team {
		@XmlElement(required = true) protected List<Player> player = new ArrayList<>();
		@XmlElement(required = true) protected List<Coach> coach = new ArrayList<>();

		@XmlAttribute protected String type;
		@XmlAttribute protected String id;
		@XmlAttribute protected String name;

		public static class Player {
			@XmlAttribute protected Integer id;
			@XmlAttribute protected String first;
			@XmlAttribute protected String last;
			@XmlAttribute protected Integer num;
			@XmlAttribute protected String boxname;
			@XmlAttribute protected String rl;
			@XmlAttribute protected String position;
			@XmlAttribute protected String status;
			@XmlAttribute(name = "team_abbrev") protected String teamAbbrev;
			@XmlAttribute(name = "parent_team_abbrev") protected String parentTeamAbbrev;
			@XmlAttribute(name = "parent_team_id") protected Integer parentTeamId;
			@XmlAttribute protected Double avg;
			@XmlAttribute protected Integer hr;
			@XmlAttribute protected Integer rbi;
			@XmlAttribute protected Integer wins;
			@XmlAttribute protected Integer losses;
			@XmlAttribute protected Double era;
		}

		public static class Coach {
			@XmlAttribute protected String position;
			@XmlAttribute protected String first;
			@XmlAttribute protected String last;
			@XmlAttribute protected Integer id;
			@XmlAttribute protected Integer num;
		}
	}

	public static class Umpires {
		@XmlElement(required = true) protected List<Umpire> umpire = new ArrayList<>();

		public static class Umpire {
			@XmlAttribute protected String position;
			@XmlAttribute protected String name;
			@XmlAttribute protected Integer id;
		}
	}
}
