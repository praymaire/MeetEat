<%@page import="com.ad.admin.db.ReportDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.me.member.db.MemberDAO"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>
	<%
		int num = Integer.parseInt(request.getParameter("num"));
		
		ReportDAO rdao = new ReportDAO();
		ArrayList reportList = rdao.getReportList(num);
	%>
	<div class="offcanvas-header"></div>
	<table class="table table-hover">
			<tr class="table-danger"><th colspan="2"><h2 class="text-center">신고 내용</h2></th></tr>
	<c:forEach var="list" items="<%=reportList %>">
		<tr><td>신고번호</td><td> ${list.num }</td></tr>
		<tr><td>신고대상 </td><td>${list.reported_user } </td></tr>
		<tr><td>신고날짜</td><td> ${list.report_date }</td></tr>
		<tr><td>신고내용</td><td style="white-space: pre-wrap"> ${list.report_content }</td></tr>
		<tr><td>신고자 </td><td>${list.report_user }</td></tr>
	</c:forEach>
	</table>

</body>
</html> 