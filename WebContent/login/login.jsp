<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>로그인</title>

<!-- jQuery/jQueryUI -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- <link rel="stylesheet" href="/resources/demos/style.css"> -->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- <link rel="stylesheet" type="text/css" href="../css/login.css"> -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
	    
<!-- AngularJS lib -->
<!-- <script src="../js/angularjs/1.7.5/angular.min.js"></script> -->
<script src="${pageContext.request.contextPath}/js/angularjs/1.7.5/angular.min.js"></script>

<script>
var app = angular.module('memberLoginBody', []);

	app.controller('memberLoginController', 
			       ['$scope', function ($scope) {
			    	   
		// 로그인 정보 전송 버튼 클릭시...	    	   
		$scope.loginCheck = function() {
			
			// 폼점검에 문제가 있다면...
			if ($scope.login.$valid == false) {
				openLoginModal("정상적인 로그인 데이터가 아닙니다.<br>다시 입력하십시오.");
				$scope.login.id.focus(); // 아이디 필드에 입력 대기
			} else { // 전송시
				openLoginModal("로그인 정보를 전송합니다.");
			} 
			 
		} //			    	   
	}]);
</script>

<!-- jQueryUI : 전송 메시지 팝업(modal) -->
<script>
function openLoginModal(msg) { 
	
	$(function(){
		
		$("#loginDialog").html(msg); // modal 내용
	    $("#loginDialog").dialog(); // modal 띄우기
		
	});
}	
</script>	        

</style>
</head>
<body ng-app="memberLoginBody" 
	  ng-controller="memberLoginController"> 

	<!-- 로그인 전체 프레임 시작 -->
	<div id="login_wrap" style="margin:auto; width:400px;">
	 
	    <!-- 로그인 폼필드 시작 -->
	    <form id="login" 
	    	  name="login" 
	    	  method="post" action="${pageContext.request.contextPath}/login.do">
	   
			<!-- 로그인 아이디 필드 시작 -->
			<div id="id_pnl">
				<input type="text" 
					   id="id" 
					   name="id"
					   class="input_fld"
					   maxlength="20"
					   placeholder="아이디"
					   ng-model="id"
					   ng-pattern="/^[a-zA-Z]{1}\w{7,19}$/"
                       required="true">
                       
				<!-- 아이디 점검 메시지 출력 -->        
	            <div id="id_msg">

					 <!-- 아이디 필드 점검 -->
	                 <div id="id_msg1" 
	                      ng-model="id_msg1"
	                 	  ng-show="login.id.$error.pattern">
	            	 	아이디는 8~20자로 작성해야합니다.
	            	 </div>
	            	 
				</div>	
				 
			</div>
			<!-- 로그인 아이디 필드 끝 -->
			
			<!-- 로그인 패쓰워드 필드 시작 -->
			<div id="pw_pnl">
				<input type="password" 
					   id="pw" 
					   name="pw"
					   class="input_fld"
					   maxlength="20"
					   placeholder="패쓰워드"
					   ng-model="pw"
                       ng-pattern="/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,20}$/"
                       required="true">
			</div>
			
			<!-- 패쓰워드 점검 메시지 출력 -->        
            <div id="pw_msg" 
            	 ng-show="login.pw.$error.pattern">
    				  특수/대소문자,숫자  각 1자 이상  8~20자로 입력하십시오
			</div>
			<!-- 로그인 패쓰워드 필드 끝 -->
			
			<!-- 로그인 버튼 필드 시작 -->
			<div id="login_btn_pnl">
			
				<!-- 로그인 버튼 시작 -->
				<input type="submit" 
					   id="login_btn"
					   name="login_btn"
					   value="로 그 인"
					   ng-click="loginCheck()">
					   <!-- ng-disabled="login.$invalid"> -->
				<!-- 로그인 버튼 끝 -->
				
			</div>
		
		</form>
		<!-- 로그인 폼필드 끝 -->
			
<!-- 아이디 찾기/비밀번호 찾기/회원가입 버튼 시작 -->
<div id="etc_btn_pnl">

	<a href="${pageContext.request.contextPath}/idSearch.do" class="etc_btn">아이디 찾기</a>&nbsp;
	<a href="${pageContext.request.contextPath}/passwordSearch.do" class="etc_btn">비밀번호 찾기</a>&nbsp;
	<a href="${pageContext.request.contextPath}/member/memberJoinAngular.html" class="etc_btn">회원가입</a>&nbsp;

</div>
<!-- 아이디 찾기/비밀번호 찾기/회원가입 버튼 끝 -->
	
	</div>
	<!-- 로그인 전체 프레임 끝 -->
	
	<!-- 최종 로그인 메시지 팝업 시작 -->
	<div id="loginDialog" title="로그인 메시지">	  
	</div>
	<!-- 최종 로그인 메시지 팝업 끝 -->
	
</body>
</html>