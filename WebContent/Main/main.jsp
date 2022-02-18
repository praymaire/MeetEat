<%@ include file="../Main/top.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<c:if test="${requestScope.check != 1 }">
		<c:redirect url="./Main.mb"/>
	</c:if>
       <!-- Page content-->
  
        <div class="container">
        <h3 class="text-center"> 최신글</h3>
        </div>
        <div class="container">
    	    <c:forEach var="list" items="${requestScope.BoardList}" varStatus="i">
			<c:if test="${(i.index+1)%4==1 }"><div class="d-flex"></c:if>
		     <div class="col-lg-3" onclick="location.href='./read.mb?bno=${list.bno}';">
	            <div class="bs-component">
	              <div class="card mb-2 me-sm-3">
	              <img src="${pageContext.request.contextPath}${list.upload_image }" style="height:250px;">
	                <div class="card-body text-center">
	                  <h5 class="card-title ">${ list.where_name }/${ list.food_category }/${ list.when_name }</h5>
	                  <h6 class="card-subtitle text-muted">${ list.id }/
	                  <fmt:formatDate value="<%=new java.util.Date() %>" pattern="yy.MM.dd" var="today" />
				       <fmt:formatDate value="${ list.write_time }" pattern="yy.MM.dd" var="regdate"/>
				       <c:choose>
					     <c:when test="${regdate eq today }"> 
					       <fmt:formatDate value="${ list.write_time }" pattern="HH:mm"/></c:when> 
			       		 <c:otherwise>${regdate }</c:otherwise> 
			      		</c:choose></h6>
			     
	                </div>
	               </div>
				</div>
				<c:if test="${(i.index+1)%4==0 }"></div></c:if> 
			</div>
     	</c:forEach>
        	

		</div>
        </div>
<div class="offcanvas-header">　</div>
<div class="offcanvas-header">　</div>
</body>
</html>