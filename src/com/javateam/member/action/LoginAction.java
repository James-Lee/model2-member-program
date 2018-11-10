/**
 * 
 */
package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.service.MemberService;
import com.javateam.member.service.MemberServiceImpl;

/**
 * 로그인 처리
 * @author javateam
 *
 */
public class LoginAction implements CommandAction {

	/**
	 * @see com.javateam.member.controller.CommandAction#requestPro(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String requestPro(HttpServletRequest request, 
							 HttpServletResponse response) throws Throwable {

		System.out.println("로그인 처리");
		String msg = "";
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String path = "";
		
		System.out.println("id : " + id);
		System.out.println("pw : " + pw);
		
		// msg = "로그인 인자 점검";
		
		MemberService service = MemberServiceImpl.getInstance();
		msg = service.hasMember(id, pw);
		
		if (msg.equals("회원정보가 존재합니다.")) {
			
			HttpSession session = request.getSession();
			
			// 세션정보 존재 점검
			if (session.getAttribute("LOGIN_SESS")==null) {
				session.setAttribute("LOGIN_SESS", id);  // 세션 생성
			} //
			
			msg = id+"님 로그인에 성공하셨습니다.";
			return "/login/login_success.jsp";
			
		} else { // 회원정보 존재하지 않을 경우
			
			path = "login.html";			
		}
		
		// 인자 전송
		request.setAttribute("msg", msg);
		request.setAttribute("return_page", path);
		
		return "/login/login_result.jsp";
	} //

}
