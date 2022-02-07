package com.bo.qna.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class QnaDAO {
	
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
	
	// insertQna(qdto)
	public void insertQna(QnaDTO qdto) {
		int qno = 0; // 글번호 저장

		try {
			con = getCon();
			System.out.println("디비연결완료");

			// 글번호 계산
			// 3. sql 생성 & pstmt 객체
			sql = "select max(qno) from qna";
			pstmt = con.prepareStatement(sql);

			// 4. sql 구문 실행
			rs = pstmt.executeQuery();
			// 5. 데이터 처리
			if (rs.next()) {
				// 기존의 글번호(저장된최대값) + 1
				qno = rs.getInt(1) + 1;
				// getInt(인덱스) -> 컬럼의 값을 리턴, 만약에 SQL null이면 0리턴
			}
			
			System.out.println(" 글 번호 : " + qno);
			
			// 전달받은 글정보를 DB insert				
			// 3. sql 작성 (insert) & pstmt 객체 생성
			sql = "insert into qna(qno,id,title,content,readcount,"
					+ "re_ref,re_lev,re_seq,reg_date) "
					+ "values(?,?,?,?,?,?,?,?,now())";
			
			pstmt = con.prepareStatement(sql);
			
			// ?
			pstmt.setInt(1, qno);
			pstmt.setString(2, qdto.getId());
			pstmt.setString(3, qdto.getTitle());
			pstmt.setString(4, qdto.getContent());
			pstmt.setInt(5, 0);// 조회수 0으로 초기화
			pstmt.setInt(6, qno);  // re_ref 그룹번호
			pstmt.setInt(7, 0);   //re_lev 레벨값
			pstmt.setInt(8, 0);   // re_seq 순서

			
			// 4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println(" DAO : 글작성 완료! ");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
	}
	
	
	// insertQna()
	

}
