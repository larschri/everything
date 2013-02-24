package org.pvv.larschri.gameday.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * This file was created by
 * <ul>
 *  <li>Download http://gd2.mlb.com/components/game/mlb/year_2012/month_06/day_13/gid_2012_06_13_houmlb_sfnmlb_1/inning/inning_all.xml</li>
 *  <li>Create xsd on: http://xmlgrid.net/xml2xsd.html</li>
 *  <li>Generate java: xjc ~/Downloads/game.xsd -p org.pvv.larschri.gameday.xml -d src</li>
 *  <li>Manually fix bugs, remove verbose and redundant parts and add types that the tools decided to skip.
 * </ul>
 */
@XmlRootElement(name = "game")
public class InningAll {

	@XmlElement(required = true) public List<Inning> inning = new ArrayList<>();
	@XmlAttribute public Integer atBat;
	@XmlAttribute public Integer deck;
	@XmlAttribute public Integer hole;
	@XmlAttribute public String ind;

	public static class Inning {

		@XmlElement(required = true) public InningAll.Inning.HalfInning top;
		@XmlElement(required = true) public InningAll.Inning.HalfInning bottom;
		@XmlAttribute public Integer num;
		@XmlAttribute(name = "away_team") public String awayTeam;
		@XmlAttribute(name = "home_team") public String homeTeam;
		@XmlAttribute public String next;

		public static class HalfInning {

			@XmlElement(required = true) public List<Atbat> atbat = new ArrayList<>();
			@XmlElement(required = false) public List<Action> action = new ArrayList<>();

			public static class Action {
				@XmlAttribute public Integer b;
				@XmlAttribute public Integer s;
				@XmlAttribute public Integer o;
				@XmlAttribute public String des;
				@XmlAttribute public String event;
				@XmlAttribute public Integer player;
				@XmlAttribute public Integer pitch;
				@XmlAttribute(name = "tfs_zulu") @XmlSchemaType(name = "dateTime") public XMLGregorianCalendar tfsZulu;
			}

			public static class Atbat {

				@XmlElement(required = true) public List<Pitch> pitch = new ArrayList<>();
				@XmlElement(required = false) public List<Runner> runner = new ArrayList<>();
				@XmlAttribute public Integer num;
				@XmlAttribute public Integer b;
				@XmlAttribute public Integer s;
				@XmlAttribute public Integer o;
				@XmlAttribute(name = "start_tfs") public Integer startTfs;
				@XmlAttribute(name = "start_tfs_zulu") @XmlSchemaType(name = "dateTime") public XMLGregorianCalendar startTfsZulu;
				@XmlAttribute public Integer batter;
				@XmlAttribute public String stand;
				@XmlAttribute(name = "b_height") public String bHeight;
				@XmlAttribute public Integer pitcher;
				@XmlAttribute(name = "p_throws") public String pThrows;
				@XmlAttribute public String des;
				@XmlAttribute public String event;

				public static class Runner {
					@XmlAttribute public Integer id;
					@XmlAttribute public String start;
					@XmlAttribute public String end;
					@XmlAttribute public String event;
					@XmlAttribute public String score;
					@XmlAttribute public String rbi;
					@XmlAttribute public String earned;
				}

				public static class Pitch {
					@XmlAttribute public String des;
					@XmlAttribute public Integer id;
					@XmlAttribute public String type;
					@XmlAttribute public Integer tfs;
					@XmlAttribute(name = "tfs_zulu") @XmlSchemaType(name = "dateTime") public XMLGregorianCalendar tfsZulu;
					@XmlAttribute public Double x;
					@XmlAttribute public Double y;
					@XmlAttribute(name = "sv_id") public String svId;
					@XmlAttribute(name = "start_speed") public Double startSpeed;
					@XmlAttribute(name = "end_speed") public Double endSpeed;
					@XmlAttribute(name = "sz_top") public Double szTop;
					@XmlAttribute(name = "sz_bot") public Double szBot;
					@XmlAttribute(name = "pfx_x") public Double pfxX;
					@XmlAttribute(name = "pfx_z") public Double pfxZ;
					@XmlAttribute public Double px;
					@XmlAttribute public Double pz;
					@XmlAttribute public Double x0;
					@XmlAttribute public Double y0;
					@XmlAttribute public Double z0;
					@XmlAttribute public Double vx0;
					@XmlAttribute public Double vy0;
					@XmlAttribute public Double vz0;
					@XmlAttribute public Double ax;
					@XmlAttribute public Double ay;
					@XmlAttribute public Double az;
					@XmlAttribute(name = "break_y") public Double breakY;
					@XmlAttribute(name = "break_angle") public Double breakAngle;
					@XmlAttribute(name = "break_length") public Double breakLength;
					@XmlAttribute(name = "pitch_type") public String pitchType;
					@XmlAttribute(name = "type_confidence") public Double typeConfidence;
					@XmlAttribute public Integer zone;
					@XmlAttribute public Integer nasty;
					@XmlAttribute(name = "spin_dir") public Double spinDir;
					@XmlAttribute(name = "spin_rate") public Double spinRate;
					@XmlAttribute public String cc;
					@XmlAttribute public String mt;
				}
			}
		}
	}
}
