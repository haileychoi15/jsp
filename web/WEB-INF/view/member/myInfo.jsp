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
			<h2 class="main title">마이페이지</h2>
			
			<div class="breadcrumb">
				<h3 class="hidden">경로</h3>
				<ul>
					<li>home</li>
					<li>마이페이지</li>
				</ul>
			</div>
			
<%--			<div class="search-form margin-top first align-right">
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
			</div>--%>
			
			<div class="notice margin-top">
				<h3 class="hidden">내 정보 조회</h3>
				<table class="table">
					<thead>
						<tr>
							<th class="w60">아이디</th>
							<th class="w60">이름</th>
							<th class="w60">성별</th>
							<th class="w100">모바일</th>
							<th class="w100">이메일</th>
							<th class="w60">생년월일</th>
							<th class="w60">가입일자</th>
							<th class="w40">포인트</th>
						</tr>
					</thead>
					<tbody>

					<tr>
						<td class="title indent">${sessionScope.loginUser.userid}</td>
						<td class="title indent">${sessionScope.loginUser.name}</td>
						<td class="title indent">${sessionScope.loginUser.gender}</td>
						<td class="title indent">${sessionScope.loginUser.mobile}</td>
						<c:if test="${empty sessionScope.loginUser.email}">
							<td class="title indent">미등록</td>
						</c:if>
						<c:if test="${not empty sessionScope.loginUser.email}">
							<td class="title indent">${sessionScope.loginUser.email}</td>
						</c:if>
						<c:if test="${empty sessionScope.loginUser.birthday}">
							<td class="title indent">미등록</td>
						</c:if>
						<c:if test="${not empty sessionScope.loginUser.birthday}">
							<td class="title indent">${sessionScope.loginUser.birthday}</td>
						</c:if>
						<td class="title indent">${sessionScope.loginUser.registerday}</td>
						<td class="title indent">${sessionScope.loginUser.point}</td>
					</tr>

					</tbody>
				</table>
			</div>

		</main>
		
			
		</div>
	</div>

    <!-- ------------------- <footer> --------------------------------------- -->

<jsp:include page="../../../include/footer.html" flush="false"></jsp:include>

    </body>
    
    </html>
