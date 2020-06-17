<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
                        <li><a href="/index">HOME</a></li>
                        <li><a href="/member/login">로그인</a></li>
                        <li><a href="/member/agree">회원가입</a></li>
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


<script src="/resource/js/header.js"></script>
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
            <h1>회원가입</h1>

            <nav class="menu text-menu first margin-top">
                <h1>회원메뉴</h1>
                <ul>
                    <li><a href="/WEB-INF/view/member/login.html">로그인</a></li>
                    <li><a href="/WEB-INF/view/member/join.html">회원가입</a></li>
                    <li><a href="/member/">아이디찾기</a></li>
                    <li><a href="/member/t">비밀번호 재발급</a></li>
                </ul>
            </nav>




            <nav class="menu">
                <h1>협력업체</h1>
                <ul>
                    <li><a target="_blank" href="http://www.notepubs.com"><img src="../../../images/notepubs.png" alt="노트펍스" /></a></li>
                    <li><a target="_blank" href="http://www.namoolab.com"><img src="../../../images/namoolab.png" alt="나무랩연구소" /></a></li>
                </ul>
            </nav>

            <!-- <nav class="menu">
                <h1>협찬광고</h1>
                <ul>
                    <li style="width:181px;overflow:hidden;">

                    </li>
                    <li style="width:181px;overflow:hidden;">

                    </li>
                </ul>
            </nav> -->
        </aside>
        <!-- --------------------------- main --------------------------------------- -->

        <!-- content 부분 -->



        <main>
            <h2 class="main title">회원가입 폼</h2>

            <div class="breadcrumb" style="margin-top:-20px;">
                <h3 class="hidden">경로</h3>
                <img src="../../../images/member/step2.png" alt="회원가입 1단계" />
            </div>


            <form id="form1" class="join-form" method="post" action="joinAction">
                <fieldset>
                    <legend class="hidden">회원정보</legend>
                    <table class="table margin-top first">
                        <tbody>
                        <tr>
                            <th><label for="userid">아이디</label></th>
                            <td colspan="3" class="text-align-left indent">
                                <input id="userid" type="text" name="userid" class="width-half"  required="required" value="" placeholder="영문과 숫자 6~20자 이내" pattern="^\w{6,20}$" />
                                <input class="btn-text btn-default" type="button" id="id-check-button" value="중복확인" />
                            </td>
                        </tr>
                        <tr>
                            <th><label for="passwd">비밀번호</label></th>
                            <td colspan="3" class="text-align-left indent">
                                <input id="passwd" type="password" name="passwd" class="" required placeholder="비밀번호 입력" />
                            </td>
                        </tr>
                        <tr>
                            <th><label>비밀번호 확인</label></th>
                            <td colspan="3" class="text-align-left indent"><input class="" name="passwd2" type="password" required /></td>
                        </tr>
                        <tr>
                            <th><label for="name">이름</label></th>
                            <td colspan="3" class="text-align-left indent"><input id="name" class="width-half" name="name" type="text"  value="" required="required"/></td>
                        </tr>
                        <!-- <tr>
                            <th><label>필명</label></th>
                            <td colspan="3" class="text-align-left indent"><input class="width-half" name="nicname" type="text" /></td>
                        </tr> -->
                        <tr>
                            <th><label for="gender">성별</label></th>
                            <td colspan="3" class="text-align-left indent">

                                <select id="gender" class="width-half" name="gender" required>
                                    <option id="choose-gender" value="">선택</option>
                                    <option name="gender" value="남성">남성</option>
                                    <option name="gender" value="여성">여성</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th><label for="birthday">생년월일</label></th>
                            <td colspan="3" class="text-align-left indent">

                                <input id="birthday" name="birthday" type="date" class="width-half" required placeholder="예) 2000-02-17"  value=""/>
                                <!-- <input name="y" type="text" class="width-small margin-hor" style="margin-left:0px;" />년
                                <input name="m" type="text" class="width-small margin-hor" />월
                                <input name="d" type="text" class="width-small margin-hor" />일 -->
                                <input type="radio" name="isLunar" value="0" class="vertical-middle margin-hor" checked />양력
                                <input type="radio" name="isLunar" value="1" class="vertical-middle margin-hor"  />음력
                            </td>
                        </tr>
                        <tr>
                            <th><label for="mobile">핸드폰번호</label></th>
                            <td colspan="3" class="text-align-left indent"><input id="mobile" name="mobile" type="text" class="width-half" pattern="^01[016789]-\d{3,4}-\d{4}$" placeholder="예) 010-1111-2222" required  value=""/></td>
                        </tr>
                        <tr>
                            <th><label for="email">이메일</label></th>
                            <td colspan="3" class="text-align-left indent"><input id="email" name="email" type="email" class="width-half" required placeholder="예) user@newlecture.com"  value=""/></td>
                        </tr>

                        <tr>
                            <td colspan="4">
                                <input type="hidden" name="" value="" />
                                <input id="submit-Button" type="submit" name="btn" value="확인" style="height: 30px; margin:20px;" class="btn-text btn-default" />
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </fieldset>
            </form>
        </main>


    </div>
</div>
<!-- ------------------- <footer> --------------------------------------- -->

<jsp:include page="../../../include/footer.html" flush="false"></jsp:include>

</body>
</html>