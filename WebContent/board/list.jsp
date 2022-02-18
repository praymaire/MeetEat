<%@page import="java.util.ArrayList"%>
<%@ include file="../Main/top.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="javafx.scene.control.Alert"%>
<%@page import="com.mb.board.db.BoardDAO"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%--    
   <%

	//세션체크 console 
	if(latitude == null){
		System.out.println("안녕하세요 AfterMain 입니다. 좌표를 가져오지 못했습니다. 10987654321");
	  } 	
	BoardDAO bdao =new BoardDAO();
	
    int cnt=bdao.getBoardCount();
	

%>
 <%
        ArrayList geoList = (ArrayList)request.getAttribute("geoList");
     %>
  
     <c:forEach var="gdto" items="${geoList }">
         <tr>   
	       <td>${gdto.id }</td>
	       <td>${gdto.when_name }</td>
	       <td>${gdto.where_name }</td>       
	       <td>${gdto.content }</td>
	       <td>${gdto.upload_image }</td>
	       
	 
	     </tr>
     </c:forEach> --%>
    
   <c:if test="${requestScope.check != 1 }">
		<c:redirect url="./BoardSearch.mb"/>
	 </c:if>

	<div class="container">
		<h2> 전체 게시글</h2><br>
		<table class="table table-hover ">
		  <thead>
		    <tr class="table-info">
		      <th>글번호</th>
		   	  <th>제목</th>
		   	  <th>글쓴이</th>
		   	  <th>작성시간</th>
		    </tr>
		  </thead>
		  <tbody>
			<c:forEach var="list" items="${requestScope.boardList}" varStatus="i">
			    <tr>
			      <td>${ list.bno }</td>
			   	  <td><a href="./read.mb?bno=${list.bno }" class="text-decoration-none">${ list.when_name }/${ list.where_name }/${ list.food_category }</a></td>
				  <td>${ list.id }</td>
			   	  <td>
				   <fmt:formatDate value="<%=new java.util.Date() %>" pattern="yy.MM.dd" var="today" />
			       <fmt:formatDate value="${ list.write_time }" pattern="yy.MM.dd" var="regdate"/>
			       <c:choose>
				     <c:when test="${regdate eq today }"> 
				       <fmt:formatDate value="${ list.write_time }" pattern="HH:mm"/></c:when> 
			       	 <c:otherwise>${regdate }</c:otherwise> 
			       </c:choose>
			    </tr>
			 </c:forEach>  
		  </tbody>
		</table>
			
		<form action="./BoardSearch.mb" method="GET" class="float-center">
				<select name="col" id="col" onchange=" " class="form-select-sm text-center" >
					<option value="id">아이디</option>
					<option value="when_name">언제</option>
					<option value="where_name">어디서</option>
					<option value="food_category">음식종류</option>
				</select>
				<input type="text" name="value" id="value" placeholder="검색어를 입력하세요" onkeyup=" " class="mx-1 ps-2"> 
				<input type="submit" value="검색" class="btn btn-sm btn-warning" id="btn">
				<input type="button" value="글쓰기" id="btn" class="btn btn-success btn-sm float-end"  
						onclick=<c:choose><c:when test="${id!=null}">"location.href='./write.mb'"</c:when>
								<c:otherwise>"alert('회원만 이용가능합니다')"</c:otherwise></c:choose>>
		</form>
		<div class="clearfix"></div>	

			<!--  본문    끝     -->
		

		<div id="page_control">
		<ul class="pagination pagination-sm justify-content-center">
			<c:if test="${requestScope.cnt != 0 }">
				<c:if test="${requestScope.startPage > requestScope.pageBlock }">
					<li class="page-item">
					<a href="./BoardSearch.mb?pageNum=${requestScope.startPage - requestScope.pageBlock }
												&col=${requestScope.col }
												&value=${requestScope.value }" class="page-link">&laquo;</a></li>
				</c:if>
	
				<c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }" step="1">
					<li class="page-item">
					<a href="./BoardSearch.mb?pageNum=${i }
												&col=${requestScope.col }
												&value=${requestScope.value }" class="page-link">${i }</a></li>
				</c:forEach>
				<c:if test="${requestScope.endPage < requestScope.pageCount }">
					<li class="page-item">
					<a href="./BoardSearch.mb?pageNum=${ requestScope.startPage +  requestScope.pageBlock}
												&col=${requestScope.col }
												&value=${requestScope.value }" class="page-link">&raquo;</a></li>
				</c:if>
			</c:if>
			</ul>
		</div>
		</div>

			
</body>
</html>