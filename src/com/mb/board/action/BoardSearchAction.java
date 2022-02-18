package com.mb.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mb.board.db.BoardDAO;

public class BoardSearchAction implements Action{

	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		request.setAttribute("check", "1");
		
		BoardDAO bdao = new BoardDAO();
		
		String col = request.getParameter("col");
		String value = request.getParameter("value");
		
		if(col == null || col == "") {
			col = "1";
		}
		
		if(value == null || value == "") {
			value = "1";
			col = "1";
		}
		
		System.out.println("col: " + col + " / " + "value: " + value);
		
		/* 페이징 처리 */
		
		// 한 페이지에 출력될 글 개수
		int pageSize = 8;
	
		// 현 페이지 정보 설정
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
	
		// 첫행번호를 계산
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		
		// DB에서 리스트 받기, 전체 행 수 가져오기
		ArrayList boardList = bdao.searchBoardList(col, value, startRow, pageSize);
		
		int cnt = bdao.getBoardCount();
		
		// 전체 페이지수 계산
		int pageCount = cnt / pageSize + (cnt % pageSize == 0 ? 0 : 1);

		// 한 페이지에 보여줄 페이지블럭
		int pageBlock = 5;

		// 한 페이지에 보여줄 페이지 블럭 시작번호 계산
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;

		// 한 페이지에 보여줄 페이지 블럭 끝번호 계산
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		
		request.setAttribute("boardList", boardList);
		request.setAttribute("col", col);
		request.setAttribute("value", value);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("startPage", startPage);
		request.setAttribute("startRow", startRow);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("cnt", cnt);
		request.setAttribute("endPage", endPage);
		
		ActionForward forward = new ActionForward();
		forward = new ActionForward();
		forward.setPath("./board/list.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
	
}