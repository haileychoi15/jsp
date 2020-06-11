package com.hailey.web.controller.notice;

import com.hailey.web.entity.NoticeView;
import com.hailey.web.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet("/notice/list")
public class ListController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // list?f=title&q=abc 사용자가 타이핑한거 받아온다.
        String field_ = request.getParameter("f");
        String query_ = request.getParameter("q");
        String page_ = request.getParameter("p"); // 받는 값은 무조건 String

        String field = "subject";
        if(field_ != null && !field_.equals("")) // 홀더가 전달되지 않으면 null이 아니라 빈문자열이 들어온다.
            field = field_;

        String query = "";
        if(query_ != null && !query_.equals(""))
            query = query_;

        int page = 1;
        if(page_ != null && !page_.equals(""))
            page = Integer.parseInt(page_);

        // NoticeDetailController에서는 notice객체가 하나만 필요했지만 여기서는 항목이 여러개이기 때문에 여러 개(list) 필요

        NoticeService service = new NoticeService();
        List<NoticeView> list = service.getNoticePubList(field, query, page);
        // 데이터베이스에서 레코드 개수 알아오기
        int recordCount = service.getNoticeCount(field, query); // 검색할 경우 또 페이지 개수가 달라지므로

        // 글별 댓글 개수알아오기
        HashMap<Integer, Integer> commentMap = service.getCommentCountMap();

        request.setAttribute("list", list);
        request.setAttribute("recordCount", recordCount);
        request.setAttribute("commentMap", commentMap);


        //forward
        request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
        // view단인 /WEB-INF/view/notice/list.jsp 에서 톰캣 실행하지 않도록 따로 뺐다.


    }
}

