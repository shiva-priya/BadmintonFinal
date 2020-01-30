package com.badminton.servlets;

//import com.badminton.Modules.PlayerList;
import com.badminton.modules.Teams;

import com.badminton.modules.TeamsList;
import com.badminton.dao.TeamsDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
//import java.sql.ResultSet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "TeamStats", urlPatterns = "/TeamStats")
public class TeamStats extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        TeamsDAO tdb = new TeamsDAO();
       // Cookie[] cook = req.getCookies();
        String tournName = req.getParameter("trnName");
        ResultSet rs = tdb.getAll(tournName);
        ArrayList<Teams> teams = new ArrayList<>();
        Teams team = new Teams();
        try {
                while (rs.next()) {
                teams.add(team.createTeam(rs));
            }
        }catch (SQLException s){
            s.printStackTrace();
        }
        TeamsList tlist = new TeamsList();
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        System.out.println(teams.toString());
        tlist = gson.fromJson(teams.toString(), TeamsList.class);
        System.out.println(gson.toJson(tlist));
        PrintWriter pw = resp.getWriter();
        pw.write(gson.toJson(tlist));
    }
}
