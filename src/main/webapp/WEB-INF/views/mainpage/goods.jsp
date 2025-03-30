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
    <link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <!-- header -->
    <nav class="navbar navbar-expand-lg">
        <div class="container-fluid">
        <a class="navbar-brand" href="#"><img src="${pageContext.request.contextPath}/resources/images/moments_logo.png" class="moments_logo"></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse justify-content-end d-flex" id="navbarSupportedContent">
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
    <li class="nav-item">
        <a class="nav-link active" id="intro-moments" aria-current="page" href="#"><img src="${pageContext.request.contextPath}/resources/images/info_img.png" class="icon-img">  모멘트소개</a>
    </li>
    <li class="nav-item">
        <a class="nav-link active" aria-current="page" href="#"><img src="${pageContext.request.contextPath}/resources/images/alert_img.png" class="icon-img"> 알림</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="#"><img src="${pageContext.request.contextPath}/resources/images/mypage_img.png" class="icon-img"> 마이페이지</a>
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
        </div>
    </div>
    
</body>
</html>