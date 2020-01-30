package com.badminton.servlets;

import com.badminton.dao.PlayerDAO;
import com.badminton.modules.Player;
import com.badminton.modules.PlayerList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.ArrayList;

@WebServlet(name = "PlayerStats", urlPatterns = "/PlayerStats")
public class PlayerStats extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PlayerDAO db = new PlayerDAO();
        ResultSet rs  = db.getAllPlayers();
        //  resp.setContentType();
        //System.out.println(rs);
        PlayerList plist = new PlayerList();
        ArrayList<Player> playersList = new ArrayList<>();
        PrintWriter pw = resp.getWriter();
        try {
            while (rs.next()) {
                Player p = new Player();
                Player pl = p.createPlayer(rs);
                //System.out.println(pl);
                playersList.add(pl);
            }
            GsonBuilder builder = new GsonBuilder();
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            plist = gson.fromJson(playersList.toString(), PlayerList.class);
            System.out.println(gson.toJson(plist));
            pw.write(gson.toJson(plist));
            //System.out.println(playersList);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            pw.write("null");
        }
    }
}