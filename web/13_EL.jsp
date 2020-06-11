<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 서버상의 저장소 page, request, session, application 객체는 EL로 뽑아내서 쓸 수 있다.

    page : jasper가 만든 객체 중에서 pageContext, page 내에서 사용하는 서블릿객체들(request, response, session, application) 등을
    모아 놓은 것. page단위로 저장해서 쓸 수 있다.

    만약!! 각각의 저장소에 같은 이름의 변수가 있다면 이때 우선 순위는 page, request, session, application 이다.
    찾아올 저장소를 지정하고 싶다면 Scope를 쓴다. ${sessionScope.cnt}

--%>
<%
    pageContext.setAttribute("result", "page result hello"); // page 객체, pageContext는 jsp-api.jar에서 지원
%>
<html>
<head>
    <title>MVC2</title>
</head>
<body>

    <h2> *** EL의 데이터 저장소 *** </h2>

    <%=request.getAttribute("result")%>입니다.<br>
    또는 <br>
    ${requestScope.result} EL(Expression Language)입니다. <br>
    <%-- result를 request 저장소에서 찾는다. --%>

    이름1 : ${names[0]} <br>
    이름2 : ${names[1]} <br>

    id : ${map.id} <br>
    title : ${map.title} <br>
    result : ${result} <br><br> <%-- page 저장소것이 나온다. --%>

    param.num : ${param.num}<br> <%-- parameter로 넘어온 값 --%>
    header.host : ${header.host}<br>
    header.accept : ${header.accept}<br><br>

    <%-- <%=pageContext.getRequest().getMethod()%> 또는 --%>
    pageContext.request.method : ${pageContext.request.method} <br><br>
    <%-- EL을 쓸때는 매소드라는 느낌을 주지 않기 위해서 getter를 뺀다. GET --%>

    <h2> *** EL 연산자 *** </h2>

    ${param.num ge 3}, ge : 크거나 같다 <br>
    ${param.num == null || param.num == ''} 와 ${empty param.num}이 같다. <br>
    ${not empty param.num} 널이거나 빈 문자열이 아닐때 false <br>
    ${empty param.num?'값이 비어있습니다.': param.num}
    ${param.num / 2} param.num이 3일 때 문자열이니까 계산이 될까 아니면 정수/정수는 정수가 나올까 아니면 1.5가 나올까 답은 1.5 !!


</body>
</html>
