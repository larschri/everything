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

	/**
     * Gets the value of the inning property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inning property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInning().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Game.Inning }
     * 
     * 
     */
    public List<Game.Inning> getInning() {
        if (inning == null) {
            inning = new ArrayList<Game.Inning>();
        }
        return this.inning;
    }

    /**
     * Gets the value of the atBat property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAtBat() {
        return atBat;
    }

    /**
     * Sets the value of the atBat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAtBat(Integer value) {
        this.atBat = value;
    }

    /**
     * Gets the value of the deck property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDeck() {
        return deck;
    }

    /**
     * Sets the value of the deck property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDeck(Integer value) {
        this.deck = value;
    }

    /**
     * Gets the value of the hole property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHole() {
        return hole;
    }

    /**
     * Sets the value of the hole property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHole(Integer value) {
        this.hole = value;
    }

    /**
     * Gets the value of the ind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInd() {
        return ind;
    }

    /**
     * Sets the value of the ind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInd(String value) {
        this.ind = value;
    }


    /**
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "top",
        "bottom"
    })
    public static class Inning {

        @XmlElement(required = true)
        protected Game.Inning.Top top;
        @XmlElement(required = true)
        protected Game.Inning.Bottom bottom;
        @XmlAttribute
        protected Integer num;
        @XmlAttribute(name = "away_team")
        protected String awayTeam;
        @XmlAttribute(name = "home_team")
        protected String homeTeam;
        @XmlAttribute
        protected String next;

        /**
         * Gets the value of the top property.
         * 
         * @return
         *     possible object is
         *     {@link Game.Inning.Top }
         *     
         */
        public Game.Inning.Top getTop() {
            return top;
        }

        /**
         * Sets the value of the top property.
         * 
         * @param value
         *     allowed object is
         *     {@link Game.Inning.Top }
         *     
         */
        public void setTop(Game.Inning.Top value) {
            this.top = value;
        }

        /**
         * Gets the value of the bottom property.
         * 
         * @return
         *     possible object is
         *     {@link Game.Inning.Bottom }
         *     
         */
        public Game.Inning.Bottom getBottom() {
            return bottom;
        }

        /**
         * Sets the value of the bottom property.
         * 
         * @param value
         *     allowed object is
         *     {@link Game.Inning.Bottom }
         *     
         */
        public void setBottom(Game.Inning.Bottom value) {
            this.bottom = value;
        }

        /**
         * Gets the value of the num property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getNum() {
            return num;
        }

        /**
         * Sets the value of the num property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setNum(Integer value) {
            this.num = value;
        }

        /**
         * Gets the value of the awayTeam property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAwayTeam() {
            return awayTeam;
        }

        /**
         * Sets the value of the awayTeam property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAwayTeam(String value) {
            this.awayTeam = value;
        }

        /**
         * Gets the value of the homeTeam property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHomeTeam() {
            return homeTeam;
        }

        /**
         * Sets the value of the homeTeam property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHomeTeam(String value) {
            this.homeTeam = value;
        }

        /**
         * Gets the value of the next property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNext() {
            return next;
        }

        /**
         * Sets the value of the next property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNext(String value) {
            this.next = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="atbat" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="pitch" maxOccurs="unbounded">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;attribute name="des" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                           &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="tfs" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                           &lt;attribute name="tfs_zulu" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
         *                           &lt;attribute name="x" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="y" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="sv_id" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="start_speed" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="end_speed" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="sz_top" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                           &lt;attribute name="sz_bot" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="pfx_x" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="pfx_z" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="px" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="pz" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="x0" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="y0" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                           &lt;attribute name="z0" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="vx0" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="vy0" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="vz0" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="ax" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="ay" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="az" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="break_y" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="break_angle" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                           &lt;attribute name="break_length" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="pitch_type" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="type_confidence" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="zone" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                           &lt;attribute name="nasty" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                           &lt;attribute name="spin_dir" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="spin_rate" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="cc" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="mt" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *                 &lt;attribute name="num" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                 &lt;attribute name="b" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                 &lt;attribute name="s" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                 &lt;attribute name="o" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                 &lt;attribute name="start_tfs" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                 &lt;attribute name="start_tfs_zulu" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
         *                 &lt;attribute name="batter" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                 &lt;attribute name="stand" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="b_height" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="pitcher" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                 &lt;attribute name="p_throws" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="des" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="event" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "atbat"
        })
        public static class Bottom {

            @XmlElement(required = true)
            protected List<Game.Inning.Bottom.Atbat> atbat;

            /**
             * Gets the value of the atbat property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the atbat property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getAtbat().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Game.Inning.Bottom.Atbat }
             * 
             * 
             */
            public List<Game.Inning.Bottom.Atbat> getAtbat() {
                if (atbat == null) {
                    atbat = new ArrayList<Game.Inning.Bottom.Atbat>();
                }
                return this.atbat;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="pitch" maxOccurs="unbounded">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;attribute name="des" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}int" />
             *                 &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="tfs" type="{http://www.w3.org/2001/XMLSchema}int" />
             *                 &lt;attribute name="tfs_zulu" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
             *                 &lt;attribute name="x" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="y" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="sv_id" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="start_speed" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="end_speed" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="sz_top" type="{http://www.w3.org/2001/XMLSchema}int" />
             *                 &lt;attribute name="sz_bot" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="pfx_x" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="pfx_z" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="px" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="pz" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="x0" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="y0" type="{http://www.w3.org/2001/XMLSchema}int" />
             *                 &lt;attribute name="z0" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="vx0" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="vy0" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="vz0" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="ax" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="ay" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="az" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="break_y" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="break_angle" type="{http://www.w3.org/2001/XMLSchema}int" />
             *                 &lt;attribute name="break_length" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="pitch_type" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="type_confidence" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="zone" type="{http://www.w3.org/2001/XMLSchema}int" />
             *                 &lt;attribute name="nasty" type="{http://www.w3.org/2001/XMLSchema}int" />
             *                 &lt;attribute name="spin_dir" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="spin_rate" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="cc" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="mt" type="{http://www.w3.org/2001/XMLSchema}string" />
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *       &lt;attribute name="num" type="{http://www.w3.org/2001/XMLSchema}int" />
             *       &lt;attribute name="b" type="{http://www.w3.org/2001/XMLSchema}int" />
             *       &lt;attribute name="s" type="{http://www.w3.org/2001/XMLSchema}int" />
             *       &lt;attribute name="o" type="{http://www.w3.org/2001/XMLSchema}int" />
             *       &lt;attribute name="start_tfs" type="{http://www.w3.org/2001/XMLSchema}int" />
             *       &lt;attribute name="start_tfs_zulu" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
             *       &lt;attribute name="batter" type="{http://www.w3.org/2001/XMLSchema}int" />
             *       &lt;attribute name="stand" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="b_height" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="pitcher" type="{http://www.w3.org/2001/XMLSchema}int" />
             *       &lt;attribute name="p_throws" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="des" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="event" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "pitch"
            })
            public static class Atbat {

                @XmlElement(required = true)
                protected List<Game.Inning.Bottom.Atbat.Pitch> pitch;
                @XmlAttribute
                protected Integer num;
                @XmlAttribute
                protected Integer b;
                @XmlAttribute
                protected Integer s;
                @XmlAttribute
                protected Integer o;
                @XmlAttribute(name = "start_tfs")
                protected Integer startTfs;
                @XmlAttribute(name = "start_tfs_zulu")
                @XmlSchemaType(name = "dateTime")
                protected XMLGregorianCalendar startTfsZulu;
                @XmlAttribute
                protected Integer batter;
                @XmlAttribute
                protected String stand;
                @XmlAttribute(name = "b_height")
                protected String bHeight;
                @XmlAttribute
                protected Integer pitcher;
                @XmlAttribute(name = "p_throws")
                protected String pThrows;
                @XmlAttribute
                protected String des;
                @XmlAttribute
                protected String event;

                /**
                 * Gets the value of the pitch property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the pitch property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getPitch().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link Game.Inning.Bottom.Atbat.Pitch }
                 * 
                 * 
                 */
                public List<Game.Inning.Bottom.Atbat.Pitch> getPitch() {
                    if (pitch == null) {
                        pitch = new ArrayList<Game.Inning.Bottom.Atbat.Pitch>();
                    }
                    return this.pitch;
                }

                /**
                 * Gets the value of the num property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getNum() {
                    return num;
                }

                /**
                 * Sets the value of the num property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setNum(Integer value) {
                    this.num = value;
                }

                /**
                 * Gets the value of the b property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getB() {
                    return b;
                }

                /**
                 * Sets the value of the b property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setB(Integer value) {
                    this.b = value;
                }

                /**
                 * Gets the value of the s property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getS() {
                    return s;
                }

                /**
                 * Sets the value of the s property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setS(Integer value) {
                    this.s = value;
                }

                /**
                 * Gets the value of the o property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getO() {
                    return o;
                }

                /**
                 * Sets the value of the o property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setO(Integer value) {
                    this.o = value;
                }

                /**
                 * Gets the value of the startTfs property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getStartTfs() {
                    return startTfs;
                }

                /**
                 * Sets the value of the startTfs property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setStartTfs(Integer value) {
                    this.startTfs = value;
                }

                /**
                 * Gets the value of the startTfsZulu property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public XMLGregorianCalendar getStartTfsZulu() {
                    return startTfsZulu;
                }

                /**
                 * Sets the value of the startTfsZulu property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public void setStartTfsZulu(XMLGregorianCalendar value) {
                    this.startTfsZulu = value;
                }

                /**
                 * Gets the value of the batter property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getBatter() {
                    return batter;
                }

                /**
                 * Sets the value of the batter property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setBatter(Integer value) {
                    this.batter = value;
                }

                /**
                 * Gets the value of the stand property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getStand() {
                    return stand;
                }

                /**
                 * Sets the value of the stand property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setStand(String value) {
                    this.stand = value;
                }

                /**
                 * Gets the value of the bHeight property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getBHeight() {
                    return bHeight;
                }

                /**
                 * Sets the value of the bHeight property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setBHeight(String value) {
                    this.bHeight = value;
                }

                /**
                 * Gets the value of the pitcher property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getPitcher() {
                    return pitcher;
                }

                /**
                 * Sets the value of the pitcher property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setPitcher(Integer value) {
                    this.pitcher = value;
                }

                /**
                 * Gets the value of the pThrows property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getPThrows() {
                    return pThrows;
                }

                /**
                 * Sets the value of the pThrows property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setPThrows(String value) {
                    this.pThrows = value;
                }

                /**
                 * Gets the value of the des property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDes() {
                    return des;
                }

                /**
                 * Sets the value of the des property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDes(String value) {
                    this.des = value;
                }

                /**
                 * Gets the value of the event property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getEvent() {
                    return event;
                }

                /**
                 * Sets the value of the event property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setEvent(String value) {
                    this.event = value;
                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;attribute name="des" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}int" />
                 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="tfs" type="{http://www.w3.org/2001/XMLSchema}int" />
                 *       &lt;attribute name="tfs_zulu" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
                 *       &lt;attribute name="x" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="y" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="sv_id" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="start_speed" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="end_speed" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="sz_top" type="{http://www.w3.org/2001/XMLSchema}int" />
                 *       &lt;attribute name="sz_bot" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="pfx_x" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="pfx_z" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="px" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="pz" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="x0" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="y0" type="{http://www.w3.org/2001/XMLSchema}int" />
                 *       &lt;attribute name="z0" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="vx0" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="vy0" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="vz0" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="ax" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="ay" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="az" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="break_y" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="break_angle" type="{http://www.w3.org/2001/XMLSchema}int" />
                 *       &lt;attribute name="break_length" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="pitch_type" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="type_confidence" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="zone" type="{http://www.w3.org/2001/XMLSchema}int" />
                 *       &lt;attribute name="nasty" type="{http://www.w3.org/2001/XMLSchema}int" />
                 *       &lt;attribute name="spin_dir" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="spin_rate" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="cc" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="mt" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Pitch {

                    @XmlAttribute
                    protected String des;
                    @XmlAttribute
                    protected Integer id;
                    @XmlAttribute
                    protected String type;
                    @XmlAttribute
                    protected Integer tfs;
                    @XmlAttribute(name = "tfs_zulu")
                    @XmlSchemaType(name = "dateTime")
                    protected XMLGregorianCalendar tfsZulu;
                    @XmlAttribute
                    protected Double x;
                    @XmlAttribute
                    protected Double y;
                    @XmlAttribute(name = "sv_id")
                    protected String svId;
                    @XmlAttribute(name = "start_speed")
                    protected Double startSpeed;
                    @XmlAttribute(name = "end_speed")
                    protected Double endSpeed;
                    @XmlAttribute(name = "sz_top")
                    protected Double szTop;
                    @XmlAttribute(name = "sz_bot")
                    protected Double szBot;
                    @XmlAttribute(name = "pfx_x")
                    protected Double pfxX;
                    @XmlAttribute(name = "pfx_z")
                    protected Double pfxZ;
                    @XmlAttribute
                    protected Double px;
                    @XmlAttribute
                    protected Double pz;
                    @XmlAttribute
                    protected Double x0;
                    @XmlAttribute
                    protected Double y0;
                    @XmlAttribute
                    protected Double z0;
                    @XmlAttribute
                    protected Double vx0;
                    @XmlAttribute
                    protected Double vy0;
                    @XmlAttribute
                    protected Double vz0;
                    @XmlAttribute
                    protected Double ax;
                    @XmlAttribute
                    protected Double ay;
                    @XmlAttribute
                    protected Double az;
                    @XmlAttribute(name = "break_y")
                    protected Double breakY;
                    @XmlAttribute(name = "break_angle")
                    protected Double breakAngle;
                    @XmlAttribute(name = "break_length")
                    protected Double breakLength;
                    @XmlAttribute(name = "pitch_type")
                    protected String pitchType;
                    @XmlAttribute(name = "type_confidence")
                    protected Double typeConfidence;
                    @XmlAttribute
                    protected Integer zone;
                    @XmlAttribute
                    protected Integer nasty;
                    @XmlAttribute(name = "spin_dir")
                    protected Double spinDir;
                    @XmlAttribute(name = "spin_rate")
                    protected Double spinRate;
                    @XmlAttribute
                    protected String cc;
                    @XmlAttribute
                    protected String mt;

                    /**
                     * Gets the value of the des property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getDes() {
                        return des;
                    }

                    /**
                     * Sets the value of the des property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setDes(String value) {
                        this.des = value;
                    }

                    /**
                     * Gets the value of the id property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Integer }
                     *     
                     */
                    public Integer getId() {
                        return id;
                    }

                    /**
                     * Sets the value of the id property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Integer }
                     *     
                     */
                    public void setId(Integer value) {
                        this.id = value;
                    }

                    /**
                     * Gets the value of the type property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getType() {
                        return type;
                    }

                    /**
                     * Sets the value of the type property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setType(String value) {
                        this.type = value;
                    }

                    /**
                     * Gets the value of the tfs property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Integer }
                     *     
                     */
                    public Integer getTfs() {
                        return tfs;
                    }

                    /**
                     * Sets the value of the tfs property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Integer }
                     *     
                     */
                    public void setTfs(Integer value) {
                        this.tfs = value;
                    }

                    /**
                     * Gets the value of the tfsZulu property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link XMLGregorianCalendar }
                     *     
                     */
                    public XMLGregorianCalendar getTfsZulu() {
                        return tfsZulu;
                    }

                    /**
                     * Sets the value of the tfsZulu property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link XMLGregorianCalendar }
                     *     
                     */
                    public void setTfsZulu(XMLGregorianCalendar value) {
                        this.tfsZulu = value;
                    }

                    /**
                     * Gets the value of the x property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getX() {
                        return x;
                    }

                    /**
                     * Sets the value of the x property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setX(Double value) {
                        this.x = value;
                    }

                    /**
                     * Gets the value of the y property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getY() {
                        return y;
                    }

                    /**
                     * Sets the value of the y property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setY(Double value) {
                        this.y = value;
                    }

                    /**
                     * Gets the value of the svId property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getSvId() {
                        return svId;
                    }

                    /**
                     * Sets the value of the svId property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setSvId(String value) {
                        this.svId = value;
                    }

                    /**
                     * Gets the value of the startSpeed property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getStartSpeed() {
                        return startSpeed;
                    }

                    /**
                     * Sets the value of the startSpeed property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setStartSpeed(Double value) {
                        this.startSpeed = value;
                    }

                    /**
                     * Gets the value of the endSpeed property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getEndSpeed() {
                        return endSpeed;
                    }

                    /**
                     * Sets the value of the endSpeed property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setEndSpeed(Double value) {
                        this.endSpeed = value;
                    }

                    /**
                     * Gets the value of the szTop property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Integer }
                     *     
                     */
                    public Double getSzTop() {
                        return szTop;
                    }

                    /**
                     * Sets the value of the szTop property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Integer }
                     *     
                     */
                    public void setSzTop(Double value) {
                        this.szTop = value;
                    }

                    /**
                     * Gets the value of the szBot property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getSzBot() {
                        return szBot;
                    }

                    /**
                     * Sets the value of the szBot property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setSzBot(Double value) {
                        this.szBot = value;
                    }

                    /**
                     * Gets the value of the pfxX property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getPfxX() {
                        return pfxX;
                    }

                    /**
                     * Sets the value of the pfxX property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setPfxX(Double value) {
                        this.pfxX = value;
                    }

                    /**
                     * Gets the value of the pfxZ property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getPfxZ() {
                        return pfxZ;
                    }

                    /**
                     * Sets the value of the pfxZ property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setPfxZ(Double value) {
                        this.pfxZ = value;
                    }

                    /**
                     * Gets the value of the px property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getPx() {
                        return px;
                    }

                    /**
                     * Sets the value of the px property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setPx(Double value) {
                        this.px = value;
                    }

                    /**
                     * Gets the value of the pz property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getPz() {
                        return pz;
                    }

                    /**
                     * Sets the value of the pz property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setPz(Double value) {
                        this.pz = value;
                    }

                    /**
                     * Gets the value of the x0 property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getX0() {
                        return x0;
                    }

                    /**
                     * Sets the value of the x0 property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setX0(Double value) {
                        this.x0 = value;
                    }

                    /**
                     * Gets the value of the y0 property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Integer }
                     *     
                     */
                    public Double getY0() {
                        return y0;
                    }

                    /**
                     * Sets the value of the y0 property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Integer }
                     *     
                     */
                    public void setY0(Double value) {
                        this.y0 = value;
                    }

                    /**
                     * Gets the value of the z0 property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getZ0() {
                        return z0;
                    }

                    /**
                     * Sets the value of the z0 property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setZ0(Double value) {
                        this.z0 = value;
                    }

                    /**
                     * Gets the value of the vx0 property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getVx0() {
                        return vx0;
                    }

                    /**
                     * Sets the value of the vx0 property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setVx0(Double value) {
                        this.vx0 = value;
                    }

                    /**
                     * Gets the value of the vy0 property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getVy0() {
                        return vy0;
                    }

                    /**
                     * Sets the value of the vy0 property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setVy0(Double value) {
                        this.vy0 = value;
                    }

                    /**
                     * Gets the value of the vz0 property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getVz0() {
                        return vz0;
                    }

                    /**
                     * Sets the value of the vz0 property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setVz0(Double value) {
                        this.vz0 = value;
                    }

                    /**
                     * Gets the value of the ax property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getAx() {
                        return ax;
                    }

                    /**
                     * Sets the value of the ax property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setAx(Double value) {
                        this.ax = value;
                    }

                    /**
                     * Gets the value of the ay property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getAy() {
                        return ay;
                    }

                    /**
                     * Sets the value of the ay property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setAy(Double value) {
                        this.ay = value;
                    }

                    /**
                     * Gets the value of the az property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getAz() {
                        return az;
                    }

                    /**
                     * Sets the value of the az property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setAz(Double value) {
                        this.az = value;
                    }

                    /**
                     * Gets the value of the breakY property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getBreakY() {
                        return breakY;
                    }

                    /**
                     * Sets the value of the breakY property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setBreakY(Double value) {
                        this.breakY = value;
                    }

                    /**
                     * Gets the value of the breakAngle property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Integer }
                     *     
                     */
                    public Double getBreakAngle() {
                        return breakAngle;
                    }

                    /**
                     * Sets the value of the breakAngle property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Integer }
                     *     
                     */
                    public void setBreakAngle(Double value) {
                        this.breakAngle = value;
                    }

                    /**
                     * Gets the value of the breakLength property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getBreakLength() {
                        return breakLength;
                    }

                    /**
                     * Sets the value of the breakLength property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setBreakLength(Double value) {
                        this.breakLength = value;
                    }

                    /**
                     * Gets the value of the pitchType property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getPitchType() {
                        return pitchType;
                    }

                    /**
                     * Sets the value of the pitchType property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setPitchType(String value) {
                        this.pitchType = value;
                    }

                    /**
                     * Gets the value of the typeConfidence property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getTypeConfidence() {
                        return typeConfidence;
                    }

                    /**
                     * Sets the value of the typeConfidence property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setTypeConfidence(Double value) {
                        this.typeConfidence = value;
                    }

                    /**
                     * Gets the value of the zone property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Integer }
                     *     
                     */
                    public Integer getZone() {
                        return zone;
                    }

                    /**
                     * Sets the value of the zone property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Integer }
                     *     
                     */
                    public void setZone(Integer value) {
                        this.zone = value;
                    }

                    /**
                     * Gets the value of the nasty property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Integer }
                     *     
                     */
                    public Integer getNasty() {
                        return nasty;
                    }

                    /**
                     * Sets the value of the nasty property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Integer }
                     *     
                     */
                    public void setNasty(Integer value) {
                        this.nasty = value;
                    }

                    /**
                     * Gets the value of the spinDir property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getSpinDir() {
                        return spinDir;
                    }

                    /**
                     * Sets the value of the spinDir property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setSpinDir(Double value) {
                        this.spinDir = value;
                    }

                    /**
                     * Gets the value of the spinRate property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getSpinRate() {
                        return spinRate;
                    }

                    /**
                     * Sets the value of the spinRate property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setSpinRate(Double value) {
                        this.spinRate = value;
                    }

                    /**
                     * Gets the value of the cc property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCc() {
                        return cc;
                    }

                    /**
                     * Sets the value of the cc property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCc(String value) {
                        this.cc = value;
                    }

                    /**
                     * Gets the value of the mt property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getMt() {
                        return mt;
                    }

                    /**
                     * Sets the value of the mt property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setMt(String value) {
                        this.mt = value;
                    }

                }

            }

        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="atbat" maxOccurs="unbounded">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="pitch" maxOccurs="unbounded">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;attribute name="des" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                           &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="tfs" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                           &lt;attribute name="tfs_zulu" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
         *                           &lt;attribute name="x" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="y" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="sv_id" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="start_speed" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="end_speed" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="sz_top" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="sz_bot" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="pfx_x" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="pfx_z" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="px" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="pz" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="x0" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="y0" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                           &lt;attribute name="z0" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="vx0" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="vy0" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="vz0" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="ax" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="ay" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="az" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="break_y" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="break_angle" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="break_length" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="pitch_type" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="type_confidence" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="zone" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                           &lt;attribute name="nasty" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                           &lt;attribute name="spin_dir" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="spin_rate" type="{http://www.w3.org/2001/XMLSchema}double" />
         *                           &lt;attribute name="cc" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="mt" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *                 &lt;attribute name="num" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                 &lt;attribute name="b" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                 &lt;attribute name="s" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                 &lt;attribute name="o" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                 &lt;attribute name="start_tfs" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                 &lt;attribute name="start_tfs_zulu" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
         *                 &lt;attribute name="batter" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                 &lt;attribute name="stand" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="b_height" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="pitcher" type="{http://www.w3.org/2001/XMLSchema}int" />
         *                 &lt;attribute name="p_throws" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="des" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                 &lt;attribute name="event" type="{http://www.w3.org/2001/XMLSchema}string" />
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "atbat"
        })
        public static class Top {

            @XmlElement(required = true)
            protected List<Game.Inning.Top.Atbat> atbat;

            /**
             * Gets the value of the atbat property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the atbat property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getAtbat().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Game.Inning.Top.Atbat }
             * 
             * 
             */
            public List<Game.Inning.Top.Atbat> getAtbat() {
                if (atbat == null) {
                    atbat = new ArrayList<Game.Inning.Top.Atbat>();
                }
                return this.atbat;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;complexContent>
             *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *       &lt;sequence>
             *         &lt;element name="pitch" maxOccurs="unbounded">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;attribute name="des" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}int" />
             *                 &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="tfs" type="{http://www.w3.org/2001/XMLSchema}int" />
             *                 &lt;attribute name="tfs_zulu" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
             *                 &lt;attribute name="x" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="y" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="sv_id" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="start_speed" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="end_speed" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="sz_top" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="sz_bot" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="pfx_x" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="pfx_z" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="px" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="pz" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="x0" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="y0" type="{http://www.w3.org/2001/XMLSchema}int" />
             *                 &lt;attribute name="z0" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="vx0" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="vy0" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="vz0" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="ax" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="ay" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="az" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="break_y" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="break_angle" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="break_length" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="pitch_type" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="type_confidence" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="zone" type="{http://www.w3.org/2001/XMLSchema}int" />
             *                 &lt;attribute name="nasty" type="{http://www.w3.org/2001/XMLSchema}int" />
             *                 &lt;attribute name="spin_dir" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="spin_rate" type="{http://www.w3.org/2001/XMLSchema}double" />
             *                 &lt;attribute name="cc" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="mt" type="{http://www.w3.org/2001/XMLSchema}string" />
             *               &lt;/restriction>
             *             &lt;/complexContent>
             *           &lt;/complexType>
             *         &lt;/element>
             *       &lt;/sequence>
             *       &lt;attribute name="num" type="{http://www.w3.org/2001/XMLSchema}int" />
             *       &lt;attribute name="b" type="{http://www.w3.org/2001/XMLSchema}int" />
             *       &lt;attribute name="s" type="{http://www.w3.org/2001/XMLSchema}int" />
             *       &lt;attribute name="o" type="{http://www.w3.org/2001/XMLSchema}int" />
             *       &lt;attribute name="start_tfs" type="{http://www.w3.org/2001/XMLSchema}int" />
             *       &lt;attribute name="start_tfs_zulu" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
             *       &lt;attribute name="batter" type="{http://www.w3.org/2001/XMLSchema}int" />
             *       &lt;attribute name="stand" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="b_height" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="pitcher" type="{http://www.w3.org/2001/XMLSchema}int" />
             *       &lt;attribute name="p_throws" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="des" type="{http://www.w3.org/2001/XMLSchema}string" />
             *       &lt;attribute name="event" type="{http://www.w3.org/2001/XMLSchema}string" />
             *     &lt;/restriction>
             *   &lt;/complexContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "pitch"
            })
            public static class Atbat {

                @XmlElement(required = true)
                protected List<Game.Inning.Top.Atbat.Pitch> pitch;
                @XmlAttribute
                protected Integer num;
                @XmlAttribute
                protected Integer b;
                @XmlAttribute
                protected Integer s;
                @XmlAttribute
                protected Integer o;
                @XmlAttribute(name = "start_tfs")
                protected Integer startTfs;
                @XmlAttribute(name = "start_tfs_zulu")
                @XmlSchemaType(name = "dateTime")
                protected XMLGregorianCalendar startTfsZulu;
                @XmlAttribute
                protected Integer batter;
                @XmlAttribute
                protected String stand;
                @XmlAttribute(name = "b_height")
                protected String bHeight;
                @XmlAttribute
                protected Integer pitcher;
                @XmlAttribute(name = "p_throws")
                protected String pThrows;
                @XmlAttribute
                protected String des;
                @XmlAttribute
                protected String event;

                /**
                 * Gets the value of the pitch property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the pitch property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getPitch().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link Game.Inning.Top.Atbat.Pitch }
                 * 
                 * 
                 */
                public List<Game.Inning.Top.Atbat.Pitch> getPitch() {
                    if (pitch == null) {
                        pitch = new ArrayList<Game.Inning.Top.Atbat.Pitch>();
                    }
                    return this.pitch;
                }

                /**
                 * Gets the value of the num property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getNum() {
                    return num;
                }

                /**
                 * Sets the value of the num property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setNum(Integer value) {
                    this.num = value;
                }

                /**
                 * Gets the value of the b property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getB() {
                    return b;
                }

                /**
                 * Sets the value of the b property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setB(Integer value) {
                    this.b = value;
                }

                /**
                 * Gets the value of the s property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getS() {
                    return s;
                }

                /**
                 * Sets the value of the s property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setS(Integer value) {
                    this.s = value;
                }

                /**
                 * Gets the value of the o property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getO() {
                    return o;
                }

                /**
                 * Sets the value of the o property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setO(Integer value) {
                    this.o = value;
                }

                /**
                 * Gets the value of the startTfs property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getStartTfs() {
                    return startTfs;
                }

                /**
                 * Sets the value of the startTfs property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setStartTfs(Integer value) {
                    this.startTfs = value;
                }

                /**
                 * Gets the value of the startTfsZulu property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public XMLGregorianCalendar getStartTfsZulu() {
                    return startTfsZulu;
                }

                /**
                 * Sets the value of the startTfsZulu property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public void setStartTfsZulu(XMLGregorianCalendar value) {
                    this.startTfsZulu = value;
                }

                /**
                 * Gets the value of the batter property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getBatter() {
                    return batter;
                }

                /**
                 * Sets the value of the batter property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setBatter(Integer value) {
                    this.batter = value;
                }

                /**
                 * Gets the value of the stand property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getStand() {
                    return stand;
                }

                /**
                 * Sets the value of the stand property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setStand(String value) {
                    this.stand = value;
                }

                /**
                 * Gets the value of the bHeight property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getBHeight() {
                    return bHeight;
                }

                /**
                 * Sets the value of the bHeight property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setBHeight(String value) {
                    this.bHeight = value;
                }

                /**
                 * Gets the value of the pitcher property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Integer }
                 *     
                 */
                public Integer getPitcher() {
                    return pitcher;
                }

                /**
                 * Sets the value of the pitcher property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Integer }
                 *     
                 */
                public void setPitcher(Integer value) {
                    this.pitcher = value;
                }

                /**
                 * Gets the value of the pThrows property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getPThrows() {
                    return pThrows;
                }

                /**
                 * Sets the value of the pThrows property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setPThrows(String value) {
                    this.pThrows = value;
                }

                /**
                 * Gets the value of the des property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getDes() {
                    return des;
                }

                /**
                 * Sets the value of the des property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setDes(String value) {
                    this.des = value;
                }

                /**
                 * Gets the value of the event property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getEvent() {
                    return event;
                }

                /**
                 * Sets the value of the event property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setEvent(String value) {
                    this.event = value;
                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;complexContent>
                 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
                 *       &lt;attribute name="des" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}int" />
                 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="tfs" type="{http://www.w3.org/2001/XMLSchema}int" />
                 *       &lt;attribute name="tfs_zulu" type="{http://www.w3.org/2001/XMLSchema}dateTime" />
                 *       &lt;attribute name="x" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="y" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="sv_id" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="start_speed" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="end_speed" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="sz_top" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="sz_bot" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="pfx_x" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="pfx_z" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="px" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="pz" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="x0" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="y0" type="{http://www.w3.org/2001/XMLSchema}int" />
                 *       &lt;attribute name="z0" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="vx0" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="vy0" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="vz0" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="ax" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="ay" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="az" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="break_y" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="break_angle" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="break_length" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="pitch_type" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="type_confidence" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="zone" type="{http://www.w3.org/2001/XMLSchema}int" />
                 *       &lt;attribute name="nasty" type="{http://www.w3.org/2001/XMLSchema}int" />
                 *       &lt;attribute name="spin_dir" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="spin_rate" type="{http://www.w3.org/2001/XMLSchema}double" />
                 *       &lt;attribute name="cc" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="mt" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *     &lt;/restriction>
                 *   &lt;/complexContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "")
                public static class Pitch {

                    @XmlAttribute
                    protected String des;
                    @XmlAttribute
                    protected Integer id;
                    @XmlAttribute
                    protected String type;
                    @XmlAttribute
                    protected Integer tfs;
                    @XmlAttribute(name = "tfs_zulu")
                    @XmlSchemaType(name = "dateTime")
                    protected XMLGregorianCalendar tfsZulu;
                    @XmlAttribute
                    protected Double x;
                    @XmlAttribute
                    protected Double y;
                    @XmlAttribute(name = "sv_id")
                    protected String svId;
                    @XmlAttribute(name = "start_speed")
                    protected Double startSpeed;
                    @XmlAttribute(name = "end_speed")
                    protected Double endSpeed;
                    @XmlAttribute(name = "sz_top")
                    protected Double szTop;
                    @XmlAttribute(name = "sz_bot")
                    protected Double szBot;
                    @XmlAttribute(name = "pfx_x")
                    protected Double pfxX;
                    @XmlAttribute(name = "pfx_z")
                    protected Double pfxZ;
                    @XmlAttribute
                    protected Double px;
                    @XmlAttribute
                    protected Double pz;
                    @XmlAttribute
                    protected Double x0;
                    @XmlAttribute
                    protected Double y0;
                    @XmlAttribute
                    protected Double z0;
                    @XmlAttribute
                    protected Double vx0;
                    @XmlAttribute
                    protected Double vy0;
                    @XmlAttribute
                    protected Double vz0;
                    @XmlAttribute
                    protected Double ax;
                    @XmlAttribute
                    protected Double ay;
                    @XmlAttribute
                    protected Double az;
                    @XmlAttribute(name = "break_y")
                    protected Double breakY;
                    @XmlAttribute(name = "break_angle")
                    protected Double breakAngle;
                    @XmlAttribute(name = "break_length")
                    protected Double breakLength;
                    @XmlAttribute(name = "pitch_type")
                    protected String pitchType;
                    @XmlAttribute(name = "type_confidence")
                    protected Double typeConfidence;
                    @XmlAttribute
                    protected Integer zone;
                    @XmlAttribute
                    protected Integer nasty;
                    @XmlAttribute(name = "spin_dir")
                    protected Double spinDir;
                    @XmlAttribute(name = "spin_rate")
                    protected Double spinRate;
                    @XmlAttribute
                    protected String cc;
                    @XmlAttribute
                    protected String mt;

                    /**
                     * Gets the value of the des property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getDes() {
                        return des;
                    }

                    /**
                     * Sets the value of the des property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setDes(String value) {
                        this.des = value;
                    }

                    /**
                     * Gets the value of the id property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Integer }
                     *     
                     */
                    public Integer getId() {
                        return id;
                    }

                    /**
                     * Sets the value of the id property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Integer }
                     *     
                     */
                    public void setId(Integer value) {
                        this.id = value;
                    }

                    /**
                     * Gets the value of the type property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getType() {
                        return type;
                    }

                    /**
                     * Sets the value of the type property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setType(String value) {
                        this.type = value;
                    }

                    /**
                     * Gets the value of the tfs property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Integer }
                     *     
                     */
                    public Integer getTfs() {
                        return tfs;
                    }

                    /**
                     * Sets the value of the tfs property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Integer }
                     *     
                     */
                    public void setTfs(Integer value) {
                        this.tfs = value;
                    }

                    /**
                     * Gets the value of the tfsZulu property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link XMLGregorianCalendar }
                     *     
                     */
                    public XMLGregorianCalendar getTfsZulu() {
                        return tfsZulu;
                    }

                    /**
                     * Sets the value of the tfsZulu property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link XMLGregorianCalendar }
                     *     
                     */
                    public void setTfsZulu(XMLGregorianCalendar value) {
                        this.tfsZulu = value;
                    }

                    /**
                     * Gets the value of the x property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getX() {
                        return x;
                    }

                    /**
                     * Sets the value of the x property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setX(Double value) {
                        this.x = value;
                    }

                    /**
                     * Gets the value of the y property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getY() {
                        return y;
                    }

                    /**
                     * Sets the value of the y property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setY(Double value) {
                        this.y = value;
                    }

                    /**
                     * Gets the value of the svId property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getSvId() {
                        return svId;
                    }

                    /**
                     * Sets the value of the svId property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setSvId(String value) {
                        this.svId = value;
                    }

                    /**
                     * Gets the value of the startSpeed property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getStartSpeed() {
                        return startSpeed;
                    }

                    /**
                     * Sets the value of the startSpeed property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setStartSpeed(Double value) {
                        this.startSpeed = value;
                    }

                    /**
                     * Gets the value of the endSpeed property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getEndSpeed() {
                        return endSpeed;
                    }

                    /**
                     * Sets the value of the endSpeed property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setEndSpeed(Double value) {
                        this.endSpeed = value;
                    }

                    /**
                     * Gets the value of the szTop property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getSzTop() {
                        return szTop;
                    }

                    /**
                     * Sets the value of the szTop property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setSzTop(Double value) {
                        this.szTop = value;
                    }

                    /**
                     * Gets the value of the szBot property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getSzBot() {
                        return szBot;
                    }

                    /**
                     * Sets the value of the szBot property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setSzBot(Double value) {
                        this.szBot = value;
                    }

                    /**
                     * Gets the value of the pfxX property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getPfxX() {
                        return pfxX;
                    }

                    /**
                     * Sets the value of the pfxX property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setPfxX(Double value) {
                        this.pfxX = value;
                    }

                    /**
                     * Gets the value of the pfxZ property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getPfxZ() {
                        return pfxZ;
                    }

                    /**
                     * Sets the value of the pfxZ property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setPfxZ(Double value) {
                        this.pfxZ = value;
                    }

                    /**
                     * Gets the value of the px property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getPx() {
                        return px;
                    }

                    /**
                     * Sets the value of the px property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setPx(Double value) {
                        this.px = value;
                    }

                    /**
                     * Gets the value of the pz property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getPz() {
                        return pz;
                    }

                    /**
                     * Sets the value of the pz property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setPz(Double value) {
                        this.pz = value;
                    }

                    /**
                     * Gets the value of the x0 property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getX0() {
                        return x0;
                    }

                    /**
                     * Sets the value of the x0 property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setX0(Double value) {
                        this.x0 = value;
                    }

                    /**
                     * Gets the value of the y0 property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Integer }
                     *     
                     */
                    public Double getY0() {
                        return y0;
                    }

                    /**
                     * Sets the value of the y0 property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Integer }
                     *     
                     */
                    public void setY0(Double value) {
                        this.y0 = value;
                    }

                    /**
                     * Gets the value of the z0 property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getZ0() {
                        return z0;
                    }

                    /**
                     * Sets the value of the z0 property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setZ0(Double value) {
                        this.z0 = value;
                    }

                    /**
                     * Gets the value of the vx0 property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getVx0() {
                        return vx0;
                    }

                    /**
                     * Sets the value of the vx0 property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setVx0(Double value) {
                        this.vx0 = value;
                    }

                    /**
                     * Gets the value of the vy0 property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getVy0() {
                        return vy0;
                    }

                    /**
                     * Sets the value of the vy0 property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setVy0(Double value) {
                        this.vy0 = value;
                    }

                    /**
                     * Gets the value of the vz0 property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getVz0() {
                        return vz0;
                    }

                    /**
                     * Sets the value of the vz0 property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setVz0(Double value) {
                        this.vz0 = value;
                    }

                    /**
                     * Gets the value of the ax property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getAx() {
                        return ax;
                    }

                    /**
                     * Sets the value of the ax property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setAx(Double value) {
                        this.ax = value;
                    }

                    /**
                     * Gets the value of the ay property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getAy() {
                        return ay;
                    }

                    /**
                     * Sets the value of the ay property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setAy(Double value) {
                        this.ay = value;
                    }

                    /**
                     * Gets the value of the az property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getAz() {
                        return az;
                    }

                    /**
                     * Sets the value of the az property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setAz(Double value) {
                        this.az = value;
                    }

                    /**
                     * Gets the value of the breakY property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getBreakY() {
                        return breakY;
                    }

                    /**
                     * Sets the value of the breakY property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setBreakY(Double value) {
                        this.breakY = value;
                    }

                    /**
                     * Gets the value of the breakAngle property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getBreakAngle() {
                        return breakAngle;
                    }

                    /**
                     * Sets the value of the breakAngle property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setBreakAngle(Double value) {
                        this.breakAngle = value;
                    }

                    /**
                     * Gets the value of the breakLength property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getBreakLength() {
                        return breakLength;
                    }

                    /**
                     * Sets the value of the breakLength property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setBreakLength(Double value) {
                        this.breakLength = value;
                    }

                    /**
                     * Gets the value of the pitchType property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getPitchType() {
                        return pitchType;
                    }

                    /**
                     * Sets the value of the pitchType property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setPitchType(String value) {
                        this.pitchType = value;
                    }

                    /**
                     * Gets the value of the typeConfidence property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getTypeConfidence() {
                        return typeConfidence;
                    }

                    /**
                     * Sets the value of the typeConfidence property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setTypeConfidence(Double value) {
                        this.typeConfidence = value;
                    }

                    /**
                     * Gets the value of the zone property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Integer }
                     *     
                     */
                    public Integer getZone() {
                        return zone;
                    }

                    /**
                     * Sets the value of the zone property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Integer }
                     *     
                     */
                    public void setZone(Integer value) {
                        this.zone = value;
                    }

                    /**
                     * Gets the value of the nasty property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Integer }
                     *     
                     */
                    public Integer getNasty() {
                        return nasty;
                    }

                    /**
                     * Sets the value of the nasty property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Integer }
                     *     
                     */
                    public void setNasty(Integer value) {
                        this.nasty = value;
                    }

                    /**
                     * Gets the value of the spinDir property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getSpinDir() {
                        return spinDir;
                    }

                    /**
                     * Sets the value of the spinDir property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setSpinDir(Double value) {
                        this.spinDir = value;
                    }

                    /**
                     * Gets the value of the spinRate property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Double }
                     *     
                     */
                    public Double getSpinRate() {
                        return spinRate;
                    }

                    /**
                     * Sets the value of the spinRate property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Double }
                     *     
                     */
                    public void setSpinRate(Double value) {
                        this.spinRate = value;
                    }

                    /**
                     * Gets the value of the cc property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCc() {
                        return cc;
                    }

                    /**
                     * Sets the value of the cc property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCc(String value) {
                        this.cc = value;
                    }

                    /**
                     * Gets the value of the mt property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getMt() {
                        return mt;
                    }

                    /**
                     * Sets the value of the mt property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setMt(String value) {
                        this.mt = value;
                    }

                }

            }

        }

    }

}
