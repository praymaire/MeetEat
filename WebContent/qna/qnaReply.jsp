<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QnA 답글 페이지</title>
</head>
<body>
<%
String id = (String) session.getAttribute("id");

//전달된 파라미터 저장
int qno = Integer.parseInt(request.getParameter("qno"));
int re_ref = Integer.parseInt(request.getParameter("re_ref"));
int re_lev = Integer.parseInt(request.getParameter("re_lev"));
int re_seq = Integer.parseInt(request.getParameter("re_seq"));
%>
<fieldset>
	<form action="./QnaReplyProAction.bo" method="post" name="fr">
		<input type="hidden" name="qno" value="<%=qno %>">
		<input type="hidden" name="re_ref" value="<%=re_ref %>">
		<input type="hidden" name="re_lev" value="<%=re_lev %>">
		<input type="hidden" name="re_seq" value="<%=re_seq %>">
	<table>
	
	<tr>
		<td colspan="2"> 작성자 </td>
		<td colspan="3"><input type="text" name="id" value="<%=id%>"> </td>
	</tr>
	
	<tr>
		<td colspan="2"> 글 제목 </td>
		<td colspan="3"><input type="text" name="title"> </td>
	</tr>
	
	<tr>
		<td colspan="2"> 글 내용 </td>
		<td colspan="3">
		   <textarea rows="10" cols="40" name="content"></textarea>
		</td>
	</tr>

	</table>
	  <input type="submit" value="답글등록" >
	  <input type="reset" value="초기화">
	  <input type="button" value="목록으로" onclick="location.href='./QnaList.bo'">
	  
	</form>
</fieldset>
</body>
</html>