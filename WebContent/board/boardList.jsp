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
        <link href="../css/bootstrap.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body> 
<%
	String id=(String)session.getAttribute("id");
	// BoardDao 객체생성
	//BoardDAO bdao=new BoardDAO();
	// 게시판 DB에 있는 글 개수를 확인
//	int cnt=bdao.getBoardCount();
	int cnt=100;


	///////////////////////////////////////////////
	/////////////////// 페이징 처리 ///////////////////
	//
	// 한 페이지에 출력될 글 개수
	int pageSize=10;
	// 한 페이지 정보 설정
	String pageNum=request.getParameter("pageNum");
	if(pageNum==null){
		pageNum="1";
	} 
	
	// 첫행번호를 계산
	int currentPage = Integer.parseInt(pageNum);
	int startRow=(currentPage-1)*pageSize+1;
	///////////////////////////////////////////////
%>


	<!-- 상단 메뉴 -->
	 <jsp:include page="../Main/top.jsp"></jsp:include>
	 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
	<!-- 상단 메뉴 -->

	<div class="contain">
		<div class="row">
		<div class="col-2"><br> <!-- 테이블 왼쪽 공백 --> </div>
			<div class="col-8">
				<h2> 주변 기반 게시글</h2><br>
				<%
				if(id!=""){
				%>
				<button class="btn btn-success float-sm-end mb-3" onclick="location.href='write.jsp';">글쓰기</button>
				<%} %>
				<table class="table table-hover">
				
					<tbody class="table-light">
				    <tr>
				      <th scope="row" class="col-6"><b>1시간뒤/맥도날드 서면점/햄버거</b></th>
				   	  <td rowspan="2" class="col-4"><img class="img-thumbnail" src="menu.png"></td>
				    </tr>
				    <tr>
				      <td class="col-6">최소금액 같이 채우실분~~</td>
				    </tr>
				  </tbody>
				</table>
			

			<!-- 페이지 네비게이션 -->
	
			 <ul class="pagination pagination-sm justify-content-sm-center">
			 <li class="page-item">
  				    <a class="page-link" href="#">&laquo;</a>
   				 </li>
			<% if(cnt!=0){
				/////////// 페이징처리
				int pageCnt=cnt/pageSize+(cnt%pageSize==0? 0:1);
				
				// 한 페이지에 보여줄 페이지 블럭
				int pageBlock=10;
				
				// 한 페이지에 보여줄 페이지블럭 시작번호 계산
				int startPage =((currentPage-1)/pageBlock)*pageBlock+1;
				
				// 한페이지에 보여줄 페이지 블럭 끝번호 계산
				int endPage=startPage+pageBlock-1;
				if(endPage>pageCnt){
					endPage=pageCnt;
				}

				if(startPage>pageBlock){%>
				 <li class="page-item">
  				    <a class="page-link" href="#">&laquo;</a>
   				 </li><%
				}for(int i=startPage;i<=endPage;i++){%>
				    <li class="page-item">
				      <a class="page-link" href="?pageNum=<%=i%>"><%=i %></a>
				    </li>
				<%}
				if(pageCnt>endPage){%>
				    <li class="page-item">
				      <a class="page-link" href="?pageNum=<%=startPage+pageBlock%>">&raquo;</a>
				    </li><%
				}}%>
				 <li class="page-item">				      <a class="page-link" href="#">&raquo;</a>
				 </li>
			</ul>
			</div>
			</div>	
			
	
	</div>

</body>
</html>