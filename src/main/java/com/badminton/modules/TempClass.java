package com.badminton.modules;

import com.badminton.dao.TournamentDAO;


import java.sql.SQLException;
import java.text.ParseException;

public class TempClass {

    public static void main(String args[]) throws ParseException, SQLException {

//        TournamentDAO db = new TournamentDAO();
//        //db.updateMatchesPlayed("abc","pramati");
//        db.getWinCount(118,"yellow");
//        db.getWinCount(118,"jingle");
//        /*

       // JSONParser parser = new JSONParser();
        String k = "{\"name\":\"prya\"}";
      //  JSONObject json = (JSONObject) parser.parse(k);

        String a = "hey";
          //     out.format("json");
        /*
        String update = "";
        try {
            while (res.next()) {


                String jk = "{ \"name\": \"Baeldung\", \"java\": true }";
                JsonObject jsonObject = new JsonParser().parse(jk).getAsJsonObject();
                System.out.println(jsonObject);


                update += "{\"Tournament\": " + res.getString("tname") + "\"MatchID\": " + res.getString("mid") +
                        "\"team1\":"
                        + res.getString("team1") + "\"team2\":" + res.getString("team2") + "\"SetNo\": "
                        + res.getString("setNo") +
                        "\"Player1\":" + res.getString("player1") + "\"Player2\":" + res.getString("player2") +
                        "\"WONBY\":" + res.getString("winner");
                System.out.println(update);

            }
        } catch (SQLException e) {
            System.out.println(e);
        }

         */
        //String json = res.toString();

      /*  String jk = "{ \"name\":"+a+", \"java\": true }";
        JsonObject jsonObject = new JsonParser().parse(jk).getAsJsonObject();
        System.out.println(jsonObject);


         */

        //PlayerDAO pdb = new PlayerDAO();
        //pdb.updatePlayerPoints("shiva",3);
       // db.getliveScore();

        //db.getJoined("spriya@a");
      //  db.updateTeamPoints("purple",3," summer");

      //  SchedulerLogic sl = new SchedulerLogic();
        //sl.createMatches("2020/12/10",3,2,"pramati");
        /*
        SchedulerLogic sl = new SchedulerLogic();
      //  sl.createMatches();
        String dt = "2020/02/11";
        Date date=new SimpleDateFormat("yyyy/MM/dd").parse(dt);
        System.out.println(date);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String testDateString = df.format(date);
        System.out.println("String in dd/MM/yyyy format is: " + testDateString);

x
         */

        /*
        String dt = "2008-01-01";  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(dt));
        c.add(Calendar.DATE, 1);  // number of days to add
        dt = sdf.format(c.getTime());

        System.out.println(dt);


         */
    }
}
