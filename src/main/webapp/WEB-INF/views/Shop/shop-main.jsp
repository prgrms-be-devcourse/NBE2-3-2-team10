<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>워시팡 사장님 페이지</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
  <style>
    body {
      font-family: 'Noto Sans KR', sans-serif;
    }
    .background-banner {
      background-image: url('https://source.unsplash.com/featured/?laundry');
      background-size: cover;
      background-position: center;
      height: 200px;
    }
  </style>
</head>
<body class="bg-gray-100">
<!-- 상단 배너 -->
<div class="background-banner flex items-center justify-center">
  <h1 class="text-white text-3xl font-bold">워시팡 사장님 페이지</h1>
</div>

<!-- 메뉴 카드 -->
<div class="max-w-md mx-auto mt-4 space-y-4 px-4">
  <!-- 수거 요청 버튼 -->
  <button type="button" onclick="location.href='/pickup-check'" class="flex items-center justify-between w-full bg-white rounded-2xl shadow-md p-4 hover:bg-gray-100 active:bg-gray-200 focus:outline-none">
    <span class="text-xl font-bold">수거 요청</span>
    <img src="https://img.icons8.com/ios-filled/50/00bcd4/delivery.png" alt="수거 요청" class="h-10 w-10">
  </button>

  <!-- 매출 관리 버튼 -->
  <button type="button" onclick="location.href='/sales-summary'" class="flex items-center justify-between w-full bg-white rounded-2xl shadow-md p-4 hover:bg-gray-100 active:bg-gray-200 focus:outline-none">
    <span class="text-xl font-bold">매출 관리</span>
    <img src="https://img.icons8.com/ios-filled/50/00bcd4/combo-chart.png" alt="매출 관리" class="h-10 w-10">
  </button>

  <!-- 리뷰 관리 버튼 -->
  <button type="button" onclick="location.href='/shop-review'" class="flex items-center justify-between w-full bg-white rounded-2xl shadow-md p-4 hover:bg-gray-100 active:bg-gray-200 focus:outline-none">
    <span class="text-xl font-bold">리뷰 관리</span>
    <img src="https://img.icons8.com/ios-filled/50/00bcd4/comments.png" alt="리뷰 관리" class="h-10 w-10">
  </button>
</div>

<!-- 하단 네비게이션 -->
<div class="fixed bottom-0 left-0 right-0 bg-white shadow-md">
  <div class="flex justify-around py-2">
    <button onclick="location.href='/shop-main'" class="flex flex-col items-center text-teal-500">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none"
           viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round"
              stroke-width="2" d="M3 12h18M3 6h18M3 18h18" />
      </svg>
      <span class="text-xs">홈</span>
    </button>
    <button onclick="location.href='/pickup-list'" class="flex flex-col items-center text-gray-500">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none"
           viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round"
              stroke-width="2" d="M5 13l4 4L19 7" />
      </svg>
      <span class="text-xs">주문내역</span>
    </button>
    <button class="flex flex-col items-center text-gray-500">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none"
           viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round"
              stroke-width="2" d="M12 11c0 1.104-.896 2-2 2s-2-.896-2-2 .896-2 2-2
              2 .896 2 2zm0 0c0 1.104.896 2 2 2s2-.896 2-2-.896-2-2-2-2
              .896-2 2zm0 0v6m0 0H9m3 0h3" />
      </svg>
      <span class="text-xs">마이</span>
    </button>
  </div>
</div>
</body>
</html>
