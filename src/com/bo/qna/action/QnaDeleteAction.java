package com.bo.qna.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.qna.db.QnaDAO;

public class QnaDeleteAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : QnaDeleteAction_execute() 호출 ");
		
		// 세션체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 한글처리
		request.setCharacterEncoding("UTF-8");
		int qno = Integer.parseInt(request.getParameter("qno"));
		
		// DAO 객체 
		QnaDAO qdao = new QnaDAO();
		qdao.deleteQna(qno);
		System.out.println(" M : 큐앤에이 삭제완료 ");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print(" alert('글이 삭제되었습니다.');");
		out.print("</script>");
		out.close();

		forward.setPath("./QnaList.bo");
		forward.setRedirect(true);		
		return forward;
	}

}
