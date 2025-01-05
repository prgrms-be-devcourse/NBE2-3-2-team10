<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Web App Prototype</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }

        .hover-img-home:hover {
            content: url("https://havebin.s3.ap-northeast-2.amazonaws.com/washpang/footer/Home_2.svg");
        }

        .hover-img-bag:hover {
            content: url("https://havebin.s3.ap-northeast-2.amazonaws.com/washpang/footer/Bag_2.svg");
        }

        .hover-img-star:hover {
            content: url("https://havebin.s3.ap-northeast-2.amazonaws.com/washpang/footer/Star_2.svg");
        }
    </style>
</head>
<body class="bg-gray-100">

<!-- Header -->
<header class="bg-white shadow p-4 flex justify-between items-center max-w-[448px] overflow-x-auto mx-auto">
    <div class="text-[14px] font-bold" id="myAddress">로그인을 진행해주세요.</div>
    <div class="text-xl">
        <img src="./main/Heart.svg" class="h-[26px] w-[24px]"/>
    </div>
</header>

<!-- Main Content -->
<main class="p-3 space-y-4 max-w-[448px] overflow-x-auto mx-auto bg-gray-100">
    <!-- Buttons -->
    <div class="flex space-x-4">
        <button class="flex-1 bg-[#4AC7D5] text-white py-2 rounded-lg shadow" onclick="window.location.href='/laundryshop-by-map'">내 주변 세탁소 찾기</button>
        <button class="flex-1 bg-[#4AC7D5] text-white py-2 rounded-lg shadow">이용방법</button>
    </div>

    <!-- Categories -->
    <div class="border border-[#E1D9D9] p-1 h-[201px] bg-[#FFFFFF] rounded-lg shadow-md text-[10pt]">
        <div class="mt-2 grid grid-cols-4 ">
            <div class="flex flex-col items-center" onclick="window.location.href='/laundryshop-by-category/SHOES'">
                <img src = "./main/category/shoes.svg" class = "h-[54px] w-[63px]"/>
                <div class="mt-2">신발</div>
            </div>
            <div class="flex flex-col items-center" onclick="window.location.href='/laundryshop-by-category/PADDING'">
                <img src = "./main/category/padding.svg" class = "h-[54px] w-[63px]"/>
                <div class="mt-2">패딩</div>
            </div>
            <div class="flex flex-col items-center" onclick="window.location.href='/laundryshop-by-category/PREMIUM_FABRIC'">
                <img src = "./main/category/premium-fabric.svg" class = "h-[54px] w-[63px]"/>
                <div class="mt-2">프리미엄</div>
            </div>
            <div class="flex flex-col items-center" onclick="alert('구현 중 입니다.')">
                <img src = "./main/category/carrier-sanitation.svg" class = "h-[54px] w-[63px]"/>
                <div class="mt-2">캐리어 소독</div>
            </div>
        </div>

        <div class="mt-2 grid grid-cols-4">
            <div class="flex flex-col items-center" onclick="window.location.href='/laundryshop-by-category/COTTON_LAUNDRY'">
                <img src = "./main/category/cotton-laundry.svg" class = "h-[54px] w-[63px]"/>
                <div class="mt-2">면 세탁물</div>
            </div>
            <div class="flex flex-col items-center" onclick="window.location.href='/laundryshop-by-category/STORAGE_SERVICE'">
                <img src = "./main/category/bedstorage-service.svg" class = "h-[54px] w-[63px]"/>
                <div class="mt-2">보관 서비스</div>
            </div>
            <div class="flex flex-col items-center" onclick="window.location.href='/laundryshop-by-category/BEDDING'">
                <img src = "./main/category/bedding.svg" class = "h-[54px] w-[63px]"/>
                <div class="mt-2">침구</div>
            </div>
        </div>
    </div>

    <!-- Promo Section -->
    <div class="border border-[#E1D9D9] bg-white rounded-lg shadow overflow-hidden">
        <div class="p-4 bg-white text-black font-bold">Promo</div>
        <img src="./upload/mainImg.svg" alt="Promo" class="w-full">
        <div class="p-4 text-lg font-bold">세탁소 이용</div>
    </div>

<%--    <!-- 세탁망 요청 버튼 -->--%>
<%--    <div class="flex justify-center space-x-4 font-bold" style="font-family: 'DXYeoksa', sans-serif;">--%>
<%--        <button class="bg-white mr-auto text-black border border-[#E1D9D9] rounded-[28px] shadow-md justify-center items-center w-[197px] h-[189px]">--%>
<%--            <img src="./main/laundry-basket.svg" class="h-[90px] w-[97px] m-auto mb-3"/>--%>
<%--            <span class="m-auto text-[17px]">세탁망 요청</span>--%>
<%--        </button>--%>
<%--    </div>--%>
</main>

<div class="m-[80px] bg-gray-100"></div>

<!-- Footer -->
<footer class="fixed bottom-0 left-0 right-0 bg-white shadow p-4 flex justify-around overflow-x-auto mx-auto max-w-[448px] rounded-t-lg">
    <button class="flex flex-col items-center text-blue-500" onclick="location.href='/main'">
        <img id = "home" src = "./footer/Home.svg" class = "h-6 w-6"/>
        <span class="text-black text-[10pt] mt-1">홈</span>
    </button>
    <button class="flex flex-col items-center text-gray-500" onclick="location.href='/orderHistory'" >
        <img id = "bag" src = "./footer/Bag.svg" class = "h-6 w-6"/>
        <span class="text-black text-[10pt] mt-1">주문내역</span>
    </button>
    <button class="flex flex-col items-center text-gray-500" onclick="location.href='/mypage'">
        <img id = "star" src = "./footer/Star.svg" class = "h-6 w-6"/>
        <span class="text-black text-[10pt] mt-1">내 정보</span>
    </button>
</footer>

    <script>
        const url = "http://localhost:8080"
        const token = sessionStorage.getItem("accessToken");

        function getUserAddress() {
            axios.get(url + '/api/user/address', {
                headers: {
                    Authorization: 'Bearer ' + token
                }
            }).then(res => {
                const string = res.data.split(' ');
                document.getElementById('myAddress').innerHTML = string[0] + ' ' + string[1] + ' ' + string[2] + " " + "...";
                    //+ '<img src="./main/arrowDown.svg" class="h-[9px] w-[5px]"/>';

            }).catch(error => {
                alert(error.response.data);
            });
        }

        function checkAccessToken() {
            axios.post(url + '/api/user/check-login', {
                headers: {
                    Authorization: 'Bearer ' + token
                }
            }).then(res => {
                sessionStorage.setItem("accessToken", res.data.accessToken);
                getUserAddress();
            }).catch(error => {
                alert("로그인이 만료되었습니다. 다시 로그인해주세요.");
                location.href = '/';
            });
        }

        function changeSvg() {
            const svgUrl = "https://havebin.s3.ap-northeast-2.amazonaws.com/washpang/footer"
            const path = window.location.pathname;
            // alert(currentPath);

            const homeArray = ["/main", "/laundryshop-by-map", "/laundryshop-by-category", "/shop-main", "/pickup-check", "/pickup-delivery" ];
            const orderArray = ["/orderHistory"];
            const starArray = ["/mypage", "/myInfo", "/myInfoModify", "/shop-mypage"];

            if (homeArray.includes(path)) {
                document.getElementById('home').src = svgUrl + "/Home_2.svg";
            } else {
                document.getElementById('home').src = svgUrl + "/Home.svg";
            }

            if (orderArray.includes(path)) {
                document.getElementById('bag').src = svgUrl + "/Bag_2.svg";
            } else {
                document.getElementById('bag').src = svgUrl + "/Bag.svg";
            }

            if (starArray.includes(path)) {
                document.getElementById('star').src = svgUrl + "/Star_2.svg";
            } else {
                document.getElementById('star').src = svgUrl + "/Star.svg";
            }
        }

        window.onload = () => {
            changeSvg();
            checkAccessToken();
        }
    </script>

</body>
</html>