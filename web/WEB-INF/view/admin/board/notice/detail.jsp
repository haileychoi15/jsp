<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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



                        <li>
                            <form action="/logout" method="post">
                                <input type="hidden" name="" value="" />
                                <input type="submit" value="로그아웃"
                                       style="border:none;background: none;vertical-align: middle;font-size: 10px;color:#979797;font-weight: bold;" />

                            </form>
                        </li>

                        <li><a href="/member/agree">회원가입</a></li>
                    </ul>
                </nav>

                <nav id="member-menu" class="linear-layout">
                    <h1 class="hidden">고객메뉴</h1>
                    <ul class="linear-layout">
                        <li><a href="/member/myInfo"><img src="/images/txt-mypage.png" alt="마이페이지" /></a></li>
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




        <main>
            <h2 class="main title">공지사항</h2>

            <div class="breadcrumb">
                <h3 class="hidden">breadlet</h3>
                <ul>
                    <li>home</li>
                    <li>고객센터</li>
                    <li>공지사항</li>
                </ul>
            </div>

            <div class="margin-top first">
                <h3 class="hidden">공지사항 내용</h3>
                <table class="table">
                    <tbody>
                    <tr>
                        <th>제목</th>
                        <td class="text-align-left text-indent text-strong text-orange" colspan="3">${n.subject}</td>
                    </tr>
                    <tr>
                        <th>작성일</th>
                        <td class="text-align-left text-indent" colspan="3">
                            <fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${n.writeday}"></fmt:formatDate>
                        </td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td>${n.userid}</td>
                        <th>조회수</th>
                        <td>${n.viewcount}</td>
                    </tr>
                    <tr>
                        <th>첨부파일</th>
                        <td colspan="3">
                            <c:forTokens var="fileName" items="${n.files}" delims="," varStatus="st">
                                <%--items 에 들어가는 값은 문자열이고 이것을 delims , 기준으로 자르고 fileName 변수에 담는다. --%>

                                <c:set var="style" value=""></c:set>
                                <c:if test="${fn:endsWith(fileName, 'zip')}">
                                    <c:set var="style" value="font-weight: bold; color: red"></c:set>
                                </c:if>
                                <a download href="../../../upload/${fileName}" style="${style}">${fn:toUpperCase(fileName)}</a>
                                <%-- functions tag는 태그형태로 쓰지 않고 EL안에서 함수 호출하는 패키지처럼 사용한다. --%>

                                <c:if test="${!st.last}"> <%-- st속성값이 마지막이 아니면 --%>
                                    /
                                </c:if>
                            </c:forTokens>
                        </td>
                    </tr>
                    <tr class="content">
                        <td colspan="4">${n.contents}</td>
                    </tr>
                    </tbody>
                </table>
            </div>

            <div class="margin-top text-align-center">
                <a class="btn-text btn-cancel" href="list">목록</a>
                <a class="btn-text btn-default" href="edit">수정</a>
                <a class="btn-text btn-default" href="del">삭제</a>
            </div>

            <div class="margin-top">
                <table class="table border-top-default">
                    <tbody>
                    <tr>
                        <th>다음글</th>
                        <td colspan="3" class="text-align-left text-indent">다음글이 없습니다.</td>
                    </tr>
                    <tr>
                        <th>이전글</th>
                        <td colspan="3" class="text-align-left text-indent"><a class="text-blue text-strong"
                                                                               href="">스프링 DI 예제 코드</a></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </main>

    </div>
</div>

<!-- ------------------- <footer> --------------------------------------- -->

<jsp:include page="../../../../../include/footer.html" flush="false"></jsp:include>

</body>

</html>