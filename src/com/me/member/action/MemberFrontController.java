package com.me.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFrontController extends HttpServlet{
										// HttpServlet을 상속할 때 request, response를 가져온다.
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("MemberFrontController_doProcess() 호출!");
		// 페이지가 GET/POST방식 상관없이 호출될 때 실행되는 메소드
		// *.me(확장자) - 프로젝트 중에서 회원정보 처리에 해당하는 동작
		
		// ---------------------------1. 가상 주소 계산 (*.me, *.net, *.com ...)--------------------------------------
		
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI - "+requestURI);
		//프로젝트 경로를 가져와서 저장
		String ctxPath = request.getContextPath(); 
		System.out.println(" C : ctxPath - "+ctxPath);
		// requestURI에서 프로젝트명(.)을 제외한 나머지 주소 가져오기
		// 계산된 주소에는 반드시 '/'가 포함되어 있어야 한다!
		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command - "+command);
		System.out.println(" C : 1. 가상 주소 계산 완료");
		
		// ---------------------------1. 가상 주소 계산 (*.me, *.net, *.com ...)--------------------------------------
		// ---------------------------2. 가상 주소 매핑 (처리)-------------------------------------------------------
		
		Action action = null;
		ActionForward forward = null;
		
		// 메인 페이지(view) .me -> .jsp : false
		if(command.equals("/Main.me")) {
			forward = new ActionForward();
			forward.setPath("./Main/main.jsp");
			forward.setRedirect(false);
		} 
		// 로그인 페이지(view) .me -> .jsp : false
		else if(command.equals("/MemberLogin.me")) {
			forward = new ActionForward();
			forward.setPath("./Member/loginForm.jsp");
			forward.setRedirect(false);
		}
		// 로그인 Pro페이지(model) : MemberLoginAction 객체 생성
		else if(command.equals("/MemberLoginAction.me")) {
			action = new MemberLoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 회원 가입 페이지(view) .me -> .jsp : false
		else if(command.equals("/MemberJoin.me")) {	
			forward = new ActionForward();
			forward.setPath("./Member/joinForm.jsp");	
			forward.setRedirect(false);
		}
		// 회원가입 Pro(model & view) .me -> .me : true
		else if(command.equals("/MemberJoinAction.me")) {
			action = new MemberJoinAction();
			try {
				forward = action.execute(request, response); 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 로그아웃(DB 정보 X, view X) js로 main.me로 이동
		else if(command.equals("/MemberLogout.me")) {
			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 회원정보 확인(DB 정보 O, view O) 
		else if(command.equals("/MemberInfo.me")) {
			// MemberInfoAction() 객체 생성
			action = new MemberInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 회원정보 수정(DB 정보, view(EL 표현식))
		else if(command.equals("/MemberUpdate.me")) {
			// MemberUpdateAction() 객체 생성
			action = new MemberUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 회원정보 수정Pro(DB 처리, view로 이동(main.jsp))
		else if(command.equals("/MemberUpdateProAction.me")) {
			// MemberUpdateProAction() 객체 생성
			action = new MemberUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 회원 탈퇴(view) : false
		else if(command.equals("/MemberDelete.me")) {
			forward = new ActionForward();
			forward.setPath("./Member/deleteForm.jsp");
			forward.setRedirect(false);
		}
		// 회원 탈퇴(DB O, view X) : js로 main.me로 이동 
		else if(command.equals("/MemberDeleteAction.me")) {
			action = new MemberDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 관리자 회원목록 (DB O, view O)
		else if(command.equals("/MemberList.me")) {			
			action = new MemberListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 아이디 중복 체크(DB O, view X) js
		else if(command.equals("/MemberIdCheck.me")) {
			action = new MemberIdCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 닉네임 중복 검사(DB O, view X) js
		else if(command.equals("/MemberNicknameCheck.me")) {
			action = new MemberNicknameCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		///// 멤버
		else if (command.equals("/MemberReport.me")) {
			action = new MemberReportAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if (command.equals("/MemberReportAction.me")) {
			action = new MemberReportProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		// 패스워드 찾는 페이지(view) .me -> .jsp (false)
		else if(command.equals("/MemberFindPwAction.me")) {
			forward = new ActionForward();
			forward.setPath("./Member/findPwForm.jsp");
			forward.setRedirect(false);
		}
		
		// 패스워드 찾는Pro(DB O, view O) - 패스워드 변경 페이지(view) .me -> .jsp(false)
		else if(command.equals("/MemberFindPwProAction.me")) {
			action = new MemberFindPwProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 임시패스워드 변경페이지(view) - .me -> .jsp(false)
		else if(command.equals("/MemberModifytempPw.me")) {
			forward = new ActionForward();
			forward.setPath("./Member/changePw.jsp");
			forward.setRedirect(false);
		}
		// 임시패스워드 저장 Pro(DB O, view x) 
		else if(command.equals("/MemberModifytempPwAction.me")) {
			action = new MemberModifytempPwAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// id 찾는 페이지(view O) .me -> .jsp (false)
		else if(command.equals("/MemberFindIdAction.me")) {
			forward = new ActionForward();
			forward.setPath("./Member/findIdForm.jsp");
			forward.setRedirect(false);
		}
		// id Pro페이지(DB O, view O)
		else  if(command.equals("/MemberFindIdProAction.me")) {
	 		action = new MemberFindIdProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// pw 변경 페이지
		else if(command.equals("/MemberModifyoldPw.me")) {
			forward = new ActionForward();
			forward.setPath("./Member/modifyoldPw.jsp");
			forward.setRedirect(false);
		}
		
		else if(command.equals("/MemberModifyoldPwAction.me")) {
			action = new MemberModifyoldPwAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if(command.equals("/MemberLoginCheckAction.me")) {
			action = new MemberLoginCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		//// 멤버

		System.out.println(" C : 2. 가상 주소 매칭(처리) 끝 (페이지 이동 아직 실행 전)");
		
		// ---------------------------2. 가상 주소 매핑 (처리)-------------------------------------------------------
		// ---------------------------3. 페이지 이동---------------------------------------------------------------
		
		// 페이지 이동정보가 있을 때만 이동.
		if(forward != null) {
			// if문에 들어갔을 때!
			if(forward.isRedirect()) { // true
				response.sendRedirect(forward.getPath());
				System.out.println(" C : 페이지 주소 - "+forward.getPath());
				System.out.println(" C : 페이지 이동 (sendRedirect)");
			} else { // false
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				System.out.println(" C : 페이지 주소 - "+forward.getPath());
				System.out.println(" C : 페이지 이동 (forward)");
			}
			
		}
		System.out.println(" C : 3. 페이지 이동 완료  \n\n\n");
		
		// ---------------------------3. 페이지 이동---------------------------------------------------------------

		
	
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("MemberFrontController_doGet() 호출!");
		// 페이지가 GET방식으로 호출될 때 실행되는 메소드
	
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		System.out.println("MemberFrontController_doPost() 호출!");
		// 페이지가 POST방식으로 호출될 때 실행되는 메소드
		
		doProcess(request, response);
	}

	
}