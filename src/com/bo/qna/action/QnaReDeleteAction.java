package com.bo.qna.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.qna.db.QnaDAO;

public class QnaReDeleteAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : QnaReDeleteAction_execute() 호출 ");
		
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
		int re_seq = Integer.parseInt(request.getParameter("re_seq"));
		
		// DAO 객체 
		QnaDAO qdao = new QnaDAO();		
		boolean result = qdao.redeleteQna(qno,re_seq);
		
		if(result == false) {
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = response.getWriter();	
			out.print("<script>");
			out.print("alert('삭제 과정에서 문제가 발생했습니다.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return null; 
		} 
		
		System.out.println(" M : 큐앤에이 답글 삭제완료 ");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print(" alert('답글이 삭제되었습니다.');");
		out.print("location.href='./QnaList.bo';");
		out.print("</script>");
		out.close();
		
		return null;
	}
}
