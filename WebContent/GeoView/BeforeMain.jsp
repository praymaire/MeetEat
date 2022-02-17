<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main page</title>
<%     //세션정리(테스트용, 아이디 세션 유지를 위해 삭제 가능)
       session.invalidate();
%>
<button id="getLocation" type="button">위치 정보 수집</button>



<script type="text/javascript">
/*위치정보 처리 */
	if (!navigator.geolocation) {
		alert("지오로케이션을 지원하지 않습니다!");
		document.getElementById('latitude').value = "37.566824";
		document.getElementById('longitude').value= "126.978522";
		var gpsForm = document.getElementById("gpsForm");
		gpsForm.submit();
	}
	function success(position) {
		var latitude = position.coords.latitude;
		var longitude = position.coords.longitude;
		
		document.getElementById('latitude').value = latitude;
		document.getElementById('longitude').value= longitude;
		//var gpsForm = document.getElementById("gpsForm"); 
		 location.href="./GeoMarkerAction.do?latitude="+latitude+"&longitude="+longitude;
		
		//gpsForm.submit();
	};
	function error() {
		alert("사용자의 위치를 찾을 수 없습니다! 수동검색 페이지로 이동합니다.");
		location.href="./roadname.do";		
	};
	document.getElementById("getLocation").onclick = function () {

	
	navigator.geolocation.getCurrentPosition(success, error);
	
	};
</script>



</head>
<body>

<h1>BeforeMain 페이지 - (위치정보 적용 전)</h1>


<h2>위치정보 수집</h2>
<h3>위치정보를 자동 - GeoMarkerAction.do / 수동 - roadname.do 으로 넘겨줌</h3>

<form method="post" action="./GeoMarkerAction.do" id="gpsForm"> 
	<input 	type = "hidden" id = "latitude" name = "latitude" value = "">
	<input type = "hidden" id = "longitude" name = "longitude" value = "">
</form>






</body>
</html>