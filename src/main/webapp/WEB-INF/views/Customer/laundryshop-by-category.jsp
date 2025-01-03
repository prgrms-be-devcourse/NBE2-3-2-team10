<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>패딩 페이지</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
    </style>
    <script type="text/javascript">
        window.onload = function () {
            fetch(`/api/laundry/category/${category}`)
                .then(response => response.json())
                .then(data => {
                    console.log(data);  // 디버깅용: 데이터 확인

                    let result = '';
                    data.forEach(item => {
                       // var message = `<div style="padding:5px;">\${shop.shop_name}</div>`;

                        const shop = item.shop; // 세탁소 정보
                        const cheapestItem = item.cheapestItem; // 가장 저렴한 항목 정보

                        result += `<div class="bg-white p-4 rounded-lg shadow shop-item" data-id="\${shop.id}">
                                        <div class="flex justify-between items-center">
                                            <div>
                                                <h2 class="text-lg font-bold">\${shop.shop_name}</h2>
                                                <p class="text-gray-500">\${shop.address}</p>
                                            </div>
                                            <img src="https://source.unsplash.com/random/100x100?blanket" alt="Product" class="w-16 h-16 object-cover rounded-lg">
                                        </div>
                                        <div class="mt-2">
                                            <span class="text-xl font-bold">\${cheapestItem.price.toLocaleString()}원</span>
                                            <span class="text-gray-500">/\${cheapestItem.item_name}</span>
                                        </div>
                                    </div>`;
                    });
                    document.getElementById('laundryList').innerHTML = result;

                    document.querySelectorAll('.shop-item').forEach(item => {
                        item.addEventListener('click', function() {
                            const shopId = this.getAttribute('data-id');
                            window.location.href = `/laundryshop-detail/\${shopId}`;
                        });
                    });
                })
                .catch(error => console.error('Error fetching data:', error));
        }
    </script>
</head>
<body class="bg-gray-100">
<div class="max-w-md mx-auto bg-white shadow-lg rounded-lg overflow-hidden">
    <div class="flex items-center justify-between p-4">
        <button class="text-gray-500" onclick="window.location.href='/main'">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
            </svg>
        </button>
        <h1 class="text-lg font-bold">${categoryName}</h1>
        <button class="text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h18M3 12h18m-9 9h9" />
            </svg>
        </button>
    </div>
    <div class="flex space-x-2 px-4">
        <button class="bg-gray-200 text-gray-700 px-3 py-1 rounded-full" onclick="window.location.href='/laundryshop-by-category/PADDING'">패딩</button>
        <button class="bg-gray-200 text-gray-700 px-3 py-1 rounded-full" onclick="window.location.href='/laundryshop-by-category/SHOES'">신발세탁</button>
        <button class="bg-gray-200 text-gray-700 px-3 py-1 rounded-full" onclick="window.location.href='/laundryshop-by-category/PREMIUM_FABRIC'">프리미엄 패브릭</button>
        <button class="bg-gray-200 text-gray-700 px-3 py-1 rounded-full" onclick="window.location.href='/laundryshop-by-category/COTTON_LAUNDRY'">면 세탁물</button>
        <button class="bg-gray-200 text-gray-700 px-3 py-1 rounded-full" onclick="window.location.href='/laundryshop-by-category/BEDDING'">침구</button>
        <button class="bg-gray-200 text-gray-700 px-3 py-1 rounded-full" onclick="window.location.href='/laundryshop-by-category/STORAGE_SERVICE'">보관서비스</button>
    </div>
    <div class="p-4">
        <img src="/images/${category}.jpg" alt="Jacket" class="w-full h-48 object-cover rounded-lg">
    </div>
    <div class="p-4 space-y-4" id="laundryList">


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