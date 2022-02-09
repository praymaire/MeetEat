<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>banManagePopup.jsp</h1>
	
	<%
		String reported_user = request.getParameter("reported_user");
		int reported_count = Integer.parseInt(request.getParameter("reported_count"));
	%>
	
	<h2>정지관리</h2>
	<hr>
	유저아이디 <%=reported_user %> <br> <br>
	누적 신고횟수 <%=reported_count %> <br> <br>
	<form action="./AdminBanManageProAction.ad">
		<input type="hidden" name="reported_user" value="<%=reported_user %>">
		정지기간 <input type="date" name="date"> <br> <br>
		<hr>
		<input type="submit" value="정지"> 
	</form>
</body>
</html>