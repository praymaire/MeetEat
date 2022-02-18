<%@ include file="../Main/top.jsp" %>

<%@page import="com.bo.qna.db.QnaDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<title>QnA 글 본문 페이지</title>

<% 
	request.setCharacterEncoding("utf-8");
	QnaDTO qdto = (QnaDTO)request.getAttribute("qdto"); 
%>
<form class="container">
<table class="table table-hover">
  <tbody>
    <tr>
      <th scope="row" class="ps-4 text-start" ><h5>${qdto.title }</h5></th>
      <td class="text-end pe-4">조회수 ${qdto.readcount }</td>
    </tr>
    <tr class=" table-light">
      <td scope="row" class="ps-4 text-start">${qdto.id }</td>
      <td class="text-end pe-4">${qdto.reg_date }</td>
    </tr>
    <tr>
      <td scope="row" colspan="2" style="white-space: pre-wrap">
  	  <p>${qdto.content }</p>
	  </td>
    </tr>
    </tbody>
    </table>


 	<div  class="py-sm-2 float-sm-end">

	<%if(id!=null){ %>
	<%if(id.equals(qdto.getId())||id.equals("admin")){ %>
		<input type="button" value="수정" onclick="location.href='./QnaUpdate.bo?qno=<%=qdto.getQno()%>'" class="btn btn-warning">
		<input type="button" value="삭제" onclick="location.href='./QnaDelete.bo?qno=<%=qdto.getQno()%>'" class="btn btn-danger">	
	<%} %>	
	<%if(id.equals("admin")){ %>	
		<input type="button" value="답글" onclick="location.href='QnaReply.bo?qno=<%=qdto.getQno()%>&re_ref=<%=qdto.getRe_ref()%>&re_lev=<%=qdto.getRe_lev()%>&re_seq=<%=qdto.getRe_seq()%>';" class="btn btn-info">
	<%} %>
 	<%} %>
		<input type="button" value="목록" onclick=" location.href='./QnaList.bo'" class="btn btn-secondary">
		
	</div>
	</form>

 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div> 
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
</body>
</html>