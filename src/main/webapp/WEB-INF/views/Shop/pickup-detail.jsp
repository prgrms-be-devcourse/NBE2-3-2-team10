<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>세탁 요청 상세</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
  <style>
    body {
      font-family: 'Noto Sans KR', sans-serif;
    }
    .button-outline {
      border: 2px solid;
      border-radius: 20px;
      padding: 8px 20px;
      font-weight: bold;
    }
  </style>
</head>
<body class="bg-gray-100">
<!-- 메인 컨테이너 -->
<div class="max-w-md mx-auto bg-white shadow-md rounded-lg mt-4">
  <!-- 상단 헤더 -->
  <div class="flex items-center justify-between p-4 border-b">
    <button class="text-gray-500" onclick="history.back()">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
      </svg>
    </button>
    <h1 class="text-lg font-bold">세탁 요청 상세</h1>
    <div></div>
  </div>

  <!-- 주문 정보 상세 -->
  <div class="mt-2 px-4 pb-6">
    <div class="bg-white p-4 rounded-lg shadow-sm border">
      <p class="font-bold text-sm mb-2">주문일자 : <span class="font-normal">2024년 12월 12일</span></p>
      <p class="font-bold text-sm mb-2">
        주소 : <span class="font-normal">서울특별시 어쩌구 저쩌구 123 동그라미 아파트<br>(123동 123호)</span>
      </p>

      <p class="text-sm mb-2 leading-6">
        와이셔츠 1개 &nbsp; 청바지 2개 &nbsp; 코트 1개 <br>
        정장 1개 &nbsp; 수건 7개 &nbsp; 가디건 2개
      </p>

      <p class="text-blue-500 font-bold text-lg text-right">배송중</p>

      <!-- 버튼 그룹 -->
      <div class="flex justify-between mt-4">
        <button class="button-outline text-blue-500 border-blue-500 hover:bg-blue-100">배송 완료</button>
        <button class="button-outline text-red-400 border-red-400 hover:bg-red-100">뒤로 가기</button>
      </div>
    </div>
  </div>
</div>

<!-- 하단 네비게이션 -->
<div class="fixed bottom-0 left-0 right-0 bg-white shadow-md">
  <div class="flex justify-around py-2">
    <!-- 홈 버튼 -->
    <button onclick="location.href='/shop-main'" class="flex flex-col items-center text-blue-500">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M13 5v6h6m-6 0l-7 7-2-2m9 2h6" />
      </svg>
      <span class="text-xs font-medium mt-1">홈</span>
    </button>

    <!-- 주문내역 버튼 -->
    <button onclick="location.href='/pickup-list'" class="flex flex-col items-center text-gray-500">
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