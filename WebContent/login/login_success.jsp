<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인 성공</title>
<!-- bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<style>
*
{
	padding:0px;
}
</style>
<script>
$(function(){
	
	$("#logout_btn").click(function(){
		
		alert("로그아웃");
		$(location).attr("href", "logout.do"); // 로그아웃 페이지로 이동
	});
});
</script>
</head>
<body>
<c:if test="${not empty page}">
	이동하려던 페이지 : <a href="${pageContext.request.contextPath}${page}">${page}</a><br><br>
</c:if>

${LOGIN_SESS}님 로그인 하셨습니다.<br><br>

<input type="button" id="logout_btn" class="btn btn-info" value="로그아웃">
</body>
</html>