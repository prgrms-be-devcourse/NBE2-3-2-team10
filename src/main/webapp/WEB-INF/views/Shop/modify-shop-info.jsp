<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>정보 수정</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
    </style>
</head>
<body class="bg-gray-100">
<!-- 메인 컨텐츠 -->
<div class="max-w-md mx-auto bg-white p-6 rounded-lg shadow-md mt-4">
    <div class="text-center mb-6">
        <h1 class="text-2xl font-bold">정보 수정</h1>
    </div>
    <form>
        <div class="mb-4">
            <label class="block text-gray-700">업체명</label>
            <input type="text" placeholder="업체명을 입력하세요"
                   class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">전화번호</label>
            <input type="tel" placeholder="전화번호를 입력하세요"
                   class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">대표자명</label>
            <input type="text" placeholder="대표자명을 입력하세요"
                   class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">주소</label>
            <div class="flex">
                <input type="text" placeholder="주소를 입력하세요"
                       class="w-full px-3 py-2 border rounded-l-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
                <button type="button" class="px-4 py-2 bg-blue-500 text-white rounded-r-lg">주소 찾기</button>
            </div>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">사업자번호</label>
            <input type="text" placeholder="사업자번호를 입력하세요"
                   class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">영업일</label>
            <textarea placeholder="영업일을 입력하세요"
                      class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"></textarea>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">가격표</label>
            <textarea placeholder="가격표를 입력하세요"
                      class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"></textarea>
        </div>

        <!-- 정보 저장 버튼 -->
        <button type="submit"
                class="w-full py-3 mb-4 bg-blue-500 text-white font-bold rounded-lg hover:bg-blue-600">
            정보 저장
        </button>
    </form>
</div>

<!-- 하단 네비게이션 바에 가리지 않도록 여백 추가 -->
<div class="pb-20"></div>

<!-- 하단 네비게이션 -->
<div class="fixed bottom-0 left-0 right-0 bg-white shadow-md">
    <div class="flex justify-around py-2">
        <button onclick="location.href='/shop-main'" class="flex flex-col items-center text-blue-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001 1h6a1 1 0 001-1V10m-7 11V10m0 11H5a1 1 0 01-1-1V10m0 0L3 12" />
            </svg>
            <span>홈</span>
        </button>
        <button onclick="location.href='/pickup-list'" class="flex flex-col items-center text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 7h18M3 12h18m-9 5h9" />
            </svg>
            <span>주문내역</span>
        </button>
        <button class="flex flex-col items-center text-gray-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5.121 17.804A1 1 0 016 17h12a1 1 0 01.879.516l1.5 2.5A1 1 0 0119.5 22h-15a1 1 0 01-.879-1.484l1.5-2.5zM12 2a5 5 0 00-5 5v3a5 5 0 0010 0V7a5 5 0 00-5-5z" />
            </svg>
            <span>마이</span>
        </button>
    </div>
</div>
</body>
</html>