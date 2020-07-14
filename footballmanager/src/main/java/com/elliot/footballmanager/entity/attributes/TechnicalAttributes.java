package com.elliot.footballmanager.entity.attributes;

import java.io.Serializable;

/**
 * Collection of all the technical attributes that a player can have.
 *
 * @author Elliot
 */
public class TechnicalAttributes implements Serializable, PlayerAttribute {

  private Integer finishing;
  private Integer longShots;
  private Integer penalties;
  private Integer shotPower;
  private Integer volleys;
  private Integer crossing;
  private Integer curve;
  private Integer freeKick;
  private Integer longPassing;
  private Integer shortPassing;
  private Integer ballControl;
  private Integer dribbling;
  private Integer heading;
  private Integer marking;
  private Integer slidingTackle;
  private Integer standingTackle;

  public TechnicalAttributes() {

  }

  public TechnicalAttributes(Integer finishing, Integer longShots, Integer penalties,
      Integer shotPower,
      Integer volleys, Integer crossing, Integer curve, Integer freeKick, Integer longPassing,
      Integer shortPassing, Integer ballControl, Integer dribbling, Integer heading,
      Integer marking,
      Integer slidingTackle, Integer standingTackle) {
    this.finishing = finishing;
    this.longShots = longShots;
    this.penalties = penalties;
    this.shotPower = shotPower;
    this.volleys = volleys;
    this.crossing = crossing;
    this.curve = curve;
    this.freeKick = freeKick;
    this.longPassing = longPassing;
    this.shortPassing = shortPassing;
    this.ballControl = ballControl;
    this.dribbling = dribbling;
    this.heading = heading;
    this.marking = marking;
    this.slidingTackle = slidingTackle;
    this.standingTackle = standingTackle;
  }

  public Integer getFinishing() {
    return finishing;
  }

  public void setFinishing(Integer finishing) {
    this.finishing = finishing;
  }

  public Integer getLongShots() {
    return longShots;
  }

  public void setLongShots(Integer longShots) {
    this.longShots = longShots;
  }

  public Integer getPenalties() {
    return penalties;
  }

  public void setPenalties(Integer penalties) {
    this.penalties = penalties;
  }

  public Integer getShotPower() {
    return shotPower;
  }

  public void setShotPower(Integer shotPower) {
    this.shotPower = shotPower;
  }

  public Integer getVolleys() {
    return volleys;
  }

  public void setVolleys(Integer volleys) {
    this.volleys = volleys;
  }

  public Integer getCrossing() {
    return crossing;
  }

  public void setCrossing(Integer crossing) {
    this.crossing = crossing;
  }

  public Integer getCurve() {
    return curve;
  }

  public void setCurve(Integer curve) {
    this.curve = curve;
  }

  public Integer getFreeKick() {
    return freeKick;
  }

  public void setFreeKick(Integer freeKick) {
    this.freeKick = freeKick;
  }

  public Integer getLongPassing() {
    return longPassing;
  }

  public void setLongPassing(Integer longPassing) {
    this.longPassing = longPassing;
  }

  public Integer getShortPassing() {
    return shortPassing;
  }

  public void setShortPassing(Integer shortPassing) {
    this.shortPassing = shortPassing;
  }

  public Integer getBallControl() {
    return ballControl;
  }

  public void setBallControl(Integer ballControl) {
    this.ballControl = ballControl;
  }

  public Integer getDribbling() {
    return dribbling;
  }

  public void setDribbling(Integer dribbling) {
    this.dribbling = dribbling;
  }

  public Integer getHeading() {
    return heading;
  }

  public void setHeading(Integer heading) {
    this.heading = heading;
  }

  public Integer getMarking() {
    return marking;
  }

  public void setMarking(Integer marking) {
    this.marking = marking;
  }

  public Integer getSlidingTackle() {
    return slidingTackle;
  }

  public void setSlidingTackle(Integer slidingTackle) {
    this.slidingTackle = slidingTackle;
  }

  public Integer getStandingTackle() {
    return standingTackle;
  }

  public void setStandingTackle(Integer standingTackle) {
    this.standingTackle = standingTackle;
  }
}
