<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src= "./JQuery/jquery-3.6.0.slim.js"></script>
<script type="text/javascript">
	
	/* // window 로드 됐을 때, id가 findBtn인 것을 클릭하면 해당 함수 실행
	$(window).load(function(){
		$("#findBtn").click(function(){
			var email = $("#email");
			if(email.val()= ="") {
				alert('이메일을 입력하세요');
				return;
			}
		// 해당 값을 ./MemberFindIdProAction.me로 전달
		// 전달한 후 받은 익명의 function으로 controller로부터 받은 결과값(result 변수 값)을 alert창을 통해 출력
		$.post("./MemberFindIdProAction.me",
				{email : email.val()}, 
				function(data) {
					alert(eval(data).result);
				});
		});
	}); */

</script>
<title>Insert title here</title>
</head>
<body>

	<h1>./Member/findIdForm.jsp</h1>
	
	<div>
		<div>아이디 찾기</div>
		<div>아이디는 가입시 입력하신 이메일을 통해 찾을 수 있습니다.</div>
 		<form action="./MemberFindIdProAction.me" method="post" name="find">	
			<div>
				<label>이메일</label>
				<input type="email" name="email" id="email" placeholder="회원가입한 이메일주소를 입력하세요" required autofocus>
			</div>
			<div>
				<input type="submit" id="findBtn" value="찾기">
				<input type="button" onclick="history.go(-1);" value="로그인하러 가기">
			</div>
		</form> 
	</div>
</body>
</html>