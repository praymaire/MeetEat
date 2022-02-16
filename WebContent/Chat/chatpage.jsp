<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WebSocket Basic</title>
<%-- <c:if test="${empty sessionScope.id }">
	<script type="text/javascript">
		alert('로그인 후 이용가능합니다');
		history.back();
		window.close();
	</script>		
</c:if> --%>
<script>
	//	소켓 연결을 위해 websocket 객체를 만든다.	 서블릿의 형태로 접속을 한다.
	var websocket = new WebSocket("ws://localhost:8088/MeetEat/wsocket");
	
	var line = 0;
	
	//	아래는 웹 소켓에서 사용하는 4가지 이벤트들
	websocket.onopen = function(message){
	}
	websocket.onclose = function(message){}
	websocket.onerror = function(message){}
	//	onmessage는 외부에서 들어온 데이터를 받아서 처리를 하는 이벤트이다.
	websocket.onmessage = function(message)
	{
		//	메시지가 20개이면, 채팅창의 내용을 지운다.
		/* if (++line % 20 == 0){
			line = 0;
			document.getElementById("chat").value = "";
		} */
		
		document.getElementById("chat").value = 
			document.getElementById("chat").value + "\n" +message.data;
	}
	
	function sendmessage()
	{
		//	메시지 박스에 있는 데이터를 전송한다.
		msg = "[${id}]: " + document.getElementById("send").value;
		websocket.send(msg);
		
		//	메시지를 보내고, 메시지 입력 부분을 지워준다. 
		document.getElementById("send").value = "";
		/*
		//	나의 메시지
		document.getElementById("chat").value = 
			document.getElementById("chat").value + "\n"+msg; 
		*/
	}
	
	function keypress()
	{
		var keycode = event.keyCode;
		
		if (keycode == 13)
			sendmessage();
	}
	
</script>
</head>

<body bgcolor="#FFFFCC">
	
    <div>
    	<div>
	    	<div>[${id}]</div>
			<input id="send" type="text" size="30" onkeypress="keypress()">
			<input type="button" value="보내기" id="button" onclick=sendmessage()>
			<input type="button" value="나가기" onclick="window.close();">
		</div>
		<p></p>
		<div style="width: 30px; height: 100px;">
			<textarea id="chat" readonly cols="45" rows="23">
			
			</textarea>
		</div>
	</div>
</body>
</html>