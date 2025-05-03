<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link href="https://fonts.googleapis.com/css2?family=Kodchasan:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/header.css">
</head>
<body>
<!-- 헤더 영역 -->
<div class="header">
    <div class="size">
        <!-- 로고 및 제목 -->
        <div class="title">         
            <div class="title-img">
                <a href="${pageContext.request.contextPath}">
                    <img id="mlogo" src="${pageContext.request.contextPath}/img/moments_logo.png" alt="로고">
                </a>
            </div>
            <div class="title-text">
                <a id="moments" href="${pageContext.request.contextPath}">moments</a>
            </div>
        </div>

        <!-- 로그인 여부에 따른 UI 변경 -->
        <c:choose>
            <c:when test="${sessionScope.loginInfo == null}">
                <div class="login">
                    <a href="${pageContext.request.contextPath}/users/login">로그인</a> 
                    <a href="${pageContext.request.contextPath}/users/join">회원가입</a>
                </div>
            </c:when>
            <c:otherwise>
                <div class="login">
                    <a href="${pageContext.request.contextPath}/notifications">
                        <img id="alerticon" src="${pageContext.request.contextPath}/img/alert.png" alt="알림"> 알림목록
                    </a> 
                    <a href="${pageContext.request.contextPath}/mypage/info">마이페이지</a> 
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
