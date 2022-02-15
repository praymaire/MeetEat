<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>meateat!</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="../css/bootstrap.css" rel="stylesheet" />
    </head>
<body>
<!-- 세션 id 정보 불러오기 -->
<%-- <% String id=request.getParameter("id");%> --%>
<%-- <% String id=session.getAttribute("id") %> --%>
<%String id="홍길동"; %><!-- 이건 css 테스트용 -->
       	<!-- 상단 메뉴 -->
	 <jsp:include page="../css/top.jsp"></jsp:include>
	 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
	<!-- 상단 메뉴 -->
       
    <div class="container">   
      <div class="row">
		<div class="col-2"><br></div>
		<div class="col-8">
			<div class="list-group">
			<!-- 글 제목 -->
			  <a href="#" class="list-group-item list-group-item-action flex-column align-items-start active">
			    <div class="d-flex w-100 justify-content-between my-sm-3">
			      <h2 class="mb-1 ms-sm-4 ">치킨드실분~~~ </h2>
			      <h3>1/4</h3>
			    </div>
			  </a>
			  <!-- 글 본문 -->
			  <a href="#" class="list-group-item list-group-item-action flex-column align-items-start">
			      <div class="text-end"><small class="text-muted ">홍길동  16:24</small></div><br><br>
			      <div class="text-center"> <img src="ckmenu.JPG" width="400px"></div>
			      <p class="mb-1">1시간 뒤에 노랑통닭 시킬거에요</p>
			      <small class="text-muted">(추가 넣을 내용이 있으면..?)</small>
			  </a>
			</div>
			<div class="py-sm-2 float-sm-end">
			<%if(id.equals("홍길동")!=false){ %>
				<button type="button" class="btn btn-outline-warning btn-sm">수정</button>
				<button type="button" class="btn btn-outline-danger btn-sm">삭제</button><%} %>
				<button type="button" class="btn btn-outline-success btn-sm">목록</button>
				</div>

		</div>
	  </div>
	</div>

<!-- body~bottom 사이 공백 -->
	 <div class="offcanvas-header">　</div>
</body>
</html>