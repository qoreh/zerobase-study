<%@ page import="wifi.History" %>
<%@ page import="wifi.HistoryService" %><%--
  Created by IntelliJ IDEA.
  User: sukyungyang
  Date: 2023/11/10
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        body {
            margin: 20px 5px 0 5px;
            font-size: 14px;
        }

        .hyperlink {
            font-size: 13px;
        }

        table {
            margin-top: 8px;
            width: 100%;
            font-size: 12px;
            font-weight: normal;
            border: 1px solid #d7d7d7;
            border-collapse: collapse;
        }

        th {
            background-color: #00a176;
            color: #FFFFFF;
            height: 34px;
            border: 1px solid #eaeaea;
            width: 300px;

        }

        td {
            border: 1px solid #eaeaea;
            height: 35px;
            padding: 0 7px 0 7px;
        }

        button {
            font-size: 12px;

        }

        tr:nth-child(even) {
            background-color: #f6f6f6;
        }
    </style>
</head>

<body>
<%
    History history = HistoryService.getHistory(Integer.parseInt(request.getParameter("id")));
%>
<h1>히스토리 삭제</h1>
<div class="hyperlink">
    <a href="../index.jsp">홈</a> |
    <a href="../history.jsp">위치 히스토리 목록</a> |
    <a href="../load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
    <a href="bookmark-list.jsp">북마크 보기</a> |
    <a href="bookmark-group.jsp">북마크 그룹 관리</a>
</div>
<p>히스토리를 삭제하시겠습니까?</p>

<table>
    <tbody>
    <tr>
        <th>X좌표</th>
        <td><%=history.getLnt()%></td>
    </tr>
    <tr>
        <th>Y좌표</th>
        <td><%=history.getLat()%></td>
    </tr>
    <tr>
        <th>조회일자</th>
        <td><%=history.getCheckDate()%></td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: center">
            <a href="history.jsp">돌아가기</a> |
            <form action="history-delete-submit.jsp" method="POST" style="display: inline;">
                <input type="hidden" name="id" value="<%=history.getId()%>">
                <button type="submit">삭제</button>
            </form>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>
