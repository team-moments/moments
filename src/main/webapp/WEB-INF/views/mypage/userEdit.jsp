<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String field = request.getParameter("field");
%>
<html>
<head>
    <title>회원 정보 수정</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userEdit.css" />
</head>
<body>
<div class="wrapper">
	<%@ include file="/WEB-INF/views/head.jsp"%>
	<div class="container">
		<div class="edit-container">
		<%@ include file="sidebar.jsp" %>
		<div class="edit-content">
	    <div class="edit-box">
	        <div class="edit-title">${field == 'name' ? '이름' : field == 'phone' ? '연락처' : field == 'email' ? '이메일' : field == 'password' ? '비밀번호' : '회원정보'}</div>
	        <form action="/mypage/userEdit" method="post">
	            <input type="hidden" name="field" value="<%= field %>" />
	            <input type="hidden" name="email" value="${user.users_email}" />
	
	            <div class="edit-field">
	                <label>
	                    <c:choose>
	                        <c:when test="${field == 'name'}">이름</c:when>
	                        <c:when test="${field == 'phone'}">연락처</c:when>
	                        <c:when test="${field == 'email'}">이메일</c:when>
	                        <c:when test="${field == 'password'}">새 비밀번호</c:when>
	                    </c:choose>
	                </label>
	                
	                
	                
	                <c:choose>
	                    <c:when test="${field == 'name'}">
	                        <input type="text" name="value" placeholder="${user.users_name}"" />
	                    </c:when>
	                    <c:when test="${field == 'phone'}">
	                        <input type="text" name="value" placeholder="${user.users_phone}" />
	                    </c:when>
	                    <c:when test="${field == 'email'}">
	                        <input type="email" name="value" placeholder="${user.users_email}" />
	                    </c:when>
	                    
	                    <!-- 비밀번호 변경하고 로그아웃후 로그인페이지로 이동  -->
	                    
	                    <c:when test="${field == 'password'}">
	                        <input type="password" name="currentPassword" placeholder="현재 비밀번호 입력" />
	            			<input type="password" name="newPassword" placeholder="새 비밀번호 입력" />
	            			<input type="password" name="confirmPassword" placeholder="새 비밀번호 확인" />
	                    </c:when>
	                    <c:otherwise>
	                        <p>잘못된 접근입니다.</p>
	                    </c:otherwise>
	                </c:choose>
	            </div>
	
	            <div class="edit-buttons">
	                <a href='${pageContext.request.contextPath}/mypage' class="btn cancel">취소</a>
	                <button type="submit" class="btn submit">수정</button>
	            </div>
	        </form>
	    </div>
	    </div>
	    </div>
	</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
	</div>	
	</div>
</body>
</html>
