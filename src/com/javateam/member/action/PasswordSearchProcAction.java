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
 * 분실된 패쓰워드 처리
 * @author javateam
 *
 */
public class PasswordSearchProcAction implements CommandAction {

	/**
	 * @see com.javateam.member.controller.CommandAction#requestPro(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String requestPro(HttpServletRequest request, 
							 HttpServletResponse response) throws Throwable {

		System.out.println("패쓰워드 검색 처리");
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		String msg = "";
		
		MemberService service = MemberServiceImpl.getInstance();
		String pw = service.getPwByIdEmailMobile(id, email, mobile);
		
		msg = pw.equals("") ? "해당되는 패쓰워드가 없습니다." : pw;
		
		request.setAttribute("msg", msg);
		
		return "/login/passwordSearch.jsp";
	}

}
