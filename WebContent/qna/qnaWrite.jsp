<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>QNA 글 작성 페이지</title>
</head>
<body>
<%
String id = (String)session.getAttribute("id");
%>

<fieldset>
<legend>QnA 등록</legend>
	<form action="./QnaAddAction.bo" method="post" name="fr">
	<input type="hidden" name="id" value="<%=id%>">
	 <table border="1">
		<tr>
			<td>제목</td>
			<td><input type="text" name="title" required="required"></td>
		</tr>
		<tr>
			<td>본문 내용</td>
			<td><textarea rows="10" cols="30" name="content" required="required"></textarea></td>
		</tr>
		
		<tr>
			<td colspan="2">
			<input type="submit" value="글 등록">
			<input type="button" value="목록" onclick=" location.href='./QnaList.bo'; ">
		</tr>
	</table>
	</form>
</fieldset>

</body>
</html>