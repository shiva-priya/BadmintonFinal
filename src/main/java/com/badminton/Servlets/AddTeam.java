package com.badminton.Servlets;

import com.badminton.PlayerDB;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "AddTeam", urlPatterns = "/AddTeam")
public class AddTeam  extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out =  resp.getWriter();
        TeamsDB db;
        db = new TeamsDB();
        PlayerDB pdb = new PlayerDB();
        Cookie[] cook = req.getCookies();
        String tournName = cook[2].getValue();
        System.out.println("add team called");

        // System.out.println(cook[0].getValue());
        //String tournamentName = ;
        //String tournName = "pramati";
        String teamName = req.getParameter("teamname");
        System.out.println(teamName);
        int res = db.addTournament(tournName, teamName);
        System.out.println(res);
        String ja = req.getParameter("players");
        String str = "\\[";
        ja =  ja.replaceAll(str, " ");
        ja =ja.replaceAll("\""," ");
        ja = ja.replaceAll("\\]"," ");
        String[] players = ja.trim().split(",");

        for(int i=0;i<players.length;++i){
            String[] name = players[i].trim().split(" ");
            System.out.println(name);
            String query = "update users set team = '"+teamName+"' where firstName = '"+name[0]+"';";
            int kres =pdb.playersTeamUpdate(query);
            System.out.println(kres);
            System.out.println(query);
        }
        System.out.println(ja);
        out.write(ja);

    }
}
