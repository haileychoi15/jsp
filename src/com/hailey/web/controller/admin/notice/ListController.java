package com.hailey.web.controller.admin.notice;

import com.hailey.web.entity.NoticeView;
import com.hailey.web.service.NoticeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet {

    // 404 : url이 없어서 발생하는 오류
    // 405 : url은 존재하나, 그 안에 받을 수 없는 method가 없을 때 발생하는 오류
    // 403 : url, method 있지만 권한이 없을 때 (보안오류)

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String[] openBoardnos = request.getParameterValues("open-boardno");
        String[] delBoardnos = request.getParameterValues("del-boardno");
        String cmd = request.getParameter("cmd"); // name = "cmd" 로 보낸 값 받아온다.
        String boardno_ = request.getParameter("boardnos"); // 모든 boardno값을 가져온다.
        String[] boardnos = boardno_.trim().split(" "); // 배열 형태로 만든다.

        NoticeService service = new NoticeService();

        switch (cmd){

            case "일괄공개" :
                // 콘솔에 찍어 보기
                for(String openBoardno : openBoardnos)
                    System.out.printf("open boarndo : %s\n", openBoardno);

                List<String> oBoardnos = Arrays.asList(openBoardnos);
                // 1,2,3,4,5,6,7,8,9,10 - 3,5,8 => 1,2,4,6,7,8,9,10

                // Arrays.asList(boardnos).removeAll(oBoardnos);
                // 그러나 asList 로 만들면 크기가 정적이라서 데이터를 더하거나 뺄 수 없다.
                // asList로 만든 것을 새로운 List에 담는다.
                List<String> cBoardnos = new ArrayList(Arrays.asList(boardnos));
                cBoardnos.removeAll(oBoardnos);

                System.out.println(Arrays.asList(boardnos));
                System.out.println(oBoardnos);
                System.out.println(cBoardnos);

                // Transaction : 내가 생각하는 업무수행(논리적인) 단위
                // 두 개의 함수가 하나의 명령처럼 실행될 수 있게 하는 작업을 Controller가 담당해야 함.
                service.pubNoticeAll(oBoardnos, cBoardnos);
                // service.closeNoticeList(clsBoardnos);

                break;

            case "일괄삭제" :

                // 콘솔에 찍어 보기
                for(String delBoardno : delBoardnos)
                    System.out.printf("del boarndo : %s\n", delBoardno);

                int[] boardnos1 = new int[delBoardnos.length]; // String[]을 int[]로 바꾸기
                for(int i=0; i<delBoardnos.length; i++)
                    boardnos1[i] = Integer.parseInt(delBoardnos[i]);

                int result = service.deleteNoticeAll(boardnos1);
                // jdbc library는 자동 커밋


                break;
        }

        // post 일처리를 하고 난 뒤 다시 수정반영된 페이지를 보여주도록 get 요청한다.
        // get 요청 호출

        response.sendRedirect("list"); // 서버에서 서버에 있는 다른 페이지를 요청하듯이, list 페이지 재요청


    }

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
        List<NoticeView> list = service.getNoticeList(field, query, page);
        // 데이터베이스에서 레코드 개수 알아오기
        int recordCount = service.getNoticeCount(field, query); // 검색할 경우 또 페이지 개수가 달라지므로

        // 글별 댓글 개수알아오기
        HashMap<Integer, Integer> commentMap = service.getCommentCountMap();

        request.setAttribute("list", list);
        request.setAttribute("recordCount", recordCount);
        request.setAttribute("commentMap", commentMap);


        //forward
        request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/list.jsp").forward(request, response);
        // view단인 /WEB-INF/view/notice/list.jsp 에서 톰캣 실행하지 않도록 따로 뺐다.


    }
}


