<%@page import="org.eclipse.jdt.internal.compiler.lookup.MemberTypeBinding"%>
<%@page import="com.itwillbs.member.db.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

   function check(){
	   
	   if(document.fr.pass.value == ""){
		   alert(" 비밀번호를 입력하세요! ");
		   document.fr.pass.focus();
		   return false;
	   }
	   
	   
   }

</script>
</head>
<body>
   <h1>WebContent/member/updateForm.jsp</h1>
   
   <%
     // 세션값 제어
     String id = (String)session.getAttribute("id");
   
     if(id == null){
    	 response.sendRedirect("./MemberLogin.me");
     }   
     
     // 해당 회원의 모든정보를 가져와서 화면에 출력
   
   %>
   
   
   <h2> 회원정보수정 </h2>
   <fieldset>
      <legend> ITWILL 회원정보 수정 </legend>
      
      <form action="./MemberUpdateProAction.me" method="post" name ="fr" onsubmit="return check();">          
         <!-- readonly : 읽기전용 (submit가능) / disabled : 사용불가 (submit 불가능) -->
         아이디 : <input type="text" name="id" value="${dto.id }"   readonly="readonly"><br>
         비밀번호 : <input type="password" name="pass" placeholder="비밀번호를 입력하시오."><br>
         이름 : <input type="text" name="name" value="${dto.name }"><br>
         나이 : <input type="text" name="age" value="${dto.age }"><br>
         이메일 : <input type="text" name="email" value="${dto.email }"><br>
         성별 : <input type="radio" name="gender" value="남"  
		         <%
		           MemberDTO dto = (MemberDTO) request.getAttribute("dto");
		         if(dto.getGender().equals("남")){ %>
		         checked
		         <%} %>
		          >남 
                <input type="radio" name="gender" value="여" 
                 <%if(dto.getGender().equals("여")){ %>
		         checked
		         <%} %>
                 >여
                
         <input type="submit" value="회원수정">
         <input type="reset" value="초기화">
      </form>
   
   </fieldset>
   
   
   
   
   
</body>
</html>