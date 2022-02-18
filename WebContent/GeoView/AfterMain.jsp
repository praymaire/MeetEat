<%@page import="java.util.ArrayList"%>
<%@ include file="../Main/top.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>


<!-- 
위치정보(주소) 없으면 접근 불가, 제일 첫 메인페이지(자동/수동)으로 돌려보내기
세션으로 받는 정보 : 주소(한글) /위,경도
  -->
<% 
   //세션제어
     String latitude =(String)session.getAttribute("latitude");
     String longitude =(String)session.getAttribute("longitude");
  
    //세션체크 console 
    if(latitude == null){
    	System.out.println("안녕하세요 AfterMain 입니다. 좌표를 가져오지 못했습니다. 10987654321");
       } 
%>


 <!-- 내 위치 주변 100m 이내의 사람의 좌표를 테스트 삼아 가져와 표시했습니다. 
             기존에 작성하신 게시판 리스트 가져오는 코드에서 GeoDAO- GetgeoList() sql만 부분 교체해서 사용하시면 되세요*^^*   -->
 
 <script>

 </script>
 
<!-- 사용법 예시 -->
<%-- ${geoList } --%>
   <div class="container">
   <h2>주변 정보보기</h2>
   <table class="table table-hober">
     <tr class="table-warning">
        <td>글번호</td>
        <td>언제/어디서/무엇을</td>
        <td>내용</td>
  <!--       <td>이미지</td> -->
       
     </tr>
     
     <%
        ArrayList geoList = (ArrayList)request.getAttribute("geoList");
     
     %>

     <c:forEach var="dto" items="${geoList }">
         <tr>   
	       <td><a href="./read.mb?bno=${dto.bno }" class="text-decoration-none">${dto.bno }</a></td>
	       <td><a href="./read.mb?bno=${dto.bno }" class="text-decoration-none">${dto.when_name }/${dto.where_name }/${dto.food_category }</a></td>
	       <td><a href="./read.mb?bno=${dto.bno }" class="text-decoration-none">${dto.content }</a></td>
	   <%--     <td>${dto.upload_image }</td> --%>
			<c:if test="${empty geoList }"><td>결과 없음</td></c:if>
	      	
	 
	     </tr>
     </c:forEach>    
     
   </table>

</div>


 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div> 
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
</body>
</html>