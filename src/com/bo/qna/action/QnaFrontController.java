package com.bo.qna.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class QnaFrontController extends HttpServlet{
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("QnaFrontController_doProcess() 호출 ");
		// 페이지가 GET/POST방식 상관없이 호출될때 실행되는 메서드
		// *.me - 회원정보 처리 동작
		
		// -----------------------1. 가상 주소 계산 --------------------------		
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI - "+requestURI);
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath - "+ctxPath);
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - "+command);
		System.out.println(" C : 1. 가상 주소 계산 끝! ");
		// -----------------------1. 가상 주소 계산 --------------------------
		// -----------------------2. 가상 주소 매핑(처리) --------------------
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/QnaList.bo")){
			System.out.println(" C : /QnaList.bo 호출");
			// 회원 가입 페이지 보여줘야함. (DBx -> 화면(View-jsp) 출력) 
			
			forward = new ActionForward();
			forward.setPath("./qna/qnalist.jsp");
			forward.setRedirect(false);			
		}else if(command.equals("/QnaAdd.bo")) {
			System.out.println( "C : /QnaAdd.bo 호출");
			
			action = new QnaAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
	// -----------------------3. 페이지 이동 -----------------------------
	// 페이지 이동정보가 있을때
	if(forward != null){
		if(forward.isRedirect()){ // true
			response.sendRedirect(forward.getPath());
			System.out.println(" C : 페이지 주소 - "+forward.getPath());
			System.out.println(" C : 페이지 이동 (sendRedirect) ");
		}else{ // false
			RequestDispatcher dis =
					request.getRequestDispatcher(forward.getPath());
			dis.forward(request, response);
			System.out.println(" C : 페이지 주소 - "+forward.getPath());
			System.out.println(" C : 페이지 이동 (forward) ");
		}
	}
	System.out.println(" C : 3. 페이지 이동끝 \n\n\n ");		
	// -----------------------3. 페이지 이동 -----------------------------
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("QnaFrontController_doGet() 호출");
		// 페이지가 GET 방식으로 호출될 때 실행되는 메서드
		super.doGet(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("QnaFrontController_doPost() 호출");
		// 페이지가 POST 방식으로 호출될 때 실행되는 메서드
		super.doPost(request, response);
	}

}
