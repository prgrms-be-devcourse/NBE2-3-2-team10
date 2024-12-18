<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Prototype</title>
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
  <div class="p-4">
    <div class="text-sm text-blue-500 font-bold">일반 회원</div>
    <div class="text-xl font-bold mt-2">오현식 님</div>
    <div class="text-gray-600 mt-1">깨끗한 하루 되세요!</div>
  </div>
  <div class="flex justify-around mt-4">
    <button class="text-center focus:outline-none">
      <svg class="w-8 h-8 mx-auto text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-6 4h6m-6 0a2 2 0 00-2 2v10a2 2 0 002 2h6a2 2 0 002-2V9a2 2 0 00-2-2H8z"></path>
      </svg>
      <div class="text-sm mt-1">위시팡 내역</div>
    </button>
    <button class="text-center focus:outline-none">
      <svg class="w-8 h-8 mx-auto text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
      </svg>
      <div class="text-sm mt-1">내정보</div>
    </button>
  </div>
  <div class="mt-4 border-t">
    <button class="flex justify-between items-center p-4 border-b w-full focus:outline-none">
      <div>공지사항</div>
      <svg class="w-4 h-4 text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path>
      </svg>
    </button>
    <button class="flex justify-between items-center p-4 w-full focus:outline-none">
      <div>고객센터</div>
      <svg class="w-4 h-4 text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path>
      </svg>
    </button>
  </div>
</div>
<div class="fixed bottom-0 left-0 right-0 bg-white shadow-md">
  <div class="flex justify-around py-2">
    <button class="text-center focus:outline-none">
      <svg class="w-6 h-6 mx-auto text-blue-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-10 0a1 1 0 001 1h6a1 1 0 001-1m-7-1h6"></path>
      </svg>
      <div class="text-xs mt-1">홈</div>
    </button>
    <button class="text-center focus:outline-none">
      <svg class="w-6 h-6 mx-auto text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 7h18M3 12h18m-9 5h9"></path>
      </svg>
      <div class="text-xs mt-1">주문내역</div>
    </button>
    <button class="text-center focus:outline-none">
      <svg class="w-6 h-6 mx-auto text-gray-500" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5.121 17.804A1 1 0 014 17V7a1 1 0 011.121-.804l7 2.5a1 1 0 01.879.804l7 2.5A1 1 0 0120 13v10a1 1 0 01-1.121.804l-7-2.5a1 1 0 01-.879-.804l-7-2.5z"></path>
      </svg>
      <div class="text-xs mt-1">마이</div>
    </button>
  </div>
</div>
</body>
</html>