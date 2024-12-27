<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*" %>
<%@ page import="org.team10.washcode.ResponseDTO.pickup.PickupSalesSummeryDTO" %>
<%
    Calendar calendar = Calendar.getInstance();
    int currentYear = calendar.get(Calendar.YEAR);
    int currentMonth = calendar.get(Calendar.MONTH) + 1;
    int startYear = currentYear - 2;

    List<PickupSalesSummeryDTO> pickupList = (List<PickupSalesSummeryDTO>) request.getAttribute("pickupList");

    long argTotalSum = 0;
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
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24"
                 stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"/>
            </svg>
        </button>
        <h1 class="text-lg font-bold">매출액 조회</h1>
        <!-- 날짜 선택 버튼 -->
        <button id="date-button"
                onclick="openCalendar()"
                class="button-outline text-blue-500 border-blue-500 hover:bg-blue-100">
            <%= currentYear %>-<%= String.format("%02d", currentMonth) %>
        </button>
    </div>
</div>


<!-- 매출 내역을 동적으로 넣을 컨테이너 -->
<div id="sales-container" class="max-w-md mx-auto mt-2 space-y-4 px-4 pb-20">
    <%
        if (pickupList != null && !pickupList.isEmpty()) {
            for (PickupSalesSummeryDTO dto : pickupList) {

                Calendar cal = Calendar.getInstance();
                cal.setTime(dto.getCreatedAt());
                int y = cal.get(Calendar.YEAR);
                int m = cal.get(Calendar.MONTH) + 1;
                int d = cal.get(Calendar.DAY_OF_MONTH);

                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
                String[] dayNames = {"일", "월", "화", "수", "목", "금", "토"};
                String dayName = dayNames[dayOfWeek - 1];

                String displayDate = String.format("%04d-%02d-%02d (%s)", y, m, d, dayName);
    %>
    <!-- 날짜별 박스 -->
    <div class="bg-white p-4 rounded-lg shadow-md mb-2">
        <!-- 날짜 표시 -->
        <p class="text-blue-500 font-bold mb-2"><%= displayDate %></p>

        <%
            if (dto.getOrderItems() != null) {
                for (PickupSalesSummeryDTO.OrderItemDTO item : dto.getOrderItems()) {
                    argTotalSum += item.getTotalPrice();
        %>
        <div class="flex justify-between mb-2">
            <div>
                <!-- 품목 이름 -->
                <p class="text-sm font-bold"><%= item.getItemName() %></p>
                <!-- 상태 -->
                <p class="text-gray-400 text-xs"><%= dto.getStatus() %></p>
            </div>
            <!-- 금액 -->
            <span class="font-bold text-sm"><%= item.getTotalPrice() %> 원</span>
        </div>
        <%
                }
            }
        %>
    </div>
    <%
        }
    } else {
    %>
    <div class="bg-white p-4 rounded-lg shadow-md text-center text-gray-500">
        매출 데이터가 없습니다.
    </div>
    <%
        }
    %>

    <!-- 총 매출액 박스 -->
    <div class="flex justify-between items-center bg-white p-4 rounded-lg shadow-md">
        <span class="font-bold text-gray-700">총 매출액</span>
        <!-- 3자리 콤마 등 포맷 -->
        <span class="font-bold text-blue-500 text-lg">
            <%= String.format("%,d", argTotalSum) %> 원
        </span>
    </div>
</div>

<!-- 달력 모달 -->
<div id="calendar-modal" class="hidden fixed inset-0 bg-gray-800 bg-opacity-50 flex items-center justify-center">
    <div class="bg-white p-4 rounded-lg shadow-lg max-w-sm w-full">
        <div class="flex justify-between items-center mb-4">
            <button id="prev-year" class="text-gray-500 hover:text-gray-700">&lt;</button>
            <span id="current-year" class="font-bold"><%= currentYear %></span>
            <button id="next-year" class="text-gray-500 hover:text-gray-700">&gt;</button>
        </div>
        <div id="month-buttons" class="grid grid-cols-3 gap-2"></div>
        <div class="mt-4 text-right">
            <button onclick="closeCalendar()" class="button-outline text-gray-500 border-gray-500 hover:bg-gray-100">
                닫기
            </button>
        </div>
    </div>
</div>

<!-- 하단 네비게이션 -->
<div class="fixed bottom-0 left-0 right-0 bg-white shadow-md">
    <div class="flex justify-around py-2">
        <!-- 홈 버튼 -->
        <button onclick="location.href='/shop-main'" class="flex flex-col items-center text-blue-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24"
                 stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="M3 12l2-2m0 0l7-7 7 7M13 5v6h6m-6 0l-7 7-2-2m9 2h6"/>
            </svg>
            <span class="text-xs font-medium mt-1">홈</span>
        </button>

        <!-- 주문내역 버튼 -->
        <button onclick="location.href='/pickup-list'" class="flex flex-col items-center text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24"
                 stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"/>
            </svg>
            <span class="text-xs font-medium mt-1">주문내역</span>
        </button>

        <!-- 마이페이지 버튼 -->
        <button class="flex flex-col items-center text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24"
                 stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                      d="M12 11c0 1.104-.896 2-2 2s-2-.896-2-2 .896-2 2-2 2 .896 2 2zm0 0c0 1.104.896 2 2 2s2-.896 2-2-.896-2-2-2-2 .896-2 2zm0 0v6m0 0H9m3 0h3"/>
            </svg>
            <span class="text-xs font-medium mt-1">마이</span>
        </button>
    </div>
</div>

<script>
    const startYear = <%= startYear %>;
    let currentYear = <%= currentYear %>;

    function openCalendar() {
        renderCalendar();
        document.getElementById('calendar-modal').classList.remove('hidden');
    }

    function closeCalendar() {
        document.getElementById('calendar-modal').classList.add('hidden');
    }

    function renderCalendar() {
        const yearElement = document.getElementById('current-year');
        const monthButtonsContainer = document.getElementById('month-buttons');

        yearElement.textContent = currentYear;
        monthButtonsContainer.innerHTML = '';

        for (let month = 1; month <= 12; month++) {
            const button = document.createElement('button');
            button.textContent = (month < 10 ? '0' + month : month) + '월';

            button.className = 'button-outline text-blue-500 border-blue-500 hover:bg-blue-100';
            button.addEventListener('click', () => selectMonth(currentYear, month));
            monthButtonsContainer.appendChild(button);
        }

        console.log(currentYear);
        console.log(startYear);
        console.log(`Rendered months for year: <%=currentYear%>`);
    }

    document.getElementById('prev-year').addEventListener('click', () => {
        if (currentYear > startYear) {
            currentYear--;
            renderCalendar();
        }
    });

    document.getElementById('next-year').addEventListener('click', () => {
        if (currentYear < <%= currentYear %>) {
            currentYear++;
            renderCalendar();
        }
    });

    function selectMonth(selectedYear, selectedMonth) {
        fetch("/api/pickup/sales-summary?year=" + selectedYear + "&month=" + selectedMonth)
            .then(function(response) {
                return response.json();
            })
            .then(function(data) {
                console.log("가져온 데이터: ", data);

                var container = document.getElementById("sales-container");
                container.innerHTML = "";

                var total = 0;

                data.forEach(function(dto) {
                    var createdDate = new Date(dto.createdAt);
                    var yearNum = createdDate.getFullYear();
                    var monthNum = String(createdDate.getMonth() + 1).padStart(2, "0");
                    var day = String(createdDate.getDate()).padStart(2, "0");

                    var dayNames = ["일", "월", "화", "수", "목", "금", "토"];
                    var dayOfWeek = createdDate.getDay(); // 0~6
                    var dayName = dayNames[dayOfWeek];

                    var displayDate = yearNum + "-" + monthNum + "-" + day + " ("+ dayName +")";

                    // orderItems를 HTML로 변환
                    var itemsHtmlArr = dto.orderItems.map(function(item) {
                        total += item.totalPrice;

                        return '<div class="flex justify-between mb-2">'
                            + '  <div>'
                            + '    <p class="text-sm font-bold">' + item.itemName + '</p>'
                            + '    <p class="text-gray-400 text-xs">' + dto.status + '</p>'
                            + '  </div>'
                            + '  <span class="font-bold text-sm">' + item.totalPrice + ' 원</span>'
                            + '</div>';
                    });

                    var itemsHtml = itemsHtmlArr.join("");

                    // 박스 하나의 HTML
                    var boxHtml = '<div class="bg-white p-4 rounded-lg shadow-md mb-2">'
                        + '  <p class="text-blue-500 font-bold mb-2">' + displayDate + '</p>'
                        + itemsHtml
                        + '</div>';

                    container.insertAdjacentHTML("beforeend", boxHtml);
                });

                // 총 매출액 박스
                var totalHtml = '<div class="flex justify-between items-center bg-white p-4 rounded-lg shadow-md">'
                    + '  <span class="font-bold text-gray-700">총 매출액</span>'
                    + '  <span class="font-bold text-blue-500 text-lg">' + total.toLocaleString() + ' 원</span>'
                    + '</div>';

                container.insertAdjacentHTML("beforeend", totalHtml);

                var dateButton = document.getElementById("date-button");
                var formattedMonth = (selectedMonth < 10 ? "0" + selectedMonth : selectedMonth);
                dateButton.textContent = selectedYear + "-" + formattedMonth;

                closeCalendar();
            })
            .catch(function(err) {
                console.error(err);
            });
    }


</script>

</body>
</html>
