package com.ad.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ad.admin.db.AdminDAO;



public class AdminDeleteAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		// 전달된 파라메터 저장
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
				
		// DAO 객체를 생성 - 로그인 체크 메서드
		AdminDAO dao = new AdminDAO();
		int result = dao.deleteMember(id, pass);


		// 처리 결과에 따른 페이지 이동(0,1,-1)-js
		if(result == 0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
					
			out.print("<script>");
			out.print(" alert('비밀번호 오류');");
			out.print(" history.back(); ");
			out.print("</script>");
					
			out.close();
			return null;
		}
				
		if(result == -1){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
					
			out.print("<script>");
			out.print(" alert('회원정보 없음');");
			out.print(" history.back(); ");
			out.print("</script>");
					
			out.close();
			return null;
		}
				
		//result == 1
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
				
		out.print("<script>");
		out.print(" alert('회원정보 수정완료!');");
		out.print(" location.href='./MemberList.me'; ");
		out.print("</script>");
				
		out.close();
		return null;
	}
}
