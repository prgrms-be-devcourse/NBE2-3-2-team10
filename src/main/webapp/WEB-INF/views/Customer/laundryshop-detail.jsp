<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>세탁소 명</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
    </style>
    <script>
        window.onload = function() {
            const token = sessionStorage.getItem("accessToken")

            // 페이지 로드가 완료된 후 fetch를 사용하여 데이터 받아오기
            fetch(`/api/laundry/${laundryId}`, {
                method: "GET",
                headers: {
                    Authorization: 'Bearer ' + token
                }
            })
                .then(response => response.json())
                .then(data => {
                    console.log(data); // 받아온 데이터를 출력

                    const handledItems = data.handledItems; // handledItems 추출

                    // 중복된 항목 제거
                    const uniqueItems = Array.from(
                        new Set(handledItems.map(item => item.category))
                    ).map(category => {
                        return handledItems.find(item => item.category === category);
                    });

                    // 데이터를 화면에 표시
                    const laundryItemsContainer = document.getElementById('category');
                    uniqueItems.forEach(item => {
                        const itemElement = document.createElement('div');
                        const category = getCategoryEnum(item.category);
                        itemElement.innerHTML = `<img src="https://source.unsplash.com/random/50x50?jacket" alt="\${category}" width="50" height="50">`;
                        laundryItemsContainer.appendChild(itemElement);
                    });

                    const obtn = document.getElementById('obtn');
                    <%--obtn.innerHTML = `<button className="mt-4 w-full bg-blue-500 text-white py-2 rounded-lg"--%>
                    <%--                   onClick="window.location.href='/api/orders/create?id=\${data.id}&laundryShopId=${laundryId}'">세탁 신청--%>
                    <%--                  </button>`--%>
                    obtn.addEventListener('click', function() {
                        window.location.href = `/api/orders/create?id=\${data.id}&laundryShopId=${laundryId}`;
                    });

                })
                .catch(error => {
                    console.error('데이터를 가져오는 중 오류 발생:', error);
                });
        };

        function getCategoryEnum(category) {
            const categoryMap = {
                "SHOES" : "신발",
                "PADDING" : "패딩",
                "PREMIUM_FABRIC" : "프리미엄 패브릭",
                "CARRIER_SANITATION" : "캐리어 소독",
                "COTTON_LAUNDRY" : "면 세탁물",
                "STORAGE_SERVICE" : "보관 서비스",
                "BEDDING" : "침구"
            };
            return categoryMap[category] || "";
        }

    </script>
</head>
<body class="bg-gray-100">
<div class="max-w-md mx-auto bg-white shadow-lg rounded-lg overflow-hidden">
    <div class="flex items-center justify-between p-4">
        <button class="text-gray-500" onclick="history.back()">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
            </svg>
        </button>
        <h1 class="text-lg font-bold">${laundry.shop_name}</h1>
        <button class="text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h18M3 12h18m-9 9h9" />
            </svg>
        </button>
    </div>
    <div class="p-4">
        <div class="bg-white p-4 rounded-lg shadow">
            <h2 class="text-xl font-bold">${laundry.shop_name}</h2>
            <p class="text-gray-500">${laundry.address}</p>
            <p class="text-gray-500">전화 <span role="img" aria-label="phone">📞 ${laundry.phone}</span></p>
            <p class="text-gray-500">휴무: ${laundry.non_operating_days} <span class="text-red-500">휴무</span></p>
            <div class="flex space-x-4 mt-4" id="category">

            </div>
            <button class="mt-4 w-full bg-blue-500 text-white py-2 rounded-lg" id="obtn">세탁 신청</button>
        </div>
        <div class="mt-4 bg-blue-100 p-4 rounded-lg shadow">
            <p class="text-gray-700"><strong>1234님</strong></p>
            <p class="text-gray-700">되게 깨끗하게 잘 해주셨어요. 정말 완전 만족해요 ㅎㅎ</p>
        </div>
        <div class="mt-2 bg-gray-200 p-4 rounded-lg shadow">
            <p class="text-gray-700"><strong>1234님</strong>, 만족하셨다니 다행이에요!</p>
        </div>
    </div>

    <footer class="fixed bottom-0 left-0 right-0 bg-white shadow p-4 flex justify-around overflow-x-auto mx-auto max-w-[448px] rounded-t-lg">
        <button class="flex flex-col items-center text-blue-500" onclick="location.href='/main'">
            <img src = "/footer/Home.svg" class = "h-6 w-6"/>
            <span class="text-black text-[10pt] mt-1">홈</span>
        </button>
        <button class="flex flex-col items-center text-gray-500" onclick="location.href='/orderHistory'" >
            <img src = "/footer/Bag.svg" class = "h-6 w-6"/>
            <span class="text-black text-[10pt] mt-1">주문내역</span>
        </button>
        <button class="flex flex-col items-center text-gray-500" onclick="location.href='/mypage'">
            <img src = "/footer/Star.svg" class = "h-6 w-6"/>
            <span class="text-black text-[10pt] mt-1">내 정보</span>
        </button>
    </footer>
</div>
</body>
</html>