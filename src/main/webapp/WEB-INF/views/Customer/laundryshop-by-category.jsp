<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>패딩 페이지</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
    </style>
</head>
<body class="bg-gray-100">
<div class="max-w-md mx-auto bg-white shadow-lg rounded-lg overflow-hidden">
    <div class="flex items-center justify-between p-4">
        <button class="text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
            </svg>
        </button>
        <h1 class="text-lg font-bold">패딩</h1>
        <button class="text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h18M3 12h18m-9 9h9" />
            </svg>
        </button>
    </div>
    <div class="flex space-x-2 px-4">
        <button class="bg-gray-200 text-gray-700 px-3 py-1 rounded-full">패딩</button>
        <button class="bg-gray-200 text-gray-700 px-3 py-1 rounded-full">신발세탁</button>
        <button class="bg-gray-200 text-gray-700 px-3 py-1 rounded-full">가죽제품</button>
        <button class="bg-gray-200 text-gray-700 px-3 py-1 rounded-full">커튼/카페트</button>
        <button class="bg-gray-200 text-gray-700 px-3 py-1 rounded-full">침구</button>
    </div>
    <div class="p-4">
        <img src="https://source.unsplash.com/random/800x400?jacket" alt="Jacket" class="w-full h-48 object-cover rounded-lg">
    </div>
    <div class="p-4 space-y-4">
        <div class="bg-white p-4 rounded-lg shadow">
            <div class="flex justify-between items-center">
                <div>
                    <h2 class="text-lg font-bold">세탁소명 <span class="text-yellow-500">★BEST</span></h2>
                    <p class="text-gray-500">서울 잠실 ○○아파트</p>
                </div>
                <img src="https://source.unsplash.com/random/100x100?blanket" alt="Product" class="w-16 h-16 object-cover rounded-lg">
            </div>
            <div class="mt-2">
                <span class="text-green-500 font-bold">25%</span>
                <span class="text-xl font-bold">35,600원</span>
                <span class="text-gray-500">/4주</span>
            </div>
        </div>
        <div class="bg-white p-4 rounded-lg shadow">
            <div class="flex justify-between items-center">
                <div>
                    <h2 class="text-lg font-bold">세탁소명 <span class="text-yellow-500">★BEST</span></h2>
                    <p class="text-gray-500">서울 잠실 ○○아파트</p>
                </div>
                <img src="https://source.unsplash.com/random/100x100?blanket" alt="Product" class="w-16 h-16 object-cover rounded-lg">
            </div>
            <div class="mt-2">
                <span class="text-green-500 font-bold">25%</span>
                <span class="text-xl font-bold">35,600원</span>
                <span class="text-gray-500">/4주</span>
            </div>
        </div>
        <div class="bg-white p-4 rounded-lg shadow">
            <div class="flex justify-between items-center">
                <div>
                    <h2 class="text-lg font-bold">세탁소명 <span class="text-yellow-500">★BEST</span></h2>
                    <p class="text-gray-500">서울 잠실 ○○아파트</p>
                </div>
                <img src="https://source.unsplash.com/random/100x100?blanket" alt="Product" class="w-16 h-16 object-cover rounded-lg">
            </div>
            <div class="mt-2">
                <span class="text-green-500 font-bold">25%</span>
                <span class="text-xl font-bold">35,600원</span>
                <span class="text-gray-500">/4주</span>
            </div>
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