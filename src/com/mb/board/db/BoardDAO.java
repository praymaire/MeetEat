package com.mb.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.mb.board.db.BoardDTO;

public class BoardDAO { // BoardDAO ����
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";

	// ��񿬰�
	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/itwillbs7");
		con = ds.getConnection();
		return con;
	} // ��񿬰� ��

	// �ڿ�����
	public void CloseDB() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	} //�ڿ����� ��
	
	// insertBoard ����    //�����κ� �����ʿ�
	public void insertBoard(BoardDTO dto) {
			int tbno = 1;
			
		
		try {
			System.out.println("insertboard ���� ���� !");
			con = getCon();
						
			sql = "select max(bno) from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				tbno = rs.getInt(1)+1;
			}
			
			System.out.println("�� ��ȣ :"+tbno);
	
			sql = "insert into board(bno,id,write_time,when_name,where_name,food_category,content,upload_image) " + "values(?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);

//			bno
			pstmt.setInt(1, tbno);
//			id
			pstmt.setString(2, dto.getId());
//			write_time
			pstmt.setTimestamp(3, dto.getWrite_time());
//			when_name
			pstmt.setString(4, dto.getWhen_name());
//			where_name
			pstmt.setString(5, dto.getWhere_name());
//			food_category
			pstmt.setString(6, dto.getfood_category());
//			content
			pstmt.setString(7, dto.getContent());
//			upload_image
			pstmt.setString(8, dto.getUpload_image());
			// sql ����
			
			pstmt.executeUpdate();

			System.out.println(" DAO : ���ۼ� �Ϸ�! ");
			
			// ����Ʈ �߰�
			
			StringBuffer sb = new StringBuffer();
			
	         sb.append("UPDATE member_manage SET user_point = user_point + 50, ");
	         sb.append("user_level = (");
	         sb.append("   CASE");
	         sb.append("      WHEN user_point >= 0 and user_point < 50 THEN 1");
	         sb.append("      WHEN user_point >= 50 and user_point < 250 THEN 2");
	         sb.append("      WHEN user_point >= 250 and user_point < 500 THEN 3");
	         sb.append("      WHEN user_point >= 500 and user_point < 750 THEN 4");
	         sb.append("      WHEN user_point >= 750 and user_point < 1000 THEN 5");
	         sb.append("      WHEN user_point >= 1000 and user_point < 1500 THEN 6");
	         sb.append("      WHEN user_point >= 1500 and user_point < 2500 THEN 7");
	         sb.append("      WHEN user_point >= 2500 and user_point < 3500 THEN 8");
	         sb.append("      WHEN user_point >= 3500 and user_point < 4500 THEN 9");
	         sb.append("      WHEN user_point >= 4500 THEN 10");
	         sb.append("  ELSE 10");
	         sb.append(" END )");
	         sb.append("WHERE id = ?");
			
			sql = sb.toString();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.executeUpdate();
			
			System.out.println("����Ʈ �߰� ����");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
	}  // insertBoard ��
	

	public int getBoardCount(){
		int count = 0;
		
		try {
			
			System.out.println("getBoardCount ���� ���� !");
			con = getCon();
			
			sql = " select found_rows();";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
				System.out.println("�� �Խñ� : "+count);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
		
		return count;
	}
	
	public ArrayList getBoardList(int bno){
		
		ArrayList BoardList = new ArrayList();
		
		try{
			
			System.out.println("getboarlist ����");
			con = getCon();
			sql = "select * from board where bno = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				
				BoardDTO dto = new BoardDTO();	
				
				dto.setBno(rs.getInt("bno"));
				dto.setId(rs.getString("id"));
				dto.setWrite_time(rs.getTimestamp("write_time"));
				dto.setWhen_name(rs.getString("when_name"));
				dto.setWhere_name(rs.getString("where_name"));
				dto.setfood_category(rs.getString("food_category"));
				dto.setUpload_image(rs.getString("upload_image"));
				dto.setContent(rs.getString("content"));
				
				BoardList.add(dto);
			
			}
			System.out.println("�� ���� ���� �Ϸ� : "+BoardList);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
				CloseDB();
		}
		
		return BoardList;
	}
	
	public ArrayList getBoardList(){
		
		ArrayList BoardList = new ArrayList();
		
		try{
			
			System.out.println("getboarlist ����");
			con = getCon();
			sql = "select * from board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			
			
			if(rs.next()){
				
				BoardDTO dto = new BoardDTO();	
				
				dto.setBno(rs.getInt("bno"));
				dto.setId(rs.getString("id"));
				dto.setWrite_time(rs.getTimestamp("write_time"));
				dto.setWhen_name(rs.getString("when_name"));
				dto.setWhere_name(rs.getString("where_name"));
				dto.setfood_category(rs.getString("food_category"));
				dto.setUpload_image(rs.getString("upload_image"));
				dto.setContent(rs.getString("content"));
				
				BoardList.add(dto);
			
			}
			System.out.println("�� ���� ���� �Ϸ� : "+BoardList);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
				CloseDB();
		}
		
		return BoardList;
	}
	
	
	public ArrayList searchBoardList(String col, String value, int startRow, int pageSize){
		
		ArrayList BoardList = new ArrayList();
		
		String query = "where "+col+" like '%"+value+"%' ";
		
		if(col == "1" && col == "1") {
			query = "";
		}
		
		try{
			
			System.out.println("searchBoardList ����");
			con = getCon();
			sql = "select SQL_CALC_FOUND_ROWS * " + 
					"from board " + 
					query + 
					"order by bno desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startRow-1); // ������-1  (���� ROW�ε��� ��ȣ)
			pstmt.setInt(2, pageSize);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				
				BoardDTO dto = new BoardDTO();	
				
				dto.setBno(rs.getInt("bno"));
				dto.setId(rs.getString("id"));
				dto.setWrite_time(rs.getTimestamp("write_time"));
				dto.setWhen_name(rs.getString("when_name"));
				dto.setWhere_name(rs.getString("where_name"));
				dto.setfood_category(rs.getString("food_category"));
				dto.setUpload_image(rs.getString("upload_image"));
				dto.setContent(rs.getString("content"));
				
				BoardList.add(dto);
			
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
				CloseDB();
		}
		
		return BoardList;
	}
	
	public ArrayList getBoardDetail(int bno) {
		
		ArrayList BoardDetail = new ArrayList();
		
	try{
		con = getCon();
		sql = "select * from board where bno=?";
		pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, bno);
		
		rs = pstmt.executeQuery();
		
		while(rs.next()){
			
			BoardDTO dto = new BoardDTO();	
			
			dto.setBno(rs.getInt("bno"));
			dto.setId(rs.getString("id"));
			dto.setWrite_time(rs.getTimestamp("write_time"));
			dto.setWhen_name(rs.getString("when_name"));
			dto.setWhere_name(rs.getString("where_name"));
			dto.setfood_category(rs.getString("food_category"));
			dto.setUpload_image(rs.getString("upload_image"));
			dto.setContent(rs.getString("content"));
			
			BoardDetail.add(dto);
		
		}

		System.out.println("BoardDetail : "+BoardDetail);
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
			CloseDB();
	}
		
	
	
	 return BoardDetail;
	}

	public void DeleteBoard(int bno) {
			
		try{
			con = getCon();
			sql = "delete from board where bno=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			pstmt.execute();
			
			System.out.println("�� ��ȣ : "+bno+" ���� �Ϸ� ! ");
			
			
	}catch(Exception e){
		e.printStackTrace();
	}finally{
			CloseDB();
	}
		
	}

	public void ModifyBoard(BoardDTO dto) {
		
		try {
			
			con = getCon();
						
			sql = "update board set food_category=?,when_name=?,where_name=?,upload_image=?,content=? where bno=?";
			pstmt = con.prepareStatement(sql);
			
			//food_category=?
			pstmt.setString(1, dto.getfood_category());
			//when_name=?
			pstmt.setString(2, dto.getWhen_name());
			//where_name=?
			pstmt.setString(3, dto.getWhere_name());
			//upload_image=?
			pstmt.setString(4, dto.getUpload_image());
			//content=? 
			pstmt.setString(5, dto.getContent());
			//where bno=?
			pstmt.setInt(6, dto.getbno());
			
			pstmt.executeUpdate();

			System.out.println(" DAO : ������ ���� �Ϸ�! ");

			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseDB();
		}
		
	}
	
public ArrayList getMainList(){
		
		ArrayList BoardList = new ArrayList();
		
		try{
			
			System.out.println("mainboarlist ����");
			con = getCon();
			sql = "select bno, id, write_time, when_name, where_name, food_category, upload_image "
					+ "from board order by bno desc limit 8";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				
				BoardDTO dto = new BoardDTO();	
				
				dto.setBno(rs.getInt("bno"));
				dto.setId(rs.getString("id"));
				dto.setWrite_time(rs.getTimestamp("write_time"));
				dto.setWhen_name(rs.getString("when_name"));
				dto.setWhere_name(rs.getString("where_name"));
				dto.setfood_category(rs.getString("food_category"));
				dto.setUpload_image(rs.getString("upload_image"));
				
				BoardList.add(dto);
			
			}
			System.out.println("�� ���� ���� �Ϸ� : "+BoardList);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
				CloseDB();
		}
		
		return BoardList;
	}

public String getImage(int bno)
	  {
	    try
	    {
	      con = getCon(); 
	      
	      sql = "select upload_image from board where bno=?";
	      pstmt = con.prepareStatement(sql);
	      pstmt.setInt(1, bno);
	      rs = pstmt.executeQuery();
	      if (rs.next())
	      {
	    	System.out.println("img: " + rs.getString("upload_image"));
	        if (rs.getString("upload_image").contains("null") || rs.getString("upload_image") == null) {
	          return "����� ������ �����ϴ�.";
	        }
	        return "http://localhost:8088/MeetEat/" + rs.getString("upload_image");
	      }
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	    finally
	    {
	      CloseDB();
	    }
	    
	    System.out.println(" B : BoardDAO_getImage() ����Ϸ�");
	    return "����� ������ �����ϴ�.";
	  }
	
	
	
} //BoardDAO ��