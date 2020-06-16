<%@ page import="java.util.*" %>
<%@ page import="com.hailey.web.entity.Notice" %>
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
    
        #visual .content-container{	
            height:inherit;
            display:flex; 
            align-items: center;
            
            background: url("../../../images/customer/visual.png") no-repeat center;
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
							<c:if test="${sessionScope.loginUser.userid == null}">
								<li><a href="/member/login">로그인</a></li>
								<li><a href="/member/agree">회원가입</a></li>
							</c:if>
							<c:if test="${sessionScope.loginUser.userid != null}">
								<li><a href="/member/logoutAction">로그아웃</a></li>
							</c:if>
                        </ul>
                    </nav>

                    <nav id="member-menu" class="linear-layout">
                        <h1 class="hidden">고객메뉴</h1>
                        <ul class="linear-layout">
                            <li><a href="/member/home"><img src="/images/txt-mypage.png" alt="마이페이지" /></a></li>
                            <li><a href="/WEB-INF/view/notice/list.html"><img src="/images/txt-customer.png" alt="고객센터" /></a></li>
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
				<h1>고객센터</h1>

				<nav class="menu text-menu first margin-top">
					<h1>고객센터메뉴</h1>
					<ul>
						<li><a class="current"  href="/customer/notice">공지사항</a></li>
						<li><a class=""  href="/customer/faq">자주하는 질문</a></li>
						<li><a class="" href="/customer/question">수강문의</a></li>
						<li><a class="" href="/customer/event">이벤트</a></li>
						
					</ul>
				</nav>


	<nav class="menu">
		<h1>협력업체</h1>
		<ul>
			<li><a target="_blank" href="http://www.notepubs.com"><img src="/images/notepubs.png" alt="노트펍스" /></a></li>
			<li><a target="_blank" href="http://www.namoolab.com"><img src="/images/namoolab.png" alt="나무랩연구소" /></a></li>
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
							<option ${(param.f == "subject")?"selected":""} value="subject">제목</option>
							<option ${(param.f == "fk_userid")?"selected":""} value="fk_userid">작성자</option>
						</select>
						<label class="hidden">검색어</label>
						<input type="text" name="q" value="${param.q}"/>
						<input class="btn btn-search" type="submit" value="검색" />
					</fieldset>
				</form>
			</div>
			
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
						</tr>
					</thead>
					<tbody>

					<%--
					<% List<Notice> list = (List<Notice>)request.getAttribute("list");
						for(Notice n : list) {

						pageContext.setAttribute("n", n);
					%>
					VIEW단에 보이는 이 코드블럭을 개선하기 위해서 tag library 사용하겠다. --%>

					<%--
					 **** tag library ****
					 core : 제어의 행위 담당
					 formating : 출력시 날짜,숫자 단위 포맷팅
					 functions : 저장소에서 데이터를 꺼내 사용할 때 문자열 조작할 수 있는 함수들을 묶어놓음
					 sql
					 xml --%>

					<c:forEach var="n" items="${list}" begin="0" end="10" varStatus="st">
						<%-- list에 담긴 것을 items에 담는다. 반복될 때 마다 뽑아서 "n"에 담는다. 이 것을 pageContent에 담는 것을 forEach가 해준다.--%>

					<tr>
						<td><%--${st.index+1}--%>${n.boardno}</td>
						<td class="title indent text-align-left"><a href="detail?id=${n.boardno}">${n.subject}</a>
                            <c:if test="${n.cmtCount != 0}">
                                <span>[<span style="color: orange; font-weight: bold">${n.cmtCount}</span>]</span>
                            </c:if>
<%--                                해쉬맵을 통해서 댓글 개수 가져오기
                                    <c:forEach var="map" items="${commentMap}">
                                    <c:if test="${n.boardno == map.key}">
                                         <span><c:out value="[${map.value}]"/></span>
                                    </c:if>
                                </c:forEach>--%>

                        </td>
						<td>${n.userid}</td>
						<td><fmt:formatDate pattern="yyyy-MM-dd" value="${n.writeday}"/></td>
						<td>${n.viewcount}</td>
					</tr>

					</c:forEach>
					<%--<% } %>--%>

					</tbody>
				</table>
			</div>

            <c:set var="page" value="${(param.p == null)?1:param.p}"></c:set>
            <c:set var="startNum" value="${page-(page-1) % 5}"></c:set>
            <c:set var="lastNum" value="${fn:substringBefore(Math.ceil(recordCount/10),'.')}"></c:set> <%-- . 이 발견되면 . 앞에 것만 출력--%>

			<div class="indexer margin-top align-right">
				<h3 class="hidden">현재 페이지</h3>
				<div><span class="text-orange text-strong">${(empty param.p)?1:param.p}</span> / ${lastNum} pages</div>
			</div> <%-- empty는 null과 빈문자열 포함한다. --%>

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



        <footer id="footer">
            <div class="content-container">
                <h2 id="footer-logo"><img src="/images/logo-footer.png" alt="회사정보"></h2>
    
                <div id="company-info">
                    <dl>
                        <dt>주소:</dt>
                        <dd>서울특별시 </dd>
                        <dt>관리자메일:</dt>
                        <dd>admin@newlecture.com</dd>
                    </dl>
                    <dl>
                        <dt>사업자 등록번호:</dt>
                        <dd>111-11-11111</dd>
                        <dt>통신 판매업:</dt>
                        <dd>신고제 1111 호</dd>
                    </dl>
                    <dl>
                        <dt>상호:</dt>
                        <dd>뉴렉처</dd>
                        <dt>대표:</dt>
                        <dd>홍길동</dd>
                        <dt>전화번호:</dt>
                        <dd>111-1111-1111</dd>
                    </dl>
                    <div id="copyright" class="margin-top">Copyright ⓒ newlecture.com 2012-2014 All Right Reserved.
                        Contact admin@newlecture.com for more information</div>
                </div>
            </div>
        </footer>
    </body>
    
    </html>
