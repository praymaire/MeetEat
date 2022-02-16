<%@ include file="../Main/top.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<title>QNA 글 작성 페이지</title>

<fieldset class="container">
<legend>QnA 등록</legend>
	<form action="./QnaAddAction.bo" method="post" name="fr">
	<input type="hidden" name="id" value="<%=id%>">
	 <table class="table table-hover">
		<tr>
			<td class="form-label">제목</td>
			<td><input type="text" name="title" required="required" class="form-control-plaintext p-2"></td>
		</tr>
		<tr>
			<td>본문 내용</td>
			<td><textarea name="content" class="form-control" required="required" rows="10"></textarea>
			
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
			<input type="submit" value="등록하기" class="btn btn-success">
			<input type="button" value="목록으로" onclick=" location.href='./QnaList.bo'; " class="btn btn-secondary">
		</tr>
	</table>
	</form>
</fieldset>
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div> 
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
</body>
</html>