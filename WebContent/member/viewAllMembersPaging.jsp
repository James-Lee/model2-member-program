<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>전체 회원 정보 보기</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<style>
*
{
	font-size: 12px;
	color: #333;
}

/* table#memberAllView {
	border: 0px solid gray;
	border-width: 1px 0 0 1px;
	margin: 0 auto;
	border-collapse: collapse;
} */
/* <table cellspaing=0>과 동일 효과*/

td 
{
	text-indent: 0.5em; /* 들여쓰기 */
}  

/* 긴 주소 줄여서 보여주기 */
.address_reduce_styl
{
	width:100px; 
	white-space:nowrap; 
	overflow:hidden; 
	text-overflow:ellipsis; /* 크기보다 긴 글자 부분 "..."" 처리 */
}
</style>

<!-- 수정/삭제 처리 --> <!-- 변경된 경로에 유의 -->
<script src="./js/custom/adminMenu.js"></script>

<script>
$(document).ready(function(){
    
	$('[data-toggle="tooltip"]').tooltip(); // rollover-popup
	$('[data-toggle="popover"]').popover(); // click-popup
    
});
</script>
</head>
<body>

	<!-- 페이지 관련 변수들 -->
	<div id="paging_var">
		로그인 인증 세션 정보 : ${sessionScope.LOGIN_SESS}<br> 
		searchKey : ${searchKey}<br>
	    searchWord : ${searchWord}<br>
	    <hr>
		현재 페이지(curPage) : <b>${pagingVO.curPage}</b><br>
		총 페이지(totPage) : <b>${pagingVO.totPage}</b><br>
		페이지당 행수(rowPerPage) : <b>${pagingVO.rowPerPage}</b><br>
		할당 인원수(members.size()) : <b>${members.size()}</b><br> 
		시작 페이지(startPage) : <b>${pagingVO.startPage}</b><br>
		마지막 페이지(endPage) : <b>${pagingVO.endPage}</b><br>
		이전 페이지(prePage) : <b>${pagingVO.prePage}</b><br>
		다음 페이지(nextPage) : <b>${pagingVO.nextPage}</b><br>
		총 인원수(memberNum) : <b>${memberNum}</b><br>
	</div>
	
	 <%-- 회원 정보가 없을 경우 --%>
	<c:if test="${members.size() == 0}">
		<div style="text-align:center; font-size:12px">
			회원 정보 없음
		</div>
	</c:if>
	
	<c:if test="${memberNum != 0}">

		<table id="memberAllView" class="table table-sm table-striped table-hover">
	
			<!-- 타이틀 시작 -->
		    <thead class="thead-dark">
			<tr align="center">
				<th width="50">번호</td>
				<th width="90">아이디</td>
				<th width="120">이름</td>
				<th width="90">패쓰워드</td>
				<th width="50">성별</td>
				<th width="100">이메일</td>
				<th>휴대폰</td>
				<th>연락처</td>
				<th colspan="2">도로명 주소</td>
				<th colspan="2">(구) 주소</td>
				<th width="150">생년월일</td>
				<th width="150">가입일</td>
				<th>메뉴</td>
			</tr>
			</thead>
			<!-- 타이틀 끝 -->
	
			<!-- 전체 회원 정보 출력 시작 -->
			<tbody>
			<c:forEach var="member" items="${members}" varStatus="st">
			<tr>
				<%-- <td>${st.count}</td> --%>
				<td align="center">${(pagingVO.curPage-1)*pagingVO.rowPerPage + st.count}</td>
				<td>${member.id}</td>
				<td>${member.name}</td>
				<td>${member.pw}</td>
				<td>${member.gender == 109 ? "남" : "여"}</td>
				<td>${member.email}</td>
				<td>${member.mobile}</td>
				<td>${member.phone}</td>
				<td>${member.zip1}</td>
				<%-- <td>${fn:replace(member.address1, "*", " ")}</td> --%>
				<td width="200"> 
					<!-- 긴 주소 일부만 보이도록 조치 : 클릭(click)하면 팝업으로 보이도록 조치 -->
					<c:set var="address1Crop" 
						   value="${fn:replace(member.address1, \"*\", \" \")}" />
					<a href="#" data-toggle="popover" 
								data-content="${address1Crop}">
						${fn:substring(address1Crop,0,8)}..
					</a>
				</td>
				
				<td>${fn:substring(member.zip2,0,3)}-${fn:substring(member.zip2,3,6)}</td>
				
				<%-- <td>${fn:replace(member.address2, "*", " ")}</td> --%>
				<td width="200">
					<!-- 긴 주소 일부만 보이도록 조치 : 롤오버(rollover)하면 팝업으로 보이도록 조치 -->
					<div class="address_reduce_styl">
						<a href="#" data-toggle="tooltip" 
									data-placement="left" 
									title="${fn:replace(member.address1, '*', ' ')}">
							${fn:replace(member.address1, "*", " ")}
						</a>
					</div>
				</td>	
				<td><fmt:formatDate value="${member.birthday}" pattern="yyyy년 M월 d일" /></td>
				<td><fmt:formatDate value="${member.joindate}" pattern="yyyy년 M월 d일" /></td>
				<td class="btn-group btn-group-sm">
					<input class="btn btn-info"
						   type="button" value="수정"
	                	   onClick="updateConfirm('${member.id}','close')"><br>
	                <input class="btn btn-info" 
	                	   type="button" value="삭제" 
	                       onClick="deleteConfirm('${member.id}','close')">
	                       <!-- 팝업으로 실행시 윈도우 자동 닫기 인자(close) 첨가 -->
			</tr>
			
			</c:forEach>
			</tbody>
			<!-- 전체 회원 정보 출력 끝 -->
	
		</table>
		
	</c:if>	
	<!-- 전체 회원 보기 끝 -->
	
	<br>
	<!-- 회원정보 검색 기능 시작 -->
	
	<%@ include file="search.jsp" %>
	
	<!-- 회원정보 검색 기능 끝 --->
	
	<!-------- paging 시작 -------->
	<!-- 이름 검색 페이징 추가 -->
	
	<!-- 전체 조회 -->
	<c:if test="${empty param.searchKey}">
		<%@ include file="paging.jsp" %> <!-- 3 페이지씩 전개 -->
	</c:if>
	
	<!-- 회원명 조회 -->
	<c:if test="${param.searchKey eq 'name'}">
		<%@ include file="pagingByName.jsp" %> <!-- 3 페이지씩 전개 -->
	</c:if>
	<!-------- paging 끝 --------->
	
</body>
</html>