package com.hailey.web.controller.member;

import com.hailey.web.entity.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/member/myInfo")
public class MyInfoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Member loginUser = (Member) session.getAttribute("loginUser");

        if(loginUser != null) {
            request.getRequestDispatcher("/WEB-INF/view/member/myInfo.jsp").forward(request, response);
        }
    }

}


