package com.hailey.web.controller.notice;
import com.hailey.web.dao.MemberDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        int result = memberDAO.login(userid, passwd);

        PrintWriter script = response.getWriter();

        switch (result){

            case 1 : //로그인 성공
                response.sendRedirect("/notice/list");
                break;

            case 0 : // 비밀번호 불일치

                // alert 한글 깨짐
                script.println("<script>");
                script.println("alert('비밀번호가 일치하지 않습니다.');");
                script.println("history.back();");
                script.println("</script>");
                break;

            case -1 : // 아이디 없음

                script.println("<script>");
                script.println("alert('존재하지 않는 아이디입니다.');");
                script.println("history.back();");
                script.println("</script>");
                break;

            case -2 : // 데이터베이스 오류
                break;
        }
    }
}