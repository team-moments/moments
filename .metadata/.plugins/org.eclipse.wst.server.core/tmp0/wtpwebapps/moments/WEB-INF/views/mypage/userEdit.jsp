<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>회원정보 수정</title>
</head>
<body>
  <h2>${field} 수정</h2>
  
  <form action="${pageContext.request.contextPath}/mypage/userUpdate" method="post">
    <input type="hidden" name="field" value="${field}" />

    <c:choose>
      <c:when test="${field == 'name'}">
        <label>이름:</label>
        <input type="text" name="value" value="${user.name}" />
      </c:when>
      <c:when test="${field == 'phone'}">
        <label>연락처:</label>
        <input type="text" name="value" value="${user.phone}" />
      </c:when>
      <c:when test="${field == 'email'}">
        <label>이메일:</label>
        <input type="email" name="value" value="${user.email}" />
      </c:when>
      <c:when test="${field == 'password'}">
        <label>새 비밀번호:</label>
        <input type="password" name="value" />
      </c:when>
    </c:choose>

    <button type="submit">수정하기</button>
  </form>
</body>
</html>
