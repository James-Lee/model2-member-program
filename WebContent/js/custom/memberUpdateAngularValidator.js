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
            /* var output = '';
             for (var key in data) {
             	output += key + ":" +  data[key]+"\n";
             }
             
             alert(output); */

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
///////////////////////////////////////////////////////////////////

// 비동기적 함수 callback(호출)
function emailAsyncCheck() {
	
	var email_pnl = document.getElementById("email_pnl");
	var email_val = document.getElementById("email_val");
    var msg = document.getElementById('email_msg');
    var id = document.getElementById('id');
    
    var flag = checkEmail(combineEmail()); // 이메일 조합 함수 사용
    
    if (flag == true) { // 이메일 폼점검 통과시
    	
    	sendEmail();
        
    } else {
        
		 email_pnl.style.height = "50px";
		 email_val.style.height = "50px";
         msg.style.fontSize = "12px"; // font-size
         msg.style.paddingLeft = "10px"; // padding-left
         msg.style.paddingTop = "6px"; // padding-top
         msg.style.color = "red"; // color
         msg.innerHTML = "부적절한 이메일입니다. 다시 입력하세요";
         
         document.getElementById('email1').value = "";
         return false;
         
    } // if 
    
}

// 중복 점검 이메일 전송 : 클라이언트 -> 서버 전송
function sendEmail() {
	
	var email1 = document.getElementById("email1");
	var email2 = document.getElementById("email2");
	var email3 = document.getElementById("email3");
	
	var email_pnl = document.getElementById("email_pnl");
	var email_val = document.getElementById("email_val");
	var email = document.getElementById("email"); // 이메일 히든 필드
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
	
	// jQuery AJAX를 통한 아이디 및 이메일 전송(중복 점검)
	 $.ajax({
			 url : '${pageContext.request.contextPath}/emailByIdAsyncCheck.do',
			 type : 'get',
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
				 
					  email_msg.innerHTML = "사용할 수 있는 이메일입니다";
					  email_msg.style.color = "blue";
					  email_pnl.style.height = "50px";
					  email_val.style.height = "50px";
				      
				} else { // 서버 리턴 전송값 = 1 : 중복
					
					  console.log("중복된 메일");
					 
					  email_msg.innerHTML = "이미 등록된 이메일입니다";
					  email_msg.style.color = "red";
					  email_pnl.style.height = "50px";
					  email_val.style.height = "50px";
					  
					  document.getElementById('email1').value = "";
				}
				 
			}, // success
		 	
		 	error : function(xhr, status) {
		 		
		        console.log(xhr+" : "+status); // 에러 코드
	        } 
 
	 }); // $.ajax
}