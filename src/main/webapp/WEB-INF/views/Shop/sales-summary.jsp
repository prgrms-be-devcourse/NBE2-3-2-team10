<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>매출액 조회</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
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
        <button onclick="history.back()" class="text-gray-500 hover:text-gray-700">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24"
                 stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7"/>
            </svg>
        </button>
        <h1 class="text-lg font-bold">매출액 조회</h1>
        <button id="date-button"
                onclick="openCalendar()"
                class="button-outline text-blue-500 border-blue-500 hover:bg-blue-100">
        </button>
    </div>
</div>

<div id="sales-container" class="max-w-md mx-auto mt-2 space-y-4 px-4 pb-20">
</div>

<div id="calendar-modal" class="hidden fixed inset-0 bg-gray-800 bg-opacity-50 flex items-center justify-center">
    <div class="bg-white p-4 rounded-lg shadow-lg max-w-sm w-full">
        <div class="flex justify-between items-center mb-4">
            <button id="prev-year" class="text-gray-500 hover:text-gray-700">&lt;</button>
            <span id="current-year" class="font-bold"></span>
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
<footer class="fixed bottom-0 left-0 right-0 bg-white shadow p-4 flex justify-around overflow-x-auto mx-auto max-w-[448px] rounded-t-lg">
    <button class="flex flex-col items-center text-blue-500" onclick="location.href='/shop-main'">
        <img id = "home" src = "./footer/Home.svg" class = "h-6 w-6"/>
        <span class="text-black text-[10pt] mt-1">홈</span>
    </button>
        <button class="flex flex-col items-center text-gray-500" onclick="location.href='/pickup-list'" >
            <img src = "./footer/Bag.svg" class = "h-6 w-6"/>
            <span class="text-black text-[10pt] mt-1">주문내역</span>
        </button>
    <button class="flex flex-col items-center text-gray-500" onclick="location.href='/shop/mypage'">
        <img id = "star" src = "./footer/Star.svg" class = "h-6 w-6"/>
        <span class="text-black text-[10pt] mt-1">내 정보</span>
    </button>
</footer>

<script>
    const BASE_URL = "http://localhost:8080";
    const token = sessionStorage.getItem("accessToken");

    let currentYearJS = new Date().getFullYear();
    let currentMonthJS = new Date().getMonth() + 1;
    let startYearJS = currentYearJS - 2;

    window.onload = function() {
        changeSvg();
        checkAccessToken();


        const dateButton = document.getElementById("date-button");
        dateButton.textContent = formatYearMonth(currentYearJS, currentMonthJS);

        selectMonth(currentYearJS, currentMonthJS);
    };

    function changeSvg() {
        const svgUrl = "https://havebin.s3.ap-northeast-2.amazonaws.com/washpang/footer"
        const path = window.location.pathname;
        // alert(path);

        const homeArray = ["/shop-main", "/pickup-check", "/pickup-delivery", "/shop-review", "/sales-summary"];
        const starArray = ["/shop/mypage", "/shop/myInfoModify", "/shop/myInfo", "/shop/mypage"];

        if (homeArray.includes(path)) {
            document.getElementById('home').src = svgUrl + "/Home_2.svg";
        } else {
            document.getElementById('home').src = svgUrl + "/Home.svg";
        }

        if (starArray.includes(path)) {
            document.getElementById('star').src = svgUrl + "/Star_2.svg";
        } else {
            document.getElementById('star').src = svgUrl + "/Star.svg";
        }
    }

    function checkAccessToken() {
        axios.post(
            BASE_URL + "/api/user/check-login",
            {},
            {
                headers: {
                    "Authorization": "Bearer " + token
                }
            }
        ).then(function(response) {
            if (response.data === false) {
                alert("로그인이 필요합니다.");
                location.href = "/";
            }
        }).catch(function(error) {
            console.error("로그인 체크 에러:", error);
            alert("로그인 정보를 확인할 수 없습니다.");
            location.href = "/";
        });
    }

    function formatYearMonth(year, month) {
        return year + "-" + String(month).padStart(2, "0");
    }

    function openCalendar() {
        renderCalendar();
        document.getElementById("calendar-modal").classList.remove("hidden");
    }

    function closeCalendar() {
        document.getElementById("calendar-modal").classList.add("hidden");
    }

    function renderCalendar() {
        const yearElement = document.getElementById("current-year");
        const monthButtonsContainer = document.getElementById("month-buttons");

        yearElement.textContent = currentYearJS;
        monthButtonsContainer.innerHTML = "";

        for (let m = 1; m <= 12; m++) {
            const button = document.createElement("button");
            button.textContent = (m < 10 ? "0" + m : m) + "월";
            button.className = "button-outline text-blue-500 border-blue-500 hover:bg-blue-100";
            button.addEventListener("click", function() {
                selectMonth(currentYearJS, m);
            });
            monthButtonsContainer.appendChild(button);
        }
    }

    document.getElementById("prev-year").addEventListener("click", function() {
        if (currentYearJS > startYearJS) {
            currentYearJS--;
            renderCalendar();
        }
    });
    document.getElementById("next-year").addEventListener("click", function() {
        const nowYear = new Date().getFullYear();
        if (currentYearJS < nowYear) {
            currentYearJS++;
            renderCalendar();
        }
    });

    function selectMonth(selectedYear, selectedMonth) {
        fetch(BASE_URL + "/api/pickup/sales-summary?year=" + selectedYear + "&month=" + selectedMonth, {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token
            }
        })
            .then(function(response) {
                return response.json();
            })
            .then(function(data) {
                renderSalesData(data, selectedYear, selectedMonth);
                closeCalendar();
            })
            .catch(function(err) {
                console.error("매출 데이터 불러오기 오류:", err);
            });
    }

    function renderSalesData(data, year, month) {
        const container = document.getElementById("sales-container");
        container.innerHTML = "";

        let total = 0;

        if (!data || data.length === 0) {
            container.innerHTML =
                "<div class='bg-white p-4 rounded-lg shadow-md text-center text-gray-500'>" +
                "  매출 데이터가 없습니다." +
                "</div>";
        } else {
            data.forEach(function(dto) {
                const createdDate = new Date(dto.createdAt);
                const yearNum = createdDate.getFullYear();
                const monthNum = String(createdDate.getMonth() + 1).padStart(2, "0");
                const dayNum = String(createdDate.getDate()).padStart(2, "0");

                const dayNames = ["일", "월", "화", "수", "목", "금", "토"];
                const dayOfWeek = createdDate.getDay();
                const dayName = dayNames[dayOfWeek];

                const displayDate = yearNum + "-" + monthNum + "-" + dayNum + " (" + dayName + ")";

                let itemsHtml = "";
                if (dto.orderItems && dto.orderItems.length > 0) {
                    dto.orderItems.forEach(function(item) {
                        total += item.totalPrice;

                        itemsHtml +=
                            "<div class='flex justify-between mb-2'>" +
                            "  <div>" +
                            "    <p class='text-sm font-bold'>" + item.itemName + "</p>" +
                            "    <p class='text-gray-400 text-xs'>" + dto.status + "</p>" +
                            "  </div>" +
                            "  <span class='font-bold text-sm'>" + item.totalPrice + " 원</span>" +
                            "</div>";
                    });
                }

                const boxHtml =
                    "<div class='bg-white p-4 rounded-lg shadow-md mb-2'>" +
                    "  <p class='text-blue-500 font-bold mb-2'>" + displayDate + "</p>" +
                    itemsHtml +
                    "</div>";

                container.insertAdjacentHTML("beforeend", boxHtml);
            });
        }

        const totalBoxHtml =
            "<div class='flex justify-between items-center bg-white p-4 rounded-lg shadow-md'>" +
            "  <span class='font-bold text-gray-700'>총 매출액</span>" +
            "  <span class='font-bold text-blue-500 text-lg'>" + total.toLocaleString() + " 원</span>" +
            "</div>";

        container.insertAdjacentHTML("beforeend", totalBoxHtml);

        const dateButton = document.getElementById("date-button");
        dateButton.textContent = formatYearMonth(year, month);
    }
</script>
</body>
</html>
