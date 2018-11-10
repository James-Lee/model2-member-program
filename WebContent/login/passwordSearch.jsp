<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>패쓰워드 검색(조회)</title>

<!-- jQuery/jQueryUI/bootstrap -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
	    
<!-- AngularJS lib -->
<script src="${pageContext.request.contextPath}/js/angularjs/1.7.5/angular.min.js"></script>

<script>
var app = angular.module('passwordSearchBody', []);

	app.controller('passwordSearchController', 
			       ['$scope', function ($scope) {
			    	   
		// 로그인 정보 전송 버튼 클릭시...	    	   
		$scope.passwordSearchCheck = function() {
			
			// 폼점검에 문제가 있다면...
			if ($scope.passwordSearchForm.$valid == false) {
				openpasswordSearchModal("정상적인 검색 데이터가 아닙니다.<br>다시 입력하십시오.");
				$scope.passwordSearchForm.id.focus(); // 아이디 필드에 입력 대기
			} else { // 전송시
				openpasswordSearchModal("패쓰워드 검색 정보를 전송합니다.");
			} 
			 
		} //	
		
		// 로그인 메뉴로 이동
		$scope.moveLogin = function() {
			
			// location.href = "${pageContext.request.contextPath}/login/login.html";
			location.href = "${pageContext.request.contextPath}/loginForm.do";
		} //
	}]);
</script>

<style>
*
{
	padding:0px;
}

div#wrap
{
	width:400px;
	margin:auto;
	padding:5px;
	text-indent:5px;
}

input[type="text"]
{
	height:30px;
}

[id$=msg] 
{
	/* background-color:yellow; */
	height:50px;
	color:red;
	text-indent:2em;
}
</style>

<!-- jQueryUI : 전송 메시지 팝업(modal) -->
<script>
function openpasswordSearchModal(msg) { 
	
	$(function(){
		
		$("#passwordSearchDialog").html(msg); // modal 내용
	    $("#passwordSearchDialog").dialog(); // modal 띄우기
		
	});
}	
</script>	        

</head>
<body ng-app="passwordSearchBody" 
	  ng-controller="passwordSearchController"> 
	  
	<!-- 아이디 검색 메시지 팝업 시작 -->
	<div id="passwordSearchDialog" title="검색 메시지">	  
	</div>
	<!-- 아이디 검색 메시지 팝업 끝 -->

	<div id="wrap">
	
		<form id="passwordSearchForm" 
			  name="passwordSearchForm" 
			  method="post" 
			  action="passwordSearchProc.do">

			<span>※ 아이디,이메일과 휴대폰 번호로 패쓰워드를 검색합니다.</span><br><br>	
			
			<label for="id">아이디 : </label>
				   <input type="text" 
						  id="id" 
						  name="id"
						  ng-model="id" 
						  size="30" 
						  maxlength="20"
						  required="true"
						  ng-pattern="/^[a-zA-Z]{1}\w{7,19}$/">
				
				    <br>
					<span id="id_msg" 
						  ng-show="passwordSearchForm.id.$error.pattern">
						올바른 아이디 형식이 아닙니다. 다시 입력하십시오.
					</span>	 	
					<br>
				
			<label for="email">이메일 : </label>
				   <input type="text" 
						  id="email" 
						  name="email"
						  ng-model="email" 
						  size="30" 
						  maxlength="50"
						  required="true"
						  ng-pattern="/^[a-z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/">
					
					<br>
					<span id="email_msg" 
						  ng-show="passwordSearchForm.email.$error.pattern">
						올바른 이메일 형식이 아닙니다. 다시 입력하십시오.
					</span>	  
				    <br>
				    
			<label for="mobile">휴대폰 : </label>
			       <input type="text" 
						  id="mobile" 
						  name="mobile" 
						  ng-model="mobile"
						  size="30" 
						  maxlength="50"
						  ng-pattern="/^01\d{1}-\d{3,4}-\d{4}$/">
				   
				   <span>
				   	  ex) 010-1234-5678	
				   </span>
				   
				   <br>	
			 	   <span id="mobile_msg" 
			 	   	     ng-show="passwordSearchForm.mobile.$error.pattern">
			 	   		올바른 휴대폰 형식이 아닙니다. 다시 입력하십시오.
			 	   </span>	
				   <br>
		
			<!-- 메뉴 -->
			<div style="width:400px; text-align:center">
			
				<input type="submit" 
					   id="passwordSearchBtn"
					   name="passwordSearchBtn"
					   value="패쓰워드 검색" 
					   class="btn btn-primary"
					   ng-click="passwordSearchCheck()">
					   
				<input type="button"
					   id="to_login_btn"
					   name="to_login_btn"
					   class="btn btn-primary"
					   value="로그인"
					   ng-click="moveLogin()">
			</div>	   	
		</form>
		
		<!-- 아이디 검색 결과 메시지 -->
		<div id="result_msg">
			${empty msg ? '' :   
			  msg != '해당되는 패쓰워드가 없습니다.' ?
			  '패쓰워드는 <b>'.concat(msg).concat('</b>입니다') : msg}
		</div>
		 
	</div>
	
</body>
</html>