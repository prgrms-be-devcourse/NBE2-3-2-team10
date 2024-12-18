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
</head>
<body class="bg-gray-100">

<div class="max-w-md mx-auto bg-white shadow-md rounded-lg overflow-hidden">
    <div class="p-4">
        <input type="text" placeholder="세탁소를 검색하세요" class="w-full p-2 border rounded-lg">
    </div>
    <div class="relative">
        <img src="https://via.placeholder.com/400x300" alt="Map" class="w-full">
        <div class="absolute top-2 right-2 bg-white p-2 rounded-full shadow-md">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 text-gray-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 11c0 1.104-.896 2-2 2s-2-.896-2-2 .896-2 2-2 2 .896 2 2zM12 11c0 1.104.896 2 2 2s2-.896 2-2-.896-2-2-2-2 .896-2 2zM12 11v10m0-10c0-1.104.896-2 2-2s2 .896 2 2-.896 2-2 2-2-.896-2-2zM12 11c0-1.104-.896-2-2-2s-2 .896-2 2 .896 2 2 2 2-.896 2-2z" />
            </svg>
        </div>
    </div>
    <div class="flex justify-around bg-white p-4 border-t fixed bottom-0 w-full max-w-md">
        <button class="flex flex-col items-center text-blue-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-10 0a1 1 0 001-1V10m0 0l7-7 7 7" />
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
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5.121 17.804A1 1 0 014 17V7a1 1 0 011.121-.804l7 2.5a1 1 0 01.879.804l7 2.5A1 1 0 0120 13v10a1 1 0 01-1.121.804l-7-2.5a1 1 0 01-.879-.804l-7-2.5z" />
            </svg>
            <span>마이</span>
        </button>
    </div>
</div>

</body>
</html>