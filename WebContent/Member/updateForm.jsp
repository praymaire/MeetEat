<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src= "./JQuery/jquery-3.6.0.slim.js"></script>
<script type="text/javascript">

   function checkValue(){
	   
	   if(document.update.pw.value == ""){
		   alert(" 비밀번호를 입력하세요! ");
		   document.update.pw.focus();
		   return false;
	   }
	   if (document.update.nickname.value == "") {
			alert("닉네임을 입력하세요");
			document.update.nickname.focus();
			return false;
		}
		if (document.update.phone.value == "") {
			alert("전화번호를 입력하세요");
           document.update.phone.focus();
           return false;
		}
		if (document.update.phone.value.length !== 11) {
			alert("올바른 전화번호를 입력하세요");
			document.update.phone.focus();
			return false;
		}
		if (isNaN(document.update.phone.value)) {
			alert("휴대폰 번호는 숫자로만 입력해주세요");
			document.update.phone.focus();
			return false;			
		}
		if (document.update.address.value == "") {
			alert("주소를 입력하세요");
			document.update.address.focus();
			return false;
		}
		if (document.update.email1.value == "") {
			alert("이메일을 입력하세요");
			document.update.email1.focus();
			return false;
		}
		if (document.update.email2.value == "") {
			alert("이메일을 입력하세요");
			document.update.email2.focus();
			return false;
		}   
   }
   
	// 닉네임 일치 검사
	function nicknameCheck(nickname) {
		if (nickname == "") {
			alert("닉네임을 입력하세요");
			document.update.nickname.focus();
			return false;
		}
		else {
			location.href='./MemberNicknameCheck.me?nickname=' + nickname;
		}
	}
	
	// 이메일
	function email_change() {
		if (document.update.email.options[document.update.email.selectedIndex].value == '0') {
			document.update.email2.disabled = true;
			document.update.email2.value = "";
		}
		if (document.update.email.options[document.update.email.selectedIndex].value == '6') {
			document.update.email2.disabled = false;
			document.update.email2.value = "";
			document.update.email2.focus();
		} else {
			document.update.email2.disabled = true;
			document.update.email2.value = document.update.email.options[document.update.email.selectedIndex].value;
		}
	}
	
	// 우편번호 찾는 이벤트
	function daumPostcode() {
		new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.update.postcode.value = data.zonecode;
                document.update.address.value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.update.detailAddress.focus();
            }
        }).open();
    }

</script>
</head>
<body>
   <h1>./Member/updateForm.jsp</h1>
   
   <%
     // 세션값 제어
     String id = (String)session.getAttribute("id");
   
     if(id == null){
    	 response.sendRedirect("./MemberLogin.me");
     }   
   
   %>
   
   <fieldset>
   		<div>회원정보 수정</div>
      	<form action="./MemberUpdateProAction.me" method="post" name="update" enctype="multipart/form-data" onsubmit="return checkValue();"> 
      		<div>
      			<label>프로필 사진</label>
				<input type="text" disabled placeholder="프로필을 업로드하세요">
	      		<input type="file" name="profile_image">
		    </div>
		    <div>
		      	<label>아이디</label>	
		        <input type="text" name="id" value="${ mdto.id }" readonly="readonly">
		    </div>
		    <div>
		        <label>비밀번호</label>
		        <input type="password" name="pw" placeholder="비밀번호를 입력하세요.">
		    </div>
		    <div>
		        <label>닉네임</label>
	        	<input type="text" name="nickname" id="nickname">
	        	<input type="button" value="중복체크" onclick="nicknameCheck(this.form.nickname.value)">		        
		    </div>
		    <div>
		        <label>휴대폰 번호(-없이 번호만 입력해주세요)</label>
	        	<input type="text" name="phone" id="phone" maxlength="11" placeholder="ex)01012345678">
	        </div>
		    <div>
		    	<label>이메일</label>
	         	<input type="text" name="email1" id="email1" value="이메일" maxlength="50" onfocus="this.value='';"> @ 
				<input type="text" name="email2" value="" disabled>
				<select name="email" id="email" onchange="email_change()">
					<option value="0">선택하세요</option>
				 	<option value="daum.net">daum.net</option>
					<option value="gmail.com">gmail.com</option>
					<option value="nate.com">nate.com</option>
				 	<option value="naver.com">naver.com</option>
				 	<option value="hanmail.net">hanmail.net</option>
				 	<option value="6">직접 입력</option>
				</select>
		    </div>
		    <div>
		    	<label>주소</label>
		        <input type="text" name="postcode" id="postcode" placeholder="우편번호">
				<input type="button" onclick="daumPostcode()" value="우편번호 찾기"> <br>
				<input type="text" name="address" id="address" placeholder="주소"> <br>
				<input type="text" name="detailAddress" id="detailAddress" placeholder="상세주소">
				<input type="text" name="extraAddress" id="extraAddress" placeholder="참고항목">
		    </div>
       		<div>        
        		<input type="submit" value="회원수정">
         		<input type="reset" value="초기화">
       	 	</div>
      	</form>
   </fieldset>
   
   
   
</body>
</html>