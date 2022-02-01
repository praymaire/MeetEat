<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

</script>
<title>Insert title here</title>
</head>
<body>

 <h1>./Member/loginForm.jsp</h1>
 
 <div>
  	<div> 로그인 </div>
  	<form action="./MemberLoginAction.me" method="post" id="login" onclick="return checkValue()">
   		<div>
    		<input type="text" name="id" id="id" placeholder="ID를 입력하세요" ><br>
   		</div>
   		<div>
    		<input type="password" name="pw" id="pw"placeholder="비밀번호를 입력하세요"><br>
    	</div>
    	<div>
    		<input type="submit" value="로그인">   <br>
    		<input type="button" value="회원가입" onclick=" location.href='./MemberJoin.me'; ">
   		</div>										
  	</form> 
 </div>
 
 
 
 
 
 
 

</body>
</html>