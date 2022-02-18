package com.bo.qna.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.qna.db.QnaDAO;

public class QnaListAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("M : QnaListAction?˜ execute() ?˜¸ì¶?");
		
		
		// ?„¸?…˜? œ?–´
		HttpSession session = request.getSession();
		String id = (String ) session.getAttribute("id");
		
		QnaDAO qdao = new QnaDAO();	
		
		/* ?Ž˜?´ì§? ì²˜ë¦¬ */
		
		// ?•œ ?Ž˜?´ì§??— ì¶œë ¥?  ê¸? ê°œìˆ˜
		int pageSize = 5;
	
		// ?˜„ ?Ž˜?´ì§? ? •ë³? ?„¤? •
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
	
		// ì²«í–‰ë²ˆí˜¸ë¥? ê³„ì‚°
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;
		
		// DB?—?„œ ë¦¬ìŠ¤?Š¸ ë°›ê¸°, ? „ì²? ?–‰ ?ˆ˜ ê°?? ¸?˜¤ê¸?
		ArrayList qnaList = qdao.getQnaList(startRow, pageSize);
		
		int cnt = qdao.getQnaCount();
		
		// ? „ì²? ?Ž˜?´ì§??ˆ˜ ê³„ì‚°
		int pageCount = cnt / pageSize + (cnt % pageSize == 0 ? 0 : 1);

		// ?•œ ?Ž˜?´ì§??— ë³´ì—¬ì¤? ?Ž˜?´ì§?ë¸”ëŸ­
		int pageBlock = 2;

		// ?•œ ?Ž˜?´ì§??— ë³´ì—¬ì¤? ?Ž˜?´ì§? ë¸”ëŸ­ ?‹œ?ž‘ë²ˆí˜¸ ê³„ì‚°
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;

		// ?•œ ?Ž˜?´ì§??— ë³´ì—¬ì¤? ?Ž˜?´ì§? ë¸”ëŸ­ ?ë²ˆí˜¸ ê³„ì‚°
		int endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		
		request.setAttribute("qnaList", qnaList);
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
		
		//request.setAttribute("qnaList", qdao.getQnaList(startRow,pageSize));
		forward = new ActionForward();
		forward.setPath("./qna/qnalist.jsp");
		forward.setRedirect(false);
		return forward;
		
	}

}
