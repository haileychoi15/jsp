package com.hailey.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.util.*;

import java.io.IOException;

@WebServlet("/mvc2")
public class MVC2 extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int num = 0;
        String num_ = request.getParameter("num");

        if(num_ != null && !num_.equals("")){
            num = Integer.parseInt(num_);
        }

        String result;
        if(num%2 != 0)
            result = "홀수";
        else
            result = "짝수";

        String[] names = {"imdud", "sunny", "zia"};

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",1);
        map.put("title", "EL 편해요.");

        request.setAttribute("result", result);
        request.setAttribute("names", names);
        request.setAttribute("map", map);
        // 선언된 변수는 request에 저장된다. 이름은 다르게 해도 된다.

        // 위의 내용을 view 단으로 전이 하는 방법
        // forward : 현재 작업한 내용을 이어갈 수 있도록 공유
        // ** 비교 ** redirect : 현재 작업한 내용과 무관하게 새로운 요청

        // RequestDispatcher dispatcher = request.getRequestDispatcher("12_mvc2.jsp"); // url상 같은 디렉토리에 있기 때문에 /를 쓰지않음.
        RequestDispatcher dispatcher2 = request.getRequestDispatcher("13_EL.jsp");
        // dispatcher.forward(request, response);
        dispatcher2.forward(request, response);
        // 현재 작업한 내용이 request, response에 담기고 "12_mvc2.jsp"으로 공유된다.
        // "12_mvc2.jsp"는 jsp로 되어있지만 서블릿이다.


    }

}
