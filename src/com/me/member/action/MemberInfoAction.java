package com.me.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.me.member.db.MemberDAO;
import com.me.member.db.MemberDTO;

public class MemberInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println(" M : MemberInfoAction_execute() 호출");
		
		// ID 정보를 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// DAO 객체 생성 - ID에 해당하는 사용자 정보를 모두 가져오기
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = mdao.getMember(id);
		
		// view페이지로 정보를 전달하기 위해서 DTO 정보 저장(request 영역에 저장 - 포워딩이랑 한 세트)
		request.setAttribute("mdto", mdto);
		
		// info.jsp 페이지로 이동
		forward.setPath("./Member/info.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

	
}
