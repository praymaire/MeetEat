<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>meeteat!</title>
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
	 <jsp:include page="../Main/top.jsp"></jsp:include>
	 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
	<!-- 상단 메뉴 -->
       
    <div class="container">   

  <form action=""></form>
  <fieldset>
    <legend class="m-3"> 모집글쓰기</legend>
    <div class="form-group row">
      <label for="staticEmail" class="col-sm-2 col-form-label">작성자</label>
      <div class="col-sm-10">
        <input type="text" readonly="" class="form-control-plaintext" id="id" name="id" value="<%=id%>">
      </div>
    </div>
    
    <div class="form-group">
      <label for="food_category" class="form-label mt-4">음식 카테고리</label>
      <select class="form-select" name="food_category">
        <option>종류를 선택하세요</option>
        <option>한식</option>
        <option>양식</option>
        <option>중식</option>
        <option>일식</option>
        <option>분식</option>
        <option>기타</option>
      </select>
    </div>
    
      <div class="form-group">
      <label for="when_name" class="form-label mt-4">시간</label>
      <select class="form-select" name="when_name">
        <option>시간을 선택하세요</option>
        <option>30분 뒤</option>
        <option>1시간 뒤</option>
        <option>1시간 30분 뒤</option>
        <option>2시간 뒤</option>
      </select>
    </div>
    
   <div class="form-group">
      <label for="where_name" class="form-label mt-4">가게 이름</label>
      <input type="text" class="form-control" name="where_name" placeholder="주문할 식당의 이름을 작성하세요">
    </div>
    
    <div class="form-group">
      <label for="upload_image" class="form-label mt-4">사진 업로드</label>
      <input class="form-control" type="file" name="upload_image" name="upload_image" placeholder="메뉴판 사진을 올려주세요">
    </div>
    
    <div class="form-group">
      <label for="content" class="form-label mt-4">추가 내용</label>
      <textarea class="form-control" id="content" name="content" rows="3"></textarea>
    </div>    
    
    <div class="my-3 float-sm-end">
    <button type="submit" class="btn btn-success">작성</button>
    <button type="button" class="btn btn-warning">수정</button>
    <button type="button" class="btn btn-danger" onclick="location.href='/Member/report.jsp'">신고</button>
    <button type="button" class="btn btn-info">채팅</button>
    <button type="button" class="btn btn-secondary" onclick="history.back();">뒤로가기</button>
    </div>
  </fieldset>
</form>
	</div>

<!-- body~bottom 사이 공백 -->
	 <div class="offcanvas-header">　</div>
</body>
</html>