<%@page import="com.bo.qna.db.QnaDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QnA 게시판</title>
</head>
<body>

<%
String id = (String) session.getAttribute("id");

QnaDTO qdto = new QnaDTO();
%>

  <table border="1">
    <tr>
       <td>글번호</td>
       <td>제목</td>
       <td>작성자</td>
       <td>작성날짜</td>
       <td>조회수</td>
     </tr>
     
     <%
        ArrayList qnaList 
          = (ArrayList)request.getAttribute("qnaList");
     %>
     
     <c:forEach var="qdto" items="${qnaList }">
         <tr>
	       <td>${qdto.qno }</td>
	       <td>
	       	<%if(qdto.getRe_lev()>0){ %>
				<img src="level.gif" height="10" width="<%=qdto.getRe_lev()*10%>">
				<img src="re.gif">
	 		<%} %>
	       <a href="./QnaContent.bo?qno=${qdto.qno}">${qdto.title }</a></td>
	       <td>${qdto.id }</td>
	       <td>${qdto.reg_date }</td>
	       <td>${qdto.readcount }</td>
	     </tr>
     </c:forEach>   
  
  </table>
  


<input type="button" value="글쓰러가기" onclick="location.href='./QnaWrite.bo'">
<input type="button" value="메인페이지" onclick="location.href='./Main.me'">

</body>
</html>