<%@ page import="com.me.member.db.MemberDTO"%>
<%@ page import="com.me.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>WebContent/member/info.jsp</h1>
  <%
    // 로그인한 사용자의 정보를 출력하는 페이지
    
    // 로그인을 안한경우 -> 로그인페이지로 이동
    String id = (String) session.getAttribute("id");
    
    if(id == null){
    	response.sendRedirect("./MemberLogin.me");
    }
    // 로그인한 사용자의 모든 정보를 DB에서 가져와서 화면에 출력 
    // MemberInfoAction에서 저장한 DTO 객체 정보를 가져온다
    // request.setAttribute("mdto",mdto);
    
    MemberDTO mdto = (MemberDTO) request.getAttribute("mdto");
    // EL을 쓰면 속성을 불러와서 DTO 객체를 만들어서 굳이 다운캐스팅하여 쓰지 않아도 된다!
    
  	String profile = new MemberDAO().getProfile(id);
  %>
  <div>
  	프로필 사진 : <img alt="<%=profile %>" src="<%=profile %>" style="width: 100px; height: 100px;"> <br>
  	아이디 : ${ mdto.id } <br>
  	비밀번호 : ******** 
  	<input type="button" onclick="location.href='./MemberModifyoldPw.me';" value="비밀번호 변경하러 가기"> <br>
  	닉네임 : ${ mdto.nickname } <br>
  	전화번호 : ${ mdto.phone } <br>
  	이메일 : ${ mdto.email } <br>
  	주소 : ${ mdto.address } <br>
  </div>
  <div>
  	<a href="./MemberUpdate.me">회원정보 수정</a>
  </div>
  <div>
	<a href="./Main.me">메인페이지로 이동</a>
  </div>
  
</body>
</html>