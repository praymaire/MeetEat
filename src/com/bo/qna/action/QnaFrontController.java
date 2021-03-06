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
		System.out.println("QnaFrontController_doProcess() ?ΈμΆ? ");
		// ??΄μ§?κ°? GET/POSTλ°©μ ?κ΄???΄ ?ΈμΆλ ? ?€??? λ©μ?
		
		// -----------------------1. κ°?? μ£Όμ κ³μ° --------------------------		
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI - "+requestURI);
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath - "+ctxPath);
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - "+command);
		System.out.println(" C : 1. κ°?? μ£Όμ κ³μ° ?! ");
		// -----------------------1. κ°?? μ£Όμ κ³μ° --------------------------
		// -----------------------2. κ°?? μ£Όμ λ§€ν(μ²λ¦¬) --------------------
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/QnaList.bo")){
			System.out.println(" C : /QnaList.bo ?ΈμΆ?");
			action = new QnaListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/QnaWrite.bo")) {
			System.out.println(" C : /QnaWrite.bo ?ΈμΆ?! ");
			
			forward = new ActionForward();
			forward.setPath("./qna/qnaWrite.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/QnaAddAction.bo")) {
			System.out.println( "C : /QnaAddAction.bo ?ΈμΆ?");
			
			action = new QnaAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/QnaContent.bo")) {
			System.out.println( "C : /QnaContent.bo ?ΈμΆ?");
			
			action = new QnaContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/QnaUpdate.bo")) {
			System.out.println("C : /QnaUpdate.bo ?ΈμΆ?");
			
			action = new QnaUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/QnaUpdateProAction.bo")) {
			System.out.println("C : /QnaUpdateProAction.bo ?ΈμΆ?");
			
			action = new QnaUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/QnaDelete.bo")) {
			System.out.println("C : /QnaDelete.bo ?ΈμΆ?");
			
			action = new QnaDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/QnaReply.bo")) {
			System.out.println(" C : /QnaReply.bo ?ΈμΆ? ");
			
			forward = new ActionForward();
			forward.setPath("./qna/qnaReply.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/QnaReplyProAction.bo")) {
			System.out.println("C : /QnaReplyProAction.bo ?ΈμΆ?");
			
			action = new QnaReplyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
	// -----------------------3. ??΄μ§? ?΄? -----------------------------
	// ??΄μ§? ?΄?? λ³΄κ? ???
	if(forward != null){
		if(forward.isRedirect()){ // true
			response.sendRedirect(forward.getPath());
			System.out.println(" C : ??΄μ§? μ£Όμ - "+forward.getPath());
			System.out.println(" C : ??΄μ§? ?΄? (sendRedirect) ");
		}else{ // false
			RequestDispatcher dis =
					request.getRequestDispatcher(forward.getPath());
			dis.forward(request, response);
			System.out.println(" C : ??΄μ§? μ£Όμ - "+forward.getPath());
			System.out.println(" C : ??΄μ§? ?΄? (forward) ");
		}
	}
	System.out.println(" C : 3. ??΄μ§? ?΄?? \n\n\n ");		
	// -----------------------3. ??΄μ§? ?΄? -----------------------------
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("QnaFrontController_doGet() ?ΈμΆ?");
		// ??΄μ§?κ°? GET λ°©μ?Όλ‘? ?ΈμΆλ  ? ?€??? λ©μ?
		doProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("QnaFrontController_doPost() ?ΈμΆ?");
		// ??΄μ§?κ°? POST λ°©μ?Όλ‘? ?ΈμΆλ  ? ?€??? λ©μ?
		doProcess(request, response);
	}

}
