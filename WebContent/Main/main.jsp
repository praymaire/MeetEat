<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Insert title here</title>
 </head>
 <body>
  <h1>WebContent/Main/main.jsp</h1>
  
  <%
	// 로그인한 id값을 들고다닌다
	String id = (String)session.getAttribute("id");

	if(id != null) { 
  %>
  <div>	
	<a href="#"><%=id %>님 반갑습니다.</a> | <a href="./MemberInfo.me">회원정보 확인하기</a> <br>
	<a href="./MemberLogout.me">로그아웃</a> | <a href="./MemberDelete.me">탈퇴하기</a>
  </div>
  <div>
	<a href="./MemberReport.me">신고하기</a>
  </div>
  <%
	} else {
  %>
  <div>
	<a href="./MemberLogin.me">로그인</a> | <a href="./MemberJoin.me">회원가입</a>
  </div>
  
  
  <%	
	}
  %>
 
  <% 
   if(id != null){
   	if(id.equals("admin")){
  %>
  
  <div>
  	<a href='./MemberList.me'> 회원전체 목록보기</a>
  </div>
  <%
     }
   }
  
  %>
  
  

</body>
</html>