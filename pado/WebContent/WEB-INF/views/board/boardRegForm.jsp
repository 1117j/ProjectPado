<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 등록 폼</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9b554607ceeb060d931e9eedfa0d54dc&libraries=services"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/default.css">
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>
 	<div class="board">
		<form action="boardReg.do" method="post" enctype="multipart/form-data">
			<table>
				<tr>
					<td><input type="hidden" value="${member.mid}" name="mid"></td>
					<td><textarea rows="10" cols="50" name="bmessage" id="bmessage"></textarea></td>
					<td><input type="file" name="bphoto" id="bphoto"></td>
					<td><div id="map" style="width:100%;height:500px;"></div></td>
					<td> <input type="text" name="baddr"id="sample5_address" placeholder="주소" value="addr.value"></td>
					
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="글쓰기"><input
						type="reset" value="전체삭제"></td>
				</tr>
			</table>
		</form>
	</div> 


    <input type="text" id="sample5_address" placeholder="주소">
    <input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색"><br>
    <div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>

    
 


</body>
</html>
    <script>
        var mapContainer = document.getElementById('map'), // 지도를 표시할 div
            mapOption = {
                center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
                level: 5 // 지도의 확대 레벨
            };

        //지도를 미리 생성
        var map = new daum.maps.Map(mapContainer, mapOption);
        //주소-좌표 변환 객체를 생성
        var geocoder = new daum.maps.services.Geocoder();
        //마커를 미리 생성
        var marker = new daum.maps.Marker({
            position: new daum.maps.LatLng(37.537187, 127.005476),
            map: map}),

        infowindow = new kakao.maps.InfoWindow({zindex:1}); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다




        function sample5_execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function (data) {
                    var addr = data.address; // 최종 주소 변수

                    // 주소 정보를 해당 필드에 넣는다.
                    document.getElementById("sample5_address").value = addr;
                    // 주소로 상세 정보를 검색
                    geocoder.addressSearch(data.address, function (results, status) {
                        // 정상적으로 검색이 완료됐으면
                        if (status === daum.maps.services.Status.OK) {

                            var result = results[0]; //첫번째 결과의 값을 활용

                            // 해당 주소에 대한 좌표를 받아서
                            var coords = new daum.maps.LatLng(result.y, result.x);
                            // 지도를 보여준다.
                            mapContainer.style.display = "block";
                            map.relayout();
                            // 지도 중심을 변경한다.
                            map.setCenter(coords);
                            // 마커를 결과값으로 받은 위치로 옮긴다.
                            marker.setPosition(coords)
                            
                            
                            
                            // 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시합니다
                            searchAddrFromCoords(map.getCenter(), displayCenterInfo);

                            // 지도를 클릭했을 때 클릭 위치 좌표에 대한 주소정보를 표시하도록 이벤트를 등록합니다
                            kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
                                searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
                                    if (status === kakao.maps.services.Status.OK) {
                                        var detailAddr = !!result[0].road_address ? '<div>도로명주소 : ' + result[0].road_address.address_name + '</div>' : '';
                                        detailAddr += '<div>지번 주소 : ' + result[0].address.address_name + '</div>';
                                        
                                        	console.log(detailAddr.innerHTML);
                                        
                                        var content = '<div class="bAddr">' +
                                                        '<span class="title">주소정보</span>' + 
                                                        detailAddr +'<input type="submit" id="submitAddress" value="선택하기">'+
                                                    '</div>'
                                                    
                                                    ;
                                        // 마커를 클릭한 위치에 표시합니다 
                                        marker.setPosition(mouseEvent.latLng);
                                        marker.setMap(map);

                                        // 인포윈도우에 클릭한 위치에 대한 법정동 상세 주소정보를 표시합니다
                                        infowindow.setContent(content);
                                        infowindow.open(map, marker); 
                                    }   
                                });
                            });
                            
                            
                            
                        }
                    });
                }
            }).open();
        }
        

        // 중심 좌표나 확대 수준이 변경됐을 때 지도 중심 좌표에 대한 주소 정보를 표시하도록 이벤트를 등록합니다
        kakao.maps.event.addListener(map, 'idle', function() {
            searchAddrFromCoords(map.getCenter(), displayCenterInfo);
        });

        function searchAddrFromCoords(coords, callback) {
            // 좌표로 행정동 주소 정보를 요청합니다
            geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);         
        }

        function searchDetailAddrFromCoords(coords, callback) {
            // 좌표로 법정동 상세 주소 정보를 요청합니다
            geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
        }

        // 지도 좌측상단에 지도 중심좌표에 대한 주소정보를 표출하는 함수입니다
        function displayCenterInfo(result, status) {
            if (status === kakao.maps.services.Status.OK) {
                var infoDiv = document.getElementById('centerAddr');
                for(var i = 0; i < result.length; i++) {
                    // 행정동의 region_type 값은 'H' 이므로
                    if (result[i].region_type === 'H') {
                        infoDiv.innerHTML = result[i].address_name;
                        break;
                    }
                }
            }    
        }

        
    </script>