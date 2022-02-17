<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../Main/top.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
      
<title>meeteat!</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="css/bootstrap.css" rel="stylesheet" />
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src= "JQuery/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	
	/* onkeyup 이벤트 */
	
	// 비밀번호
	function pwCheckFunction() {
		
		var pass = $('#pw').val();
		var checkPw = $('#checkPw').val();
		var passReg = /^(?=.*\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{8,16}$/;
		
		if(pass == "") {
			$('#pwCheckMessage').html('비밀번호를 입력해주세요');
			$('#pwCheckMessage').attr('color', 'red');
			$('#canUsePass').val('F');
			return false;
		}
		
		if(!passReg.test(pass)) {
			$('#pwCheckMessage').html('비밀번호는 영문과 숫자를 포함한 8자이상이여야합니다');
			$('#pwCheckMessage').attr('color', 'red');
			$('#canUsePass').val('F');
			return false;
		} else if (passReg.test(pass)){
			$('#pwCheckMessage').html('&nbsp;');
		}
		
		if(checkPw == "") {
			$('#checkPwCheckMessage').html('비밀번호 확인란을 입력해주세요');
			$('#checkPwCheckMessage').attr('color', 'red');
			$('#canUsePass').val('F');
			return false;
		}
		
		if(pass == checkPw && checkPw != "") {
			$('#checkPwCheckMessage').html('비밀번호가 서로 일치합니다');
			$('#checkPwCheckMessage').attr('color', 'green');
			$('#canUsePass').val('T');
		} else {
			$('#checkPwCheckMessage').html('비밀번호가 서로 일치하지 않습니다.');
			$('#checkPwCheckMessage').attr('color', 'red');
			$('#canUsePass').val('F');
		}
		return false;
	
	}
	
	// 휴대전화
	function phoneCheck() {
		
		var phone = $('#phone').val();
		var phoneReg = /^[0-9]*$/;
		
		if(phone == "") {
			$('#phoneCheckMessage').html('휴대전화번호를 입력해주세요');
			$('#phoneCheckMessage').attr('color', 'red');
			return false;
		}
		
		if(phone.length != 11){
			$('#phoneCheckMessage').html('휴대전화번호의 자릿수를 확인해주세요');
			$('#phoneCheckMessage').attr('color', 'red');
			return false;
		}
		
		if(!phoneReg.test(phone)) {
			$('#phoneCheckMessage').html('휴대전화번호는 숫자만 입력가능합니다');
			$('#phoneCheckMessage').attr('color', 'red');
			
			return false;
		} else {
			$('#phoneCheckMessage').html('&nbsp;');
		}
		return false;
		
	}
	
	// 이메일
	function emailCheck() {
		
		var email1 = $('#email1').val();
		var email2 = $('#email2').val();
		
		var email1 = $('#email1').val();
		var email2 = $('#email2').val();
		
		if(email1 != "" && email2 != "") {
			$('#emailCheckMessage').html('&nbsp;');
		} 
		
	}
	
	function email_change() {
		
		if (document.join.email.options[document.join.email.selectedIndex].value == '6') {
			document.join.email2.disabled = false;
			document.join.email2.value = "";
			document.join.email2.focus();
		} else {
			document.join.email2.disabled = true;
			document.join.email2.value = document.join.email.options[document.join.email.selectedIndex].value;
		}
		
		emailCheck();
		
	}
	
	// 주소
	function addressCheck() {
		
		var postcode = $('#postcode').val()
		var address = $('#address').val()
		var detailAddress = $('#detailAddress').val()
		
		if (postcode != "" || address != "" || detailAddress != "") {
			$('#addressCheckMessage').html('&nbsp;');
		}
		
	}

	
	/* Submit 할 때 유효성 검사 */
	function checkValue() {
		
		// input값 유효성 체크, 중복체크 했는지 체크, Submit에서 막혔을시 중복검사 초기화
	
		var inputId = $('#inputId').val();
		var isIdChecked = $('#isIdChecked').val();
		
		// 아이디
 		if(inputId == "") {
			$('#checkId').html('아이디를 입력하세요');
			$('#checkId').attr('color', 'red');
			$('#inputId').focus();
			$('#isIdChecked').val('F');
			return false;	
		} 
		
		if(isIdChecked == "F") {
			$('#checkId').html('아이디 중복검사를 진행해주세요');
			$('#checkId').attr('color', 'red');
			$('#inputId').focus();
			return false; 
		}
		
		idCheck();	
		
		
		// 비밀번호
		var canUsePass = $('#canUsePass').val();
		
		if(canUsePass == 'F') {
			$('#checkPwCheckMessage').html('비밀번호를 올바르게 입력했는지 확인해주세요');
			$('#checkPwCheckMessage').attr('color', 'red');
			$('#pw').focus();
			return false;
		}
		
		pwCheckFunction();
		
		// 닉네임
		var nickname = $('#nickname').val();
		var isNicknameChecked = $('#isNicknameChecked').val();
		
		if(nickname == "") {
			$('#nicknameCheckMessage').html('닉네임을 입력하세요');
			$('#nicknameCheckMessage').attr('color', 'red');
			$('#nickname').focus();
			$('#isNicknameChecked').val('F');
			return false;	
		}
		
		if(isNicknameChecked == "F") {
			$('#nicknameCheckMessage').html('닉네임 중복검사를 진행해주세요');
			$('#nicknameCheckMessage').attr('color', 'red');
			$('#nickname').focus();
			return false; 
		}
		
		nicknameCheck();

		
		// 휴대전화
		
		var phone = $('#phone').val();
		var phoneReg = /^[0-9]*$/;
		
		if(phone == "") {
			$('#phoneCheckMessage').html('휴대전화번호를 입력해주세요');
			$('#phoneCheckMessage').attr('color', 'red');
			$('#phone').focus();
			return false;
		}
		
		if(phone.length != 11){
			$('#phoneCheckMessage').html('휴대전화번호의 자릿수를 확인해주세요');
			$('#phoneCheckMessage').attr('color', 'red');
			$('#phone').focus();
			return false;
		}
		
		if(!phoneReg.test(phone)) {
			$('#phoneCheckMessage').html('휴대전화번호는 숫자만 입력가능합니다');
			$('#phoneCheckMessage').attr('color', 'red');
			$('#phone').focus();
			return false;
		} 
		
		phoneCheck();	
		
		
		// 이메일
		var email1 = $('#email1').val();
		var email2 = $('#email2').val();
		
		if(email1 == "") {
			$('#emailCheckMessage').html('이메일을 입력해주세요');
			$('#emailCheckMessage').attr('color', 'red');
			$('#email1').focus();
			return false;
		}
		
		if(email2 == "") {
			$('#emailCheckMessage').html('이메일을 입력해주세요');
			$('#emailCheckMessage').attr('color', 'red');
			$('#email2').focus();
			return false;
		}
		
		
		// 주소 체크
		var postcode = $('#postcode').val()
		var address = $('#address').val()
		var detailAddress = $('#detailAddress').val()
		
		if (postcode == "" || address == "" || detailAddress == "") {
			$('#addressCheckMessage').html('주소를 입력해주세요')
			$('#addressCheckMessage').css('color', 'red')
			return false;
		}

	}
	

	/* 우편번호 */
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
                document.join.postcode.value = data.zonecode;
                document.join.address.value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.join.detailAddress.focus();
            }
        }).open();
    }
	
    
 	/* Ajax 중복검사 */
 	
 	// 아이디 중복검사
    function idCheck() {
 		
    	var inputId = $('#inputId').val();
    	var idReg = /^[a-z]+[a-z0-9]{5,19}$/g;
 		     
        if(inputId == "") {
        	$('#checkId').html('아이디를 입력하세요')
        	$('#checkId').attr('color', 'red');
			$('#isIdChecked').val('F');
        	return false;
        }
        
        if(!idReg.test(inputId)) {
        	$('#checkId').html('닉네임은 영소문자로 시작하는 영소문자 또는 숫자 6~20자여야 합니다')
        	$('#checkId').attr('color', 'red');
			$('#isIdChecked').val('F');
        	return false;
        }
        
        $.ajax({
            url: "./MemberIdCheck.me",
            type: "POST",
            data: {inputId: inputId},
            dataType: 'JSON',
            success: function(result){
                if(result == 0){
                    $("#checkId").html('이미 사용중인 아이디입니다.');
                    $("#checkId").attr('color', 'red');
                    $('#isIdChecked').val('F');
                    return false;
                } else {
                    $("#checkId").html('사용할 수 있는 아이디입니다.');
                    $("#checkId").attr('color', 'green');
                    $('#isIdChecked').val('T');
                    return false;
                }
            },
            error: function(request, error){
                alert("오류!");
                // Ajax 오류 발생시 원인 확인 창
                alert("code:"+request.status+ "\n" + "message: " + request.responseText + "\n" + "error: " + error);
            }
                
        });
    }
    
 	
 	// 닉네임 중복검사
    function nicknameCheck() {
 		
    	var nickname = $('#nickname').val();
    	var nicknameReg = /^([a-zA-Z0-9ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{1,10}$/;;
		
        if(nickname == "") {
        	$('#nicknameCheckMessage').html('닉네임를 입력하세요');
        	$('#nicknameCheckMessage').attr('color', 'red');
        	$('#nickname').focus();
        	$('isNicknameChecked').val('F');
        	return false;
        }
        
        if(!nicknameReg.test(nickname)) {
        	$('#nicknameCheckMessage').html('닉네임은 한글, 영문, 숫자만 가능하며 2-10자리만 가능합니다')
        	$('#nicknameCheckMessage').attr('color', 'red');
        	$('#nickname').focus();
        	$('#isNicknameChecked').val('F');
        	return false;
        }
		
        $.ajax({
            url: "./MemberNicknameCheck.me",
            type: "POST",
            data: {nickname: nickname},
            dataType: 'JSON',
            success: function(result){
                if(result == 0){
                    $("#nicknameCheckMessage").html('이미 사용중인 닉네임입니다.');
                    $("#nicknameCheckMessage").attr('color', 'red');
                    $('#isNicknameChecked').val('F');
                    return false;
                } else {
                    $("#nicknameCheckMessage").html('사용할 수 있는 닉네임입니다.');
                    $("#nicknameCheckMessage").attr('color', 'green');
                    $('#isNicknameChecked').val('T');
                    return false;
                }
            },
            error: function(request, error){
                alert("오류!");
                // Ajax 오류 발생시 원인 확인 창
                alert("code:"+request.status+ "\n" + "message: " + request.responseText + "\n" + "error: " + error);
            }
                
        });
    }
 	
</script>
<title>Insert title here</title>
</head>
<body>
<div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
<form action="./MemberJoinAction.me" method="post" name="join" id="join" onsubmit="return checkValue()" class="container">
 <div class="row justify-content-center">
 <fieldset class="card bg-light mb-6 col-lg-6">
    <legend class="card-header">회원가입</legend>
		<div class=" p-sm-5">
		
		<!-- id -->
		<div class="form-group row">
 			<label class="form-label col-sm-2">아이디</label>
			<div class="input-group">
	     		<input type="text" class="form-control" name="id" id="inputId" onkeyup="document.join.isIdChecked.value='F'" placeholder="아이디를 입력하세요">
	    		<input type="button" class="btn btn-warning" onclick="idCheck();" value="중복확인">
	    		<input type="hidden" value="F" name="isIdChecked" id="isIdChecked"> <!-- 중복체크했는지 여부 확인 -->
			</div>
		</div>
		<font id="checkId" size="4">&nbsp;</font>
		<!-- id -->
    
	    <!-- pw -->
	    <div class="form-group">
	      <label class="form-label mt-4 col-sm-2">비밀번호</label>
	      <input type="password" class="form-control col-sm-8" name="pw" id="pw" placeholder="비밀번호를 입력하세요" onkeyup="pwCheckFunction();">
	    </div>
    
    	<font id="pwCheckMessage" size="4">&nbsp;</font>
    
	    <div class="form-group">
	      <label class="form-label mt-4 col-sm-2">비밀번호 확인</label>
	      <input type="password" class="form-control col-sm-8" name="checkPw" id="checkPw" placeholder="비밀번호를 확인하세요" onkeyup="pwCheckFunction();">
	    </div>
	    
	    <input type="hidden" value="F" id="canUsePass"> <!-- 비밀번호 사용가능 여부 확인 -->
	   	<font id="checkPwCheckMessage" size="4">&nbsp;</font>
	   	<!-- pw -->
	    
	    <!-- 닉네임 -->
		<div class="form-group row">
 			<label class="form-label mt-4 col-sm-2">닉네임</label>
			<div class="input-group">
	     		<input type="text" class="form-control" name="nickname" id="nickname" onkeyup="document.join.isNicknameChecked.value='F'" placeholder="닉네임을 입력하세요">
	    		<button class="btn btn-warning" type="button" onclick="nicknameCheck(); ">중복확인</button>
	    		<input type="hidden" value="F" name="isNicknameChecked" id="isNicknameChecked"> <!-- 중복체크했는지 여부 확인 -->
			</div>
		</div>
		<font id="nicknameCheckMessage" size="4">&nbsp;</font>
		<!-- 닉네임 -->
		
		<!-- 연락처 -->
		<div class="form-group">
	      <label class="form-label mt-4 col-sm-2">휴대전화번호</label>
	      <input type="text" class="form-control col-sm-8" name="phone" id="phone" onkeyup="phoneCheck();" placeholder="- 없이 숫자만 입력해주세요" maxlength="11">
	    </div>
	    <font id="phoneCheckMessage" size="4">&nbsp;</font>
	    <!-- 연락처 -->
	    
	   	<!-- 이메일 -->
		<div class="form-group">
	      <label class="form-label mt-4 col-sm-2">이메일</label>
	      <div class=" d-flex">
	      <div class="col-sm-4"><input type="text" name="email1" id="email1" placeholder="이메일" maxlength="50"  onkeyup="emailCheck();" class="form-control d-inline"></div>
	      <div class="fs-3">@ </div>
		  <div class="col-sm-4"><input type="text" name="email2" id="email2" onkeyup="emailCheck();" class="form-control d-inline"></div>
		  <div class="col-sm"><select name="email" id="email" onchange="email_change()"  class="form-select d-inline">
					<option value="" disabled selected hidden>선택하세요</option>
				 	<option value="daum.net">daum.net</option>
					<option value="gmail.com">gmail.com</option>
					<option value="nate.com">nate.com</option>
				 	<option value="naver.com">naver.com</option>
				 	<option value="hanmail.net">hanmail.net</option>
				 	<option value="6">직접 입력</option>
				</select></div></div>
	    </div>
	    <font id="emailCheckMessage" size="4">&nbsp;</font>
	   	<!-- 이메일 -->
	   		    
	    <!-- 주소 -->
	   	<div class="form-group">
	      <label class="form-label mt-4 col-sm-2">주소</label>
	      <div class="form-floating">
		      <div class="form-group d-flex mb-2">
		      	<div class="col-sm-2 me-2"><input type="text" class="form-control col-sm-2 d-inline"  name="postcode" id="postcode" placeholder="우편번호" ></div>
		      	<input type="button" onclick="daumPostcode()" value="우편번호 찾기" class="btn btn-warning my-sm-0">
		      	</div>
		      <div class="form-group d-flex pe-2">
		      	<div class="col-sm-8"><input type="text" class="form-control d-inline me-2" name="address" id="address" placeholder="도로명주소" size=30></div>
		      	<input type="hidden" name="extraAddress" id="extraAddress" placeholder="참고항목">
		      	<div class="col-sm ms-1"><input type="text" class="form-control d-inline" name="detailAddress" id="detailAddress" placeholder="상세주소"></div>
		      </div>
	      </div>  
	    </div>
	    <font id="addressCheckMessage" size="4">&nbsp;</font>
	    <!-- 주소 -->
	    
	    <!-- 가입, 초기화 -->
	    <div class="mt-4 d-flex justify-content-end">
		<input type="submit" value="회원가입" class="btn btn-lg btn-success me-2">
		<input type="reset" value="초기화" class="btn btn-lg disable btn-secondary"></div>
		<!-- 가입, 초기화 -->    
	</div>
	</fieldset>
	</div>
</form>    
<div class="offcanvas-header"></div>
<div class="offcanvas-header"></div>
</body>
</html>
