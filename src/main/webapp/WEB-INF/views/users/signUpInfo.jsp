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
		<div class="signUpInfoContainer">
			<div class="signUptitle">
				<div class="signUptitle1">
					<div class ="signUpCircle">1</div>
					<span class="signUpText">이용약관 동의하기</span>
				 </div>
				<div class="signUptitle2">
					<div class ="signUpCircle">2</div>
					<span class="signUpText">개인정보 입력</span>
				 </div>
			</div>
			<form action="/moments/signUp" method="POST">
			  <div class="userInfoInput">
			    <input class="emailInput" type="text" name="email" placeholder="이메일 아이디" />
			    <!-- <input class="emaiAuthInput" type="text" name="authCode" placeholder="인증번호 입력" /> -->
			    <input class="passwordInput" type="password" name="password" placeholder="비밀번호" />
			    <input class="passwordConfirmInput" type="password" name="passwordConfirm" placeholder="비밀번호 확인" />
			    <input class="nameInput" type="text" name="name" placeholder="이름" />
			    <input class="phoneNumberInput" type="text" name="phoneNumber" placeholder="전화번호" />
			    
			    <button type="button" onclick="window.location.href="">이전</button>
			    <button type="submit">가입하기</button>
			  </div>
			</form>
	
		<!-- 예외처리 추가 1. 입력안되면 안넘어가게..중복처리도 제 외 같은 이메일 금지-->
		
		</div>
	</div>
	<footer>
		<%@ include file="/WEB-INF/views/footer.jsp"%>
	</footer>
	</div>
</body>
</html>