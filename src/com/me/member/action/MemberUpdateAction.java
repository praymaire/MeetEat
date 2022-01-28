package com.me.member.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.me.member.db.MemberDAO;
import com.me.member.db.MemberDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MemberUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		System.out.println(" M : MemberUpdateAction_execute() 호출");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// 세션 체크
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./MemberLogin.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// DAO 객체 생성 - id에 해당하는 회원정보 가져오기
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = new MemberDTO();
		mdto = mdao.getMember(id);
		
		String profile_image = mdto.getProfile_image();
		System.out.println("profile_image "+profile_image);
		
		OutputStream out = response.getOutputStream();
		File file = new File(profile_image);
		FileInputStream in = new FileInputStream(file);
		byte[] buffer = new byte[1024 * 8];
		while(true) {
			int count = in.read(buffer);
			if(count == -1)
				break;
			out.write(buffer,0,count);
		}
		in.close();
		out.close();
		
		// 해당정보 request 영역에 저장
		request.setAttribute("mdto", mdto);
		
		// DB에서 전달된 정보를 처리없이 바로 view페이지로 전달할 때
		// request.setAttribute("mdto", mdao.getMember(id));
		 	// MemberDTO mdto = mdao.getMember(id); 와
			// request.setAttribute("mdto", mdto); 를 합친 코드
		
		// 페이지 이동(updateForm.jsp)
		forward.setPath("./Member/updateForm.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

	
}
