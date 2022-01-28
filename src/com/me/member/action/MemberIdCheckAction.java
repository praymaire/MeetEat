package com.me.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.me.member.db.MemberDAO;

public class MemberIdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" S : MemberIdCheckAction_execute() 호출");
		
		String id = request.getParameter("id");
		
		MemberDAO mdao = new MemberDAO();
		int result = mdao.idCheck(id);
		System.out.println(" M : 아이디 조회 완료");
		
		if(result == 1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('사용할 수 있는 아이디입니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('사용할 수 없는 아이디입니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}
		
	}

}
