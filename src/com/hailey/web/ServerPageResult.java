package com.hailey.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/result") //calcpage
public class ServerPageResult extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
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
        out.write("    <form action=\"page\" method=\"Get\">");
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
}

