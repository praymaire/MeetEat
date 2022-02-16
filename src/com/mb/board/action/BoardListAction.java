package com.mb.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mb.board.db.BoardDAO;
import com.mb.board.db.BoardDTO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 한글 처리
		request.setCharacterEncoding("utf-8");
			
	    BoardDAO dao = new BoardDAO();
	   
	    
	    ArrayList<BoardDTO> BoardList = dao.getBoardList();
	    
		request.setAttribute("BoardList", BoardList);
		
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("./board/list.jsp");
		forward.setRedirect(false);		
		return forward;
	}

}
