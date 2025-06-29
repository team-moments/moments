<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sidebar.css">
<div class="sidebar">
  <div class="user-info">
    <img src="${pageContext.request.contextPath}/images/profile.png" class="profile-img" />
    <div class="user-name">${user.name}님</div>
    <div class="user-email">${user.email}</div>
  </div>

  <div class="notification" onclick="location.href='${pageContext.request.contextPath}/notifications'">
    🔔 읽지 않은 알림 ${unreadCount}개 &gt;
  </div>

  <c:set var="uri" value="${pageContext.request.requestURI}" />

<div class="menu">
  <div class="menu-item ${fn:contains(uri, '/mypage') && !fn:contains(uri, '/myWishGoods') ? 'active' : ''}"
       onclick="location.href='${pageContext.request.contextPath}/mypage'">
    회원 정보 수정
  </div>
  <div class="menu-item ${fn:contains(uri, '/myWishGoods') ? 'active' : ''}"
       onclick="location.href='${pageContext.request.contextPath}/mypage/myWishGoods'">
    마이 위시 상품
  </div>
</div>

  <div class="logout" onclick="location.href='/logout'">로그아웃</div>
  <div class="leave" onclick="location.href='/leave'">탈퇴하기</div>
  
</div>