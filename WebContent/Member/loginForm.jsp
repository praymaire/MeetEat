<%@ include file="../Main/top.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>meeteat!</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/bootstrap.css" rel="stylesheet" />
<script src= "JQuery/jquery-3.6.0.min.js"></script>
<style type="text/css">
	#loginCheckMessage {
		position: relative;
    	left: 280px;
	}
</style>
</head>
<body>


		<form action="./MemberLoginAction.me" method="post" id="login" class="container">
		  <div class="row justify-content-center">
			  <fieldset class="col-lg-6">			    
			    <!-- 아이디 -->
			    <div class="form-group mb-2 fs-4">
			      <label for="id" class="col-5 col-form-label text-center">아이디</label>
			     	<input type="text" class="col-6 ps-3" name="inputId" id="inputId">  
			    </div>
			    
			 	<!-- 비밀번호 -->
			    <div class="form-group mb-2 fs-4">
			      <label for="pw" class="col-5 col-form-label text-center">비밀번호</label>
			      <input type="password" class="col-6 ps-3" name="inputPw" id="inputPw"> <br>
			      <font size="4" id="loginCheckMessage"></font>
			    </div>
				
				<!-- 제출버튼 -->
				<div class="d-flex justify-content-center">
					<button class="btn btn-lg btn-info mb-2 px-5" type="button" onclick="loginCheck();">로그인</button>
					<input type="hidden" id="buttonClickCheck">
				</div>
				<div class="d-flex justify-content-center ">
					<a href='./MemberFindIdAction.me' class="nav-link me-2">아이디찾기</a>
					<a href='./MemberFindPwAction.me' class="nav-link me-2">비밀번호찾기</a>
					<a href='./MemberJoin.me' class="nav-link">회원가입</a>
				</div>
				
	
			  </fieldset>
			
		  </div>
		  </form>
	    <script>
	  
	        function loginCheck() {
	        	
	            var inputId = $('#inputId').val();
	            var inputPw = $('#inputPw').val();
	            var buttonClickCheck = $('#buttonClickCheck').val();
				
	            if(inputId == "") {
	            	$('#loginCheckMessage').html('아이디를 입력하세요');
	            	$('#loginCheckMessage').attr('color', 'red');
	            	return false;
	            }
	            
	            if(inputPw == "") {
	            	$('#loginCheckMessage').html('비밀번호를 입력하세요');
	            	$('#loginCheckMessage').attr('color', 'red');
	            	return false;
	            }
	            
	            $.ajax({
	                url: "./MemberLoginCheckAction.me",
	                type: "POST",
	                data: {inputId: inputId,inputPw: inputPw},
	                dataType: 'JSON',
	                success: function(result){
	                	console.log(result);
	                    if(result == 0 || result == -1){
	                        $("#loginCheckMessage").html('아이디 또는 비밀번호가 존재하지 않습니다.');
	                        $("#loginCheckMessage").attr('color', 'red');
	                        $("#buttonClickCheck").val("1");
	                    } else if (result == -2) {
	                    	$("#loginCheckMessage").html('정지된 아이디입니다.');
	                        $("#loginCheckMessage").attr('color', 'red');
	                        $("#buttonClickCheck").val("1");
	                    } else {
	                    	$('#login').submit();
	                    }
	                },
	                error: function(request, error){
	                    alert("오류!");
	                    // Ajax 오류 발생시 원인 확인 창
	                    alert("code:"+request.status+ "\n" + "message: " + request.responseText + "\n" + "error: " + error);
	                }
	                    
	            });
	        }
    </script>	

</body>
</html>