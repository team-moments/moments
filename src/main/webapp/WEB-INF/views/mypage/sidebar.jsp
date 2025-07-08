<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sidebar.css">
<div class="sidebar">
	<!-- 사이드바 알림 팝업 -->
	<div id="sidebarNotificationPopup" style="
	  position: absolute;
	  top: 27%;
	  left: 21%;
	  transform: translateY(-50%);
	  margin-left: 12px;
	  background: #fff;
	  border: 1px solid #ccc;
	  border-radius: 12px;
	  padding: 14px;
	  width: 300px;
	  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
	  z-index: 9999;
	  display: none;
	">
	  <!-- 왼쪽 꼬리 -->
	  <div style="
	    position: absolute;
	    top: 50%;
	    left: -10px;
	    transform: translateY(-50%);
	    width: 0;
	    height: 0;
	    border-top: 10px solid transparent;
	    border-bottom: 10px solid transparent;
	    border-right: 10px solid #ccc;
	  "></div>
	
	  <div style="font-size: 14px;">
	    <c:choose>
	      <c:when test="${not empty alertList}">
	        <div class="notification-box">
			  <div class="notification-title">알림</div>
			  <c:forEach var="alert" items="${alertList}">
			    <div class="notification-card">
			      <div class="notification-date">
			        <fmt:formatDate value="${alert.sent_date}" pattern="M월 d일" />
			      </div>
			      <div class="notification-message">
			        <c:choose>
			          <c:when test="${alert.is_sent == 1}">
			            ${alert.name} 상품이 설정한 가격에 도달했습니다
			          </c:when>
			          <c:when test="${alert.is_sent == 2}">
			            ${alert.name}의 위시 기간이 만료되었습니다
			          </c:when>
			        </c:choose>
			      </div>
			    </div>
			  </c:forEach>
			</div>
	      </c:when>
	      <c:otherwise>
	        <p>알림이 없습니다.</p>
	      </c:otherwise>
	    </c:choose>
	  </div>
	</div>
  <div class="user-info">
    <img src="${pageContext.request.contextPath}/images/profile.png" class="profile-img" />
    <div class="user-name">${user.name}님</div>
    <div class="user-email">${user.email}</div>
  </div>

  <div class="notification" id="notificationBtn2" style="cursor: pointer;">
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

<script>
  document.addEventListener('DOMContentLoaded', function () {
    const sidebarBtn = document.getElementById('notificationBtn2');
    const sidebarPopup = document.getElementById('sidebarNotificationPopup');

    sidebarBtn.addEventListener('click', function (e) {
      e.stopPropagation();
      sidebarPopup.style.display = (sidebarPopup.style.display === 'none' || sidebarPopup.style.display === '') ? 'block' : 'none';
    });

    document.addEventListener('click', function (e) {
      if (!sidebarPopup.contains(e.target) && !sidebarBtn.contains(e.target)) {
        sidebarPopup.style.display = 'none';
      }
    });
  });
</script>