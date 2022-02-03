<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src= "./JQuery/jquery-3.6.0.slim.js"></script>
<script type="text/javascript">

	//pw 일치 검사
	function pwCheckFunction() {
		var pw = $('#pw').val();
		var checkPw = $('#checkPw').val();
		if(pw != checkPw) {
			$('#pwCheckMessage').html('비밀번호가 서로 일치하지 않습니다.');
		} else {
			$('#pwCheckMessage').html('비밀번호가 서로 일치합니다.');
		}
	}

	// 유효성 검사
	function checkValue() {
		var oldPw = $("#oldPw").val();
		var pw = $('#pw').val();
		var checkPw = $('#checkPw').val();
		if(oldPw == "") {
			alert('현재 비밀번호를 입력하세요');
			document.modify.oldPw.focus();
			return false;
		}
		if(pw == "") {
			alert('새 비밀번호를 입력하세요');
			document.modify.pw.focus();
			return false;
		}
		if(checkPw == "") {
			alert('새 비밀번호를 재입력하세요');
			document.modify.checkPw.focus();
			return false;
		}
	}

</script>
<title>Insert title here</title>
</head>
<body>

 <h1>./Member/changePw.jsp</h1>
 
 <%
     // 세션값 제어
     String id = (String)session.getAttribute("id");
   
     if(id == null){
    	 response.sendRedirect("./MemberLogin.me");
     }   
   
   %>

 <div>
  	<div> 비밀번호 변경 </div>
  	<form action="./MemberModifytempPwAction.me" method="post" name="modify" onclick="return checkValue()">
   		<div>
   			<label>현재 비밀번호</label>
    		<input type="password" name="oldPw" id="oldPw" value="${ sessionScope.pw }" readonly="readonly">
   		</div>
   		<div>
   			<label>새 비밀번호</label>
    		<input type="password" name="pw" id="pw" placeholder="새 비밀번호를 입력하세요" onkeyup="pwCheckFunction();">
    	</div>
   		<div>
   			<label>새 비밀번호 재확인</label>
    		<input type="password" name="checkPw" id="checkPw" placeholder="새 비밀번호를 재입력하세요" onkeyup="pwCheckFunction();">
    		<h5 id="pwCheckMessage"></h5>
    	</div>
    	<div>
    		<input type="submit" value="저장">
    	</div>
  	</form> 
    	<div><a href="./Main.me">홈페이지로 이동</a></div>										
 </div>
</body>
</html>