package com.itwillbs.geo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class GeoFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GeoFrontController_doProcess() 호출! ");
		// 페이지가 GET/POST방식 상관없이 호출될때 실행되는 메서드
		// *.do - 지오, 게시판 처리 동작
		
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
		
		
		
		
		
		
		
		
		if(command.equals("/BeforeMain.do")){
			System.out.println(" G : /BeforeMain.do 호출! ");
			// 위치정보 입력 전 페이지 보여줘야함. (DBx -> 화면(View-jsp) 출력) 
			
			forward = new ActionForward();
			forward.setPath("./GeoView/BeforeMain.jsp");
			forward.setRedirect(false);		
			
			
		}else if(command.equals("/roadname.do")){
			System.out.println(" G : /roadname.do 호출! ");
			//수동 : 위치정보를 수동으로 검색 (roadname.do) -> 좌표를 디비로 보내서 1000미터 근방의 사람 좌표를 가져와 마커로 표시 -> Aftermain으로 이동해 사람 목록을 리스트로 보여준다.			
		
			forward = new ActionForward();
			forward.setPath("./GeoView/roadname.jsp");
			forward.setRedirect(false);	
		}
		else if(command.equals("/markerview.do")){
			System.out.println(" G : /markerview.do 호출! ");
			//수동 : 위치정보를 수동으로 검색 (roadname.do) -> 좌표를 디비로 보내서 1000미터 근방의 사람 좌표를 가져와 마커로 표시 -> Aftermain으로 이동해 사람 목록을 리스트로 보여준다.			
		
			forward = new ActionForward();
			forward.setPath("./GeoView/markerview.jsp");
			forward.setRedirect(false);	
		}
		else if(command.equals("/GeoMarkerAction.do")){
			System.out.println(" G : /GeoMarkerAction.do 호출! ");
			// 수동 : 으로 받은 위치정보를 디비로 전달해서  저장된 사람의 좌표에 해당하는 마커를 표시한다
			
			action = new GeoMarkerAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/AfterMain.do")){
			// 메인페이지로 이동해서 사람목록을 리스트로 보여준다 ( 디비 조회? / 세션? )
			
			forward = new ActionForward();
			forward.setPath("./GeoView/AfterMain.jsp");
			forward.setRedirect(false);			
		}
		else if(command.equals("/GeoListAction.do")){
			// 메인페이지로 이동해서 사람목록을 리스트로 보여준다 ( 디비 조회? / 세션? )
			
			action = new GeoListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				}	
		}
		
		
		
		


		
		
		
		
		
		
		
		System.out.println(" G : 2. 가상 주소 매핑(처리) 끝 (페이지이동X)");
		// -----------------------2. 가상 주소 매핑(처리) --------------------
		// -----------------------3. 페이지 이동 -----------------------------
		// 페이지 이동정보가 있을때
		if(forward != null){
			if(forward.isRedirect()){ // true
				response.sendRedirect(forward.getPath());
				System.out.println(" G : 페이지 주소 - "+forward.getPath());
				System.out.println(" G : 페이지 이동 (sendRedirect) ");
			}else{ // false
				RequestDispatcher dis =
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				System.out.println(" G : 페이지 주소 - "+forward.getPath());
				System.out.println(" G : 페이지 이동 (forward) ");
			}
		}
		System.out.println(" G : 3. 페이지 이동끝 \n\n\n ");		
		// -----------------------3. 페이지 이동 -----------------------------
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GeoFrontController_doGet() 호출! ");
		// 페이지가 GET방식으로 호출될때 실행되는 메서드
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("GeoFrontController_doPost() 호출! ");
		// 페이지가 POST방식으로 호출될때 실행되는 메서드
		doProcess(request, response);
	}

}
