package com.me.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.me.admin.db.AdminDAO;

public class AdminListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println(" M : MemberListAction_execute() ");
		
		// 세션제어
		HttpSession session = request.getSession();
		String id = (String ) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null || !id.equals("admin")){
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// DAO 객체 생성
		AdminDAO adao = new AdminDAO();
		// getMemberList() - 전체 회원목록 가져오기
		// dao.getMemberList();
		// request 영역에 저장
		request.setAttribute("memberList", adao.getMemberList());
		
		// ./member/list.jsp
		forward.setPath("./admin/memberlist.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
