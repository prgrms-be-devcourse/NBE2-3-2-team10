<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
    <form id="signupForm">
        <!-- 아이디 입력란 -->
        <div class="mb-4">
            <label class="block text-gray-700">아이디</label>
            <div class = "flex">
                <input
                        type="email"
                        id="email"
                        class="w-[83%] h-10 px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                        placeholder="${not empty kakaoUserData.email ? kakaoUserData.email : '이메일을 입력해주세요.'}"
                        value="${not empty kakaoUserData.email ? kakaoUserData.email : ''}"
                ${not empty kakaoUserData ? 'disabled' : ''}>
                <button
                        type="button"
                        onclick="checkEmailDuplication()"
                        class="w-[17%] px-1 py-2 h-10 ml-1 text-sm text-[#807171] rounded-lg bg-[#E4E4E4] border border-gray-300 hover:text-[#6E6060]" >
                    중복확인
                </button>
            </div>
        </div>

        <!-- 비밀번호 입력란 -->
        <div class="mb-4">
            <label class="block text-gray-700">비밀번호</label>
            <input
                    type="password"
                    id="password"
                    placeholder="${not empty kakaoUserData ? '****' : '8~30자리 영대-소문자, 숫자, 특수문자 조합'}"
                    class="w-full h-10 px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            ${not empty kakaoUserData ? 'disabled' : ''}>
        </div>

        <!-- 비밀번호 확인 입력란 -->
        <div class="mb-4">
            <label class="block text-gray-700">비밀번호 확인</label>
            <input
                    type="password"
                    id="password2"
                    placeholder="${not empty kakaoUserData ? '****' : '8~30자리 영대-소문자, 숫자, 특수문자 조합'}"
                    class="w-full h-10 px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            ${not empty kakaoUserData ? 'disabled' : ''}>
        </div>

        <!-- 이름 입력란 -->
        <div class="mb-4">
            <label class="block text-gray-700">이름</label>
            <input
                    type="text"
                    id="name"
                    placeholder="${not empty kakaoUserData.nickname ? kakaoUserData.nickname : '성함을 입력해주세요.'}"
                    class="w-full h-10 px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    value="${not empty kakaoUserData.nickname ? kakaoUserData.nickname : ''}"
            ${not empty kakaoUserData ? 'disabled' : ''}>
        </div>

        <!-- 주소 입력란 -->
        <div class="mb-4">
            <label class="block text-gray-700 mb-2">주소</label>
            <div class="flex">
                <input
                        type="text"
                        id="address"
                        placeholder="주소를 입력해주세요"
                        class="w-[83%] h-10 px-3 py-2 mb-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                        disabled >
                <button
                        type="button"
                        onclick="findAddress()"
                        class="w-[17%] px-1 py-2 h-10 ml-1 text-sm text-[#807171] rounded-lg bg-[#E4E4E4] border border-gray-300 hover:text-[#6E6060]">
                    주소 찾기
                </button>
            </div>
            <input
                    type="text"
                    id="detailAddress"
                    placeholder="상세 주소를 입력해주세요"
                    class="w-full h-10 px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" >
        </div>

        <!-- 전화번호 입력란 -->
        <div class="mb-4">
            <label class="block text-gray-700">전화번호</label>
            <div class="flex">
                <input type="tel" id="phone" placeholder="휴대 전화 번호를 입력해주세요" class="w-[83%] h-10 px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
                <button type="button" class="w-[17%] px-1 py-2 h-10 ml-1 text-sm text-[#807171] rounded-lg bg-[#E4E4E4] border border-gray-300 hover:text-[#6E6060]">인증 번호</button>
            </div>
            <div class="flex mt-2">
                <input type="tel" id="CertificationNum" placeholder="인증번호 5자리" class="w-[83%] h-10 px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
                <button type="button" class="w-[17%] px-1 py-2 h-10 ml-1 text-sm text-[#807171] rounded-lg bg-[#E4E4E4] border border-gray-300 hover:text-[#6E6060]">확인</button>
            </div>
        </div>

        <!-- 유저 유형 선택 -->
        <div class="mb-6">
            <label class="block text-gray-700">유형</label>
            <select id="userRole" class="w-full h-10 px-3 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" name="userRole">
                <option value="">고객 또는 상점 관리자를 선택해주세요</option>
                <option value="USER">고객</option>
                <option value="SHOP">상점 관리자</option>
            </select>
        </div>

        <!-- 회원가입 버튼 -->
        <button
                type="button"
                id="submitBtn"
                onclick="reqRegister()"
                class="w-full py-3 mb-4 text-white font-bold rounded-lg bg-[#4AC7D5] hover:bg-[#39b2c3]">
            회원가입
        </button>
    </form>

    <script>
        const url = "http://localhost:8080";
        var isPhoneCertified = false;

        function phoneCertification() {
            const phone = document.getElementById('phone').value;
            if (!phone) {
                alert('휴대 전화 번호를 입력해주세요.');
                return;
            }

            axios.post(url + '/api/user/phoneCertification', { phone })
                .then(res => {
                    alert('인증번호가 발송되었습니다.');
                    isPhoneCertified = true;
                })
                .catch(error => {
                    alert(error.response.data);
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

                    document.getElementById("address").value = addr + extraAddr;
                    document.getElementById("address").focus();
                }
            }).open();
        }

        function reqRegister() {
            const kakao_id = "${kakaoUserData.id}";
            const kakao_password = "${kakaoUserData.password}";

            const formData = {
                email: document.getElementById('email').value,
                password: kakao_password ? kakao_password : document.getElementById('password').value,
                name: document.getElementById('name').value,
                role: document.getElementById('userRole').value,
                address: document.getElementById('address').value + " " + document.getElementById('detailAddress').value,
                phone: document.getElementById('phone').value,
                kakao_id: kakao_id ? kakao_id : null
            };

            console.log(formData);

            if (!formData.email || !formData.password || !formData.name || !formData.role || !formData.address || !formData.phone) {
                alert('모든 항목을 입력해주세요.');
                return;
            }

            if (formData.password !== document.getElementById('password2').value) {
                if (!kakao_id) {
                    alert('비밀번호가 일치하지 않습니다.');
                    return;
                }
            }

            axios.post(url + '/api/user/register', formData)
                .then(res => {
                    alert('회원가입이 완료되었습니다.');
                    window.location.href = '/';
                })
                .catch(error => {
                    alert(error.response.data);
                });
        }

        // 이메일 중복 확인 함수
        function checkEmailDuplication() {
            const email = document.getElementById('email').value;
            if (!email) {
                alert('이메일을 입력해주세요.');
                return;
            }

            axios.get(url + '/api/user/check-email', {
                params: {
                    email: email
                }
            }).then(res => {
                alert(res.data); // `res.data`가 응답 본문입니다.
            }).catch(error => {
                alert(error.response.data); // 에러 메시지 출력
            });
        }
    </script>
</div>

<div class="pb-20"></div>
</body>
</html>