package com.hailey.web;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/calc")
public class Post extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        // UTF-8로 인코딩해서 보냈지만 해석하는 쪽이 다른 인코딩방식으로 해석하면 한글이 깨진다.
        response.setContentType("text/html; charset=UTF-8");
        // 브라우저에게 문자를 자의적으로 해석하지 말고 또한 UTF-8로 해석하라고 알려주는 정보를 보낸다.

        request.setCharacterEncoding("UTF-8");
        // form에서 post로 보낸 한글을 서버에서 UTF-8 읽어라.

        PrintWriter out = response.getWriter();

        String x_ = request.getParameter("x");
        String y_ = request.getParameter("y");
        String op = request.getParameter("operator"); // 누른 것에 따라서 value값이 들어옴
        // 쿼리스트링으로 요청해서 getParameter로 들어오는 값은 무조건 String type

        if(x_.equals("") || y_.equals("")) {
            out.println("두 개의 값을 입력해주세요.");
        }
        else {

            int x = Integer.parseInt(x_);
            int y = Integer.parseInt(y_);

            if(op.equals("덧셈")) out.println(x+y);
            else out.println(x-y);

        }
    }
}

