package com.me.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.me.member.db.MemberDAO;


public class MemberLoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : MemberLoginAction_execute() 호출");
		
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		// 전달된 파라미터 저장
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		// 회원유무 판단 - DAO 객체 생성 , 로그인 체크 메소드
		MemberDAO mdao = new MemberDAO();
		int result = mdao.loginCheck(id, pw);
		
		// 처리 결과에 따른 페이지 이동 -> 자바스크립트 사용(자바에서 자바스크립트를?)
		if(result == 0) {
			// 비밀번호 오류
			response.setContentType("text/html; charset=UTF-8");
			//response를 통한 입출력 통로 생성
			PrintWriter out = response.getWriter();
			// 통로를 통해 화면에 출력 -> html코드 사용 가능 -> 스크립트 코드도 사용 가능!
			out.print("<script>");
			out.print("alert('아이디, 혹은 비밀번호가 존재하지 않습니다!');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return null;	
		} 
		else if(result == -1) {
			// 계정 X
			response.setContentType("text/html; charset=UTF-8");
			//response를 통한 입출력 통로 생성
			PrintWriter out = response.getWriter();
			// 통로를 통해 화면에 출력 -> html코드 사용 가능 -> 스크립트 코드도 사용 가능!
			out.print("<script>");
			out.print("alert('아이디, 혹은 비밀번호가 존재하지 않습니다!');");
		    out.print("history.back();");
			out.print("</script>");
			out.close();
					
			return null;
		}
		
		// 로그인 성공!
		// 세션 값 생성(로그인 ID)
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		
		// 페이지 이동 - ActionForward
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true);
		return forward;
	}
		
}
