<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        
        <title>meeteat!</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/bootstrap.css" rel="stylesheet" />
        <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script src= "./JQuery/jquery-3.6.0.slim.js"></script>

</head>
<script type="text/javascript">

	function reportCheck() {
		var content = document.getElementById("report_content").value;
		
		if(content == null || content.length <= 20) {
			alert('신고내용은 20자 이상 작성해주세요');
			return false;
		}
	}
	
</script>
<body>

<%
	String writer = request.getParameter("writer");
	String id = request.getParameter("id");
%>

<!-- Responsive navbar-->
<%-- <jsp:include page="../Main/top.jsp"></jsp:include> --%>
<div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
	<form action="./MemberReportAction.me" class="container" onsubmit="return reportCheck();">	
	 <div class="row">
		<div class="col-4"></div>
		<fieldset class="col-7" style="position: relative; right: 80px">	
			<!-- 게시글 신고, 채팅에서 신고 -->
			<legend class="legend"> 신고하기 </legend> 
			<hr>
			<div class="form-group row">
 				<label class="form-label">신고유저</label> 
			</div>
				     		<b>[<%=writer %>]</b> 

		    <div class="form-group row mb-4">
				<label for="report_content" class="col-sm-2 col-form-label">신고내용</label>
				<textarea rows="10" cols="10" name="report_content" id="report_content" class="ms-3"></textarea> 
		    </div>
			<input type="hidden" name="report_user" value="<%=id %>">
			<input type="hidden" name="reported_user" value="<%=writer %>">
			<!-- 제출버튼 -->
			<div class="d-grid col-8">
		  		<button class="btn btn-lg btn-info mb-2" type="submit">신고하기</button>
      		</div>
	
		</fieldset>
		</div>
	</form>
	

</body>
</html>