package com.badminton.servlets;

import com.badminton.dao.PlayerDAO;
import com.badminton.dao.TournamentDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ExistingTournament",urlPatterns = "/ExistingTournament")
public class ExistingTournament extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PrintWriter out = resp.getWriter();
        String trnName = req.getParameter("trnName");
        String email = req.getParameter("email");
        TournamentDAO tdb = new TournamentDAO();
        PlayerDAO pdao = new PlayerDAO();
        int k = tdb.checkAdminTournament(email, trnName);
        if(k!=1)
        {
            out.write("f");
        }
        else {
            Cookie cTourName = new Cookie("tourName", trnName);
            cTourName.setMaxAge(60 * 60 * 24 * 15);// 15 days
            System.out.println(cTourName.getValue());
            resp.addCookie(cTourName);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("AdminDashboard.html");
            System.out.println("tour cookie= " + cTourName.getValue());
            requestDispatcher.forward(req, resp);
            out.write("s");
        }
    }
}
