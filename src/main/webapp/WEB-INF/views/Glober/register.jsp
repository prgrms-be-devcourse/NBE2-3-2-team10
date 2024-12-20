<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
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
<div class="max-w-md mx-auto bg-white p-6 rounded-lg shadow-md">
    <div class="text-center mb-6">
        <h1 class="text-2xl font-bold">회원가입</h1>
    </div>
    <form id = "signupForm">
        <div class="mb-4">
            <label class="block text-gray-700">아이디</label>
            <input
                    type="email"
                    id = "email"
                    class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    placeholder="${not empty kakaoUserData.email ? kakaoUserData.email : '이메일을 입력해주세요.'}"
                    value="${not empty kakaoUserData.email ? kakaoUserData.email : ''}"
            ${not empty kakaoUserData ? 'disabled' : ''}>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">비밀번호</label>
            <input
                    type="password"
                    placeholder="${not empty kakaoUserData ? '****' : '8~30자리 영대-소문자, 숫자, 특수문자 조합'}"
                    class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    ${not empty kakaoUserData ? 'disabled' : ''}>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">비밀번호 확인</label>
            <input
                    type="password"
                    id = "password"
                    placeholder="${not empty kakaoUserData ? '****' : '8~30자리 영대-소문자, 숫자, 특수문자 조합'}"
                    class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    ${not empty kakaoUserData ? 'disabled' : ''}>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">이름</label>
            <input
                    type="text"
                    id = "name"
                    placeholder="${not empty kakaoUserData.nickname ? kakaoUserData.nickname : '성함을 입력해주세요.'}"
                    class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    value="${not empty kakaoUserData.nickname ? kakaoUserData.nickname : ''}"
                    ${not empty kakaoUserData ? 'disabled' : ''}>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">주소</label>
            <div class="flex">
                <input type="text" id = "address" placeholder="주소를 입력해주세요" class="w-full px-3 py-2 border rounded-l-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
                <button type="button" class="px-4 py-2 bg-blue-500 text-white rounded-r-lg">주소 찾기</button>
            </div>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">전화번호</label>
            <div class="flex">
                <input type="tel" id = "phone" placeholder="휴대 전화 번호를 입력해주세요" class="w-full px-3 py-2 border rounded-l-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
                <button type="button" class="px-4 py-2 bg-blue-500 text-white rounded-r-lg">인증 번호</button>
            </div>
        </div>
        <div class="mb-6">
            <label class="block text-gray-700">유형</label>
            <select id="userRole" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" name="userRole">
                <option value="">고객 또는 상점 관리자를 선택해주세요</option>
                <option value="USER">고객</option>
                <option value="SHOP">상점 관리자</option>
            </select>
        </div>
        <button
                type="button"
                id="submitBtn"
                onclick="reqRegister()"
                class="w-full py-3 mb-4 bg-blue-500 text-white font-bold rounded-lg hover:bg-blue-600">
            회원가입
        </button>
    </form>

    <script>
        const url = "http://localhost:8080";

        function reqRegister() {
            const kakao_id = "${kakaoUserData.id}";
            const kakao_password = "${kakaoUserData.password}";

            const formData = {
                email: document.getElementById('email').value,
                password: kakao_password ? kakao_password : document.getElementById('password').value,
                name: document.getElementById('name').value,
                userRole: document.getElementById('userRole').value,
                address: document.getElementById('address').value,
                phone: document.getElementById('phone').value,
                kakao_id: kakao_id ? kakao_id : null  // 카카오 ID는 있는 경우만 포함
            };

            console.log(formData);  // 디버깅용

            axios.post(url + '/api/user/register', formData)
                // body에 있는 값 출력

                .then(res => {
                    alert('회원가입이 완료되었습니다.');
                    window.location.href = '/';
                })
                .catch(error => {
                    alert(error.response.data);
                });
        }
    </script>
</div>

<div class="pb-20"></div>
</body>
</html>