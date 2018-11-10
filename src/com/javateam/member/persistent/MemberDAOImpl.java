/**
 * 
 */
package com.javateam.member.persistent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.javateam.member.domain.MemberVO;
import com.javateam.member.domain.PagingVO;
import com.javateam.member.util.DbUtil;

/**
 * 회원 관리 DAO 구현 클래스
 * @author javateam
 *
 */
public class MemberDAOImpl implements MemberDAO {

	// 싱글턴 객체
	// MemberDAO dao = MemberDAOImpl.getInstance();
	// 1) 생성자 내부 사용(private)
	// 2) instance 멤버 필드
	// 3) getInstance 간접 생성자(정적/final)
	
	// TODO
	private static MemberDAOImpl instance = null;
	
	private MemberDAOImpl() {}
	
	public static final MemberDAOImpl getInstance() {
		
		if (instance == null) {
			instance = new MemberDAOImpl();
		}
		
		return instance;
	} // 
	
	/**
	 * @see com.javateam.member.persistent.MemberDAO#insertMember(com.javateam.member.domain.MemberVO)
	 */
	@Override
	public int insertMember(MemberVO member) { // throws Exception {
		
		Connection con = DbUtil.connect("jdbc/xe");
		String sql = "INSERT INTO member_tbl " 
				   + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		int flag = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPw());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getGender()+"");
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getMobile());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getZip1());
			pstmt.setString(9, member.getAddress1());
			pstmt.setString(10, member.getZip2());
			pstmt.setString(11, member.getAddress2());
			pstmt.setDate(12, member.getBirthday());
			pstmt.setDate(13, member.getJoindate());
			
			flag = (pstmt.executeUpdate()==1) ? 1: 0;
				
		} catch (Exception e) {
			System.out.println("MemberDAO insertMember : ");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, null);	
		} //
		
		return flag;
	} //

	/**
	 * @see com.javateam.member.persistent.MemberDAO#getMember(java.lang.String)
	 */
	@Override
	public MemberVO getMember(String id) throws Exception {

		MemberVO member = new MemberVO();
		Connection con = DbUtil.connect("jdbc/xe");
		String sql = "SELECT * FROM member_tbl WHERE id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setGender(rs.getString("gender").charAt(0));
				member.setEmail(rs.getString("email"));
				member.setMobile(rs.getString("mobile"));
				member.setPhone(rs.getString("phone"));
				member.setZip1(rs.getString("zip1"));
				member.setAddress1(rs.getString("address1"));
				member.setZip2(rs.getString("zip2"));
				member.setAddress2(rs.getString("address2"));
				member.setBirthday(rs.getDate("birthday"));
				member.setJoindate(rs.getDate("joindate"));
			} //
				
		} catch (Exception e) {
			System.out.println("MemberDAO getMember : ");
			System.out.println("회원정보를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, rs);	
		} //
		
		return member;
	} //

	/**
	 * @see com.javateam.member.persistent.MemberDAO#updateMember(com.javateam.member.domain.MemberVO)
	 */
	@Override
	public int updateMember(MemberVO member) { // throws Exception {

		Connection con = DbUtil.connect("jdbc/xe");
		String sql = "UPDATE member_tbl SET " 
				   + "pw=?,"
				   + "email=?,"
				   + "mobile=?,"
				   + "phone=?,"
				   + "zip1=?,"
				   + "address1=?,"
				   + "zip2=?,"
				   + "address2=? "
				   + "WHERE id=?";
		
		PreparedStatement pstmt = null;
		int flag = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getPw());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getMobile());
			pstmt.setString(4, member.getPhone()+"");
			pstmt.setString(5, member.getZip1());
			pstmt.setString(6, member.getAddress1());
			pstmt.setString(7, member.getZip2());
			pstmt.setString(8, member.getAddress2());
			pstmt.setString(9, member.getId());
			
			flag = (pstmt.executeUpdate()==1) ? 1: 0;
				
		} catch (Exception e) {
			System.out.println("MemberDAO updateMember : ");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, null);	
		} //
		
		return flag;
	} //

	/**
	 * @see com.javateam.member.persistent.MemberDAO#deleteMember(java.lang.String)
	 */
	@Override
	public int deleteMember(String id) throws Exception {

		Connection con = DbUtil.connect("jdbc/xe");
		String sql = "DELETE member_tbl WHERE id=?";
		
		PreparedStatement pstmt = null;
		int flag = 0;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			flag = (pstmt.executeUpdate()==1) ? 1: 0;
				
		} catch (Exception e) {
			System.out.println("MemberDAO deleteMember : ");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, null);	
		} //
		
		return flag;
	}

	/**
	 * @see com.javateam.member.persistent.MemberDAO#isMember(java.lang.String)
	 */
	@Override
	public boolean isMember(String id) throws Exception {

		boolean flag = false;
		Connection con = DbUtil.connect("jdbc/xe");
		String sql = "SELECT * FROM member_tbl WHERE id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			flag = rs.next() ? true : false;
				
		} catch (Exception e) {
			System.out.println("MemberDAO isMember : ");
			System.out.println("회원정보를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, rs);	
		} //
		
		return flag;
	}

	/**
	 * @see com.javateam.member.persistent.MemberDAO#isEmail(java.lang.String)
	 */
	@Override
	public boolean isEmail(String email) throws Exception {

		boolean flag = false;
		Connection con = DbUtil.connect("jdbc/xe");
		String sql = "SELECT count(*) FROM member_tbl WHERE email=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				flag = rs.getInt(1)==1 ? true : false;
			}
			
			System.out.println("flag : "+flag);
				
		} catch (Exception e) {
			System.out.println("MemberDAO isEmail : ");
			System.out.println("회원 이메일을 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, rs);	
		} //
		
		return flag;
	}

	/**
	 * @see com.javateam.member.persistent.MemberDAO#getAllMembers()
	 */
	@Override
	public List<MemberVO> getAllMembers() throws Exception {

		List<MemberVO> members = new ArrayList<>();
		MemberVO member = null;
		Connection con = DbUtil.connect("jdbc/xe");
		String sql = "SELECT * FROM member_tbl";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setGender(rs.getString("gender").charAt(0));
				member.setEmail(rs.getString("email"));
				member.setMobile(rs.getString("mobile"));
				member.setPhone(rs.getString("phone"));
				member.setZip1(rs.getString("zip1"));
				member.setAddress1(rs.getString("address1"));
				member.setZip2(rs.getString("zip2"));
				member.setAddress2(rs.getString("address2"));
				member.setBirthday(rs.getDate("birthday"));
				member.setJoindate(rs.getDate("joindate"));
				
				members.add(member);
			} //
				
		} catch (Exception e) {
			System.out.println("MemberDAO getAllMembers : ");
			System.out.println("전체 회원정보를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, rs);	
		} //
		
		return members;
	} //

	/**
	 * @see com.javateam.member.persistent.MemberDAO#getAllMembers(com.javateam.member.domain.PagingVO)
	 */
	@Override
	public List<MemberVO> getAllMembers(PagingVO pagingVO) throws Exception {

		
		List<MemberVO> members = new ArrayList<>();
		Connection con = DbUtil.connect("jdbc/xe");
		MemberVO member = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null; 
		String sql = "SELECT *" + 
		   			 "   FROM (SELECT ROWNUM, " + 
		   			 "	       		  m.*," + 
		   			 "	       		  FLOOR((ROWNUM - 1)/? + 1) page " + 
		   			 "	          FROM (" + 
		   			 "	                  SELECT * FROM member_tbl " + 
		   			 "	                  ORDER BY id ASC " + 
		   			 "	                ) m " + 
		   			 "	       ) " + 
		   			 "WHERE page = ?";
		
		try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pagingVO.getRowPerPage());
				pstmt.setInt(2, pagingVO.getCurPage()); 
				rs = pstmt.executeQuery(); 
				
				while (rs.next()) { 
					
					member = new MemberVO();
					
					member.setId(rs.getString("id"));
					member.setPw(rs.getString("pw"));
					member.setName(rs.getString("name"));
					member.setGender(rs.getString("gender").charAt(0));
					member.setEmail(rs.getString("email"));
					member.setMobile(rs.getString("mobile"));
					member.setPhone(rs.getString("phone"));
					member.setZip1(rs.getString("zip1"));
					member.setAddress1(rs.getString("address1"));
					member.setZip2(rs.getString("zip2"));
					member.setAddress2(rs.getString("address2"));
					member.setBirthday(rs.getDate("birthday"));
					member.setJoindate(rs.getDate("joindate"));
					
					members.add(member);
				} // 
				
		} catch (Exception e) {
			System.out.println("MemberDAO getAllMembers 페이징 : ");
			System.out.println("회원정보(페이징)를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, rs);	
		}
		
		return members;
	} //

	/**
	 * @see com.javateam.member.persistent.MemberDAO#getMemberByName(java.lang.String)
	 */
	@Override
	public List<MemberVO> getMemberByName(String name) {

		List<MemberVO> members = new ArrayList<>();
		MemberVO member = null;
		Connection con = DbUtil.connect("jdbc/xe");
		String sql = "SELECT * FROM member_tbl "
				   + "WHERE name=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setGender(rs.getString("gender").charAt(0));
				member.setEmail(rs.getString("email"));
				member.setMobile(rs.getString("mobile"));
				member.setPhone(rs.getString("phone"));
				member.setZip1(rs.getString("zip1"));
				member.setAddress1(rs.getString("address1"));
				member.setZip2(rs.getString("zip2"));
				member.setAddress2(rs.getString("address2"));
				member.setBirthday(rs.getDate("birthday"));
				member.setJoindate(rs.getDate("joindate"));
				
				members.add(member);
			} //
				
		} catch (Exception e) {
			System.out.println("MemberDAO getMemberByName : ");
			System.out.println("회원정보를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, rs);	
		} //
		
		return members;
	} //

	/**
	 * @see com.javateam.member.persistent.MemberDAO#getMemberByName(java.lang.String, com.javateam.member.domain.PagingVO)
	 */
	@Override
	public List<MemberVO> getMemberByName(String name, PagingVO pagingVO) throws Exception {

		List<MemberVO> members = new ArrayList<>();
		MemberVO member = null;
		Connection con = DbUtil.connect("jdbc/xe");
		String sql = "SELECT *" + 
		   			 "   FROM (SELECT ROWNUM, " + 
		   			 "	       		  m.*," + 
		   			 "	       		  FLOOR((ROWNUM - 1)/? + 1) page " + 
		   			 "	          FROM (" + 
		   			 "	                  SELECT * FROM member_tbl " +
		   			 "					  WHERE name = ? " +
		   			 "	                  ORDER BY id ASC " + 
		   			 "	                ) m " + 
		   			 "	       ) " + 
		   			 "WHERE page = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pagingVO.getRowPerPage());
			pstmt.setString(2, name);
			pstmt.setInt(3, pagingVO.getCurPage()); 
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setPw(rs.getString("pw"));
				member.setName(rs.getString("name"));
				member.setGender(rs.getString("gender").charAt(0));
				member.setEmail(rs.getString("email"));
				member.setMobile(rs.getString("mobile"));
				member.setPhone(rs.getString("phone"));
				member.setZip1(rs.getString("zip1"));
				member.setAddress1(rs.getString("address1"));
				member.setZip2(rs.getString("zip2"));
				member.setAddress2(rs.getString("address2"));
				member.setBirthday(rs.getDate("birthday"));
				member.setJoindate(rs.getDate("joindate"));
				
				members.add(member);
			} //
				
		} catch (Exception e) {
			System.out.println("MemberDAO getMemberByName : ");
			System.out.println("회원정보를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, rs);	
		} //
		
		return members;
	} //

	/**
	 * @see com.javateam.member.persistent.MemberDAO#hasMember(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean hasMember(String id, String pw) throws Exception {

		System.out.println("MemberDAO hasMember");
		boolean flag = false;
		
		Connection con = DbUtil.connect("jdbc/xe");
		String sql = "SELECT * FROM member_tbl "
				   + "WHERE id=? AND pw=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			flag = rs.next()==true ? true : false;
				
		} catch (Exception e) {
			System.out.println("MemberDAO hasMember : ");
			System.out.println("회원정보를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, rs);	
		} //
		
		return flag;
	}

	/**
	 * @see com.javateam.member.persistent.MemberDAO
	 * #getIdByEmailMobile(java.lang.String, java.lang.String)
	 */
	@Override
	public String getIdByEmailMobile(String email, String mobile) {
	
		System.out.println("MemberDAO getIdByEmailMobile");
		String id = "";
		
		Connection con = DbUtil.connect("jdbc/xe");
		String sql = "SELECT id FROM member_tbl "
				   + "WHERE email=? AND mobile=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, mobile);
			rs = pstmt.executeQuery();
			
			id = rs.next()==true ? rs.getString(1) : "";
				
		} catch (Exception e) {
			System.out.println("MemberDAO getIdByEmailMobile : ");
			System.out.println("회원정보를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, rs);	
		} //
		
		return id;
	} // 

	/**
	 * @see com.javateam.member.persistent.MemberDAO#getIdByIdEmailMobile(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getIdByIdEmailMobile(String id, String email, String mobile) {

		System.out.println("MemberDAO getPwByIdEmailMobile");
		String pw = "";
		
		Connection con = DbUtil.connect("jdbc/xe");
		String sql = "SELECT pw FROM member_tbl "
				   + "WHERE id=? "
				   + "AND email=? "
				   + "AND mobile=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, email);
			pstmt.setString(3, mobile);
			rs = pstmt.executeQuery();
			
			pw = rs.next()==true ? rs.getString(1) : "";
				
		} catch (Exception e) {
			System.out.println("MemberDAO getIdByIdEmailMobile : ");
			System.out.println("회원정보를 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, rs);	
		} //
		
		return pw;
	} //

	/**
	 * @see com.javateam.member.persistent.MemberDAO#getRoleById(java.lang.String)
	 */
	@Override
	public int getRoleById(String id) throws Exception {

		System.out.println("MemberDAO getRoleById");
		int role = 0;
		
		Connection con = DbUtil.connect("jdbc/xe");
		String sql = "SELECT degree FROM member_role "
				   + "WHERE id=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			role = rs.next()==true ? rs.getInt(1) : 0;
				
		} catch (Exception e) {
			System.out.println("MemberDAO getRoleById : ");
			System.out.println("회원 role(계층)을 확인할 수 없습니다.");
			e.printStackTrace();
		} finally {
			DbUtil.close(con, pstmt, rs);	
		} //
		
		return role;
	} //

}