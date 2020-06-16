package com.hailey.web.controller.member;

import com.hailey.web.dao.MemberDAO;
import com.hailey.web.entity.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/member/loginAction")
public class LoginActionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/member/loginAction.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userid = request.getParameter("userid");
        String passwd = request.getParameter("passwd");

        MemberDAO memberDAO = new MemberDAO();
        Member loginUser = memberDAO.login(userid, passwd);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter script = response.getWriter();

        if(loginUser == null)  { //로그인 실패

            script.println("<script>");
            script.println("alert('아이디 또는 비밀번호가 틀렸습니다.');");
            script.println("history.back();");
            script.println("</script>");

        }
        else { // 로그인성공
            response.sendRedirect("/notice/list");
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", loginUser);
        }

    }
}