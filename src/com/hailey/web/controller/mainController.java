package com.hailey.web.controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/main")
public class mainController extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userid = null;
        HttpSession session = request.getSession();
        if(session.getAttribute("userid") != null){

            userid = (String) session.getAttribute("userid");

            System.out.println(userid);
        }

        request.setAttribute("userid", userid);
        request.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(request, response);

    }
}