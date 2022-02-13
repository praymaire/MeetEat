<%@page import="com.me.member.db.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../Main/top.jsp" %>
<style type="text/css">

	.msg {
		position: relative;
    	left: 270px;
	}
	
</style>

 <%
     // 세션값 제어 
     if(id == null){
    	 response.sendRedirect("./MemberLogin.me");
     }
 	MemberDAO mdao = new MemberDAO();
 	String pw = mdao.findPw(id); 
%>

	<form action="./MemberModifyoldPwAction.me" method="post" name="modify" onsubmit="return check()" class="container">
	  <div class="row justify-content-center ">
	  <fieldset class="col-lg-6">		
		  <legend class="text-center">비밀번호 변경</legend>	    
		    <!-- 기존비밀번호 -->
		    <div class="form-group mb-2 fs-5">
		      <label for="oldPW" class="col-5 col-form-label text-center">현재 비밀번호</label>  
		     	<input type="password" name="oldPw" id="oldPw" placeholder="현재 비밀번호를 입력하세요" class="col-6 ps-3">
		    </div>
		    
		    <font class="msg" id="oldPwCheckMessage" size="4"></font>

		 	<!-- 신규비밀번호 -->
		    <div class="form-group mb-2 fs-5">
		      <label for="pw" class="col-5  col-form-label text-center">신규 비밀번호</label>
		      <input type="password" name="pw" id="pw" placeholder="새 비밀번호를 입력하세요" class="col-6 ps-3" >
		    </div>
		    
		    <font class="msg" id="pwCheckMessage" size="4"></font>
			
		 	<!-- 신규비밀번호확인 -->
		    <div class="form-group mb-2 fs-5">
		      <label for="checkPw" class="col-5  col-form-label text-center">신규 비밀번호 확인</label>
		   	  <input type="password" name="checkPw" id="checkPw" placeholder="새 비밀번호를 재입력하세요" class="col-6 ps-3">
		    </div>
		    
		    <font class="msg" id="checkPwCheckMessage" size="4"></font>
		    
			<div class="d-flex justify-content-center">
				<button class="btn btn-lg btn-info  me-1" type="submit">비밀번호 변경</button>
				<button class="btn btn-lg btn-secondary ms-1" type="button" onclick="location.href='./Main.me'">홈페이지로 이동</button>
			</div>
		  </fieldset>
		</div>
	  </form>
<script src= "JQuery/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		
		// 현재비밀번호 일치여부
		
		$('#oldPw').focusout(function(){
			var pw = "<%=pw %>"
			var oldPw = $('#oldPw').val();

	        if(pw != oldPw) {
	        	$("#oldPwCheckMessage").html('현재 비밀번호와 일치하지 않습니다');
            	$("#oldPwCheckMessage").attr('color', 'red');
            	return false;
	        } else {
	        	$("#oldPwCheckMessage").html('비밀번호가 일치합니다');
                $("#oldPwCheckMessage").attr('color', 'green');
	        }
		});
		
		
	});
	
	function check() {
		
		var oldPw = "<%=pw %>"
		var inputOldPw = $('#oldPw').val();
		var pw = $('#pw').val();
		var checkPw = $('#checkPw').val();
		var passReg = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/;
		
		if(inputOldPw == "") {
			$("#oldPwCheckMessage").html('현재 비밀번호를 입력해주세요');
        	$("#oldPwCheckMessage").attr('color', 'red');
        	return false;
		}
		
		if(inputOldPw != oldPw) {
			$("#oldPwCheckMessage").html('현재 비밀번호와 일치하지 않습니다');
        	$("#oldPwCheckMessage").attr('color', 'red');
        	return false;
		}
		
		if(pw == "") {
			$("#pwCheckMessage").html('새 비밀번호를 입력해주세요');
        	$("#pwCheckMessage").attr('color', 'red');
        	return false;
		} else {
			$("#pwCheckMessage").html('');
		}
		
		if(oldPw == pw) {
			$("#pwCheckMessage").html('현재 비밀번호와 동일한 비밀번호는 사용할 수 없습니다');
        	$("#pwCheckMessage").attr('color', 'red');
        	return false;
		} else {
			$("#pwCheckMessage").html('');
		}
		
		if(!passReg.test(pw)) {
			$("#pwCheckMessage").html('비밀번호는 영문과 숫자를 포함한 8자이상이여야합니다');
        	$("#pwCheckMessage").attr('color', 'red');
        	return false;
		} else {
			$("#pwCheckMessage").html('');
		}
		
		if(checkPw != pw) {
			$("#checkPwCheckMessage").html('새 비밀번호가 일치하지않습니다');
        	$("#checkPwCheckMessage").attr('color', 'red');
        	return false;
		} else {
			$("#checkPwCheckMessage").html('');
		}
	
	}
	
</script>
	  
 <div class="offcanvas-header"></div> 
 <div class="offcanvas-header"></div>
</body>
</html> 