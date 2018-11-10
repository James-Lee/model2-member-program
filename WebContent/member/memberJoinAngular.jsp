<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>회원가입(AngularJS Form Validation Version)</title>

<!-- 회원가입폼 CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberJoinAngular.css" type="text/css">
	  
<!-- 최상단 메시지 출력 관련 CSS -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/memberJoin2Angular.css" type="text/css">

<!-- Angular Form Validation CSS -->
<style>
	input.ng-invalid 
	{ /* 에러 출력시 필드 외곽선(적색) 표시  */
	   border: 5px solid red;
	}
	
	/* 에러 메시지 출력 팝업 */
	.msg-popup 
	{
		background-color:yellow;
		color:red;
		padding:10px;
		border-radius:7px;
		border:2px solid cyan;
		z-index:2;
	}
</style>

<!-- jQuery UI CSS : 캘린더(datePicker) -->	  	  
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/jQuery/ui/1.12.1/jquery-ui.min.css">

<!-- 주소검색 : daum -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/jQuery/3.3.1/jquery-3.3.1.min.js"></script>

<!-- jQuery UI -->
<script src="${pageContext.request.contextPath}/js/jQuery/ui/1.12.1/jquery-ui.min.js"></script>

<!-- jQuery UI : 캘린더(datePicker) 설정(한글 지원) -->	
<script src="${pageContext.request.contextPath}/js/custom/datepicker.ko.js"></script>

<!-- 폼 점검(form validation) -->
<script src="${pageContext.request.contextPath}/js/custom/memberJoinAngularValidator.js"></script>

<!-- AngularJS lib -->
<script src="${pageContext.request.contextPath}/js/angularjs/1.7.5/angular.min.js"></script>

<!-- AngularJS -->
<script>
var app = angular.module('memberJoinBody', []);

	app.controller('memberJoinAngularController', 
			       ['$scope', function ($scope) {
				
	    // TODO
	    // 아이디 중복 점검 : 버튼 방식
	    /* $scope.idBtnHandler = function() {
	        
	    	// 아이디 필드 점검 
	        if ($scope.memberJoin.id_fld.$valid == true) {
	          	sendId(); // 비동기적 폼 점검
	        } // 
	        
	    }; // */
	    
	    // 아이디 중복 점검 : ng-blur (비-버튼 방식)
	    $scope.idBtnHandlerForBlur = function() {
	    	
	    	// 아이디 필드 점검 
	        if ($scope.memberJoin.id_fld.$valid == true) {
	        	var result = sendIdForBlur();
	        	console.log("중복 여부 : "+ result);
	        	
	        	// 전송 버튼 상태 갱신
    		    if (result == 1) { // 아이디 중복되었을 때
    		 	   document.getElementById("submit_btn").disabled = true; // 전송 불가
    		    } else {
    			   document.getElementById("submit_btn").disabled = false; // 전송 가능
    		    } //
	        } //
	    	
	    } // 아이디 중복 점검
	    
	    // 이메일 중복 점검
	    $scope.emailCheckForBlur = function() {
	    	
	    	var result = sendEmailForBlur();
        	console.log("이메일 점검(가입 페이지) : "+ result);
        	
        	// 전송 버튼 상태 갱신
		    if (result == false) { // 사용 불가
		    	$scope.memberJoin.$valid = false;
		    } else {
		    	$scope.memberJoin.$valid = true;
		    } //
		    
		    // 전송 버튼 상태 갱신
		    if ($scope.memberJoin.$valid == false) {
		 	    document.getElementById("submit_btn").disabled = true; // 전송 불가
		    } else {
			    document.getElementById("submit_btn").disabled = false; // 전송 가능
		    }
	    	
	    } //
	
	}]);

// jQuery : 아이디 폼 점검과 중복 점검 메시지 충돌 방지을 위한 초기화
$(function() {
	
	$("#id_fld").focus(function(){
		$("#id_msg2").text(""); // 메시지 초기화
	}); //
});
</script>
</head>

<!-- ng-controller begin -->
<!-- ng-app : 작동 위한  필수 기재 -->
<body ng-app="memberJoinBody" 
	  ng-controller="memberJoinAngularController"> 
	  
   <!-- 테스트 모니터단 시작 -->
   
   <!-- 누산 플래그 표시 -->
   <div id="valid_flag"></div>
   
   <!-- 최상단 메시지 -->
   <div id="total_msg"></div><br>
  
   <!-- 테스트 모니터단 끝 -->
   
   <!-- 회원가입 시작 -->
   <div id="wrap"> 
   
        <form id="memberJoin" 
        	  name="memberJoin"
        	  method="post"
        	  action="${pageContext.request.contextPath}/join.do">
            
            <!-- 아이디 시작 -->
            <div id="id_title" class="fld_title">
                                  아이디
            </div>
            <!-- 아이디 끝 -->
            
            <div id="id_pnl" class="fld_val">
                
                <input type="text" 
                	   id="id_fld" 
                	   name="id_fld" 
                       maxlength="20" 
                       size="20" 
                       ng-model="id_fld"
                       ng-pattern="/^[a-zA-Z]{1}\w{7,19}$/"
                       required="true"
                       ng-blur="idBtnHandlerForBlur()">&nbsp; 
                       
                <!-- <input type="button" 
	                   value="아이디중복점검" 
	                   class="btn_style"
	                   ng-click="idBtnHandler()"> --> <!-- sendId() -->
	            
	           
	            <!-- 아이디 중복 여부 메시지 출력 -->        
	            <div id="id_msg"> 
	            
	                 <!-- 아이디 필드 점검 -->
	                 <div id="id_msg1" 
	                      ng-model="id_msg1"
	                 	  ng-show="memberJoin.id_fld.$error.pattern">
	            	 	아이디는 8~20자로 작성해야합니다.
	            	 </div>
	            	 
	            	 <!-- 보완 : 아이디 중복 메시지용 -->
	            	 <div id="id_msg2"
	            	 	  ng-model="id_msg2"
	            	 	  ng-hide="memberJoin.id_fld.$error.pattern"
	            	 	  style="position:relative; top:-6px">
	            	 </div> 
	            	 
	            </div>
	            
            </div>
            
            <!-- 패쓰워드 시작 -->
            <div id="pw_title" class="fld_title">
                        패쓰워드
            </div>
            <div class="fld_val">
                <input type="password" 
                	   id="pw_fld" 
                	   name="pw_fld" 
                       maxlength="20" 
                       size="25"
					   ng-model="pw_fld"
                       ng-pattern="/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,20}$/"
                       required="true">&nbsp;
                       
                <!-- 패쓰워드 점검 메시지 출력 -->        
	            <div id="pw_msg" 
	            	 ng-show="memberJoin.pw_fld.$error.pattern">
     				  특수/대소문자,숫자  각 1자 이상  8~20자로 입력하십시오
				</div>
	            
            </div> 
            <!-- 패쓰워드 끝 -->
            
            <!-- 이름 시작 -->
            <div id="name_pnl" class="fld_title">
                                   성명
            </div>
            <div class="fld_val">
                <input type="text" 
                	   id="name_fld" 
                	   name="name_fld"
                       maxlength="50"
					   ng-model="name_fld"
					   ng-pattern="/^[가-힣]{2,50}$/"
					   required="true">&nbsp;
                       
                <!-- 이름 점검 메시지 출력 -->        
	            <div id="name_msg" 
	            	 ng-show="memberJoin.name_fld.$error.pattern">
     				  이름은 한글로만 2~50자까지 가능합니다.
				</div> <!-- name -->
	                   
            </div>
            <!-- 이름 끝 -->
            
            <!-- 성별 시작 -->
            <div id="gender_pnl" class="fld_title">
                                  성별
            </div>
            <div class="fld_val">
		                남 <input type="radio" 
	                    id="gender"
	                    name="gender" 
	                    value="m"
	                    ng-model="gender"
	                    required="true">&nbsp;
		                    
		                여 <input type="radio" 
	                    id="gender"
	                    name="gender" 
	                    value="f"
	                    ng-model="gender"
	                    required="true">
					   		
               <!-- 성별 점검 메시지 출력 -->        
               <div id="gender_msg">
					<div ng-show="memberJoin.gender.$error.pattern">
                		성별을 입력하십시오.
                	</div>
			   </div> 
               
            </div>
            
            <!-- 이메일 시작 -->
            <div id="email_pnl" class="fld_title">
                               이메일
            </div>    
            <div id="email_val" class="fld_val">
                <input type="text" 
                	   id="email1" 
                       name="email1" 
                       size="15"
                       maxlength="20"
                       ng-model="email1"
                       ng-pattern="/^\w{2,20}$/"
                       ng-required="true"
                       onfocus="emailMsgInit()"
                       ng-blur="emailCheckForBlur()">
                @
                <select id="email2" 
                		name="email2"
                		ng-model="email2"
                        required="true"
                        onfocus="emailMsgInit()"
                        ng-blur="emailCheckForBlur()">
                    <option>naver.com</option>
                    <option>daum.net</option>
                    <option>empal.com</option>
                    <option>nate.com</option>
                    <option>paran.com</option>
                    <option>사용자 직접 입력</option>
                </select>
                
                <input type="text" 
                	   id="email3"
                       name="email3" 
                       maxlength="30"
                       size="15"
                       ng-model="email3"
                       onfocus="emailMsgInit()"
                       ng-blur="emailCheckForBlur()"> 
                       
               <!--  <input type="button" 
	                   value="이메일중복점검" 
	                   class="btn_style"
	                   onClick="sendEmail()"> -->
	                   
                <!-- 이메일 점검 메시지 출력 -->        
                <div id="email_msg">
                	 
                	<div ng-show="memberJoin.email1.$error.pattern"
                	     class="msg-popup">
                		이메일 아이디는 2자 이상 입력합니다
                	</div>
                	
                	<div ng-if="email2 == '사용자 직접 입력' && 
                	           (email3 == undefined || 
                	            email3.trim() == '')"
                	     class="msg-popup">
                		사용자 이메일 도메인을 입력합니다
                	</div>
     
                </div>      
                 
            </div>
            <!-- 이메일 끝 -->
            
            <!-- 휴대폰 시작 -->
            <div id="mobile_pnl" class="fld_title">
                                 휴대폰
            </div>
            <div class="fld_val">
                
                <select id="mobile1" 
                		name="mobile1"
                		ng-model="mobile1">
                    <option value="010" selected>010</option>
                    <option value="011">011</option>
                    <option value="016">016</option>
                    <option value="017">017</option>
                    <option value="018">018</option>
                    <option value="019">019</option>
                </select> -    
                <input type="text" 
                	   id="mobile2"
                       name="mobile2" 
                       maxlength="4"
                       size="6"
                       ng-model="mobile2"
                       required="true"
                       ng-pattern="/^\d{3,4}$/"> -   
                                
                <input type="text" 
                	   id="mobile3"
                       name="mobile3" 
                       maxlength="4"
                       size="6"
                       ng-model="mobile3"
                       required="true"
                       ng-pattern="/^\d{4}$/">   
                       
                <!-- 휴대폰 점검 메시지 출력 -->        
                <div id="mobile_msg">
                
                   <div ng-show="memberJoin.mobile2.$error.pattern || 
                   				 memberJoin.mobile3.$error.pattern">
					  휴대폰 나머지 자리를 입력하십시오.
				   </div> 
				   
				</div> 
				
            </div>
            <!-- 휴대폰 끝 -->
            
            <!-- 연락처 시작 -->
            <div id="phone_pnl" class="fld_title">
                                 연락처
            </div>
            <div class="fld_val">
                <select id="phone1" 
                		name="phone1"
                		ng-model="phone1">
                    <option value="02" selected>서울</option>
					<option value="031">경기</option>
					<option value="032">인천</option>
					<option value="033">강원</option>
					<option value="041">충남</option>
					<option value="042">대전</option>
					<option value="043">충북</option>
					<option value="044">세종</option>
					<option value="051">부산</option>
					<option value="052">울산</option>
					<option value="053">대구</option>
					<option value="054">경북</option>
					<option value="055">경남</option>
					<option value="061">전남</option>
					<option value="062">광주</option>
					<option value="063">전북</option>
					<option value="064">제주</option>
                </select> - 
                <input type="text" 
                	   id="phone2"
                       name="phone2" 
                       maxlength="4"
                       size="6"
                       ng-model="phone2"
                       required="true"
                       ng-pattern="/^\d{3,4}$/"> -            
                <input type="text" 
                	   id="phone3"
                       name="phone3" 
                       maxlength="4"
                       size="6"
                       ng-model="phone3"
                       required="true"
                       ng-pattern="/^\d{4}$/">     
                       
                <!-- 연락처 점검 메시지 출력 -->        
                <div id="phone_msg">

				   <div ng-show="memberJoin.phone2.$error.pattern || 
				   				 memberJoin.phone3.$error.pattern">
					 연락처 나머지 자리를 입력하십시오.
				   </div>	
				   
				</div>
				          
            </div>
            <!-- 연락처 끝 -->
            
            <!-- 도로명 우편번호 시작 -->
            <div id="zip1_title" class="fld_title">
                                  우편번호(도로)
            </div>
            <div class="fld_val">
                <input type="text" 
                	   id="zip1" 
                       name="zip1" 
                       size="6"
                       maxlength="5"
                       readonly> &nbsp;
                       
                <input type="button" 
                	   id="zip1btn" 
                       value="도로명 주소검색" 
                       class="btn_style"
                       onClick="road_Postcode()">
            </div>
            <!-- 도로명 우편번호 끝 -->
            
            <!-- 도로명 주소 시작 -->
            <div id="road_address_title" class="fld_title2">
                        도로명 주소
            </div>
            <div class="fld_val2">
                <input type="text" 
                	   id="roadAddr1"
                       name="roadAddr1"    
                       maxlength="200"
                       size="75"
                       readonly>
                       <br><br>
                       
                <input type="text" 
                	   id="roadAddr2"
                       name="roadAddr2"    
                       maxlength="100"
                       size="75">      
            </div>
            <!-- 도로명 주소 끝 -->
            
            <!-- 구 우편번호 시작 -->
            <div id="zip2_title" class="fld_title">
                        우편번호(구)
            </div>
            <div class="fld_val">
                <input type="text" 
                	   id="zip2_1" 
                       name="zip2_1" 
                       size="4"
                       maxlength="3"
                       readonly>-
                <input type="text" 
                	   id="zip2_2" 
                       name="zip2_2" 
                       size="4"
                       maxlength="3"
                       readonly> &nbsp;
                       
                <input type="button" 
                	   id="zip2btn" 
                       value="주소검색" 
                       class="btn_style"
                       onClick="old_Postcode()">
                       
            </div>
            <!-- 구 우편번호 끝 -->
            
            <!-- (구)주소 시작 -->
            <div id="old_address_title" class="fld_title2">
                        주소(구)
            </div>
            <div class="fld_val2">
                <input type="text" 
                	   id="oldAddr1"
                       name="oldAddr1"    
                       maxlength="200"
                       size="75"
                       readonly>
                       <br><br>
                <input type="text" 
                	   id="oldAddr2"
                       name="oldAddr2"    
                       maxlength="100"
                       size="75">      
            </div>
            <!-- (구)주소 끝 -->
            
            <!-- 생년월일 시작 -->
            <div id="birth_title" class="fld_title">
                        생년월일
            </div>
            <div class="fld_val">
                 <input type="text" 
                 		id="birthday"
                        name="birthday"
                        ng-model="birthday"
                        maxlength="10"
                        size="12"
                        required="true"
                        ng-pattern="/^\d{4}-\d{1,2}-\d{1,2}$/">   
                        
                  <!-- 생년월일 점검 메시지 출력 -->        
                  <div id="birthday_msg">

				   <div ng-show="memberJoin.birthday.$error.pattern">
					  생년월일을 입력하십시오.
				   </div>	
				   
				  </div>       
                        
            </div>
            <!-- 생년월일 끝 -->
            
            <!-- 가입일 시작 -->
            <div id="join_title" class="fld_title">
                         가입일
            </div>
            <div class="fld_val">
                <input type="text" 
                	   id="joindate"
                       name="joindate"
                       maxlength="10"
                       size="12"
                       readonly>    
            </div>
            <!-- 가입일 끝 -->
            
            <!-- 가입/취소 버튼 시작 -->
            <div id="join_cancel_pnl" class="btn_fld">
               
                <!-- 폼점검 미완료시(memberJoin.$invalid) 버튼 비활성화(disabled) -->
                <button type="submit"
                	    id="submit_btn"
                	    name="submit_btn"    	    
                	    ng-disabled="memberJoin.$invalid"> 
               	    회원가입
                </button>	    
                &nbsp;
                <button type="reset"
                	    id="cancel_btn"
                	    name="cancel_btn">
              	    가입취소
            	</button>    
			</div>
            <!-- 가입/취소 버튼 끝 -->            
        
        </form>
    </div> 
    <!-- 회원가입 끝 -->
 
</body>
</html>