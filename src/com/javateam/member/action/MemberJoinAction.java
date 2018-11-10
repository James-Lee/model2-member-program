/**
 * 
 */
package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.domain.MemberVO;
import com.javateam.member.service.MemberService;
import com.javateam.member.service.MemberServiceImpl;

/**
 * @author javateam
 *
 */
public class MemberJoinAction implements CommandAction {

	/**
	 * @see com.javateam.member.controller.CommandAction#requestPro(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String requestPro(HttpServletRequest request, 
						     HttpServletResponse response) throws Throwable {

		System.out.println("memberJoin");
		
		MemberVO member = new MemberVO(request.getParameterMap());
		System.out.println(member);
		
		// Service/DAO
		MemberService service = MemberServiceImpl.getInstance();
		String msg = service.insertMember(member);
		
		request.setAttribute("result", msg);
		
		return "/member/result.jsp";
	} //

}
