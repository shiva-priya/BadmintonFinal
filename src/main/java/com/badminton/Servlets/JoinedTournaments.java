package com.badminton.Servlets;

import com.badminton.DAO.TournamentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "JoinedTournaments", urlPatterns = "/JoinedTournaments")
public class JoinedTournaments extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String email = req.getParameter("email");

        TournamentDAO db = new TournamentDAO();
        ResultSet rs = db.getJoined(email);
        String result = "";
        int k =1;
        try {
            while (rs.next()) {
                result = result + (k++) + ". "+ rs.getString("trnName")+ " \n";
            }
        }
        catch (SQLException e)
        {
            result = result + "f";
        }

        out.write(result);


    }
}
