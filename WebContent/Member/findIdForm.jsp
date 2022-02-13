<%@ include file="../Main/top.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

	<form action="./MemberFindIdProAction.me" method="post" name="find" class="container">
	  <div class="row justify-content-center">
		<fieldset class="col-lg-6 text-center">	
		<legend>아이디 찾기</legend>
		<h5 class="text-danger">아이디는 가입시 입력하신 이메일을 통해 찾을 수 있습니다.</h5>
	 		 <!-- 아이디 -->
		<div class="form-group mb-2 fs-4">
			<label for="email" class="col-sm-2 col-form-label">이메일</label>
			<input type="email" name="email" id="email" placeholder="가입 시 기입한 이메일주소를 입력하세요" required autofocus class="col-9 p-2 fs-6">  
		</div>

		<!-- 제출버튼 -->
		<div class="float-sm-center">
			<input type="submit" id="findBtn" value="아이디찾기" class="btn btn-info mb-2">
			<input type="button" onclick="history.go(-1);" value="로그인하기" class="btn btn-info mb-2">
    	</div>
       </fieldset>
	  </div>
	</form>
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div> 
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
</body>
</html> 