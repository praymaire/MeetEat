<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main page(After)</title>
</head>
<body>

<!-- 
위치정보(주소) 없으면 접근 불가, 제일 첫 메인페이지(자동/수동)으로 돌려보내기
세션으로 받는 정보 : 주소(한글) /위,경도
  -->
<% 
   //hidden으로 설정하기
    String latitude = request.getParameter("latitude");
    String longitude = request.getParameter("longitude");
  
    //세션체크 console 
    if(latitude == null){
    	System.out.println("안녕하세요 AfterMain 입니다. 좌표를 가져오지 못했습니다. 10987654321");
       } 
%>


 <h1>AfterMain 페이지 - (위치정보 적용 후)</h1>
 <!-- 내 위치 주변 100m 이내의 사람의 좌표를 테스트 삼아 가져와 표시했습니다. 
             기존에 작성하신 게시판 리스트 가져오는 코드에서 GeoDAO- GetgeoList() sql만 부분 교체해서 사용하시면 되세요*^^*   -->
 
 
 
<!-- 사용법 예시 -->
${geoList }
<hr>
   
   <table border="1">
     <tr>
       <td>위도</td>
       <td>경도</td>
     </tr>
     
     <%
        ArrayList geoList = (ArrayList)request.getAttribute("geoList");
     %>
     
     <c:forEach var="dto" items="${geoList }">
         <tr>
	       <td>${dto.latitude }</td>
	       <td>${dto.longitude }</td>
	     </tr>
     </c:forEach>    
     
   </table>




</body>
</html>