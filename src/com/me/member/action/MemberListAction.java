package com.me.member.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.me.member.db.MemberDAO;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println(" M : MemberListAction_execute() 호출");
		
		// 세션 제어 & 관리자 제어
		HttpSession session =  request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null || !id.equals("admin")) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// DAO 객체 생성
		// getMemberList() - 전체 회원 목록 가져오기
		MemberDAO mdao = new MemberDAO();
		ArrayList memberList = mdao.getMemberList();
		
		// request 영역에 정보 저장
		request.setAttribute("memberList", memberList);
		
		// ./Member/list.jsp 에 출력
		forward = new ActionForward();
		forward.setPath("./Member/list.jsp");
		forward.setRedirect(false);
	
		return forward;
	}

}
