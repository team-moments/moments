<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>마이페이지</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/info.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="wrapper">
  <%@ include file="/WEB-INF/views/head.jsp"%>
  <div class="container">
	  <div class="mypage-container">
	    <%@ include file="sidebar.jsp" %>
	
	    <main class="mypage-content">
	      <div class="section">
	        <h3>기본정보</h3>
	        <div class="info-box">
	          <div class="info-row" onclick="location.href='${pageContext.request.contextPath}/userEdit?field=name'">
	            <span>이름</span><span class="value">${user.name}</span><span class="arrow">></span>
	          </div>
	          <div class="info-row" onclick="location.href='${pageContext.request.contextPath}/userEdit?field=phone'">
	            <span>연락처</span><span class="value">${user.phone}</span><span class="arrow">></span>
	          </div>
	        </div>
	      </div>
	
	      <div class="section">
	        <h3>계정 정보</h3>
	        <div class="info-box">
	          <div class="info-row" onclick="location.href='${pageContext.request.contextPath}/userEdit?field=email'">
	            <span>이메일</span><span class="value">${user.email}</span><span class="arrow">></span>
	          </div>
	          <div class="info-row" onclick="location.href='${pageContext.request.contextPath}/userEdit?field=password'">
	            <span>비밀번호</span><span class="value">********</span><span class="arrow">></span>
	          </div>
	        </div>
	      </div>
	
	      <div class="section">
	        <h3>SNS계정 연동 정보</h3>
	        <div class="info-box">
	          <div class="info-row">
	            <span>SNS계정</span><span class="value">${user.snsEmail}</span><span class="arrow">></span>
	          </div>
	        </div>
	      </div>
	    </main>
	  </div>
  </div>
  <%@ include file="/WEB-INF/views/footer.jsp"%>
 </div>
</body>
</html>