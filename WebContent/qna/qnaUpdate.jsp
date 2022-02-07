<%@page import="com.bo.qna.db.QnaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QnA 본문 글 수정 페이지</title>
</head>
<body>
<%
	String id = (String)session.getAttribute("id");

	QnaDTO qdto = (QnaDTO)request.getAttribute("qdto");
%>

<form action="./QnaUpdateProAction.bo" method="post" name ="fr">
<input type="hidden" name="qno" value="<%=qdto.getQno()%>">
<table>
    
	<tr>
		<td colspan="2"> 작성자 </td>
		<td colspan="3"><input type="text" name="name" value="<%=qdto.getId()%>" readonly="readonly"> </td>
	</tr>
	
	<tr>
		<td colspan="2"> 글 제목 </td>
		<td colspan="3"><input type="text" name="title" value="<%=qdto.getTitle()%>" required="required"> </td>
	</tr>
	
	<tr>
		<td colspan="2"> 글 내용 </td>
		<td colspan="3">
		   <textarea rows="10" cols="40" name="content" required="required"><%=qdto.getContent() %></textarea>
		</td>
	</tr>

</table>

<div>
<%if(id!=null && id.equals(qdto.getId())){ %>
	<input type="submit" value="수정등록">
	<input type="reset" value="초기화">	
<%} %>
	<input type="button" value="목록" onclick=" location.href='./QnaList.bo'" >

	
</div>

</form>
</body>
</html>