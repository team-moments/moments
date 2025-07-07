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
    <link href="${pageContext.request.contextPath}/css/signUpInfo.css" rel="stylesheet" type="text/css" />
</head>
<script>

window.onload = function() {
    // 페이지 캐시 방지 및 폼 초기화
    if (performance.navigation.type === 2) { // 뒤로가기 탐지
      location.replace('${pageContext.request.contextPath}/mainpage');
    }
    // 또는 폼 초기화
    document.getElementById("signupForm").reset();
  };

  // 페이지 복원 시 강제 새로고침
  window.addEventListener('pageshow', function(event) {
    if (event.persisted) {
      location.reload();
    }
  });

$(document).ready(function () {
  const touched = {
    email: false,
    password: false,
    confirmPassword: false,
    name: false,
    phone: false
  };

  // 필드별 입력 감지 및 터치 처리
  $('#email').on('input', function () {
    touched.email = true;
    validateEmail();
  });

  $('#password').on('input', function () {
    touched.password = true;
    validatePassword();
    validateConfirmPassword(); // 비밀번호 변경 시 확인도 같이 체크
  });

  $('#confirmPassword').on('input', function () {
    touched.confirmPassword = true;
    validateConfirmPassword();
  });

  $('#name').on('input', function () {
    touched.name = true;
    validateName();
  });

  $('#phone').on('input', function () {
    touched.phone = true;
    validatePhone();
  });

  // 이메일 유효성 검사
  function validateEmail() {
    if (!touched.email) {
      $('#emailError').hide();
      return;
    }
    const val = $('#email').val();
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!regex.test(val)) {
      $('#emailError').text('올바른 이메일 형식이 아닙니다.').show();
    } else {
      $('#emailError').hide();
    }
  }

  // 비밀번호 유효성 검사 (6자 이상)
 function validatePassword() {
  if (!touched.password) {
    $('#passwordError').hide();
    return;
  }

  const val = $('#password').val();

  const lengthCheck = /^.{8,16}$/;
  const upperLowerCheck = /[A-Za-z]/;     
  const numberCheck = /[0-9]/;            
  const specialCharCheck = /[!@#$%^&*(),.?":{}|<>]/; 
  const spaceCheck = /^\S+$/;             
  const validCharCheck = /^[A-Za-z0-9!@#$%^&*(),.?":{}|<>]+$/; 

  if (!lengthCheck.test(val) ||
      !upperLowerCheck.test(val) ||
      !numberCheck.test(val) ||
      !specialCharCheck.test(val) ||
      !spaceCheck.test(val) ||
      !validCharCheck.test(val)) {
    $('#passwordError').text('비밀번호: 8~16자의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.').show();
  } else {
    $('#passwordError').hide();
  }
}
  // 비밀번호 확인 일치 여부 검사
  function validateConfirmPassword() {
    if (!touched.confirmPassword) {
      $('#confirmPasswordError').hide();
      return;
    }
    const pwd = $('#password').val();
    const confirm = $('#confirmPassword').val();
    if (pwd !== confirm) {
      $('#confirmPasswordError').text('비밀번호가 일치하지 않습니다.').show();
    } else {
      $('#confirmPasswordError').hide();
    }
  }

  // 이름 유효성 검사 (2자 이상)
  function validateName() {
    if (!touched.name) {
      $('#nameError').hide();
      return;
    }
    const val = $('#name').val();
    if (val.trim().length < 2) {
      $('#nameError').text('이름은 2자 이상 입력해주세요.').show();
    } else {
      $('#nameError').hide();
    }
  }

  // 휴대폰 번호 유효성 검사 (010, 011 등 시작 + 총 10~11자리)
  function validatePhone() {
    if (!touched.phone) {
      $('#phoneError').hide();
      return;
    }
    const val = $('#phone').val();
    const regex = /^01[016789][0-9]{7,8}$/;
    if (!regex.test(val)) {
      $('#phoneError').text('올바른 휴대폰 번호를 입력해주세요.').show();
    } else {
      $('#phoneError').hide();
    }
  }

  // 폼 제출 이벤트
  $('#signupForm').on('submit', function (e) {
    e.preventDefault(); // 기본 제출 막기

    // 모든 필드를 강제로 터치 처리 (입력 안 해도 오류 보이게)
    for (let key in touched) {
      touched[key] = true;
    }

    // 전체 유효성 검사 호출
    validateEmail();
    validatePassword();
    validateConfirmPassword();
    validateName();
    validatePhone();

    // 에러 메시지 표시 여부 체크
    const hasError =
      $('#emailError').is(':visible') ||
      $('#passwordError').is(':visible') ||
      $('#confirmPasswordError').is(':visible') ||
      $('#nameError').is(':visible') ||
      $('#phoneError').is(':visible');

    if (hasError) {
      return; // 에러가 있으면 ajax 호출하지 않음
    }

    // 유효성 검사 통과 시 폼 데이터 수집
    const formData = {
      email: $('#email').val(),
      password: $('#password').val(),
      passwordConfirm: $('#confirmPassword').val(),
      name: $('#name').val(),
      phone: $('#phone').val()
    };

    // ajax 요청
    $.ajax({
      url: '${pageContext.request.contextPath}/signUp',
      type: 'POST',
      contentType: 'application/json',
      data: JSON.stringify(formData),
      success: function (response) {
        window.location.href = '${pageContext.request.contextPath}/signUpCompleted'; // 로그인 페이지 이동 예시
      },
      error: function (xhr) {
        alert('회원가입 실패했습니다');
        window.location.href = '${pageContext.request.contextPath}/login'; // 로그인 페이지 이동 예시

      }
    });
  });
});
</script>
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
			<form id="signupForm">
			  <div class="userInfoInput">
			  	<input type="text" id="email" name="email" placeholder="이메일" />
				  <span id="emailError" class="error-message"></span>
				
				  <input type="password" id="password" name="password" placeholder="비밀번호" />
				  <span id="passwordError" class="error-message"></span>
				
				  <input type="password" id="confirmPassword" name="passwordConfirm" placeholder="비밀번호 확인" />
				  <span id="confirmPasswordError" class="error-message"></span>
				
				  <input type="text" id="name" name="name" placeholder="이름" />
				  <span id="nameError" class="error-message"></span>
				
				  <input type="text" id="phone" name="phone" placeholder="휴대폰 번호" />
				  <span id="phoneError" class="error-message"></span>
			    <button type="button" onclick="window.location.href="">이전</button>
			    <button type="submit">회원가입</button>
			  </div>
			</form>
	
		<!-- 예외처리 추가 2. 이메일인증 1. 입력안되면 안넘어가게..중복처리도 제 외 같은 이메일 금지-->
		
		</div>
	</div>
	<footer>
		<%@ include file="/WEB-INF/views/footer.jsp"%>
	</footer>
	</div>
</body>
</html>


<!-- <script>
  // 페이지가 로드될 때 히스토리 상태를 현재 URL로 교체해서 뒤로가기를 조작하기 편하게 만듦
  window.history.replaceState(null, null, window.location.href);

  // 뒤로가기 혹은 앞으로 가기 버튼 누르면 작동
  window.onpopstate = function(event) {
    // 원하는 페이지로 강제로 이동시킴
    location.replace('${pageContext.request.contextPath}/signUpAccept');
  };

  // 페이지 로드되면 폼 초기화(입력 내용 삭제)
  window.onload = function() {
    const form = document.getElementById("signupForm");
    if(form) form.reset();
  };
</script> -->