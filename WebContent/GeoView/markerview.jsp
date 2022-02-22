<%@ include file="../Main/top.jsp" %>

<%@page import="com.geo.db.GeoDTO"%>
<%@page import="com.geo.db.GeoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8575e229b8545244290313ed12b9fc6b"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8575e229b8545244290313ed12b9fc6b&libraries=services"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<div class="container">
<h5 id="loc"> 현재위치 : </h5>
<h5 id="result">검색결과 : </h5>

<% //세션제어
String latitude =(String)session.getAttribute("latitude");
String longitude =(String)session.getAttribute("longitude");

if(latitude == null){
	System.out.println("안녕하세요 markerview 입니다. 위치정보를 수령하지 못했습니다. 12345678913");
	//메인페이지로 돌려보내기
	//forward.setPath("./BeforeMain.do");
	//forward.setRedirect(true);
	//return forward;
};



//이 좌표를 AfterMain으로 전달한다.
%>
<button id="getLocation" type="button" class="btn btn-success col-3">현재 정보로 주변 검색</button>
<input type="button" value="위치 재설정하기" onclick="location.href='./roadname.do';" class="btn btn-warning col-3">
<div id="map" style="width:100%;height:500px;" class="mt-2"></div>
<div id="coordXY"></div>
<div id="distance"></div>
</div>

<script type="text/javascript" >
var mapContainer = document.getElementById("map");
var coordXY   = document.getElementById("coordXY");
var distanceGap  = document.getElementById("distance");



var x = <%=latitude%>;    // 현재 내 GPS x좌표
var y = <%=longitude%>;  // 현재 내 GPS y좌표
var radius = 100;        // 반경 미터(100m) 표시


var geocoder = new kakao.maps.services.Geocoder();

var coord = new kakao.maps.LatLng(x,y);
var callback = function(result, status) {
    if (status === kakao.maps.services.Status.OK) {
    	$(document).ready(function(){
    		 $("#loc").append(result[0].address.address_name );
    	});
    } 

};

geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);


var latlngyo = new kakao.maps.LatLng(x, y);
var mapOption = {
  center: latlngyo, // 지도의 중심좌표
        level: 3     // 지도의 확대 레벨
};


document.getElementById("getLocation").onclick = function () {
// 내 위치 좌표 넘겨주기 , GeoListAction으로 이동
	location.href="./GeoListAction.do?x="+x+"&y="+y;	
};


var map = new kakao.maps.Map(mapContainer, mapOption); //지도생성

var circle = new daum.maps.Circle({
    map: map,
    center : latlngyo,
    radius: radius,
    strokeWeight: 2,
    strokeColor: '#FF00FF',
    strokeOpacity: 0.8,
    strokeStyle: 'dashed',
    fillColor: '#D3D5BF',
    fillOpacity: 0.5
});


var marker = new daum.maps.Marker({
 position: latlngyo, // 마커의 좌표
 title: "마커1",
 map: map          // 마커를 표시할 지도 객체
});



<%
// DB안에 있는데이터 저장
ArrayList geomList = (ArrayList)request.getAttribute("geomList");
%>

var me = new Array();
<c:forEach var="dto" items="${geomList }" varStatus="status">
me[${status.index}] = ["사용자", ${dto.latitude }, ${dto.longitude }];
</c:forEach> 


var arr = new Array();
 for(var t=0; t<me.length;t++){	
	arr[t] = [me[t][0]+(t+1),me[t][1],me[t][2]];
 }; 


//---------------------------------------마커 표현
var markerTmp;      // 마커
var polyLineTmp;    // 두지점간 직선거리
var distanceArr = new Array();
var distanceStr = "";
var walkkTime = 0;
var walkHour = "", walkMin = "";



for (var i=0;i<arr.length;i++) {


    markerTmp = new daum.maps.Marker({
        position: new daum.maps.LatLng(arr[i][1],arr[i][2]),
        title: arr[i][0],
        map:map
    });



    polyLineTmp = new daum.maps.Polyline({
        map: map,
        path: [
            latlngyo, new daum.maps.LatLng(arr[i][1],arr[i][2])
              ],
        strokeWeight: 2,   
        strokeColor: '#FF00FF',
        strokeOpacity: 0.8,
        strokeStyle: 'dashed'
      });


    distance[i] = polyLineTmp.getLength();  // 도보의 시속은 평균 4km/h 이고 도보의 분속은 67m/min입니다  -> 40 변경

 // 계산한 도보 시간이 60분 보다 크면 시간으로 표시
    walkkTime = distance[i] / 40;   
    if (walkkTime > 60) {
        walkHour = Math.floor(walkkTime / 60) + " 시간 ";
    }   
    walkMin = parseInt(walkkTime % 60) + " 분";
    
 distanceStr += arr[i][0] + " : "  + Math.floor(distance[i]) + " m 거리 ,  도보 : "+ walkHour + walkMin +"<br>"; 
	

    walkkTime = 0;
    walkHour = "";
    walkMin = "";
}

 /* coordXY.innerHTML = "<br>현재 GPS X좌표 : " + x + " , Y좌표 : " + y + " ( 100m 반경 )<br><br>"; */
distanceGap.innerHTML = distanceStr;
$(document).ready(function(){
	 $("#result").append(i+"건" );
});

/* var sessionData = distanceStr; 
sessionStorage.setItem("distanceStr", sessionData ); // 저장
 */

</script>

 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div> 
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
</body>
</html>