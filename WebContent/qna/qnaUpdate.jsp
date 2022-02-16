<%@ include file="../Main/top.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="com.bo.qna.db.QnaDTO"%>


<%
	QnaDTO qdto = (QnaDTO)request.getAttribute("qdto");
%>

<fieldset class="container">
<legend>수정하기</legend>
	<form action="./QnaUpdateProAction.bo" method="post" name ="fr">
	<input type="hidden" name="qno" value="${qdto.qno }">
	<input type="hidden" name="name" value="${qdto.id }">
	 <table class="table table-hover">
		<tr>
			<td class="form-label">제목</td>
			<td><input type="text" name="title" value="${qdto.getTitle() }" required="required" class="form-control-plaintext p-2"></td>
		</tr>
		<tr>
			<td>본문 내용</td>
			<td><textarea name="content" class="form-control" required="required" rows="10">${qdto.getContent() }</textarea>
			
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
			<%if(id!=null && id.equals(qdto.getId())){ %>
			<input type="submit" value="등록하기" class="btn btn-success">
			<input type="reset" value="초기화" class="btn btn-warning">
			<%} %>
			<input type="button" value="목록으로" onclick=" location.href='./QnaList.bo'; " class="btn btn-secondary">
		</tr>
	</table>
	</form>
</fieldset>
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div> 
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
</body>
</html>