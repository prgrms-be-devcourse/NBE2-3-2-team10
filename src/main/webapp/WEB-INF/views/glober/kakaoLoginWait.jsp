<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
        }

        body, html {
            height: 100%;
            margin: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: Arial, sans-serif;
            background-color: #ffffff;
        }

        .center-text {
            font-size: 12px;
            font-weight: bold;
            text-align: center;
        }
    </style>
    </style>
</head>
<body>
    <div class="center-text">
        <img style = "width: 150px;" src = "./upload/logo.svg">
        <p>잠시만 기다려 주세요...!</p>
    </div>
    <script>
        sessionStorage.setItem('accessToken', '${Authorization}')

        setTimeout(function() {
            window.location.href = "/customer/main";
        }, 1000);
    </script>
</div>
</body>
</html>