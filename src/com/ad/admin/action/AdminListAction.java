package com.ad.admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ad.admin.db.AdminDAO;

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
		
		// 파라미터, 초기값 세팅
		String col_name = request.getParameter("col_name");
		String id_nick = request.getParameter("id_nick");
		
		if(col_name == null || col_name == "") {
			col_name = "1";
		} 
		
		if(id_nick == null || id_nick == "") {
			id_nick = "1";
			col_name = "1";
		}
		
		// DAO 객체 생성
		AdminDAO adao = new AdminDAO();
		
		/* 페이징 처리 */
		
		// 한 페이지에 출력될 회원 수
		int pageSize = 10;
	
		// 현 페이지 정보 설정
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
	
		// 첫행번호를 계산
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		
		// DB에서 리스트 받기, 전체 행 수 가져오기
		ArrayList memberList = adao.getMemberList(col_name, id_nick, startRow, pageSize);
		
		int cnt = adao.getMemberCount();
		
		// 전체 페이지수 계산
		int pageCount = cnt / pageSize + (cnt % pageSize == 0 ? 0 : 1);

		// 한 페이지에 보여줄 페이지블럭
		int pageBlock = 2;

		// 한 페이지에 보여줄 페이지 블럭 시작번호 계산
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;

		// 한 페이지에 보여줄 페이지 블럭 끝번호 계산
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		
		request.setAttribute("memberList", memberList);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("cnt", cnt);
		request.setAttribute("endPage", endPage);

		forward = new ActionForward();		
		forward.setPath("./admin/memberlist.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
