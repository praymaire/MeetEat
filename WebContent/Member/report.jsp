<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>WebContent/Member/report.jsp</h1>
	
	<fieldset>
		<!-- 게시글 신고, 채팅에서 신고 -->
		<legend> 신고하기 </legend> 
			<form action="./MemberReportAction.me">
				<input type="hidden" name="report_user" value="${id }">
				신고할 유저 <br> <input type="text" name="reported_user"> <br> <br>
				신고내용 <br> <textarea rows="20" cols="20" name="report_content"></textarea> <br> <br>
				<input type="submit" value="제출">
			</form>
	</fieldset>

</body>
</html>