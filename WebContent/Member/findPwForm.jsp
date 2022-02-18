<%@ include file="../Main/top.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

	<form action="./MemberFindPwProAction.me" method="post" name="find" class="container">
	  <div class="row justify-content-center">
		<div class="offcanvas-header"></div>
		<fieldset class="col-lg-6 text-center">	
		<legend>비밀번호 찾기</legend>
		
	 		 <!-- 아이디 -->
		<div class="form-group mb-2 fs-4">
			<label for="id" class="col-sm-2 col-form-label">아이디</label>
			<input type="text" name="id" id="id" placeholder="아이디를  입력하세요" required="required" class="col-sm-6 p-2 fs-6">  
		</div>
		<div class="form-group mb-2 fs-4">
			<label for="email" class="col-sm-2 col-form-label">이메일</label>
			<input type="text" name="email" id="email" placeholder="가입한 정보의 이메일주소를 입력하세요" required="required" class="col-sm-6 p-2 fs-6">  
		</div>

		<!-- 제출버튼 -->
		<div class="float-sm-center">
			<input type="submit" id="findBtn" value="비밀번호찾기" class="btn btn-info mb-2">
			<input type="button" onclick="history.go(-1);" value="로그인하기" class="btn btn-info mb-2">
    	</div>
       </fieldset>
	  </div>
	</form>
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div> 
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
</body>
</html>