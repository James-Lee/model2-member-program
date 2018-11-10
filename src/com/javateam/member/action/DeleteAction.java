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
public class DeleteAction implements CommandAction {

	/**
	 * @see com.javateam.member.controller.CommandAction#requestPro(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String requestPro(HttpServletRequest request, 
							 HttpServletResponse response) throws Throwable {

		System.out.println("회원 정보 삭제 처리");
		String path = ""; // 이동 경로(JSP)
		
		// 인자 수신 점검 : id
		if (request.getParameter("id")==null ||
			request.getParameter("id").trim().equals("")) {
		
			System.out.println("error !");
			path="/member/result_msg.jsp";
			request.setAttribute("msg", "회원 아이디를 입력하십시오.");
			request.setAttribute("return_page", "delete.html");
			
		} else {
			
			// 공백이 "+"로 변환되어 전송되므로 "+" 치환/제거후 점검 
			System.out.println(request.getParameter("id"));
			
			// get 방식일 경우 공백이 "+" 입력되는 현상 있음.
			String id = request.getParameter("id").replaceAll("\\+", "").trim();
			System.out.println("아이디 : "+id);
		
			// 회원정보 갱신 : 기존 회원정보 보기
			// Service/DAO
			MemberService service = MemberServiceImpl.getInstance();
			MemberVO member = service.getMember(id);
			
			if (member.getId() == null) { // 삭제할 회원 정보가 없을 경우
				
				System.out.println("회원정보가 없습니다.");
				path="/member/result_msg.jsp";
				request.setAttribute("msg", "회원 정보가 없습니다. 삭제할 회원 아이디를 다시 입력하십시오.");
				request.setAttribute("return_page", "delete.html");
				
			} else { // 회원 정보가 존재할 경우 : 삭제 가능

				String msg = service.deleteMember(id);
				
				request.setAttribute("result", msg);
				
	            path = "/member/result.jsp";	
			} // if 
			
		} // if 
		
		return path;
	} //

}
