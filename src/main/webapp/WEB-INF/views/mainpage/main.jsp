<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>moments</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">    
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
                            <button class="category-button-style">전체</button>
                            <button class="category-button-style">여성패션</button>
                            <button class="category-button-style">남성패션</button>
                            <button class="category-button-style">뷰티</button>
                            <button class="category-button-style">출산/유아동</button>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="category-button-area">
                            <button class="category-button-style">식품</button>
                            <button class="category-button-style">주방용품</button>
                            <button class="category-button-style">생활용품</button>
                            <button class="category-button-style">홈인테리어</button>
                            <button class="category-button-style">가전디지털
                            </button>
                        </div>
                    </div>
                    <div class="carousel-item">
                        <div class="category-button-area">
                            <button class="category-button-style">스포츠/레저</button>
                            <button class="category-button-style">자동차용품</button>
                            <button class="category-button-style">도서/음반/DVD</button>
                            <button class="category-button-style">완구/취미</button>
                            <button class="category-button-style">문구/오피스</button>
                        </div>
                    </div>
                     <div class="carousel-item">
                        <div class="category-button-area">
                            <button class="category-button-style">헬스/건강식품</button>
                            <button class="category-button-style">국내여행</button>
                            <button class="category-button-style">해외여행</button>
                            <button class="category-button-style">반려동물용품</button>
                            <button class="category-button-style">유아동패션</button>
                        </div>
                    </div>
                </div>
                <button class="carousel-control-prev" type="button" data-bs-target="#categoryCarousel" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#categoryCarousel" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
        
        
			<!-- 상품 목록 -->
			<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
			
			<div class="container mb-5">
			    <div class="row">
			        <c:forEach var="goods" items="${goodsList}">
			            <div class="col-3 mb-4">
			                <div class="card">
			                    <div class="card-body">
			                        <img src="${goods.goods_image}" alt="${goods.goods_name}" class="card-img-top" style="width:100%; height:auto;">
			                        <p class="card-text">${goods.goods_name}</p>
			                    </div>
			                </div>
			            </div>
			        </c:forEach>
			
			        <c:if test="${empty goodsList}">
			            <p>상품 목록이 없습니다.</p>
			        </c:if>
			    </div>
			</div>


     	</div>
        <!-- footer -->
       <%@ include file="/WEB-INF/views/footer.jsp"%>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>