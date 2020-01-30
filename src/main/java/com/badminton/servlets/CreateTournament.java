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
@WebServlet(name = "CreateTournament",urlPatterns = "/CreateTournament")
public class CreateTournament extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter p = resp.getWriter();
        PlayerDAO pdb = new PlayerDAO();
       // resp.setContentType("text/html");
        resp.setContentType("text");
        System.out.println("servlet called");
        System.out.println(req.getParameter("trnName"));
        StringBuilder name = new StringBuilder(req.getParameter("trnName"));
        String location = req.getParameter("tloc");
        String winPrize = req.getParameter("twinp");
        String runPrize = req.getParameter("trunp");
        String email = req.getParameter("email");
        System.out.println(email);
        pdb.updatePlayerRole(email);
        TournamentDAO tourdb = new TournamentDAO();
        tourdb.createTournament(name.toString(),winPrize,runPrize,location,email);
        if(name.toString().contains(" ")) {
            String[] s = name.toString().trim().split(" ");
            name = new StringBuilder();
            for (String value : s)
                name.append(value).append("-");
        }
        String na = name.toString().trim();
        Cookie cTourName = new Cookie("tourName", na);

        cTourName.setMaxAge(60 * 60 * 24 * 15);// 15 days
        System.out.println(cTourName.getValue());
        resp.addCookie(cTourName);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("AdminDashboard.html");
        System.out.println("tour cookie= "+cTourName.getValue());
         requestDispatcher.forward(req, resp);
        String respString = "s";
        System.out.println(respString);
        Cookie[] cook = req.getCookies();
        for(int i=0;i<cook.length;i++){
            System.out.println(cook[i].getValue());
        }
        p.write(respString);
    }

}
