/**
 * 
 */
package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;

/**
 * 분실된 패쓰워드 검색(입력)
 * @author javateam
 *
 */
public class PasswordSearchAction implements CommandAction {

	/**
	 * @see com.javateam.member.controller.CommandAction#requestPro(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String requestPro(HttpServletRequest request, 
							 HttpServletResponse response) throws Throwable {

		System.out.println("분실 패쓰워드 입력폼");
		
		return "/login/passwordSearch.jsp";
	} //

}
