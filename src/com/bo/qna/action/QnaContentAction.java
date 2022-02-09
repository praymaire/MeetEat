package com.bo.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.qna.db.QnaDAO;
import com.bo.qna.db.QnaDTO;

public class QnaContentAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : QnaContentAction의 execute() 호출");
		// 세션제어
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		int qno = Integer.parseInt(request.getParameter("qno"));
						
		ActionForward forward = new ActionForward();
						
		QnaDAO qdao = new QnaDAO();
		qdao.updateReadcount(qno);
		QnaDTO qdto = qdao.getQna(qno);
		
		request.setAttribute("qdto", qdto);
		
		forward.setPath("./qna/qcontent.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}

}
