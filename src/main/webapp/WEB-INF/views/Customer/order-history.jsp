<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.team10.washcode.ResponseDTO.order.OrderlistResDTO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>이용내역</title>
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
    <div class="p-4 border-b">
        <h1 class="text-xl font-bold">이용내역</h1>
    </div>
    <form action="/api/orders/history" method="get">
        <input type="hidden" name="userId" value="<%= request.getAttribute("userId") %>" />
        <div class="p-4">
            <%-- orders 리스트를 반복문을 통해 출력 --%>
            <%
                List<OrderlistResDTO> orders = (List<OrderlistResDTO>) request.getAttribute("orders");
                if (orders != null) {
                    for (OrderlistResDTO order : orders) {
            %>
            <div class="bg-white p-4 rounded-lg shadow mb-4 cursor-pointer">
                <div class="flex justify-between items-center">
                    <h2 class="font-bold"><%= order.getShopName() %></h2>
                    <span class="text-blue-500"><%= order.getStatus().getDesc() %></span>
                </div>
                <p class="text-gray-500">주문 생성일 : <%= order.getCreatedAt() %></p>
                <%-- 주문 상세보기 버튼 추가 --%>
                <a href="/api/orders/history/<%= request.getAttribute("userId") %>/<%= order.getPickup_id() %>"
                   class="text-blue-500 mt-2 inline-block">
                    상세보기
                </a>
            </div>
            <%
                    }
                }
            %>
        </div>
    </form>
</div>
<!-- Footer -->
<footer class="fixed bottom-0 left-0 right-0 bg-white shadow p-4 flex justify-around overflow-x-auto mx-auto max-w-[448px] rounded-t-lg">
    <button class="flex flex-col items-center text-blue-500" onclick="location.href='/main'">
        <img src = "./footer/Home.svg" class = "h-6 w-6"/>
        <span class="text-black text-[10pt] mt-1">홈</span>
    </button>
    <button class="flex flex-col items-center text-gray-500" onclick="location.href='/orderHistory'" >
        <img src = "./footer/Bag.svg" class = "h-6 w-6"/>
        <span class="text-black text-[10pt] mt-1">주문내역</span>
    </button>
    <button class="flex flex-col items-center text-gray-500" onclick="location.href='/mypage'">
        <img src = "./footer/Star.svg" class = "h-6 w-6"/>
        <span class="text-black text-[10pt] mt-1">내 정보</span>
    </button>
</footer>
</body>
</html>
