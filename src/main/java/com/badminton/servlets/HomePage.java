package com.badminton.servlets;

import com.badminton.dao.PlayerDAO;
import com.badminton.dao.TeamsDAO;
import com.badminton.dao.TournamentDAO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HomePage",urlPatterns = "/HomePage")
public class HomePage extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        JsonObject res = null;
        res = homeDetails();
        out.write(res.toString());
    }

    public JsonObject homeDetails() {
        TeamsDAO tdao = new TeamsDAO();
        PlayerDAO pdao = new PlayerDAO();
        String update = "";
        update += pdao.getTopPlayer();
        System.out.println(update);
        update += tdao.getTopTeam();
       // update += "}";
        System.out.println(update);
        System.out.println(update.charAt(45));
        JSONObject jo = new JSONObject();
        JsonObject jsonObject = new JsonParser().parse(update).getAsJsonObject();
        System.out.println(jsonObject + "is json");
        return jsonObject;

        // String tquery = "select * from TournamentTeams where teamPoints = (select max(teamPoints) from TournamentTeams);";
//        System.out.println(pquery);
//        try {
//            ResultSet res = stmt.executeQuery(pquery);
//
//            System.out.println(res);
//
//            String winner = "";
//            String tname = "";
//            while (res.next()) {
//
//                update += "{\"fname\": " + res.getString("firstName") + ",\"lname\": " + res.getString("lastName") +
//                        ",\"image\": \""
//                        + res.getString("image") + "\",\"points\":" + res.getString("Points")
//                        + ",\"team\":" + res.getString("team");
//            }
        // ResultSet tres = stmt.executeQuery(tquery);
        //if (tres.next()) {

//                update += ",\"trname\": " + tres.getString("tournamentName") + ",\"tmname\": "
//                        + tres.getString("teamName") +
//                        ",\"pts\":" + tres.getString("teamPoints") + "}";
        // }



    }

}
