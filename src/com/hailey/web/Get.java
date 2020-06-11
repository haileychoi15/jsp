
package com.hailey.web;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/test1")
public class Get extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        // UTF-8로 인코딩해서 보냈지만 해석하는 쪽이 다른 인코딩방식으로 해석하면 한글이 깨진다.
        response.setContentType("text/html; charset=UTF-8");
        // 브라우저에게 문자를 자의적으로 해석하지 말고 또한 UTF-8로 해석하라고 알려주는 정보를 보낸다.

        PrintWriter out = response.getWriter();

        String temp = request.getParameter("cnt");
        // 쿼리스트링으로 요청해서 getParameter로 들어오는 값은 무조건 String type


        /*
          http://.../hi?cnt=3   =>  "3"
          http://.../hi?cnt=    =>  ""
          http://.../hi?        =>  null
          http://.../hi         =>  null
        */


        int cnt = 10; // 제대로 된 숫자값이 들어오지 않았을 때의 default 값
        if(temp != null && !temp.equals(""))
            cnt = Integer.parseInt(temp);
        // 만약 cnt에 문자열이 들어오면 그에 대한 처리를 해주지 않았기 때문에 505내부서버오류 발생

        for(int i=0; i<cnt; i++) {
            out.println("Hello jsp get 안녕 <br>");
        }
    }
}


