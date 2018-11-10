/**
 * 
 */
package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;

/**
 * 분실 아이디 검색 처리
 * @author javateam
 *
 */
public class IdSearchAction implements CommandAction {

	/**
	 * @see com.javateam.member.controller.CommandAction
	 * 	#requestPro(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String requestPro(HttpServletRequest request, 
							 HttpServletResponse response) throws Throwable {
		
		System.out.println("아이디 검색");
		
		return "/login/idSearch.jsp";
	} //

}
