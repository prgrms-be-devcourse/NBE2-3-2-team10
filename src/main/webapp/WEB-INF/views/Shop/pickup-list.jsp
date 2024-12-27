<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.team10.washcode.ResponseDTO.pickup.PickupResDTO" %>
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
    List<PickupResDTO> pickedUpList = (List<PickupResDTO>) request.getAttribute("pickedUpList");
%>

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
        <h1 class="text-lg font-bold">세탁 요청</h1>
        <div></div>
    </div>

    <!-- 메인 컨텐츠 -->
    <div class="mt-2 space-y-4 px-4 pb-20">
        <% if (pickedUpList == null || pickedUpList.isEmpty()) { %>
        <!-- 세탁 요청 없음 -->
        <div class="bg-white p-4 rounded-lg shadow-sm">
            <div class="flex justify-center items-center">
                <div>
                    <p class="text-sm font-semibold text-gray-700">현재 세탁 요청이 없습니다.</p>
                </div>
            </div>
        </div>
        <% } else { %>
        <% for (PickupResDTO pickup : pickedUpList) { %>
        <!-- 세탁 요청 항목 -->
        <div class="bg-white p-4 rounded-lg shadow-sm">
            <div class="flex justify-between items-center">
                <div>
                    <p class="text-sm font-bold">주문일자 : <%= pickup.getCreatedAt().toLocaleString() %>
                    </p>
                    <p class="text-sm">주소 : <%= pickup.getAddress() %>
                    </p>
                    <p class="text-sm">
                        <%
                            List<PickupResDTO.OrderItemDTO> items = pickup.getOrderItems();
                            for (int i = 0; i < items.size(); i++) {
                                PickupResDTO.OrderItemDTO item = items.get(i);
                                out.print(item.getItemName() + " " + item.getQuantity() + "개");
                                if (i < items.size() - 1) {
                                    out.print(", ");
                                }
                            }
                        %>
                    </p>
                </div>
                <div>
                    <% if ("IN_PROGRESS".equals(pickup.getStatus().toString())) { %>
                    <span class="text-green-700 text-sm font-bold block whitespace-nowrap">
                        결제완료
                    </span>
                    <% } %>
                    <span class="
                        <% if ("DELIVERING".equals(pickup.getStatus().toString())) { %>
                            text-yellow-400
                        <% } else if ("DELIVERED".equals(pickup.getStatus().toString())) { %>
                            text-green-400
                        <% } else if ("PICKED_UP".equals(pickup.getStatus().toString())) { %>
                            text-blue-400
                        <% } else if ("IN_PROGRESS".equals(pickup.getStatus().toString())) { %>
                            text-red-500
                        <% } else { %>
                            text-gray-400
                        <% } %>
                        text-sm font-bold whitespace-nowrap">
                        <%= pickup.getStatus().getDesc() %>
                    </span>
                </div>
            </div>
            <div class="flex justify-end">
                <button class="mt-2 px-4 py-2 text-sm text-red-500 border border-red-500 rounded-full"
                        onclick="location.href='/pickup-detail?id=<%=pickup.getPickupId()%>'">
                    상세보기
                </button>
            </div>
        </div>
        <% } %>
        <% } %>
    </div>
</div>

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
