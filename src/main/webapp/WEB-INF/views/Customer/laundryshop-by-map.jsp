<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Map Prototype</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
        }
    </style>
</head>
<body class="bg-gray-100">

<div class="max-w-md mx-auto bg-white shadow-md rounded-lg overflow-hidden">
    <div class="p-4">
        <input type="text" placeholder="세탁소를 검색하세요" class="w-full p-2 border rounded-lg">
    </div>
    <div class="relative">
        <div id="map" style="width:400px;height:300px;"></div>
        <script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=ffeefd8246bf28331ea26a9ff648525c&libraries=services"></script>
        <script>
            var infowindow = new kakao.maps.InfoWindow({zIndex:1});

            var container = document.getElementById('map');
            var options = {
                center: new kakao.maps.LatLng(33.450701, 126.570667),
                level: 5
            };

            var map = new kakao.maps.Map(container, options);

            if (navigator.geolocation) {

                // GeoLocation을 이용해서 접속 위치를 얻어옵니다
                navigator.geolocation.getCurrentPosition(function(position) {

                    var lat = position.coords.latitude, // 위도
                        lon = position.coords.longitude; // 경도

                    var locPosition = new kakao.maps.LatLng(lat, lon);
                    map.setCenter(locPosition);


                    var laundry_lat = 37.270351,
                        laundry_lon = 127.040352;

                    var laundryPosition = new kakao.maps.LatLng(laundry_lat, laundry_lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
                        message = '<div style="padding:5px;">월드크리닝매탄힐스테이트</div>'; // 인포윈도우에 표시될 내용입니다

                    // 마커와 인포윈도우를 표시합니다
                    displayMarker(laundryPosition, message);

                    // 지도 중심좌표를 접속위치로 변경합니다


                });

            } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

                var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
                    message = 'geolocation을 사용할수 없어요..'

                displayMarker(locPosition, message);
                //searchPlaces();
            }

            // 지도에 마커와 인포윈도우를 표시하는 함수입니다
            function displayMarker(locPosition, message) {

                // 마커를 생성합니다
                var marker = new kakao.maps.Marker({
                    map: map,
                    position: locPosition
                });

                // 마커에 클릭이벤트를 등록합니다
                kakao.maps.event.addListener(marker, 'click', function() {
                    // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
                    infowindow.setContent('<div style="padding:5px;font-size:12px;">' + message + '</div>');
                    infowindow.open(map, marker);
                })


            }
        </script>
    </div>
    <div class="flex justify-around bg-white p-4 border-t fixed bottom-0 w-full max-w-md">
        <button class="flex flex-col items-center text-blue-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-10 0a1 1 0 001-1V10m0 0l7-7 7 7" />
            </svg>
            <span>홈</span>
        </button>
        <button class="flex flex-col items-center text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 7h18M3 12h18m-9 5h9" />
            </svg>
            <span>주문내역</span>
        </button>
        <button class="flex flex-col items-center text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5.121 17.804A1 1 0 014 17V7a1 1 0 011.121-.804l7 2.5a1 1 0 01.879.804l7 2.5A1 1 0 0120 13v10a1 1 0 01-1.121.804l-7-2.5a1 1 0 01-.879-.804l-7-2.5z" />
            </svg>
            <span>마이</span>
        </button>
    </div>
</div>

</body>
</html>