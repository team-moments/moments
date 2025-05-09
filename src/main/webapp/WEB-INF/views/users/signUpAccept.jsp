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
  function nextStep() {
    const checkbox = document.getElementById('agree');
    if (!checkbox.checked) {
      alert("약관에 동의하셔야 다음 단계로 진행할 수 있습니다.");
      return false; // 폼 제출 막기
    }
    return true; // 체크되었으면 제출 허용
  }
</script>


<body>
	<div class="wrapper">
	<header>
		<%@ include file="/WEB-INF/views/head.jsp"%>
	</header>
	
	<div class="container">
		<div class="signUpAccenptContainer">
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
			<form action="/moments/agreeTerms" method="POST" onsubmit="return nextStep()">
				<div>
				  <input type="checkbox" id="agree">
				  <label for="agree">전체 동의하기</label>
				</div>
				<div class="loginPwd">
					<textarea>개인정보저리방침....(약관)</textarea>
				</div>
				<div>
					 <button type="button" onclick="window.location.href='${pageContext.request.contextPath}/login'">이전</button>
					<button type="submit">다음</button>
				</div>
			</form>
			
			
		</div>
	</div>
	<footer>
		<%@ include file="/WEB-INF/views/footer.jsp"%>
	</footer>
	</div>
</body>
</html>