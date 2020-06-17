package com.hailey.web.controller.notice;

import com.hailey.web.entity.Member;
import com.hailey.web.entity.Notice;
import com.hailey.web.dao.NoticeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

// from 에서 enctype="multipart/form-data으로 인코딩 하니까 값이 잘 전달되지 않는다.
@MultipartConfig(
        fileSizeThreshold = 1024*1024, // 저장공간
        maxFileSize = 1024*1024*50, // 하나의 파일 사이즈
        maxRequestSize = 1024*1024*50*5 // 전체 요청에 대한 사이즈
) // 멀티파트로 인코딩해서 보낸 데이터는 서버쪽에서 멀티파트로 받아야한다. 이부분은 보내는 부분임

@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet {


    // 글쓰기 버튼 누르면 글쓰기 페이지가 나오는 get 요청, 등록 누르면 post 요청
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Member loginUser = (Member) session.getAttribute("loginUser");

        if(loginUser != null) {
            request.getRequestDispatcher("/WEB-INF/view/admin/board/notice/reg.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        String isOpen = request.getParameter("open");

        // 다중 파일 업로드
        Collection<Part> parts = request.getParts();
        StringBuilder builder = new StringBuilder(); // db에 파일 이름 넣을 수 있게
        for(Part p : parts) {
            if (!p.getName().equals("file")) continue;
            if (p.getSize() == 0) continue; // name이 file이기는 한데 비어있을 때
            Part filePart = p;

            // 단일 파일 업로드
            // Part filePart = request.getPart("file"); // Part는 바이너리라는 컨텐츠를 넘겨준다.
            String fileName = filePart.getSubmittedFileName();

            builder.append(fileName);
            builder.append(",");

            InputStream fis = filePart.getInputStream(); // 스트림을 통해 바이너리받는다. => 스트림을 통해서 받은 파일을 저장할 경로 필요

            String realPath = request.getServletContext().getRealPath("/upload"); // 물리 경로 지정
            System.out.println(realPath);

            // 파일 저장소 만들기
            File path = new File(realPath);
            if(!path.exists())
                path.mkdirs(); // 부모꺼까지 확인하고 만든다.

            String filePath = realPath + File.pathSeparator + fileName; // 파일경로 + 파일명
            FileOutputStream fos = new FileOutputStream(filePath); // 출력 스트림

            // int b = fis.read(); // 데이터를 바이트단위로 반환, 다 도달하면 정수 -1 반환
            byte[] buf = new byte[1024]; // 1키로바이트
            int size = 0;
            while ((size = fis.read(buf)) != -1)
                fos.write(buf, 0, size);

            fos.close();
            fis.close();

        }

        builder.delete(builder.length() - 1, builder.length()); // 마지막 쉼표 삭제하기

        boolean pub = false;

        if(isOpen != null)
            pub = true;

        Notice notice = new Notice();
        notice.setSubject(subject);
        notice.setContents(content);
        notice.setPub(pub);
        notice.setUserid("zzia");
        notice.setFiles(builder.toString());

        NoticeDAO service = new NoticeDAO();
        int result = service.insertNotice(notice);

        //insert 하고 나서 목록페이지로 이동
        response.sendRedirect("list"); // controller를 요청하게 되는데, 경로 지정하지 않으면 현재 url에 reg대신 list가 들어간 url요청하게 된다.


       /*
        // 입력할 때는 UTF-8 필터 설정해놨는데, 출력할 때는 한글이 깨진다.
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.printf("subject : %s<br>", subject);
        out.printf("content : %s<br>", content);
        out.printf("isOpen : %s<br>", isOpen);   */

    }
}
