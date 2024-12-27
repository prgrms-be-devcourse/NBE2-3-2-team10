<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내 정보</title>
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
    <div class="flex items-center p-4 border-b">
        <button class="text-xl">&larr;</button>
        <h1 class="flex-grow text-center text-xl font-bold">내 정보</h1>
        <button class="text-blue-500">수정</button>
    </div>
    <div class="p-4">
        <div class="mb-6">
            <h2 class="text-lg font-bold mb-2">기본 정보</h2>
            <div class="flex justify-between items-center mb-2">
                <span>Email</span>
                <span>ks06891@naver.com</span>
            </div>
            <div class="flex justify-between items-center mb-2">
                <span>이름</span>
                <span>통장잔고원</span>
            </div>
            <div class="flex justify-between items-center mb-2">
                <span>전화번호</span>
                <span>010-1234-5678</span>
            </div>
        </div>
        <div>
            <h2 class="text-lg font-bold mb-2">배송 정보</h2>
            <div class="mb-2">
                <span class="block font-semibold">배송지</span>
                <span>서울특별시 송파구 충민로4길 6, 78층 7803동 30004호</span>
            </div>
            <div>
                <span class="block font-semibold">공동현관 출입방법</span>
                <span>밑에서 78층까지 소리치리시면 됩니다.</span>
            </div>
        </div>
    </div>
</div>
<footer class="fixed bottom-0 left-0 right-0 bg-white shadow-md">
    <div class="flex justify-around p-4">
        <button class="flex flex-col items-center text-blue-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-4 0h-4m4 0a1 1 0 001-1v-4a1 1 0 00-1-1h-4a1 1 0 00-1 1v4a1 1 0 001 1z" />
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
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5.121 17.804A4.992 4.992 0 0112 15c1.657 0 3.156.672 4.121 1.804M15 10a3 3 0 11-6 0 3 3 0 016 0zM12 14v7m0 0H9m3 0h3" />
            </svg>
            <span>마이</span>
        </button>
    </div>
</footer>
</body>
</html>