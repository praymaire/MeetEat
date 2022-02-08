package com.ad.admin.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("AdminFrontController_doProcess() 호출");
		// 페이지가 GET/POST 방식 상관없이 호출될 때 실행되는 메서드
		// *.me - 회원정보 처리 동작
		
		// -------------------- 1. 가상 주소 계산 --------------------------------
		// 가상주소 가져오기
		// url : 프로토콜://아이피:포트번호/프로젝트명/가상주소
		// String url = request.getRequestURL()+"";
		
		// uri : 프로젝트명/가상주소		
		String requestURI = request.getRequestURI();
		System.out.println("C: requestURI - "+requestURI);
		String ctxPath = request.getContextPath();
		System.out.println("C: ctxPath - "+ctxPath);
		String command = requestURI.substring(ctxPath.length());
		System.out.println("C: command - "+command);
		
		
		// -------------------- 1. 가상 주소 계산 --------------------------------
		
		// -------------------- 2. 가상 주소 매핑(처리) ---------------------------
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/MemberList.ad")){
			// DB사용해서 정보를 화면에 바로 출력
			// MemberListAction() 객체 생성
			action = new AdminListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		// -------------------- 3. 페이지 이동 ----------------------------------
		if(forward != null) {
			if(forward.isRedirect()) { // true
				response.sendRedirect(forward.getPath());
				System.out.println(" C : 페이지 이동 (sendRedirect)");
			}else { // false
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				System.out.println(" C : 페이지 이동 (forward)");
			}
		}
		System.out.println("C: 3. 페이지 이동 끝 \n\n\n");
		// -------------------- 3. 페이지 이동 ----------------------------------
	
	}//end of doProcess
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AdminFrontController_doGet() 호출");
		// 페이지가 GET 방식으로 호출될 때 실행되는 메서드
		doProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("AdminFrontController_doPost() 호출");
		// 페이지가 POST 방식으로 호출될 때 실행되는 메서드
		doProcess(request, response);
	}

}
