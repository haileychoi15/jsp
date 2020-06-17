<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/05/20
  Time: 11:12 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int num = 0;
    String num_ = request.getParameter("num");

    if(num_ != null && !num_.equals("")){
        num = Integer.parseInt(num_);
    }
%>

<html>
<head>
    <title>spagetti code</title>
</head>
<body>
    <% if(num%2 != 0){ %>
            홀수입니다.
    <% } else {%>
            짝수입니다.
    <% } %>
</body>
</html>
