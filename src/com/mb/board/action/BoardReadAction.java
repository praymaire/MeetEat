package com.mb.board.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mb.board.db.BoardDAO;
import com.mb.board.db.BoardDTO;
import com.me.member.db.MemberDAO;
import com.me.member.db.MemberDTO;


public class BoardReadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		BoardDAO bdao = new BoardDAO();
		MemberDAO mdao = new MemberDAO();
		
		ArrayList boardList = bdao.getBoardList(bno);
		
		if(boardList == null) {
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print(" alert('존재하지 않는 글입니다')");
			out.print("location.href='./Main.me'");
			out.print("</script>");
			out.close();
			return null;
		} else {
			
			BoardDAO dao = new BoardDAO();
			BoardDTO bdto = (BoardDTO) boardList.get(0);
			String bid = bdto.getId();
			String img = bdto.getUpload_image();
			MemberDTO memberInfo = mdao.getMember(bid);
			
			request.setAttribute("memberInfo", memberInfo);
			request.setAttribute("boardList", boardList);
			request.setAttribute("getImage", dao.getImage(bno));
		    System.out.println("getImage : " + dao.getImage(bno));
			
			ActionForward forward = new ActionForward();
			forward.setPath("./board/read.jsp");
			forward.setRedirect(false);		
			return forward;
		}
	
	}

}
