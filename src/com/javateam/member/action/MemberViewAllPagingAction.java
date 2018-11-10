/**
 * 
 */
package com.javateam.member.action;

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
 * 전체 회원 보기
 * @author javateam
 *
 */
public class MemberViewAllPagingAction implements CommandAction {

	/**
	 * @see com.javateam.member.controller.CommandAction#requestPro(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String requestPro(HttpServletRequest request, 
							 HttpServletResponse response) throws Throwable {
		
		System.out.println("전체 페이지 보기");

		String page = ""; // 이동 페이지(JSP)
		
		PagingUtil pagingUtil = new PagingUtil(); // 페이징 유틸리티  객체
		PagingVO pagingVO = null; // 페이징 객체
		int memberNum = 0; // 총 인원수
		
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
			
		List<MemberVO> members = null;
	    MemberService service = MemberServiceImpl.getInstance();
	    members = service.getAllMembers();
		memberNum = members.size(); // 총인원수
    	
    	// 인자 전송 여부 점검
		if (request.getParameter("curPage") != null) {
			
			/*
			members = service.getAllMembers();
			memberNum = members.size(); // 총인원수
		 	*/			
			// 페이지 처리 시작
			if (request.getParameter("curPage") == null) {
				curPage = 1; // 현재 페이지
			} else { // 인자 전송시
				curPage = new Integer(request.getParameter("curPage")); // 현재 페이지
			} // 
			
			members = service.getAllMembers(new PagingVO(curPage, rowPerPage));
			// 페이지 처리 끝
				
		} else { // 첫페이지(무인자)
			
			members = service.getAllMembers(new PagingVO(1, rowPerPage));
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
	
		request.setAttribute("memberNum", memberNum); // 총 인원수
		request.setAttribute("members", members);
    	
    	page = "/member/viewAllMembersPaging.jsp";
    	
		return page;
	}

}
