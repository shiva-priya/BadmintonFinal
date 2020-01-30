package com.badminton.DAO;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TournamentDAO {
    ConnectionDAO db = new ConnectionDAO();
    //JsonReader.setLenient(true);
    Statement stmt;

    public TournamentDAO() {
        stmt = db.dbConnection();
    }

    public void createTournament(String name, String wPrize, String rPrize, String loc, String email) {
        PlayerDAO pdb = new PlayerDAO();
        String id = pdb.empIdOfPlayer(email);

        try {
            String query = "insert into tourmentable(name,winPrize,runPrize,location,empId) values('" + name + "'," + wPrize + "," + rPrize + ",'" + loc + "'," + id +");";
            System.out.println(query);
            int k = stmt.executeUpdate(query);
            System.out.println(k + "is k");
        } catch (SQLException s) {
            s.printStackTrace();
        }

    }

    public String empIdOfTour(String email) {
        PlayerDAO pdb = new PlayerDAO();
        String id = pdb.empIdOfPlayer(email);
        try {
            String query = "select name from tournamentable where empId = '" + id + "' ;";
            ResultSet rs = stmt.executeQuery(query);
            return rs.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public ResultSet getAll() {
        try {
            String query = "select * from tourmentable";
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getJoined(String email) {
        String query = "select distinct trnName from RegisteredTournaments where email ='" + email + "';";
        try {
            ResultSet res = stmt.executeQuery(query);
           /* while(res.next())
            {
                System.out.println(res.getString("trnName"));
            }

            */
            return res;
        } catch (SQLException e) {
            return null;
        }
    }


    public int joinTourn(String email, String trnName) {
        String query = "insert into RegisteredTournaments(email, trnName) values ('" + email + "','" + trnName + "');";
        String checkQuery = "select tournId from tourmentable where name ='"+trnName+"';";
        System.out.println("In joinTourn ");
        try {
            ResultSet ra = stmt.executeQuery(checkQuery);
            if(ra.next()) {
                System.out.println(query);
                int rs = stmt.executeUpdate(query);
                //   System.out.println(rs.next());
                // return rs.next();
                System.out.println(rs);
                return rs;
            }
            else
            {
                return 0;
            }

        } catch (SQLException e) {
            return 0;
        }
    }

    public int liveupdate(String query) {
        try {
            return stmt.executeUpdate(query);
        } catch (SQLException e) {
            return 0;
        }
    }

    public int updateTeamPoints(String team, int points, String tourn) {

        try {
            int k = 0;
            String query = "select max(teamPoints) as pts from TournamentTeams where teamName ='" + team +
                    "' and tournamentName ='" + tourn + "';";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                k = rs.getInt(1);
                System.out.println("this is k " + k);
            }
            k = k + points;
            System.out.println(k);

            String query1 = "update TournamentTeams set teamPoints =" + k + " where teamName ='" + team +
                    "' and tournamentName ='" + tourn + "';";
            int res = stmt.executeUpdate(query1);
            System.out.println(res);
            // int k = Integer.parseInt(rs.getString("pts"));

            System.out.println("no ex");
            return res;
        } catch (SQLException e) {
            System.out.println("ex");
            e.printStackTrace();
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
            while (res.next()) {

                //  jo.put("name",res.getString("tname"));
                //System.out.println(jo+ " json obj");

                update += "{\"Tournament\": " + res.getString("tname") + ",\"MatchID\": " + res.getString("mid") +
                        ",\"team1\":"
                        + res.getString("team1") + ",\"team2\":" + res.getString("team2") + ",\"SetNo\": "
                        + res.getString("setNo") +
                        ",\"Player1\":" + res.getString("player1") + ",\"Player2\":" + res.getString("player2") +
                        ",\"WONBY\":" + res.getString("winner");
                winner = res.getString("winner");
                System.out.println(winner);
                tname = res.getString("tname");
                System.out.println(tname);

            }
            System.out.println(winner);
            String wquery = " select teamPoints from TournamentTeams where teamName ='" + winner +
                    "' and tournamentName ='" + tname + "';";
            System.out.println(wquery);

            ResultSet winteam = stmt.executeQuery(wquery);

            if (winteam.next()) {
                update += ",\"points\":" + winteam.getString("teamPoints") + "}";
            }
            else
            {
                update +="}";
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

    public JsonObject homeDetails() {
        String pquery = "select * from users where Points = (select max(Points) from users);";
        String tquery = "select * from TournamentTeams where teamPoints = (select max(teamPoints) from TournamentTeams);";
        System.out.println(pquery);
        try {
            ResultSet res = stmt.executeQuery(pquery);
            String update = "";
            System.out.println(res);
            JSONObject jo = new JSONObject();
            String winner = "";
            String tname = "";
            while (res.next()) {
                update += "{\"fname\": " + res.getString("firstName") + ",\"lname\": " + res.getString("lastName") +
                        ",\"image\": \""
                        + res.getString("image") + "\",\"points\":" + res.getString("Points")
                        + ",\"team\":" + res.getString("team");
            }
            System.out.println(update);


            ResultSet tres = stmt.executeQuery(tquery);
            if (tres.next()) {

                update += ",\"trname\": " + tres.getString("tournamentName") + ",\"tmname\": "
                        + tres.getString("teamName") +
                        ",\"pts\":" + tres.getString("teamPoints") + "}";
            }
            System.out.println(update);
            System.out.println(update.charAt(45));

            JsonObject jsonObject = new JsonParser().parse(update).getAsJsonObject();
            System.out.println(jsonObject + "is json");
            return jsonObject;

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

    public int deleteTournament(String tourName)
    {
        String query = "delete from TournamentTeams where tournamentName ='"+tourName+"';";
        String query2 = "delete from tourmentable where name ='"+tourName+"';";
        String query3 = "delete from RegisteredTournaments where trnName ='"+tourName+"';";
        try
        {
            int r1 = stmt.executeUpdate(query);
            int r2 = stmt.executeUpdate(query2);
            int r3 = stmt.executeUpdate(query3);

            if(r1<0 || r2<0 || r3<0)
            {
                System.out.println("failed");
                return 0;
            }

           /* if(r1.next()==false || r2.next()==false || r3.next()==false)
            {
                System.out.println("failed");
                return 0;
            }

            */
            System.out.println("done");
            return 1;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public int updateMatchesPlayed(String teamName, String tourName)
    {
        String query = "update TournamentTeams set matchesPlayed=matchesPlayed+1 where teamName ='"
                +teamName+"' and tournamentName='"+tourName+"';";
        try
        {
            int rs = stmt.executeUpdate(query);
            return rs;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
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

    public int updateWins(String teamName, String tourName)
    {


        String query = "update TournamentTeams set wins=wins+1 where teamName ='"
                +teamName+"' and tournamentName='"+tourName+"';";
        try
        {
            int rs = stmt.executeUpdate(query);
            return rs;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }

    }

    public int updateLosses(String teamName, String tourName)
    {
        String query = "update TournamentTeams set lost=lost+1 where teamName ='"
                +teamName+"' and tournamentName='"+tourName+"';";
        try
        {
            int rs = stmt.executeUpdate(query);
            return rs;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    public int checkAdminTournament(String email, String tourName)
    {
        String idquery = "select empId from users where email ='"+email+"';";
        int eid =0;
        try
        {
            ResultSet rs = stmt.executeQuery(idquery);
            if(rs.next())
            {
               eid = Integer.parseInt(rs.getString("empId"));
            }
            String  cquery = "select * from tourmentable where name ='"+tourName+"' and empId ="+eid+";";
            ResultSet ra = stmt.executeQuery(cquery);
            if(ra.next())
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return 0;
        }
    }


}

