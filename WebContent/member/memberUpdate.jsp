<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">

<!-- CSS -->
<link rel="stylesheet" 
	  href="${pageContext.request.contextPath}/css/memberJoinAngular.css" type="text/css">
	  
<link rel="stylesheet" 
	  href="${pageContext.request.contextPath}/css/memberJoin2Angular.css" type="text/css">	  
	  
<link rel="stylesheet" 
	  href="${pageContext.request.contextPath}/css/memberUpdate.css" type="text/css">	  
	  
<!-- 최상단 메시지 출력 관련 CSS -->
<link rel="stylesheet" 
	  href="${pageContext.request.contextPath}/css/memberJoin2Angular.css" type="text/css">

<!-- Angular Form Validation CSS -->
<style>
 	input.ng-invalid 
	{ /* 에러 출력시 필드 외곽선(적색) 표시  */
	   border: 1px solid red;
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
<link rel="stylesheet" 
	  href="${pageContext.request.contextPath}/js/jQuery/ui/1.12.1/jquery-ui.min.css">

<!-- 주소검색 : daum -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/js/jQuery/3.3.1/jquery-3.3.1.min.js"></script>

<!-- jQuery UI -->
<script src="${pageContext.request.contextPath}/js/jQuery/ui/1.12.1/jquery-ui.min.js"></script>

<!-- jQuery UI : 캘린더(datePicker) 설정(한글 지원) -->	
<script src="${pageContext.request.contextPath}/js/custom/datepicker.ko.js"></script>

<!-- 폼 점검(form validation) -->
<script src="${pageContext.request.contextPath}/js/custom/memberUpdateAngularValidator.js"></script>

<!-- AngularJS lib -->
<script src="${pageContext.request.contextPath}/js/angularjs/1.7.5/angular.min.js"></script>

<!-- javascript -->
<script>
function selectMobile(mobile) {
	   
    var mobile1 = document.getElementById("mobile1");
    var optionLen = mobile1.options.length;
   
    for (var i=0; i<optionLen; i++) {
   
       if (mobile == mobile1.options[i].value)
           mobile1.selectedIndex = i;
   } // for
}
 
function selectPhone(phone) {
   
   // 작성 시작
   var phone1 = document.getElementById("phone1");
   var optionLen = phone1.options.length;
   
    for (var i=0; i<optionLen; i++) {
   
       if (phone == phone1.options[i].value)
           phone1.selectedIndex = i;
   } // for
   // 작성 끝
}
 
// 이메일 설정
function selectEmail(emailDomain) {
   
   var emailPortal = document.getElementById("email2");
   var emailUserDomain = document.getElementById("email3");
   var optionLen = emailPortal.options.length;
   
   // 도메인 점검 : 이메일 포털 도메인일 경우
   for (var i=0; i<optionLen-1; i++) { // "사용자 직접 입력 항목"은 제외 6-1 = 총 5항목
       
       if (emailDomain.trim() == emailPortal.options[i].value) {
           
           emailPortal.selectedIndex = i;
           emailUserDomain.value = "";
           break;
       }
       
   } // for        
   
   // 사용자 도메인일 경우
   if (emailDomain.trim() != emailPortal.options[i].value) {
 
       emailPortal.selectedIndex = optionLen-1; // 사용자 직접 입력 항목 설정
       emailUserDomain.value = emailDomain; // 사용자 메일 도메인으로 설정
   }    
}
 
// 성별 설정
function selectGender(gender) {
   
   var genderChecked = document.all["gender_sub"];
   var len = genderChecked.length;
   
   for (var i=0; i<len; i++) {
       
       if (gender.trim() == genderChecked[i].value)
           genderChecked[i].checked = true;
   } // for
}
 
window.onload = function(e) {
   
   selectMobile("${mobile[0]}"); // 휴대폰 앞번호 설정
   selectPhone("${phone[0]}");   // 연락처 앞번호 설정
   selectEmail("${email[1]}");   // 이메일 설정
   selectGender("${member.gender}");     // 성별 설정
   
};
</script>

<!-- AngularJS -->
<script>
var app = angular.module('memberUpdateBody', []);

	app.controller('memberUpdateAngularController', 
			       ['$scope', function ($scope) {
      			    	   
	   // 신규 패쓰워드 폼점검 플래그
	   $scope.new_pw_flag = false; // 신규 패쓰워드 유효성 점검 필드
	   $scope.new_pw_key_flag = false; // 신규 패쓰워드 키도브 입력 시작 여부 필드
	   
  	   // 신규 패쓰워드 점검
   	   $scope.checkNewPassword = function() {
  		   
   		   $scope.new_pw_key_flag = true;
		   
   		   // console.log($scope.new_pw_fld);
  		   var regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,20}$/; 
  		   
  		   if (regex.test($scope.new_pw_fld)) {  	
  			 	$scope.new_pw_flag = true;  	
  			 	$scope.memberUpdate.$valid = true;
  			 	console.log("$scope.memberUpdate.$valid : "+$scope.memberUpdate.$valid);
   		   } else {
   				$scope.new_pw_flag = false;
   				$scope.memberUpdate.$valid = false;
   				console.log("$scope.memberUpdate.$valid : "+$scope.memberUpdate.$valid);
   		   } //
   		   
   		   // 전송 버튼 상태 갱신
   		   if ($scope.memberUpdate.$valid == false) {
   			   document.getElementById("submit_btn").disabled = true; // 전송 불가
   		   } else {
   			   document.getElementById("submit_btn").disabled = false; // 전송 가능
   		   }
   		   
   	    } // 신규 패쓰워드 점검 끝
   	    
   	    // 상세주소에 대한 폼점검(선택적 점검)
   	    // 기본주소를 입력하지 않았을 때는 무점검 기본주소를 입력하였을 경우는 점검
   	    $scope.checkAddress = function() {

   	   	 	// 도로명 주소 점검
   	   	    if (memberUpdate.roadAddr1.value == "" && 
   	   	    	memberUpdate.roadAddr2.value != "") {
   	   	    	
   	   	    	console.log("도로명 기본 주소를 입력하세요.");
   	   	    	
   	   	 		$scope.memberUpdate.$invalid = true;
   	   	    	document.getElementById("roadAddr_msg").innerText = "기본 주소를 입력하세요.";
   	   	    	
   	   	    } else if (memberUpdate.roadAddr1.value != "" && 
   	   	   	    	   memberUpdate.roadAddr2.value == "") {
   	   	    	
   	   	    	console.log("도로명 상세 주소를 입력하세요.");
   	   	    	
   	   	 		$scope.memberUpdate.$invalid = true;
   	   	    	document.getElementById("roadAddr_msg").innerText = "상세 주소를 입력하세요.";
   	   	    	
   	   	    } else { // 정상적인 경우
   	   	    	
   	   	  		console.log("도로명 주소 폼점검됨")
   	   	  		$scope.memberUpdate.$valid = true;
   	   	  		document.getElementById("roadAddr_msg").innerText = "";
   	   	    } // 
   	   	    
   	   	    // 구 주소 점검
   	   	    if (memberUpdate.oldAddr1.value == "" && 
   	   	    	memberUpdate.oldAddr2.value != "") {
   	   	    	
   	   	    	console.log("구 주소 기본 주소를 입력하세요.");
   	   	    	
   	   	 		$scope.memberUpdate.$invalid = true;
   	   	    	document.getElementById("oldAddr_msg").innerText = "기본 주소를 입력하세요.";
   	   	    	
   	   	    } else if (memberUpdate.oldAddr1.value != "" && 
   	   	   	    	   memberUpdate.oldAddr2.value == "") {
   	   	    	
   	   	    	console.log(" 구주소 상세 주소를 입력하세요.");
   	   	    	
   	   	  		$scope.memberUpdate.$invalid = true;
   	   	    	document.getElementById("oldAddr_msg").innerText = "상세 주소를 입력하세요.";
   	   	    	
   	   	    } else { // 정상적인 경우
   	   	    	
   	   	    	console.log("구 주소 폼점검됨")
   	   	  		$scope.memberUpdate.$valid = true;
   	   	  		document.getElementById("oldAddr_msg").innerText = "";
   	   	    } // 

   	    } // 상세주소에 대한 폼점검(선택적 점검) (끝)
				
	    // TODO
	    // 초기값 : 폼필드에 ng-model을 사용할 경우 
	    // 별도 초기화하지 않으면 폼필드에 출력 안됨.
	    $scope.name_fld="${member.name}";
	    $scope.email1="${email[0]}";
	    $scope.mobile2="${mobile[1]}";
	    $scope.mobile3="${mobile[2]}";
	    $scope.phone2="${phone[1]}";
	    $scope.phone3="${phone[2]}";
	    $scope.roadAddr1="${address1[0]}";
	    $scope.roadAddr2="${addressTemp1}";
	    $scope.oldAddr1="${address2[0]}";
	    $scope.oldAddr2="${addressTemp2}";
		
	}]);
</script>
</head>

<!-- ng-controller begin -->
<!-- ng-app : 작동 위한  필수 기재 -->
<body ng-app="memberUpdateBody" 
	  ng-controller="memberUpdateAngularController">
	  
   <!-- 테스트 모니터단 시작 -->

   <!-- 최상단 메시지 -->
   <div id="total_msg">
	   <!-- 인자 출력 테스트 시작 -->		   
	   <div style="background:yellow; width:622px;">
	        기존 회원정보 = 세션(session) : ${sessionScope.LEGACY_MEMBER_INFO}<br><br>
	      member : ${member}<br><br>
	      
	      mobile : ${mobile[0]}-${mobile[1]}-${mobile[2]}<br>
          phone : ${phone[0]}-${phone[1]}-${phone[2]}<br>
          email : ${email[0]}@${email[1]}<br>
          zip21 : ${zip21}<br>
          zip22 : ${zip22}<br>
          address1 : ${address1[0]}&nbsp;${address1[1]}<br>
          address2 : ${address2[0]}&nbsp;${address2[1]}<br>
          addressTemp1 : ${addressTemp1}<br>
          addressTemp2 : ${addressTemp2}<br>
	   </div>
	   <!-- 인자 출력 테스트 끝 -->
   </div>
  
   <!-- 테스트 모니터단 끝 -->
   
   <!-- 회원 정보 수정 시작 -->
   <div id="wrapUpdate"> 
   
        <form id="memberUpdate" 
        	  name="memberUpdate"
        	  method="post"
        	  action="${pageContext.request.contextPath}/update.do">
            
            <!-- 아이디 시작 -->
            <div id="id_title" class="fld_title">
                                  아이디
            </div>
            <!-- 아이디 끝 -->
            
            <div id="id_pnl" class="fld_val">
                
                <input type="text" 
                	   id="id_fld" 
                	   name="id_fld" 
                       size="20" 
                       readonly
                       value="${member.id}">&nbsp; 
            </div>
            
            <!-- 기존 패쓰워드 시작 -->
            <div id="pw_title" class="fld_title">
               	기존 패쓰워드
            </div>
            <div class="fld_val">
            
                <input type="password" 
                	   id="pw_fld" 
                	   name="pw_fld" 
                       size="25"
					   readonly
					   value="${member.pw}">&nbsp;
            </div> 
            <!-- 기존 패쓰워드 끝 -->
            
            <!-- 패쓰워드 시작 -->
            <div id="pw_title" class="fld_title">
                              신규 패쓰워드
            </div>
            <div class="fld_val">
            
                <input type="password" 
                	   id="new_pw_fld" 
                	   name="new_pw_fld" 
                       maxlength="20" 
                       size="25"
					   ng-model="new_pw_fld"
					   ng-blur="new_pw_key_flag = false"
					   ng-keyup="checkNewPassword()">&nbsp; 
                       
                <!-- 신규 패쓰워드 점검 메시지 출력 -->    
                {{$scope.new_pw_flag}}    
	           <div id="pw_msg" 
	            	ng-show="new_pw_key_flag == true && new_pw_flag == false">
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
					   required="true"
					   value="${member.name}">&nbsp;
                       
                <!-- 이름 점검 메시지 출력 -->        
	            <div id="name_msg" 
	            	 ng-show="memberUpdate.name_fld.$error.pattern">
     				  이름은 한글로만 2~50자까지 가능합니다.
				</div> <!-- name -->
	                   
            </div>
            <!-- 이름 끝 -->
            
            <!-- 성별 시작 -->
            <div id="gender_pnl" class="fld_title">
                                  성별
            </div>
            <div class="fld_val">
            
                <!-- 성별 전송용 필드 -->
                <input type="hidden" 
                	   id="gender" 
                	   name="gender" 
                	   value="${member.gender}">
		        
		                남 <input type="radio" 
	                    id="gender_sub"
	                    name="gender_sub" 
	                    value="m"
	                    disabled>&nbsp; <!-- ng-model="gender" -->
		                    
		                여 <input type="radio" 
	                    id="gender_sub"
	                    name="gender_sub" 
	                    value="f"
	                    disabled> <!--  ng-model="gender" -->
					   		
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
                       value="${email[0]}"
                       ng-pattern="/^\w{2,20}$/"
                       placeholder="${email[0]}"
                       required="true">
                @
                <select id="email2" 
                		name="email2"
                        required="true"
                        onChange="memberUpdate.email3.value = ''">
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
                       value="${email3}"> 
                       
                <input type="button" 
	                   value="이메일중복점검" 
	                   class="btn_style"
	                   onClick="sendEmail()">
	                   
                <!-- 이메일 점검 메시지 출력 -->        
                <div id="email_msg">
                	 
                	<div ng-show="memberUpdate.email1.$error.pattern"
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
                <!-- select => ng-model 초기값 선택 안됨 -->
                <select id="mobile1" 
                		name="mobile1"> <!-- ng-model="mobile1" -->
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
                       ng-pattern="/^\d{3,4}$/"
                       placeholder="${mobile[1]}"
                       value="${mobile[1]}"> -                          
                                
                <input type="text" 
                	   id="mobile3"
                       name="mobile3" 
                       maxlength="4"
                       size="6"
                       ng-model="mobile3"
                       required="true"
                       ng-pattern="/^\d{4}$/"
                       placeholder="${mobile[2]}"
                       value="${mobile[2]}">   
                       
                <!-- 휴대폰 점검 메시지 출력 -->        
                <div id="mobile_msg">
                
                   <div ng-show="memberUpdate.mobile2.$error.pattern || 
                   				 memberUpdate.mobile3.$error.pattern">
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
                <!-- select => ng-model 초기값 선택 안됨 -->
                <select id="phone1" 
                		name="phone1"> <!-- ng-model="phone1" -->
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
                       placeholder="${phone[1]}"
                       value="${phone[1]}"
                       ng-pattern="/^\d{3,4}$/"> -            
                <input type="text" 
                	   id="phone3"
                       name="phone3" 
                       maxlength="4"
                       size="6"
                       ng-model="phone3"
                       required="true"
                       placeholder="${phone[2]}"
                       value="${phone[2]}"
                       ng-pattern="/^\d{4}$/">     
                       
                <!-- 연락처 점검 메시지 출력 -->        
                <div id="phone_msg">

				   <div ng-show="memberUpdate.phone2.$error.pattern || 
				   				 memberUpdate.phone3.$error.pattern">
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
                       readonly
                       value="${member.zip1}"> &nbsp;
                       
                <input type="button" 
                	   id="zip1btn" 
                       value="도로명 주소검색" 
                       class="btn_style"
                       onClick="road_Postcode()">
                       
                <!-- 도로명 주소 폼점검 메시징 시작-->               
                <div id="roadAddr_msg"></div>
                <!-- 도로명 주소 폼점검 메시징 끝 -->
                       
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
                       ng-model="roadAddr1"    
                       maxlength="200"
                       size="75"
                       readonly
                       value="${address1[0]}">
                       <br><br>
                       
                <input type="text" 
                	   id="roadAddr2"
                       name="roadAddr2"    
                       ng-model="roadAddr2"
                       maxlength="100"
                       size="75"
                       value="${addressTemp1}"
                       ="checkAddress()">
                
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
                       readonly
                       value="${zip21}">-
                <input type="text" 
                	   id="zip2_2" 
                       name="zip2_2" 
                       size="4"
                       maxlength="3"
                       readonly
                       value="${zip22}">&nbsp;
                       
                <input type="button" 
                	   id="zip2btn" 
                       value="주소검색" 
                       class="btn_style"
                       onClick="old_Postcode()">
                       
                <!-- 구 주소 폼점검 메시징 시작-->               
                <div id="oldAddr_msg"></div>
                <!-- 구 주소 폼점검 메시징 끝 -->   
                       
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
                       ng-model="oldAddr1" 
                       maxlength="200"
                       size="75"
                       value="${address2[0]}"
                       readonly>
                       <br><br>
                <input type="text" 
                	   id="oldAddr2"
                       name="oldAddr2"    
                       ng-model="oldAddr2"
                       maxlength="100"
                       size="75"
                       value="${addressTemp2}"
                       ng-blur="checkAddress()">
                       
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
                        maxlength="10"
                        size="12"
                        value="${member.birthday}"
                        readonly>    
                        
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
                       value="${member.joindate}"
                       readonly>    
            </div>
            <!-- 가입일 끝 -->
            
            <!-- 가입/취소 버튼 시작 -->
            <div id="join_cancel_pnl" class="btn_fld">
               
                <!-- 폼점검 미완료시(memberUpdate.$invalid) 버튼 비활성화(disabled) -->
                <button type="submit"
                	    id="submit_btn"
                	    name="submit_btn"    	    
                	    ng-disabled="memberUpdate.$invalid"> 
               	    회원 정보 수정
                </button>	    
                &nbsp;
                <button type="reset"
                	    id="cancel_btn"
                	    name="cancel_btn">
              	    취소
            	</button>    
			</div>
            <!-- 가입/취소 버튼 끝 -->            
        
        </form>
    </div> 
    <!-- 회원 정보 수정 끝 -->
 
</body>
</html>