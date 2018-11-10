<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>상단 메뉴</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</head>
<body>
	
	<nav class="navbar navbar-expand-sm bg-primary navbar-dark">

	  <!-- Links -->
	  <ul class="navbar-nav">
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/index.do">Home</a>
	    </li>
	    
	    <!-- 로그인 인증 점검 -->
	    <c:if test="${empty sessionScope.LOGIN_SESS}">
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/loginForm.do">로그인</a>
	    </li>
	    </c:if>
	    
	    <c:if test="${not empty sessionScope.LOGIN_SESS}">
	    <li class="nav-item">
	      ${sessionScope.LOGIN_SESS}님이 로그인 중입니다.
	    </li>
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/logout.do">로그아웃</a>
	    </li>
	    </c:if>
	    
	    <li class="nav-item">
	      <a class="nav-link" href="${pageContext.request.contextPath}/viewAllPaging.do">전체회원보기</a>
	    </li>
	  </ul>
	  
	  <form class="form-inline" action="search.do">
	    
	    <!-- 회원명 검색 -->
	    <input type="hidden" id="searchKey" name="searchKey" value="name">
	    
		<input class="form-control mr-sm-2" 
			   type="text" 
			   id="searchWord" 
			   name="searchWord" 
			   placeholder="이름을 입력하십시오">
		<button class="btn btn-success" type="submit">회원 검색</button>
	  </form>
	
	</nav>
	
</body>
</html>