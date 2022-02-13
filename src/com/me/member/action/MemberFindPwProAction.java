package com.me.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.me.member.db.MemberDAO;

public class MemberFindPwProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : MemberFindPwProAction_execute() 실행");
		
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		ActionForward forward = new ActionForward();
		MemberDAO mdao = new MemberDAO();
		int result = mdao.findPw(id, email);
		
		if(result == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('이메일이 틀립니다.');");
			out.print("history.go(-1);");
			out.print("</script>");
			out.close();
			
			return null;
		}
		else if(result == -1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('존재하지 않는 계정입니다.');");
			out.print("history.go(-2);");
			out.print("</script>");
			out.close();
			
			return null;
		}
		// result = 1일 때 세션에 id 저장
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		
		String pw = SetTempPw.randomPw();
		session.setAttribute("pw", pw);
		
		result = mdao.updatePw(id, pw, email);
		if(result == 1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('임시 비밀번호가 저장되었습니다. 비밀번호를 다시 설정해주시기 바랍니다.');");
			out.print("location.href='./MemberModifytempPw.me';");
			out.print("</script>");
			out.close();
			
			return null;
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('존재하지 않는 계정, 또는 이메일입니다.');");
			out.print("history.go(-2);");
			out.print("</script>");
			out.close();
			
			return null;
		}
		
		
		
		
	}

}
