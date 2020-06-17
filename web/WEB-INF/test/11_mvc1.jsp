<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/05/20
  Time: 11:12 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%  /* 입력과 제어 Controller */
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
%>
<%---------------------------------------------------------------------------------------------%>
<%-- 출력 코드가 있는 View --%>
<html>
<head>
    <title>MVC1</title>
</head>
<body>
    <%=result%>입니다. <%-- 출력 데이터 Model, 합쳐서 MVC--%>

</body>
</html>
