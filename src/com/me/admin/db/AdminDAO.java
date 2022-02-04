package com.me.admin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AdminDAO {
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	
	// 디비연결
	private Connection getCon() throws Exception{
		
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource)initCTX.lookup("java:comp/env/jdbc/meeteat");
		con = ds.getConnection();
		
		return con;
	}
	
	// 자원해제
	public void CloseDB() {
		
		try {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null) {
				pstmt.close();
			}
			if(con!=null) {
				con.close();
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	// 회원목록 조회
	public ArrayList getMemberList(){
		ArrayList memberList = new ArrayList();

		try{
			con = getCon();
			sql = "select a.id, a.pw, a.nickname, a.phone, a.email, a.address, b.user_point, b.user_level, b.reported_count " + 
					"from itwill_member a join member_manage b " + 
					"on a.id=b.id " + 
					"where a.id != 'admin'";
			pstmt = con.prepareStatement(sql);
			//8-3. 실행 -> rs저장
			rs = pstmt.executeQuery();
			while(rs.next()){
				AdminDTO adto = new AdminDTO();
				adto.setEmail(rs.getString("email"));
				adto.setId(rs.getString("id"));
				adto.setNickname(rs.getString("nickname"));
				adto.setPw(rs.getString("pw"));
				adto.setAddress(rs.getString("address"));
				adto.setPhone(rs.getString("phone"));
				adto.setUser_point(rs.getInt("user_point"));
				adto.setUser_level(rs.getInt("user_level"));
				adto.setReported_count(rs.getInt("reported_count"));
				
				//리스트 한 칸에 회원 1명의 정보 저장 
				memberList.add(adto);
			}
			System.out.println("memberList검색완료");	
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		return memberList;
	}//end of getMemberList method
	

}
