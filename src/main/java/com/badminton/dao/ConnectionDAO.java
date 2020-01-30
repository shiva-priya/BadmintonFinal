package com.badminton.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDAO {
    private static Statement stmt;
    private  static Connection con;
    public void dbConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdb", "testuser", "Pramati@123");

        } catch (SQLException | ClassNotFoundException e) {
            String response = "Sql query not correct or no such data.";
            System.out.println(response);

        }
    }

    public Statement StatementInit(){
        try{
            stmt  = con.createStatement();
            return stmt;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public ConnectionDAO(String check) {
        if(check.equals("home"))
            dbConnection();
    }
    public ConnectionDAO() {

    }
}
