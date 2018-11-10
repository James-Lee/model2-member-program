/**
 * 
 */
package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;

/**
 * role(계층) 점검 오류시 페이지 이동
 * @author javateam
 *
 */
public class RoleFailAction implements CommandAction {

	/**
	 * @see com.javateam.member.controller.CommandAction#requestPro(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		System.out.println("Role 점검 실패");
		
		request.setAttribute("msg", "본 페이지 열람에 대한 사용자 권한이 부족합니다.");
		
		return "/login/role_fail.jsp";
	}

}
