package com.bo.qna.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	// insertQna() 끝
	
	// getBoardCount()
	public int getBoardCount(){
		int cnt = 0;
		
		try {
			// 1,2 디비연결
			con = getCon();
			// 3 sql 작성(select) & pstmt 객체
			sql = "select count(*) from qna";
			pstmt = con.prepareStatement(sql);
			// 4 sql 실행
			rs = pstmt.executeQuery();
			// 5 데이터 처리
			if(rs.next()){
				//데이터 있을때 (글개수)
				cnt = rs.getInt(1);	
			}
			
			System.out.println(" DAO : 글개수 ("+cnt+")");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return cnt;
	}
	// getBoardCount() 끝
	
	
	// getQnaList()
	public List getQnaList(){
	List qnaList = new ArrayList();
		
		try {
			// 1,2 디비연결
			con = getCon();
			// 3 sql 작성 & pstmt 객체 생성
			sql = "select * from qna order by qno desc";
			pstmt = con.prepareStatement(sql);
			// 4 sql 실행
			rs = pstmt.executeQuery();
			// 5 데이터처리 (글 1개의 정보 -> DTO 1개 -> ArrayList 1칸)
			while(rs.next()){
				//데이터 있을때 마다 글 1개의 정보를 저장하는 객체 생성
				QnaDTO qdto = new QnaDTO();
				
				qdto.setId(rs.getString("id"));
				qdto.setContent(rs.getString("content"));
				qdto.setReg_date(rs.getTimestamp("reg_date"));
				qdto.setQno(rs.getInt("qno"));
				qdto.setRe_lev(rs.getInt("re_lev"));
				qdto.setRe_ref(rs.getInt("re_ref"));
				qdto.setRe_seq(rs.getInt("re_seq"));
				qdto.setReadcount(rs.getInt("readcount"));
				qdto.setTitle(rs.getString("title"));
				
				// DTO 객체를 ArrayList 한칸에 저장
				qnaList.add(qdto);				
				
			}//while
			
			System.out.println(" DAO : 글 정보 저장완료 "+qnaList.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return qnaList;
	}
	// getQnaList() 끝
	
	// updateReadcount(qno)
	public void updateReadcount(int qno){
		try {
			
			con = getCon();
			sql = "update qna set readcount = readcount+1 where qno=?";
			pstmt = con.prepareStatement(sql);
			
			//?
			pstmt.setInt(1, qno);

			pstmt.executeUpdate();
			
			System.out.println("DAO : 조회수 1증가");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
	}
	// updateReadcount(qno) 끝
	
	
	// getBoard(qno)
	public QnaDTO getQna(int qno){
		QnaDTO qdto = null;
		
		try {
			
			con = getCon();
			sql = "select * from qna where qno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qno);
			
			rs = pstmt.executeQuery();
			
			// 5. 데이터처리
			if(rs.next()){
				qdto = new QnaDTO();
				
				qdto.setId(rs.getString("id"));
				qdto.setContent(rs.getString("content"));
				qdto.setReg_date(rs.getTimestamp("reg_date"));
				qdto.setQno(rs.getInt("qno"));
				qdto.setRe_lev(rs.getInt("re_lev"));
				qdto.setRe_ref(rs.getInt("re_ref"));
				qdto.setRe_seq(rs.getInt("re_seq"));
				qdto.setReadcount(rs.getInt("readcount"));
				qdto.setTitle(rs.getString("title"));
				
			}//if			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		return qdto;
	}
	// getQna(qno)
	
	
	// updateQna(qdto)
	public void updateQna(QnaDTO qdto){
		
		try {
			//1.2. 디비연결
			con = getCon();

			sql = "update qna set title=?,content=? "
							+ "where qno=?";
			pstmt = con.prepareStatement(sql);
					
			pstmt.setString(1, qdto.getTitle());
			pstmt.setString(2, qdto.getContent());
			pstmt.setInt(3, qdto.getQno());
					
			// 4. sql 실행
			pstmt.executeUpdate();
	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
	}
	// updateQna(qdto)
	
	// deleteQna(qno)
	public void deleteQna(int qno){
		
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql
			sql ="delete from qna where re_ref=?";
			pstmt = con.prepareStatement(sql);
					
			pstmt.setInt(1, qno);
			// 4. sql 실행
			pstmt.executeUpdate();				
			
			System.out.println(" DAO : QnA 글 삭제 완료");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}		
	}
	// deleteQna(qno) 끝
	

	// replyQna(qdto)
	public void replyBoard(QnaDTO qdto) {
		int qno = 0;
		
		
		try {
			// 답글번호 계산(num)
			// 1.2 DB 연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체
			sql = "select max(qno) from qna";
			pstmt = con.prepareStatement(sql);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터처리
			if(rs.next()) {
				qno = rs.getInt(1)+1;
			}
			System.out.println("DAO : 답글 번호(bno): "+qno);
			
			// 답글순서 재배치 (re_ref 동일 그룹에서 re_seq기존의 값보다 큰값이 있을 때 re_seq를 1증가)
			
				// 3. sql 작성 & pstmt 
				sql = "update qna set re_seq = re_seq + 1 "
						+ "where re_ref=? and re_seq>?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, qdto.getRe_ref()); // 부모글의 ref
				pstmt.setInt(2, qdto.getRe_seq()); // 부모글의 seq
				
				// 4. sql 실행
				pstmt.executeUpdate();			
			// 답글순서 재배치 (re_ref 동일 그룹에서 re_seq기존의 값보다 큰값이 있을 때 re_seq를 1증가)
				
			// 답글 쓰기(insert)
			// 3. sql 작성 (insert) & pstmt 객체 생성
			sql = "insert into qna(qno,id,title,content,readcount,"
					+ "re_ref,re_lev,re_seq,reg_date) " + "values(?,?,?,?,?,?,?,?,now())";

			pstmt = con.prepareStatement(sql);

			// ?
			pstmt.setInt(1, qno);
			pstmt.setString(2, qdto.getId());
			pstmt.setString(4, qdto.getTitle());
			pstmt.setString(5, qdto.getContent());
			pstmt.setInt(6, 0);// 조회수 0으로 초기화
			pstmt.setInt(7, qdto.getRe_ref()); // re_ref 그룹번호 = 부모글의 그룹번호
			pstmt.setInt(8, qdto.getRe_lev()+1); // re_lev 레벨값 => 부모글 lev + 1
			pstmt.setInt(9, qdto.getRe_seq()+1); // re_seq 순서 => 부모글 seq + 1

			// 4. sql 실행
			pstmt.executeUpdate();
			
			System.out.println("DAO 글 작성완료");
				
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			CloseDB();
		}
		
	}
	// replyBoard(bto) 끝
	
	
	// redeleteBoard(bno,re_seq,pass)
	public int redeleteBoard(int qno,int re_seq){
		int result = -1;
		
		try {
			// 1.2. 디비연결
			con = getCon();
			// 3. sql 작성 & pstmt 객체 생성
			sql = "select pass from hj_board where qno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qno);
			
			// 4. sql 실행
			rs = pstmt.executeQuery();
			
			// 5. 데이터 처리
			if(rs.next()){
				// 3. sql
				sql ="delete from qna where qno=? and re_seq=?";
				pstmt = con.prepareStatement(sql);
					
				pstmt.setInt(1, qno);
				pstmt.setInt(2, re_seq);
				// 4. sql 실행
				pstmt.executeUpdate();
				result = 1;
			}else{
				result = -1;
			}			
			System.out.println(" DAO : 답글 삭제 완료! "+result);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}		
		
		return result;
	}
	// redeleteBoard(qno,re_seq) 끝
	

}
