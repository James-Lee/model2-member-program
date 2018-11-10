<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<title>회원정보 검색</title>
<style>
div#search_pnl
{
/* 	background:yellow;  */
	float:left;
	width:500px;
	height:70px;
	display:flex;
	align-items:center;
	justify-content:center;
}	
</style>

<script>
// 검색 카테고리 초기화
function selectSearchKey(searchKey) {
	   
    var searchKeyTag = document.getElementById("searchKey");
    var optionLen = searchKeyTag.options.length;
   
    for (var i=0; i<optionLen; i++) {
   
       if (searchKey == searchKeyTag.options[i].value)
    	   searchKeyTag.selectedIndex = i;
   } // for
}

window.onload = function() {
	
	selectSearchKey("${searchKey}");
}

$(function() {
	
	// 전체 목록으로 돌아가기 버튼
	$('#totlist_btn').click(function(){
		$(location).attr('href', 
					"${pageContext.request.contextPath}/member/viewAllPaging.html");
	});
	
});
</script>
</head>
<body>

	<div id="search_pnl" class="form-group">
	
		<form class="form-inline" name="search" 
			  action="search.do" method="post">
			
			<select class="form-control mb-2 mr-sm-2" 
					id="searchKey" 
					name="searchKey">
				<option value="id" selected>아이디</option>
				<option value="name">이름</option>
			</select>
			
			<input type="text" 
				   class="form-control mb-2 mr-sm-2" 
				   id="searchWord" 
				   name="searchWord" 
				   size="30" 
				   placeholder="${(searchKey eq 'name') && (not empty searchWord) ? searchWord : ''}"
				   value="${(searchKey eq 'name') and (not empty searchWord) ? searchWord : ''}"/> 
				   <!-- 이름 검색 초기값 추가된 부분 -->
			
			<input type="submit" class="btn btn-primary mb-2 mr-sm-2" value="검색">
		
		</form>
		
		<!-- 전체목록으로 돌아가는 버튼 -->	
		<button id="totlist_btn" class="btn btn-primary mb-2 mr-sm-2">전체 목록</button>
	
	</div>

</body>
</html>