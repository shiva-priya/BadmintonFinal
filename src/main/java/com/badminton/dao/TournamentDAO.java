package com.badminton.dao;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jnr.ffi.annotations.In;
import org.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TournamentDAO {
    ConnectionDAO db = new ConnectionDAO();
    //JsonReader.setLenient(true);
    Statement stmt;

    public TournamentDAO() {
        stmt = db.StatementInit();
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



    public ResultSet getAll() {
        try {
            String query = "select * from tourmentable";
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    //RegTour


    //regtour




   //playerdb teamsdao



    public int deleteTournament(String tourName)
    {
        TeamsDAO team = new TeamsDAO();


        RegisteredTournamentsDAO reg = new RegisteredTournamentsDAO();

        String query2 = "delete from tourmentable where name ='"+tourName+"';";
       // String query3 = "delete from RegisteredTournaments where trnName ='"+tourName+"';";
        try
        {
            int r1 = team.DeleteTournament(tourName);
            int r2 = stmt.executeUpdate(query2);
            int r3 = reg.deleteTournament(tourName);

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

    //TournamentTeams



    //TournamentTeams



    //TournamentTeams



    public int checkAdminTournament(String email, String tourName)
    {
        PlayerDAO pdao = new PlayerDAO();
        String id = pdao.empIdOfPlayer(email);
        int empId = Integer.parseInt(id);
//        String idquery = "select empId from users where email ='"+email+"';";
//        int eid =0;
        try
        {

            String  cquery = "select * from tourmentable where name ='"+tourName+"' and empId ="+empId+";";
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

    public ResultSet checkTournament(String trnName){
        try{
            String checkQuery = "select tournId from tourmentable where name ='"+trnName+"';";
            ResultSet rs = stmt.executeQuery(checkQuery);
            return rs;
        }catch (SQLException s){
            s.printStackTrace();
            return null;
        }
    }


}

