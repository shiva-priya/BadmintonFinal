package com.badminton.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisteredTournamentsDAO {
    ConnectionDAO db = new ConnectionDAO();
    Statement stmt;
    public RegisteredTournamentsDAO(){
        stmt = db.dbConnection();
    }

    public int deleteTournament(String tourName){
        try {
            String query3 = "delete from RegisteredTournaments where trnName ='" + tourName + "';";
            return stmt.executeUpdate(query3);
        }catch (SQLException s){
            s.printStackTrace();
            return 0;
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
        TournamentDAO tdb = new TournamentDAO();
        String query = "insert into RegisteredTournaments(email, trnName) values ('" + email + "','" + trnName + "');";
        try {
            ResultSet ra = tdb.checkTournament(trnName);
            if(ra.next()) {
                int rs = stmt.executeUpdate(query);
                System.out.println(rs);
                return rs;
            }
            else {
                return 0;
            }
        } catch (SQLException e) {
            return 0;
        }
    }


}
