package com.me.chat.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChatController extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
	public void init(ServletConfig config) 
			throws ServletException {}

	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		//ServletContext application = request.getServletContext();	//	전역 객체
		
		//	페이지 페이지 마다 연결 시간 설정 가능. 전역 객체이다.
		HttpSession session = request.getSession();					 
		
		//	1. 페이지를 전송하기 전에 url을 분석하고 분류해서 실행
		String url = request.getRequestURI();
		System.out.println("url : " +url);
		String contextPath = request.getContextPath();
		System.out.println("contextPath : " +contextPath);
		String command = url.substring(contextPath.length());
		System.out.println(" C : command - " +command);
		System.out.println(" C : 1. 가상 주소 계산 완료");
		
		//	웹 페이지에서 채팅 버튼이 클릭되면
		if (command.equals("/chatpage.chat")) {
			// 2. 분류된 url을 선택하고 전송하는 작업
			RequestDispatcher dispatcher = request.getRequestDispatcher("./Chat/chatpage.jsp");
					
			//	3. 페이지로 밀어주는 작업
			dispatcher.forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		service(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		service(request, response);
	}
	
}
