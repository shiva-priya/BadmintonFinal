package com.badminton.modules;

public class TournamentTeams {

    int tId;
    String tournamentName;
    String teamName;
    int matchesPlayed;

    public TournamentTeams() {
    }

    int wins;
    int lost;

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
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

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public TournamentTeams(int tId, String tournamentName, String teamName, int matchesPlayed, int wins, int lost) {
        this.tId = tId;
        this.tournamentName = tournamentName;
        this.teamName = teamName;
        this.matchesPlayed = matchesPlayed;
        this.wins = wins;
        this.lost = lost;
    }

    @Override
    public String toString() {
        return "{" +
                " \"tId\" : \"" + tId +
                "\", \"tournamentName\" : \"" + tournamentName + "\"" +
                ", \"teamName\" : \"" + teamName + "\"" +
                ", \"matchesPlayed\" : \"" + matchesPlayed +
                "\", \"wins\" : \"" + wins +
                "\", \"lost\" : \"" + lost +
                "\" }";
    }
}
