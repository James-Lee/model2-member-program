// 중복 점검 아이디 전송 : 클라이언트 -> 서버 전송 (비-버튼 방식)
function sendIdForBlur() {
	
	var result = 0;
	
	$.ajax({
		 	 url : '../idCheck.do',
			 type : 'post',
			 dataType:'text',
			 async: false, // callback no need
			 data : {
				 id : $('#id_fld').val()
			 }, // data 
			 success : function(data) {
				
				console.log("아이디 중복 점검 수신 !"); 
		        var listView = document.getElementById('id_msg2');
		       
		        // 보완 : 아이디 폼점검 메시지와 중복/충돌 방지
		        var id_msg1 = document.all["id_msg1"];
		        
		        listView.innerText = ''; // 초기화
		        
		        if (id_msg1.innerText == '아이디는 8~20자로 작성해야합니다.') {
		        	listView.innerText = '';
		        }
		        
		        // CSS 적용
		        listView.style.fontSize = "12px"; // font-size
		        listView.style.paddingLeft = "10px"; // padding-left
		        listView.style.paddingTop = "6px"; // padding-top
		        
				if(data == 0) { // 서버 리턴 전송값 = 0
				 
				  listView.innerHTML = "사용할 수 있는 ID 입니다";
				  listView.style.color = "blue";
				  
				  result = data;
				  
				} else { // 서버 리턴 전송값 = 1
				 
				  listView.innerHTML = "이미 등록된 ID 입니다";
				  listView.style.color = "red";
				  
				  document.getElementById('id_fld').value = "";
				  
				  result = data;
				}
				 
			}, // success
		 	
		 	error : function(xhr, status) {
		        console.log(xhr+" : "+status); // 에러 코드
	        } 
			
	 }); // $.ajax
			
	return result;
}

// 중복 점검 이메일 전송 : 클라이언트 -> 서버 전송 (비-버튼 방식)
function sendEmailForBlur() {
	
	var email1 
		= document.getElementById("email1");
	var email2 
		= document.getElementById("email2");
	var email3 
		= document.getElementById("email3");
	
	var email_pnl = document.getElementById("email_pnl");
	var email_val = document.getElementById("email_val");
	var email_msg = document.getElementById('email_msg');
	
	if (email2.value == "사용자 직접 입력") {
		
		email_val.value = email1.value.trim() 
						+ "@" 
						+ email3.value.trim();
	
	// 포털 메일 도메인	
	} else { 
			
		// 사용자 메일 도메인/패널 초기화
		email_pnl.style.height = "25px";
		email_val.style.height = "25px";
		
		email_msg.innerHTML = "";
		email3.value = "";
		
		email_val.value = email1.value.trim() 
						+ "@" 
						+ email2.value;
	}
	
	console.log(email_val.value);
	
	// 병합 이메일 정규 표현식 폼점검
	var regex = /^[a-z0-9_+.-]+@([a-z0-9-]+\.)+[a-z0-9]{2,4}$/; 
	   
    if (regex.test(email_val.value)) { // 폼점검 OK !
    	
    	$.ajax({
			 url : '../emailCheck.do',
			 type : 'post',
			 ajax : false, // callback no need
			 dataType:'text',
			 data : {
				 id :  $('#id_fld').val(),
				 email : $('#email_val').val()
			 }, // data 
			 
			 success : function(data) {
				
				console.log("이메일 중복 점검 수신 !"); 
				
		        // CSS 적용
				email_msg.style.fontSize = "12px"; // font-size
				email_msg.style.paddingLeft = "10px"; // padding-left
				email_msg.style.paddingTop = "6px"; // padding-top
		        
				if(data == 0) { // 서버 리턴 전송값 = 0 
					
				  console.log("data : " +data);
				 
				  email_msg.innerHTML = "사용할 수 있는 이메일입니다";
				  email_msg.style.color = "blue";
				  email_pnl.style.height = "50px";
				  email_val.style.height = "50px";
				  
				  // 추가 : 전체 레이아웃 높이 보정
				  document.getElementById("wrap").style.height = "643px";
				  
				  return true;
				  
				} else { // 서버 리턴 전송값 = 1 : 중복
				 
				  email_msg.innerHTML = "이미 등록된 이메일입니다";
				  email_msg.style.color = "red";
				  email_pnl.style.height = "50px";
				  email_val.style.height = "50px";
				  
				  // 추가 : 전체 레이아웃 높이 보정
				  document.getElementById("wrap").style.height = "643px";
				  document.getElementById('email1').value = "";
				  
				  console.log("data : " +data);
				  
				  return false;
				}
				 
			}, // success
		 	
		 	error : function(xhr, status) {
		 		
		        console.log(xhr+" : "+status); // 에러 코드
	        } 

	 }); // $.ajax
    	
    } else { // 폼점검 실패
    	
    	  console.log("원상 복귀");
    	  
          // CSS 적용
		  email_msg.style.fontSize = "12px"; // font-size
		  email_msg.style.paddingLeft = "10px"; // padding-left
		  email_msg.style.paddingTop = "6px"; // padding-top
        
		  email_msg.innerHTML = "올바른 이메일 형식이 아닙니다.";
		  email_msg.style.color = "blue";
		  email_pnl.style.height = "50px";
		  email_val.style.height = "50px";
		  
		  // 추가 : 전체 레이아웃 높이 보정
		  document.getElementById("wrap").style.height = "643px";  
		  
		  // 재입력 대기
		  email1.focus();
		  
		  //result = false;
		  return false;
    } //
    
    // console.log("메일 사용가능 여부 : "+result);
    
    // return result;
	
} // email

// 이메일 메시징 초기화
function emailMsgInit() {
	
	var email_pnl = document.getElementById("email_pnl");
	var email_val = document.getElementById("email_val");
	var email_msg = document.getElementById('email_msg');
	
	email_msg.innerHTML = "";
	email_pnl.style.height = "25px";
    email_val.style.height = "25px";
	  
	// 원래의 레이아웃 높이 복원 
	document.getElementById("wrap").style.height = "617px";   
}

////////////////////////////////////////////////////////////////
//도로명 주소 검색
 function road_Postcode() {
     new daum.Postcode({
         oncomplete: function(data) {
             // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

             // 각 주소의 노출 규칙에 따라 주소를 조합한다.
             // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
             var fullAddr = ''; // 최종 주소 변수
             var extraAddr = ''; // 조합형 주소 변수

             // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
             fullAddr = data.roadAddress;
             
             // 법정동명이 있을 경우 추가한다.
             if(data.bname !== ''){
                 extraAddr += data.bname;
             }
             // 건물명이 있을 경우 추가한다.
             if(data.buildingName !== ''){
                 extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
             }
             // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
             fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
             
            // 주소 정보 전체 필드 및 내용 확인 : javateacher
            var output = '';
             for (var key in data) {
             	output += key + ":" +  data[key]+"\n";
             }
             
             alert(output); 

             // 우편번호와 주소 정보를 해당 필드에 넣는다.
             document.getElementById('zip1').value = data.zonecode; //5자리 새우편번호 사용
             document.getElementById('roadAddr1').value = fullAddr;

             // 커서를 상세주소 필드로 이동한다.
             document.getElementById('roadAddr2').focus();
         }
     }).open();
 }
 
// 구 주소 방식 검색
 function old_Postcode() {
	 
     new daum.Postcode({
         oncomplete: function(data) {
             // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

             // 각 주소의 노출 규칙에 따라 주소를 조합한다.
             // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
             var fullAddr = ''; // 최종 주소 변수
             var extraAddr = ''; // 조합형 주소 변수
             
             fullAddr = data.jibunAddress;

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            // 주소 정보 전체 필드 및 내용 확인 : javateacher
            /* var output = ''; 
             for (var key in data) {
             	output += key + ":" +  data[key]+"\n";
             }
             
             alert(output); */
             
             // data.postcode를 두갈래로 구분하여 대입
             // ex) 138-830 ---> 138, 830
             document.getElementById('zip2_1').value 
             	= data.postcode1; 
             document.getElementById('zip2_2').value 
             	= data.postcode2;
             document.getElementById('oldAddr1').value = fullAddr;

             // 커서를 상세주소 필드로 이동한다.
             document.getElementById('oldAddr2').focus();
         }
     }).open();
 }    
 
// 생년월일 입력 : 한글 포맷 적용(datepicker.ko.js)
$(function() { 

	$("#birthday").datepicker({changeYear: true, changeMonth: true});	
	// $("#birthday").datepicker({dateFormat: "yy-m-d"});
	// $("#birthday").datepicker(); // 기본 날짜 포맷
});
 
// 가입일 자동 입력
function outputDate() {

	var date = new Date();
	var today = "20"+date.getYear().toString().substring(1,3) + "-"
	          + (date.getMonth()+1) + "-"
	          + date.getDate();
	
	document.all["joindate"].readOnly = true; // 임의 입력방지
	document.all["joindate"].value = today;
}

// 이메일 조합
function combineEmail() {
	
	var email1 = document.getElementById("email1");
	var email2 = document.getElementById("email2");
	var email3 = document.getElementById("email3");
	var email = "";
	
	var final_email = document.getElementById("email"); // 추가
	
	// 사용자 이메일 도메인(非포털)
	if (email2.value == "사용자 직접 입력") {
		
		email = email1.value.trim() 
			  + "@" 
			  + email3.value.trim();
	
	// 포털 메일 도메인	
	} else { 
			
		// 사용자 메일 도메인 초기화
		email3.value = "";
		email = email1.value.trim() 
			  + "@" 
			  + email2.value;
	}
	
	return email;
}

///////////////////////////////////////////////////////

// 페이지 로딩시 시작할 부분
window.onload = function() {
	
	outputDate(); // 가입일 생성 표시
}