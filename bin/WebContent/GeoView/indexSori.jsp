<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

index.jsp - 모든 프로젝트의 시작지점(BeforeMain 에서 버튼클릭하면 자동위치 정보 띄움 )
 
   
   <%  
       //시작 전에 세션정리(아이디, 위치정보 세션 모두 날아감) 
       session.invalidate();
   	   response.sendRedirect("./BeforeMain.do");   
   %>



</body>
</html>