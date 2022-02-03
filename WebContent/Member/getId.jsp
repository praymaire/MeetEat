<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>./Member/getId.jsp</h1>
	
	<div>
		<div>아이디 찾기</div>
		<div>입력하신 정보와 일치하는 아이디는 ${ id } 입니다.</div>
		<div>
			<input type="button" onclick="location.href='./MemberLogin.me';" value="로그인하러 가기">
			<div><a href="./MemberFindPwAction.me">비밀번호 찾으러 가기</a></div>
		</div>
	</div>

</body>
</html>