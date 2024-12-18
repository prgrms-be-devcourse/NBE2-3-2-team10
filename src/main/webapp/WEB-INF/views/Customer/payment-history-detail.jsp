<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>결제 상세 내역</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
    </style>
</head>
<body class="bg-gray-100">
<div class="max-w-md mx-auto bg-white shadow-md rounded-lg mt-0">
    <div class="flex items-center justify-between p-4 border-b">
        <button class="text-gray-500">&larr;</button>
        <h1 class="text-lg font-bold">결제 상세 내역</h1>
        <div></div>
    </div>
    <div class="p-4">
        <div class="bg-gray-50 p-4 rounded-lg">
            <p class="text-gray-700">오현식 님</p>
            <p class="text-2xl font-bold text-blue-500">130,000원</p>
            <p class="text-gray-700">입니다.</p>
        </div>
        <div class="mt-4">
            <div class="flex justify-between py-2 border-b">
                <span class="text-gray-600">결제일</span>
                <span class="text-gray-800">12월 13일 (금)</span>
            </div>
            <div class="flex justify-between py-2 border-b">
                <span class="text-gray-600">이용 서비스</span>
                <span class="text-gray-800">단건 서비스</span>
            </div>
            <div class="flex justify-between py-2 border-b">
                <span class="text-gray-600">이용 업체</span>
                <span class="text-gray-800">세탁짱 강남점</span>
            </div>
        </div>
        <div class="mt-4">
            <h2 class="text-gray-700 font-bold">세부 항목</h2>
            <div class="flex justify-between py-2 border-b">
                <span class="text-gray-600">일반 이불</span>
                <span class="text-gray-800">1</span>
                <span class="text-gray-800">25,000원</span>
            </div>
            <div class="flex justify-between py-2 border-b">
                <span class="text-gray-600">패딩</span>
                <span class="text-gray-800">1</span>
                <span class="text-gray-800">25,000원</span>
            </div>
            <div class="flex justify-between py-2 border-b">
                <span class="text-gray-600">수거배송</span>
                <span class="text-gray-800">1</span>
                <span class="text-gray-800">25,000원</span>
            </div>
        </div>
        <div class="flex justify-between py-4 mt-4 border-t">
            <span class="text-gray-700 font-bold">총 결제금액</span>
            <span class="text-2xl font-bold text-blue-500">130,000원</span>
        </div>
    </div>

</div>

<div class="fixed bottom-0 left-0 right-0 bg-white shadow-md">
    <div class="flex justify-around py-2">
        <button class="flex flex-col items-center text-blue-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M13 5v6h6m-6 0l-7 7-2-2m0 0l-7-7 7-7" />
            </svg>
            <span>홈</span>
        </button>
        <button class="flex flex-col items-center text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
            </svg>
            <span>주문내역</span>
        </button>
        <button class="flex flex-col items-center text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 11c0 1.104-.896 2-2 2s-2-.896-2-2 .896-2 2-2 2 .896 2 2zm0 0c0 1.104.896 2 2 2s2-.896 2-2-.896-2-2-2-2 .896-2 2zm0 0v6m0 0H9m3 0h3" />
            </svg>
            <span>마이</span>
        </button>
    </div>
</div>
</body>
</html>