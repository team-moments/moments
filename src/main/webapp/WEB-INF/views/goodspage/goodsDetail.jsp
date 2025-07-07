<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>moments</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="${pageContext.request.contextPath}/css/goodsdetail.css" rel="stylesheet" type="text/css" />

</head>
</head>
<body>
    <%@ include file="/WEB-INF/views/head.jsp"%>

    <div class="main">
        <div class="input-area">
            <i class="fa-solid fa-magnifying-glass search-icon"></i>
            <input type="text" class="input-box" placeholder="궁금한 상품을 검색해보세요."/>
        </div>
        
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <div class="goods_wrap">

        
 <c:if test="${not empty goods}">
    <div class="product">
        <img src="${goods.goods_image}" alt="${goods.goods_name}" />
        <div class="details">
            <div class="category">${goods.category_name}</div>
            <div class="title">${goods.goods_name}</div>
            <button class="wish-button" onclick="alert('위시 가격 기능은 준비 중입니다.')">위시가격 설정</button>
        </div>
    </div>

    <div class="line"></div>

    <div class="price-row">
        <div class="label">현재가</div>
        <div class="value"><fmt:formatNumber value="${goods.price}" type="number" />원</div>
    </div>
    <div class="line"></div>

    <div class="price-row">
        <div class="label">할인율</div>
        <div class="value discount">
            <c:choose>
                <c:when test="${not empty goods.discount_rate}">
                    -${goods.discount_rate}%
                </c:when>
                <c:otherwise>할인율 정보 없음</c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="line"></div>

    <div class="price-row">
        <div class="label">최고가</div>
        <div class="value high">
                    <c:choose>
            <c:when test="${not empty goods.max_price}">
                <fmt:formatNumber value="${goods.max_price}" type="number" />원
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
        </div>
    </div>
    <div class="line"></div>

    <div class="price-row">
        <div class="label">최저가(한달기준)</div>
        <div class="value low">
            <c:choose>
            <c:when test="${not empty goods.min_price}">
                <fmt:formatNumber value="${goods.min_price}" type="number" />원
            </c:when>
            <c:otherwise>-</c:otherwise>
        </c:choose>
        </div>
    </div>
</c:if>
 
 </div>
 
 
 
	  
	<%@ include file="/WEB-INF/views/footer.jsp"%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>