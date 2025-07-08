<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>moments</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
	<style>
      .notification-box {
        font-family: 'Apple SD Gothic Neo', sans-serif;
        padding: 20px;
      }

      .notification-title {
        font-size: 18px;
        font-weight: bold;
        margin-bottom: 10px;
      }

      .notification-card {
        border: 1px solid #ddd;
        border-radius: 16px;
        padding: 14px 16px;
        margin-bottom: 12px;
        background-color: #fafafa;
        box-shadow: 0 2px 6px rgba(0,0,0,0.08);
        position: relative;
        width: 100%;
        box-sizing: border-box;
      }

      .notification-date {
        position: absolute;
        top: 10px;
        right: 14px;
        font-size: 12px;
        color: #888;
      }

      .notification-message {
        font-size: 15px;
        font-weight: 500;
        color: #333;
        margin-top: 16px;
      }
    </style>
</head>

<body>
    <!-- header -->
   <header>
    <nav class="navbar navbar-expand-lg">`
	        <div class="container-fluid">
	        	<a class="navbar-brand" href="${pageContext.request.contextPath}/views/main.jsp"><img src="${pageContext.request.contextPath}/images/moments_logo.png" class="moments_logo"></a>
	        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	        </button>
	        <div class="collapse navbar-collapse justify-content-end d-flex" id="navbarSupportedContent">
	        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
	    <li class="nav-item">
	        <a class="nav-link active" id="intro-moments" aria-current="page" href="#"><img src="${pageContext.request.contextPath}/images/info_img.png" class="icon-img">  모멘트소개</a>
	    </li>
	  <!-- 헤더 알림 아이콘 -->
		<li class="nav-item" id="notificationTrigger" style="position: relative;">
		  <a class="nav-link active" style="cursor: pointer;">
		    <img src="${pageContext.request.contextPath}/images/alert_img.png" class="icon-img"> 알림
		  </a>
		
		  <!-- 알림 팝업 -->
		  <div id="notificationPopupWrapper" style="display: none; position: absolute; top: 40px; right: 0; z-index: 1000;
		       background: #fff; border: 1px solid #ccc; border-radius: 12px; padding: 14px; width: 300px;
		       box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);">
		    <!-- 위쪽 꼬리 -->
		    <div style="
		      position: absolute;
		      top: -10px;
		      right: 20px;
		      width: 0;
		      height: 0;
		      border-left: 10px solid transparent;
		      border-right: 10px solid transparent;
		      border-bottom: 10px solid #ccc;
		    "></div>
		
		    <!-- 알림 목록 -->
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
		</li>
		
		<script>
		  document.addEventListener('DOMContentLoaded', function () {
		    const trigger = document.getElementById('notificationTrigger');
		    const popup = document.getElementById('notificationPopupWrapper');
		
		    trigger.addEventListener('click', function (e) {
		      e.stopPropagation();
		      popup.style.display = (popup.style.display === 'none' || popup.style.display === '') ? 'block' : 'none';
		    });
		
		    document.addEventListener('click', function (e) {
		      if (!popup.contains(e.target) && !trigger.contains(e.target)) {
		        popup.style.display = 'none';
		      }
		    });
		  });
		</script>
			  
		</li>
	    <li class="nav-item">
	        <a class="nav-link" href="${pageContext.request.contextPath}/mypage"><img src="${pageContext.request.contextPath}/images/mypage_img.png" class="icon-img"> 마이페이지</a>
	    </li>
	</ul>
        </div>
        </div>
      </nav>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	</header>
</body>
</html>