package com.me.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.me.member.db.MemberDAO;

public class MemberIdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" S : MemberIdCheckAction_execute() �샇異�");
		
		request.setCharacterEncoding("UTF-8");
		
		String inputId = request.getParameter("inputId");
		
		System.out.println("inputId" + inputId);
		
		MemberDAO mdao = new MemberDAO();
		int result = mdao.idCheck(inputId);
		System.out.println(" M : idCheck()실행 완료");
		
		PrintWriter out = response.getWriter();
		
		if(result == 1) {
			System.out.println("result: " + result);
		} else {
			System.out.println("result: " + result);	
		}
		
		out.write(result + "");
		
		return null;	
	}

}
