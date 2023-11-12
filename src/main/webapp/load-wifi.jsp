<%@ page import="wifi.WifiService" %>
<%--
  Created by IntelliJ IDEA.
  User: sukyungyang
  Date: 2023/10/30
  Time: 16:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        body {
            text-align: center;
        }

        a {
            font-size: 14px;
        }
    </style>
</head>

<body>
    <% WifiService.loadWifi(); %>
    <h1 style="margin-top: 20px"><%=WifiService.totalCnt()%>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
    <a href="index.jsp">홈 으로 가기</a>
</body>
</html>
