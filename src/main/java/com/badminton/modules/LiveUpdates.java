package com.badminton.modules;

public class LiveUpdates {

    int mid;
    int setNo;
    int iid;
    String tname;
    String team1;
    String team2;
    String player1;
    String player2;
    String winner;

    public LiveUpdates(int mid, int setNo, int iid, String tname, String team1, String team2, String player1, String player2, String winner) {
        this.mid = mid;
        this.setNo = setNo;
        this.iid = iid;
        this.tname = tname;
        this.team1 = team1;
        this.team2 = team2;
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getSetNo() {
        return setNo;
    }

    public void setSetNo(int setNo) {
        this.setNo = setNo;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    @Override
    public String toString() {
        return "{\"Tournament\": " + tname + ",\"MatchID\": " + mid +
                ",\"team1\":"
                +team1 + ",\"team2\":" + team2 + ",\"SetNo\": "
                + setNo +
                ",\"Player1\":" + player1 + ",\"Player2\":" + player2 +
                ",\"WONBY\":" + winner;
    }
}
