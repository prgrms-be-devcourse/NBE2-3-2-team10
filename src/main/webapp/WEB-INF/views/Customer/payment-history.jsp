<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>거래 내역</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
    </style>
</head>
<body class="bg-gray-100">
<!-- 상단 헤더 -->
<div class="max-w-md mx-auto bg-white shadow-md rounded-lg mt-0">
    <div class="flex items-center justify-between p-4 border-b">
        <button class="text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
            </svg>
        </button>
        <h1 class="text-lg font-bold">거래 내역</h1>
        <div></div>
    </div>

    <!-- 필터 섹션 -->
    <div class="flex justify-around text-blue-500 py-2 border-b">
        <span class="cursor-pointer font-medium">1개월</span>
        <span class="cursor-pointer font-medium">3개월</span>
        <span class="cursor-pointer font-medium">6개월</span>
    </div>

    <!-- 거래 내역 리스트 -->
    <div class="p-4">
        <div class="bg-gray-50 p-4 rounded-lg shadow mb-4">
            <div class="flex justify-between items-center">
                <span class="text-gray-700">2024년 12월 13일 (금)</span>
                <span class="text-blue-500 font-medium">99,999원</span>
            </div>
            <p class="text-gray-500 mt-1">세탁짱 강남점</p>
        </div>

        <div class="bg-gray-50 p-4 rounded-lg shadow mb-4">
            <div class="flex justify-between items-center">
                <span class="text-gray-700">2024년 12월 14일 (토)</span>
                <span class="text-blue-500 font-medium">130,000원</span>
            </div>
            <p class="text-gray-500 mt-1">워시팡 서비스</p>
        </div>

        <div class="bg-gray-50 p-4 rounded-lg shadow mb-4">
            <div class="flex justify-between items-center">
                <span class="text-gray-700">2024년 12월 15일 (일)</span>
                <span class="text-blue-500 font-medium">25,000원</span>
            </div>
            <p class="text-gray-500 mt-1">세탁짱 수거배송</p>
        </div>
    </div>
</div>

<!-- 하단 네비게이션 바 -->
<div class="fixed bottom-0 left-0 right-0 bg-white shadow-md">
    <div class="flex justify-around py-2">
        <button class="flex flex-col items-center text-blue-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12h18M3 6h18M3 18h18" />
            </svg>
            <span class="text-xs font-medium">홈</span>
        </button>
        <button class="flex flex-col items-center text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
            </svg>
            <span class="text-xs font-medium">주문내역</span>
        </button>
        <button class="flex flex-col items-center text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3" />
            </svg>
            <span class="text-xs font-medium">마이페이지</span>
        </button>
    </div>
</div>
</body>
</html>