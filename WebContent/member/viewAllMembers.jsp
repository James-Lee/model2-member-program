<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>전체 회원 정보 보기</title>
<style>
body 
{
	font-size: 12px;
	color: #333;
}

table#memberAllView 
{
	border: 0px solid gray;
	border-width: 1px 0 0 1px;
	margin: 0 auto;
	border-collapse: collapse;
	/* <table cellspaing=0>과 동일 효과*/
}

td 
{
	border: 0px solid gray;
	border-width: 0 1px 1px 0;
	text-align: center;
}
</style>

<!-- 수정/삭제 처리 --> <!-- 변경된 경로에 유의 -->
<script src="${pageContext.request.contextPath}/js/custom/adminMenu.js"></script>
</head>
<body>

	<table id="memberAllView">

		<!-- 타이틀 시작 -->
		<tr>
			<td width="15">번호</td>
			<td width="90">아이디</td>
			<td width="50">이름</td>
			<td width="90">패쓰워드</td>
			<td width="20">성별</td>
			<td width="100">이메일</td>
			<td width="90">휴대폰</td>
			<td width="90">연락처</td>
			<td width="50">도로명<br>우편번호
			</td>
			<td width="200">도로명 주소</td>
			<td width="50">(구)우편번호</td>
			<td width="200">(구)주소</td>
			<td width="100">생년월일</td>
			<td width="100">가입일</td>
			<td>메뉴</td>
		</tr>
		<!-- 타이틀 끝 -->

		<!-- 전체 회원 정보 출력 시작 -->
		<c:forEach var="member" items="${members}" varStatus="st">
		<tr>
			<td>${st.count}</td>
			<td>${member.id}</td>
			<td>${member.name}</td>
			<td>${member.pw}</td>
			<td>${member.gender == 109 ? "남" : "여"}</td>
			<td>${member.email}</td>
			<td>${member.mobile}</td>
			<td>${member.phone}</td>
			<td>${member.zip1}</td>
			<td>${fn:replace(member.address1, "*", " ")}</td>
			<td>${fn:substring(member.zip2,0,3)}-${fn:substring(member.zip2,3,6)}</td>
			<td>${fn:replace(member.address2, "*", " ")}</td>
			<td><fmt:formatDate value="${member.birthday}" pattern="yyyy년 M월 d일" /></td>
			<td><fmt:formatDate value="${member.joindate}" pattern="yyyy년 M월 d일" /></td>
			<td><input type="button" value="수정"
                	   onClick="updateConfirm('${member.id}','close')"><br>
                <input type="button" value="삭제" 
                       onClick="deleteConfirm('${member.id}','close')">
                       <!-- 팝업으로 실행시 윈도우 자동 닫기 인자(close) 첨가 -->
		</tr>
		
		</c:forEach>
		<!-- 전체 회원 정보 출력 끝 -->

	</table>
	<!-- 전체 회원 보기 끝 -->
</body>
</html>