<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>이용신청</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
    </style>
    <script>
        document.getElementById('btn_order').addEventListener('click', async function (e) {
            e.preventDefault(); // 기본 폼 제출 방지

            const laundryShopId = document.querySelector('input[name="laundryShopId"]').value;
            const name = "홍길동"; // 예시 이름 (서버에서 자동 매핑되거나 사용자 이름을 가져올 수 있음)
            const address = "서울시 성북구"; // 주소 예시 (실제 데이터로 교체 필요)
            const content = document.querySelector('textarea[name="content"]').value;
            const quantity = document.querySelector('input[name="quantity"]').value;

            const data = {
                laundryshop_id: parseInt(laundryShopId),
                name: name,
                address: address,
                content: content,
                pickupItem: [
                    {
                        item_id: itemId,  // 여기서 item_id를 전달
                        quantity: parseInt(quantity),
                        total_price: "1000"
                    }
                ]
            };

            try {
                const response = await fetch('/api/orders/create', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(data),
                });

                if (response.ok) {
                    alert('수거 신청이 완료되었습니다.');
                } else {
                    alert('수거 신청에 실패했습니다.');
                }
            } catch (error) {
                console.error('Error:', error);
                alert('수거 신청 중 오류가 발생했습니다.');
            }
        });
    </script>

</head>
<body class="bg-gray-100">
<!-- Header -->
<div class="max-w-md mx-auto bg-white shadow-lg rounded-lg overflow-hidden mt-4">
    <div class="flex items-center justify-between p-4">
        <button class="text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
            </svg>
        </button>
        <h1 class="text-lg font-bold">이용신청</h1>
        <button class="text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h18M3 12h18m-9 9h9" />
            </svg>
        </button>
    </div>
</div>


<!-- Main Content -->
<form method="POST" action="/api/orders/create">
    <input type="hidden" name="userId" value="${user.id}">
<div class="max-w-md mx-auto bg-white shadow-lg rounded-lg overflow-hidden mt-4">
    <div class="p-4">
        <div class="bg-gray-100 p-4 rounded-lg mb-4">
            <h2 class="text-sm font-bold">수거/배달 주소 (${user.name})</h2>
            <p class="text-gray-700">${user.address}</p>
            <p class="text-gray-700">공동현관 비밀번호: 없음</p>
        </div>
        <input type="hidden" name="laundryShopId" value="${laundryShop.id}">
        <div class="flex items-center mb-4">
            <img src="https://source.unsplash.com/random/50x50?person" alt="Profile" class="w-12 h-12 rounded-full mr-4">
            <p class="text-gray-700">${laundryShop.shop_name}</p>
        </div>
        <div class="bg-gray-100 p-4 rounded-lg mb-4">
            <p class="text-sm font-bold">세탁물 수거는 매일 오후 6시 이후 일괄 수거 됩니다</p>
            <div class="flex items-center mt-2">
                <input type="checkbox" id="confirm" class="mr-2" checked>
                <label for="confirm" class="text-gray-700">네, 확인했습니다</label>
            </div>
        </div>
        <%-- 의류 개수 입력 --%>
        <div class="mb-4">
            <label for="quantity" class="block text-sm font-bold mb-2">맡기는 의류의 개수</label>
            <input type="number" name="quantity" id="quantity" class="w-full border rounded-lg p-2" value="1" required>
        </div>
        <%--요청사항--%>
        <div class="mb-4">
            <label for="request" class="block text-sm font-bold mb-2">요청 사항</label>
            <textarea id="request" name="content" class="w-full border rounded-lg p-2" placeholder="세탁소에 요청하실 사항을 입력해주세요"></textarea>
        </div>
        <div class="bg-gray-100 p-4 rounded-lg mb-4">
            <div class="flex items-center">
                <input type="checkbox" id="terms" class="mr-2">
                <label for="terms" class="text-gray-700">세탁 서비스 고지사항에 동의합니다</label>
            </div>
        </div>
        <button type="submit" id="btn_order" class="w-full bg-blue-500 text-white py-2 rounded-lg">수거신청</button>
    </div>
</div>
</form>
<!-- Footer -->
<div class="fixed bottom-0 left-0 right-0 flex justify-around bg-white p-4 border-t">
    <button class="flex flex-col items-center text-blue-500">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12h18m-9 9h9" />
        </svg>
        <span>홈</span>
    </button>
    <button class="flex flex-col items-center text-gray-500">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h18M3 12h18m-9 9h9" />
        </svg>
        <span>주문내역</span>
    </button>
    <button class="flex flex-col items-center text-gray-500">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h18M3 12h18m-9 9h9" />
        </svg>
        <span>마이</span>
    </button>
</div>
</body>
</html>