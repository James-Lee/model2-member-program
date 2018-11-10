package com.javateam.member.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.javateam.member.service.MemberService;
import com.javateam.member.service.MemberServiceImpl;

/**
 * 로그인 인증 점검 필터
 * Servlet Filter implementation class AuthCheckFilter
 */
@WebFilter("/*")
public class AuthCheckFilter implements Filter {
	
    /**
     * Default constructor. 
     */
    public AuthCheckFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, 
						 ServletResponse response, 
						 FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String movePath = "";

		// 컨텍스트 패쓰(ContextPath)을 제외한 순수 페이지 검출 ex) view.do
		String page = req.getRequestURI().replaceAll(req.getContextPath(), "");
		System.out.println("로그인 세션 정보 : "+session.getAttribute("LOGIN_SESS"));
		System.out.println("이동 page : "+ page);
		
		boolean pageAuthFlag = this.hasAuthRequiredPages(page);
		
		System.out.println("pageAuthFlag : "+ pageAuthFlag);

		// 로그인 인증이 필요한 페이지인지 점검
		if (session.getAttribute("LOGIN_SESS") == null &&
		    pageAuthFlag == true) { 
			
			// 로그인 페이지로 이동
			// movePath = "/login/login.html";
			movePath = "/loginForm.do";
			
			// 페이지 이동
			RequestDispatcher dispatcher 
		    	= request.getRequestDispatcher(movePath);
		    dispatcher.forward(request, response);
			
	        // 주의) Controller에서 forward 구조로 이동하므로
	        // res.sendRedirect() 형태의 이동은 에러 출력됨.
	        
	        // 이동하려던 페이지 저장
	        session.setAttribute("page", page);
	        
		} else if (session.getAttribute("LOGIN_SESS") == null &&
			       pageAuthFlag == false) {
			
			/*movePath = "/loginForm.do";
			
			// 페이지 이동
			RequestDispatcher dispatcher 
		    	= request.getRequestDispatcher(movePath);
		    dispatcher.forward(request, response);*/
	        
	        // 이동하려던 페이지 저장
	        session.setAttribute("page", page);
	        
		} else { // 세션 생성시
			
			if (session.getAttribute("LOGIN_SESS") != null) {
				
				System.out.println("로그인 인증");
				// 로그인 인증 세션
				String loginId = (String)session.getAttribute("LOGIN_SESS");
				
				try {
					// 페이지에 따른 사용자 role(계층) 점검
					boolean pageFlag = this.hasAuthRequiredPages(loginId, page);
					
					// 로그아웃 여부 점검 : 로그아웃 버그 방지 패치
					String logoutYn = (String)request.getAttribute("logout_yn");
					System.out.println("logoutYn : "+logoutYn);
					
					System.out.println("\n-----------------------");
					System.out.println("pageFlag : "+pageFlag);
					System.out.println("-----------------------\n");
					
					if (pageFlag == true) {
						
						System.out.println("인증 조건 통과");
						
				        // 이동하려던 페이지 저장
				        session.setAttribute("page", page);
				        
					} else if (pageFlag == false) {
						
						// role 경고 페이지로 이동
						// movePath = "/loginForm.do";
					     movePath = "/role_fail.do";
						
						System.out.println("사용자 Role(계층)이 페이지와 맞지 않습니다.");
						
						// 페이지 이동
						RequestDispatcher dispatcher 
					    	= request.getRequestDispatcher(movePath);
					    dispatcher.forward(request, response);
				        
				        // 이동하려던 페이지 교정 : 비인가 페이지 이동 방지
				        session.setAttribute("page", "main.html");
					} // if
					
				} catch (Exception e) {
					e.printStackTrace();
				} // try
				
			} // 로그인 인증시 
			
			
		} // if
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	
	/**
	 * 인증 필수 요구 페이지 여부 점검
	 * @param page 페이지
	 * @return 인증 필수 요구 페이지 여부 
	 */
	private boolean hasAuthRequiredPages(String page) {
		
		boolean flag = false;
		
		// 로그인 인증을 필요로 하는 페이지들 등록
		List<String> pages = new ArrayList<>();
		pages.add("/viewAllPaging.do");
		pages.add("/viewAll.do");
		pages.add("/view.do");
		pages.add("/search.do");
		
		flag = pages.contains(page) ? true : false;
		
		System.out.println("flag : "+flag);
		
		return flag;
	} //
	
	/**
	 * 인증 필수 요구 페이지 여부 점검
	 * @param page 페이지
	 * @param degree 등급(role) ex) 관리자 = 4 이상
	 * @return 인증 필수 요구 페이지 여부 
	 * @throws Exception 
	 */
	@SuppressWarnings("unused")
	private boolean hasAuthRequiredPages(String id, 
										 String page) throws Exception {
		boolean flag = true; // 초기 설정
		
		// 로그인 인증을 필요로 하는 페이지들 등록
		Map<String, Integer> pagesRole = new TreeMap<>();
		pagesRole.put("/viewAllPaging.do", 4);
		pagesRole.put("/viewAll.do", 4);
		pagesRole.put("/view.do", 1);
		pagesRole.put("/search.do", 4);
		pagesRole.put("/logout.do", 1);
		pagesRole.put("/member/main.html", 0);
		
		int role = 0;
		
		// Role 점검 가능한 페이지 여부 ex) ~.do, ~.html 
		boolean extFlag = page.contains(".do") || page.contains(".html") ? true : false;
		
		if (pagesRole.containsKey(page) && extFlag == true) { // 해당되는 페이지 검색
				
			// 데이터베이스 Role 테이블의 값과 비교
			MemberService service = MemberServiceImpl.getInstance();
			
			role = service.getRoleById(id);
			
			// role(계층) 비교
			if (role >= pagesRole.get(page)) {
				flag = true;
			}  else {
				flag = false;
			} //
		} //
		
		System.out.println("#############################");
		System.out.println("로그인 사용자 role : "+ role);
		System.out.println("페이지 : "+ page);
		System.out.println("flag : "+ flag);
		System.out.println("#############################");
		
		
		return flag;
	} //
	
} //