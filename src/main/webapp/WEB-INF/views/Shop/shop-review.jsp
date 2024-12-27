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
<div class="fixed bottom-0 left-0 right-0 bg-white shadow-md">
    <div class="flex justify-around py-2">
        <!-- 홈 버튼 -->
        <button onclick="location.href='/shop-main'" class="flex flex-col items-center text-blue-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12h18M3 6h18M3 18h18" />
            </svg>
            <span class="text-xs font-medium">홈</span>
        </button>

        <!-- 주문내역 버튼 -->
        <button onclick="location.href='/pickup-list'" class="flex flex-col items-center text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
            </svg>
            <span class="text-xs font-medium">주문내역</span>
        </button>

        <!-- 마이페이지 버튼 -->
        <button class="flex flex-col items-center text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3" />
            </svg>
            <span class="text-xs font-medium">마이페이지</span>
        </button>
    </div>
</div>
</body>
</html>