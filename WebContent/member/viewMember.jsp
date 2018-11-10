<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">

<!-- CSS -->
<link rel="stylesheet" 
	  href="${pageContext.request.contextPath}/css/memberJoinAngular.css" type="text/css">
	  
<!-- 최상단 메시지 출력 관련 CSS -->
<link rel="stylesheet" 
	  href="${pageContext.request.contextPath}/css/memberJoin2Angular.css" type="text/css">

<!-- radio 버튼 CSS -->	  
<style>
/* 아래의 CSS는 IE에서는 미적용 부분 있음 */ 
.form-radio
{
     -webkit-appearance: none;
     -moz-appearance: none;
     appearance: none;
     display: inline-block;
     position: relative;
     top: 0px;
     height: 20px;
     width: 20px;
     border: 1px solid #ccc;
     border-radius: 50px;
     cursor: pointer;     
     margin-right: 7px;
     outline: none;
}
.form-radio:checked::before
{
     position: absolute;
     font: 10px/1 'Open Sans', sans-serif;
     left: 4px;
     top: 3px;
     content: '●'; /* ● type */ 
     /* -- check type 
     content: '\02143';
     transform: rotate(40deg); */   
}
.form-radio:hover
{
	background-color: red;
     /* background-color: #f7f7f7; */
}
.form-radio:checked
{
	background-color: red;
     /* background-color: #f1f1f1; */
}

/* 라디오 버튼 라벨 */
.radio-label 
{
	float:left; 
	width:20px; 
	height:100%; 
	top:10px;
	display:flex;
	align-items:center;
	justify-content:center;
}

option
{
	background:blue;
}
</style>	  

<!-- 복합인자 처리 : 이메일, 휴대폰, 전화번호, 성별 -->
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
   
   var genderChecked = document.all["gender"];
   var len = genderChecked.length;
   
   for (var i=0; i<len; i++) {
       
       if (gender.trim() == genderChecked[i].value)
           genderChecked[i].checked = true;
   } // for
}

// 비활성화(disabled) 컴포넌트 CSS 변경 
// 글자색 : 암회색 ==> 흑색
function changeDisabledCSS() {
	
	var disabledObject = document.querySelectorAll("[disabled]");
	
	for (var i=0; i<disabledObject.length; i++)  {
		
		disabledObject[i].style.color = '#333'; // IE 및 라디오 버튼에는 글자색 적용안됨.
		disabledObject[i].style.backgroundColor = '#fff'; // IE에서는 라디오 버튼의 경우 미적용
	} //

	// select box
   	/* var disabledObject = document.querySelectorAll("select:disabled");
   	
   	for (var i=0; i<disabledObject.length; i++) {
   		
	    var optionLen = disabledObject[i].options.length;
		
	    for (var j=0; j<optionLen; j++) {
    		disabledObject[i].style.color = '#333'; // IE 및 라디오 버튼에는 글자색 적용안됨.
    		disabledObject[i].style.backgroundColor = '#fff';
	    } // for 
  		
   	} // for */
	
} //
 
window.onload = function(e) {
   
   selectMobile("${mobile[0]}"); // 휴대폰 앞번호 설정
   selectPhone("${phone[0]}");   // 연락처 앞번호 설정
   selectEmail("${email[1]}");   // 이메일 설정
   selectGender("${member.gender}");     // 성별 설정
   
   changeDisabledCSS(); // 비활성화 객체 CSS 변경
};
</script>
</head>

<body>
	  
   <!-- 테스트 모니터단 시작 -->
   
   <!-- 최상단 메시지 -->
   <div id="total_msg">
	   <!-- 인자 출력 테스트 시작 -->		   
	   <div style="background:yellow; width:622px;">
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
   
   <!-- 회원조회 시작 -->
   <div id="wrapView"> 
   
        <form id="memberView" name="memberView">
            
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
            
            <!-- 패쓰워드 시작 -->
            <div id="pw_title" class="fld_title">
               	패쓰워드
            </div>
            <div class="fld_val">
            
                <input type="password" 
                	   id="pw_fld" 
                	   name="pw_fld" 
                       size="25"
					   readonly
					   value="${member.pw}">&nbsp;
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
                       readonly
					   value="${member.name}">&nbsp;
            </div>
            <!-- 이름 끝 -->
            
            <!-- 성별 시작 -->
            <div id="gender_pnl" class="fld_title">
                                  성별
            </div>
            <div class="fld_val">
                 <!-- ${member.gender == 109 ? '남' : '여' } -->
      	         <div class="radio-label">남</div>
      	         <div style="float:left">
        	     <input type="radio" 
	                    id="gender"
	                    name="gender" 
	                    value="m"
	                    disabled
	                    class="form-radio">&nbsp;
		          </div>
		                    
              	  <div class="radio-label">여</div>
              	  <div>
              	  <input type="radio" 
	                     id="gender"
	                     name="gender" 
	                     value="f"
	                     disabled
	                     class="form-radio">
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
                       readonly
                       value="${email[0]}">
                @
                <select id="email2" 
                		name="email2"
                		disabled>
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
                       readonly> 
            </div>
            <!-- 이메일 끝 -->
            
            <!-- 휴대폰 시작 -->
            <div id="mobile_pnl" class="fld_title">
                                 휴대폰
            </div>
            <div class="fld_val">
                
                <select id="mobile1" 
                		name="mobile1"
                		disabled>
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
                       readonly
                       value="${mobile[1]}"> -   
                                
                <input type="text" 
                	   id="mobile3"
                       name="mobile3" 
                       maxlength="4"
                       size="6"
					   readonly
					   value="${mobile[2]}">   
            </div>
            <!-- 휴대폰 끝 -->
            
            <!-- 연락처 시작 -->
            <div id="phone_pnl" class="fld_title">
                                 연락처
            </div>
            <div class="fld_val">
                <select id="phone1" 
                		name="phone1"
                		disabled>
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
					   readonly
					   value="${phone[1]}"> -            
                <input type="text" 
                	   id="phone3"
                       name="phone3" 
                       maxlength="4"
                       size="6"
                       readonly
                       value="${phone[2]}">     
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
                       readonly
                       value="${address1[0]}">
                       <br><br>
                       
                <input type="text" 
                	   id="roadAddr2"
                       name="roadAddr2"    
                       maxlength="100"
                       size="75"
                       readonly
                       value="${addressTemp1}">      
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
                       readonly
                       value="${address2[0]}">
                       <br><br>
                <input type="text" 
                	   id="oldAddr2"
                       name="oldAddr2"    
                       maxlength="100"
                       size="75"
                       readonly
                       value="${addressTemp2}">      
            </div>
            <!-- (구)주소 끝 -->
            
            <!-- 생년월일 시작 -->
            <div id="birth_title" class="fld_title">
                        생년월일
            </div>
            <div class="fld_val">
                 <%-- <input type="text" 
                 		id="birthday"
                        name="birthday"
                        maxlength="10"
                        size="12"
                        readonly
                        value="${member.birthday}">  --%>   
                 <fmt:formatDate value="${member.birthday}" pattern="yyyy년 M월 d일"/>       
            </div>
            <!-- 생년월일 끝 -->
            
            <!-- 가입일 시작 -->
            <div id="join_title" class="fld_title">
              	가입일
            </div>
            <div class="fld_val">
               <%--  <input type="text" 
                	   id="joindate"
                       name="joindate"
                       maxlength="10"
                       size="12"
                       readonly
                       value="${member.joindate}"> --%>
                <fmt:formatDate value="${member.joindate}" pattern="yyyy년 M월 d일"/>       
            </div>
            <!-- 가입일 끝 -->
        
        </form>
        
    </div> 
    <!-- 회원조회 끝 -->
 
</body>
</html>