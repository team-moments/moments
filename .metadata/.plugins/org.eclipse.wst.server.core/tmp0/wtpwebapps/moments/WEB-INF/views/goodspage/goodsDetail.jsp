<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>moments</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href="${pageContext.request.contextPath}/css/goodsdetail.css" rel="stylesheet" type="text/css" />

    <!-- Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>

<body>
<%@ include file="/WEB-INF/views/head.jsp"%>

<div class="main">
    <div class="input-area">
        <i class="fa-solid fa-magnifying-glass search-icon"></i>
        <input type="text" class="input-box" placeholder="궁금한 상품을 검색해보세요."/>
    </div>

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

    <!-- 📈 가격 그래프 영역 -->
    <div class="container mt-5">
        <h5>📈 한달간 가격 변동 그래프</h5>
        <canvas id="priceChart" width="600" height="300"></canvas>
    </div>
</div>

<%@ include file="/WEB-INF/views/footer.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

<!-- Chart.js 렌더링 -->
<script>
    const priceHistory = [
        <c:forEach var="price" items="${priceHistory}" varStatus="loop">
            {
                date: "${fn:substring(price.register_date, 0, 10)}",
                price: ${price.price}
            }<c:if test="${!loop.last}">,</c:if>
        </c:forEach>
    ];

    const labels = priceHistory.map(p => p.date);
    const data = priceHistory.map(p => p.price);

    const maxPrice = Math.max(...data);
    const minPrice = Math.min(...data);
    const yesterday = data.length >= 2 ? data[data.length - 2] : null;
    const today = data[data.length - 1];
    const changeRate = yesterday ? ((today - yesterday) / yesterday * 100).toFixed(2) : null;

    window.onload = function () {
        const ctx = document.getElementById('priceChart').getContext('2d');
        new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels,
                datasets: [{
                    label: '가격 (원)',
                    data: data,
                    borderColor: 'rgb(75, 192, 192)',
                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                    borderWidth: 2,
                    fill: true,
                    tension: 0.3,
                    pointRadius: 4,
                    pointHoverRadius: 6
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: { display: true },
                    tooltip: {
                        callbacks: {
                            afterBody: function(context) {
                                const value = context[0].raw;
                                if (value === today && changeRate) {
                                    return `전일 대비: ${changeRate > 0 ? '+' : ''}${changeRate}%`;
                                }
                                return null;
                            }
                        }
                    },
                    annotation: {
                        annotations: {
                            maxLine: {
                                type: 'line',
                                yMin: maxPrice,
                                yMax: maxPrice,
                                borderColor: 'red',
                                borderWidth: 1,
                                label: {
                                    content: `최고가: ${maxPrice.toLocaleString()}원`,
                                    enabled: true,
                                    position: 'start'
                                }
                            },
                            minLine: {
                                type: 'line',
                                yMin: minPrice,
                                yMax: minPrice,
                                borderColor: 'blue',
                                borderWidth: 1,
                                label: {
                                    content: `최저가: ${minPrice.toLocaleString()}원`,
                                    enabled: true,
                                    position: 'start'
                                }
                            }
                        }
                    }
                },
                scales: {
                    y: {
                        beginAtZero: false
                    }
                }
            },
            plugins: [Chart.registry.getPlugin('annotation')]
        });
    };
</script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-annotation@1.4.0"></script>
</body>
</html>
