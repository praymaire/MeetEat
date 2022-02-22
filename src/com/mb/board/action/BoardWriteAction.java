package com.mb.board.action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mb.board.action.ActionForward;
import com.mb.board.db.BoardDAO;
import com.mb.board.db.BoardDTO;
import com.mysql.cj.Session;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
	    
	    String path = "/upload/board/";
	    String realPath = request.getServletContext().getRealPath(path);
	    System.out.println(" B : 프로필 사진 저장 경로 : " + realPath);
	    
	    int size = 10485760;
	    
	    MultipartRequest multi = null;
	    try
	    {
	    
	    	multi = new MultipartRequest(request, realPath, size, "UTF-8", new DefaultFileRenamePolicy());
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	      response.setContentType("text/html; charset=UTF-8");
	      PrintWriter out = response.getWriter();
	      out.print("<script>");
	      out.print("alert('이미지 크기가 10MB를 초과합니다.');");
	      out.print("history.back();");
	      out.print("</script>");
	      out.close();
	      
	      return null;
	    }
	    
	    String id = multi.getParameter("id");
	    String when_name = multi.getParameter("when_name");
	    String food_category = multi.getParameter("food_category");
	    String where_name = multi.getParameter("where_name");
	    String upload_image = multi.getFilesystemName("upload_image");
	    String content = multi.getParameter("content");
	    String latitude = multi.getParameter("latitude");
	    String longitude = multi.getParameter("longitude");
		
		// 세션 에서 가져오기

		//bno
		//write_time
			
		// DTO 객체 생성
		
		BoardDTO dto = new BoardDTO();
		
		// 전달된 정보를 저장
		
		dto.setWrite_time(new Timestamp(System.currentTimeMillis()));
		
		dto.setWhen_name(when_name); // 언제
		dto.setFood_category(food_category); // 음식
		dto.setWhere_name(where_name); // 어디서
		dto.setUpload_image(path + upload_image); //첨부파일
		dto.setId(id);
		dto.setContent(content); // 본문
		dto.setLatitude(latitude);//
		dto.setLongitude(longitude);//
		
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
