<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Information Update</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
    </style>
</head>
<body class="bg-gray-100">
<div class="max-w-md mx-auto bg-white shadow-md">
    <header class="flex items-center p-4 border-b">
        <button class="text-xl">&larr;</button>
        <h1 class="flex-grow text-center font-bold">내 정보 수정</h1>
    </header>
    <main class="p-4">
        <section class="mb-6">
            <h2 class="font-bold mb-2">정보 수정</h2>
            <div class="space-y-4">
                <div>
                    <label class="block text-sm font-bold">Email</label>
                    <input type="email" value="ks06891@naver.com" class="w-full border p-2 rounded" readonly>
                </div>
                <div>
                    <label class="block text-sm font-bold">이름</label>
                    <input type="text" value="통장잔고0원" class="w-full border p-2 rounded" readonly>
                </div>
                <div>
                    <label class="block text-sm font-bold">전화번호</label>
                    <div class="flex">
                        <input type="text" value="010-1234-5678" class="flex-grow border p-2 rounded-l" readonly>
                        <button class="bg-blue-500 text-white px-4 rounded-r">인증 받기</button>
                    </div>
                </div>
            </div>
        </section>
        <section>
            <h2 class="font-bold mb-2">배송지</h2>
            <div class="flex justify-between items-center mb-2">
                <span>서울특별시 송파구 충민로4길 6...</span>
                <button class="text-blue-500">수정</button>
            </div>
            <div class="border p-4 rounded shadow-sm">
                자유출입가능
            </div>
        </section>
    </main>
    <footer class="fixed bottom-0 left-0 right-0 bg-white border-t">
        <div class="flex justify-around p-2">
            <button class="flex flex-col items-center text-blue-500">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M13 5v6h6m-6 0l-7 7-2-2m0 0l-7-7 7-7" />
                </svg>
                <span>홈</span>
            </button>
            <button class="flex flex-col items-center text-gray-500">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h18M3 9h18M3 15h18M3 21h18" />
                </svg>
                <span>주문내역</span>
            </button>
            <button class="flex flex-col items-center text-gray-500">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                </svg>
                <span>마이</span>
            </button>
        </div>
    </footer>
</div>
</body>
</html>