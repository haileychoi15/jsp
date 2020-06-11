package com.hailey.web;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/calculator")
public class Calculator extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String exp = "0";
        Cookie[] cookiesArr = request.getCookies();// 쿠키 온 거 읽기

        if(cookiesArr != null) {
            for (Cookie c : cookiesArr)
                if (c.getName().equals("exp")) {
                    exp = c.getValue();
                    break;
                }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.write("<!DOCTYPE html>");
        out.write("<html lang=\"en\">");
        out.write("<head>");
        out.write("<meta charset=\"UTF-8\">");
        out.write("<title>Title</title>");
        out.write(" <style>");
        out.write("         input {");
        out.write("     width: 50px;");
        out.write("    height: 50px;");
        out.write("}");
        out.write(".output {");
        out.write("   height: 50px;");
        out.write("   background-color: #e9e9e9;");
        out.write("   font-size: 24px;");
        out.write("   font-weight: bold;");
        out.write("   text-align: right;");
        out.write("   padding: 0px 5px; /* 상하 0px, 좌우 5px */");
        out.write("}");
        out.write(" </style>");
        out.write("</head>");
        out.write("<body>");
        out.write(" <div>");
        out.write("    <form method=\"post\">"); // 자기자신한테 보낼때는 action 안써도 된다.
        out.write("      <table>");
        out.write("          <tr>");
        out.printf("              <td class=\"output\" colspan=\"4\">%s</td>", exp);
        out.write("          </tr>");
        out.write("         <tr>");
        out.write("            <td><input type=\"submit\" name=\"operator\" value=\"CE\"></td>");
        out.write("            <td><input type=\"submit\" name=\"operator\" value=\"C\"></td>");
        out.write("            <td><input type=\"submit\" name=\"operator\" value=\"BS\"></td>");
        out.write("            <td><input type=\"submit\" name=\"operator\" value=\"/\"></td>");
        out.write("        </tr>");
        out.write("       <tr>");
        out.write("       <td><input type=\"submit\" name=\"value\" value=\"7\"></td>");
        out.write("         <td><input type=\"submit\" name=\"value\" value=\"8\"></td>");
        out.write("<td><input type=\"submit\" name=\"value\" value=\"9\"></td>");
        out.write("<td><input type=\"submit\" name=\"operator\" value=\"*\"></td>");
        out.write("</tr>");
        out.write("<tr>");
        out.write("          <td><input type=\"submit\" name=\"value\" value=\"4\"></td>");
        out.write("          <td><input type=\"submit\" name=\"value\" value=\"5\"></td>");
        out.write("          <td><input type=\"submit\" name=\"value\" value=\"6\"></td>");
        out.write("         <td><input type=\"submit\" name=\"operator\" value=\"-\"></td>");
        out.write("     </tr>");
        out.write("     <tr>");
        out.write("         <td><input type=\"submit\" name=\"value\" value=\"1\"></td>");
        out.write("         <td><input type=\"submit\" name=\"value\" value=\"2\"></td>");
        out.write("         <td><input type=\"submit\" name=\"value\" value=\"3\"></td>");
        out.write("         <td><input type=\"submit\" name=\"operator\" value=\"+\"></td>");
        out.write("     </tr>");
        out.write("     <tr>");
        out.write("         <td></td>");
        out.write("        <td><input type=\"submit\" name=\"value\" value=\"0\"></td>");
        out.write("        <td><input type=\"submit\" name=\"dot\" value=\".\"></td>");
        out.write("        <td><input type=\"submit\" name=\"operator\" value=\"=\"></td>");
        out.write("    </tr>");
        out.write(" </table>");
        out.write("</form>");
        out.write(" </div>");
        out.write("</body>");
        out.write("</html>");

    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 쿠키 온 거 읽기
        Cookie[] cookiesArr = request.getCookies();

        // 사용자가 입력한 내용
        String value = request.getParameter("value");
        String operator = request.getParameter("operator");
        String dot = request.getParameter("dot");

        // 기존에 만들었던 exp
        String exp = "";
        if(cookiesArr != null) {
            for (Cookie c : cookiesArr)
                if (c.getName().equals("exp")) {
                    exp = c.getValue();
                    break;
                }
        }

        if(operator != null && operator.equals("=")){

            // 자바스크립트로 계산해보기
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            try {
                exp = String.valueOf(engine.eval(exp));
            } catch (ScriptException e) {
                e.printStackTrace();
            }
        }
/*
        else if(operator != null && operator.equals("C")) {

            exp=""; // 쿠키가 완전히 사라진 것이 아니고 그냥 빈 문자열이 된다.
        }
*/
        else { // 기존에 만든 exp에 사용자가 입력한 cookie 가져와서 덧붙힌다.

            exp += (value == null) ? "" : value;
            exp += (operator == null) ? "" : operator;
            exp += (dot == null) ? "" : dot;
        }

        // 그것을 쿠키로 저장
        Cookie expCookie = new Cookie("exp", exp);

        if(operator != null && operator.equals("C"))
            expCookie.setMaxAge(0); // 쿠키가 브라우저로 가서 바로 사라짐.

        expCookie.setPath("/calculator");
        // 범위 축소시켜서 이 url에서만 쿠키사용 가능
        response.addCookie(expCookie);

        response.sendRedirect("calculator");
        // 자기가 자기를 요청할 때에는 ./이고 Redirect는 get 요청을 의미
        // 서버측에서 이 페이지를 돌려준다.


    }

}

