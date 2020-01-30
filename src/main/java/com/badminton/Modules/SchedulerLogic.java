package com.badminton.Modules;

import com.badminton.DAO.PlayerDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SchedulerLogic {

    public String makeSchedule()
    {
        return "";
    }


    public ArrayList<String> createMatches(String startDate,int numberOfRounds, int matchesPerDay, String trnName ) throws ParseException {
        PlayerDAO db = new PlayerDAO();
        ArrayList<String> matches = new ArrayList<>();
     //   String trnName = "pramati";
    //    int numberOfRounds = 1;
        //ArrayList<String> teamsList = new ArrayList<String>();
        System.out.println(trnName);
        ArrayList<String> teamsList = db.getTeams(trnName);
        System.out.println(teamsList);
        // System.out.println(db.getTeams());

        int totTeams = teamsList.size();
        int dtCount =0;
        int firstMatch = 0;
        System.out.println(matchesPerDay +" matches per day");
        System.out.println(numberOfRounds+ " rounds per day");

        for(int k = 1; k<=numberOfRounds; k++)
        {
            matches.add("Round : "+k);
            for(int i = 0; i < totTeams-1; i++)
            {
                //matches.add("Round : "+k);
                for(int j = i+1; j < totTeams; j++)
                {
                    //Match match = new Match();
                    String team1 = teamsList.get(i);
                    String team2 = teamsList.get(j);
                    //match.setTeams(teamsList.get(i), teamsList.get(j));
                   // System.out.println(dtCount);
                    if(dtCount == matchesPerDay) {
                     //   if (firstMatch == 1) {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                            Calendar c = Calendar.getInstance();
                            c.setTime(sdf.parse(startDate));
                            c.add(Calendar.DATE, 1);  // number of days to add
                            startDate = sdf.format(c.getTime());
                            dtCount = 0;
                     //   System.out.println(dtCount + " after date change");
                       // }
                    }

                    matches.add(team1 + " Vs "+ team2 + " On "+startDate);
                    dtCount =dtCount +1;
                  //  firstMatch=1;
                    //System.out.println(dtCount + " out side");

                }
            }
        }
        System.out.println(matches);
        System.out.println(matches.size());
        return matches;
    }







}
