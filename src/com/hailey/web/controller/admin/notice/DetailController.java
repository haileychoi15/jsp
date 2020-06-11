package com.hailey.web.controller.admin.notice;

import com.hailey.web.entity.Notice;
import com.hailey.web.service.NoticeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/board/notice/detail")
public class DetailController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int boardno = Integer.parseInt(request.getParameter("id"));

        NoticeService service = new NoticeService();
        Notice notice = service.getNotice(boardno);
        request.setAttribute("n", notice);

        // 서블릿에서 서블릿으로 데이터를 전이하는 방법은 두가지 redirect, forward
        // forward 사용하려면 dispatcher를 이용할 수 있고 이는 request를 통해 얻을 수 있다.
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/detail.jsp"); // home 디렉토리기준 home / 은 web
        dispatcher.forward(request, response); // request 저장소 만들었고 이 곳에 위에서 만들었던 model을 담아준다. forward 하기 전에 !!
        // view단인 /WEB-INF/view/notice/detail.jsp 에서 톰캣실행하지 않도록 따로 뺐다.

    }
}
