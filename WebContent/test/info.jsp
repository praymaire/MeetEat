<%@page import="com.itwillbs.member.db.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>WebContent/member/info.jsp(model2)</h1>
  <%
    // 로그인한 사용자의 정보를 출력하는 페이지
    
    // 로그인을 안한경우 -> 로그인페이지로 이동
    String id = (String) session.getAttribute("id");
    
    if(id == null){
    	response.sendRedirect("./MemberLogin.me");
    }
    // 로그인한 사용자의 모든 정보를 DB에서 가져와서 화면에 출력
    // MemberInfoAction에서 저장한 DTO 객체 정보를 가져오기
    // request.setAttribute("dto", dto);
    MemberDTO dto = (MemberDTO) request.getAttribute("dto");
  %>
  <h3> 아이디 : <%=dto.getId() %> </h3>
  <h3> 아이디(el) : ${requestScope.dto.id }</h3>
  <h3> 아이디(el) : ${dto.id }</h3>
  <h3> 비밀번호 : <%=dto.getPass() %></h3>
  <h3> 이름 : <%=dto.getName() %></h3>
  <h3> 나이 : <%=dto.getAge() %></h3>
  <h3> 성별 : <%=dto.getGender() %></h3>
  <h3> 이메일 : <%=dto.getEmail() %></h3>
  <h3> 회원가입일 : <%=dto.getReg_date() %></h3>

  <h2><a href="./Main.me">메인페이지로 이동</a></h2>
  
  
  
  
</body>
</html>