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
public class Game {

	@XmlElement(required = true) protected List<Inning> inning = new ArrayList<>();
	@XmlAttribute protected Integer atBat;
	@XmlAttribute protected Integer deck;
	@XmlAttribute protected Integer hole;
	@XmlAttribute protected String ind;

	public static class Inning {

		@XmlElement(required = true) protected Game.Inning.HalfInning top;
		@XmlElement(required = true) protected Game.Inning.HalfInning bottom;
		@XmlAttribute protected Integer num;
		@XmlAttribute(name = "away_team") protected String awayTeam;
		@XmlAttribute(name = "home_team") protected String homeTeam;
		@XmlAttribute protected String next;

		public static class HalfInning {

			@XmlElement(required = true) protected List<Atbat> atbat = new ArrayList<>();
			@XmlElement(required = false) protected List<Action> action = new ArrayList<>();

			public static class Action {
				@XmlAttribute protected Integer b;
				@XmlAttribute protected Integer s;
				@XmlAttribute protected Integer o;
				@XmlAttribute protected String des;
				@XmlAttribute protected String event;
				@XmlAttribute protected Integer player;
				@XmlAttribute protected Integer pitch;
				@XmlAttribute(name = "tfs_zulu") @XmlSchemaType(name = "dateTime") protected XMLGregorianCalendar tfsZulu;
			}

			public static class Atbat {

				@XmlElement(required = true) protected List<Pitch> pitch = new ArrayList<>();
				@XmlElement(required = false) protected List<Runner> runner = new ArrayList<>();
				@XmlAttribute protected Integer num;
				@XmlAttribute protected Integer b;
				@XmlAttribute protected Integer s;
				@XmlAttribute protected Integer o;
				@XmlAttribute(name = "start_tfs") protected Integer startTfs;
				@XmlAttribute(name = "start_tfs_zulu") @XmlSchemaType(name = "dateTime") protected XMLGregorianCalendar startTfsZulu;
				@XmlAttribute protected Integer batter;
				@XmlAttribute protected String stand;
				@XmlAttribute(name = "b_height") protected String bHeight;
				@XmlAttribute protected Integer pitcher;
				@XmlAttribute(name = "p_throws") protected String pThrows;
				@XmlAttribute protected String des;
				@XmlAttribute protected String event;

				public static class Runner {
					@XmlAttribute protected Integer id;
					@XmlAttribute protected String start;
					@XmlAttribute protected String end;
					@XmlAttribute protected String event;
					@XmlAttribute protected String score;
					@XmlAttribute protected String rbi;
					@XmlAttribute protected String earned;
				}

				public static class Pitch {
					@XmlAttribute protected String des;
					@XmlAttribute protected Integer id;
					@XmlAttribute protected String type;
					@XmlAttribute protected Integer tfs;
					@XmlAttribute(name = "tfs_zulu") @XmlSchemaType(name = "dateTime") protected XMLGregorianCalendar tfsZulu;
					@XmlAttribute protected Double x;
					@XmlAttribute protected Double y;
					@XmlAttribute(name = "sv_id") protected String svId;
					@XmlAttribute(name = "start_speed") protected Double startSpeed;
					@XmlAttribute(name = "end_speed")protected Double endSpeed;
					@XmlAttribute(name = "sz_top") protected Double szTop;
					@XmlAttribute(name = "sz_bot") protected Double szBot;
					@XmlAttribute(name = "pfx_x") protected Double pfxX;
					@XmlAttribute(name = "pfx_z") protected Double pfxZ;
					@XmlAttribute protected Double px;
					@XmlAttribute protected Double pz;
					@XmlAttribute protected Double x0;
					@XmlAttribute protected Double y0;
					@XmlAttribute protected Double z0;
					@XmlAttribute protected Double vx0;
					@XmlAttribute protected Double vy0;
					@XmlAttribute protected Double vz0;
					@XmlAttribute protected Double ax;
					@XmlAttribute protected Double ay;
					@XmlAttribute protected Double az;
					@XmlAttribute(name = "break_y") protected Double breakY;
					@XmlAttribute(name = "break_angle") protected Double breakAngle;
					@XmlAttribute(name = "break_length") protected Double breakLength;
					@XmlAttribute(name = "pitch_type") protected String pitchType;
					@XmlAttribute(name = "type_confidence") protected Double typeConfidence;
					@XmlAttribute protected Integer zone;
					@XmlAttribute protected Integer nasty;
					@XmlAttribute(name = "spin_dir") protected Double spinDir;
					@XmlAttribute(name = "spin_rate") protected Double spinRate;
					@XmlAttribute protected String cc;
					@XmlAttribute protected String mt;
				}
			}
		}
	}
}
