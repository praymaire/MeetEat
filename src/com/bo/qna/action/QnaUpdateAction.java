package com.bo.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.qna.db.QnaDAO;
import com.bo.qna.db.QnaDTO;

public class QnaUpdateAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : QnaUpdateAction_execute() 호출 ");
		
		// 세션체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
				
		ActionForward forward = new ActionForward();
		
		int qno = Integer.parseInt(request.getParameter("qno"));
		
		QnaDAO qdao = new QnaDAO();
		QnaDTO qdto = qdao.getQna(qno);
		
		request.setAttribute("qdto", qdto);
		
		forward.setPath("./qna/qnaUpdate.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
