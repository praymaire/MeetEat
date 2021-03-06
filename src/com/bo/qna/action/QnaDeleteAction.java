package com.bo.qna.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bo.qna.db.QnaDAO;

public class QnaDeleteAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : QnaDeleteAction_execute() ?ΈμΆ? ");
		
		// ?Έ?μ²΄ν¬
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if (id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// ?κΈ?μ²λ¦¬
		request.setCharacterEncoding("UTF-8");
		int qno = Integer.parseInt(request.getParameter("qno"));
		
		// DAO κ°μ²΄ 
		QnaDAO qdao = new QnaDAO();
		qdao.deleteQna(qno);
		System.out.println(" M : ??€??΄ ?­? ?λ£? ");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print(" alert('κΈ??΄ ?­? ???΅??€.');");
		out.print("</script>");
		out.close();

		forward.setPath("./QnaList.bo");
		forward.setRedirect(true);		
		return forward;
	}

}
