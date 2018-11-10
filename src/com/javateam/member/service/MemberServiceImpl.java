/**
 * 
 */
package com.javateam.member.service;

import java.sql.Connection;
import java.util.List;

import com.javateam.member.domain.MemberVO;
import com.javateam.member.domain.PagingVO;
import com.javateam.member.persistent.MemberDAO;
import com.javateam.member.persistent.MemberDAOImpl;
import com.javateam.member.util.DbUtil;

/**
 * 회원관리 서비스(구현 클래스)
 * @author javateam
 *
 */
public class MemberServiceImpl implements MemberService {
	
	// 서비스 싱글턴 객체
	private static MemberServiceImpl instance = null;
	
	private MemberServiceImpl() {}
	
	public static final MemberServiceImpl getInstance() {
		
		if (instance == null) {
			instance = new MemberServiceImpl();
		}
		
		return instance;
	} // 

	/**
	 * @see com.javateam.member.service.MemberService#insertMember(com.javateam.member.domain.MemberVO)
	 */
	@Override
	public String insertMember(MemberVO member) throws Exception {

		String msg = "";
		System.out.println("MemberService insertMember");
		Connection con = DbUtil.connect("jdbc/xe");
		
		// DAO/transaction
		try {
				con.setAutoCommit(false); // 수동 모드 전환
				MemberDAO dao = MemberDAOImpl.getInstance();
				
				if (dao.insertMember(member)==1) {
					 msg = "회원정보 저장에 성공하였습니다.";
					 System.out.println(msg);
				     con.commit();
				} else {
					 msg = "회원정보 저장에 실패하였습니다.";
					 System.out.println(msg);
				     con.rollback();
				 	 // throw new Exception();
				} //
	
		} catch (Exception e) {
			System.out.println("MemberService insertMember : ");
			con.rollback();
			e.printStackTrace();
		} finally {
			DbUtil.close(con);	
		}			
		
		return msg;
	} //

	/**
	 * @see com.javateam.member.service.MemberService#getMember(java.lang.String)
	 */
	@Override
	public MemberVO getMember(String id) throws Exception {

		MemberVO member = null;
		System.out.println("MemberService getMember");
		Connection con = DbUtil.connect("jdbc/xe");
		
		// DAO
		try {
				MemberDAO dao = MemberDAOImpl.getInstance();
				member = dao.getMember(id);
	
		} catch (Exception e) {
			System.out.println("MemberService getMember: ");
			System.out.println("회원정보를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con);	
		}			
		
		return member;
	} //

	/**
	 * @see com.javateam.member.service.MemberService#updateMember(com.javateam.member.domain.MemberVO)
	 */
	@Override
	public String updateMember(MemberVO member) throws Exception {
		
		String msg = "";
		System.out.println("MemberService updateMember");
		Connection con = DbUtil.connect("jdbc/xe");
		
		// DAO/transaction
		try {
				con.setAutoCommit(false); // 수동 모드 전환
				MemberDAO dao = MemberDAOImpl.getInstance();
				
				if (dao.updateMember(member)==1) {
					 msg = "회원정보 갱신(수정)에 성공하였습니다.";
					 System.out.println(msg);
				     con.commit();
				} else {
					 msg = "회원정보 갱신(수정)에 실패하였습니다.";
					 System.out.println(msg);
				     con.rollback();
				} //
	
		} catch (Exception e) {
			System.out.println("MemberService updateMember: ");
			con.rollback();
			e.printStackTrace();
		} finally {
			DbUtil.close(con);	
		}			
		
		return msg;
	} //
	

	/**
	 * @see com.javateam.member.service.MemberService#deleteMember(java.lang.String)
	 */
	@Override
	public String deleteMember(String id) throws Exception {

		String msg = "";
		System.out.println("MemberService deleteMember");
		Connection con = DbUtil.connect("jdbc/xe");
		
		// DAO/transaction
		try {
				con.setAutoCommit(false); // 수동 모드 전환
				MemberDAO dao = MemberDAOImpl.getInstance();
				
				if (dao.deleteMember(id)==1) {
					 msg = "회원정보 삭제에 성공하였습니다.";
					 System.out.println(msg);
				     con.commit();
				} else {
					 msg = "회원정보 삭제에 실패하였습니다.";
					 System.out.println(msg);
				     con.rollback();
				} //
	
		} catch (Exception e) {
			System.out.println("MemberService deleteMember: ");
			con.rollback();
			e.printStackTrace();
		} finally {
			DbUtil.close(con);	
		}			
		
		return msg;
	}

	/**
	 * @see com.javateam.member.service.MemberService#checkId(java.lang.String)
	 */
	@Override
	public boolean checkId(String id) throws Exception {

		boolean flag = false;
		System.out.println("MemberService checkId");
		Connection con = DbUtil.connect("jdbc/xe");
		
		// DAO
		try {
				MemberDAO dao = MemberDAOImpl.getInstance();
				flag = dao.isMember(id);
	
		} catch (Exception e) {
			System.out.println("MemberService checkId : ");
			System.out.println("회원 아이디를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con);	
		}			
		
		return flag;
	} //

	/**
	 * @see com.javateam.member.service.MemberService#checkEmail(java.lang.String)
	 */
	@Override
	public boolean checkEmail(String email) throws Exception {

		boolean flag = false;
		System.out.println("MemberService checkEmail");
		Connection con = DbUtil.connect("jdbc/xe");
		
		// DAO
		try {
				MemberDAO dao = MemberDAOImpl.getInstance();
				flag = dao.isEmail(email);
	
		} catch (Exception e) {
			System.out.println("MemberService checkEmail : ");
			System.out.println("회원 이메일을 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con);	
		}			
		
		return flag;
	}

	/**
	 * @see com.javateam.member.service.MemberService#getAllMembers()
	 */
	@Override
	public List<MemberVO> getAllMembers() throws Exception {

		List<MemberVO> members = null;
		System.out.println("MemberService getAllMembers");
		Connection con = DbUtil.connect("jdbc/xe");
		
		// DAO
		try {
				MemberDAO dao = MemberDAOImpl.getInstance();
				members = dao.getAllMembers();
	
		} catch (Exception e) {
			System.out.println("MemberService getAllMembers : ");
			System.out.println("전체 회원정보를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con);	
		}			
		
		return members;
	} //

	/**
	 * @see com.javateam.member.service.MemberService#getAllMembers(com.javateam.member.domain.PagingVO)
	 */
	@Override
	public List<MemberVO> getAllMembers(PagingVO pagingVO) throws Exception {

		List<MemberVO> members = null;
		System.out.println("MemberService getAllMembers 페이징");
		Connection con = DbUtil.connect("jdbc/xe");
		
		// DAO
		try {
				MemberDAO dao = MemberDAOImpl.getInstance();
				members = dao.getAllMembers(pagingVO);
	
		} catch (Exception e) {
			System.out.println("MemberService getAllMembers 페이징 : ");
			System.out.println("회원정보(페이징)를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con);	
		}			
		
		return members;
	}

	/**
	 * @see com.javateam.member.service.MemberService#getMemberByName(java.lang.String)
	 */
	@Override
	public List<MemberVO> getMemberByName(String name) {

		List<MemberVO> members = null;
		System.out.println("MemberService getMemberByName");
		Connection con = DbUtil.connect("jdbc/xe");
		
		// DAO
		try {
				MemberDAO dao = MemberDAOImpl.getInstance();
				members = dao.getMemberByName(name);
	
		} catch (Exception e) {
			System.out.println("MemberService getMemberByName : ");
			System.out.println("회원정보를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con);	
		}			
		
		return members;
	} //

	/**
	 * @see com.javateam.member.service.MemberService#getMemberByName(java.lang.String, com.javateam.member.domain.PagingVO)
	 */
	@Override
	public List<MemberVO> getMemberByName(String name, PagingVO pagingVO) throws Exception {

		List<MemberVO> members = null;
		System.out.println("MemberService getMemberByName");
		Connection con = DbUtil.connect("jdbc/xe");
		
		// DAO
		try {
				MemberDAO dao = MemberDAOImpl.getInstance();
				members = dao.getMemberByName(name, pagingVO);
	
		} catch (Exception e) {
			System.out.println("MemberService getMemberByName : ");
			System.out.println("회원정보를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con);	
		}			
		
		return members;
	} //

	/**
	 * @see com.javateam.member.service.MemberService#hasMember(java.lang.String, java.lang.String)
	 */
	@Override
	public String hasMember(String id, String pw) throws Exception {

		System.out.println("MemberService hasMember");
		String msg = "";
		Connection con = DbUtil.connect("jdbc/xe");
		
		// DAO
		try {
				MemberDAO dao = MemberDAOImpl.getInstance();
				
				if (dao.isMember(id)==false) { // 아이디 존재하지 않음.
				
					msg = "존재하지 않는 회원 정보입니다.";
				
				} else { // 아이디 존재함
					
					if (dao.hasMember(id, pw)==true) {
						
						msg = "회원정보가 존재합니다.";
						
					} else {
						
						msg = "패쓰워드가 틀렸습니다.";
					} // 
					
				} //
	
		} catch (Exception e) {
			System.out.println("MemberService hasMember : ");
			System.out.println("회원정보를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con);	
		}			
		
		return msg;
	}

	/**
	 * @see com.javateam.member.service.MemberService
	 * #getIdByEmailMobile(java.lang.String, java.lang.String)
	 */
	@Override
	public String getIdByEmailMobile(String email, String mobile) {
	
		System.out.println("MemberService getIdByEmailMobile : ");
		String id = "";
		Connection con = DbUtil.connect("jdbc/xe");
		
		try {
				MemberDAO dao = MemberDAOImpl.getInstance();
				id = dao.getIdByEmailMobile(email, mobile);
			
		} catch (Exception e) {
			System.out.println("MemberService getIdByEmailMobile : ");
			System.out.println("회원 아이디를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con);	
		} //
		
		return id;
	} //

	/**
	 * @see com.javateam.member.service.MemberService#getPwByIdEmailMobile(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getPwByIdEmailMobile(String id, String email, String mobile) {

		System.out.println("MemberService getPwByIdEmailMobile : ");
		String pw = "";
		Connection con = DbUtil.connect("jdbc/xe");
		
		try {
				MemberDAO dao = MemberDAOImpl.getInstance();
				pw = dao.getIdByIdEmailMobile(id, email, mobile);
			
		} catch (Exception e) {
			System.out.println("MemberService getIdByIdEmailMobile : ");
			System.out.println("회원 패쓰워드를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con);	
		} //
		
		return pw;
	}

	/**
	 * @see com.javateam.member.service.MemberService#getRoleById(java.lang.String)
	 */
	@Override
	public int getRoleById(String id) throws Exception {

		System.out.println("getRoleById");
		int role = 0;
		Connection con = DbUtil.connect("jdbc/xe");
		
		try {
				MemberDAO dao = MemberDAOImpl.getInstance();
				role = dao.getRoleById(id);
			
		} catch (Exception e) {
			System.out.println("MemberService getRoleById : ");
			System.out.println("회원 role(계층)을 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con);	
		} //
		
		return role;
	}

}