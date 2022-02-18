package com.me.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.me.member.db.MemberDAO;

public class MemberReportProAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		// 세션 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberMain.me");
			forward.setRedirect(true);
			return forward;
		}
		
		String report_user = request.getParameter("report_user");
		String reported_user = request.getParameter("reported_user");
		String report_content = request.getParameter("report_content");
		
		MemberDAO dao = new MemberDAO();
		dao.insertReport(report_user, reported_user, report_content);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print("alert('신고가 성공적으로 처리되었습니다');");
		out.print("window.close();");
		out.print("</script>");
		out.close();
		
		return null;
	}

}