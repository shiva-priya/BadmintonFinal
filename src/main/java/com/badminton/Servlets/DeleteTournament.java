package com.badminton.Servlets;

import com.badminton.DAO.TournamentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteTournament",urlPatterns = "/DeleteTournament")

public class DeleteTournament extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cook = req.getCookies();
        String tournName = cook[2].getValue();
        System.out.println(tournName);
        PrintWriter out = resp.getWriter();

        TournamentDAO tdb = new TournamentDAO();
        int res = tdb.deleteTournament(tournName);
        if(res==0)
        out.write("f");
        else out.write("p");


    }
}
