package com.bo.qna.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.qna.db.QnaDAO;
import com.bo.qna.db.QnaDTO;

public class QnaReplyProAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : QnaReplyProAction_execute() 호출 ");
		
		// 세션체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if (id == null || !id.equals("admin")) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		
		QnaDTO qdto = new QnaDTO();
		
		qdto.setQno(Integer.parseInt(request.getParameter("qno")));
		qdto.setId(request.getParameter("id"));
		qdto.setTitle(request.getParameter("title"));
		qdto.setContent(request.getParameter("content"));
		//qdto.setReadcount(Integer.parseInt(request.getParameter("readcount")));
		qdto.setRe_ref(Integer.parseInt(request.getParameter("re_ref")));
		qdto.setRe_lev(Integer.parseInt(request.getParameter("re_lev")));
		qdto.setRe_seq(Integer.parseInt(request.getParameter("re_seq")));
		qdto.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		System.out.println(" M : "+qdto);
		
		// DAO 객체 
		QnaDAO qdao = new QnaDAO();
		qdao.replyQna(qdto);
		System.out.println(" M : 큐앤에이 답글완료 ");		
		// 페이지 이동 -> 이동정보 저장
		// ActionForward 객체를 생성
		
		forward.setPath("./QnaList.bo");
		forward.setRedirect(true);		
		return forward;
	}

}
