package com.me.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberReportAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		System.out.println("id: " + id);
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			
			out.print("<script>");
			out.print("alert('로그인하세요');");
			out.print("window.close();");
			out.print("</script>");
			out.close();

			return null;
		}
	
		
		
		forward = new ActionForward();
		forward.setPath("./Member/report.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
}
