package com.elliot.footballmanager.match;

import com.elliot.footballmanager.footballteam.FootballTeam;

/**
 * @author Elliot
 */
public class FootballTeamMatchStats {

    private FootballTeam footballTeam;

    // Stats that are usually tracked in a real football match
    private Integer shots = 0;
    private Integer shotsOnTarget = 0;
    private Integer corners = 0;
    private Integer freekicks = 0;
    private Integer penalties = 0;
    private Integer yellowCards = 0;
    private Integer redCards = 0;

    public FootballTeamMatchStats() {

    }

    public FootballTeamMatchStats(FootballTeam footballTeam) {
        this.footballTeam = footballTeam;
    }

    public FootballTeam getFootballTeam() {
        return footballTeam;
    }

    public void setFootballTeam(FootballTeam footballTeam) {
        this.footballTeam = footballTeam;
    }

    public Integer getShots() {
        return shots;
    }

    public void setShots(Integer shots) {
        this.shots = shots;
    }

    public Integer getShotsOnTarget() {
        return shotsOnTarget;
    }

    public void setShotsOnTarget(Integer shotsOnTarget) {
        this.shotsOnTarget = shotsOnTarget;
    }

    public Integer getCorners() {
        return corners;
    }

    public void setCorners(Integer corners) {
        this.corners = corners;
    }

    public Integer getFreekicks() {
        return freekicks;
    }

    public void setFreekicks(Integer freekicks) {
        this.freekicks = freekicks;
    }

    public Integer getPenalties() {
        return penalties;
    }

    public void setPenalties(Integer penalties) {
        this.penalties = penalties;
    }

    public Integer getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(Integer yellowCards) {
        this.yellowCards = yellowCards;
    }

    public Integer getRedCards() {
        return redCards;
    }

    public void setRedCards(Integer redCards) {
        this.redCards = redCards;
    }
}
