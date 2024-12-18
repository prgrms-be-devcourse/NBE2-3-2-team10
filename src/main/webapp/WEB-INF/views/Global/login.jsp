<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <script src="https://cdn.tailwindcss.com"></script>
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
        <svg xmlns="http://www.w3.org/2000/svg" class="h-12 w-12 text-yellow-500" fill="none" viewBox="0 0 24 24" stroke="currentColor">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 11c0-1.657-1.343-3-3-3s-3 1.343-3 3 1.343 3 3 3 3-1.343 3-3zm0 0c0 1.657 1.343 3 3 3s3-1.343 3-3-1.343-3-3-3-3 1.343-3 3zm0 0V7m0 8v2m0 0h-2m2 0h2" />
        </svg>
    </div>
    <form>
        <div class="mb-4">
            <label class="block text-gray-700 mb-2" for="email">아이디</label>
            <input type="email" id="email" placeholder="이메일을 입력해주세요." class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-yellow-500">
        </div>
        <div class="mb-6">
            <label class="block text-gray-700 mb-2" for="password">비밀번호</label>
            <input type="password" id="password" placeholder="비밀번호를 입력해주세요." class="w-full px-3 py-2 border border-gray-300 rounded focus:outline-none focus:ring-2 focus:ring-yellow-500">
        </div>
        <button type="submit" class="w-full bg-gray-300 text-gray-700 py-2 rounded mb-4 hover:bg-gray-400">로그인</button>
        <div class="text-center text-gray-500 mb-4">
            <a href="#" class="hover:underline">회원가입</a>
        </div>
        <div class="flex items-center justify-between mb-4">
            <hr class="w-full border-gray-300">
            <span class="px-2 text-gray-500 whitespace-nowrap">간편로그인</span>
            <hr class="w-full border-gray-300">
        </div>
        <button type="button" class="w-full bg-yellow-400 text-black py-2 rounded flex items-center justify-center hover:bg-yellow-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6 mr-2" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 12h.01M12 12h.01M16 12h.01M9 16h6m-7 4h8a2 2 0 002-2V6a2 2 0 00-2-2H7a2 2 0 00-2 2v12a2 2 0 002 2z" />
            </svg>
            카카오로 시작하기
        </button>
    </form>
</div>
</body>
</html>