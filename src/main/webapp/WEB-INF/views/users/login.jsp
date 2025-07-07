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
<script>
$(document).ready(function() {
	  $('#loginForm').submit(function(e) {
	    e.preventDefault();

	    const data = {
	    		  email : $('#email').val(),
	    		  password: $('#password').val()
	    		};

	    		$.ajax({
	    		  url: '${pageContext.request.contextPath}/login',
	    		  type: 'POST',
	    		  contentType: 'application/json',
	    		  data: JSON.stringify(data),
	    		  xhrFields: {
	    		    withCredentials: true  // ★ 쿠키 저장하려면 필수!!
	    		  },
	    		  success: function(response) {
	    		    window.location.href = '${pageContext.request.contextPath}/mainpage';
	    		  },
	    		  error: function(xhr) {
	    		    alert('아이디 또는 비밀번호를 다시 입력해주세요');
	    		  }
	    		});
	  });
	});
</script>

<body>
	<div class="wrapper">
	<%@ include file="/WEB-INF/views/head.jsp"%>
	
	<div class="container">
	<div class="loginContainer">
		<div class= "loginsubContainer">
			<form  id="loginForm" class="loginForm">
			<!-- action="/moments/login/auth" method="POST" class="loginForm" 
			onsubmit="return loginChk(this);">
			 --> 
			 <div class="loginID">
			    <input
			      class="idPwdInput"
			      type="text"
			      id="email"
			      placeholder="아이디"
			      required
			    />
			  </div>
			
			  <div class="loginPwd">
			    <input
			      class="idPwdInput"
			      type="password"
			      id="password"
			      placeholder="비밀번호"
			      required
			    />
			  </div>
			
		    <button id="loginBtn" type="submit" > 
		      로그인
		    </button>
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
				<a href="" style=" border-left: 1.5px solid #BDBDBD;  border-right: 1.5px solid #BDBDBD; padding: 0 20px 0 20px;" >비밀번호찾기</a>
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