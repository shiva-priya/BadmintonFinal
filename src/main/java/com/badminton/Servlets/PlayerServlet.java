package com.badminton.Servlets;


import com.badminton.DAO.PlayerDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "PlayerServlet", urlPatterns = "/PlayerServlet")
public class PlayerServlet extends HttpServlet {

    public PlayerServlet()
    {}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fname = req.getParameter("firstName");
        String lname = req.getParameter("lastName");
        //String uname = req.getParameter("userName");
        String email = req.getParameter("email");
        //String gender = req.getParameter("gender");
        //String image = req.getParameter("image");
        //String phone = req.getParameter("phone");
        //String ssize = req.getParameter("shirtSize");
        int eid =Integer.parseInt( req.getParameter("empId"));
        //String age = req.getParameter("age");
        String pwd = req.getParameter("password");
        PrintWriter out = resp.getWriter();
        PlayerDAO database = new PlayerDAO();
        database.addPlayer(fname,lname,email,eid,pwd);
        Cookie cUserName = new Cookie("cookuser", email.trim());
        System.out.println("login cookie = "+cUserName);
        System.out.println("login cookie value = "+cUserName.getValue());

        cUserName.setMaxAge(60 * 60 * 24 * 15);// 15 days

        resp.addCookie(cUserName);


        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("sessuser", email.trim());
        System.out.println("cookie enabled");

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("playerReg.html");
        requestDispatcher.forward(req, resp);



    }
}