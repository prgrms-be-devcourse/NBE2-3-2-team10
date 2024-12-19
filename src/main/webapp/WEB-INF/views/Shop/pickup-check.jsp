<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.team10.washcode.ResponseDTO.pickup.PickupDetailResDTO" %>
<%
  List<PickupDetailResDTO> pickupList = (List<PickupDetailResDTO>) request.getAttribute("pickupList");
%>

<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>세탁 요청</title>
  <script src="https://cdn.tailwindcss.com"></script>
  <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
  <style>
    body {
      font-family: 'Noto Sans KR', sans-serif;
    }
  </style>
</head>
<body class="bg-gray-100">

<%
  if (pickupList == null || pickupList.isEmpty()) {
%>
<!-- 세탁 요청 없음 -->
<div class="max-w-md mx-auto bg-white shadow-md rounded-lg mt-4 mb-20 p-6 text-center">
  <h1 class="text-2xl font-bold mb-4">세탁 요청</h1>
  <p class="text-gray-700 text-center mb-4 font-semibold">현재 세탁 요청이 없습니다.</p>
</div>
<%
} else {
  for (PickupDetailResDTO pickup : pickupList) {
%>
<!-- 메인 컨테이너 -->
<div class="max-w-md mx-auto bg-white shadow-md rounded-lg mt-4 mb-20 p-6">
  <!-- 제목 -->
  <h1 class="text-2xl font-bold mb-4 text-center">세탁 요청</h1>
  <p class="text-blue-500 text-center mb-4 font-semibold">세탁물 수거 요청이 들어왔어요.</p>

  <!-- 요청 상세 정보 -->
  <div class="border rounded-lg p-4 shadow-sm mb-4">
    <h2 class="text-lg font-bold mb-2"><%= pickup.getShopName() %></h2>
    <ul class="list-disc list-inside mb-2 text-gray-700">
      <% for (PickupDetailResDTO.OrderItemDTO item : pickup.getOrderItems()) { %>
      <li><%= item.getItemName() + " " + item.getQuantity() + " 개 " + " / 금액 " + item.getTotalPrice() %></li>
      <% } %>
    </ul>
    <p class="text-gray-600 mb-2"><span class="font-semibold">주문일자:</span> <%= pickup.getCreatedAt().toLocaleString() %></p>
    <h2 class="text-lg font-bold mt-4 mb-2">결제금액</h2>
    <p class="text-gray-600 mb-1"><span class="font-semibold">주문 금액:</span> <%= pickup.getPaymentAmount() %>원</p>
    <p class="text-gray-600 mb-2"><span class="font-semibold">결제 방법:</span> <%= pickup.getPaymentMethod() %></p>
    <h2 class="text-lg font-bold mt-4 mb-2">배달 주소</h2>
    <p class="text-gray-600"><span class="font-semibold">주소:</span> <%= pickup.getAddress() %></p>
    <p class="text-gray-600"><span class="font-semibold">전화번호:</span> <%= pickup.getPhone() %></p>
  </div>

  <!-- 버튼 그룹 -->
  <div class="flex justify-between mt-4 space-x-2">
    <button class="w-1/2 bg-teal-500 text-white font-bold py-2 rounded-lg hover:bg-teal-600 shadow-md">
      수거 확인
    </button>
    <button class="w-1/2 bg-red-500 text-white font-bold py-2 rounded-lg hover:bg-red-600 shadow-md">
      수거 거절
    </button>
  </div>
</div>
<%
    }
  }
%>

<!-- 하단 네비게이션 -->
<div class="fixed bottom-0 left-0 right-0 bg-white shadow-md">
  <div class="flex justify-around py-2">
    <!-- 홈 버튼 -->
    <button onclick="location.href='/shop-main'" class="flex flex-col items-center text-teal-500">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M13 5v6h6m-6 0l-7 7-2-2m9 2h6" />
      </svg>
      <span class="text-xs mt-1">홈</span>
    </button>

    <!-- 주문내역 버튼 -->
    <button onclick="location.href='/pickup-list'" class="flex flex-col items-center text-gray-500">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
      </svg>
      <span class="text-xs mt-1">주문내역</span>
    </button>

    <!-- 마이페이지 버튼 -->
    <button class="flex flex-col items-center text-gray-500">
      <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 11c0 1.104-.896 2-2 2s-2-.896-2-2 .896-2 2-2 2 .896 2 2zm0 0c0 1.104.896 2 2 2s2-.896 2-2-.896-2-2-2-2 .896-2 2zm0 0v6m0 0H9m3 0h3" />
      </svg>
      <span class="text-xs mt-1">마이</span>
    </button>
  </div>
</div>

</body>
</html>
