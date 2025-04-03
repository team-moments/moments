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
    <!-- header -->
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
        <a class="navbar-brand" href="#"><img src="${pageContext.request.contextPath}/images/moments_logo.png" class="moments_logo"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end d-flex" id="navbarSupportedContent">
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
    <li class="nav-item">
        <a class="nav-link active" id="intro-moments" aria-current="page" href="#"><img src="${pageContext.request.contextPath}/images/info_img.png" class="icon-img">  모멘트소개</a>
    </li>
    <li class="nav-item">
        <a class="nav-link active" aria-current="page" href="#"><img src="${pageContext.request.contextPath}/images/alert_img.png" class="icon-img"> 알림</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#"><img src="${pageContext.request.contextPath}/images/mypage_img.png" class="icon-img"> 마이페이지</a>
    </li>
</ul>
        </div>
        </div>
        </nav>

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
                            <button class="category-button-style">생활용품
</button>
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
                            <button class="category-button-style">국내여행
</button>
                            <button class="category-button-style">해외여행
</button>
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
      <div class="container mb-5">
          <div class="row">
              <c:forEach var="goods" items="${goodsList}">
                  <div class="col-3 mb-4">
                      <a href="${pageContext.request.contextPath}/goods.jsp?id=${goods.id}" class="card">                          <div class="card-body">
                              <p class="card-text">${goods.name}</p>
                              <p class="card-text">${goods.price}원</p>
                          </div>
                      </a>
                  </div>
                  
              </c:forEach>
          
          <c:if test="${empty goodsList}">
    <p>상품 목록이 없습니다.</p>
</c:if>
          
          
          </div>
      </div>
      
      
      
        
        
        
        
        
        
     </div>
        <!-- footer -->
        <footer>
            <div class="container">
                <div class="pb-5 pt-5 first-line">
                    <div class="row">
                        <div class="col">
                            MOMENTS
                        </div>
                        <div class="col">
                            모멘트가 제공하는 가격 정보는 쿠팡 파트너스의 API를 사용 하여 제공 합니다.<br/>
                            제공되는 제품 가격 정보는 정확하지만 수시로 변경 될 수 있고 시차가 발생 할 수 있습니다.<br/>
                           모멘트는 쿠팡의 특정 카테고리는 제외하고 추적 합니다. 모멘트의 가격 정보를 서비스를 이용한 고객이 모멘트의 쿠팡 바로가기 링크를 통한 구매에 대해서 쿠팡으로부터 수수료를 받을 수 있습니다.<br/>
                           모멘트는 통신판매중개자이며 통신판매의 당사자가 아닙니다. 따라서 모멘트는 상품 · 거래정보 및 거래에 대하여 책임을 지지 않습니다.
                        </div>
                    </div>
                </div>
            </div>
            <div class="third-line pt-5 pb-5 text-center"> 
                Copyright: © 김이슬 장세영 홍유경
            </div>
        
        
            </div>
        </footer>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        
</body>
</html>