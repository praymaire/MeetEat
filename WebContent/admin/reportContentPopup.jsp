<%@page import="com.ad.admin.db.ReportDAO"%>
<%@page import="com.me.member.db.ReportDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.me.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>reportContentPopup.jsp</h1>
	
	<%
		int num = Integer.parseInt(request.getParameter("num"));
		
		ReportDAO rdao = new ReportDAO();
		ArrayList reportList = rdao.getReportList(num);
	%>
	
	<h1>신고내용</h1>
	<hr>
	<c:forEach var="list" items="<%=reportList %>">
		신고번호 ${list.num } <br>
		신고대상 ${list.reported_user } | 신고날짜 ${list.report_date }
		<hr>
		신고내용 <br>
		${list.report_content }
		
		<hr>
		신고자 ${list.report_user }
	</c:forEach>


</body>
</html>