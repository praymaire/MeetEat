package com.bo.qna.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.qna.db.QnaDAO;
import com.bo.qna.db.QnaDTO;

public class QnaAddAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : QnaAddAction_execute() 호출");
		
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
		
		QnaDTO qdto = new QnaDTO();
		
		qdto.setId(request.getParameter("id"));
		qdto.setTitle(request.getParameter("title"));
		qdto.setContent(request.getParameter("content"));
/*		qdto.setReadcount(Integer.parseInt(request.getParameter("readcount")));
		qdto.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		qdto.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		qdto.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));*/
		qdto.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		System.out.println(" M : "+qdto);
		
		// DAO 객체 
		QnaDAO qdao = new QnaDAO();
		// 전달된 정보를 DB에 저장
		qdao.insertQna(qdto);
		System.out.println(" M : 큐앤에이 저장완료! ");		
		// 페이지 이동 -> 이동정보 저장
		// ActionForward 객체를 생성
		ActionForward forward = new ActionForward();
		forward.setPath("./QnaList.bo");
		forward.setRedirect(true);		
		return forward;
	}
	
	

}
