<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Web App Prototype</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
    </style>
</head>
<body class="bg-white">

<!-- Header -->
<header class="bg-white shadow p-4 flex justify-between items-center max-w-[600px] overflow-x-auto mx-auto">
    <div class="text-lg font-bold" id="myAddress">로그인을 진행해주세요.</div>
    <div class="text-xl">❤️</div>
</header>

<!-- Main Content -->
<main class="p-4 space-y-4 max-w-[600px] overflow-x-auto mx-auto">
    <!-- Buttons -->
    <div class="flex space-x-4">
        <button class="flex-1 bg-blue-500 text-white py-2 rounded-lg shadow" onclick="window.location.href='/laundryshop-by-map'">내 주변 세탁소 찾기</button>
        <button class="flex-1 bg-blue-500 text-white py-2 rounded-lg shadow">이용방법</button>
    </div>

    <!-- Categories -->
    <div class="grid grid-cols-4 gap-4">
        <div class="flex flex-col items-center" onclick="window.location.href='/laundryshop-by-category/SHOES'">
            <div class="bg-gray-200 p-4 rounded-full">👟</div>
            <div class="mt-2">신발</div>
        </div>
        <div class="flex flex-col items-center" onclick="window.location.href='/laundryshop-by-category/PADDING'">
            <div class="bg-gray-200 p-4 rounded-full">🧥</div>
            <div class="mt-2">패딩</div>
        </div>
        <div class="flex flex-col items-center" onclick="window.location.href='/laundryshop-by-category/PREMIUM_FABRIC'">
            <div class="bg-gray-200 p-4 rounded-full">🤵</div>
            <div class="mt-2">프리미엄</div>
        </div>
        <div class="flex flex-col items-center" onclick="window.location.href='/laundryshop-by-category/CARRIER_SANITATION'">
            <div class="bg-gray-200 p-4 rounded-full">👗</div>
            <div class="mt-2">캐리어 소독</div>
        </div>
        <div class="flex flex-col items-center" onclick="window.location.href='/laundryshop-by-category/COTTON_LAUNDRY'">
            <div class="bg-gray-200 p-4 rounded-full">👖</div>
            <div class="mt-2">면 세탁물</div>
        </div>
        <div class="flex flex-col items-center" onclick="window.location.href='/laundryshop-by-category/BEDSTORAGE_SERVICEDING'">
            <div class="bg-gray-200 p-4 rounded-full">👕</div>
            <div class="mt-2">보관 서비스</div>
        </div>
        <div class="flex flex-col items-center" onclick="window.location.href='/laundryshop-by-category/SHBEDDINGOES'">
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
    <div class="flex justify-center">
        <button class="bg-gray-500 text-white py-2 px-4 rounded-lg shadow flex items-center space-x-2">
            <span>🧺</span>
            <span>세탁망 요청</span>
        </button>
    </div>
</main>

<!-- Footer -->
<footer class="fixed bottom-0 left-0 right-0 bg-white shadow p-4 flex justify-around overflow-x-auto mx-auto max-w-[600px] rounded-t-lg">
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
                document.getElementById('myAddress').innerHTML = string[0] + ' ' + string[1] + ' ' + string[2] + "...";
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
            }).catch(error => {
                alert(error.response.data);
                location.href = '/';
            });
        }

        window.onload = () => {
            checkAccessToken();
            getUserAddress();
        }
    </script>

</body>
</html>