<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <META name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=no"> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet" type="text/css" />
</head>


<body>
	<div class="wrapper">
	<header>
		<%@ include file="/WEB-INF/views/head.jsp"%>
	</header>
	
	<div class="container">
		<h1>회원가입 완료</h1>
		<p>모멘트 회원가입을 환영합니다.</p>
		<p>모멘트의 어떤 상품이든 원하는 가격이 되면 알림이 오게 설정할 수 있어요.</p>
	 	<button type="button" onclick="window.location.href='${pageContext.request.contextPath}/main'">메인페이지로 이동</button>
		<button type="button" onclick="window.location.href='${pageContext.request.contextPath}/login'">로그인하러 가기</button>
		
	</div>
	<footer>
		<%@ include file="/WEB-INF/views/footer.jsp"%>
	</footer>
	</div>
	
</body>
</html>