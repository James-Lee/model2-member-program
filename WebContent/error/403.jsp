<%@ page isErrorPage="true" 
		 language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>403 patch</title>
<script>
	window.onload = function() {
		
		alert("보안화로 인해 JSP 페이지에 직접 접근할 수 없습니다.\n홈페이지로 이동하겠습니다.");
		
		setTimeout(function() {
			location.href= "http://localhost:8181/member_project/index.html"; // 페이지 이동
		}, 1000);
	} //
</script>
</head>
<body>

</body>
</html>