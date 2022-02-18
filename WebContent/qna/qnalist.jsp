<%@ include file="../Main/top.jsp" %>

<%@page import="com.bo.qna.db.QnaDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 


<script type="text/javascript">
	function getContextPath() {
		var hostIndex = location.href.indexOf( location.host ) + location.host.length;
		return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
	}
</script>
<%

	QnaDTO qdto = new QnaDTO();
%>
<div class="container">
<table class="table table-hover">
  <thead>
    <tr class="table-dark">
      <th scope="col" class="col-1">No</th>
      <th scope="col" >제목</th>
      <th scope="col" class="col-2">작성자</th>
      <th scope="col" class="col-2">날짜</th>
      <th scope="col" class="col-1">조회수</th>
    </tr>
  </thead>
  <tbody>
   <tr class="table-light">   
    <c:if test="${requestScope.cnt != 0 }">
     <c:forEach var="qdto" items="${qnaList }">
         <tr>
         <!-- 글번호  -->
	       <th scope="row">${qdto.qno }</th>
	     <!-- 제목 -->
	       <td class="text-start">
	       	<c:if test="${qdto.re_lev>0}">
				<img src="${pageContext.request.contextPath}/qna/level.gif" height="10" width="${qdto.re_lev*10 }">
				<img src="${pageContext.request.contextPath}/qna/re.gif">
	 		</c:if>
	       <a href="./QnaContent.bo?qno=${qdto.qno}&pageNum=${pageNum}" class="text-decoration-none">${qdto.title }</a></td>
	       <td>${qdto.nickname }</td>
	       <td>
		       <fmt:formatDate value="<%=new java.util.Date() %>" pattern="yy.MM.dd" var="today" />
		       <fmt:formatDate value="${qdto.reg_date }" pattern="yy.MM.dd" var="regdate"/>
		       <c:choose>
			     <c:when test="${regdate eq today }"> 
			       <fmt:formatDate value="${qdto.reg_date }" pattern="HH:mm"/></c:when> 
		       	 <c:otherwise>${regdate }</c:otherwise> 
		       </c:choose>
	       </td>
	       <td>${qdto.readcount }</td>
	     </tr>
     </c:forEach>   
  	</c:if>
  	<c:if test="${requestScope.cnt == 0 }">
			<tr>
				<td> 작성 글이 없습니다. </td>
			</tr>
	</c:if>
  </tbody>
  </table>
  <!-- 페이징  처리 -->
	<div id="page_control">
	<ul class="pagination pagination-sm justify-content-center">
		<c:if test="${requestScope.cnt != 0 }">
			<c:if test="${requestScope.startPage > requestScope.pageBlock }">
			  <li class="page-item">
				<a href="./QnaList.bo?pageNum=${requestScope.startPage - requestScope.pageBlock }" class="page-link">&laquo;</a></li>
			</c:if>
			<c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }" step="1">
			  <li class="page-item">
				<a href="./QnaList.bo?pageNum=${i }" class="page-link">${i }</a></li>
			</c:forEach>
			<c:if test="${requestScope.endPage < requestScope.pageCount }">
			  <li class="page-item">
				<a href="./QnaList.bo?pageNum=${ requestScope.startPage +  requestScope.pageBlock}" class="page-link">&raquo;</a></li>
			</c:if>
		</c:if>
	</div>
  <!-- 페이징  처리 -->

<input type="button" value="글쓰기" onclick="location.href='./QnaWrite.bo'" class="btn btn-warning">
<input type="button" value="메인으로" onclick="location.href='./Main.me'" class="btn btn-secondary">
</div>
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div> 
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
</body>
</html>