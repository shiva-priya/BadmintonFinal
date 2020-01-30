package com.badminton.Servlets;

//import jnr.ffi.annotations.In;

import com.badminton.Modules.SchedulerLogic;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;


@WebServlet(name = "Scheduler",urlPatterns = "/Scheduler")

public class Scheduler extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //String startDate,int numberOfRounds, int matchesPerDay, String trnNam yyyy/MM/dd"

        System.out.println("servlet called");
        PrintWriter out = resp.getWriter();

        String dt = req.getParameter("sdate");
        dt = dt.replaceAll("-","/");
        System.out.println(dt);

        int rounds = Integer.parseInt(req.getParameter("rounds"));

        int matchesPerDay = Integer.parseInt( req.getParameter("matches") );

      //  int matchesPerDay  = 0;
        Cookie[] cook = req.getCookies();
        String tournName = cook[2].getValue();

        SchedulerLogic sl = new SchedulerLogic();
        try {
          ArrayList<String> result  =  sl.createMatches(dt,rounds,matchesPerDay,tournName);
          String json = new Gson().toJson(result);
          out.write(String.valueOf(json));
        } catch (ParseException e) {
            e.printStackTrace();
        }

      //  out.write("r "+rounds+" d"+dt+" ma "+matchesPerDay+" tr "+tournName);


            /*
            //out.write(dt);

            Date date = null;
            date = new SimpleDateFormat("yyyy/MM/dd").parse(dt);
           // System.out.println("wrong form");
            System.out.println(date);
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String testDateString = df.format(date);
            System.out.println("String in dd/MM/yyyy format is: " + testDateString);
          //  out.write(testDateString);

             */

    }
}
