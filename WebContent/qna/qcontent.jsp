<%@ include file="../Main/top.jsp" %>

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
	request.setCharacterEncoding("utf-8");
	String pageNum = request.getParameter("pageNum");
	QnaDTO qdto = (QnaDTO)request.getAttribute("qdto");
%>

		<script type="text/javascript">
		function delcheck() {
			var value = confirm("게시글을 삭제하시겠습니까?");
			if(value){
			   location.href="./QnaDelete.bo?qno=${qdto.qno}";
			}else{
			    history.back();
			}
		}
		
		function delcheck2() {
			var value = confirm("답글을 삭제하시겠습니까?");
			if(value){
			   location.href="./QnaReDelete.bo?qno=${qdto.qno}&re_seq=${qdto.re_seq}";
			}else{
			    history.back();
			}
		}
		
		</script>

<form class="container">
<table class="table table-hover">
    <tbody>
	<tr>
		<td colspan="2"> 작성자  </td>
		<td scope="row" colspan="3"><%=qdto.getNickname()%></td>
	</tr>
	
	<tr>
		<td colspan="2"> 글 제목  </td>
		<td scope="row" colspan="3"><%=qdto.getTitle()%> </td>
	</tr>
	
	<tr>
		<td colspan="2"> 글 내용  </td>
		<td scope="row" colspan="2" style="white-space: pre-wrap">
		   <p><%=qdto.getContent() %></p>
		</td>
	</tr>
</tbody>
</table>

<div class="py-sm-2 float-sm-end">
<%if(id!=null){ %>
	<%if(id.equals(qdto.getId())||id.equals("admin")){ %>
		<input type="button" value="수정" onclick="location.href='./QnaUpdate.bo?qno=<%=qdto.getQno()%>&pageNum=<%=pageNum%>'" class="btn btn-warning">
		<%if(qdto.getRe_seq()==0){%>
		<input type="button" value="삭제" onclick="delcheck()" class="btn btn-danger">
		<%} %>
	<%} %>	
	<%if(id.equals("admin")){ %>	
		<input type="button" value="답글" onclick="location.href='./QnaReply.bo?qno=<%=qdto.getQno()%>&pageNum=<%=pageNum%>&re_ref=<%=qdto.getRe_ref()%>&re_lev=<%=qdto.getRe_lev()%>&re_seq=<%=qdto.getRe_seq()%>';" class="btn btn-info">
		<%if(qdto.getRe_seq()>0){%>
		<input type="button" value="삭제" onclick="delcheck2()" class="btn btn-danger">
		<%} %>
	<%} %>
<%} %>
	<input type="button" value="목록" onclick=" location.href='./QnaList.bo?&pageNum=<%=pageNum%>'" class="btn btn-secondary" >
	
</div>

</form>

 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div> 
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>

</body>
</html>