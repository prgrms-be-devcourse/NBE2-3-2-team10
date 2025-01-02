<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>결제 성공</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #ffffff;
        }
        .container {
            text-align: center;
        }
        .success-icon {
            width: 100px;
            height: 100px;
            background-color: #4AC7D5;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            margin: 0 auto 20px auto;
        }
        .success-icon svg {
            width: 60px;
            height: 60px;
            fill: #ffffff;
        }
        .message {
            font-size: 20px;
            font-weight: bold;
            margin-bottom: 20px;
        }
        .details {
            margin-bottom: 20px;
            font-size: 14px;
            color: #555;
        }
        .details span {
            display: block;
            margin-bottom: 5px;
        }
        .back-button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            color: #4AC7D5;
            border: 1px solid #4AC7D5;
            border-radius: 5px;
            text-decoration: none;
            margin-top: 20px;
        }
        .back-button:hover {
            background-color: #4AC7D5;
            color: #ffffff;
        }

    </style>
</head>
<body>
<div class="container">
    <div class="success-icon">
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24">
            <path d="M9 16.2l-4.2-4.2 1.4-1.4 2.8 2.8 6.8-6.8 1.4 1.4z"/>
        </svg>
    </div>
    <div class="message">결제에 성공하였습니다</div>
    <div class="details">
        <span>주문번호: ${aid}</span>
        <span>결제일시: ${approvedAt}</span>
    </div>
    <a href="/orderHistory" class="back-button">돌아가기</a>
</div>
</body>
</html>

