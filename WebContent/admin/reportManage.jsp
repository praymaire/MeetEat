<%@ include file="../Main/top.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.me.member.db.MemberDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script src="JQuery/jquery-3.6.0.js"></script>
<script type="text/javascript">
	
	/* 새로고침시 값 유지 */
	function init() {
		localStorage.clear();
		location.href="./AdminReportManage.ad";
	}
		
	function saveOptionValue(){
		localStorage.setItem("option", $('#option').val());
	}
	
	function saveUserValue(){
		localStorage.setItem("userSelect", $('#userSelect').val());
	}
	
	function saveSortValue() {
		localStorage.setItem("sort", $('#sort').val());
	}
	
	function saveIdValue(e) {
		var val = e.value
		localStorage.setItem("id", val);
	}
	
	document.addEventListener('DOMContentLoaded', function(){
		
		if(localStorage.getItem("option") == null) {
			$("#option option:eq(0)").prop("selected", true);
		} else {
			$('#option').val(localStorage.getItem("option")).prop("selected", true);
		}
		
		if (localStorage.getItem("userSelect") == null) {
			$("#userSelect option:eq(0)").prop("selected", true);
		} else {
			$('#userSelect').val(localStorage.getItem("userSelect")).prop("selected", true);
		}
		
		if (localStorage.getItem("sort") == null) {
			$("#sort option:eq(0)").prop("selected", true);
		} else {
			$('#sort').val(localStorage.getItem("sort")).prop("selected", true);
		}
		$('#id').val(localStorage.getItem("id"));
	});
	
	document.addEventListener('onunload', function(){
		localStorage.clear();
	});
	
	/* 팝업창 */
	function reportContentPopup(num){
        window.open("./AdminReportPopup.ad?num=" + num,
        			"신고내용",
        			"width=500, height=600, top=10, left=10");
    }
	
	function banManagePopup(reported_user, reported_count) {
		window.open("./AdminBanManagePopup.ad?reported_user=" + reported_user + 
					"&reported_count=" + reported_count, 
					"정지관리",
					"width=300, height=300, top=50, left=50");
	}
	
    function banCancel(reported_user) {
        if (!confirm(reported_user + "를 정지해제 하시겠습니까?")) {
            alert("취소(아니오)를 누르셨습니다.");
        } else {
            location.href="./AdminBanCancelProAction.ad?reported_user=" + reported_user;
        }
    }
	
</script>

	<!-- 첫 진입시 초기값 받아오기 -->
	<c:if test="${requestScope.check != 1 }">
		<c:redirect url="./AdminReportSort.ad"/>
	</c:if>

	<div class="container">
	<ul class="nav nav-tabs h4">
	  <li class="nav-item">
	    <a class="nav-link" href="./MemberList.ad">회원정보</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link"  href="./AdminReportManage.ad">신고관리</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="#">Disabled</a>
	  </li>
	</ul>
	<br>
	<!-- 검색 -->

			<form action="./AdminReportSort.ad" method="GET" class="ms-4 d-flex">
				<div>
				<h6>아이디 검색 / <span class="text-muted">전체검색시 빈칸</span></h6>
					<select name="userSelect" id="userSelect" onchange=" saveUserValue(); " class="form-select-sm" >
						<option value="reported_user">신고대상</option>
						<option value="report_user">신고자</option>
					</select>
					<input type="text" name="id" id="id" placeholder="아이디 검색" onkeyup=" saveIdValue(this); "> 
					<input type="submit" value="검색">
				</div>
				<div class="offcanvas-header"></div>
				<div>
				<h6>정렬</h6>
					<select name="option" id="option" onchange=" saveOptionValue(); " class="form-select-sm" >
						<option value="report_date">신고날짜</option>
						<option value="reported_count">누적신고횟수</option>
						<option value="ban_date">정지날짜</option>
					</select>
					<select name="sort" id="sort" onchange=" saveSortValue(); " class="form-select-sm" >
						<option value="desc">내림차순</option>
						<option value="asc">오름차순</option>
					</select> 
					<input type="submit" value="정렬" class="me-5">
					<input type="button" value="초기화" onclick="init();" >
				</div>
			</form> 
<hr>

	<!-- 검색 -->
	<br>
	<!-- 신고리스트 -->
	<div>
		<table class="table table-hober">
			<tr class="table-success">
				<th>신고번호</th>
				<th>신고대상</th>
				<th>누적신고횟수</th>
				<th>신고내용</th>
				<th>신고자</th>
				<th>신고날짜</th>
				<th>정지날짜</th>
				<th>정지</th>
			</tr>
		<c:if test="${requestScope.cnt != 0 }">
			<c:forEach var="list" items="${requestScope.reportList }">
				<tr class="<c:if test="${!empty list.ban_date }">table-danger</c:if>">
					<td>${list.num }</td>
					<td>${list.reported_user }</td>
					<td>${list.reported_count }</td>
					<td><a href="./AdminReportPopup.ad" 
						   onclick="reportContentPopup(${list.num }); return false;">신고내용 보기</a></td>
					<td>${list.report_user }</td>
					<td>${list.report_date }</td>
					<td>${list.ban_date }</td>
					<td><input type="button" value="정지관리" 
							   onclick="banManagePopup('${list.reported_user}', ${list.reported_count });"> / 
				    <input type="button" 
				   		   value="정지해제" 
						   onclick=" banCancel('${list.reported_user }'); ">
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${requestScope.cnt == 0 }">
			<tr>
				<td> 내용이 없습니다! </td>
			</tr>
		</c:if>
		</table> <br>
	</div>
	<!-- 신고리스트 -->	

	<!-- 페이징 -->

	<div id="page_control">
	<ul class="pagination pagination-sm justify-content-center">
		<c:if test="${requestScope.cnt != 0 }">
			<c:if test="${requestScope.startPage > requestScope.pageBlock }">
				<li class="page-item">
				<a href="./AdminReportSort.ad?pageNum=${requestScope.startPage - requestScope.pageBlock }
											&option=${requestScope.option }
											&userSelect=${requestScope.userSelect }
											&id=${requestScope.id }
											&sort=${requestScope.sort }" class="page-link">&laquo;</a></li>
			</c:if>

			<c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }" step="1">
				<li class="page-item">
				<a href="./AdminReportSort.ad?pageNum=${i }
											&option=${requestScope.option }
											&userSelect=${requestScope.userSelect }
											&id=${requestScope.id }
											&sort=${requestScope.sort }" class="page-link">${i }</a></li>
			</c:forEach>
			<c:if test="${requestScope.endPage < requestScope.pageCount }">
				<li class="page-item">
				<a href="./AdminReportSort.ad?pageNum=${ requestScope.startPage +  requestScope.pageBlock}
											&option=${requestScope.option }
											&userSelect=${requestScope.userSelect }
											&id=${requestScope.id }
											&sort=${requestScope.sort }" class="page-link">&raquo;</a></li>
			</c:if>
		</c:if>
		</ul>
	</div>
	</div>
	<!-- 페이징 -->
</head>
<div class="offcanvas-header"></div><div class="offcanvas-header"></div>
</body>
</html>