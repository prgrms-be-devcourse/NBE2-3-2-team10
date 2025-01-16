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

    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        function changeSvg() {
            const svgUrl = "https://havebin.s3.ap-northeast-2.amazonaws.com/washpang/footer"
            const path = window.location.pathname;
            // alert(path);

            const homeArray = ["/shop/shop-main", "/shop/pickup-check", "/shop/pickup-delivery", "/shop/shop-review"];
            const starArray = ["/shop/mypage", "/shop/myInfoModify", "/shop/myInfo", "/shop/mypage", "/modify-shop-info"];

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

        window.onload = () => {
            changeSvg();
        };
    </script>

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
                   class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    id="shop_name" name="shop_name">
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">전화번호</label>
            <input type="tel" placeholder="전화번호를 입력하세요"
                   class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    id="phone" name="phone">
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">대표자명</label>
            <input type="text" placeholder="대표자명을 입력하세요"
                   class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    id="user_name">
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">주소</label>
            <div class="flex">
                <input type="text" placeholder="주소를 입력하세요"
                       class="w-full px-3 py-2 border rounded-l-lg focus:outline-none focus:ring-2 focus:ring-blue-500" id="address" name="address">
                <button type="button" class="px-4 py-2 bg-blue-500 text-white rounded-r-lg" onclick="sample6_execDaumPostcode()">주소 찾기</button>
            </div>
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">사업자번호</label>
            <input type="text" placeholder="사업자번호를 입력하세요"
                   class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
                    id="business_number" name="business_number">
        </div>
        <div class="mb-4">
            <label class="block text-gray-700">휴업일</label>
            <textarea placeholder="휴업일을 입력하세요"
                      class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" id="non_operating_days" name="non_operating_days"></textarea>
        </div>
        <!-- Product Information -->
        <div class="mb-4">
            <label class="block text-gray-700 font-semibold mb-2">
                상품 정보
            <div class="flex justify-end space-x-2">
                <button class="px-2 py-1 bg-green-500 text-white rounded addRowBtn whitespace-nowrap" onclick="addRow()" type="button">
                    가격표 추가
                </button>
                <!-- Delete Button -->
                <button class="px-2 py-1 bg-red-400 text-white rounded deleteRowBtn" onclick="this.closest('tr').remove()">
                    전체 삭제
                </button>
            </div>
            </label>

            <div class="relative">
                <!-- 필요 시 여기에 상품 정보에 대한 textarea 또는 입력 창을 추가할 수 있습니다 -->
            </div>
        </div>

        <!-- Product Table -->
        <div class="mb-4">
            <table class="w-full border-collapse">
                <thead>
                <tr class="bg-gray-200">
                    <th class="border p-2">이름</th>
                    <th class="border p-2 whitespace-nowrap">카테고리</th>
                    <th class="border p-2">가격</th>
                    <th class="border p-2">작업</th>
                </tr>
                </thead>
                <tbody id="productTableBody">
                <tr>
                    <td class="border p-2">
                        <input
                                type="text"
                                placeholder="상품 이름"
                                class="w-full px-2 py-1 border rounded"
                                name="item_name"
                                id="item_name"
                        />
                    </td>
                    <td class="border p-2">
                        <select class="w-full px-2 py-1 border rounded" id="category" name="category">
                            <option>신발</option>
                            <option>패딩</option>
                            <option>프리미엄 패브릭</option>
                            <option>캐리어 소독</option>
                            <option>면 세탁물</option>
                            <option>침구</option>
                            <option>보관서비스</option>
                        </select>
                    </td>
                    <td class="border p-2">
                        <input
                                type="number"
                                placeholder="숫자만 입력"
                                class="w-full px-2 py-1 border rounded"
                                id="price"
                                name="price"
                        />
                    </td>
                    <td class="border p-2 text-center">
                        <!-- Delete Button -->
                        <button class="px-2 py-1 bg-red-400 text-white rounded deleteRowBtn" onclick="this.closest('tr').remove()">
                            -
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>

        <div id="btn">
            <!-- 정보 저장 버튼 -->
            <button class="w-full py-3 mb-4 bg-blue-500 text-white font-bold rounded-lg hover:bg-blue-600" id="rbtn">
                정보 저장
            </button>
        </div>
    </form>
</div>

<!-- 하단 네비게이션 바에 가리지 않도록 여백 추가 -->
<div class="pb-20"></div>

<!-- 하단 네비게이션 -->
<footer class="fixed bottom-0 left-0 right-0 bg-white shadow p-4 flex justify-around overflow-x-auto mx-auto max-w-[448px] rounded-t-lg">
    <button class="flex flex-col items-center text-blue-500" onclick="location.href='/shop/shop-main'">
        <img id = "home" src = "./footer/Home.svg" class = "h-6 w-6"/>
        <span class="text-black text-[10pt] mt-1">홈</span>
    </button>
    <button class="flex flex-col items-center text-gray-500" onclick="location.href='/shop/pickup-list'" >
        <img src = "./footer/Bag.svg" class = "h-6 w-6"/>
        <span class="text-black text-[10pt] mt-1">주문내역</span>
    </button>
    <button class="flex flex-col items-center text-gray-500" onclick="location.href='/shop/mypage'">
        <img id = "star" src = "./footer/Star.svg" class = "h-6 w-6"/>
        <span class="text-black text-[10pt] mt-1">내 정보</span>
    </button>
</footer>


<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ffeefd8246bf28331ea26a9ff648525c&libraries=services"></script>
    <script>
        document.addEventListener("DOMContentLoaded", async () => {
            const token = sessionStorage.getItem("accessToken");
            const btn = document.getElementById("btn");
            try {
                const response = await fetch("/api/laundry/", {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                        Authorization: "Bearer " + token,
                    },
                });

                // 응답 상태 코드 확인
                if (!response.ok) {
                    console.warn(`HTTP error! status: ${response.status}`);
                    return;
                }

                // 응답 데이터 확인
                const text = await response.text(); // 원본 응답 확인
                if (!text.trim()) {
                    console.warn("Empty response received (expected behavior)");
                    return; // 빈 응답 처리
                }

                // JSON 파싱
                const data = JSON.parse(text);
                if (!data) {
                    console.warn("Null or undefined data received.");
                    return;
                }

                // 기존 데이터가 있으면 폼 필드에 채우기
                if (data) {
                    document.getElementById("phone").value = data.phone || "";
                    document.getElementById("address").value = data.address || "";
                    document.getElementById("shop_name").value = data.shop_name || "";
                    document.getElementById("non_operating_days").value = data.non_operating_days || "";
                    document.getElementById("user_name").value = data.user_name;
                    document.getElementById("business_number").value = data.business_number;

                    btn.innerHTML = `
                <button class="w-full py-3 mb-4 bg-blue-500 text-white font-bold rounded-lg hover:bg-blue-600" id="mbtn">
                    정보 수정
                </button>`;

                    // 상품 정보 테이블 채우기
                    const productTableBody = document.getElementById("productTableBody");
                    productTableBody.innerHTML = ""; // 기존 행 삭제
                    data.handledItems.forEach(item => {
                        const newRow = document.createElement("tr");
                        newRow.innerHTML = `
                    <td class="border p-2">
                        <input
                            type="text"
                            placeholder="상품 이름"
                            class="w-full px-2 py-1 border rounded"
                            name="item_name"
                            value="\${item.item_name}"
                        />
                    </td>
                    <td class="border p-2">
                        <select class="w-full px-2 py-1 border rounded" name="category">
                            <option \${item.category === "SHOES" ? "selected" : ""}>신발</option>
                            <option \${item.category === "PADDING" ? "selected" : ""}>패딩</option>
                            <option \${item.category === "PREMIUM_FABRIC" ? "selected" : ""}>프리미엄 패브릭</option>
                            <option \${item.category === "CARRIER_SANITATION" ? "selected" : ""}>캐리어 소독</option>
                            <option \${item.category === "COTTON_LAUNDRY" ? "selected" : ""}>면 세탁물</option>
                            <option \${item.category === "BEDDING" ? "selected" : ""}>침구</option>
                            <option \${item.category === "STORAGE_SERVICE" ? "selected" : ""}>보관서비스</option>
                        </select>
                    </td>
                    <td class="border p-2">
                        <input
                            type="number"
                            placeholder="숫자만 입력"
                            class="w-full px-2 py-1 border rounded"
                            name="price"
                            value="\${item.price}"
                        />
                    </td>
                    <td class="border p-2 text-center">
                        <button class="px-2 py-1 bg-red-400 text-white rounded deleteRowBtn" onclick="this.closest('tr').remove()">-</button>
                    </td>
                `;
                        productTableBody.appendChild(newRow);
                    });
                }

            } catch (error) {
                console.error("Error fetching laundry info:", error);
            }
        });

        //저장 버튼 클릭시 세탁소 정보 저장
        document.getElementById('rbtn').onclick = function(event) {
            event.preventDefault();

            const user_name = document.getElementById("user_name").value;
            const phone = document.getElementById("phone").value;
            const address = document.getElementById("address").value;
            const shop_name = document.getElementById("shop_name").value;
            const business_number = document.getElementById("business_number").value;
            const non_operating_days = document.getElementById("non_operating_days").value;
            let latitude = 0;
            let longitude = 0;

            if(user_name.trim() === ""){
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

            if(non_operating_days.trim() === ""){
                alert("휴무일을 입력하세요.");
                return false
            }

            // 주소-좌표 변환 객체를 생성합니다
            var geocoder = new kakao.maps.services.Geocoder();
            const token = sessionStorage.getItem("accessToken");

            geocoder.addressSearch(address, async function(result, status) {
                // 정상적으로 검색이 완료됐으면
                if (status === kakao.maps.services.Status.OK) {
                    longitude = result[0].x;
                    latitude = result[0].y;

                    try {
                        //세탁소 정보 등록 및 수정
                        const response1 = await fetch("/api/laundry/", {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json',
                                Authorization:  'Bearer ' + token
                            },
                            body: JSON.stringify({
                                user_name: user_name,
                                phone: phone,
                                address: address,
                                shop_name: shop_name,
                                business_number: business_number,
                                non_operating_days: non_operating_days,
                                latitude: latitude,
                                longitude: longitude
                            })
                        });


                        if (response1.ok) {
                            const data = await response1.json();
                            const laundry_id = data.laundry_id;

                            // 가격표 데이터 전송
                            sendData_register(laundry_id);

                            alert("등록이 완료되었습니다!");
                            window.location.href="/shop/shop-main";

                        } else {
                            const errorData = await response1.json();
                            alert(`오류 발생: ${errorData.message || '서버 에러'}`);
                        }
                    } catch (error) {
                        console.error('Error:', error);
                        alert('네트워크 오류가 발생했습니다.');
                    }
                } else {
                    alert("주소 검색에 실패했습니다. 올바른 주소를 입력해주세요.");
                }
            });
        };

        //세탁소 정보 수정
        document.body.addEventListener('click', function(event) {
            if (event.target && event.target.id === 'mbtn') {
                event.preventDefault();

                const user_name = document.getElementById("user_name").value;
                const phone = document.getElementById("phone").value;
                const address = document.getElementById("address").value;
                const shop_name = document.getElementById("shop_name").value;
                const business_number = document.getElementById("business_number").value;
                const non_operating_days = document.getElementById("non_operating_days").value;
                let latitude = 0;
                let longitude = 0;

                if(user_name.trim() === ""){
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

                if(non_operating_days.trim() === ""){
                    alert("휴무일을 입력하세요.");
                    return false
                }


                // 주소-좌표 변환 객체를 생성합니다
                var geocoder = new kakao.maps.services.Geocoder();
                const token = sessionStorage.getItem("accessToken");

                geocoder.addressSearch(address, async function(result, status) {
                    // 정상적으로 검색이 완료됐으면
                    if (status === kakao.maps.services.Status.OK) {
                        longitude = result[0].x;
                        latitude = result[0].y;

                        try {
                            //세탁소 정보 등록 및 수정
                            const response1 = await fetch("/api/laundry/", {
                                method: 'PUT',
                                headers: {
                                    'Content-Type': 'application/json',
                                    Authorization:  'Bearer ' + token
                                },
                                body: JSON.stringify({
                                    user_name: user_name,
                                    phone: phone,
                                    address: address,
                                    shop_name: shop_name,
                                    business_number: business_number,
                                    non_operating_days: non_operating_days,
                                    latitude: latitude,
                                    longitude: longitude
                                })
                            });


                            if (response1.ok) {
                                const data = await response1.json();
                                const laundry_id = data.laundry_id;

                                // 가격표 데이터 전송
                                sendData_modify(laundry_id);

                                alert("수정이 완료되었습니다!");
                                window.location.href="/shop/shop-main";

                            } else {
                                const errorData = await response1.json();
                                alert(`오류 발생: ${errorData.message || '서버 에러'}`);
                            }
                        } catch (error) {
                            console.error('Error:', error);
                            alert('네트워크 오류가 발생했습니다.');
                        }
                    } else {
                        alert("주소 검색에 실패했습니다. 올바른 주소를 입력해주세요.");
                    }
                });
            }
        });

        function sendData_register(laundry_id) {
            const rows = document.querySelectorAll('#productTableBody tr');
            const items = [];

            rows.forEach(row => {
                const item_name = row.querySelector('input[name="item_name"]').value;
                const category = row.querySelector('select[name="category"]').value;
                const price = row.querySelector('input[name="price"]').value;

                if (item_name && category && price) {
                    items.push({
                        item_name: item_name,
                        category: getCategoryEnum(category),
                        price: price,
                        laundry_id: laundry_id
                    });
                }
            });

            console.log(JSON.stringify(items));

            // JSON 데이터를 서버로 보내기
            const response2 = fetch("api/laundry/handled-items", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(items)
            });
        }

        //세탁소 가격정보 수정
        function sendData_modify(laundry_id) {
            const rows = document.querySelectorAll('#productTableBody tr');
            const items = [];

            rows.forEach(row => {
                const item_name = row.querySelector('input[name="item_name"]').value;
                const category = row.querySelector('select[name="category"]').value;
                const price = row.querySelector('input[name="price"]').value;

                if (item_name && category && price) {
                    items.push({
                        item_name: item_name,
                        category: getCategoryEnum(category),
                        price: price,
                        laundry_id: laundry_id
                    });
                }
            });

            console.log(JSON.stringify(items));

            // JSON 데이터를 서버로 보내기
            const response2 = fetch("api/laundry/handled-items", {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(items)
            });
        }

        function addRow() {
            const table = document.querySelector('table tbody');
            const newRow = document.createElement('tr');
            newRow.innerHTML = `
                    <td class="border p-2">
                        <input
                                type="text"
                                placeholder="상품 이름"
                                class="w-full px-2 py-1 border rounded"
                                name="item_name"
                                id="item_name"
                        />
                    </td>
                    <td class="border p-2">
                        <select class="w-full px-2 py-1 border rounded" id="category" name="category">
                            <option>신발</option>
                            <option>패딩</option>
                            <option>프리미엄 패브릭</option>
                            <option>캐리어 소독</option>
                            <option>면 세탁물</option>
                            <option>침구</option>
                            <option>보관서비스</option>
                        </select>
                    </td>
                    <td class="border p-2">
                        <input
                                type="number"
                                placeholder="숫자만 입력"
                                class="w-full px-2 py-1 border rounded"
                                id="price"
                                name="price"
                        />
                    </td>
                    <td class="border p-2 text-center">
                        <!-- Delete Button -->
                        <button class="px-2 py-1 bg-red-400 text-white rounded deleteRowBtn" onclick="this.closest('tr').remove()">
                            -
                        </button>
                    </td>
            `;
            table.appendChild(newRow);
        }

        function getCategoryEnum(category) {
            const categoryMap = {
                "신발": "SHOES",
                "패딩": "PADDING",
                "프리미엄 패브릭": "PREMIUM_FABRIC",
                "캐리어 소독": "CARRIER_SANITATION",
                "면 세탁물": "COTTON_LAUNDRY",
                "보관서비스": "STORAGE_SERVICE",
                "침구": "BEDDING"
            };
            return categoryMap[category] || "";
        }


    </script>

</body>
</html>