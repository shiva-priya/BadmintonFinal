package com.badminton.dao;

import com.badminton.modules.LiveUpdates;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LiveUpdatesDAO {
    ConnectionDAO db = new ConnectionDAO();
    Statement stmt;
    public LiveUpdatesDAO(){
        stmt = db.dbConnection();
    }

    public int liveupdate(int mid,String tournName,String t1,String t2,String p1,String p2,int set,String win) {
        try {
            String query = "insert into LiveUpdate (mid,tname, team1, team2, player1, player2,setNo,winner) values ( "
                    + mid+",'"+tournName+"','"+t1+"','"+t2+"','"+p1+"','"+p2+"',"+set+",'"+win+"');";
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            return 0;
        }
    }

    public JsonObject getliveScore() throws SQLException {
        String query = "select * from LiveUpdate where iid = (select max(iid) from LiveUpdate);";
        System.out.println("init");
        try {
            String update = "";
            ResultSet res = stmt.executeQuery(query);
            System.out.println(res);
            //  String jk = "{ \"name\": \"Baeldung\", \"java\": true }";
            JSONObject jo = new JSONObject();
            String winner = "";
            String tname = "";
            if (res.next()) {

                //  jo.put("name",res.getString("tname"));
                //System.out.println(jo+ " json obj");
                LiveUpdates liveObj = new LiveUpdates(res.getInt("mid"), res.getInt("setNo"),res.getInt("iid"),res.getString("tname"),res.getString("team1"),res.getString("team2"),res.getString("player1"),res.getString("player2"),res.getString("winner"));
                update += liveObj.toString();
                /*
                update += "{\"Tournament\": " + res.getString("tname") + ",\"MatchID\": " + res.getString("mid") +
                        ",\"team1\":"
                        + res.getString("team1") + ",\"team2\":" + res.getString("team2") + ",\"SetNo\": "
                        + res.getString("setNo") +
                        ",\"Player1\":" + res.getString("player1") + ",\"Player2\":" + res.getString("player2") +
                        ",\"WONBY\":" + res.getString("winner");

                 */
                winner = liveObj.getWinner();
                System.out.println(winner);
                tname = liveObj.getTname();
                System.out.println(tname);

            }
            System.out.println(winner);
            String wquery = " select teamPoints from TournamentTeams where teamName ='" + winner +
                    "' and tournamentName ='" + tname + "';";
            System.out.println(wquery);

            ResultSet winteam = stmt.executeQuery(wquery);

            while (winteam.next()) {
                update += ",\"points\":" + winteam.getString("teamPoints") + "}";
            }

            System.out.println(update);

            JsonObject jsonObject = new JsonParser().parse(update).getAsJsonObject();
            System.out.println(jsonObject + "is json");
            String json = res.toString();

            return jsonObject;

        } catch (SQLException e) {
            return null;
        }
    }

    public int getWinCount(int mid, String teamName)
    {
        String query = " select count(winner) from LiveUpdate where mid="+mid+" and winner = '"+teamName+"';";
        int k=0;
        try
        {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next())
            {
                k = Integer.parseInt(rs.getString("count(winner)"));
                System.out.println(k);

            }
            return k;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    public String getWinner(){
        try {
            String query = "select winner from LiveUpdate where iid = (select max(iid) from LiveUpdate);";
            ResultSet rs = stmt.executeQuery(query);
            return rs.getString("winner");
        }catch (SQLException s){
            s.printStackTrace();
            return null;
        }
    }

    public String getTourName(){
        try{
            String query = "select tname from LiveUpdate where iid = (select max(iid) from LiveUpdate);";
            ResultSet rs = stmt.executeQuery(query);
            return rs.getString("tnmae");
        }catch (SQLException s){
            s.printStackTrace();
            return null;
        }
    }
}
