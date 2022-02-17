<%@ include file="../Main/top.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	#pwCheckMessage {
		position: relative;
	    left: 270px;
	    }
</style>
<script src= "JQuery/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

	//pw 일치 검사
	function pwCheckFunction() {
		
		var pw = $('#pw').val();
		var checkPw = $('#checkPw').val();
		
		if(pw == checkPw && pw != "") {
			$('#pwCheckMessage').html('비밀번호가 서로 일치합니다.');
			$("#pwCheckMessage").attr('color', 'green');
		} else {
			$('#pwCheckMessage').html('비밀번호가 서로 일치하지 않습니다.');
			$("#pwCheckMessage").attr('color', 'red');
		}
		
	}
	
	// 유효성 검사
	function checkValue() {
		
		var oldPw = $("#oldPw").val();
		var pw = $('#pw').val();
		var checkPw = $('#checkPw').val();
		var passReg = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/;
		
		if(oldPw == "") {
			document.modify.oldPw.focus();
			$('#pwCheckMessage').html('현재 비밀번호를 입력하세요');
			$("#pwCheckMessage").attr('color', 'red');
			return false;
		}
		
		if(pw == "") {
			document.modify.pw.focus();
			$('#pwCheckMessage').html('새 비밀번호를 입력하세요');
			$("#pwCheckMessage").attr('color', 'red');
			return false;
		}
		
		if(!passReg.test(pw)) {
			$("#pwCheckMessage").html('비밀번호는 영문과 숫자를 포함한 8자이상이여야합니다');
        	$("#pwCheckMessage").attr('color', 'red');
        	return false;
		}
		
		if(checkPw == "") {
			document.modify.checkPw.focus();
			$('#pwCheckMessage').html('새 비밀번호를 입력하세요');
			$("#pwCheckMessage").attr('color', 'red');
			return false;
		}
		
		if(pw != checkPw) {
			$('#pwCheckMessage').html('비밀번호가 서로 일치하지 않습니다.');
			$("#pwCheckMessage").attr('color', 'red');
			return false;
		}
		
	}
	
</script>

		
 <%
     // 세션값 제어
     if(id == null){
    	 response.sendRedirect("./MemberLogin.me");
     }     
   %>

<form action="./MemberModifytempPwAction.me" method="post" name="modify" onsubmit="return checkValue()"  class="container">
  <div class="row justify-content-center ">
  <fieldset class="col-lg-6">		
	  <legend class="text-center">비밀번호 변경</legend>	    
	    <!-- 기존비밀번호 -->
	    <div class="form-group mb-2 fs-5">
	      <label for="oldPW" class="col-5 col-form-label text-center">기존 비밀번호</label>  
	     	<input type="password" name="oldPw" id="oldPw" value="${ sessionScope.pw }" readonly="readonly"  class="col-6 ps-3">
	    </div>
	    
	 	<!-- 신규비밀번호 -->
	    <div class="form-group mb-2 fs-5">
	      <label for="pw" class="col-5  col-form-label text-center">신규 비밀번호</label>
	      <input type="password" name="pw" id="pw" placeholder="새 비밀번호를 입력하세요" onkeyup="pwCheckFunction(); " class="col-6 ps-3" >
	    </div>
		
	 	<!-- 신규비밀번호확인 -->
	    <div class="form-group mb-2 fs-5">
	      <label for="checkPw" class="col-5  col-form-label text-center">신규 비밀번호 확인</label>
	   	  <input type="password" name="checkPw" id="checkPw" placeholder="새 비밀번호를 재입력하세요" onkeyup="pwCheckFunction();" class="col-6 ps-3">
	    </div>
	    
	    <font size="4" id="pwCheckMessage"></font> <br>
	    
		<div class="d-flex justify-content-center">
			<button class="btn btn-lg btn-info  me-1" type="submit">비밀번호 변경</button>
			<button class="btn btn-lg btn-secondary ms-1" type="button" onclick="location.href='./Main.me'">홈페이지로 이동</button>
		</div>
	  </fieldset>
	</div>
  </form>
   <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div> 
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
</body>
</html>