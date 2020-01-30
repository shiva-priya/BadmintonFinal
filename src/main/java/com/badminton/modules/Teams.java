package com.badminton.modules;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Teams {
    String tId;
    String tournamentName;
    String teamName;
    String MatchesPlayed;
    String wins;
    String lost;
    String teamPoints;

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getMatchesPlayed() {
        return MatchesPlayed;
    }

    public void setMatchesPlayed(String matchesPlayed) {
        MatchesPlayed = matchesPlayed;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getLost() {
        return lost;
    }

    public void setLost(String lost) {
        this.lost = lost;
    }

    public String getTeamPoints() {
        return teamPoints;
    }

    public void setTeamPoints(String teamPoints) {
        this.teamPoints = teamPoints;
    }

    public Teams() {
    }


    public Teams createTeam (ResultSet rs){
        Teams team = new Teams();
        try{
            team.setTeamName(rs.getString("teamName"));
            team.settId(rs.getString("tId"));
            team.setMatchesPlayed(rs.getString("MatchesPlayed"));
            team.setWins(rs.getString("wins"));
            team.setLost(rs.getString("lost"));
            team.setTeamPoints(rs.getString("teamPoints"));
            team.setTournamentName(rs.getString("tournamentName"));
        }catch (SQLException s){
            s.printStackTrace();

        }
        return team;
    }

    @Override
    public String toString() {
        return "{" +
                " \"tId\" : \"" + tId +
                "\", \"teamName\" : \"" + teamName  +
                "\", \"MatchesPlayed\" : \"" + MatchesPlayed +
                "\", \"wins\" : \"" + wins +
                "\", \"lost\" : \"" + lost +
                "\", \"tournamentName\" : \"" + tournamentName +
                "\", \"teamPoints\" : \"" + teamPoints +
                "\" }";
    }
}
