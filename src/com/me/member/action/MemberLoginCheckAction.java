package com.me.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.me.member.db.MemberDAO;

public class MemberLoginCheckAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		// 전달된 파라미터 저장
		String paramId = request.getParameter("inputId");
		String paramPw = request.getParameter("inputPw");
		
		String inputId = (paramId == null ? "" : paramId);
		String inputPw = (paramPw == null ? "" : paramPw);
		
		System.out.println("inputId: " + inputId + "\n inputPw: " + inputPw);
		
		MemberDAO mdao = new MemberDAO();
		int result = mdao.loginCheck(inputId, inputPw);
		
	
		PrintWriter out = response.getWriter();

		out.write(result + "");
		out.close();
		
		return null;
	}

}
