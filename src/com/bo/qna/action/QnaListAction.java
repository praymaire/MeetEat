package com.bo.qna.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.qna.db.QnaDAO;

public class QnaListAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : QnaListAction의 execute() 호출");
		
		
		// 세션제어
		HttpSession session = request.getSession();
		String id = (String ) session.getAttribute("id");
		
		QnaDAO qdao = new QnaDAO();	
		
		/* 페이징 처리 */
		
		// 한 페이지에 출력될 글 개수
		int pageSize = 5;
	
		// 현 페이지 정보 설정
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
	
		// 첫행번호를 계산
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		
		// DB에서 리스트 받기, 전체 행 수 가져오기
		//ArrayList qnaList = qdao.getQnaList(startRow,pageSize);
		
		int cnt = qdao.getQnaCount();
		
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
		
		request.setAttribute("qnaList", qdao.getQnaList(startRow,pageSize));
		
		forward.setPath("./qna/qnalist.jsp");
		forward.setRedirect(false);
		return forward;
		
	}

}
