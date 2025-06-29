<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <title>마이 위시 상품</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/myWishGoods.css" />
</head>
<meta charset="UTF-8">
<title>test</title>
<body>
<div class="wrapper">
	  <%@ include file="/WEB-INF/views/head.jsp"%>
	  <div class="container">
	 	<div class="mywish-container">
	  	<%@ include file="sidebar.jsp" %>
			<div class="wish-content">
				<div class="section achieved">
				    <h3>달성 상품 목록</h3>
				    <div class="product-list achieved">
				        <c:forEach var="item" items="${achievedList}">
				        	<div class="product-box achieved" onclick="window.open('${item.coupangLink}', '_blank')">
				                <img src="${item.image}" alt="상품 이미지" />
					                <div class="product-info">
					                	<div class="product-name">상품명 : ${item.name}</div>
					                	<div class="price-line">
						                <div class="product-price">설정 가격 : ${item.wish_price}원</div>
						                <div class="product-current">현재가 : ${item.currentPrice}원</div>
					            	</div>
				            	</div>
				            </div>
				        </c:forEach>
				    </div>
			    </div>
				<div class="section wished">
				    <h3>위시 상품</h3>
				    <div class="product-list wish">
				        <c:forEach var="item" items="${ongoingList}">
				            <div class="product-box">
				                <img src="${item.image}" alt="상품 이미지" />
				                <div class="product-name">상품명 : ${item.name}</div>
				                <div class="product-price">설정 가격 : ${item.wish_price}원</div>
				                <div class="product-current">현재가 : ${item.currentPrice}원</div>
				            </div>
				        </c:forEach>
				    </div>
			    </div>
				<div class="section expired">
				    <h3>만료 상품 목록</h3>
				    <div class="product-list expired">
				        <c:forEach var="item" items="${expiredList}">
				            <div class="product-box">
				                <img src="${item.image}" alt="상품 이미지" />
				                <div class="product-name">상품명 : ${item.name}</div>
				                <div class="product-price">설정 가격 : ${item.wish_price}원</div>
				                <div class="product-current">현재가 : ${item.currentPrice}원</div>
				            </div>
				        </c:forEach>
				    </div>
			    </div>
			</div>
		</div>
		</div>
	<%@ include file="/WEB-INF/views/footer.jsp"%>
	</div>
</body>
</html>