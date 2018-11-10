/**
 * 
 */
package com.javateam.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.domain.MemberVO;
import com.javateam.member.service.MemberService;
import com.javateam.member.service.MemberServiceImpl;

/**
 * 회원정보 수정(갱신) : 기존 회원정보 보기
 * @author javateacher
 *
 */
public class MemberUpdateViewAction implements CommandAction {

	/**
	 * @see com.javateam.member.controller.CommandAction#requestPro(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String requestPro(HttpServletRequest request, 
							 HttpServletResponse response) throws Throwable {
		
		System.out.println("회원 정보 수정 : 기존 회원 정보 조회");
		String path = ""; // 이동 경로(JSP)
		
		// 인자 수신 점검 : id
		if (request.getParameter("id")==null ||
			request.getParameter("id").trim().equals("")) {
		
			System.out.println("error !");
			path="/member/result_msg.jsp";
			request.setAttribute("msg", "회원 아이디를 입력하십시오.");
			request.setAttribute("return_page", "viewMember.html");
			
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
			
			if (member.getId() == null) { // 조회한 회원 정보가 없을 경우
				
				System.out.println("회원정보가 없습니다.");
				path="/member/result_msg.jsp";
				request.setAttribute("msg", "회원 정보가 없습니다. 다시 조회하십시오.");
				request.setAttribute("return_page", "update.html");
				
			} else { // 회원 정보가 존재할 경우
				
				request.setAttribute("member", member);

				// System.out.println(member);
				
				// 복합 인자 : 주소, 휴대폰, 연락처, 이메일 등등
				String mobile[] = member.getMobile().split("-"); // 필수 항목
				
				String phone[] = member.getPhone()==null ? 
								 new String[3] : member.getPhone().split("-");
								 
				String email[] = member.getEmail().split("\\@");
				String zip21 = member.getZip2()==null ?
							   "" : member.getZip2().substring(0, 3);
				String zip22 = member.getZip2()==null ?
							   "" : member.getZip2().substring(3);
				
				String address1[] = member.getAddress1()==null ?
						            new String[2] : member.getAddress1().split("\\*");
				
	            String address2[] = member.getAddress1()==null ?
	            				    new String[2] : member.getAddress2().split("\\*");
	            
	            // 버그 패치 : 주소를 아예 입력하지 않았을 경우 예외 발생 패치 ex) address1.length<=1		
				String addressTemp1 = address1.length<=1 ? "" : address1[1];
				String addressTemp2 = address2.length<=1 ? "" : address2[1];
				
				// 기존 정보를 세션으로 저장 보존함 : 추후 기존 정보와 갱신 정보 비교를 위한 것.
				HttpSession session = request.getSession();
				/*List<Object> legacyMember = new ArrayList<>();
				legacyMember.add(member);
				legacyMember.add(mobile);
				legacyMember.add(phone);
				legacyMember.add(email);
				legacyMember.add(zip21);
				legacyMember.add(zip22);
				legacyMember.add(address1);
				legacyMember.add(address2);
				legacyMember.add(addressTemp1);
				legacyMember.add(addressTemp2);*/
				
				if (session.getAttribute("LEGACY_MEMBER_INFO")==null)
				   session.setAttribute("LEGACY_MEMBER_INFO", member);
	
				// 전송 인자들 생성
				request.setAttribute("mobile", mobile);
	            request.setAttribute("phone", phone);
	            request.setAttribute("email", email);
	            request.setAttribute("zip21", zip21);
	            request.setAttribute("zip22", zip22);
	            request.setAttribute("address1", address1);
	            request.setAttribute("address2", address2);
	            request.setAttribute("addressTemp1", addressTemp1);
	            request.setAttribute("addressTemp2", addressTemp2);
								
	            path = "/member/memberUpdate.jsp";	
			} // if 
			
		} // if 
		
		return path;
	} // 

}
