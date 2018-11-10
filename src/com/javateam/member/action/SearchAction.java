/**
 * 
 */
package com.javateam.member.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.domain.MemberVO;
import com.javateam.member.domain.PagingVO;
import com.javateam.member.service.MemberService;
import com.javateam.member.service.MemberServiceImpl;
import com.javateam.member.util.PagingUtil;

/**
 * 검색 기능
 * @author javateam
 *
 */
public class SearchAction implements CommandAction {

	/**
	 * @see com.javateam.member.controller.CommandAction#requestPro(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("검색 액션");
		String page = "";
		String msg = "";
		
		// 인자 점검
	 	if (request.getParameter("searchWord") == null ||
 			request.getParameter("searchWord").trim().equals("")) {
	 		
	 		 msg = "검색 키워드를 입력하십시오 !";
	 		 page = "/member/result_msg.jsp";
	 		 request.setAttribute("msg", msg);
	 		 request.setAttribute("return_page", "viewAllPaging.html");
	 		 
	 	} else { // 아이디 인자 입력시(시작)
	 		
	 		 System.out.println("인자 입력시");
	 
			 String searchKey 
			 	= request.getParameter("searchKey");
			 
			 System.out.println("검색어 : "+ searchKey);
			 
			 MemberService service = MemberServiceImpl.getInstance();
			 
			 // 검색키가 "아이디"인 경우 시작
			 if (searchKey.equals("id")) {
				 
				 System.out.println("아이디 검색");
				 String id = request.getParameter("searchWord");
				 MemberVO member = service.getMember(id);
				 
				 System.out.println("id : "+id);
				 
				 if (member.getId() == null) { // 조회한 회원 정보가 없을 경우
					
					System.out.println("회원정보가 없습니다.");
					page = "/member/result_msg.jsp"; // 템플릿 검색시 경로 주의 "/" 추가
					request.setAttribute("msg", "회원 정보가 없습니다. 다시 조회하십시오.");
					request.setAttribute("return_page", "viewAllPaging.html");
					
				 } else { // 회원 정보가 존재할 경우
					 
					// 템플릿 적용
					PagingUtil pagingUtil = new PagingUtil(); // 페이징 유틸리티  객체
					PagingVO pagingVO = null; // 페이징 객체
					int memberNum = 1; // 인원수
					
					List<MemberVO> members = new ArrayList<>();
					members.add(member);
				
					// 페이지 관련 변수들
					// 현재 페이지
					int curPage = 1;
					// 시작 페이지
					int startPage = 1;
					// 마지막 페이지
					int endPage = 1;
					// 페이지별 글수
					int rowPerPage = 5;
					// 총 페이지
					int totPage = 0;
					// 이전 페이지
					int prePage = 0;
					// 다음 페이지
					int nextPage = 0;
					
					System.out.println("회원정보 존재"); 
					System.out.println(member);
					request.setAttribute("member", member);
							            
		            // 페이징 인자 객체 형성
					pagingVO = new PagingVO(curPage,
					 						 startPage,
											 endPage,
											 rowPerPage,
											 totPage,
											 prePage,
											 nextPage);
					
					// 페이징 처리
					pagingVO = pagingUtil.getPageInfo(memberNum, pagingVO);
					
					// 전송 인자
					request.setAttribute("pagingVO", pagingVO); // 페이징 관련 인자
				
					request.setAttribute("memberNum", memberNum); // 1명(검색)
					request.setAttribute("members", members);   
					 
					// 전송 인자 추가된 부분 : 아이디 검색
					request.setAttribute("searchWord", id);
					request.setAttribute("searchKey", "id");
									
					page = "/member/viewAllMembersPaging.jsp"; // 템플릿 검색시 경로 주의 "/" 추가
				 
			     } //
			 
			 } else if (searchKey.equals("name")) {
				 
				 System.out.println("이름 검색");
			
			 	 String name = request.getParameter("searchWord").trim();
				 
				 List<MemberVO> members = service.getMemberByName(name);
				 int num = members.size();
				 
				 System.out.println("name : " + name);
				 System.out.println("num : "+num);
				 
				 // 템플릿 적용
				 if (num >= 1) { // 결과가 1명 이상일 경우 
				 
					 PagingUtil pagingUtil = new PagingUtil(); // 페이징 유틸리티  객체
					 PagingVO pagingVO = null; // 페이징 객체
					 int memberNum = 0; // 이름으로 검색된 총인원수
					
					 // 페이지 관련 변수들
					 // 현재 페이지
					 int curPage = 1;
					 // 시작 페이지
					 int startPage = 1;
					 // 마지막 페이지
					 int endPage = 1;
					 // 페이지별 글수
					 int rowPerPage = 5;
					 // 총 페이지
					 int totPage = 0;
					 // 이전 페이지
					 int prePage = 0;
					 // 다음 페이지
					 int nextPage = 0;
					 
					 members = service.getMemberByName(name);
					 memberNum = members.size(); // 이름으로 검색된 총인원수
			    	
			    	 // 인자 전송 여부 점검
					 if (request.getParameter("curPage") != null) {
						
						 System.out.println(1);
						 // 페이지 처리 시작
						 if (request.getParameter("curPage") == null) {
					 		curPage = 1; // 현재 페이지
						 } else { // 인자 전송시
							curPage = new Integer(request.getParameter("curPage")); // 현재 페이지
						 } // 
						
						 members = service.getMemberByName(name, 
								 			new PagingVO(curPage, rowPerPage));
						 System.out.println(2);
						 System.out.println("검색된 회원수 : "+members.size());
						 // 페이지 처리 끝
							
					 } else { // 첫페이지(무인자)
						
					 	 members = service.getMemberByName(name,
					 			 			new PagingVO(1, rowPerPage));
					 } 
					
					 // 페이징 인자 객체 형성
					 pagingVO = new PagingVO(curPage,
					 						 startPage,
											 endPage,
											 rowPerPage,
											 totPage,
											 prePage,
											 nextPage);
					
 					 // 페이징 처리
					 pagingVO = pagingUtil.getPageInfo(memberNum, pagingVO);
					
					 System.out.println("memberNum : "+memberNum);					 
					
					 // 전송 인자
					 request.setAttribute("pagingVO", pagingVO); // 페이징 관련 인자
				
					 request.setAttribute("memberNum", memberNum); // 이름으로 검색된 총인원수
					 request.setAttribute("members", members);   
					 
					 System.out.println("검색된 회원수 : "+members.size());

					 // 전송 인자 추가된 부분 : 이름 검색
					 request.setAttribute("searchWord", name);
					 request.setAttribute("searchKey", "name");
					 
					 page = "/member/viewAllMembersPaging.jsp"; // 템플릿 검색시 경로 주의 "/" 추가
					 
				 } else { // 결과 없음
					 
					 request.setAttribute("msg", "회원 조회 결과 없음");
					 request.setAttribute("return_page", "viewAllPaging.html");
					 page = "/member/result_msg.jsp"; // 템플릿 검색시 경로 주의 "/" 추가
				 } // 
				 
			 } // if (searchKey.equals("id")) {
	 	
	 	} // 인자 점검
	 	
		return page;
	}

}
