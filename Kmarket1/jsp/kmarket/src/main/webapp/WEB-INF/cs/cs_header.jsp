<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kmarket 고객센터</title>
    <link rel="stylesheet" href="/kmarket/css/cs.css">
    <link rel="shortcut icon" type="image/x-icon" href="/kmarket/img/common/favicon.ico" />
	   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
 
</head>
<body>
    <div class="wrapper">
        <header>
            <div class="top">
                <div>
                    <p>
                       <c:choose>
	          	<c:when test="${empty sessUser }">
		            <a href="/kmarket/member/login.do">로그인</a>
		            <a href="/kmarket/member/signup.do">회원가입</a>
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
		      		  <a href="/kmarket/index.do">HOME</a>
                    </p>
                </div>
            </div>
            <div class="logo">
                <div>
                    <a href="/kmarket/cs/csIndex.do">
                        <img src="/kmarket/img/cs/logo.png" alt="로고">고객센터
                    </a>
                </div>
            </div>
        </header>
  