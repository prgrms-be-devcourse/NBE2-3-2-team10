<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.team10.washcode.ResponseDTO.review.ReviewResDTO" %>
<%@ page import="java.util.List" %>
<%
    List<ReviewResDTO> reviewList = (List<ReviewResDTO>) request.getAttribute("reviewList");
    if (reviewList == null) {
        reviewList = java.util.Collections.emptyList();
    }
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰 관리</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
    </style>
</head>
<body class="bg-gray-100">
<!-- 메인 컨테이너 -->
<div class="max-w-md mx-auto bg-white shadow-md rounded-lg mt-4">
    <!-- 상단 헤더 -->
    <div class="flex items-center justify-between p-4 border-b">
        <button class="text-gray-500" onclick="history.back()">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
            </svg>
        </button>
        <h1 class="text-lg font-bold">리뷰 관리</h1>
        <div></div>
    </div>

    <!-- 메인 컨텐츠 -->
    <div class="mt-2 space-y-4 px-4 pb-20">
        <%
            for (ReviewResDTO review : reviewList) {
        %>
        <div class="bg-blue-100 p-4 rounded-lg shadow-sm">
            <!-- 작성자 이름 -->
            <p class="font-bold">
                <%= review.getUser_name() %>님
            </p>
            <!-- 리뷰 내용 -->
            <p class="text-sm mt-2">
                <%= review.getContent() %>
            </p>
        </div>
        <%
            }
        %>
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
        function changeSvg() {
            const svgUrl = "https://havebin.s3.ap-northeast-2.amazonaws.com/washpang/footer"
            const path = window.location.pathname;
            // alert(path);

            const homeArray = ["/shop-main", "/pickup-check", "/pickup-delivery", "/shop-review"];
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

        window.onload = () => {
            changeSvg();
        }
    </script>

</body>
</html>