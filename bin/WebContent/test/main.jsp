<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h1>WebContent/member/main.jsp</h1>
  
  <h2> 메인페이지 (model2) </h2>
  
  <!-- 로그인 정보없이는 접근 불가능 
      로그인정보가 없을경우 - 로그인페이지로 이동
      로그인정보가 있을경우 - 화면에 로그인정보(아이디) 출력  
  -->
  <%
    String id = (String)session.getAttribute("id");
  
    if(id == null){
    	response.sendRedirect("./MemberLogin.me");
    }
  %>
  
  로그인 아이디 : <%=id %><br>
  <input type="button" value="로그아웃" onclick=" location.href='./MemberLogout.me'; ">
  
  <hr><hr>
  
  <h2><a href='./MemberInfo.me'> 회원 정보 확인하기</a></h2>
  
  <hr><hr>
  
  <h2><a href='./MemberUpdate.me'> 회원 정보 수정하기</a></h2>
  
  <hr><hr>
  
  <h2><a href='deleteForm.jsp'> 회원 정보 삭제하기</a></h2>

  
  <% 
   if(id != null){
   	if(id.equals("admin")){
   	
//     if(id !=null && id.equals("admin")){}  (o)
//     if(id.equals("admin") && id !=null ){}  (x)
   		%>
  
  <hr><hr>
  <hr><hr>
  <h2><a href='./MemberList.me'> 회원전체 목록보기</a></h2>
  
  <%
     }
   }
  
  %>
  

</body>
</html>