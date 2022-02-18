package com.ad.admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ad.admin.db.ReportDAO;

public class AdminReportSortAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ReportDAO rdao = new ReportDAO();
		
		/* 페이지 처음 진입했는지 확인용 값*/
		request.setAttribute("check", "1");
		
		/* 파라미터 */
		String paramOption = request.getParameter("option");
		String paramUser = request.getParameter("userSelect");
		String paramId = request.getParameter("id");
		String paramSort = request.getParameter("sort");
	
		/* 초기값 세팅*/
		String option = (paramOption == null ? "report_date" : paramOption);
		String userSelect = (paramUser == null ? "1" : paramUser);
		String id = (paramId == null ? "1" : paramId);
		String sort = (paramSort == null ? "desc" : paramSort);
		
		/* where절 필요없을 때 좌변,우변 1로 처리 */
		id = (id == "" ? "1" : id);
		userSelect = (id == "1" ? "1" : userSelect); 
		
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
		ArrayList reportList = rdao.sortReport(userSelect, id, option, sort, startRow, pageSize);
		
		int cnt = rdao.getReportCount();
		
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
		
		request.setAttribute("reportList", reportList);
		request.setAttribute("option", option);
		request.setAttribute("userSelect", userSelect);
		request.setAttribute("id", id);
		request.setAttribute("sort", sort);
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
		forward.setPath("./admin/reportManage.jsp");
		forward.setRedirect(false);
		
		return forward;
	}
	
}
