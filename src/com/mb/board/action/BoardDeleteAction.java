package com.mb.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mb.board.db.BoardDAO;
import com.mb.board.db.BoardDTO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		com.mb.board.db.BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();
		
		int bno =Integer.parseInt(request.getParameter("bno"));
		System.out.println("삭제 요청한 글 번호 : "+bno);
		
		dao.DeleteBoard(bno);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print("<script>");
		out.print("alert('삭제 완료');");
		out.print("location.href = './list.mb'; "); // 뒤로가기 + 새로고침
		out.print("</script>");
		out.close();
		
		return null;
		
		
	}

}
