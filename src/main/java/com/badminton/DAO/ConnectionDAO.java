package com.badminton.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDAO {
    private static Statement stmt;
    public Statement dbConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/taskdb", "testuser", "Pramati@123");
            stmt = con.createStatement();
            return stmt;
        } catch (SQLException | ClassNotFoundException e) {
            String response = "Sql query not correct or no such data.";
            System.out.println(response);
            return null;
        }
    }

    public ConnectionDAO() {
        dbConnection();
    }
}
