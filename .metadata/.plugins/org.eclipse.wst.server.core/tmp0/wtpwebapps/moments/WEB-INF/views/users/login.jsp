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
	<%@ include file="/WEB-INF/views/head.jsp"%>
	
	<div class="container">
	<div class="loginContainer">
		<div class= "loginsubContainer">
			<form action="/moments/login/auth" method="POST" class="loginForm">
			  <div class="loginID">
			    <input
			      class="idPwdInput"
			      type="text"
			      name="email"
			      placeholder="아이디"
			      required
			    />
			  </div>
			
			  <div class="loginPwd">
			    <input
			      class="idPwdInput"
			      type="password"
			      name="password"
			      placeholder="비밀번호"
			      required
			    />
			  </div>
			
			  <div class="userlogin">
			    <button class="idPwdInput" type="submit" id="loginBtn">
			      로그인
			    </button>
			  </div>
			</form>
			 <div class="userloginKeeping">
			  <input
			    class="idInput"
			    type="checkbox"
			  />
			  로그인 상태 유지하기
			</div>
		
			<div class="userAccount">
				<a href="">아이디찾기</a>
				<a href="" style=" border-left: 2px solid #000;  border-right: 2px solid #000; padding: 0 12px 0 12px;" >비밀번호찾기</a>
				<a href="${pageContext.request.contextPath}/signUpAccept">회원가입</a>
			</div>
		
			<div class="SNSLogin">
				<!--네이버-->
				<button type="button" class="loginBntImg">
				  <img src="${pageContext.request.contextPath}/images/login_naver.png" alt="대체 텍스트" onclick="" >
				</button>
				
				<!--카카오-->
				<button type="button" class="loginBntImg">
				  <img src="${pageContext.request.contextPath}/images/login_kakao.png" alt="대체 텍스트" onclick="" >
				</button>
				<!--구글-->
				<button type="button" class="loginBntImg">
				  <img src="${pageContext.request.contextPath}/images/login_google.png" alt="대체 텍스트" onclick="" >
				</button>
			</div>
		</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
	</div>
</body>
</html>