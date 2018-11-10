/**
 * 
 */
package com.javateam.member.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * DB 연결 유틸리티(utility)
 * 
 * @author javateam
 *
 */
public class DbUtil {
	
	/**
	 * DBCP/JNDI 연결(connect)
	 *  
	 * @param jndi dbcp/jndi명 ex) jdbc/xe 
	 * @return Connection DB 연결 객체
	 */
	public static Connection connect(String jndi) {
		
		Connection con = null;
		
		try {
			Context initContext = new InitialContext();
			DataSource ds = (DataSource)initContext.lookup("java:/comp/env/"+jndi);
			con = ds.getConnection();
			
		} catch (NamingException e) {
			System.out.println("DBUtil Connect NE : ");
			e.printStackTrace();
		} catch (SQLException e) {	
		// } catch (NamingException | SQLException e) { // Java 1.7 over
			System.out.println("DBUtil Connect SQLE : ");
			e.printStackTrace();
		}
		
		return con;
	} //
	
	/**
	 * DBCP/JNDI 자원 반납(해제)
	 * 
	 * @param con DB 연결 객체
	 */
	public static void close(Connection con) {
		
		try {
				if (con != null) con.close();
		} catch (Exception e) {
			System.out.println("DbUtil close E : ");
			e.printStackTrace();
		} //
		
	} //
	
	/**
	 * DBCP/JNDI 자원 반납(해제)
	 * 
	 * @param con DB 연결 객체
	 * @param pstmt SQL 처리 객체
	 * @param rs SQL 결과셋 객체
	 */
	public static void close(Connection con,
							 PreparedStatement pstmt, 
							 ResultSet rs) {
		try {
				if (con != null) con.close();
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
		} catch (Exception e) {
			System.out.println("DbUtil close E : ");
			e.printStackTrace();
		} //
		
	} //

}
