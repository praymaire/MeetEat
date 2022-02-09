<%@page import="java.util.ArrayList"%>
<%@page import="com.me.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="JQuery/jquery-3.6.0.js"></script>
<script type="text/javascript">
	
	/* 새로고침시 값 유지 */
	function init() {
		localStorage.clear();
		location.href="./AdminReportManage.ad";
	}
		
	function saveOptionValue(){
		localStorage.setItem("option", $('#option').val());
		location.reload();
	}
	
	function saveUserValue(){
		localStorage.setItem("userSelect", $('#userSelect').val());
		location.reload();
	}
	
	function saveSortValue() {
		localStorage.setItem("sort", $('#sort').val());
		location.reload();
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
					"width=500, height=600, top=10, left=10");
	}
	
    function banCancel(reported_user) {
        if (!confirm(reported_user + "를 정지해제 하시겠습니까?")) {
            alert("취소(아니오)를 누르셨습니다.");
        } else {
            location.href="./AdminBanCancelProAction.ad?reported_user=" + reported_user;
        }
    }
	
</script>
<body>
	<!-- 첫 진입시 초기값 받아오기 -->
	<c:if test="${requestScope.check != 1 }">
		<c:redirect url="./AdminReportSort.ad"/>
	</c:if>

	<h1>WebContent/admin/reportManage.jsp</h1>
	
	<h1>신고관리</h1>
	<hr> <br> 
	
	<!-- 검색 -->
	<div>
		<h2>신고내역 검색</h2>
		<hr>
		<fieldset>
			<form action="./AdminReportSort.ad" method="GET">
				아이디 검색 <font color="gray">/ 전체검색시 빈칸</font> <br>
				<select name="userSelect" id="userSelect" onchange=" saveUserValue(); ">
					<option value="reported_user">신고대상</option>
					<option value="report_user">신고자</option>
				</select>
				<input type="text" name="id" id="id" placeholder="아이디 검색" onkeyup=" saveIdValue(this); "> 
				<input type="submit" value="검색"> <br> <br>
				
				정렬 <br>
				<select name="option" id="option" onchange=" saveOptionValue(); ">
					<option value="report_date">신고날짜</option>
					<option value="reported_count">누적신고횟수</option>
					<option value="ban_date">정지날짜</option>
				</select>
				
				<select name="sort" id="sort" onchange=" saveSortValue(); ">
					<option value="desc">내림차순</option>
					<option value="asc">오름차순</option>
				</select> 
				<input type="submit" value="정렬"> <br> <br>
				<input type="button" value="초기화" onclick=" init(); ">
			</form> 
		</fieldset>
	</div>
	<!-- 검색 -->
	<br>
	<!-- 신고리스트 -->
	<div>
		<table border="1">
			<tr>
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
				<tr>
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
	<hr>
	<!-- 페이징 -->
	<div id="page_control">
		<c:if test="${requestScope.cnt != 0 }">
			<c:if test="${requestScope.startPage > requestScope.pageBlock }">
				<a href="./AdminReportSort.ad?pageNum=${requestScope.startPage - requestScope.pageBlock }
											&option=${requestScope.option }
											&userSelect=${requestScope.userSelect }
											&id=${requestScope.id }
											&sort=${requestScope.sort }">이전</a>
			</c:if>
			<c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }" step="1">
				<a href="./AdminReportSort.ad?pageNum=${i }
											&option=${requestScope.option }
											&userSelect=${requestScope.userSelect }
											&id=${requestScope.id }
											&sort=${requestScope.sort }">${i }</a>
			</c:forEach>
			<c:if test="${requestScope.endPage < requestScope.pageCount }">
				<a href="./AdminReportSort.ad?pageNum=${ requestScope.startPage +  requestScope.pageBlock}
											&option=${requestScope.option }
											&userSelect=${requestScope.userSelect }
											&id=${requestScope.id }
											&sort=${requestScope.sort }">다음</a>
			</c:if>
		</c:if>
	</div>
	<!-- 페이징 -->
</head>
</body>
</html>