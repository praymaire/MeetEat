<%@ include file="../Main/top.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	<div class=" row justify-content-center">
		<div class="card border-success mb-3" style="max-width: 30rem;">
		  <div class="card-header text-center">아이디 찾기</div>
			  <div class="card-body justify-content-center">
			    <p class="card-text text-center">입력하신 정보와 일치하는 아이디는 <mark class="fs-5">${id}</mark>입니다.</p>
			    	<div class="d-flex justify-content-center">
			     	<button type="button" class="btn btn-success me-1" onclick="location.href='./MemberLogin.me';">로그인하기</button>
				 	<button type="button" class="btn btn-warning ms-1" onclick="location.href='./MemberFindPwAction.me';">비밀번호 찾기</button>
			  		</div>
			  </div>
		</div>
	</div>
		
</body>
</html> 