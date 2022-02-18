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
		String paramId = request.getParameter("inputId");
		String paramPw = request.getParameter("inputPw");
		
		String inputId = (paramId == null ? "" : paramId);
		String inputPw = (paramPw == null ? "" : paramPw);
		
		System.out.println("inputId: " + inputId + "\n inputPw: " + inputPw);
		
		MemberDAO mdao = new MemberDAO();
		int result = mdao.loginCheck(inputId, inputPw);
		
		if(result == 0) {
			return null;	
		} 
		else if(result == -1) {
			return null;
		}

		
		// 처리 결과에 따른 페이지 이동 -> 자바스크립트 사용(자바에서 자바스크립트를?)
		// 세션 값 생성(로그인 ID)
		HttpSession session = request.getSession();
		session.setAttribute("id", inputId);
		
		// 페이지 이동 - ActionForward
		ActionForward forward = new ActionForward();
		forward.setPath("./Main.me");
		forward.setRedirect(true);
		return forward;
	}		
}