<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>moments</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <%@ include file="/WEB-INF/views/head.jsp"%>
    
    <!-- 검색 , 카테고리 목록 -->
    <div class="main">
        <div class="input-area">
            <i class="fa-solid fa-magnifying-glass search-icon"></i>
            <input type="text" class="input-box" placeholder="궁금한 상품을 검색해보세요."/>
            <div id="categoryCarousel" class="carousel slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="category-button-area">
                            <a href="${pageContext.request.contextPath}/mainpage" class="category-button-style">전체</a>
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1001" class="category-button-style">여성패션</a>
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1002" class="category-button-style">남성패션</a>
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1010" class="category-button-style">뷰티</a>
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1011" class="category-button-style">출산/유아동</a>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="category-button-area">
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1012" class="category-button-style">식품</a>
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1013" class="category-button-style">주방용품</a>
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1014" class="category-button-style">생활용품</a>
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1015" class="category-button-style">홈인테리어</a>
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1016" class="category-button-style">가전디지털</a>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="category-button-area">
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1017" class="category-button-style">스포츠/레저</a>
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1018" class="category-button-style">자동차용품</a>
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1019" class="category-button-style">도서/음반</a>
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1020" class="category-button-style">완구/취미</a>
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1021" class="category-button-style">문구/오피스</a>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="category-button-area">
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1024" class="category-button-style">헬스/건강식품</a>
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1025" class="category-button-style">국내여행</a>
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1026" class="category-button-style">해외여행</a>
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1029" class="category-button-style">반려동물용품</a>
                            <a href="${pageContext.request.contextPath}/goods/category?category_no=1030" class="category-button-style">유아동패션</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- 정렬 기준 -->
        <div class="rank mt-3 mb-3">
            <a href="${pageContext.request.contextPath}/mainpage?sort=drop_rate">급락순</a> |
            <a href="${pageContext.request.contextPath}/mainpage?sort=wish_cnt">위시순</a> |
  			<a href="${pageContext.request.contextPath}/goods/category/sort?category_no=${param.category_no}&sort=price_asc">낮은가격순</a> |
    		<a href="${pageContext.request.contextPath}/goods/category/sort?category_no=${param.category_no}&sort=price_desc">높은가격순</a>
        </div>

        <!-- 상품 목록 -->
        <div class="container mb-5">
            <div class="row">
                <c:forEach var="goods" items="${goodsList}">
                    <div class="col-md-3 col-sm-6 mb-4">
                        <div class="card h-100 shadow-sm" onclick="location.href='${pageContext.request.contextPath}/goodspage/goodsDetail?goods_id=${goods.goods_id}'">
                            <img src="${goods.goods_image}" alt="${goods.goods_name}" class="card-img-top" style="object-fit: cover; height: 200px;">
                            <div class="card-body text-center">
                                <h5 class="card-title">${goods.goods_name}</h5>
                                <p class="card-text text-muted">카테고리: ${goods.category_no}</p>
                                <p class="card-text">
                                    <strong>현재 가격: </strong>
                                    <c:choose>
                                        <c:when test="${not empty goods.price}">
                                            <span class="text-danger fw-bold">
                                                <fmt:formatNumber value="${goods.price}" type="number"/>원
                                            </span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-secondary">가격 정보 없음</span>
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                                <p class="card-text">위시 수: ${goods.wish_cnt}</p>
                            </div>
                        </div>
                    </div>
                </c:forEach>

                <c:if test="${empty goodsList}">
                    <div class="col-12 text-center mt-5">
                        <p class="text-muted">상품 목록이 없습니다.</p>
                    </div>
                </c:if>
            </div>
        </div>

        
      
        
    </div>

    <%@ include file="/WEB-INF/views/footer.jsp"%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
