<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.team10.washcode.ResponseDTO.order.OrderResDTO" %>
<%@ page import="java.util.List" %>
<%
    int pickupId = (int) request.getAttribute("pickupId");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>이용내역</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
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
            <div class="flex justify-between items-center" id="top">
                <h2 class="font-bold" id="shop_name"></h2>

            </div>

            <p id="order-detail"> </p>
            <p class="text-gray-500" id="created_at">주문일자 : </p>
            <div class="border-t border-gray-200 mt-2 pt-2">
                <h2 class="font-bold mb-[10px]">결제금액</h2>
                <p id="amount">주문 금액 : 원</p>
                <p id="method">결제 방법 : </p>
            </div>

            <div class="border-t border-gray-200 mt-2 pt-2">
                <h2 class="font-bold mb-[10px]">배달 주소</h2>
                <p id="address"></p>
                <p id="phone">전화번호 : </p>
            </div>
          
            <div class="border-t border-gray-200 mt-2 pt-2">
                <h2 class="font-bold mb-[10px]">요청사항</h2>
                <p id="content" style="color: gray">없음.</p>
            </div>
          
            <div class="flex border-t border-gray-200 pt-2" id="cancel" style="text-align: right; margin-top: 70px">
                <button id="payment" style="display: none;" onclick="kakaoPay()">
<%--                   <img src="https://havebin.s3.ap-northeast-2.amazonaws.com/washpang/img.png"--%>
<%--                        class = "w-[100px] h-[40px] rounded-lg"/>--%>
                    <p class = "bg-blue-500 text-white font-medium py-2 px-4 rounded-lg">결제하기</p>
                </button>
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
  
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
    const url = "http://localhost:8080";
    const token = sessionStorage.getItem("accessToken");
  
    let totalPrice = 0;
    let orderItems = [];
    let paymentId = null;

    // KakaoPay 버튼 보이게 하는 함수
    function showPaymentButton(orderStatus, paymentMethod) {
        if (orderStatus === '결제 대기' && paymentMethod === '카카오페이') {
            document.getElementById("payment").style.display = "block";
            document.getElementById("payment").style.marginRight = "10px"
        }
    }

    // 카카오페이 결제 통신 Axios
    function kakaoPay() {
        // https://velog.io/@ryuneng2/%EC%B9%B4%EC%B9%B4%EC%98%A4%ED%8E%98%EC%9D%B4-API-%EC%97%B0%EB%8F%99-%ED%8C%9D%EC%97%85%EC%B0%BD%EB%9D%84%EC%9A%B0%EA%B8%B0-%EA%B2%B0%EC%A0%9C%EC%8A%B9%EC%9D%B8-%EA%B5%AC%ED%98%84
        // 참조
        let orderItem = "";
        const orderItemsSize = orderItems.length;

        if (orderItemsSize > 1) {
            orderItem = orderItems[0].name + " 외 " + (orderItems.length - 1) + "건";
        } else {
            orderItem = orderItems[0].name;
        }

        const kakaoPayReqDTO = {
            name: orderItem,             // 카카오페이에 보낼 대표 상품명
            totalPrice: totalPrice,      // 총 결제금액
            quantity : orderItemsSize,   // 상품 수량 (목록 수량인듯)
            paymentId : paymentId        // 결제 ID
        };

        axios.post('/api/orders/kakaopay/ready', kakaoPayReqDTO, {
            headers: {
                Authorization: 'Bearer ' + token
            }
        }).then((response) => {
            // 성공 시, 카카오페이 결제 URL로 이동
            const parsedData = JSON.parse(response.headers['readykakaopayres']);

            if(!isMobile()) {
                window.location.href = parsedData.next_redirect_pc_url;
            } else {
                window.location.href = parsedData.next_redirect_mobile_url;
            }
        }).catch((error) => {
            // 에러 처리
            console.error('결제 준비 중 오류:', error);
        });
    }

    function getOrderDetail() {
        axios.get(url + '/api/orders/<%=pickupId%>', {
            headers: {
                Authorization: 'Bearer ' + token
            }
        }).then(res => {
            let orderDetailHtml = '';
            let cancelHtml = '';
            let topHtml = '';
            document.getElementById("shop_name").innerHTML = res.data.shop_name;
            //document.getElementById("status").innerHTML = res.data.status;
            if(res.data.status === '주문 취소' || res.data.status === '픽업 취소') {
                topHtml += '<span class="text-red-500" id="status">' + res.data.status + '</span>';
            } else {
                topHtml += '<span class="text-blue-500" id="status">' + res.data.status + '</span>';
            }
            $("#top").append(topHtml);


            $.each(res.data.order_items, function (i, row) {
                orderDetailHtml += '<p>' + row.item_name + ' ' + row.quantity + '개</p>';
                orderItems.push({name: row.item_name});
            });
            $("#order-detail").append(orderDetailHtml);
            
            showPaymentButton(res.data.status, res.data.method)

            totalPrice = res.data.amount;
            paymentId = res.data.paymentId;

            if (res.data.status === '픽업 신청' || res.data.status === '결제 대기') {
                cancelHtml += '<button type="submit" onClick="cancelOrder()" class="bg-red-100 text-red-500 font-medium py-2 px-4 rounded-lg">주문취소</button>';
            }
            $("#cancel").append(cancelHtml);


            document.getElementById("created_at").innerHTML = '주문일자 : ' + res.data.created_at;
            document.getElementById("amount").innerHTML = '주문 금액 : ' + res.data.amount + '원';
            document.getElementById("method").innerHTML = '결제 방법 : ' + res.data.method;
            document.getElementById("address").innerHTML = res.data.address;
            document.getElementById("phone").innerHTML = '전화번호 : ' + res.data.phone;
            document.getElementById("content").innerHTML =
                (res.data.content != null && res.data.content.trim() !== "") ? res.data.content : '요청사항 없음.';
        }).catch (error => {
            alert(error.response.data);
            location.href = "/orderHistory";
        });

    }

    function cancelOrder() {
        axios.post(url + '/api/orders/cancel', {
            pickup_id : <%=pickupId%>
        }, {
            headers: {
                Authorization: 'Bearer ' + token
            }
        }).then(res => {
            alert(res.response.data);
            location.href = "/orderHistory";
        }).catch (error => {
            alert(error.response.data);
            location.href = "/orderHistory";
        });
    }
  
    function isMobile() {
        const userAgent = navigator.userAgent || navigator.vendor || window.opera;

        // 모바일 기기 식별
        const mobileDevices = /android|iphone|ipad|ipod|blackberry|windows phone|opera mini|iemobile|mobile/i;

        return mobileDevices.test(userAgent);
    }

    window.onload = () => {
        getOrderDetail();
    };
</script>

</body>
</html>