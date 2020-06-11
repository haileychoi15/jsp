
<%--
    MVC2 model : Dispactcher를 집중화 한 후의 모델
    서블릿은  Dispactcher 하나만 만들고 Controller에서 일반 클래스인 POJO class를 만든다.
    사용자 요청이 들어오면 디스패처가 적절한 컨틀롤러 찾는다.
    컨틀롤러에서는 로직에 해당되는 뷰를 호출할 수 있도록 url-mapping을 통해서 디스패처에게 알려준다.

    사용자 --- 톰캣 <---> Dispatcher (디스패처 서블릿) ---> Controller (POJO)
                            |                  \
                            |                    <url-mapping>
                        View (jsp)
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>MVC2</title>
</head>
<body>
    <%-- 출력 데이터 Model, 합쳐서 MVC--%>
    <%=request.getAttribute("result")%>입니다.<br>
    또는 <br>
    ${result} EL(Expression Language)입니다. <br>

    이름1 : ${names[0]} <br>
    이름2 : ${names[1]} <br>

    id : ${map.id} <br>
    title : ${map.title}

<%-- 실행할때 MVC2.java 에서 해야한다. 여기 나오는 데이터가 Controller에서 만들어졌기 때문. 여기 내용은 껍데기일 뿐이다.


    ***** 비교 *****

                [Controller]                                        [View]

    request.setAttribute("cnt",30);                   ==>  request.getAttribute("cnt");
                                                           또는 EL(Expression Language) : ${cnt}

    List list = new ArrayList(){"1", "title" ... };
    request.setAttribute("list",list);                ==> (List)request.getAttribute("list").get(0)
                                                          // 이 함수는 담긴게 무엇인던 Object형식으로 뽑아내기 때문에 앞에(List) 해줘야한다.
                                                           또는 EL(Expression Language) : ${list[0]}

    Map map = new HashMap();
    request.setAttribute("map",map);                  ==> (Map)request.getAttribute("map").get("title")
                                                           또는 EL(Expression Language) : ${map.title}
--%>



</body>
</html>
