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
		System.out.println("QnaFrontController_doProcess() ?ò∏Ï∂? ");
		// ?éò?ù¥Ïß?Í∞? GET/POSTÎ∞©Ïãù ?ÉÅÍ¥??óÜ?ù¥ ?ò∏Ï∂úÎê†?ïå ?ã§?ñâ?êò?äî Î©îÏÑú?ìú
		
		// -----------------------1. Í∞??ÉÅ Ï£ºÏÜå Í≥ÑÏÇ∞ --------------------------		
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI - "+requestURI);
		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath - "+ctxPath);
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - "+command);
		System.out.println(" C : 1. Í∞??ÉÅ Ï£ºÏÜå Í≥ÑÏÇ∞ ?Åù! ");
		// -----------------------1. Í∞??ÉÅ Ï£ºÏÜå Í≥ÑÏÇ∞ --------------------------
		// -----------------------2. Í∞??ÉÅ Ï£ºÏÜå Îß§Ìïë(Ï≤òÎ¶¨) --------------------
		
		Action action = null;
		ActionForward forward = null;
		
		if(command.equals("/QnaList.bo")){
			System.out.println(" C : /QnaList.bo ?ò∏Ï∂?");
			action = new QnaListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/QnaWrite.bo")) {
			System.out.println(" C : /QnaWrite.bo ?ò∏Ï∂?! ");
			
			forward = new ActionForward();
			forward.setPath("./qna/qnaWrite.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/QnaAddAction.bo")) {
			System.out.println( "C : /QnaAddAction.bo ?ò∏Ï∂?");
			
			action = new QnaAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/QnaContent.bo")) {
			System.out.println( "C : /QnaContent.bo ?ò∏Ï∂?");
			
			action = new QnaContentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/QnaUpdate.bo")) {
			System.out.println("C : /QnaUpdate.bo ?ò∏Ï∂?");
			
			action = new QnaUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/QnaUpdateProAction.bo")) {
			System.out.println("C : /QnaUpdateProAction.bo ?ò∏Ï∂?");
			
			action = new QnaUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/QnaDelete.bo")) {
			System.out.println("C : /QnaDelete.bo ?ò∏Ï∂?");
			
			action = new QnaDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/QnaReply.bo")) {
			System.out.println(" C : /QnaReply.bo ?ò∏Ï∂? ");
			
			forward = new ActionForward();
			forward.setPath("./qna/qnaReply.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/QnaReplyProAction.bo")) {
			System.out.println("C : /QnaReplyProAction.bo ?ò∏Ï∂?");
			
			action = new QnaReplyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
	// -----------------------3. ?éò?ù¥Ïß? ?ù¥?èô -----------------------------
	// ?éò?ù¥Ïß? ?ù¥?èô?†ïÎ≥¥Í? ?ûà?ùÑ?ïå
	if(forward != null){
		if(forward.isRedirect()){ // true
			response.sendRedirect(forward.getPath());
			System.out.println(" C : ?éò?ù¥Ïß? Ï£ºÏÜå - "+forward.getPath());
			System.out.println(" C : ?éò?ù¥Ïß? ?ù¥?èô (sendRedirect) ");
		}else{ // false
			RequestDispatcher dis =
					request.getRequestDispatcher(forward.getPath());
			dis.forward(request, response);
			System.out.println(" C : ?éò?ù¥Ïß? Ï£ºÏÜå - "+forward.getPath());
			System.out.println(" C : ?éò?ù¥Ïß? ?ù¥?èô (forward) ");
		}
	}
	System.out.println(" C : 3. ?éò?ù¥Ïß? ?ù¥?èô?Åù \n\n\n ");		
	// -----------------------3. ?éò?ù¥Ïß? ?ù¥?èô -----------------------------
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("QnaFrontController_doGet() ?ò∏Ï∂?");
		// ?éò?ù¥Ïß?Í∞? GET Î∞©Ïãù?úºÎ°? ?ò∏Ï∂úÎê† ?ïå ?ã§?ñâ?êò?äî Î©îÏÑú?ìú
		doProcess(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("QnaFrontController_doPost() ?ò∏Ï∂?");
		// ?éò?ù¥Ïß?Í∞? POST Î∞©Ïãù?úºÎ°? ?ò∏Ï∂úÎê† ?ïå ?ã§?ñâ?êò?äî Î©îÏÑú?ìú
		doProcess(request, response);
	}

}
