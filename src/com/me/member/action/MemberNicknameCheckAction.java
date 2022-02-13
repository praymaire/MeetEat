package com.me.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.me.member.db.MemberDAO;

public class MemberNicknameCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" S : MemberNicknameCheckAction_execute() 호출");
		
		request.setCharacterEncoding("UTF-8");
		
		String nickname = request.getParameter("nickname");
		
		System.out.println("nickname: " + nickname);
		
		MemberDAO mdao = new MemberDAO();
		int result = mdao.nicknameCheck(nickname);
		System.out.println(" M : 닉네임 조회 완료");
		
		PrintWriter out = response.getWriter();

		out.write(result + "");
		out.close();
		
		return null;
	}

}
