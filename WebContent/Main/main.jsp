<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<body>

        <!-- Responsive navbar-->
               <jsp:include page="../Main/top.jsp"></jsp:include>
        	 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
  
  <%
	// 로그인한 id값을 들고다닌다
	String id = (String)session.getAttribute("id");%>


       <!-- Page content-->

        
        <div class="container">
        <h3 class="text-center"> 최신글</h3>
        </div>
        <div class="container">
         <div class="d-flex">
		     <div class="col-lg-3">
	            <div class="bs-component">
	              <div class="card mb-2 me-sm-3">
	                <svg xmlns="http://www.w3.org/2000/svg" class="d-block user-select-none" width="100%" height="200" aria-label="Placeholder: Image cap" focusable="false" role="img" preserveAspectRatio="xMidYMid slice" viewBox="0 0 318 180" style="font-size:1.125rem;text-anchor:middle">
	                  <rect width="100%" height="100%" fill="#868e96"></rect>
	                  <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text>
	                </svg>
	                <div class="card-body text-center">
	                  <h5 class="card-title ">서면/치킨드실분/1시간뒤</h5>
	                  <h6 class="card-subtitle text-muted">반반무마니/16:23</h6>
	                </div>
	               </div>
				</div>
			</div>
    
        	<div class="col-lg-3">
	            <div class="bs-component">
	              <div class="card mb-2 me-sm-3">
	                <svg xmlns="http://www.w3.org/2000/svg" class="d-block user-select-none" width="100%" height="200" aria-label="Placeholder: Image cap" focusable="false" role="img" preserveAspectRatio="xMidYMid slice" viewBox="0 0 318 180" style="font-size:1.125rem;text-anchor:middle">
	                  <rect width="100%" height="100%" fill="#868e96"></rect>
	                  <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text>
	                </svg>
	                <div class="card-body text-center">
	                  <h5 class="card-title ">서면/치킨드실분/1시간뒤</h5>
	                  <h6 class="card-subtitle text-muted">반반무마니/16:23</h6>
	                </div>
	               </div>
				</div>
			</div>
		     <div class="col-lg-3">
	            <div class="bs-component">
	              <div class="card mb-2 me-sm-3">
	                <svg xmlns="http://www.w3.org/2000/svg" class="d-block user-select-none" width="100%" height="200" aria-label="Placeholder: Image cap" focusable="false" role="img" preserveAspectRatio="xMidYMid slice" viewBox="0 0 318 180" style="font-size:1.125rem;text-anchor:middle">
	                  <rect width="100%" height="100%" fill="#868e96"></rect>
	                  <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text>
	                </svg>
	                <div class="card-body text-center">
	                  <h5 class="card-title ">서면/치킨드실분/1시간뒤</h5>
	                  <h6 class="card-subtitle text-muted">반반무마니/16:23</h6>
	                </div>
	               </div>
				</div>
			</div>
		     <div class="col-lg-3">
	            <div class="bs-component">
	              <div class="card mb-2 me-sm-3">
	                <svg xmlns="http://www.w3.org/2000/svg" class="d-block user-select-none" width="100%" height="200" aria-label="Placeholder: Image cap" focusable="false" role="img" preserveAspectRatio="xMidYMid slice" viewBox="0 0 318 180" style="font-size:1.125rem;text-anchor:middle">
	                  <rect width="100%" height="100%" fill="#868e96"></rect>
	                  <text x="50%" y="50%" fill="#dee2e6" dy=".3em">Image cap</text>
	                </svg>
	                <div class="card-body text-center">
	                  <h5 class="card-title ">서면/치킨드실분/1시간뒤</h5>
	                  <h6 class="card-subtitle text-muted">반반무마니/16:23</h6>
	                </div>
	               </div>
				</div>
			</div>


		</div>
        </div>

</body>
</html>