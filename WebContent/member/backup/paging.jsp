<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>페이징</title>

<!-- bootstrap pagination -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

</head>
<body>

<!-------- paging 시작 -------->
 <div id="paging" style="width:100%; margin:20px 0">
 
   <ul class="pagination justify-content-center">
  
   <!-- 3 페이지씩 출력되도록 조치 --> 
   <c:choose>
   
      <c:when test="${memberNum <= pagingVO.rowPerPage}">
      	 <li class="page-item"><span style="font-size:12px">1</span></li>
      </c:when>   
   
      <%-- 회원 페이지 --%>
      <c:when test="${not empty pagingVO.curPage}">
      
         <!-- 처음 페이지 -->
   	     <li class="page-item"><a class="page-link" href="viewAllPaging.do?curPage=1">◀◀ </a></li>
		 <!-- 이전 페이지 -->
		 <li class="page-item"><a class="page-link" href="viewAllPaging.do?curPage=${pagingVO.prePage}">◀</a></li>
  
         <!-- 시작/끝 페이지 설정 -->
      	 <c:set var="startNo" value="1"/>
      	 
	 	 <c:set var="endNo" 
	 	 	    value="${memberNum <= pagingVO.rowPerPage ? 1 : 3}"/> <!-- 3 페이지 단위 -->
	 	 
      	 <c:choose>
       		<c:when test="${pagingVO.curPage == 1}">
       			<c:set var="startNo" value="${startNo+1}" />
       			<c:set var="endNo" value="4"/>  <!-- patch -->
       		</c:when>
       		<c:when test="${pagingVO.curPage == pagingVO.endPage}">
       			<c:set var="endNo" value="${endNo-1}" />
       		</c:when>
       	 </c:choose>	 
       	 
       	 <!-- 마지막 페이지 2페이지 출력 : 버그 패치 -->
       	 <c:set var="beginVar" 
       	 		value="${memberNum > 10 && pagingVO.curPage == pagingVO.totPage ? 
       	 			     startNo-1 : startNo}" />
       	 <c:set var="endVar" value="${pagingVO.totPage < 2 ? pagingVO.endPage+1 : endNo}" />
       	 <c:set var="endVar" value="${pagingVO.curPage == pagingVO.totPage ? endNo : endNo}" />
       	 
      	 <!-- 페이지 번호 나열 -->
         <c:forEach begin="${beginVar}" 
         			end="${endVar}" 
         			varStatus="st">
         			
			<li class="active">
				<a class="page-link" href="viewAllPaging.do?curPage=${st.index + pagingVO.curPage-2}">
	
				 <%-- 현재 페이지 = bold <b> 처리 --%>	
				 <c:choose>
				 	<c:when test="${pagingVO.curPage == (st.index + pagingVO.curPage-2)}">
				 	 ${st.index + pagingVO.curPage-2}
				 	</c:when>
				 	<c:otherwise>
				 	 ${st.index + pagingVO.curPage - 2}
				 	</c:otherwise>
				 </c:choose>
				 	
			</a></li>
			
         </c:forEach>
         
         <!-- 다음 페이지 -->
		  <li class="page-item"><a class="page-link" class="page-link" href="viewAllPaging.do?curPage=${pagingVO.nextPage}">▶ </a></li>  
		  
		 <!-- 마지막 페이지 -->
		 <li class="page-item"><a class="page-link" href="viewAllPaging.do?curPage=${pagingVO.endPage}">▶▶ </a></li>
		 
      </c:when>
      
   </c:choose>
   
   </ul>
   
 </div>
 
<!-------- paging 끝 -------->

</body>
</html>