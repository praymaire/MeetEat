<%@page import="com.mb.board.db.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>meateat!</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="./css/bootstrap.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body> 

	<c:if test="${requestScope.check != 1 }">
		<c:redirect url="./BoardSearch.mb"/>
	</c:if>


	<!-- 상단 메뉴 -->
	 <jsp:include page="../Main/top.jsp"></jsp:include>
	<!-- 상단 메뉴 -->
	
	
	<div class="offcanvas-header"> <!-- 공백 -->　</div>
	<div class="contain">
		<div class="row">
		<div class="col-2"><br> <!-- 테이블 왼쪽 공백 --> </div>
			<div class="col-8">
			
			
			
			<!--  테스트 공간 -->
			
		
			<!--  테스트 공간 -->
			
			
			
			<!--  본문    시작     -->
			
				<h2> 주변 기반 게시글</h2><br>
				<table class="table table-hover">
				
					<tbody class="table-light">
				    <tr>
				      <td>글번호</td>
				   	  <td>제목</td>
				   	  <td>글쓴이</td>
				   	  <td>작성시간</td>
				    </tr>
		
				<c:forEach var="list" items="${requestScope.boardList}" varStatus="i">
				    <tr>
				      <td>${ list.bno }</td>
				   	  <td><a href="./read.mb?bno=${list.bno }">${ list.when_name }/${ list.where_name }/${ list.food_category }</a></td>
				   	  <td>${ list.id }</td>
				   	  <td>${ list.write_time }</td>
				    </tr>
				 </c:forEach>
				    
				  </tbody>
				</table>
			
			<form action="./BoardSearch.mb" method="GET" class="ms-4 d-flex">
				
				검색 &nbsp;&nbsp;
					<select name="col" id="col" onchange=" " class="form-select-sm" >
						<option value="id">아이디</option>
						<option value="when_name">언제</option>
						<option value="where_name">어디서</option>
						<option value="food_category">음식종류</option>
					</select>
					<input type="text" name="value" id="value" placeholder="검색" onkeyup=" "> 
					<input type="submit" value="검색">
				
			</form>
			
			<input type="button" value="글쓰기" style="float: right;" onclick="location.href='./write.mb' ">
			
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
				<!-- 페이징 -->
			</div>
			</div>	
			
	
	</div>

</body>
</html>