package com.hailey.web.controller.member;

import com.hailey.web.dao.MemberDAO;
import com.hailey.web.entity.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/member/joinAction")
public class JoinActionController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/member/joinAction.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 혹여나 파일 업로드하는 from 타입이 enctype="multipart/form-data" 일 경우
        // request.getParameter으로 받으면 안되고 MultipartRequest 객체 얻어와야한다.

        String userid = request.getParameter("userid");
        String passwd = request.getParameter("passwd");
        String name = request.getParameter("name");
        String mobile = request.getParameter("mobile");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");

        if(gender.equals("여성"))
            gender = "여";
        else
            gender = "남";


   /*     // jsp에서 쓸 수 있게 보내기
        Member member = new Member(userid, passwd, name, mobile, 0, 1, email, birthday, gender);
        request.setAttribute("member", member);
   */

        Member member  = new Member();
        member.setUserid(userid);
        member.setPasswd(passwd);
        member.setName(name);
        member.setMobile(mobile);
        member.setGender(gender);
        member.setEmail(email);
        member.setBirthday(birthday);

        MemberDAO memberDAO = new MemberDAO();
        int result = memberDAO.join(member);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter script = response.getWriter();

        if(result == 1){ //회원가입 성공
            script.println("<script>");
            script.println("alert('회원가입이 완료되었습니다.');");
            script.println("location.href = '../index';");
            script.println("</script>");
        }
        else{ // 데이터베이스 오류
            script.println("<script>");
            script.println("alert('회원가입에 실패했습니다.');");
            script.println("history.back();");
            script.println("</script>");
        }

    }
}