<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>내 정보</title>
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
    <div class="flex items-center p-4 border-b">
        <button class="text-xl">&larr;</button>
        <h1 class="flex-grow text-center text-xl font-bold">내 정보</h1>
        <button class="text-blue-500" onclick="location.href='/myInfoModify'" >수정</button>
    </div>
    <div class="p-4">
        <div class="mb-6">
            <h2 class="text-lg font-bold mb-2">기본 정보</h2>
            <div class="flex justify-between items-center mb-2">
                <span>Email</span>
                <span id = "email">ks06891@naver.com</span>
            </div>
            <div class="flex justify-between items-center mb-2">
                <span>이름</span>
                <span id = "name">통장잔고원</span>
            </div>
            <div class="flex justify-between items-center mb-2">
                <span>전화번호</span>
                <span id = "phone">010-1234-5678</span>
            </div>
        </div>
        <div>
            <h2 class="text-lg font-bold mb-2">배송 정보</h2>
            <div class="mb-2">
                <span class="block font-semibold">배송지</span>
                <span id = "address">서울특별시 송파구 충민로4길 6, 78층 7803동 30004호</span>
            </div>
            <div>
                <span class="block font-semibold">공동현관 출입방법</span>
                <span>밑에서 78층까지 소리치리시면 됩니다.</span>
            </div>
        </div>
    </div>
</div>
<!-- Footer -->
<footer class="fixed bottom-0 left-0 right-0 bg-white shadow p-4 flex justify-around max-w-[600px] overflow-x-auto mx-auto ">
    <button class="flex flex-col items-center text-blue-500" onclick="location.href='/main'">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12h18m-9 9h9" />
        </svg>
        <span>홈</span>
    </button>
    <button class="flex flex-col items-center text-gray-500" onclick="location.href='/orderHistory'" >
        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h18M3 12h18m-9 9h9" />
        </svg>
        <span>주문내역</span>
    </button>
    <button class="flex flex-col items-center text-gray-500" onclick="location.href='/mypage'">
        <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h18M3 12h18m-9 9h9" />
        </svg>
        <span>마이</span>
    </button>
</footer>

    <script>
        const url = "http://localhost:8080"
        const token = sessionStorage.getItem("accessToken");

        function checkAccessToken() {
            axios.post(url + '/api/user/check-login', {
                headers: {
                    Authorization: 'Bearer ' + token
                }
            }).then(res => {
                sessionStorage.setItem("accessToken", res.data.accessToken);
            }).catch(error => {
                alert(error.response.data);
            });
        }

        function getUserInfo() {
            axios.get(url + '/api/user', {
                headers: {
                    Authorization: 'Bearer ' + token
                }
            }).then(function(response) {
                document.getElementById('email').innerHTML = response.data.email;
                document.getElementById('name').innerHTML = response.data.name
                document.getElementById('phone').innerHTML = response.data.phone
                document.getElementById('address').innerHTML = response.data.address
            }).catch(function(error) {
                console.error(error);
            });
        }

        window.onload = () => {
            checkAccessToken();
            getUserInfo();
        }
    </script>
</body>
</html>