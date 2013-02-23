package org.pvv.larschri.gameday.xml;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * This file was created by 
 * 
 * Generated using xjc ~/Downloads/game.xsd -p org.pvv.larschri.gameday.xml -d src
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
		"inning"
})
@XmlRootElement(name = "game")
public class Game {

	@XmlElement(required = true) protected List<Game.Inning> inning;
	@XmlAttribute protected Integer atBat;
	@XmlAttribute protected Integer deck;
	@XmlAttribute protected Integer hole;
	@XmlAttribute protected String ind;

	public List<Game.Inning> getInning() {
		if (inning == null) {
			inning = new ArrayList<Game.Inning>();
		}
		return this.inning;
	}

	public Integer getAtBat() { return atBat; }
	public void setAtBat(Integer value) { this.atBat = value; }
	public Integer getDeck() { return deck; }
	public void setDeck(Integer value) { this.deck = value; }
	public Integer getHole() { return hole; }
	public void setHole(Integer value) { this.hole = value; }
	public String getInd() { return ind; }
	public void setInd(String value) { this.ind = value; }


	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = {
			"top",
			"bottom"
	})
	public static class Inning {

		@XmlElement(required = true) protected Game.Inning.Top top;
		@XmlElement(required = true) protected Game.Inning.Bottom bottom;
		@XmlAttribute protected Integer num;
		@XmlAttribute(name = "away_team") protected String awayTeam;
		@XmlAttribute(name = "home_team") protected String homeTeam;
		@XmlAttribute protected String next;

		public Game.Inning.Top getTop() { return top; }
		public Game.Inning.Bottom getBottom() { return bottom; }
		public Integer getNum() { return num; }
		public String getAwayTeam() { return awayTeam; }
		public String getNext() { return next; }


		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = {
				"atbat"
		})
		public static class Bottom {

			@XmlElement(required = true) protected List<Game.Inning.Bottom.Atbat> atbat;
			public List<Game.Inning.Bottom.Atbat> getAtbat() {
				if (atbat == null) {
					atbat = new ArrayList<Game.Inning.Bottom.Atbat>();
				}
				return this.atbat;
			}

			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = {
					"pitch"
			})
			public static class Atbat {

				@XmlElement(required = true) protected List<Game.Inning.Bottom.Atbat.Pitch> pitch;
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

				public List<Game.Inning.Bottom.Atbat.Pitch> getPitch() {
					if (pitch == null) {
						pitch = new ArrayList<Game.Inning.Bottom.Atbat.Pitch>();
					}
					return this.pitch;
				}

				public Integer getNum() { return num; }
				public Integer getB() { return b; }
				public Integer getS() { return s; }
				public Integer getO() { return o; }
				public Integer getStartTfs() { return startTfs; }
				public XMLGregorianCalendar getStartTfsZulu() { return startTfsZulu; }
				public Integer getBatter() { return batter; }
				public String getStand() { return stand; }
				public Integer getPitcher() { return pitcher; }
				public String getPThrows() { return pThrows; }
				public String getDes() { return des; }
				public String getEvent() { return event; }

				@XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "")
				public static class Pitch {

					@XmlAttribute protected String des;
					@XmlAttribute protected Integer id;
					@XmlAttribute protected String type;
					@XmlAttribute protected Integer tfs;
					@XmlAttribute(name = "tfs_zulu") @XmlSchemaType(name = "dateTime") protected XMLGregorianCalendar tfsZulu;
					@XmlAttribute protected Double x;
					@XmlAttribute protected Double y;
					@XmlAttribute(name = "sv_id") protected String svId;
					@XmlAttribute(name = "start_speed")protected Double startSpeed;
					@XmlAttribute(name = "end_speed") protected Double endSpeed;
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

					public String getDes() { return des; }
					public Integer getId() { return id; }
					public String getType() { return type; }
					public Integer getTfs() { return tfs; }
					public XMLGregorianCalendar getTfsZulu() { return tfsZulu; }
					public Double getX() { return x; }
					public Double getY() { return y; }
					public String getSvId() { return svId; }
					public Double getStartSpeed() { return startSpeed; }
					public Double getEndSpeed() { return endSpeed; }
					public Double getSzTop() { return szTop; }
					public Double getSzBot() { return szBot; }
					public Double getPfxX() { return pfxX; }
					public Double getPfxZ() { return pfxZ; }
					public Double getPx() { return px; }
					public Double getPz() { return pz; }
					public Double getX0() { return x0; }
					public Double getY0() { return y0; }
					public Double getZ0() { return z0; }
					public Double getVx0() { return vx0; }
					public Double getVy0() { return vy0; }
					public Double getVz0() { return vz0; }
					public Double getAx() { return ax; }
					public Double getAy() { return ay; }
					public Double getAz() { return az; }
					public Double getBreakY() { return breakY; }
					public Double getBreakAngle() { return breakAngle; }
					public Double getBreakLength() { return breakLength; } public String getPitchType() { return pitchType; }
					public Double getTypeConfidence() { return typeConfidence; }
					public Integer getZone() { return zone; }
					public Integer getNasty() { return nasty; }
					public Double getSpinDir() { return spinDir; }
					public Double getSpinRate() { return spinRate; }
					public String getCc() { return cc; }
					public String getMt() { return mt; }
				}
			}
		}

		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = {
				"atbat"
		})
		public static class Top {

			@XmlElement(required = true)
			protected List<Game.Inning.Top.Atbat> atbat;

			public List<Game.Inning.Top.Atbat> getAtbat() {
				if (atbat == null) {
					atbat = new ArrayList<Game.Inning.Top.Atbat>();
				}
				return this.atbat;
			}

			@XmlAccessorType(XmlAccessType.FIELD)
			@XmlType(name = "", propOrder = {
					"pitch"
			})
			public static class Atbat {

				@XmlElement(required = true) protected List<Game.Inning.Top.Atbat.Pitch> pitch;
				@XmlAttribute protected Integer num;
				@XmlAttribute protected Integer b;
				@XmlAttribute protected Integer s;
				@XmlAttribute protected Integer o;
				@XmlAttribute(name = "start_tfs") protected Integer startTfs;
				@XmlAttribute(name = "start_tfs_zulu")
				@XmlSchemaType(name = "dateTime") protected XMLGregorianCalendar startTfsZulu;
				@XmlAttribute protected Integer batter;
				@XmlAttribute protected String stand;
				@XmlAttribute(name = "b_height") protected String bHeight;
				@XmlAttribute protected Integer pitcher;
				@XmlAttribute(name = "p_throws") protected String pThrows;
				@XmlAttribute protected String des;
				@XmlAttribute protected String event;

				public List<Game.Inning.Top.Atbat.Pitch> getPitch() {
					if (pitch == null) {
						pitch = new ArrayList<Game.Inning.Top.Atbat.Pitch>();
					}
					return this.pitch;
				}

				public Integer getNum() { return num; }
				public Integer getB() { return b; }
				public Integer getS() { return s; }
				public Integer getO() { return o; }
				public Integer getStartTfs() { return startTfs; }
				public XMLGregorianCalendar getStartTfsZulu() { return startTfsZulu; }
				public Integer getBatter() { return batter; }
				public String getStand() { return stand; }
				public String getBHeight() { return bHeight; }
				public Integer getPitcher() { return pitcher; }
				public String getPThrows() { return pThrows; }
				public String getDes() { return des; }
				public String getEvent() { return event; }

				@XmlAccessorType(XmlAccessType.FIELD) @XmlType(name = "")
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

					public String getDes() { return des; }
					public Integer getId() { return id; }
					public String getType() { return type; }
					public Integer getTfs() { return tfs; }
					public XMLGregorianCalendar getTfsZulu() { return tfsZulu; }
					public Double getX() { return x; }
					public Double getY() { return y; }
					public String getSvId() { return svId; }
					public Double getStartSpeed() { return startSpeed; }
					public Double getEndSpeed() { return endSpeed; }
					public Double getSzTop() { return szTop; }
					public Double getSzBot() { return szBot; }
					public Double getPfxX() { return pfxX; }
					public Double getPfxZ() { return pfxZ; }
					public Double getPx() { return px; }
					public Double getPz() { return pz; }
					public Double getX0() { return x0; }
					public Double getY0() { return y0; }
					public Double getZ0() { return z0; }
					public Double getVx0() { return vx0; }
					public Double getVy0() { return vy0; }
					public Double getVz0() { return vz0; }
					public Double getAx() { return ax; }
					public Double getAy() { return ay; }
					public Double getAz() { return az; }
					public Double getBreakY() { return breakY; }
					public Double getBreakAngle() { return breakAngle; }
					public Double getBreakLength() { return breakLength; }
					public String getPitchType() { return pitchType; }
					public Double getTypeConfidence() { return typeConfidence; }
					public Integer getZone() { return zone; }
					public Integer getNasty() { return nasty; }
					public Double getSpinDir() { return spinDir; }
					public Double getSpinRate() { return spinRate; }
					public String getCc() { return cc; }
					public String getMt() { return mt; }
				}
			}
		}
	}
}
