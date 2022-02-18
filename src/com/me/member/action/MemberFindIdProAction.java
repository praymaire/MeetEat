package com.me.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.me.member.db.MemberDAO;

public class MemberFindIdProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println(" M : MemberFindIdProAction.me_execute() 실행");
		
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");

		MemberDAO mdao = new MemberDAO();
		String id = mdao.findId(email);
		System.out.println(" M : id - "+ id);
		
		ActionForward forward = null;
		
		if(id == "-1") {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('입력하신 정보로 찾을 수 없습니다. 정확한 이메일을 입력해주세요.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return null;
		} else if(id != "-1") 
			request.setAttribute("id", id);
			forward = new ActionForward();
			forward.setPath("./Member/getId.jsp");
			forward.setRedirect(false);
			
			return forward;
		
	}

}