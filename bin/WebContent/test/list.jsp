<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>WebContent/member/list.jsp</h1>
   
   <%
     // 세션제어 (로그인X,관리자X)
     String id = (String)session.getAttribute("id");
   
    if(id == null || !id.equals("admin") ){
    	response.sendRedirect("./MemberLogin.me");
    }
     
    // request.setAttribute("memberList", dao.getMemberList());
   %>
   ${memberList }
   
   <table border="1">
     <tr>
       <td>아이디</td>
       <td>비밀번호</td>
       <td>이름</td>
       <td>나이</td>
       <td>성별</td>
       <td>이메일</td>
       <td>회원가입일</td>
     </tr>
     
     
     <%
    // ArrayList memberList =
    // (ArrayList)request.getAttribute("memberList");
     %>
     
     
     <c:forEach var="dto" items="${memberList }">
         <tr>
	       <td>${dto.id }</td>
	       <td>${dto.pass }</td>
	       <td>${dto.name }</td>
	       <td>${dto.age }</td>
	       <td>${dto.gender }</td>
	       <td>${dto.email }</td>
	       <td>${dto.reg_date }</td>
	     </tr>
     </c:forEach>    
     
   </table>
   
   
   
   
   
   
   
</body>
</html>