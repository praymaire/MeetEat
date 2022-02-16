<%@page import="com.ad.admin.db.AdminDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>계정삭제 페이지</title>
</head>
<body>
	<h1>./admin/adminDelete.jsp</h1>
    <%
    String id = (String)request.getParameter("id");
    %>
    <div>
   		<form action="./AdminDeletePro.ad" name="delete" method="post">
        	<div>아이디</div>
        	<div>
        		<input type="text" name="id" value="<%=id%>" readonly="readonly">
        	</div>
        	<div>비밀번호</div>
        	<div>
        		<input type="password" name="pw" placeholder="비밀번호를 입력해주세요"> 
        	</div>
        	<div>
       			<input type="submit" value="계정삭제"> 
       		</div>
    	</form>
    </div>
</body>
</html>