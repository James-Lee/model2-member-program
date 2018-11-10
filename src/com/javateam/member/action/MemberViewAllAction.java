/**
 * 
 */
package com.javateam.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.domain.MemberVO;
import com.javateam.member.service.MemberService;
import com.javateam.member.service.MemberServiceImpl;

/**
 * 전체 회원 보기
 * @author javateam
 *
 */
public class MemberViewAllAction implements CommandAction {

	/**
	 * @see com.javateam.member.controller.CommandAction#requestPro(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String requestPro(HttpServletRequest request, 
							 HttpServletResponse response) throws Throwable {

		String page = ""; // 이동 페이지(JSP)
			
	    MemberService service = MemberServiceImpl.getInstance();
    	List<MemberVO> members = service.getAllMembers();
    	
    	// 전송 인자(전체 회원 정보) : ${members}
		request.setAttribute("members", members);
		page = "member/viewAllMembers.jsp";
		
		return page;
	}

}
