<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kmarket::list</title>
    <link rel="stylesheet" href="/kmarket/css/product.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script>
        

    </script>

</head>
<body>
    <div id="wrapper">
        <!-- 헤더 작업 -->
        <header>
            <div class="top">
                <div>
                    <c:choose>
	          	<c:when test="${empty sessUser }">
		            <a href="/kmarket/member/login.do">로그인</a>
		            <a href="/kmarket/member/signup.do">회원가입</a>
		            <a href="/kmarket/member/mypage.do" class="mypage">마이페이지</a>
		      	</c:when>
		      	<c:otherwise>
		      		<span>${sessUser.name }</span>님 반갑습니다.
		            <a href="/kmarket/member/logout.do">로그아웃</a>
		            <a href="/kmarket/member/mypage.do">마이페이지</a>
		            <a href="/kmarket/product/cart.do"
		              ><i class="fa fa-shopping-cart" aria-hidden="true"></i
		              >&nbsp;장바구니</a>
				</c:otherwise>
		      </c:choose>
                </div>
            </div>
            <div class="logo">
                <div>
                    <a href="/kmarket/index.do"><img src="../img/product/header_logo.png" alt="로고"/></a>
                    <form action="#">
                        <input type="text" name="keyword"/>
                        <button><i class="fa fa-search"></i></button>
                    </form>                
                </div>
            </div>
            <div class="menu">
                <div>
                    <ul>
                        <li><a href="#">히트상품</a></li>
                        <li><a href="#">추천상품</a></li>
                        <li><a href="#">최신상품</a></li>
                        <li><a href="#">인기상품</a></li>
                        <li><a href="#">할인상품</a></li>
                    </ul>
                    <ul>
                        <li><a href="#">쿠폰존</a></li>
                        <li><a href="#">사용후기</a></li>
                        <li><a href="#">개인결제</a></li>
                        <li><a href="/kmarket/cs/qna/list.do">고객센터</a></li>
                        <li><a href="/kmarket/cs/faq/list.do">FAQ</a></li>
                    </ul>
                </div>
            </div>
        </header>