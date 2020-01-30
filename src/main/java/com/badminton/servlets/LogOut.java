package com.badminton.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
@WebServlet(name="LogOut", urlPatterns = "/LogOut")
public class LogOut extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text");
        System.out.println("logout serv start");
        Cookie cUserName = new Cookie("cookuser", null);
        Cookie cTourName = new Cookie("tourName", null);
        cUserName.setMaxAge(0);
       cTourName.setMaxAge(0);
        resp.addCookie(cUserName);
        resp.addCookie(cTourName);
        HttpSession session = req.getSession(false);
        if(session != null){
            session.invalidate();
        }
        req.setAttribute("msg", "You have successfully logged out.");
        resp.getWriter().write("s");
        System.out.println("logout serv end");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.html");
        requestDispatcher.forward(req, resp);


    }
}
