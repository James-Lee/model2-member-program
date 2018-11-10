/**
 * 
 */
package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.service.MemberService;
import com.javateam.member.service.MemberServiceImpl;

/**
 * 분실 아이디 검색 처리
 * @author javateam
 *
 */
public class IdSearchProcAction implements CommandAction {

	/**
	 * @see com.javateam.member.controller.CommandAction
	 * 	#requestPro(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String requestPro(HttpServletRequest request, 
							 HttpServletResponse response) throws Throwable {
		
		System.out.println("아이디 검색 처리");
		
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String msg = "";
		
		MemberService service = MemberServiceImpl.getInstance();
		String id = service.getIdByEmailMobile(email, mobile);
		
		msg = id.equals("") ? "해당되는 아이디가 없습니다." : id;
		
		request.setAttribute("msg", msg);
		
		return "/login/idSearch.jsp";
	} //

}
