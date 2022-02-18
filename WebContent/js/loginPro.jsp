<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
	request.setCharacterEncoding("utf-8");
	String id=request.getParameter("id");
	String pw=request.getParameter("pw");
	String email=request.getParameter("email");
	String address=request.getParameter("address");
	String postcode=request.getParameter("postcode");
	String detailAddress=request.getParameter("detailAddress");%>
	id: <%=id %><br>
	pw: <%=pw %><br>
	email: <%=email %><br>
	postcode: <%=postcode %><br>
	address : <%=address %><br>
	detailAddress : <%=detailAddress %><br>
	
</body>
</html>