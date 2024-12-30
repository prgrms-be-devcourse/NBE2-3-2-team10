<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Washpang</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
    </style>
</head>
<body class="bg-gray-100">
<div class="max-w-md mx-auto bg-white shadow-md rounded-lg overflow-hidden mt-4">
    <div class="relative">
        <img src="https://source.unsplash.com/featured/?laundry" alt="Laundry" class="w-full h-48 object-cover">
        <div class="absolute inset-0 bg-black opacity-50"></div>
        <div class="absolute inset-0 flex items-center justify-center">
            <h1 class="text-white text-xl font-bold">워시팡,<br>6단계로 세탁 없는 일상 보내기</h1>
        </div>
    </div>
    <div class="p-4">
        <div class="mb-4">
            <div class="bg-gray-200 h-24 rounded-lg mb-2"></div>
            <h2 class="text-lg font-bold">STEP 1. 내게 맞는 세탁소 찾기</h2>
            <p class="text-gray-600">홈 화면의 '세탁소 찾기' 눌러 근처의 이용 가능한 세탁소 리스트를 확인할 수 있어요</p>
        </div>
        <div>
            <div class="bg-gray-200 h-24 rounded-lg mb-2"></div>
            <h2 class="text-lg font-bold">STEP 2. 취급 상품과 요금 확인하기</h2>
            <p class="text-gray-600">세탁소마다 취급하는 상품과 요금이 달라요. 수거 신청 전 꼭 확인해주세요</p>
        </div>
    </div>
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
</div>
</body>
</html>