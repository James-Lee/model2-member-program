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
 * 아이디 중복 점검
 * @author javateam
 *
 */
public class IdCheckAction implements CommandAction {

	/**
	 * @see com.javateam.member.controller.CommandAction#requestPro(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String requestPro(HttpServletRequest request, 
							 HttpServletResponse response) throws Throwable {

		System.out.println("아이디 중복 점검");
		
		String id = request.getParameter("id").trim();
		MemberService service = MemberServiceImpl.getInstance();
		boolean flag = service.checkId(id);
		
		System.out.println("flag : "+ (flag==true ? "아이디 존재" : "아이디 없음"));
		
		// 1 : 중복이 있음, 0 : 중복이 없음
		int result = flag == true ? 1 : 0;
		request.setAttribute("result", result);
		
		return "/member/result.jsp";
	}

}
