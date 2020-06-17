<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
    <title>코딩 전문가를 만들기 위한 온라인 강의 시스템</title>
    <meta charset="UTF-8">
    <title>공지사항목록</title>

    <link href="/css/customer/layout.css" type="text/css" rel="stylesheet" />
    <style>
        #visual .content-container {
            height: inherit;
            display: flex;
            align-items: center;

            background: url("/images/mypage/visual.png") no-repeat center;
        }
    </style>
</head>

<body>
<!-- header 부분 -->

<header id="header">

    <div class="content-container">
        <!-- ---------------------------<header>--------------------------------------- -->

        <h1 id="logo">
            <a href="/WEB-INF/view/index.html">
                <img src="/images/logo.png" alt="뉴렉처 온라인" />

            </a>
        </h1>

        <section>
            <h1 class="hidden">헤더</h1>

            <nav id="main-menu">
                <h1>메인메뉴</h1>
                <ul>
                    <li><a href="/guide">학습가이드</a></li>

                    <li><a href="/course">강좌선택</a></li>
                    <li><a href="/answeris/index">AnswerIs</a></li>
                </ul>
            </nav>

            <div class="sub-menu">

                <section id="search-form">
                    <h1>강좌검색 폼</h1>
                    <form action="/course">
                        <fieldset>
                            <legend>과정검색필드</legend>
                            <label>과정검색</label>
                            <input type="text" name="q" value="" />
                            <input type="submit" value="검색" />
                        </fieldset>
                    </form>
                </section>

                <nav id="acount-menu">
                    <h1 class="hidden">회원메뉴</h1>
                    <ul>
                        <li><a href="/WEB-INF/view/index.html">HOME</a></li>
                        <li><a href="/member/login.html">로그인</a></li>
                        <li><a href="/member/agree.html">회원가입</a></li>
                    </ul>
                </nav>

                <nav id="member-menu" class="linear-layout">
                    <h1 class="hidden">고객메뉴</h1>
                    <ul class="linear-layout">
                        <li><a href="/member/home"><img src="/images/txt-mypage.png" alt="마이페이지" /></a></li>
                        <li><a href="/notice/list.html"><img src="/images/txt-customer.png" alt="고객센터" /></a></li>
                    </ul>
                </nav>

            </div>
        </section>

    </div>

</header>

<!-- --------------------------- <visual> --------------------------------------- -->
<!-- visual 부분 -->

<div id="visual">
    <div class="content-container"></div>
</div>
<!-- --------------------------- <body> --------------------------------------- -->
<div id="body">
    <div class="content-container clearfix">

        <!-- --------------------------- aside --------------------------------------- -->
        <!-- aside 부분 -->


        <aside class="aside">
            <h1>ADMIN PAGE</h1>

            <nav class="menu text-menu first margin-top">
                <h1>마이페이지</h1>
                <ul>
                    <li><a href="/WEB-INF/view/admin/index.html">관리자홈</a></li>
                    <li><a href="/teacher/index.html">선생님페이지</a></li>
                    <li><a href="/student/index.html">수강생페이지</a></li>
                </ul>
            </nav>

            <nav class="menu text-menu">
                <h1>알림관리</h1>
                <ul>
                    <li><a href="/WEB-INF/view/admin/board/notice/list.html">공지사항</a></li>
                </ul>
            </nav>

        </aside>
        <!-- --------------------------- main --------------------------------------- -->



        <main class="main">
            <h2 class="main title">공지사항</h2>

            <div class="breadcrumb">
                <h3 class="hidden">경로</h3>
                <ul>
                    <li>home</li>
                    <li>고객센터</li>
                    <li>공지사항</li>
                </ul>
            </div>

            <div class="search-form margin-top first align-right">
                <h3 class="hidden">공지사항 검색폼</h3>
                <form class="table-form">
                    <fieldset>
                        <legend class="hidden">공지사항 검색 필드</legend>
                        <label class="hidden">검색분류</label>
                        <select name="f">
                            <option value="title">제목</option>
                            <option value="writerId">작성자</option>
                        </select>
                        <label class="hidden">검색어</label>
                        <input type="text" name="q" value="" />
                        <input class="btn btn-search" type="submit" value="검색" />
                    </fieldset>
                </form>
            </div>


        <form action="list" method="post">
            <div class="notice margin-top">
                <h3 class="hidden">공지사항 목록</h3>
                <table class="table">
                    <thead>
                    <tr>
                        <th class="w60">번호</th>
                        <th class="expand">제목</th>
                        <th class="w100">작성자</th>
                        <th class="w100">작성일</th>
                        <th class="w60">조회수</th>
                        <th class="w40">공개</th>
                        <th class="w40">삭제</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach var="n" items="${list}" begin="0" end="10" varStatus="st">
                        <%-- list에 담긴 것을 items에 담는다. 반복될 때 마다 뽑아서 "n"에 담는다. 이 것을 pageContent에 담는 것을 forEach가 해준다.--%>

                        <c:set var="open" value=""></c:set>
                        <c:if test="${n.pub}"><%-- true라면 --%>
                            <c:set var="open" value="checked"></c:set>
                        </c:if>


                        <tr>
                            <td><%--${st.index+1}--%>${n.boardno}</td>
                            <td class="title indent text-align-left"><a href="detail?id=${n.boardno}">${n.subject}</a>
                                <c:if test="${n.cmtCount != 0}">
                                    <span>[<span style="color: orange; font-weight: bold">${n.cmtCount}</span>]</span>
                                </c:if>
                            </td>
                            <td>${n.userid}</td>
                            <td><fmt:formatDate pattern="yyyy-MM-dd" value="${n.writeday}"/></td>
                            <td>${n.viewcount}</td>

                            <td><input type="checkbox" name="open-boardno" ${open} value="${n.boardno}"></td>
                            <td><input type="checkbox" name="del-boardno" value="${n.boardno}"></td>
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>
            </div>

            <c:set var="page" value="${(param.p == null)?1:param.p}"></c:set>
            <c:set var="startNum" value="${page-(page-1) % 5}"></c:set>
            <c:set var="lastNum" value="${fn:substringBefore(Math.ceil(recordCount/10),'.')}"></c:set>


            <div class="indexer margin-top align-right">
                <h3 class="hidden">현재 페이지</h3>
                <div><span class="text-orange text-strong">${(empty param.p)?1:param.p}</span> / ${lastNum} pages</div>
            </div>

            <div class="text-align-right margin-top">

                <%-- 일괄공개 버튼 누르면 밑에 안보이게 쌓아둔 boardnos 값과 함께 전달된다. --%>
                <c:set var="boardnos" value=""/>
                <c:forEach var="n" items="${list}">
                    <c:set var="boardnos" value="${boardnos} ${n.boardno}"/>
                </c:forEach>
                <input type="hidden" name="boardnos" value="${boardnos}">
                <input type="submit" class="btn-text btn-default" name="cmd" value="일괄공개">
                <input type="submit" class="btn-text btn-default" name="cmd" value="일괄삭제">
                <%-- submit을 누르면 어쩔때는 "일괄공개"가 가고, 어쩔 땐 "일괄삭제"가 간다. name을 똑같이 주고 받은 다음에 서버쪽에서 누가 왔나 확인하면 됨 --%>
                <a class="btn-text btn-default" href="reg">글쓰기</a>
            </div>
        </form>


            <div class="margin-top align-center pager">

                <div>
                    <c:if test="${startNum-1 >= 1}">
                        <a href="?p=${startNum-1}&t=&q=" class="btn btn-prev" >이전</a>
                    </c:if>
                    <c:if test="${startNum-1 < 1}">
                        <span class="btn btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</span>
                    </c:if>
                </div>

                <ul class="-list- center">
                    <c:forEach var="i" begin="0" end="4">
                        <c:if test="${(startNum+i) <= lastNum}">
                            <li><a class="-text- ${(page == (startNum+i))?'orange':''} bold" href="?p=${startNum+i}&f=${param.f}&q=${param.q}" >${startNum+i}</a></li>
                        </c:if> <%-- if문이 forEach문 안에 존재 --%>
                    </c:forEach>
                </ul>

                <div>
                    <c:if test="${startNum+4 < lastNum}">
                        <a href="?p=${startNum+5}&t=&q=" class="btn btn-next" >다음</a>
                    </c:if>
                    <c:if test="${startNum+4 >= lastNum}">
                        <span class="btn btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</span>
                    </c:if>
                </div>

            </div>
        </main>


    </div>
</div>

<!-- ------------------- <footer> --------------------------------------- -->

<jsp:include page="../../../../../include/footer.html" flush="false"></jsp:include>

</body>

</html>