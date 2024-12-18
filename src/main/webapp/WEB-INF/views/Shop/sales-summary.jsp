<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>매출액 조회</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
  <style>
    body {
      font-family: 'Noto Sans KR', sans-serif;
    }
    .button-outline {
      border: 2px solid;
      border-radius: 20px;
      padding: 6px 16px;
      font-weight: bold;
      font-size: 0.9rem;
    }
  </style>
</head>
<body class="bg-gray-100">
<!-- 상단 헤더 -->
<div class="max-w-md mx-auto bg-white shadow-md rounded-lg">
  <div class="flex justify-between items-center p-4 border-b">
    <!-- 뒤로가기 버튼 -->
    <button onclick="history.back()" class="text-gray-500 hover:text-gray-700">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
      </svg>
    </button>
    <h1 class="text-lg font-bold">매출액 조회</h1>
    <!-- 날짜 선택 버튼 -->
    <button class="button-outline text-blue-500 border-blue-500 hover:bg-blue-100">2024-01</button>
  </div>
</div>

<!-- 매출 내역 -->
<div class="max-w-md mx-auto mt-2 space-y-4 px-4 pb-20">
  <!-- 날짜별 리스트 -->
  <div class="bg-white p-4 rounded-lg shadow-md">
    <!-- 날짜 -->
    <p class="text-blue-500 font-bold mb-2">2024-01-27 (토)</p>
    <!-- 항목 1 -->
    <div class="flex justify-between mb-2">
      <div>
        <p class="text-sm font-bold">정장 드라이클리닝 프리미엄</p>
        <p class="text-gray-400 text-xs">결제완료</p>
      </div>
      <span class="font-bold text-sm">10,200 원</span>
    </div>
    <!-- 항목 2 -->
    <div class="flex justify-between mb-2">
      <div>
        <p class="text-sm font-bold">정장 드라이클리닝 프리미엄</p>
        <p class="text-gray-400 text-xs">결제완료</p>
      </div>
      <span class="font-bold text-sm">10,200 원</span>
    </div>
    <!-- 항목 3 -->
    <div class="flex justify-between mb-2">
      <div>
        <p class="text-sm font-bold">정장 드라이클리닝 프리미엄</p>
        <p class="text-gray-400 text-xs">결제완료</p>
      </div>
      <span class="font-bold text-sm">10,200 원</span>
    </div>
  </div>

  <div class="bg-white p-4 rounded-lg shadow-md">
    <!-- 날짜 -->
    <p class="text-blue-500 font-bold mb-2">2024-01-29 (월)</p>
    <!-- 항목 1 -->
    <div class="flex justify-between mb-2">
      <div>
        <p class="text-sm font-bold">정장 드라이클리닝 프리미엄</p>
        <p class="text-gray-400 text-xs">결제완료</p>
      </div>
      <span class="font-bold text-sm">10,200 원</span>
    </div>
    <!-- 항목 2 -->
    <div class="flex justify-between mb-2">
      <div>
        <p class="text-sm font-bold">정장 드라이클리닝 프리미엄</p>
        <p class="text-gray-400 text-xs">결제완료</p>
      </div>
      <span class="font-bold text-sm">10,200 원</span>
    </div>
    <!-- 항목 3 -->
    <div class="flex justify-between mb-2">
      <div>
        <p class="text-sm font-bold">정장 드라이클리닝 프리미엄</p>
        <p class="text-gray-400 text-xs">결제완료</p>
      </div>
      <span class="font-bold text-sm">10,200 원</span>
    </div>
  </div>

  <!-- 총 매출액 -->
  <div class="flex justify-between items-center bg-white p-4 rounded-lg shadow-md">
    <span class="font-bold text-gray-700">총 매출액</span>
    <span class="font-bold text-blue-500 text-lg">12,250,240 원</span>
  </div>
</div>

<!-- 하단 네비게이션 -->
<div class="fixed bottom-0 left-0 right-0 bg-white shadow-md">
  <div class="flex justify-around py-2">
    <!-- 홈 버튼 -->
    <button class="flex flex-col items-center text-blue-500">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M13 5v6h6m-6 0l-7 7-2-2m9 2h6" />
      </svg>
      <span class="text-xs font-medium mt-1">홈</span>
    </button>

    <!-- 주문내역 버튼 -->
    <button class="flex flex-col items-center text-gray-500">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
      </svg>
      <span class="text-xs font-medium mt-1">주문내역</span>
    </button>

    <!-- 마이페이지 버튼 -->
    <button class="flex flex-col items-center text-gray-500">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 11c0 1.104-.896 2-2 2s-2-.896-2-2 .896-2 2-2 2 .896 2 2zm0 0c0 1.104.896 2 2 2s2-.896 2-2-.896-2-2-2-2 .896-2 2zm0 0v6m0 0H9m3 0h3" />
      </svg>
      <span class="text-xs font-medium mt-1">마이</span>
    </button>
  </div>
</div>
</body>
</html>