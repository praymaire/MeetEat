package com.mb.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController_doProcess() 호출! ");
		// 페이지가 GET/POST방식 상관없이 호출될때 실행되는 메서드
		// *.me - 회원정보 처리 동작
		
		// -----------------------1. 가상 주소 계산 --------------------------
		//가상주소 가져오기
		//String url = request.getRequestURL()+"";
		// http://localhost:8088/Model2/test1234.me
		// 프로토콜:// 아이피 : 포트번호 / 프로젝트명 / 가상주소
		//String uri = request.getRequestURI(); 
		// /Model2/test1234.me
		// 프로젝트명/ 가상주소
		//System.out.println("url : "+url);
		//System.out.println("uri : "+uri);
		
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
		
		if(command.equals("/write.mb")){
			System.out.println(" C : write.mb 호출! ");
			
			forward = new ActionForward();
			forward.setPath("./board/write.jsp");
			forward.setRedirect(false);			
		}
		else if(command.equals("/BoardWriteAction.mb")){
			System.out.println(" C : /BoardWriteAction.mb 호출! ");
		
			action = new BoardWriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/list.mb")){
			System.out.println(" C : /BoardListActiom.mb 호출! ");
			
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(command.equals("/read.mb")){
			System.out.println(" C : /BoardReadActiom.mb 호출! ");
			
			action = new BoardReadAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/BoardSearch.mb")){
			System.out.println(" C : /BoardSearch.mb 호출! ");
			
			action = new BoardSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/delete.mb")){
			System.out.println(" C : /BoardDeleteAction.me 호출! ");
			
			action = new BoardDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/modify.mb")){
			System.out.println(" C : /BoardModifyAction.me 호출! ");
			
			action = new BoardModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/modifyPro.mb")){
			System.out.println(" C : /BoardModifyProAction.me 호출! ");
			
			action = new BoardModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/Main.mb")){
			System.out.println(" C : /main.mb 호출! ");
			
			action = new MainListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		System.out.println(" C : 2. 가상 주소 매핑(처리) 끝 (페이지이동X)");
		// -----------------------2. 가상 주소 매핑(처리) --------------------
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
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController_doGet() 호출! ");
		// 페이지가 GET방식으로 호출될때 실행되는 메서드
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardFrontController_doPost() 호출! ");
		// 페이지가 POST방식으로 호출될때 실행되는 메서드
		doProcess(request, response);
	}

}