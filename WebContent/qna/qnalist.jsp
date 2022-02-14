<%@page import="com.bo.qna.db.QnaDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
	function getContextPath() {

		var hostIndex = location.href.indexOf( location.host ) + location.host.length;

		return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );

	}
</script>
<title>QnA 게시판</title>
</head>
<body>

<%
String id = (String) session.getAttribute("id");

QnaDTO qdto = new QnaDTO();
%>

  <table border="1">
    <tr>
       <th>글번호</th>
       <th>제목</th>
       <th>작성자</th>
       <th>작성날짜</th>
       <th>조회수</th>
     </tr>
     
<%--      <%
        ArrayList qnaList 
          = (ArrayList)request.getAttribute("qnaList");
     %> --%>
     <c:if test="${requestScope.cnt != 0 }">
     <c:forEach var="qdto" items="${qnaList }">
         <tr>
	       <td>${qdto.qno }</td>
	       <td>
	       	<c:if test="${(qdto.re_lev)>0}">	       		
				<img src="level.gif" height="10" width="${(qdto.re_lev)*10}">
				<img src="${pageContext.request.contextPath}/qna/re.gif">
	 		</c:if> 
	       <a href="./QnaContent.bo?qno=${qdto.qno}&pageNum=${pageNum}">${qdto.title }</a></td>
	       <td>${qdto.nickname }</td>
	       <td><fmt:formatDate value="${qdto.reg_date }"/></td>
	       <td>${qdto.readcount }</td>
	     </tr>
     </c:forEach>   
  	</c:if>
  	<c:if test="${requestScope.cnt == 0 }">
			<tr>
				<td> 작성 글이 없습니다. </td>
			</tr>
	</c:if>
  </table>
  
  <!-- 페이징  처리 -->
	<div id="page_control">
		<c:if test="${requestScope.cnt != 0 }">
			<c:if test="${requestScope.startPage > requestScope.pageBlock }">
				<a href="./QnaList.bo?pageNum=${requestScope.startPage - requestScope.pageBlock }">이전</a>
			</c:if>
			<c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }" step="1">
				<a href="./QnaList.bo?pageNum=${i }">${i }</a>
			</c:forEach>
			<c:if test="${requestScope.endPage < requestScope.pageCount }">
				<a href="./QnaList.bo?pageNum=${ requestScope.startPage +  requestScope.pageBlock}">다음</a>
			</c:if>
		</c:if>
	</div>
  <!-- 페이징  처리 -->
  
  

<hr>

<input type="button" value="글쓰러가기" onclick="location.href='./QnaWrite.bo'">
<input type="button" value="메인페이지" onclick="location.href='./Main.me'">

</body>
</html>