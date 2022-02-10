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
       <td>닉네임</td>
       <td>전화번호</td>
       <td>이메일</td>
       <td>주소</td>
       <td>회원포인트</td>
       <td>회원등급</td>
       <td>누적신고횟수</td>
       <td>정지날짜</td>
       <td>관리</td>
     </tr>
     
     <%
     	ArrayList memberList =
    	(ArrayList)request.getAttribute("memberList");
     %>
     <c:if test="${requestScope.cnt != 0 }">
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
	       <td>${dto.ban_date }</td>
	       <td><input type="button" value="정지관리" onclick="banManagePopup('${dto.id}', '${dto.reported_count }');"> /
	       <input type="button" value="정지해제" onclick="banCancel('${dto.id}');"> /
	       <input type="button" value="계정삭제"></td>
	     </tr>
     </c:forEach>  
     </c:if>
       	<c:if test="${requestScope.cnt == 0 }">
			<tr>
				<td> 회원이 존재하지 않습니다. </td>
			</tr>
	 </c:if>
   </table>
   
   
   <!-- 페이징  처리 -->
	<div id="page_control">
		<c:if test="${requestScope.cnt != 0 }">
			<c:if test="${requestScope.startPage > requestScope.pageBlock }">
				<a href="./MemberList.ad?pageNum=${requestScope.startPage - requestScope.pageBlock }">이전</a>
			</c:if>
			<c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }" step="1">
				<a href="./MemberList.ad?pageNum=${i }">${i }</a>
			</c:forEach>
			<c:if test="${requestScope.endPage < requestScope.pageCount }">
				<a href="./MemberList.ad?pageNum=${ requestScope.startPage +  requestScope.pageBlock}">다음</a>
			</c:if>
		</c:if>
	</div>
  <!-- 페이징  처리 -->
   
   
   
   
   
   
</body>
</html>