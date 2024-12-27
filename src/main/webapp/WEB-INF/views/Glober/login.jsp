<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
    </style>
</head>
<body class="bg-gray-100 flex items-center justify-center min-h-screen">
<div class="bg-white p-8 rounded-lg shadow-lg w-full max-w-sm">
    <div class="flex justify-center mb-6">
        <img src = "./upload/logo.svg" class = "w-[130px]"/>
    </div>
    <div>
        <div class="mb-4">
            <label class="block text-gray-700 mb-2" for="email">아이디</label>
            <input type="email" id="email" placeholder="이메일을 입력해주세요." class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-yellow-500">
        </div>
        <div class="mb-6">
            <label class="block text-gray-700 mb-2" for="password">비밀번호</label>
            <input type="password" id="password" placeholder="비밀번호를 입력해주세요." class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-yellow-500">
        </div>
        <button type="submit" onclick="login()" class="w-full bg-gray-300 text-gray-700 py-2 rounded mb-4 hover:bg-gray-400">로그인</button>
        <div class="text-center text-gray-500 mb-4">
            <a href="register" class="hover:underline">회원가입</a>
        </div>
        <div class="flex items-center justify-between mb-4">
            <hr class="w-full border-gray-300">
            <span class="px-2 text-gray-500 whitespace-nowrap">간편로그인</span>
            <hr class="w-full border-gray-300">
        </div>
        <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=${kakaoApiKey}&redirect_uri=${redirectUri}">
            <img src = "./upload/kakao_login.png"/>
        </a>
    </div>

    <script>
        const url = "http://localhost:8080";

        function login() {
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;

            axios.post(url + '/api/user/login', {
                email: email,
                password: password
            })
            .then(res => {
                sessionStorage.setItem("accessToken", res.data.accessToken); // 세션 스토리지에 저장
                alert("환영합니다");

                axios.get(url + '/api/user/role', {
                    headers: {
                        Authorization: 'Bearer ' + res.data.accessToken
                    }
                }).then(res => {
                    if(res.data.role==="USER"){
                        console.log(res.data.role)
                        location.href = "/main";
                    }else if(res.data.role==="SHOP"){
                        location.href = "/shop-main";
                    }

                })
            })
            .catch (error => {
                alert(error.response.data);
            })
        }

        function checkAccessToken(token) {
            axios.post(url + '/api/user/check-login', {
                headers: {
                    Authorization: 'Bearer ' + token
                }
            }).then(res => {
                sessionStorage.setItem("accessToken", res.data.accessToken);

                location.href = "/main";
            })
        }

        window.onload = () => {
            const token = sessionStorage.getItem("accessToken");
            checkAccessToken(token);
        }
    </script>
</div>
</body>
</html>