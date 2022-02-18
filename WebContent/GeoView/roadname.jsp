<%@ include file="../Main/top.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<div class="container">
<input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색" class="btn btn-warning px-3 ms-2 mb-2"><br>
<input type="hidden" id="sample5_address" placeholder="검색결과">
<div id="map" style="width:600px;height:400px;"></div>
<div id="distance"></div>

<!-- 세션 확인용 폼 / 차후 삭제 요망 -->
<form method="post" action="./GeoMarkerAction.do" id="gpsForm">
<input type="hidden" name="latitude" id="latitude" value="" placeholder="위도"/>
<input type="hidden" name="longitude" id="longitude" value="" placeholder="경도"/>
</form>

</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8575e229b8545244290313ed12b9fc6b&libraries=services"></script>

<script>
var distanceGap  = document.getElementById("distance");


    var mapContainer = document.getElementById('map'), // 지도를 표시할 div
        mapOption = {
            center: new daum.maps.LatLng(35.85659647224101, 128.53115388420585), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨
        };

    //지도를 미리 생성
    var map = new daum.maps.Map(mapContainer, mapOption);
    //주소-좌표 변환 객체를 생성
    var geocoder = new daum.maps.services.Geocoder();
    //마커를 미리 생성
    var marker = new daum.maps.Marker({
        position: new daum.maps.LatLng(35.1507198, 129.059539),
        title: "마커1",
        map: map
    });

  
    //-----------------------------------------------------
    function sample5_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var addr = data.address; // 최종 주소 변수

                // 주소 정보를 해당 필드에 넣는다.
                document.getElementById("sample5_address").value = addr;
                // 주소로 상세 정보를 검색
                geocoder.addressSearch(data.address, function(results, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === daum.maps.services.Status.OK) {

                        var result = results[0]; //첫번째 결과의 값을 활용

                        // 해당 주소에 대한 좌표를 받아서
                        var coords = new daum.maps.LatLng(result.y, result.x);
 
                        //위 경도 출력
                        document.getElementById('latitude').value = result.y;
               		 	document.getElementById('longitude').value = result.x;
    
               		 	//출력확인
               		 	//alert(document.getElementById('find_lat').value);
               		 	//alert(document.getElementById('find_lng').value);
               		 
               		/* //페이지 넘기기
               		 var gpsForm = document.getElementById("gpsForm"); 
               		alert(gpsForm);
             		 gpsForm.submit();
             		 alert("안녕하세요");
             		 */
             		 
             		 location.href="./GeoMarkerAction.do?latitude="+result.y+"&longitude="+result.x;


                    }
                });
            }
        }).open();
    }
  
</script>

<!-- 폼을 script 상단에 올려주세요! (오류 잦음) -->
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div> 
 <div class="offcanvas-header"> <!-- top~body 사이 공백 -->　</div>
</body>
</html>