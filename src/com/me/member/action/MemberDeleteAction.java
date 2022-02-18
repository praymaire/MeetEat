package com.me.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.me.member.db.MemberDAO;
import com.me.member.db.MemberDTO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		System.out.println(" M : MemberDeleteAction_execute() 호출");
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		String pw = request.getParameter("pw");
		
		MemberDAO mdao = new MemberDAO();
		int result = mdao.deleteMember(id, pw);
		
		// js
		if(result == 0) {
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = response.getWriter();	
			out.print("<script>");
			out.print("alert('비밀번호가 틀립니다');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return null; 
		} 
		else if(result == -1) { // 계정 X
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('계정이 존재하지 않습니다');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return null;
		}
		// 페이지 이동
		session.invalidate();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print("alert('정상적으로 탈퇴되었습니다. 처음화면으로 되돌아갑니다.');");
		out.print("location.href='./Main.me';");
		out.print("</script>");
		out.close();
		
		return null;
	
	}


}