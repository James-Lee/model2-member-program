/**
 * 
 */
package com.javateam.member.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javateam.member.controller.CommandAction;
import com.javateam.member.domain.MemberVO;
import com.javateam.member.service.MemberService;
import com.javateam.member.service.MemberServiceImpl;

/**
 * @author javateam
 *
 */
public class MemberUpdateAction implements CommandAction {

	/**
	 * @see com.javateam.member.controller.CommandAction#requestPro(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public String requestPro(HttpServletRequest request, 
						     HttpServletResponse response) throws Throwable {

		System.out.println("memberUpdate");
		String msg = "";
		
		Map<String, String[]> paramMap = request.getParameterMap();
		
		// 인자 출력 테스트
		MemberVO member = new MemberVO(paramMap);
		System.out.println("신규 패쓰워드 : "+paramMap.get("new_pw_fld")[0]);
		System.out.println("이메일 : "+paramMap.get("email2")[0]);
		System.out.println("이메일 : "+paramMap.get("email3")[0]);
		
		member.setPw(paramMap.get("new_pw_fld")[0]);
		
		HttpSession session = request.getSession();
		MemberVO oldMember = (MemberVO)(session.getAttribute("LEGACY_MEMBER_INFO"));
		
		// 만약 기존 정보를 세션을 사용하지 않는다면....
		// MemberService service = MemberServiceImpl.getInstance();
		// MemberVO oldMember = service.getMember(paramMap.get("id_fld")[0]);
		// MemberVO oldMember = service.getMember(request.getParameter("id_fld"));
		
		System.out.println("기존");
		System.out.println(oldMember);
		
		System.out.println("------------------------");
		
		System.out.println("신규");
		System.out.println(member);
		
		System.out.println("------------------------");
		
		System.out.println("변경");
		oldMember.compareUpdateFields(member);
		System.out.println("------------------------");
		System.out.println(oldMember);
		
		// 유의사항)
		// 기존의 회원정보와 신규 회원정보를 비교하고 
		// 신규 회원정보가 공백이 이면 기존의 회원정보를 저장하고
		// 신규 회원정보가 공백이 아니라면 신규 회원정보로써 기존 정보를 갱신한다.
		
		// 문) 이 부분에서 회원정보를 업데이트(수정)하는 서비스 부분을 호출하고
		// 부가적으로 서비스단과 DAO 메서드(memberUpdate)를 작성하십시오. 
		
		// 기존 정보 세션"만" 소멸 : invalidate(X)
		
		// Service/DAO
		MemberService service = MemberServiceImpl.getInstance();
		msg = service.updateMember(oldMember);
		
		if (session.getAttribute("LEGACY_MEMBER_INFO") != null)
			session.removeAttribute("LEGACY_MEMBER_INFO");
		
		request.setAttribute("result", msg);
		
		return "/member/result.jsp";
	} //

}
