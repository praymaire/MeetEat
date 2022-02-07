<%@page import="com.bo.qna.db.QnaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QnA 글 본문 페이지</title>
</head>
<body>

<%
	String id = (String)session.getAttribute("id");

	QnaDTO qdto = (QnaDTO)request.getAttribute("qdto");
%>

<form>
<table>
    
	<tr>
		<td colspan="2"> 작성자  </td>
		<td colspan="3"><input type="text" name="name" value="<%=qdto.getId()%>"> </td>
	</tr>
	
	<tr>
		<td colspan="2"> 글 제목  </td>
		<td colspan="3"><input type="text" name="title" value="<%=qdto.getTitle()%>"> </td>
	</tr>
	
	<tr>
		<td colspan="2"> 글 내용  </td>
		<td colspan="3">
		   <textarea rows="10" cols="40" name="content"><%=qdto.getContent() %></textarea>
		</td>
	</tr>

</table>

<div>
<%if(id!=null && id.equals(qdto.getId())){ %>
	<input type="button" value="수정" onclick="location.href='./QnaUpdate.bo?qno=<%=qdto.getQno()%>'">
	<input type="button" value="삭제" onclick="location.href='./QnaDelete.bo?qno=<%=qdto.getQno()%>'">	
<%} %>	
<%if(id!=null && id.equals("admin")){ %>	
	<input type="button" value="답글" onclick="location.href='QnaReply.bo?qno=<%=qdto.getQno()%>&re_ref=<%=qdto.getRe_ref()%>&re_lev=<%=qdto.getRe_lev()%>&re_seq=<%=qdto.getRe_seq()%>';">
<%} %>
	<input type="button" value="목록" onclick=" location.href='./QnaList.bo'" >
	
</div>

</form>



</body>
</html>