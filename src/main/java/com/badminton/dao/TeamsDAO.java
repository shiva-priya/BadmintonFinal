package com.badminton.dao;

//import com.badminton.Admin;

import com.badminton.dao.ConnectionDAO;
import com.badminton.modules.TournamentTeams;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TeamsDAO {
    ConnectionDAO db = new ConnectionDAO();
    private static Statement stmt;
    public TeamsDAO() {
        stmt  = db.dbConnection();
    }

    public int addTournament(String tournName, String teamName)
    {
        String mquery = "insert into TournamentTeams (tournamentName ,teamName) values ('"+ tournName +"','"+teamName+"');";
        System.out.println(mquery);
        try {
            int k =    stmt.executeUpdate(mquery);
            return k;
        }
        catch(SQLException e)
        {
            return 0;
        }

    }

    public  ArrayList<String> listAll(){
        try{
            String query = "select Name from TournamentTeams";
            ResultSet rs =  stmt.executeQuery(query);
            ArrayList<String> teamName = new ArrayList<>();
            while(rs.next()){
                teamName.add(rs.getString("teamName"));
            }
            return teamName;
        }catch (SQLException q) {
            String response = "Sql query not correct or no such data.";
            System.out.println(response);
            return null;
        }
    }

    public  ResultSet getAll(String TournamentTeams){
        try{
            String query = "select * from TournamentTeams where tournamentName = '"+TournamentTeams+"'";
            return stmt.executeQuery(query);

            //return teams;
        }catch (SQLException q) {
            String response = "Sql query not correct or no such data.";
            System.out.println(response);
            return null;
        }
    }


    public String getTopTeam(){
        try{
            String tquery = "select * from TournamentTeams where teamPoints = (select max(teamPoints) from TournamentTeams);";
            ResultSet tres = stmt.executeQuery(tquery);
            String result = "";
            if (tres.next()) {
                TournamentTeams tTeams = new TournamentTeams(tres.getInt("tId"),tres.getString("tournamentName"),tres.getString("teamName"),tres.getInt("matchesPlayed"),tres.getInt("wins"),tres.getInt("lost"));
               // result = tTeams.toString();

                result += ",\"trname\": " + tres.getString("tournamentName") + ",\"tmname\": "
                        + tres.getString("teamName") +
                        ",\"pts\":" + tres.getString("teamPoints") + "}";
             }
            return result;
        }catch (SQLException s){
            s.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> getTeams(String trnName) {
        ArrayList<String> result = new ArrayList<>();
        String query = "select distinct teamName from TournamentTeams where tournamentName = '"+trnName +"';";
        try
        {
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                // System.out.println(rs.getString("teamName"));
                result.add(rs.getString("teamName"));
            }
            //System.out.println(result);
            return result;
        }

        catch (SQLException e)
        {
            return result;
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

    public int  DeleteTournament(String tourName){
        try{
            String query = "delete from TournamentTeams where tournamentName ='"+tourName+"';";
            int r1 = stmt.executeUpdate(query);
            return r1;
        }catch (SQLException s){
            s.printStackTrace();
            return 0;
        }
    }

    public int getTopTeamFromTour(String team, int points, String tourn) {
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
            return k;
        } catch (SQLException e) {
            System.out.println("ex");
            e.printStackTrace();
            return 0;
        }
    }

    public int updateTeamPoints(String team, int points, String tourn){
        try{
            int k = getTopTeamFromTour(team,points,tourn);
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
}