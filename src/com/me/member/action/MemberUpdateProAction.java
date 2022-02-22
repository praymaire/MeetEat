package com.me.member.action;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.me.member.db.MemberDAO;
import com.me.member.db.MemberDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberUpdateProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		System.out.println(" M : MemberUpdateProAction_execute() 호출");
		
		// 세션 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
			// 이 메소드가 끝이 나고 Controller의 forward에 저장된다.
		}
		
		// 한글 처리
		request.setCharacterEncoding("UTF-8");
				
		// 파일 처리
		// 서버에서 저장할 localhost 뒤에 붙는 위치
		String path = "/upload/member/";
		String realPath = request.getServletContext().getRealPath(path);
		System.out.println(" M : 프로필 사진 저장 경로 - "+realPath);
		
		// 저장 용량 제한
		int maxSize = 10 * 1024 * 1024;
		
		// 실제 파일 업로드 처리
		MultipartRequest multi = null;
		
		try {
			multi = new MultipartRequest(request, realPath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch(Exception e) {
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
		File file = multi.getFile("profile_image");
		if(file != null) {
			String ext = file.getName().substring(file.getName().lastIndexOf(".")+1);
			if(ext.equals("jpg") || ext.equals("png") || ext.equals("gif")) {
				String prev = new MemberDAO().getMember(id).getProfile_image();
				File prevFile = new File(realPath+"/"+prev);
				if(prevFile.exists()) {
					prevFile.delete();
				}
				fileName = file.getName();
			} else {
				if(file.exists()) {
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
		
		// 넘어오는 데이터 받기	
		String profile_image = multi.getFilesystemName("profile_image");
		String pw = multi.getParameter("pw");
		String nickname = multi.getParameter("nickname");
		String phone = multi.getParameter("phone");
		String email1 = multi.getParameter("email1");
		String email = multi.getParameter("email");
		String address = multi.getParameter("address");
		String detailAddress = multi.getParameter("detailAddress");
		String extraAddress = multi.getParameter("extraAddress");
		System.out.println(" M : profile_image : "+profile_image);
		
		if(id == null || id.equals("") || pw == null || pw.equals("")
				|| nickname == null || nickname.equals("") || phone == null || phone.equals("") 
				|| email1 == null || email1.equals("")|| email == null || email.equals("") 
				|| address == null || address.equals("")){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('모든 내용을 입력하세요.');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			return null;
		}
		if(profile_image == null || profile_image.equals("")) {
			profile_image = "/upload/member/NoImage.png";
			System.out.println(" M : profile_image : "+profile_image);
		}
		
		
		// 이전 페이지에서 전달된 정보 저장(DTO)
		MemberDTO mdto = new MemberDTO();
		mdto.setProfile_image(path+profile_image);
		mdto.setId(id);
		mdto.setPw(pw);
		mdto.setNickname(nickname);
		mdto.setPhone(phone);
		mdto.setEmail(email1+"@"+email);
		mdto.setAddress(address+" "+detailAddress+extraAddress);
		
		System.out.println(" M : "+mdto.toString());
		
		// DAO 객체 생성 
		MemberDAO mdao = new MemberDAO();
		int result = mdao.updateMember(mdto);
		System.out.println(" M : 회원정보 수정 완료");
		
		if(result == 0) { // 비번 오류
			response.setContentType("text/html; charset=UTF-8"); 
			PrintWriter out = response.getWriter();	
			out.print("<script>");
			out.print("alert('비밀번호가 다릅니다');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return null; 
		} 
		else if(result == -1) { // 계정 X
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('계정이 존재하지 않습니다');");
			out.print("history.back();");
			out.print("</script>");
			out.close();
			
			return null;
		}
		
		// result == 1
		// 계정자
		// 페이지 이동
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print("alert('회원정보 수정이 완료되었습니다. 처음 화면으로 되돌아갑니다.');");
		out.print("location.href='./Main.me';");
		out.print("</script>");
		out.close();
		
		return null;
	}

}

