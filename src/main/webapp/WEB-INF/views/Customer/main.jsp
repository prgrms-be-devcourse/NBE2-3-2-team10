<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>워시팡</title>
    <link rel="icon" href="./upload/logo2.svg" type="image/x-icon">
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
</head>
    <style>
        @font-face {
            font-family: 'DXYeoksa'; /* 원하는 이름 */
            src: url('./fonts/dx1.ttf') format('truetype'); /* TTF 파일 경로 */
            font-weight: normal;
            font-style: normal;
        }

        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
    </style>
</head>
<body class="bg-white">

<!-- Header -->
<header class="bg-[#F9F9F9] px-4 pt-4 flex justify-between items-center max-w-[448px] overflow-x-auto mx-auto">
    <div class="text-[14px] font-bold" id="myAddress">로그인을 진행해주세요.</div>
    <div class="text-xl">
        <img src="./main/Heart.svg" class="h-[26px] w-[24px]"/>
    </div>
</header>

<!-- Main Content -->
<main class="p-4 space-y-4 bg-[#F9F9F9] max-w-[448px] overflow-x-auto mx-auto">
    <!-- Buttons -->
    <div class="flex space-x-4 font-bold" style="font-family: 'DXYeoksa', sans-serif;">
        <button class="bg-white text-black border border-[#E1D9D9] rounded-[28px] shadow-md flex justify-center items-center w-[195px] h-[81px]" onclick="window.location.href='/laundryshop-by-map'">
            <img src="./main/location-pin.svg" class="h-[46px] w-[32px] ml-8"/>
            <div class="ml-auto mr-8 text-[17px] text-right">
                <p>내 주변</p>
                <p>세탁소 찾기</p>
            </div>
        </button>
        <button class="bg-white text-black border border-[#E1D9D9] rounded-[28px] shadow-md flex justify-center items-center w-[195px] h-[81px]">
            <img src="./main/exclamation-mark.svg" class="h-[42.55px] w-[45px] ml-8"/>
            <div class="ml-auto mr-8 text-[18px] text-right">
                <p>이용방법</p>
            </div>
        </button>
    </div>

    <!-- Categories -->
    <div class="grid grid-cols-4 gap-4">
        <div class="flex flex-col items-center">
            <div class="bg-gray-200 p-4 rounded-full">👟</div>
            <div class="mt-2">신발</div>
        </div>
        <div class="flex flex-col items-center">
            <div class="bg-gray-200 p-4 rounded-full">🧥</div>
            <div class="mt-2">패딩</div>
        </div>
        <div class="flex flex-col items-center">
            <div class="bg-gray-200 p-4 rounded-full">🤵</div>
            <div class="mt-2">프리미엄</div>
        </div>
        <div class="flex flex-col items-center">
            <div class="bg-gray-200 p-4 rounded-full">🛋️</div>
            <div class="mt-2">블로킹</div>
        </div>
        <div class="flex flex-col items-center">
            <div class="bg-gray-200 p-4 rounded-full">👗</div>
            <div class="mt-2">캐리어 보관</div>
        </div>
        <div class="flex flex-col items-center">
            <div class="bg-gray-200 p-4 rounded-full">👖</div>
            <div class="mt-2">면 세탁물</div>
        </div>
        <div class="flex flex-col items-center">
            <div class="bg-gray-200 p-4 rounded-full">👕</div>
            <div class="mt-2">보관 서비스</div>
        </div>
        <div class="flex flex-col items-center">
            <div class="bg-gray-200 p-4 rounded-full">🛏️</div>
            <div class="mt-2">침구</div>
        </div>
    </div>

    <!-- Promo Section -->
    <div class="bg-white rounded-lg shadow overflow-hidden">
        <div class="p-4 bg-white text-black font-bold">Promo</div>
        <img src="./upload/mainImg.svg" alt="Promo" class="w-full">
        <div class="p-4 text-lg font-bold">세탁소 이용</div>
    </div>

    <!-- Request Button -->
    <div class="flex justify-center space-x-4 font-bold" style="font-family: 'DXYeoksa', sans-serif;">
        <button class="bg-white mr-auto text-black border border-[#E1D9D9] rounded-[28px] shadow-md justify-center items-center w-[197px] h-[189px]">
            <img src="./main/laundry-basket.svg" class="h-[90px] w-[97px] m-auto mb-3"/>
            <span class="m-auto text-[17px]">세탁망 요청</span>
        </button>
    </div>
</main>

<div class="m-[80px]"></div>

<!-- Footer -->
<footer class="fixed bottom-0 left-0 right-0 bg-white shadow p-4 flex justify-around overflow-x-auto mx-auto max-w-[448px] rounded-t-lg">
    <button class="flex flex-col items-center text-blue-500" onclick="location.href='/main'">
        <img src = "./footer/Home.svg" class = "h-6 w-6"/>
        <span class="text-black text-[10pt] mt-1">홈</span>
    </button>
    <button class="flex flex-col items-center text-gray-500" onclick="location.href='/orderHistory'" >
        <img src = "./footer/Bag.svg" class = "h-6 w-6"/>
        <span class="text-black text-[10pt] mt-1">주문내역</span>
    </button>
    <button class="flex flex-col items-center text-gray-500" onclick="location.href='/mypage'">
        <img src = "./footer/Star.svg" class = "h-6 w-6"/>
        <span class="text-black text-[10pt] mt-1">내 정보</span>
    </button>
</footer>

    <script>
        const url = "http://localhost:8080"
        const token = sessionStorage.getItem("accessToken");

        function getUserAddress() {
            axios.get(url + '/api/user/address', {
                headers: {
                    Authorization: 'Bearer ' + token
                }
            }).then(res => {
                const string = res.data.split(' ');
                document.getElementById('myAddress').innerHTML = string[0] + ' ' + string[1] + ' ' + string[2] + " " + "...";
                    //+ '<img src="./main/arrowDown.svg" class="h-[9px] w-[5px]"/>';

            }).catch(error => {
                alert(error.response.data);
            });
        }

        function checkAccessToken() {
            axios.post(url + '/api/user/check-login', {
                headers: {
                    Authorization: 'Bearer ' + token
                }
            }).then(res => {
                sessionStorage.setItem("accessToken", res.data.accessToken);
                getUserAddress();
            }).catch(error => {
                alert("로그인이 만료되었습니다. 다시 로그인해주세요.");
                location.href = '/';
            });
        }

        window.onload = () => {
            checkAccessToken();
        }
    </script>

</body>
</html>