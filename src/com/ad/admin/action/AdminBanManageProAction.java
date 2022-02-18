package com.ad.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ad.admin.db.ReportDAO;

public class AdminBanManageProAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null || !id.equals("admin")) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		String reported_user = request.getParameter("reported_user");
		String date = request.getParameter("date");
		
		ReportDAO rdao = new ReportDAO();
		rdao.banMember(date, reported_user);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print("<script>");
		out.print("alert('정지처리 완료');");
		out.print("opener.location.reload();");
		out.print("window.close();");
		out.print("</script>");
		out.close();
		return null;

	}
}
