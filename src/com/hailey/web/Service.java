package com.hailey.web;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/service")
public class Service extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get으로 왔는지 post로 왔는지에 따라 다르게 처리하기
        if(request.getMethod().equals("GET")){ // 무조건 대문자

            System.out.println("get 요청이 왔습니다.");
        }
        else if(request.getMethod().equals("POST")) {

            System.out.println("post 요청이 왔습니다.");
        }

        super.service(request, response);
        // 요청에 따라 doGet 또는 doPost method 호출시킨다.

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){

        System.out.println("doPost method가 호출되었습니다.");
    }
}

