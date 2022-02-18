package com.mb.board.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mb.board.db.BoardDAO;
import com.mb.board.db.BoardDTO;

public class MainListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		request.setAttribute("check", "1");
		
		BoardDAO bdao = new BoardDAO();
		
		String col = request.getParameter("col");
		String value = request.getParameter("value");
		
		if(col == null || col == "") {
			col = "1";
		}
		
		if(value == null || value == "") {
			value = "1";
			col = "1";
		}
		
		System.out.println("col: " + col + " / " + "value: " + value);
		
	    BoardDAO dao = new BoardDAO();
	   
	    
	    ArrayList<BoardDTO> BoardList = dao.getMainList();
	    
		request.setAttribute("BoardList", BoardList);
		
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("./Main/main.jsp");
		forward.setRedirect(false);		
		return forward;
	}

}
