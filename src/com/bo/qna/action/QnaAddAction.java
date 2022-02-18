package com.bo.qna.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bo.qna.db.QnaDAO;
import com.bo.qna.db.QnaDTO;

public class QnaAddAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : QnaAddAction_execute() ?ò∏Ï∂?");
		
		// ?ïúÍ∏? Ï≤òÎ¶¨
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
		
		// DAO Í∞ùÏ≤¥ 
		QnaDAO qdao = new QnaDAO();
		// ?†Ñ?ã¨?êú ?†ïÎ≥¥Î?? DB?óê ???û•
		qdao.insertQna(qdto);
		System.out.println(" M : ?Åê?ï§?óê?ù¥ ???û•?ôÑÎ£?! ");		
		// ?éò?ù¥Ïß? ?ù¥?èô -> ?ù¥?èô?†ïÎ≥? ???û•
		// ActionForward Í∞ùÏ≤¥Î•? ?Éù?Ñ±
		ActionForward forward = new ActionForward();
		forward.setPath("./QnaList.bo");
		forward.setRedirect(true);		
		return forward;
	}
	
	

}
