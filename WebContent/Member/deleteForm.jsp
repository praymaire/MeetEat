<%@ include file="../Main/top.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%   
    if(id == null) {
    	response.sendRedirect("./Memberlogin.me");
    }
 %>

   		<form action="./MemberDeleteAction.me" name="delete" method="post" class="container">
   		  <div class="row">
			<div class="col-4"></div>
			<fieldset class="col-7">	
   		 <!-- 아이디 -->
			<div class="form-group mb-2 fs-4">
				<label for="id" class="col-sm-2 col-form-label">아이디</label>
				<input type="text" name="id" value="<%=id%>" disabled="disabled" class="col-sm-6 p-2">  
			</div>
			    
		 	<!-- 비밀번호 -->
		    <div class="form-group mb-2 fs-4">
				<label for="pw" class="col-sm-2 col-form-label">비밀번호</label>
				<input type="password" class="col-sm-6 p-2" id="pw" name="pw">
		    </div>
			
			<!-- 제출버튼 -->
			<div class="d-grid col-8">
		  		<button class="btn btn-lg btn-info mb-2" type="submit">탈퇴하기</button>
      		</div>
      		</fieldset>
      	  </div>
    	</form>
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div> 
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
</body>
</html>