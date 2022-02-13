<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.css" rel="stylesheet" />
<title>Insert title here</title>

</head>
<body>
	<%
		String reported_user = request.getParameter("reported_user");
		int reported_count = Integer.parseInt(request.getParameter("reported_count"));
	%>
	<div class="offcanvas-header"></div>
	<form action="./AdminBanManageProAction.ad">
		<input type="hidden" name="reported_user" value="<%=reported_user %>">
		<table class="table table-hover">
			<tr class="table-danger"><th colspan="2"><h2 class="text-center">회원정지관리</h2></th></tr>
			<tr><td>유저아이디</td><td> <%=reported_user %></td></tr>
			<tr><td>누적 신고횟수</td><td> <%=reported_count %></td></tr>
			<tr><td>정지기간</td><td><input type="date" name="date"></td></tr>
		</table>
		<div class="justify-content-center d-flex"><input type="submit" value="정지" class="btn btn-danger"></div>

	</form>
</body>
</html>