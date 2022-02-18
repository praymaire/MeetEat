package com.mb.board.action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mb.board.db.BoardDAO;
import com.mb.board.db.BoardDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
				
		request.setCharacterEncoding("UTF-8");
	    
	    System.out.println("여기까지 1" + request.getParameter("bno"));
	    int bno = Integer.parseInt(request.getParameter("bno"));
	    
	    String path = "/upload/board/";
	    String realPath = request.getServletContext().getRealPath(path);
	    System.out.println(" B : 이미지 저장경로 - " + realPath);
	    
	    int maxSize = 10485760;
	    
	    MultipartRequest multi = null;
	    try
	    {
	      multi = new MultipartRequest(request, realPath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	    }
	    catch (Exception e)
	    {
	      response.setContentType("text/html; charset=UTF-8");
	      PrintWriter out = response.getWriter();
	      out.print("<script>");
	      out.print("alert('이미지 크기가 10MB를 초과합니다');");
	      out.print("history.back();");
	      out.print("</script>");
	      out.close();
	      
	      return null;
	    }
	    String fileName = "";
	    File file = multi.getFile("upload_image");
	    if (file != null)
	    {
	      String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1);
	      if ((ext.equals("jpg")) || (ext.equals("png")) || (ext.equals("gif")))
	      {
	        String prev = new BoardDAO().getImage(bno);
	        File prevFile = new File(realPath + "/" + prev);
	        if (prevFile.exists()) {
	          prevFile.delete();
	        }
	        fileName = file.getName();
	      }
	      else
	      {
	        if (file.exists()) {
	          file.delete();
	        }
	        response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.print("<script>");
	        out.print("alert('이미지 파일만 업로드 가능합니다!');");
	        out.print("history.back();");
	        out.print("</script>");
	        out.close();
	        
	        return null;
	      }
	    }
	    
	    String id = multi.getParameter("id");
	    String when_name = multi.getParameter("when_name");
	    String food_category = multi.getParameter("food_category");
	    String where_name = multi.getParameter("where_name");
	    String upload_image = multi.getFilesystemName("upload_image");
	    String content = multi.getParameter("content");
	    System.out.println(" B : upload_image : " + upload_image);
	    
	    BoardDTO dto = new BoardDTO();
	    
	    dto.setWhen_name(when_name);
	    dto.setfood_category(food_category);
	    dto.setWhere_name(where_name);
	    dto.setUpload_image(path + upload_image);
	    dto.setId(id);
	    dto.setContent(content);
	    dto.setBno(bno);
	    
	    System.out.println(" BoardAction : " + dto);
	    
	    BoardDAO dao = new BoardDAO();
	    dao.ModifyBoard(dto);
	    
	    System.out.println(" B : 글 정보 수정 완료! ");
	    
	    ActionForward forward = new ActionForward();
	    forward.setPath("./list.mb");
	    forward.setRedirect(true);
	    return forward;
	}

}
