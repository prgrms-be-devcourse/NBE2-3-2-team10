<%@ page import="org.team10.washcode.ResponseDTO.pickup.PickupResDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    PickupResDTO pickupDetail = (PickupResDTO) request.getAttribute("pickupDetail");
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
    <script>
        function updatePickupStatus(pickupId, newStatus) {
            const button = document.getElementById('statusButton');
            button.disabled = true;
            button.classList.add('bg-gray-300', 'border-gray-300', 'text-gray-500');
            button.classList.remove('text-blue-500', 'border-blue-500', 'hover:bg-blue-100');

            fetch('/api/pickup/updateStatus?pickupId=' + pickupId + '&status=' + newStatus, {
                method: 'POST'
            })
                .then(response => {
                    if (response.ok) {
                        window.location.reload();
                    } else {
                        alert("상태 변경 실패");
                        button.disabled = false;
                        button.classList.remove('bg-gray-300', 'border-gray-300', 'text-gray-500');
                        button.classList.add('text-blue-500', 'border-blue-500', 'hover:bg-blue-100');
                    }
                })
                .catch(error => {
                    console.error("Error:", error);
                    button.disabled = false;
                    button.classList.remove('bg-gray-300', 'border-gray-300', 'text-gray-500');
                    button.classList.add('text-blue-500', 'border-blue-500', 'hover:bg-blue-100');
                });
        }
    </script>
</head>
<body class="bg-gray-100">
<!-- 메인 컨테이너 -->
<div class="max-w-md mx-auto bg-white shadow-md rounded-lg mt-4">
    <!-- 상단 헤더 -->
    <div class="flex items-center justify-between p-4 border-b">
        <button class="text-gray-500" onclick="history.back()">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24"
                 stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"/>
            </svg>
        </button>
        <h1 class="text-lg font-bold">세탁 요청 상세</h1>
        <div></div>
    </div>

    <!-- 주문 정보 상세 -->
    <div class="mt-2 px-4 pb-6">
        <div class="bg-white p-4 rounded-lg shadow-sm border">
            <!-- 동적 주문일자 -->
            <p class="font-bold text-sm mb-2">
                주문일자 : <span class="font-normal"><%= pickupDetail.getCreatedAt().toLocaleString() %></span>
            </p>
            <!-- 동적 주소 -->
            <p class="font-bold text-sm mb-2">
                주소 : <span class="font-normal"><%= pickupDetail.getAddress() %></span>
            </p>
            <p class="font-bold text-sm mb-2">
                요청 사항 : <span class="font-normal"><%= pickupDetail.getContent().toString() %></span>
            </p>
            <!-- 동적 상품 리스트 -->
            <p class="text-sm mb-2 leading-6">
                <%
                    List<PickupResDTO.OrderItemDTO> items = pickupDetail.getOrderItems();
                    for (int i = 0; i < items.size(); i++) {
                        PickupResDTO.OrderItemDTO item = items.get(i);
                        out.print(item.getItemName() + " " + item.getQuantity() + "개");
                        if (i < items.size() - 1) {
                            out.print(", ");
                        }
                    }
                %>
            </p>
            <!-- 동적 상태 -->
            <div>
                <% if ("IN_PROGRESS".equals(pickupDetail.getStatus().toString())) { %>
                <span class="text-green-700 text-sm font-bold block whitespace-nowrap">
          결제완료
        </span>
                <% } %>
                <span class="
            <% if ("DELIVERING".equals(pickupDetail.getStatus().toString())) { %>
                text-yellow-400
            <% } else if ("DELIVERED".equals(pickupDetail.getStatus().toString())) { %>
                text-green-400
            <% } else if ("PICKED_UP".equals(pickupDetail.getStatus().toString())) { %>
                text-blue-400
            <% } else if ("IN_PROGRESS".equals(pickupDetail.getStatus().toString())) { %>
                text-red-500
            <% } else { %>
                text-gray-400
            <% } %>
            text-lg font-bold whitespace-nowrap">
            <%= pickupDetail.getStatus().getDesc() %>
        </span>
            </div>
            <!-- 버튼 그룹 -->
            <div class="flex justify-between mt-4 space-x-4">
                <% if (!"OUT_FOR_DELIVERY".equals(pickupDetail.getStatus().toString())
                        && !"PAYMENT_PENDING".equals(pickupDetail.getStatus().toString())) { %>
                <!-- 상태 변경 버튼 -->
                <button id="statusButton"
                        class="button-outline text-blue-500 border-blue-500 hover:bg-blue-100 flex-1"
                        onclick="updatePickupStatus(<%= pickupDetail.getPickupId() %>, 'OUT_FOR_DELIVERY')">
                    작업 완료
                </button>
                <% } %>
                <!-- 뒤로가기 버튼 -->
                <button class="button-outline text-red-400 border-red-400 hover:bg-red-100 flex-1"
                        onclick="location.href='/pickup-list'">
                    뒤로 가기
                </button>
            </div>
        </div>
    </div>

</div>

<!-- 하단 네비게이션 -->
<div class="fixed bottom-0 left-0 right-0 bg-white shadow-md">
    <div class="flex justify-around py-2">
        <!-- 홈 버튼 -->
        <button onclick="location.href='/shop-main'" class="flex flex-col items-center text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24"
                 stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="M3 12h18M3 6h18M3 18h18"/>
            </svg>
            <span class="text-xs font-medium">홈</span>
        </button>

        <!-- 주문내역 버튼 -->
        <button onclick="location.href='/pickup-list'" class="flex flex-col items-center text-blue-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24"
                 stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"/>
            </svg>
            <span class="text-xs font-medium">주문내역</span>
        </button>

        <!-- 마이페이지 버튼 -->
        <button class="flex flex-col items-center text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24"
                 stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="M12 8v4l3 3"/>
            </svg>
            <span class="text-xs font-medium">마이페이지</span>
        </button>

    </div>
</div>
</body>
</html>