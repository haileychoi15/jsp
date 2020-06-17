<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/05/20
  Time: 10:34 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%--
contentType : 브라우저가 인식하게 하는 헤더, pageEncoding : 브라우저에 출력 할 때 UTF-8 쓰겠다.
내가 만든 코드블럭 외에 제스퍼가 만든 코드블럭이 서블릿에 있다. page, session, out, application, response, request 등 내장 객체
--%>

<%
    String cnt_ = request.getParameter("cnt");

    int cnt = 10;
    if(cnt_ != null && !cnt_.equals("")){
        cnt = Integer.parseInt(cnt_);
    }
%>

<html>
<head>
    <title>Title</title>
</head>
<body>

   <% for(int i=0; i<cnt; i++){ %>
        hello jsp servlet!! <br>
   <%} %>


</body>
</html>
