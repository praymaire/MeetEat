<%@page import="com.ad.admin.db.AdminDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.css" rel="stylesheet" />
<title>계정삭제 페이지</title>
</head>
<body>
    <%
    String id = (String)request.getParameter("id");
    %>
    <div >
   		<form action="./AdminDeletePro.ad" name="delete" method="post">
        	<table class="table table-hover">
        	<tr class="table-danger"><th colspan="2"><h2 class="text-center">계정삭제관리</h2></th></tr>
        	<tr><td>아이디</td><td> <input type="text" name="id" value="<%=id%>" readonly="readonly">  </td></tr>
			<tr><td>비밀번호</td><td> <input type="password" name="pw" placeholder="비밀번호를 입력해주세요">    </td></tr>

    	</table>
		<div class="justify-content-center d-flex"><input type="submit" value="계정삭제" class="btn btn-danger"></div>
    	
    	</form>
    </div>
</body>
</html>