<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
        <title>meateat!</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <link href="css/bootstrap.css" rel="stylesheet" />
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
</head>
<body>
<% 	String id = (String)session.getAttribute("id"); %>
<header>
<nav class="navbar navbar-expand-lg navbar-light bg-light p-4">
  <div class="container-fluid">
    <a class="navbar-brand ms-2" href="#">Meeteat</a>

    <!-- 공백  --><div class="offcanvas"></div>

	<!-- 검색/위치 메뉴  -->    
	<form action="<!-- 검색결과 페이지  -->" class="d-block">
		<div class="input-group">
			<sup>현재위치는…</sup><br>
			<i class='fas fa-map-marked-alt me-1'></i>
			<a> 부산진구 전포동 </a></div>
<!-- 			<input type="text" placeholder="검색어를 입력하세요" class="ps-2">
			<a href="#" ><i class='fas fa-search' style='font-size:24px'></i></a> -->
    </form>

    <!-- 공백 --><div class="offcanvas"></div>
    
	<div class="d-block">
		<!-- 로그인정보 / 회원가입+로그인  -->
		<div class="d-flex"> 
        	<ul class="navbar-nav me-auto">
        	<%if(id==null){ %>
        	 <li class="nav-item">
        		 <a class="nav-link" href="./MemberLogin.me">로그인</a>
      		 </li>
      		 <li class="nav-item">
        		 <a class="nav-link" href="./MemberJoin.me">회원가입</a>
      		 </li>
       		<%} else {
       			if(id.equals("admin")){%>
	      		 <li class="nav-item">
	        		 <a class="nav-link" href="./MemberList.ad">관리자 페이지</a>
	      		 </li>
      		 <%} else{%>
      		 	 <li class="nav-item">
        		 	<a class="nav-link" href="#"><%=id %>님</a>
      		 	 </li>
      		 	 <li class="nav-item">
        		 	<a class="nav-link" href="./MemberInfo.me">내정보</a>
      		 	 </li>
			<%}%>
     		 	<li class="nav-item">
     		 		<a class="nav-link" href="./MemberLogout.me">로그아웃</a>
   		 	 	</li>
       		<% } %>
       		
      		</ul>
      	</div>
        
        <!-- 메뉴 -->
    	<button class="navbar-toggler collapesd" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
      		<span>MENU</span>
    	</button>
	   	<div class="navbar-collapse show" id="navbarColor03" style="">
	      <ul class="navbar-nav">
	        <li class="nav-item me-3">
	          <a class="nav-link" href="./Main.me"><i class='fas fa-home me-1'></i>Home</a>
	        </li>
	        <li class="nav-item me-3">
	          <a class="nav-link" href="./list.mb"><i class='fas fa-utensils me-1'></i>찾기</a>
	        </li>
	        <li class="nav-item me-3">
	          <a class="nav-link" href="#"><i class='far fa-handshake me-1'></i>Not Yet</a>
	        </li>
	        <li class="nav-item me-3">
	          <a class="nav-link" href="./QnaList.bo"><i class='far fa-question-circle me-1'></i>Q&A</a>
	        </li>
	      </ul>
	     </div>
      </div> <!-- <div class="d-block"> -->
      <!-- 공백 --><div class="offcanvas"></div>
	</div> <!--  <div class="container-fluid"> -->
</nav>
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div> 
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
</header>

