package com.bo.qna.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.qna.db.QnaDAO;
import com.bo.qna.db.QnaDTO;

public class QnaAddAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		QnaDTO qdto = new QnaDTO();
		qdto.setId(request.getParameter("id"));
		
		
		// DAO 객체 
		QnaDAO qdao = new QnaDAO();
		// 전달된 정보를 DB에 저장
		qdao.insertQna(qdto);
				
		// 페이지 이동 -> 이동정보 저장
		// ActionForward 객체를 생성
		ActionForward forward = new ActionForward();
		forward.setPath("./QnaList.bo");
		forward.setRedirect(true);		
		return forward;
	}
	
	

}
