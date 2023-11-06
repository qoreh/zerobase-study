<%@ page import="java.util.List" %>
<%@ page import="m1.History" %><%--
  Created by IntelliJ IDEA.
  User: sukyungyang
  Date: 2023/10/30
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>

    <style>
        body{
            margin: 20px 5px 0 5px;
            font-size: 14px;
        }

        .hyperlink{
            font-size: 13px;
        }


        table{
            margin-top: 8px;
            width: 100%;
            font-size: 12px;
            font-weight: normal;
            border: 1px solid #d7d7d7;
            border-collapse: collapse;
        }

        thead {
            background-color: #00a176;
            color: #FFFFFF;
            height: 34px;
        }

        th {
            border: 1px solid #FFFFFF;
        }

        td {
            border: 1px solid #d7d7d7;
            height: 35px;
            padding: 0 7px 0 7px;
        }
        tr:nth-child(even) {
            background-color: #f6f6f6;
        }
        tbody tr:hover {
            background-color: #e0e0e0;
        }

    </style>
</head>
<body>
<%
    List<History> historyList = History.getHistoryInfo();
%>

<h1>위치 히스토리 목록</h1>
<div class="hyperlink">
    <a href="index.jsp" >홈</a> |
    <a href="history.jsp">위치 히스토리 목록</a> |
    <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
    <a href="bookmark/bookmark.jsp">북마크 보기</a> |
    <a href="bookmark/bookmark-group.jsp">북마크 그룹 관리</a>
</div>


<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>

    <tbody>
        <%
            for (History history : historyList) {
        %>

            <tr>
                <td><%=history.getId()%></td>
                <td><%=history.getLnt()%></td>
                <td><%=history.getLat()%></td>
                <td><%=history.getCheckDate()%></td>
                <td style="text-align: center"><button style="font-size: 12px">삭제</button></td>
            </tr>

        <%
            }
        %>
    </tbody>

</table>

</body>
</html>
