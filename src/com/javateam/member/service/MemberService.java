/**
 * 
 */
package com.javateam.member.service;

import java.util.List;

import com.javateam.member.domain.MemberVO;
import com.javateam.member.domain.PagingVO;

/**
 * 회원관리 서비스
 * @author javateam
 *
 */
public interface MemberService {
	
	/**
	 * 회원 가입 처리
	 * @param member 회원 정보 객체
	 * @return 메시지
	 * @throws Exception 예외처리
	 */
	String insertMember(MemberVO member) throws Exception;
	
	/**
	 * 개별 회원 정보 조회(보기)
	 * @param id 회원 아이디
	 * @return 회원 정보 객체
	 * @throws Exception 예외처리
	 */
	MemberVO getMember(String id) throws Exception;
	
	/**
	 * 회원 정보 갱신(수정) 처리
	 * @param member 회원 정보 객체
	 * @return 메시지
	 * @throws Exception 예외처리
	 */
	String updateMember(MemberVO member) throws Exception;
	
	/**
	 * 회원 정보 삭제 처리
	 * @param id 회원 아이디
	 * @return 메시지 
	 * @throws Exception 예외처리
	 */
	String deleteMember(String id) throws Exception;
	
	/**
	 * 아이디 중복 점검
	 * @param id 조회할 회원 아이디
	 * @return 아이디 존재 여부
	 * @throws Exception 예외처리
	 */
	boolean checkId(String id) throws Exception;
	
	/**
	 * 이메일 중복 점검
	 * @param email 조회할 회원 이메일
	 * @return 이메일 존재 여부
	 * @throws Exception 예외처리
	 */
	boolean checkEmail(String email) throws Exception;
	
	/**
	 * 전체 회원 정보 조회(보기)
	 * @return 전체 회원 정보 리스트
	 * @throws Exception 예외처리
	 */
	List<MemberVO> getAllMembers() throws Exception;
	
	/**
	 * 페이징 사용한 전체 회원 정보 조회(보기) 
	 * @param pagingVO 페이징 객체
	 * @return 회원 정보 리스트
	 * @throws Exception 예외처리
	 */
	List<MemberVO> getAllMembers(PagingVO pagingVO) throws Exception;

	/**
	 * 이름 검색 기능
	 * @param name 회원명
	 * @return 검색 회원정보(들)
	 */
	List<MemberVO> getMemberByName(String name);
	
	/**
	 * 페이징 사용한 이름 검색 기능
	 * @param name 회원명
	 * @param pagingVO 페이징 객체
	 * @return 검색 회원정보(들)
	 * @throws Exception 예외처리
	 */
	List<MemberVO> getMemberByName(String name, 
								   PagingVO pagingVO) throws Exception;
	
	/**
	 * 회원 여부 점검 ex) 로그인 처리
	 * @param id 회원 아이디
	 * @param pw 회원 패쓰워드
	 * @return 로그인 메시지
	 * @throws Exception 예외처리
	 */
	String hasMember(String id, String pw) throws Exception;

	/**
	 * 분실된 아이디 검색 
	 * @param email 이메일
	 * @param mobile 휴대폰
	 * @return 분실된 아이디
	 * @throws Exception 예외처리
	 */
	String getIdByEmailMobile(String email, String mobile) throws Exception;

	/**
	 * 분실된 패쓰워드 검색
	 * @param id 아이디
	 * @param email 이메일
	 * @param mobile 휴대폰
	 * @return 분실된 패쓰워드
	 * @throws Exception 예외처리
	 */
	String getPwByIdEmailMobile(String id, String email, String mobile) throws Exception;
	
	/**
	 * 회원 Role(계층) 확인
	 * @param id 아이디
	 * @return 회원 role(계층)
	 * @throws Exception 예외처리
	 */
	int getRoleById(String id) throws Exception;
}
