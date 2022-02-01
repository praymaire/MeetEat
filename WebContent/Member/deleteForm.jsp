<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>./Member/deleteForm.jsp</h1>
    <%
    // 사용자의 비밀번호 한번 더 입력받아 처리
    String id = (String)session.getAttribute("id");
    
    if(id == null) {
    	response.sendRedirect("./Memberlogin.me");
    }
    %>
    <div>
   		<form action="./MemberDeleteAction.me" name="delete" method="post">
        	<div>아이디</div>
        	<div>
        		<input type="text" name="id" value="<%=id%>" disabled="disabled">
        	</div>
        	<div>비밀번호</div>
        		<input type="password" name="pw" placeholder="비밀번호를 입력해주세요"> 
        	</div>
        	<div>
       			<input type="submit" value="탈퇴하기"> 
       		</div>
    	</form>
    </div>
</body>
</html>