<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
<% 	
	String writer = request.getParameter("writer");
	String id = request.getParameter("id");
%>
<body style="width:500px">

	<form action="./MemberReportAction.me" class="container mt-4" onsubmit="return reportCheck();" style="width:450px" >	
	<fieldset>
	<!-- 게시글 신고, 채팅에서 신고 -->
	<legend class="legend"> 신고하기 </legend> 
	
	<div class="form-group row">
		<label class="form-label">신고할 유저</label>
   		<input type="text" name="reported_user" readonly="" class=" py-2 ps-3 form-control-plaintext"
   				value=<%=writer%> >  
	</div>


    <div class="form-group row mb-4">
		<label for="report_content" class="col-sm-2 col-form-label">신고내용</label>
		<textarea rows="10" cols="10" name="report_content" id="report_content" class="p-3"></textarea> 
    </div>
	<input type="hidden" name="report_user" value="<%=id %>">
	<input type="hidden" name="reported_user" value="<%=writer %>">
	<!-- 제출버튼 -->
	<div class="d-grid">
		<button class="btn btn-lg btn-danger mb-2" type="submit">신고하기</button>
	</div>
	
	</fieldset>
	</form>
	

</body>
</html>