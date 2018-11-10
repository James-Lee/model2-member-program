package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.service.MemberService;
import com.javateam.member.service.MemberServiceImpl;

/**
 * 이메일 중복 점검
 * @author javateam
 *
 */
public class EmailCheckAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, 
							 HttpServletResponse response) throws Throwable {

		System.out.println("이메일 중복 점검");
		
		String email = request.getParameter("email").trim();
		
		System.out.println("이메일 : "+email);
		
		MemberService service = MemberServiceImpl.getInstance();
		boolean flag = service.checkEmail(email);
		
		System.out.println("flag : "+ (flag==true ? "이메일 존재" : "이메일 없음"));
		
		// 1 : 이메일 있음, 0 : 이메일 없음
		int result = flag == true ? 1 : 0;
		request.setAttribute("result", result);
		
		return "/member/result.jsp";
	} //

}
