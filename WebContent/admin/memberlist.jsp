<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원정보 관리 페이지</title>
</head>
<body>
<h1>WebContent/admin/memberlist.jsp</h1>
<%--    
   <%
     // 세션제어 (로그인X,관리자X)
     String id = (String)session.getAttribute("id");
   
    if(id == null || !id.equals("admin") ){
    	response.sendRedirect("./MemberLogin.me");
    }
     
    // request.setAttribute("memberList", dao.getMemberList());
   %> --%>
   ${memberList }
   
   <table border="1">
     <tr>
       <td>아이디</td>
       <td>비밀번호</td>
       <td>닉네임</td>
       <td>전화번호</td>
       <td>이메일</td>
       <td>주소</td>
       <td>회원포인트</td>
       <td>회원등급</td>
       <td>누적신고횟수</td>
       <td>관리</td>
     </tr>
     
     
     <%
     	ArrayList memberList =
    	(ArrayList)request.getAttribute("memberList");
     %>
     
     
     <c:forEach var="dto" items="${memberList }">
         <tr>
	       <td>${dto.id }</td>
	       <td>${dto.pw }</td>
	       <td>${dto.nickname }</td>
	       <td>${dto.phone }</td>
	       <td>${dto.email }</td>
	       <td>${dto.address }</td>
	       <td>${dto.user_point }</td>
	       <td>${dto.user_level }</td>
	       <td>${dto.reported_count }</td>
	       <td><input type="button" value="정지"><input type="button" value="삭제"></td>
	     </tr>
     </c:forEach>    
     
   </table>
   
   
   
   
   
   
   
</body>
</html>