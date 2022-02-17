<%@ include file="../Main/top.jsp" %>


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

//전달된 파라미터 저장
int qno = Integer.parseInt(request.getParameter("qno"));
int re_ref = Integer.parseInt(request.getParameter("re_ref"));
int re_lev = Integer.parseInt(request.getParameter("re_lev"));
int re_seq = Integer.parseInt(request.getParameter("re_seq"));
%>
<fieldset class="container">
<legend>QnA 답변 등록</legend>
	<form action="./QnaReplyProAction.bo" method="post" name="fr">
		<input type="hidden" name="qno" value="<%=qno %>">
		<input type="hidden" name="re_ref" value="<%=re_ref %>">
		<input type="hidden" name="re_lev" value="<%=re_lev %>">
		<input type="hidden" name="re_seq" value="<%=re_seq %>">
		<input type="hidden" name="id" value="<%=id%>">
	<table class="table table-hover">

	<tr>
		<td class="form-label"> 글 제목 </td>
		<td><input type="text" name="title" required="required" class="form-control p-2"> </td>
	</tr>
	
	<tr>
		<td> 글 내용 </td>
		<td>
		   <textarea rows="10" class="form-control" name="content"></textarea>
		</td>
	</tr>

	<tr>
	<td colspan="2">
	  <input type="submit" value="답글등록" class="btn btn-success">
	  <input type="reset" value="초기화" class="btn btn-secondary">
	  <input type="button" value="목록으로" onclick="location.href='./QnaList.bo'"  class="btn btn-secondary">
	 </td>
	 </tr>
	 </table>
	</form>
</fieldset>

 <div class="offcanvas-header"></div> 
 <div class="offcanvas-header"></div>

</body>
</html>