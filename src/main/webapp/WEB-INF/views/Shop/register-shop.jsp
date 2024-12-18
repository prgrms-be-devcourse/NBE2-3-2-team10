<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>업체 정보 등록</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }
    </style>

    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        function sample6_execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {
                    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                    // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var addr = ''; // 주소 변수

                    //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                    if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                        addr = data.roadAddress;
                    } else { // 사용자가 지번 주소를 선택했을 경우(J)
                        addr = data.jibunAddress;
                    }


                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.getElementById("address").value = addr;
                }
            }).open();
        }
    </script>
</head>
<body class="bg-gray-100">
<!-- 메인 컨텐츠 -->
<div class="max-w-md mx-auto bg-white p-6 rounded-lg shadow-md">
    <!-- 상단 헤더 -->
    <div class="flex items-center justify-between pb-4 border-b">
        <button class="text-gray-500" onclick="history.back()">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
            </svg>
        </button>
        <h1 class="text-xl font-bold">업체 정보 등록</h1>
        <div></div>
    </div>

    <form class="mt-4" name="rfrm" action="/api/laundry" method="post">
        <div class="mb-4">
            <label class="block text-gray-700">대표자명</label>
            <input type="text" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="대표자명" id="user" name="user">
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">전화번호</label>
            <input type="tel" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="전화번호" id="phone" name="phone">
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">주소</label>
            <div class="flex">
                <input type="text" class="w-full px-3 py-2 border rounded-l-lg focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="주소" id="address" name="address">
                <button type="button" class="px-4 py-2 bg-blue-500 text-white rounded-r-lg hover:bg-blue-600" onclick="sample6_execDaumPostcode()">주소 찾기</button>
            </div>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">세탁소명</label>
            <input type="text" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="세탁소명" id="shop_name" name="shop_name">
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">사업자번호</label>
            <input type="text" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="사업자번호" id="business_number" name="business_number">
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">영업일</label>
            <input type="text" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="영업일" id="operating_days" name="operating_days">
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">영업시간</label>
            <input type="text" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="00:00 ~ 00:00" id="business_hours" name="business_hours">
        </div>

        <!-- 등록 버튼 -->
        <button class="w-full py-3 mb-4 bg-blue-500 text-white font-bold rounded-lg hover:bg-blue-600" id="rbtn">
            등록
        </button>
    </form>
</div>

<!-- 하단 네비게이션 바에 가리지 않도록 여백 추가 -->
<div class="pb-20"></div>

<!-- 하단 네비게이션 -->
<div class="fixed bottom-0 left-0 right-0 bg-white shadow-md">
    <div class="flex justify-around py-2">
        <button class="flex flex-col items-center text-blue-500">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001 1h6a1 1 0 001-1V10m-7 11V10m0 11H5a1 1 0 01-1-1V10m0 0L3 12" />
            </svg>
            <span>홈</span>
        </button>
        <button class="flex flex-col items-center text-gray-500">
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

<script type="text/javascript">
    document.getElementById('rbtn').onclick = function() {
        const user = document.getElementById("user").value;
        const phone = document.getElementById("phone").value;
        const address = document.getElementById("address").value;
        const shop_name = document.getElementById("shop_name").value;
        const business_number = document.getElementById("business_number").value;
        const operating_days = document.getElementById("operating_days").value;
        const business_hours = document.getElementById("business_hours").value;

        if(user.trim() === ""){
            alert("이름을 입력하세요.");
            return false;
        }

        if(phone.trim() === ""){
            alert("전화번호를 입력하세요.");
            return false
        }

        if(address.trim() === ""){
            alert("주소를 입력하세요.");
            return false
        }

        if(shop_name.trim() === ""){
            alert("세탁소명을 입력하세요.");
            return false
        }

        if(business_number.trim() === ""){
            alert("사업자 번호를 입력하세요.");
            return false
        }

        if(operating_days.trim() === ""){
            alert("영업일을 입력하세요.");
            return false
        }

        if(business_hours.trim() === ""){
            alert("영업시간을 입력하세요.");
            return false
        }

        document.rfrm.submit();
    }
</script>

</body>
</html>