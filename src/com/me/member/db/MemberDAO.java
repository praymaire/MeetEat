package com.me.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	// 회원정보를 처리하는 DAO
	
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private String sql = "";
	
	// 디비 연결
	private Connection getCon() throws Exception{
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/itwillbs7");
		con = ds.getConnection();
		
		return con;
	}
	
	// 자원 해제
	public void closeDB() {
			try {
				if(rs != null) { rs.close(); }
				if(pstmt != null) { pstmt.close(); }
				if(con != null)  { con.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	// 아이디 검사
	// idCheck(id)
	public int idCheck(String id) {
		
		int result = 0;
		
		try {
			con = getCon();
			
			sql = "select * from meeteat.member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next() || id.equals("")) {
				result =  0; // 이미 존재하는 회원
			} else {
				result =  1; // 가입 가능한 회원 아이디
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		System.out.println(" M : MemberDAO_idCheck() 실행 ");
		System.out.println(" M : 실행결과 - "+result);
		return result;
	}
	// idCheck(id)
	
	// 닉네임 검사
	// nicknameCheck(nickname)
	public int nicknameCheck(String nickname) {
	
		int result = 0;
		
		try {
			con = getCon();
			
			sql = "select * from meeteat.member where nickname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickname);
			rs = pstmt.executeQuery();
			if(rs.next() || nickname.equals("")) {
				result =  0; // 이미 존재하는 닉네임
			} else {
				result =  1; // 가입 가능한 닉네임
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		System.out.println(" M : MemberDAO_nicknameCheck() 실행 ");
		System.out.println(" M : 실행결과 - "+result);
		return result;
	}
	// nicknameCheck(nickname)

	// 회원가입 
	// insertMember(mdto)
	public int insertMember(MemberDTO mdto) {

		int result = 0;
		
		try {
			con = getCon();
			
			sql = "insert into meeteat.member values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getId());
			pstmt.setString(2, mdto.getPw());
			pstmt.setString(3, mdto.getNickname());
			pstmt.setString(4, mdto.getPhone());
			pstmt.setString(5, mdto.getEmail());
			pstmt.setString(6, mdto.getAddress());
			pstmt.setString(7, mdto.getProfile_image());
			result = pstmt.executeUpdate();	// 1 
			
			sql = "insert into member_manage(id) values(?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getId());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		System.out.println(" M : MemberDAO_insertMember() 실행");
		System.out.println(" M : result - "+result);
		return result;
	}
	// insertMember(mdto)
	
	// loginCheck(id,pw)
	public int loginCheck(String id, String pw){
		
		int result = -1;
		
		try {
			con = getCon();				
			sql = "select pw from member where id=?";
			pstmt = con.prepareStatement(sql);				
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
		
			if(rs.next()){
				// 회원정보 있음
				if(pw.equals(rs.getString("pw"))){
					// 회원정보가 있으면서, 비밀번호 동일
					result = 1;
				}else{
				// 회원정보가 있음, 비밀번호 다름
					result = 0;
				}				
			}else{	
				// 회원정보 없음
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		System.out.println(" M : MemberDAO_loginCheck() 실행 ");
		System.out.println(" M : 실행결과 : "+result);
		
		return result;
	}
	// loginCheck(id,pw)	
	
	// 이메일 중복 검사
	// checkEmail(email)
	public boolean checkEmail(String email) {
			
		boolean emailCheck = false; // 초기화
			
		try {
			con = getCon();
			
			sql = "select email from member where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				emailCheck = true;
			} else {
					emailCheck = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return emailCheck;
	}
	// checkEmail(email)
	
	// getMember(id)
	public MemberDTO getMember(String id){
		
		MemberDTO mdto = null;
		
		try {
			con = getCon();
			sql = "select * from member_view where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if(rs.next()){
				mdto = new MemberDTO();
				
				mdto.setId(rs.getString("id"));
				mdto.setPw(rs.getString("pw"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setEmail(rs.getString("email"));
				mdto.setProfile_image(rs.getString("profile_image"));
				mdto.setAddress(rs.getString("address"));
				mdto.setNickname(rs.getString("nickname"));
				mdto.setUser_level(rs.getInt("user_level"));
				mdto.setUser_point(rs.getInt("user_point"));
				mdto.setBan_date(rs.getDate("ban_date"));
				mdto.setReported_count(rs.getInt("reported_count"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		System.out.println(" M : MemberDAO_getMember() 실행 ");
		System.out.println(" M : 회원정보 : "+mdto);		
		return mdto;
	}
	// getMember(id)

	// updateMember(mdto)
	public int updateMember(MemberDTO mdto) {
		int result = -1;
	
		try {
			con = getCon();
			sql = "select pw from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getId());
			rs = pstmt.executeQuery();

			if(rs.next()) { 
				if(mdto.getPw().equals(rs.getString("pw"))) { 
					// 본인
					sql = "update member set phone=?, email=?, address=?, nickname=?, profile_image=? where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, mdto.getPhone());
					pstmt.setString(2, mdto.getEmail());
					pstmt.setString(3, mdto.getAddress());
					pstmt.setString(4, mdto.getNickname());
					pstmt.setString(5, mdto.getProfile_image());
					pstmt.setString(6, mdto.getId());
					
					result = pstmt.executeUpdate();
					// -> sql 구문이 실행했을 때 영향을 준 row수 리턴
					// result = 1;
				} else {
					// 비밀번호 오류
					result = 0;
				}
			} else { 
				// 데이터 없을 때
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		System.out.println(" M : MemberDAO_updateMember() 실행완료");
		System.out.println(" M : 실행결과 - "+result);
		return result;
	}
	// updateMember(mdto) END

	// 프로필 사진 가져오기
	// getProfile(id)
	public String getProfile(String id) {
		
		try {
			con = getCon();
			sql = "select profile_image from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("profile_image").equals("") || rs.getString("profile_image") == null) {
					return "http://localhost:8088/MeetEat/upload/member/NoImage.png";
				}
				return "http://localhost:8088/MeetEat/"+rs.getString("profile_image");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		System.out.println(" M : MemberDAO_getprofile() 실행완료");
		return "http://localhost:8088/MeetEat/upload/member/NoImage.png";
	}
	// getProfile(id)
	
	// deleteMember(id,pw)
	public int deleteMember(String id, String pw) {
		
		int result = 0;
		
		try {
			con = getCon();
			
			sql = "select pw from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pw.equals(rs.getString("pw"))) {
					// 본인
					sql = "delete from member where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					result = pstmt.executeUpdate();
				} else {
					// 비밀번호 오류
					result = 0;
				} 
			} else {
				// 계정 X
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		System.out.println(" M : MemberDAO_deleteMember() 실행완료");
		System.out.println(" M : 실행결과 - "+result);
		return result;
	}
	// deleteMember(id,pw)

	// getMemberList()
	public ArrayList getMemberList() {
		
		ArrayList memberList = new ArrayList();
		
		try {
			con = getCon();
			
			sql = "select * from member_view";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// 회원 1명의 정보 -> memberList에 저장
				MemberDTO mdto = new MemberDTO();
				mdto.setId(rs.getString("id"));
				mdto.setPw(rs.getString("pw"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setEmail(rs.getString("email"));
				mdto.setProfile_image(rs.getString("profile_image"));
				mdto.setAddress(rs.getString("address"));
				mdto.setNickname(rs.getString("nickname"));
				mdto.setUser_level(rs.getInt("user_level"));
				mdto.setUser_point(rs.getInt("user_point"));
				mdto.setBan_date(rs.getDate("ban_date"));
				mdto.setReported_count(rs.getInt("reported_count"));
				
				// MemberDTO의 정보를 ArrayList 1칸에 저장
				memberList.add(mdto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		System.out.println(" M : MemberDAO_getMemberList() 실행 완료");
		return memberList;
	}
	// getMemberList()
	
	public void insertReport(String report_user, String reported_user, String report_content) {
		
		try {
			// 신고내용 접수
			con = getCon();
			sql = "insert into report_manage(report_user, reported_user, report_content, report_date) values(?, ?, ?, now())";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, report_user);
			pstmt.setString(2, reported_user);
			pstmt.setString(3, report_content);
			
			pstmt.executeUpdate();
			
			// 누적신고횟수 업데이트
			sql = "update member_manage "
					+ "set reported_count = (SELECT count(reported_user) FROM report_manage WHERE reported_user = ?) "
					+ "where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reported_user);
			pstmt.setString(2, reported_user);
			
			pstmt.executeUpdate();
			
			// 정지날짜 업데이트
			sql = "select reported_count from member_manage where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, reported_user);
			
			rs = pstmt.executeQuery();
			
			String banSql = "";
			
			if(rs.next()) {
				if(rs.getInt(1) == 3) {
					banSql="update member_manage set ban_date=DATE_ADD(now(), INTERVAL 30 DAY) where id=?";
				} else if(rs.getInt(1) == 5) {
					banSql="update member_manage set ban_date=DATE_ADD(now(), INTERVAL 100 DAY) where id=?";
				} else if(rs.getInt(1) == 7) {
					banSql="update member_manage set ban_date=DATE_ADD(now(), INTERVAL 100 YEAR) where id=?";
				}
				
				pstmt = con.prepareStatement(banSql);
				pstmt.setString(1, reported_user);
				pstmt.executeUpdate();
				
			} else {
				System.out.println("정지날짜 업데이트 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	}
	
	
}
