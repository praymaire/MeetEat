<%@ include file="../Main/top.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="container">   
<form action="modifyPro.mb?bno=${ dto[0].bno }" method="post">
  <fieldset>
    <legend class="m-3"> 글수정</legend>
    <div class="form-group row">
      <label for="id" class="col-sm-2 col-form-label">작성자</label>
      <div class="col-sm-10">
        <input type="text" readonly class="form-control-plaintext" id="id" name="id" value="${ dto[0].id }">
      </div>
    </div>
    
    <div class="form-group">
      <label for="food_category" class="form-label mt-4">음식 카테고리</label>
      <select class="form-select" name="food_category" id="food_category">
        <option>종류를 선택하세요</option>
        <c:if test="${ dto[0].food_category.equals('한식') }">
       	 <option selected="selected">한식</option>
        </c:if>
        <c:if test="${ !dto[0].food_category.equals('한식') }">
       	 <option>한식</option>
       	</c:if> 
       	<c:if test="${ dto[0].food_category.equals('양식') }">
       	 <option selected="selected">양식</option>
        </c:if>
        <c:if test="${ !dto[0].food_category.equals('양식') }">
       	 <option>양식</option>
       	</c:if>  
       	<c:if test="${ dto[0].food_category.equals('중식') }">
       	 <option selected="selected">중식</option>
        </c:if>
        <c:if test="${ !dto[0].food_category.equals('중식') }">
       	 <option>중식</option>
       	</c:if> 
        <c:if test="${ dto[0].food_category.equals('일식') }">
       	 <option selected="selected">일식</option>
        </c:if>
        <c:if test="${ !dto[0].food_category.equals('일식') }">
       	 <option>일식</option>
       	</c:if> 
        <c:if test="${ dto[0].food_category.equals('분식') }">
       	 <option selected="selected">분식</option>
        </c:if>
        <c:if test="${ !dto[0].food_category.equals('분식') }">
       	 <option>분식</option>
       	</c:if> 
        <c:if test="${ dto[0].food_category.equals('기타') }">
       	 <option selected="selected">기타</option>
        </c:if>
        <c:if test="${ !dto[0].food_category.equals('기타') }">
       	 <option>기타</option>
       	</c:if> 
       
      </select>
    </div>
    
      <div class="form-group">
      <label for="when_name" class="form-label mt-4">시간</label>
      <select class="form-select" name="when_name" id="when_name" >
        <option>시간을 선택하세요</option>
        
        <c:if test="${ dto[0].when_name.equals('30분 뒤') }">
       	 <option selected="selected">30분 뒤</option>
        </c:if>
        <c:if test="${ !dto[0].when_name.equals('30분 뒤') }">
       	 <option>30분 뒤</option>
       	</c:if> 
        
        
        <c:if test="${ dto[0].when_name.equals('1시간 뒤') }">
       	 <option selected="selected">1시간 뒤</option>
        </c:if>
        <c:if test="${ !dto[0].when_name.equals('1시간 뒤') }">
       	 <option>1시간 뒤</option>
       	</c:if>
       	
        <c:if test="${ dto[0].when_name.equals('1시간 30분 뒤') }">
       	 <option selected="selected">1시간 30분 뒤</option>
        </c:if>
        <c:if test="${ !dto[0].when_name.equals('1시간 30분 뒤') }">
       	 <option>1시간 30분 뒤</option>
       	</c:if>
       	
        <c:if test="${ dto[0].when_name.equals('2시간 뒤') }">
       	 <option selected="selected">2시간 뒤</option>
        </c:if>
        <c:if test="${ !dto[0].when_name.equals('2시간 뒤') }">
       	 <option>2시간 뒤</option>
       	</c:if>
        
      </select>
    </div>
    
   <div class="form-group">
      <label for="where_name" class="form-label mt-4">가게 이름</label>
      <input type="text" class="form-control" name="where_name" id="where_name" placeholder="주문할 식당의 이름을 작성하세요" value="${ dto[0].where_name }">
    </div>
    
    <div class="form-group">
      <label for="upload_image" class="form-label mt-4">사진 업로드</label>
      <input class="form-control" type="file" name="upload_image" name="upload_image" id="upload_image" placeholder="메뉴판 사진을 올려주세요" value="${ dto[0].upload_image }">
    </div>
    
    <div class="form-group">
      <label for="content" class="form-label mt-4">추가 내용</label>
      <textarea class="form-control" id="content" name="content" rows="3">${ dto[0].content }</textarea>
    </div>    
    
    <div class="my-3 float-sm-end">
    <button type="submit" class="btn btn-warning">수정 완료</button>
    <button type="button" class="btn btn-secondary" onclick="history.back();">뒤로가기</button>
    </div>
  </fieldset>
</form>
	</div>

<!-- body~bottom 사이 공백 -->
	 <div class="offcanvas-header">　</div>
</body>
</html>