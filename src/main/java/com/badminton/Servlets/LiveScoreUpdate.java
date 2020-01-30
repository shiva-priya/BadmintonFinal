package com.badminton.Servlets;


import com.badminton.DAO.PlayerDAO;
import com.badminton.DAO.TournamentDAO;
import com.google.gson.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LiveScoreUpdate",urlPatterns = "/LiveScoreUpdate")
public class LiveScoreUpdate extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        int set = Integer.parseInt(req.getParameter("set"));
        String t1 = req.getParameter("t1");
        String t2 = req.getParameter("t2");
        String p1 = req.getParameter("p1");
        String p2 = req.getParameter("p2");
        int sp1 = Integer.parseInt(req.getParameter("sp1"));
        int sp2 = Integer.parseInt(req.getParameter("sp2"));
        String win = req.getParameter("win");
        int mid = Integer.parseInt(req.getParameter("mid"));
        Cookie[] cook = req.getCookies();
        String tournName = cook[2].getValue();
        TournamentDAO tdb = new TournamentDAO();
        PlayerDAO pdb = new PlayerDAO();

        if(set==3)
        {
          //  tdb.check
            tdb.updateMatchesPlayed(t1,tournName);
            tdb.updateMatchesPlayed(t2,tournName);
            pdb.updateMatchesPlayed(p1);
            pdb.updateMatchesPlayed(p2);
            int t1w = tdb.getWinCount(mid,t1);
            int t2w = tdb.getWinCount(mid,t2);
            if(t1w>t2w)
            {
                tdb.updateWins(t1,tournName);
                tdb.updateLosses(t2,tournName);
            }
            else
            {
                tdb.updateWins(t2,tournName);
                tdb.updateLosses(t1,tournName);
            }
        }
            String query = "insert into LiveUpdate (mid,tname, team1, team2, player1, player2,setNo,winner) values ( "
                    + mid+",'"+tournName+"','"+t1+"','"+t2+"','"+p1+"','"+p2+"',"+set+",'"+win+"');";
            System.out.println(query);
            int res  = tdb.liveupdate(query);

            tdb.updateTeamPoints(t1,sp1,tournName);
            tdb.updateTeamPoints(t2,sp2,tournName);
            pdb.updatePlayerPoints(p1,sp1);
            pdb.updatePlayerPoints(p2,sp2);


            out.write(Integer.toString(res));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  resp.setContentType( "application/json" );
        PrintWriter out = resp.getWriter();
     //   out.write("hey called");
        TournamentDAO tdb = new TournamentDAO();
        JsonObject res = null;
        try {
            res = tdb.getliveScore();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.write(res.toString());
       // out.write("hello");

    }
}
