<%@ page import="org.team10.washcode.entity.HandledItems" %>
<%@ page import="org.team10.washcode.RequestDTO.order.OrderItemReqDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.team10.washcode.entity.HandledItems" %>
<%@ page import="org.team10.washcode.Enum.LaundryCategory" %>
<%
    //List<OrderItemReqDTO> handledItems = (List<OrderItemReqDTO>) request.getAttribute("handledItems");
    List<HandledItems> handledItems = (List<HandledItems>) request.getAttribute("handledItems");

    int laundry_id = (int) request.getAttribute("laundry_id");
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
        /*
        // 카테고리와 수량 요소 선택
        const itemSelect = document.getElementById('item_id');
        const quantityInput = document.getElementById('quantity');
        const totalPriceInput = document.getElementById('totalPrice');

        // 총 금액 계산 함수
        function calculateTotalPrice() {
            // 선택된 옵션에서 data-price 값 가져오기
            const selectedOption = itemSelect.options[itemSelect.selectedIndex];
            const price = parseFloat(selectedOption.getAttribute('data-price')) || 0;

            // 수량 값 가져오기
            const quantity = parseInt(quantityInput.value) || 0;

            // 총 금액 계산
            const totalPrice = price * quantity;

            // 총 금액 표시
            totalPriceInput.value = totalPrice.toLocaleString('ko-KR') + '원'; // 천 단위 콤마 추가
        }

        // 이벤트 리스너 추가
        itemSelect.addEventListener('change', calculateTotalPrice);
        quantityInput.addEventListener('input', calculateTotalPrice);

        // 페이지 로드 시 초기 계산
        document.addEventListener('DOMContentLoaded', calculateTotalPrice);*/
    </script>
</head>
<body class="bg-gray-100">
<!-- Header -->
<div class="max-w-md mx-auto bg-white shadow-lg rounded-lg overflow-hidden mt-4">
    <div class="flex items-center justify-between p-4">
        <button class="text-gray-500" onclick="location.href='/laundryshop-detail/<%=laundry_id%>'" >
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
    <div class="max-w-md mx-auto bg-white shadow-lg rounded-lg overflow-hidden mt-4">
        <div class="p-4">
            <div class="bg-gray-100 p-4 rounded-lg mb-4">
                <h2 class="text-sm font-bold" id="name">수거/배달 주소 ()</h2>
                <p class="text-gray-700" id="address"></p>
            </div>
            <div class="flex items-center mb-4">
                <img src="https://source.unsplash.com/random/50x50?person" alt="Profile" class="w-12 h-12 rounded-full mr-4">
                <p class="text-gray-700" id="shop_name"></p>
            </div>
            <div class="bg-gray-100 p-4 rounded-lg mb-4">
                <p class="text-sm font-bold">세탁물 수거는 매일 오후 6시 이후 일괄 수거 됩니다</p>
                <div class="flex items-center mt-2">
                    <input type="checkbox" id="confirm" class="mr-2" checked>
                    <label for="confirm" class="text-gray-700">네, 확인했습니다</label>
                </div>
            </div>

            <!-- 카테고리 -->
            <div class="mb-4">
                <label for="category">카테고리 : </label>
                    <select name="item_id" id="category" class="form-select" aria-label="Select item" required>

                    </select>
            </div>

            <!-- 의류 개수 입력 -->
            <div class="mb-4">
                <label for="quantity" class="block text-sm font-bold mb-2">맡기는 의류의 개수</label>
                <input type="number" name="quantity" id="quantity" class="w-full border rounded-lg p-2" value="1" required>
            </div>

            <!-- 요청 사항 -->
            <div class="mb-4">
                <label for="content" class="block text-sm font-bold mb-2">요청 사항</label>
                <textarea id="content" name="content" class="w-full border rounded-lg p-2" placeholder="세탁소에 요청하실 사항을 입력해주세요(공동현관 비밀번호 등)"></textarea>
            </div>

            <%-- 결제수단--%>
            <div>
            <label for="method">결제방법</label>
            <select name="method" id="method" class="form-select form-select-sm" aria-label="Small select example" >
                <option value="현금">현금(만나서 결제)</option>
                <option value="신용카드">신용카드</option>
                <option value="카카오페이">카카오페이</option>
                <option value="토스페이">토스페이</option>
            </select>
            </div>

<%--            <!-- 총 금액 표시 -->--%>
<%--            <div class="mb-4">--%>
<%--                <label for="totalPrice" class="block text-sm font-bold mb-2">총 금액</label>--%>
<%--                <input type="text" id="totalPrice" class="w-full border rounded-lg p-2 bg-gray-100" readonly>--%>
<%--            </div>--%>

            <div class="bg-gray-100 p-4 rounded-lg mb-4">
                <div class="flex items-center">
                    <input type="checkbox" id="terms" class="mr-2">
                    <label for="terms" class="text-gray-700">세탁 서비스 고지사항에 동의합니다</label>
                </div>
            </div>

            <button type="submit" onclick="createOrder()" class="w-full bg-blue-500 text-white py-2 rounded-lg">수거신청</button>
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

<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
    const url = "http://localhost:8080";
    const token = sessionStorage.getItem("accessToken");

    function getOrderInfo() {
        axios.get(url + '/api/orders/info/<%=laundry_id%>', {
            headers: {
                Authorization: 'Bearer ' + token
            }
        }).then(res => {
            let categoryHtml ='';

            document.getElementById("name").innerHTML = '수거/배달주소 ('+res.data.name+')';
            document.getElementById("address").innerHTML = res.data.address;
            document.getElementById("shop_name").innerHTML = res.data.shop_name;

            if(res.data.category !=null){
                $.each(res.data.category,function(i,row) {
                    categoryHtml += '<option value="'+ row.item_id + '">' +
                        row.item_name +' (' + row.price + '원) </option>';
                });
            } else {
                categoryHtml+= '<option value="">세탁 가능한 품목이 없습니다</option>';
            }

            $("#category").append(categoryHtml);
        });
    }


    function createOrder() {
        const selectCategory = document.getElementById('category');
        const selectMethod = document.getElementById('method');

        axios.post(url + '/api/orders',{
            laundryshop_id : <%=laundry_id%>,
            content : document.getElementById('content').value,
            item_id : selectCategory.options[selectCategory.selectedIndex].value,
            quantity : document.getElementById('quantity').value,
            paymentMethod : selectMethod.options[selectMethod.selectedIndex].value
        }, {
            headers: {
                Authorization: 'Bearer ' + token
            }
        }).then(res => {
            alert("수거 요청 완료");
            location.href= "/orderHistory/" + res.data;
        });
    }


    window.onload = () => {
        getOrderInfo();
    };
</script>
</body>
</html>
