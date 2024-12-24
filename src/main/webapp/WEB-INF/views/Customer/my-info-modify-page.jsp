<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <title>Information Update</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
    </style>
</head>
<body class="bg-gray-100">
<div class="max-w-md mx-auto bg-white shadow-md">
    <header class="flex items-center p-4 border-b">
        <button class="text-xl">&larr;</button>
        <h1 class="flex-grow text-center font-bold">내 정보 수정</h1>
    </header>
    <main class="p-4">
        <section class="mb-6">
            <h2 class="font-bold mb-2">정보 수정</h2>
            <div class="space-y-4">
                <div>
                    <label class="block text-sm font-bold">Email</label>
                    <input type="email" id="email" class="w-full border p-2 rounded" disabled>
                </div>
                <div>
                    <label class="block text-sm font-bold">이름</label>
                    <input type="text" id="name" class="w-full border p-2 rounded" disabled>
                </div>
                <div>
                    <label class="block text-sm font-bold">전화번호</label>
                    <div class="flex">
                        <input type="text" value="" class="flex-grow border p-2 rounded-l">
                        <button class="bg-blue-500 text-white px-4 rounded-r">인증 받기</button>
                    </div>
                    <div class="flex mt-3">
                        <input type="tel" id="CertificationNum" placeholder="인증번호 5자리" class="w-full px-3 py-2 border border-gray-300 rounded-l-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
                        <button type="button" class="px-4 py-2 bg-blue-500 text-white rounded-r-lg hover:bg-blue-400 focus:outline-none">확인</button>
                    </div>
                </div>
            </div>
        </section>
        <section>
            <div class="flex justify-between items-center mb-2">
                <h2 class="font-bold">배송지</h2>
                <button class="text-blue-500" onclick="findAddress()">수정</button>
            </div>
            <div class="border p-4 rounded shadow-sm">
                <span id="address"></span>
            </div>
        </section>
    </main>

    <!-- 회원가입 버튼 -->
    <div class="flex justify-center">
        <button
                type="button"
                id="submitBtn"
                onclick="reqRegister()"
                class="w-[50%] py-3 mb-4 bg-blue-500 text-white font-bold rounded-lg hover:bg-blue-600">
            변경
        </button>
    </div>

    <footer class="fixed bottom-0 left-0 right-0 bg-white border-t">
        <div class="flex justify-around p-2">
            <button class="flex flex-col items-center text-blue-500">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M13 5v6h6m-6 0l-7 7-2-2m0 0l-7-7 7-7" />
                </svg>
                <span>홈</span>
            </button>
            <button class="flex flex-col items-center text-gray-500">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 3h18M3 9h18M3 15h18M3 21h18" />
                </svg>
                <span>주문내역</span>
            </button>
            <button class="flex flex-col items-center text-gray-500">
                <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
                </svg>
                <span>마이</span>
            </button>
        </div>
    </footer>

    <script>
        const url = "http://localhost:8080"

        var name = "";
        var phone = "";
        var address = "";
        var email = "";

        window.onload = () => {
            checkAccessToken();
            getUserInfo();
        }


        function checkAccessToken() {
            axios.post(url + '/api/user/check-login')
                .then(res => {
                    if (res.data === false) {
                        alert("로그인이 필요합니다.");
                        location.href = '/';
                    }
                })
                .catch(error => {
                    alert(error.response.data);
                });
        }

        function getUserInfo() {
            axios.get(url + '/api/user')
                .then(function(response) {
                    email = response.data.email;
                    name = response.data.name;
                    phone = response.data.phone;
                    address = response.data.address;

                    document.getElementById('email').value = email;
                    document.getElementById('name').value = name;
                    document.getElementById('address').textContent = response.data.address
                }).catch(function(error) {
                console.error(error);
            });
        }

        function findAddress() {
            new daum.Postcode({
                oncomplete: function(data) {
                    var addr = '';
                    var extraAddr = '';

                    if (data.userSelectedType === 'R') {
                        addr = data.roadAddress;
                    } else {
                        addr = data.jibunAddress;
                    }

                    if (data.userSelectedType === 'R') {
                        if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                            extraAddr += data.bname;
                        }
                        if (data.buildingName !== '' && data.apartment === 'Y') {
                            extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                        }
                        if (extraAddr !== '') {
                            extraAddr = ' (' + extraAddr + ')';
                        }
                    }

                    document.getElementById("address").textContent = addr + extraAddr;
                    address = addr + extraAddr;
                    document.getElementById("address").focus();
                }
            }).open();
        }
    </script>
</div>
</body>
</html>