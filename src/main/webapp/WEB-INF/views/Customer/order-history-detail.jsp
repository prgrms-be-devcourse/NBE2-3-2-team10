<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.team10.washcode.ResponseDTO.order.OrderResDTO" %>
<%@ page import="java.util.List" %>
<%
%>
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
    <div class="p-4">
        <div class="bg-white p-4 rounded-lg shadow mb-4">
            <%
                OrderResDTO order = (OrderResDTO) request.getAttribute("order");
                if (order != null) {
            %>
            <div class="flex justify-between items-center">
                <h2 class="font-bold"><%= order.getShop_name() %></h2>
                <span class="text-blue-500"><%= order.getStatus(). %></span>
            </div>
            <%
                List<OrderResDTO.OrderItem> orderItems = order.getOrder_items();
                if (orderItems != null) {
                    for (OrderResDTO.OrderItem item : orderItems) {
            %>
            <p><%= item.getItem_name() %> <%= item.getQuantity() %>개</p>
            <%
                    }
                }
            %>
            <p class="text-gray-500">주문일자 : <%= order.getCreated_at() %></p>
            <%
                } // order != null 닫힘
            %>
            <div class="border-t border-gray-200 mt-2 pt-2">
                <h2 class="font-bold">결제금액</h2>
                <p>주문 금액 : <%= order.getAmount() %>원</p>
                <p>결제 방법 : <%= order.getMethod() %></p>
            </div>

            <div class="border-t border-gray-200 mt-2 pt-2">
                <h2 class="font-bold">배달 주소</h2>
                <p><%= order.getAddress() %></p>
                <p>전화번호 : <%= order.getPhone() %></p>
            </div>
            <div class="border-t border-gray-200 mt-2 pt-2">
                <h2 class="font-bold">요청사항</h2>
                <p><%= (order.getContent() != null && !order.getContent().trim().isEmpty()) ? order.getContent() : "요청사항 없음." %></p>
            </div>
            <div class="flex justify-between mt-4" style="text-align: right;">
<%--                <button class="bg-blue-100 text-blue-500 font-medium py-2 px-4 rounded-lg">수정</button>--%>
                <form id="deleteForm" action="/api/orders/cancel/${userId}/${pickupId}" method="post">
                <button type="submit" class="bg-red-100 text-red-500 font-medium py-2 px-4 rounded-lg">주문취소</button>
                </form>
            </div>
        </div>
    </div>
</div>


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