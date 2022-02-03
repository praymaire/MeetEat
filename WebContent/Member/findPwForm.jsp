<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src= "./JQuery/jquery-3.6.0.slim.js"></script>
<script type="text/javascript">
/* 	$(function(){
		$("#findBtn").click(function(){
			$.ajax({
				url:"./MemberFindPwProAction.me",
				type:"POST",
				data:{
					id : $("#id").val(),
					email : $("#email").val()
				},
				success: function(result) {
					alert(result);
				},
			})
		});
	}) */
</script>
<title>Insert title here</title>
</head>
<body>
	<h1>./Member/findPwForm.jsp</h1>
	
	<div>
		<div>비밀번호 찾기</div>
 		<form action="./MemberFindPwProAction.me" method="post" name="find">	
 				<div>
				<label>아이디</label>
				<input type="text" name="id" id="id" placeholder="회원가입한 아이디를  입력하세요" required="required">
			</div>
			<div>
				<label>이메일</label>
				<input type="text" name="email" id="email" placeholder="회원가입한 이메일주소를 입력하세요" required="required">
			</div>
			<div>
				<input type="submit" id="findBtn" value="찾기">
				<input type="button" onclick="history.go(-1);" value="로그인하러 가기">
			</div>
		</form> 
	</div>
</body>
</html>