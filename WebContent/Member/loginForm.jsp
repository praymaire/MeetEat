<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

 <h1>./Member/loginForm.jsp</h1>
 
 <div>
  	<div> 로그인 </div>
  	<form action="./MemberLoginAction.me" method="post" name="login" onclick="return checkValue()">
   		<div>
   			<label>아이디</label>
    		<input type="text" name="id" placeholder="ID를 입력하세요" >
   		</div>
   		<div>
   			<label>비밀번호</label>
    		<input type="password" name="pw" placeholder="비밀번호를 입력하세요">
    	</div>
    	<div><a href="./MemberFindIdAction.me">아이디 찾으러 가기</a></div>
    	<div><a href="./MemberFindPwAction.me">비밀번호 찾으러 가기</a></div>
    	<div>
    		<input type="submit" value="로그인">
    	</div>
    	<div>회원이 아니신가요?<a href="./MemberJoin.me">회원가입하러 가기</a></div>
    	<div><a href="./Main.me">홈페이지로 이동</a></div>										
  	</form> 
 </div>
 
 
 
 
 
 
 

</body>
</html>