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
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/meeteat");
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
		System.out.println(" M : 실행결과 : "+result);
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
		System.out.println(" M : 실행결과 : "+result);
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		System.out.println(" M : MemberDAO_insertMember() 실행");
		System.out.println(" M : result : "+result);
		return result;
	}
	// insertMember(mdto)
	
	// loginCheck(id,pw)
	public int loginCheck(String id, String pw){
		
		int result = -1;
		
		try {
			con = getCon();				
			sql = "select pw from meeteat.member where id=?";
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
	
	// 아이디 찾기
	// findId(email)
	public String findId(String email) {
			
		String findId = ""; // 초기화
			
		try {
			con = getCon();
			
			sql = "select id, email from meeteat.member where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				findId = rs.getString("id");
			} else {
				findId = "-1";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		System.out.println(" M : MemberDAO_findId() 실행 ");
		System.out.println(" M : 실행결과 : "+findId);
		
		return findId;
	}
	// findId(email)
	
	// getMember(id)
	public MemberDTO getMember(String id){
		
		MemberDTO mdto = null;
		
		try {
			con = getCon();
			sql = "select * from meeteat.member where id=?";
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
			sql = "select pw from meeteat.member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getId());
			rs = pstmt.executeQuery();

			if(rs.next()) { 
				if(mdto.getPw().equals(rs.getString("pw"))) { 
					// 본인
					sql = "update meeteat.member set phone=?, email=?, address=?, nickname=?, profile_image=? where id=?";
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
		System.out.println(" M : 실행결과 : "+result);
		return result;
	}
	// updateMember(mdto) END

	// 프로필 사진 가져오기
	// getProfile(id)
	public String getProfile(String id) {
		
		try {
			con = getCon();
			sql = "select profile_image from meeteat.member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString("profile_image").equals("") || rs.getString("profile_image")==null) {
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
			
			sql = "select pw from meeteat.member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pw.equals(rs.getString("pw"))) {
					// 본인
					sql = "delete from meateat.member where id=?";
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
		System.out.println(" M : 실행결과 : "+result);
		return result;
	}
	// deleteMember(id,pw)

	// getMemberList()
	public ArrayList getMemberList() {
		
		ArrayList memberList = new ArrayList();
		
		try {
			con = getCon();
			
			sql = "select * from meeteat.member";
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

	// 회원 비밀번호 찾기
	// findPw(id, email)
	public int findPw(String id, String email) {
		
		int result = 0;
		
		try {
			con = getCon();
			
			sql = "select id, email from meeteat.member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(email.equals(rs.getString("email"))) {
					result = 1;
				} else {
					// 이메일 오류
					result = 0;
				} 
			} else {
				// 계정 X
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" M : MemberDAO_findPw() 실행 완료");
		System.out.println(" M : 실행 결과 : "+result);
		return result;
	}
	// findPw(id, email)

	// 임시 비번 저장
	// updatePw(id, pw, email)
	public int updatePw(String id, String pw, String email) {
		
		int result = 0; 
		
		try {
			con = getCon();
			
			sql = "select id,email from meeteat.member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(email.equals(rs.getString("email"))) {
					
					sql = "update meeteat.member set pw=? where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, pw);
					pstmt.setString(2, id);
					result = pstmt.executeUpdate();
				} else {
					// 이메일이 다름
					result = 0;
				} 
			} else {
				// 계정 X
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" M : Member_updatePw() 실행 완료");
		System.out.println(" M : 실행 결과 : "+result);
		return result;
		
	}
	// updatePw(id, pw, email)

	// 패스워드 변경
	// modifyPw(id, oldPw, pw)
	public int modifyPw(String id, String oldPw, String pw) {
		
		int  result = 0;
		
		try {
			con = getCon();
			
			sql = "select id, pw from meeteat.member where pw=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, oldPw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(id.equals(rs.getString("id"))) {
					sql = "update meeteat.member set pw=? where id=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, pw);
					pstmt.setString(2, id);
					result = pstmt.executeUpdate();
				} else {
					// 계정 X
					result = 0;
				} 
			} else {
				// 임시 패드워드 미발급/현재 패스워드 오류
				result = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(" M : Member_modifyPw() 실행 완료");
		System.out.println(" M : 실행 결과  : "+result);
		return result;
	}

	
	
}
