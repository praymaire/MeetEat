package com.ad.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ad.admin.db.AdminDAO;
import com.ad.admin.db.AdminDTO;

public class AdminDeleteProAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : AdminDeleteProAction_execute() 호출 ");
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null || !id.equals("admin")) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 전달된 파라메터 저장
		String delid = (String)request.getParameter("id");
		String pw = request.getParameter("pw");

		System.out.println("id"+id);
		System.out.println("M: "+delid+" "+pw);
				
		// DAO 객체를 생성 - 로그인 체크 메서드
		AdminDAO adao = new AdminDAO();
		int result = adao.deleteMember(delid, pw);

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
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print("alert('정상적으로 탈퇴되었습니다.');");
		out.print("window.opener.location.reload();");
		out.print("window.close();");
		out.print("</script>");
		out.close();
		
		return null;
	
	}
}
