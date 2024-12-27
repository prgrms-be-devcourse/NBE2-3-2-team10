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
    <title>수거 요청</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
    </style>

    <script>
        // pickup 상태를 업데이트하는 함수
        function updatePickupStatus(pickupId, newStatus) {
            fetch('/api/pickup/updateStatus?pickupId=' + pickupId + '&status=' + newStatus, {
                method: 'POST'
            })
                .then(response => {
                    if (response.ok) {
                        window.location.reload();
                    } else {
                        alert("상태 변경 실패");
                    }
                })
                .catch(error => console.error("Error:", error));
        }
    </script>

</head>
<body class="bg-gray-100">

<%
    if (pickupList == null || pickupList.isEmpty()) {
%>
<!-- 수거 요청 없음 -->
<div class="max-w-md mx-auto bg-white shadow-md rounded-lg mt-4 mb-20 p-6 text-center">
    <h1 class="text-2xl font-bold mb-4">수거 요청</h1>
    <p class="text-gray-700 text-center mb-4 font-semibold">현재 수거 요청이 없습니다.</p>
</div>
<%
} else {
    for (PickupDetailResDTO pickup : pickupList) {
%>
<!-- 메인 컨테이너 -->
<div class="max-w-md mx-auto bg-white shadow-md rounded-lg mt-4 mb-20 p-6">
    <!-- 제목 -->
    <h1 class="text-2xl font-bold mb-4 text-center">수거 요청</h1>
    <p class="text-blue-500 text-center mb-4 font-semibold">세탁물 수거 요청이 들어왔어요.</p>

    <!-- 요청 상세 정보 -->
    <div class="border rounded-lg p-4 shadow-sm mb-4">
        <h2 class="text-lg font-bold mb-2"><%= pickup.getShopName() %>
        </h2>
        <ul class="list-disc list-inside mb-2 text-gray-700">
            <% for (PickupDetailResDTO.OrderItemDTO item : pickup.getOrderItems()) { %>
            <li><%= item.getItemName() + " " + item.getQuantity() + " 개 " + " / 금액 " + item.getTotalPrice() %>
            </li>
            <% } %>
        </ul>
        <p class="text-gray-600 mb-2"><span
                class="font-semibold">주문일자:</span> <%= pickup.getCreatedAt().toLocaleString() %>
        </p>
        <p class="text-gray-600 mb-2"><span
                class="font-semibold">요청 사항:</span> <%= pickup.getContent().toString() %>
        </p>

        <h2 class="text-lg font-bold mt-4 mb-2">결제금액</h2>
        <p class="text-gray-600 mb-1"><span class="font-semibold">주문 금액:</span> <%= pickup.getPaymentAmount() %>원</p>
        <p class="text-gray-600 mb-2"><span class="font-semibold">결제 방법:</span> <%= pickup.getPaymentMethod() %>
        </p>
        <h2 class="text-lg font-bold mt-4 mb-2">배달 주소</h2>
        <p class="text-gray-600"><span class="font-semibold">주소:</span> <%= pickup.getAddress() %>
        </p>
        <p class="text-gray-600"><span class="font-semibold">전화번호:</span> <%= pickup.getPhone() %>
        </p>
    </div>

    <!-- 버튼 그룹 -->
    <div class="flex justify-between mt-4 space-x-2">
        <!-- 수거 확인 버튼 -->
        <button type="button"
                class="w-1/2 bg-teal-500 text-white font-bold py-2 rounded-lg hover:bg-teal-600 shadow-md"
                onclick="updatePickupStatus(<%= pickup.getPickupId() %>, 'PAYMENT_PENDING')">
            수거 확인
        </button>

        <!-- 수거 거절 버튼 -->
        <button type="button"
                class="w-1/2 bg-red-500 text-white font-bold py-2 rounded-lg hover:bg-red-600 shadow-md"
                onclick="updatePickupStatus(<%= pickup.getPickupId() %>, 'NONE')">
            수거 거절
        </button>
    </div>
</div>
<%
        }
    }
%>

<!-- 하단 네비게이션 -->
<footer class="fixed bottom-0 left-0 right-0 bg-white shadow p-4 flex justify-around overflow-x-auto mx-auto max-w-[448px] rounded-t-lg">
    <button class="flex flex-col items-center text-blue-500" onclick="location.href='/shop-main'">
        <img src = "./footer/Home.svg" class = "h-6 w-6"/>
        <span class="text-black text-[10pt] mt-1">홈</span>
    </button>
    <button class="flex flex-col items-center text-gray-500" onclick="location.href='/pickup-list'" >
        <img src = "./footer/Bag.svg" class = "h-6 w-6"/>
        <span class="text-black text-[10pt] mt-1">주문내역</span>
    </button>
    <button class="flex flex-col items-center text-gray-500" onclick="location.href='/shop-mypage'">
        <img src = "./footer/Star.svg" class = "h-6 w-6"/>
        <span class="text-black text-[10pt] mt-1">내 정보</span>
    </button>
</footer>

</body>
</html>
