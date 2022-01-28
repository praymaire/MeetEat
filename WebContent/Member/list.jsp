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
   <h1>./Member/list.jsp</h1>
   
   <%
     // 세션제어 (로그인X,관리자X)
     String id = (String)session.getAttribute("id");
   
    if(id == null || !id.equals("admin") ){
    	response.sendRedirect("./MemberLogin.me");
    }
     
   %>
   
   <table border="1">
     <tr>
       <td>아이디</td>
       <td>비밀번호</td>
       <td>이름</td>
       <td>전화번호</td>
       <td>이메일</td>
     </tr>
    <c:forEach var="list" items="${ memberList }">
     <tr>
       <td>${ list.id }</td>
       <td>${ list.pw }</td>
       <td>${ list.name }</td>
       <td>${ list.ph } <br>
       <td>${ list.email }</td>
     </tr>
    </c:forEach> 
   </table>
</body>
</html>