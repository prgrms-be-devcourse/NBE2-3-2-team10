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


    <script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=ffeefd8246bf28331ea26a9ff648525c&libraries=services"></script>
    <script>
        window.onload = function () {
            var infowindow = new kakao.maps.InfoWindow({zIndex: 1});
            var lat;
            var lon;

            var container = document.getElementById('map');
            var options = {
                center: new kakao.maps.LatLng(33.450701, 126.570667),
                level: 5
            };

            var map = new kakao.maps.Map(container, options);

            if (navigator.geolocation) {

                // GeoLocation을 이용해서 접속 위치를 얻어옵니다
                navigator.geolocation.getCurrentPosition(function (position) {

                    lat = position.coords.latitude, // 위도
                    lon = position.coords.longitude; // 경도

                    var locPosition = new kakao.maps.LatLng(lat, lon);
                    mylocation(locPosition);
                    map.setCenter(locPosition);

                    // 초기 데이터 로드: 모든 세탁소를 로드
                    loadLaundryShops();

                });

            } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

                var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),
                    message = 'geolocation을 사용할수 없어요..'

                displayMarker(locPosition, message);
                //searchPlaces();
            }


            let markers = [];

            // 검색 입력 필드에서 변화가 있을 때마다 호출
            document.getElementById('searchLaundry').addEventListener('input', function (event) {
                const searchTerm = event.target.value;  // 검색어 가져오기
                loadLaundryShops(searchTerm);  // 검색어를 API에 전달
            });

            // 세탁소 데이터를 가져와서 지도에 마커 표시
            function loadLaundryShops(searchTerm = '') {
                fetch(`/api/laundry/map?shop_name=\${searchTerm}&userLat=\${lat}&userLng=\${lon}`)
                    .then(response => response.json())
                    .then(data => {
                        console.log(data);  // 디버깅용: 데이터 확인

                        clearMarkers();

                        let result = '';
                        data.forEach(shop => {
                            var position = new kakao.maps.LatLng(shop.latitude, shop.longitude);
                            var message = `<div style="padding:5px;">\${shop.shop_name}</div>`;
                            displayMarker(position, message);

                            result += `<li class="border-b pb-2 shop-item" data-id="\${shop.id}">
                                       \${shop.shop_name}
                                       </li>`;
                        });
                        document.getElementById('laundryList').innerHTML = result;

                        document.querySelectorAll('.shop-item').forEach(item => {
                            item.addEventListener('click', function() {
                                const shopId = this.getAttribute('data-id');
                                window.location.href = `/laundryshop-detail/\${shopId}`;
                            });
                        });
                    })
                    .catch(error => console.error('Error fetching data:', error));
            }

            function clearMarkers() {
                markers.forEach(marker => marker.setMap(null)); // 지도에서 모든 마커 삭제
                markers = []; // 배열 초기화
            }


            // 지도에 마커와 인포윈도우를 표시하는 함수입니다
            function displayMarker(locPosition, message) {

                // 마커를 생성합니다
                var marker = new kakao.maps.Marker({
                    map: map,
                    position: locPosition
                });

                // 마커에 클릭이벤트를 등록합니다
                kakao.maps.event.addListener(marker, 'click', function () {
                    // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
                    infowindow.setContent('<div style="padding:5px;font-size:12px;">' + message + '</div>');
                    infowindow.open(map, marker);
                })

                markers.push(marker);
            }

            function mylocation(location) {
                var imageSrc = './images/nowlocation.png', // 마커이미지의 주소입니다
                    imageSize = new kakao.maps.Size(30, 30), // 마커이미지의 크기입니다
                    imageOption = {offset: new kakao.maps.Point(15, 15)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.

                // 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
                var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
                //markerPosition = location; // 마커가 표시될 위치입니다

                // 마커를 생성합니다
                var marker = new kakao.maps.Marker({
                    position: location,
                    image: markerImage // 마커이미지 설정
                });

                marker.setMap(map);
            }
        }
    </script>
</head>
<body class="bg-gray-100">

<div class="max-w-md mx-auto bg-white shadow-md rounded-lg overflow-hidden">
    <div class="p-4">
        <input type="text" placeholder="세탁소를 검색하세요" class="w-full p-2 border rounded-lg" id="searchLaundry">
    </div>

    <div class="relative" style="height: calc(100vh - 64px - 60px);">
        <div class="w-full  h-1/2 object-cover" id="map" style="z-index: 0; position: absolute;"></div>
        <div class="absolute top-2 right-2 bg-white p-2 rounded-full shadow-md">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-gray-600" fill="none"
                     viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                          d="M12 11c0 1.104-.896 2-2 2s-2-.896-2-2 .896-2 2-2 2 .896 2 2zM12 11c0 1.104.896 2 2 2s2-.896 2-2-.896-2-2-2-2 .896-2 2zM12 11v10m0-10c0-1.104.896-2 2-2s2 .896 2 2-.896 2-2 2-2-.896-2-2zM12 11c0-1.104-.896-2-2-2s-2 .896-2 2 .896 2 2 2 2-.896 2-2z" />
            </svg>
        </div>


        <!-- Bottom Sheet -->
        <div id="bottomSheet"
             class="absolute left-0 w-full bg-white rounded-t-2xl transition-all duration-300 ease-in-out bottom-sheet-collapsed"
             style="bottom:60px; height: 40%; box-shadow: none;">
            <div class="flex justify-center p-2 cursor-pointer" id="toggleHandle">
                <div class="w-12 h-1 bg-gray-400 rounded"></div>
            </div>
            <div class="p-4 overflow-auto h-full" >
                <ul class="space-y-4" id="laundryList">
                    <%-- 세탁소 리스트 --%>
                </ul>
            </div>
        </div>
    </div>
    <div class="flex justify-around bg-white p-4 border-t fixed bottom-0 w-full max-w-md">
        <button class="flex flex-col items-center text-blue-500" onclick="window.location.href='/main'">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-10 0a1 1 0 001-1V10m0 0l7-7 7 7" />
            </svg>
            <span>홈</span>
        </button>
        <button class="flex flex-col items-center text-gray-500" onclick="window.location.href='/orderHistory'">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 7h18M3 12h18m-9 5h9" />
            </svg>
            <span>주문내역</span>
        </button>
        <button class="flex flex-col items-center text-gray-500" onclick="window.location.href='/mypage'">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5.121 17.804A1 1 0 014 17V7a1 1 0 011.121-.804l7 2.5a1 1 0 01.879.804l7 2.5A1 1 0 0120 13v10a1 1 0 01-1.121.804l-7-2.5a1 1 0 01-.879-.804l-7-2.5z" />
            </svg>
            <span>마이</span>
        </button>
    </div>
</div>

</body>
</html>