<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>template</title>
<style>
*
{
	margin:0;
	padding:0;
	font-size:10pt;
}

table
{
	border:0px;
	width:100%;
	padding:0;
	margin:0;
	border-spacing: 0px;
    border-collapse: collapse;
}

td 
{
	padding:0;
}

thead, tfoot
{
	width:100%;
	height:50px;
	background-color:cyan;
}

tbody
{
	width:100%;
}

#paramWrap
{
	background-color:yellow;
	height:80px;
	width:100%;
	margin:0;
}
</style>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">

<script>
// 로그아웃 버튼 클릭 및 세션 생성 여부 점검 : 템플릿으로 인해 로그아웃 했음에도 세션 종료 반영안되는 버그 패치
function checkAuthSessionAlive(session, logout_yn) {
	
	if (session != '' && logout_yn =='true') {
		// 조건적 화면 리프레쉬 : 1회
		if(!window.location.hash) {
	        window.location = window.location + '#loaded';
	        window.location.reload();
	    } //
	} //
}

// 로그아웃 했음에도 세션 종료 반영안되는 버그 패치
window.onload = function() {
	
	checkAuthSessionAlive("${sessionScope.LOGIN_SESS}", "${logout_yn}");
}
</script>

</head>
<body>

	<!-- 전송 인자 -->
	<div id="paramWrap">
	     로그아웃 버튼 클릭 여부 : ${logout_yn}<br> 
	      로그인 세션 : ${sessionScope.LOGIN_SESS}<br> 
	   contentPath : ${contentPath}	
	</div>
	
	<table>
		<!-- 상단 메뉴 -->
		<thead>
			<tr>
				<th>
					<%@ include file="header.jsp" %>
				</th>
			</tr>
		</thead>
		
		<!-- 컨텐츠 -->
		<tbody>
			<tr>
				<td>
					<jsp:include page="${empty contentPath ? '/login/login.html' : contentPath}"/>
				</td>	
			</tr>
		</tbody>
		
		<!-- 하단 -->
		<tfoot>
			<tr>
				<td align="center">
					&copy;javateam
				</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>