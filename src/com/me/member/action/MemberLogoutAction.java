package com.me.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MemberLogoutAction_execute() 호출");
		
		// 세션정보 초기화
		HttpSession session = request.getSession();
		session.invalidate();
		
		// 원래 페이지로 이동(main페이지)
		// alert창 만들고 이동 : java에서  js 이용 -> contentType부터 바꾼다
		// response를 출력할 수 있는 통로 생성
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print("<script>");
		out.print("alert('정상적으로 로그아웃되었습니다. 처음화면으로 돌아갑니다.');");
		out.print("location.href='./Main.me';");
		out.print("</script>");
		out.close();
		
		return null;
	}

	
}
