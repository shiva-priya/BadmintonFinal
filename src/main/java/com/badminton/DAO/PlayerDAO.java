package com.badminton.DAO;

import java.sql.*;
import java.util.ArrayList;


public class PlayerDAO {
    ConnectionDAO db = new ConnectionDAO();
    //Connection con;
    Statement stmt;

    public PlayerDAO()
    {
        stmt = db.dbConnection();
    }

    public ResultSet getAllPlayers() {
        try {
            String query = "select * from users order by Points desc;";
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }



    public int addPlayer(String firstName, String lastName, String email, int eid, String password)
    {
        try {
            String query = " create table users(\n" +
                    " firstName varchar(25), lastName varchar(25), userName varchar(20), email varchar(40), gender varchar(15),\n" +
                    " team varchar(20) default \"none\", image varchar(100) default \"http://cdn.onlinewebfonts.com/svg/img_184513.png\",\n" +
                    " phone varchar(13) , type varchar(15), shirtSize varchar(10), empId int unique, age int,\n" +
                    " matchesPlayed int default 0, position int default 0, bidStatus int default -1, role varchar(15) default \"player\", password varchar(30),\n" +
                    " userId int auto_increment, primary key (userId)\n" +
                    " );";
            stmt.executeUpdate(query);
            System.out.println("in add Player");
             query = "insert into users (firstName,lastName,email,empId,password) values(\""+firstName+"\",\""+lastName+"\",\""
                    +email+"\","+eid+",\""+password+"\");";
            System.out.println(query);

            stmt.executeUpdate(query);
        }
        catch (Exception e) {
            try{

                String query = "insert into users (firstName,lastName,email,empId,password) values(\""+firstName+"\",\""+lastName+"\",\""
                        +email+"\","+eid+",\""+password+"\");";
                stmt.executeUpdate(query);
            }catch(SQLException s){
                String response = "Sql query not correct or no such data.";
                System.out.println(response);
            }

        }

        return 1;
    }

    public boolean checkLogin(String email, String password)  {
        try {
            System.out.println(email);
            String query = "select password from users where email = \"" + email +"\";";
            System.out.println("query = "+query);
            ResultSet rs = stmt.executeQuery(query);
            boolean rsq = rs.next();
            System.out.println(rsq);
             if(rsq){
                 String pass = rs.getString("password");
                 System.out.println(pass);
                 System.out.println(pass.equals(password));
                 return pass.equals(password);
             }else{
                 System.out.println("else block 1 false");
                return false;
            }

        }
        catch (SQLException e)
        {
            System.out.println("catch block 1 false");
            return false;
        }
    }


    public boolean modifyPlayer(String player, String type,  int bidStatus)
    {
        try {
            String query = "update users set type = \"" +type +"\", bidStatus = \""+ bidStatus+ "\" where firstName = \""+player+"\" ;";
            System.out.println(query);
            int rs = stmt.executeUpdate(query);
            System.out.println(rs);

            if(rs>1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (SQLException e)
        {
            return false;
        }

    }

    public ResultSet getDetails(String email){
        try {
            String query = "select * from users where email = '"+email+"'";
            System.out.println(query);
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) {
                return rs;
            }else{
                return null;
            }

        }catch(SQLException s){
            s.printStackTrace();
            return null;
        }
    }

    public String empIdOfPlayer(String email){
        String query = "select empId from users where email = '"+ email +"' ;";
        System.out.println(query);
        try {
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next()) {
                String empId = rs.getString("empId");
                return empId;
            }
            else{
                return "";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }



    public int playersTeamUpdate(String query)
    {
        try
        {
            int k = stmt.executeUpdate(query);
            return k;
        }
        catch (SQLException e)
        {
            return  0;
        }
    }

    public ArrayList<String> getPlayersByTeam(String team){
        try{
            String query = "select firstName,lastName from users where team = '"+team+"';";
             ResultSet rs = stmt.executeQuery(query);
            ArrayList<String> playerName = new ArrayList<>();
            while(rs.next()){
                String fname = rs.getString("firstName");
                String lname = rs.getString("lastName");
                String name = fname+" "+lname;
                playerName.add(name);
            }
            System.out.println(playerName);
            return playerName;
        }catch (Exception s){
            s.printStackTrace();
            return null;
        }
    }

    public  ArrayList<String> listAllByTour(String tour){
        try{
            String query = "select teamName from TournamentTeams where tournamentName = '"+tour+"';";
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

    public int updatePlayerPoints(String player, int points)
    {
        try
        {
            int k=0;
            String query ="select max(Points) from users where firstName ='"+player+"';";
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next())
            {
                k = rs.getInt(1);
                System.out.println("this is k "+k);
            }
            k= k+points;
            System.out.println(k);

            String query1 = "update users set Points ="+k+" where firstName ='"+player+"';";
            int res = stmt.executeUpdate(query1);
            System.out.println(res);
            // int k = Integer.parseInt(rs.getString("pts"));

            System.out.println("no ex");
            return res;
        }
        catch (SQLException e)
        {
            System.out.println("ex");
            e.printStackTrace();
            return 0;
        }
    }

    public void updatePlayerRole(String email){
        String query = "update users set role = 'admin' where email ='"+email+"'";
        try{
            stmt.executeUpdate(query);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateMatchesPlayed(String player)
    {
        String query = "update users set matchesPlayed=matchesPlayed+1 where firstName ='"+player+"';";
        System.out.println(query);
        try
        {
            int k = stmt.executeUpdate(query);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
