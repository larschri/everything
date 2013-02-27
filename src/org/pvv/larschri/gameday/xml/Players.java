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
	@XmlElement(required = true) public List<Team> team = new ArrayList<>();
	@XmlElement(required = true) public Umpires umpires;
	@XmlAttribute public String venue;
	@XmlAttribute public String date;

	public static class Team {
		@XmlElement(required = true) public List<Player> player = new ArrayList<>();
		@XmlElement(required = true) public List<Coach> coach = new ArrayList<>();

		@XmlAttribute public String type;
		@XmlAttribute public String id;
		@XmlAttribute public String name;

		public static class Player {
			@XmlAttribute public Integer id;
			@XmlAttribute public String first;
			@XmlAttribute public String last;
			@XmlAttribute public Integer num;
			@XmlAttribute public String boxname;
			@XmlAttribute public String rl;
			@XmlAttribute public String position;
			@XmlAttribute public String status;
			@XmlAttribute(name = "team_abbrev") public String teamAbbrev;
			@XmlAttribute(name = "parent_team_abbrev") public String parentTeamAbbrev;
			@XmlAttribute(name = "parent_team_id") public Integer parentTeamId;
			@XmlAttribute public Double avg;
			@XmlAttribute public Integer hr;
			@XmlAttribute public Integer rbi;
			@XmlAttribute public Integer wins;
			@XmlAttribute public Integer losses;
			@XmlAttribute public String era; // Mostly double, sometimes dash.
		}

		public static class Coach {
			@XmlAttribute public String position;
			@XmlAttribute public String first;
			@XmlAttribute public String last;
			@XmlAttribute public Integer id;
			@XmlAttribute public Integer num;
		}
	}

	public static class Umpires {
		@XmlElement(required = true) public List<Umpire> umpire = new ArrayList<>();

		public static class Umpire {
			@XmlAttribute public String position;
			@XmlAttribute public String name;
			@XmlAttribute public Integer id;
		}
	}
}
