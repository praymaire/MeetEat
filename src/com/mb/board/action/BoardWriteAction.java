package com.mb.board.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mb.board.action.ActionForward;
import com.mb.board.db.BoardDAO;
import com.mb.board.db.BoardDTO;
import com.mysql.cj.Session;

public class BoardWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
			
		// 세션 에서 가져오기
		
		String id = request.getParameter("id");
		String when_name = request.getParameter("when_name");
		String food_category = request.getParameter("food_category");
		String where_name = request.getParameter("where_name");
		String upload_image = request.getParameter("upload_image");
		String content = request.getParameter("content");
		
		//bno
		//write_time
			
		// DTO 객체 생성
		
		BoardDTO dto = new BoardDTO();
		
		// 전달된 정보를 저장
		
		dto.setWrite_time(new Timestamp(System.currentTimeMillis()));
		
		dto.setWhen_name(when_name); // 언제
		dto.setfood_category(food_category); // 음식
		dto.setWhere_name(where_name); // 어디서
		dto.setUpload_image(upload_image); //첨부파일
		dto.setId(id);
		dto.setContent(content); // 본문
		
		System.out.println(" BoardAction : "+dto);
		
		BoardDAO dao = new BoardDAO();
		dao.insertBoard(dto);
		
		System.out.println(" B : 글정보 저장완료! ");
		
		ActionForward forward = new ActionForward();
		forward.setPath("./list.mb");
		forward.setRedirect(true);		
		return forward;
	}

}
