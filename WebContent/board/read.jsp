<%@ include file="../Main/top.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src= "JQuery/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

	function reportPopup(writer, id){
		
		if(id == null) {
			alert('로그인 하세요');
			location.href="./Main.me";
			return false;
		}
		
	    window.open("./MemberReport.me?writer="+writer+"&id="+id ,
	    			"신고",
	    			"width=500, height=600, top=10, left=10");
	}
	
	$(document).ready(function(){
		
		var reported_count = '${requestScope.memberInfo.reported_count }';
		
		console.log("신고횟수: " + reported_count);
		
		if(reported_count == null) {
			return false;
		}
		
		if(reported_count >= 3) {
			console.log(reported_count)
			$('#warnMessage').html('최근 3회이상 신고이력이 있는 유저입니다 주의하세요 !')
			$('#warnMessage').attr('color', 'red');
		}
	});
	
	
</script>
       
    <div class="container col-lg-6">   
			<div class="list-group">
		
			<c:set var="list" value="${requestScope.boardList }"/>
			<c:set var="member" value="${requestScope.memberInfo }"/>
			
			
			
			<!-- 글 제목 -->
			  <a href="#" class="list-group-item list-group-item-action flex-column align-items-start active">
			    <div class="d-flex w-100 justify-content-between my-sm-3">
			      <h2 class="mb-1 ms-sm-4 ">${list[0].when_name }/${list[0].where_name}/${list[0].food_category }</h2>
			    </div>
			  </a>
			  
			  
			  <!-- 글 본문 -->
			  <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
			      <div class="text-end"><small class="text-muted "> ${list[0].bno }번글  |  ${list[0].id }  |  <fmt:formatDate value="${list[0].write_time }" pattern=" HH:mm yy/MM/dd " /></small> <br>
			       	<div id="warnMessage" class="text-danger"></div>
			       </div>   
			      <p class="mb-1 mt-3">${list[0].content }</p>
			  </a>
			</div>
			<div class="py-sm-2 float-sm-end">
			
			
			<c:if test="${sessionScope.id == list[0].id || sessionScope.id == 'admin'}">
				<button type="button" class="btn btn-warning btn-sm" onclick="location.href='modify.mb?bno=${ list[0].bno }'">수정</button>
				<button type="button" class="btn btn-danger btn-sm" onclick="location.href='delete.mb?bno=${ list[0].bno }'">삭제</button>
			</c:if>
				<button type="button" class="btn btn-success btn-sm" onclick="location.href='./list.mb'">목록</button>
				<button type="button" class="btn btn-outline-danger btn-sm" onclick="reportPopup('${list[0].id }', '${sessionScope.id }')">신고</button>
				</div>

</div>


<div class="offcanvas-header">　</div>
<div class="offcanvas-header">　</div>
</body>
</html>