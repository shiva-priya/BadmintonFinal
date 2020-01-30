package com.badminton.servlets;

import com.badminton.dao.PlayerDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "PlayerLogIn", urlPatterns = "/PlayerLogIn")
public class PlayerLogIn extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("getQueryString = "+req.getQueryString());

        PrintWriter out = resp.getWriter();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println("-------------------------->>>>>>>>>>>>"+req.getMethod());
        System.out.println("i/p stream "+String.valueOf(req.getInputStream()));
        System.out.println(email);
        System.out.println(password);
        System.out.println("--------------------------");
        PlayerDAO database = new PlayerDAO();
        System.out.println("before login check");
        boolean result = database.checkLogin(email,password);
        System.out.println("after login check");
        if(result)
        {

            Cookie cUserName = new Cookie("cookuser", email.trim());
            System.out.println("login cookie = "+cUserName);
            System.out.println("login cookie value = "+cUserName.getValue());
         //   Cookie cPassword = new Cookie("cookpass", email.trim());
            cUserName.setMaxAge(60 * 60 * 24 * 15);// 15 days
          //  cPassword.setMaxAge(60 * 60 * 24 * 15);
            resp.addCookie(cUserName);
           // resp.addCookie(cPassword);

            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("sessuser", email.trim());
            System.out.println("cookie enabled");

           // RequestDispatcher requestDispatcher = req.getRequestDispatcher("playerDetails.html");
           // System.out.println(requestDispatcher.);
            //requestDispatcher.forward(req, resp);
            out.write("s");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("playerDetails.html");
            requestDispatcher.forward(req,resp);
        }
        else
        {
            System.out.println("f");
            out.write("failure");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("playerReg.html");
           // System.out.println(requestDispatcher.);
            requestDispatcher.forward(req, resp);
        }
    }
}
