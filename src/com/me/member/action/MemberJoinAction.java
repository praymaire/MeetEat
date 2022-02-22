package com.me.member.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.me.member.db.MemberDAO;
import com.me.member.db.MemberDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberJoinAction implements Action{
	// 회원가입 처리

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MemberJoinAction_execute() 호출!");
		
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String checkPw = request.getParameter("checkPw");
		String nickname = request.getParameter("nickname");
		String phone = request.getParameter("phone");
		String email1 = request.getParameter("email1");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String detailAddress = request.getParameter("detailAddress");
		String extraAddress = request.getParameter("extraAddress");
		String profile_image = "";

		if(id == null || id.equals("") || pw == null || pw.equals("") || checkPw == null || checkPw.equals("") 
				|| nickname == null || nickname.equals("") || phone == null || phone.equals("") 
				|| email1 == null || email1.equals("")|| email == null || email.equals("") 
				|| address == null || address.equals("") || detailAddress == null || detailAddress.equals("")){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('모든 내용을 입력하세요.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}
		if(!(pw.equals(checkPw))) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('비밀번호가 서로 일치하지 않습니다.');");
			out.print("history.back();");
			out.print("document.join.checkPw.focus();");
			out.print("</script>");
			out.close();
			return null;
		}
		System.out.println(" M : MemberJoinAction - 모든 정보 정상적으로 입력완료됨");
		
		// DTO 객체 생성, 전달된 정보를 저장
		MemberDTO mdto = new MemberDTO();
		mdto.setId(id);
		mdto.setPw(pw);
		mdto.setNickname(nickname);
		mdto.setPhone(phone);
		mdto.setEmail(email1+"@"+email);
		mdto.setAddress(address+" "+detailAddress+extraAddress);
		
		System.out.println(" M : "+mdto.toString());
		
		// DAO 객체 생성
		// 전달된 정보를 DB에 저장
		MemberDAO mdao = new MemberDAO();
		int result = mdao.insertMember(mdto);
		System.out.println(" M : 회원정보 저장 완료");
		
		if(result == 1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('회원가입이 성공적으로 처리되었습니다. 처음화면으로 되돌아갑니다.');");
			out.print("location.href='./Main.me';");
			out.print("</script>");
			out.close();
			return null;
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('이미 존재하는 회원입니다. 이전화면으로 되돌아갑니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}
	}
	
	
	
}