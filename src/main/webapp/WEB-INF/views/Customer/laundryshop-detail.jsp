<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>세탁소 명</title>
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
        <h1 class="text-lg font-bold">세탁소 명</h1>
        <button class="text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h18M3 12h18m-9 9h9" />
            </svg>
        </button>
    </div>
    <div class="p-4">
        <div class="bg-white p-4 rounded-lg shadow">
            <h2 class="text-xl font-bold">${laundry.shop_name}</h2>
            <p class="text-gray-500">${laundry.address}</p>
            <p class="text-gray-500">전화 <span role="img" aria-label="phone">📞 ${laundry.phone}</span></p>
            <p class="text-gray-500">영업: ${laundry.non_operating_days} <span class="text-red-500">휴무</span></p>
            <div class="flex space-x-4 mt-4">
                <img src="https://source.unsplash.com/random/50x50?jacket" alt="패딩" class="w-12 h-12">
                <img src="https://source.unsplash.com/random/50x50?suit" alt="프리미엄" class="w-12 h-12">
                <img src="https://source.unsplash.com/random/50x50?shirt" alt="민 세탁물" class="w-12 h-12">
                <img src="https://source.unsplash.com/random/50x50?shoes" alt="신발" class="w-12 h-12">
            </div>
            <button class="mt-4 w-full bg-blue-500 text-white py-2 rounded-lg">세탁 신청</button>
        </div>
        <div class="mt-4 bg-blue-100 p-4 rounded-lg shadow">
            <p class="text-gray-700"><strong>1234님</strong></p>
            <p class="text-gray-700">되게 깨끗하게 잘 해주셨어요. 정말 완전 만족해요 ㅎㅎ</p>
        </div>
        <div class="mt-2 bg-gray-200 p-4 rounded-lg shadow">
            <p class="text-gray-700"><strong>1234님</strong>, 만족하셨다니 다행이에요!</p>
        </div>
    </div>
    <div class="fixed bottom-0 left-0 right-0 flex justify-around bg-white p-4 border-t">
        <button class="flex flex-col items-center text-blue-500" onclick="window.location.href='/main'">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12h18m-9 9h9" />
            </svg>
            <span>홈</span>
        </button>
        <button class="flex flex-col items-center text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h18M3 12h18m-9 9h9" />
            </svg>
            <span>주문내역</span>
        </button>
        <button class="flex flex-col items-center text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h18M3 12h18m-9 9h9" />
            </svg>
            <span>마이</span>
        </button>
    </div>
</div>
</body>
</html>