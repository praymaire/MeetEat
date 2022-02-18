package com.me.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.me.member.db.MemberDAO;

public class MemberModifytempPwAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : MemberModifytempPwAction_execute() 호출");
		
		// POST - 한글
		request.setCharacterEncoding("UTF-8");
		
		// 세션 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 파라미터 저장
		String oldPw = request.getParameter("oldPw");
		String pw = request.getParameter("pw");
		
		// DB 저장
		MemberDAO mdao = new MemberDAO();
		int result = mdao.modifyPw(id, oldPw, pw);
		
		// 결과에 따른 이동
		if(result == 1) {
			
			session.invalidate();
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('비밀번호가 정상적으로 처리되었습니다.');");
			out.print("location.href='./MemberLogin.me';");
			out.print("</script>");
			out.close();
			
			return null;
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('접근할 수 없는 사용자입니다. 처음 페이지로 되돌아갑니다.');");
			out.print("location.href='./Main.me';");
			out.print("</script>");
			out.close();
			
			return null;
		}
		
	}
}