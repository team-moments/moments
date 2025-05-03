<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="sidebar">
  <div class="user-info">
    <img src="${pageContext.request.contextPath}/images/profile.png" class="profile-img" />
    <div class="user-name">${user.name}님</div>
    <div class="user-email">${user.email}</div>
  </div>

  <div class="notification" onclick="location.href='/notification'">
    🔔 읽지 않은 알림 ${unreadCount}개 &gt;
  </div>

  <div class="menu">
    <div class="menu-item ${param.tab == 'edit' || empty param.tab ? 'active' : ''}" onclick="location.href='/mypage'">회원 정보 수정</div>
    <div class="menu-item ${param.tab == 'wishlist' ? 'active' : ''}" onclick="location.href='/mypage/myWishGoods'">마이 위시 상품</div>
  </div>

  <div class="logout" onclick="location.href='/logout'">로그아웃</div>
</div>