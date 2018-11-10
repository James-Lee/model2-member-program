/**
 * 
 */
package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javateam.member.controller.CommandAction;

/**
 * 로그아웃 처리
 * @author javateam
 *
 */
public class LogoutAction implements CommandAction {

	/**
	 * @see com.javateam.member.controller.CommandAction#requestPro(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String requestPro(HttpServletRequest request, 
							 HttpServletResponse response) throws Throwable {

		System.out.println("로그아웃 처리");
		HttpSession session = request.getSession();
		
		System.out.println("-------------------------------------");
		System.out.println("세션 : "+session.getAttribute("LOGIN_SESS"));
		
		// 세션 종료
		session.invalidate();
		request.setAttribute("logout_yn", "true"); // 로그아웃 여부 플래그 전송  
		
		// 템플릿 페이지로 이동
		// return "/login/login.html";
		return "/main.html";
	} // 

}
