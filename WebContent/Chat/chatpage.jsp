<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WebSocket Basic</title>
        <link href="css/bootstrap.css" rel="stylesheet" />
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
        <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
		<link href="https://webfontworld.github.io/Cafe24Oneprettynight/Cafe24Oneprettynight.css" rel="stylesheet">
<style>
*{
font-family: 'Cafe24Oneprettynight';
font-weight: bold;}

</style>
<% String bno=request.getParameter("bno"); %>
<script>
//소켓 연결을 위해 websocket 객체를 만든다.	 서블릿의 형태로 접속을 한다.
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

function exit(){
	websocket.send("============[${id}]님이 퇴장하였습니다===========");
	window.open('','_self').close(); 
}
function keypress()
{
	var keycode = event.keyCode;
	
	if (keycode == 13)
		sendmessage();
}

</script>
</head>

<body class="bg-warning">
 <div class="container">
	<div>내정보:${id} | 글번호:<%=bno %></div>
	
		<textarea id="chat" readonly cols="58" rows="15" style=" border-radius: 0px!important;  resize: none;"></textarea>

	<div>
		<input id="send" type="text" size="44" onkeypress="keypress();" class="p-1">
		<button onclick="sendmessage();" class="btn btn-sm btn-success"><i class="fas fa-paper-plane"></i></button>
		<button onclick="exit();" class="btn btn-sm btn-danger"><i class="fas fa-times-circle"></i></button>
	</div>
  </div>
</body>
</html>