<%@ include file="../Main/top.jsp" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script src="JQuery/jquery-3.6.0.js"></script>
<script type="text/javascript">
	function banManagePopup(reported_user, reported_count) {
		window.open("./AdminBanManagePopup.ad?reported_user=" + reported_user + 
					"&reported_count=" + reported_count, 
					"정지관리",
					"width=300, height=300, top=50, left=50");
	}
	
	function banCancel(reported_user) {
        if (!confirm(reported_user + "를 정지해제 하시겠습니까?")) {
            alert("취소(아니오)를 누르셨습니다.");
        } else {
            location.href="./AdminBanCancelProAction.ad?reported_user=" + reported_user;
        }
    }
</script>
  <%
    if(id == null || !id.equals("admin") ){
    	response.sendRedirect("./MemberLogin.me");
    }
   %>
	<div class="container">
	<ul class="nav nav-tabs h4">
	  <li class="nav-item">
	    <a class="nav-link" href="./MemberList.ad">회원정보</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link"  href="./AdminReportManage.ad">신고관리</a>
	  </li>
	  <li class="nav-item">
	    <a class="nav-link" href="#">Disabled</a>
	  </li>
	</ul>
	<br>
   <table class="table table-hover">
     <tr class="table-info">
       <td>아이디</td>
       <td>비밀번호</td>
       <td>닉네임</td>
       <td>전화번호</td>
       <td>이메일</td>
       <td>주소</td>
       <td>회원포인트</td>
       <td>회원등급</td>
       <td>누적신고횟수</td>
       <td>정지날짜</td>
       <td>관리</td>
     </tr>
     <%
     	ArrayList memberList =
    	(ArrayList)request.getAttribute("memberList");
     %>
     <c:if test="${requestScope.cnt != 0 }">
	 <c:forEach var="dto" items="${memberList }">
         <tr>
	       <td>${dto.id }</td>
	       <td>${dto.pw }</td>
	       <td>${dto.nickname }</td>
	       <td>${dto.phone }</td>
	       <td>${dto.email }</td>
	       <td>${dto.address }</td>
	       <td>${dto.user_point }</td>
	       <td>${dto.user_level }</td>
	       <td>${dto.reported_count }</td>
	       <td>${dto.ban_date }</td>
	       <td><input type="button" value="정지관리" onclick="banManagePopup('${dto.id}', '${dto.reported_count }');" class="btn btn-sm btn-danger"> /
	       <input type="button" value="정지해제" onclick="banCancel('${dto.id}');" class="btn btn-sm btn-success"> /
	       <input type="button" value="삭제" class="btn btn-sm btn-secondary"></td>
	     </tr>
     </c:forEach>  
     </c:if>
     <c:if test="${requestScope.cnt == 0 }">
		<tr>
			<td> 회원이 존재하지 않습니다. </td>
		</tr>
	 </c:if>
     </table>
     	  <!-- 페이징  처리 -->
	<div id="page_control">
	<ul class="pagination pagination-sm justify-content-center">

		<c:if test="${requestScope.cnt != 0 }">
			<c:if test="${requestScope.startPage > requestScope.pageBlock }">
			  <li class="page-item">
				<a href="./MemberList.ad?pageNum=${requestScope.startPage - requestScope.pageBlock }" class="page-link">&laquo;</a></li>
			</c:if>
			<c:forEach var="i" begin="${requestScope.startPage }" end="${requestScope.endPage }" step="1" >
			  <li class="page-item">
				<a href="./MemberList.ad?pageNum=${i }" class="page-link">${i }</a></li>
			</c:forEach>
			<c:if test="${requestScope.endPage < requestScope.pageCount }">
			  <li class="page-item">
				<a href="./MemberList.ad?pageNum=${ requestScope.startPage +  requestScope.pageBlock}" class="page-link">&raquo;</a></li>
			</c:if>
		</c:if>
		</ul>
	</div>
  <!-- 페이징  처리 -->
</div>
<div class="offcanvas-header"></div><div class="offcanvas-header"></div>
</body>
</html> 