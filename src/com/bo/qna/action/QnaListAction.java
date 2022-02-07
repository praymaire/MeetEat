package com.bo.qna.action;

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
				
		ActionForward forward = new ActionForward();
				
		QnaDAO qdao = new QnaDAO();		
		
		request.setAttribute("qnaList", qdao.getQnaList());
		
		forward.setPath("./qna/qnalist.jsp");
		forward.setRedirect(false);
		return forward;
		
	}

}
