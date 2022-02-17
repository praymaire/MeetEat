<%@ include file="../Main/top.jsp" %>
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
	QnaDTO qdto = (QnaDTO)request.getAttribute("qdto");
%>

<fieldset class="container">
<legend>수정하기</legend>
<form action="./QnaUpdateProAction.bo" method="post" name ="fr">
<input type="hidden" name="qno" value="<%=qdto.getQno()%>">
<table class="table table-hover">
    
	<tr>
		<td class="form-label"> 작성자 </td>
		<td><input type="text" name="name" value="<%=qdto.getId()%>" readonly="readonly" class="form-control-plaintext p-2"> </td>
	</tr>
	
	<tr>
		<td class="form-label"> 제목 </td>
		<td><input type="text" name="title" value="<%=qdto.getTitle()%>" required="required" class="form-control-plaintext p-2"> </td>
	</tr>
	
	<tr>
		<td> 본문 내용 </td>
		<td>
		   <textarea rows="10" class="form-control" name="content" required="required"><%=qdto.getContent() %></textarea>
		</td>
	</tr>

</table>

<div>
<%if(id!=null && id.equals(qdto.getId())){ %>
	<input type="submit" value="수정등록" class="btn btn-success">
	<input type="reset" value="초기화" class="btn btn-warning">	
<%} %>
	<input type="button" value="목록" onclick=" location.href='./QnaList.bo'" class="btn btn-secondary">

	
</div>

</form>
</fieldset>
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div> 
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
</body>
</html>