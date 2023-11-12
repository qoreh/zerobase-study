<%@ page import="bookmark.BookmarkService" %>
<%@ page import="bookmark.Bookmark" %><%--
  Created by IntelliJ IDEA.
  User: sukyungyang
  Date: 2023/11/09
  Time: 22:59
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
    Integer id = Integer.parseInt(request.getParameter("id"));
    Bookmark bookmark = BookmarkService.getBookmark(id);
%>
<h1>북마크</h1>
<div class="hyperlink">
    <a href="../index.jsp">홈</a> |
    <a href="../history.jsp">위치 히스토리 목록</a> |
    <a href="../load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
    <a href="bookmark-list.jsp">북마크 보기</a> |
    <a href="bookmark-group.jsp">북마크 그룹 관리</a>
</div>

<table>
    <tbody>
    <tr>
        <th>북마크 이름</th>
        <td><%=bookmark.getGroupName()%></td>
    </tr>
    <tr>
        <th>와이파이명</th>
        <td><%=bookmark.getWifiName()%></td>
    </tr>
    <tr>
        <th>등록일자</th>
        <td><%=bookmark.getRegDate()%></td>
    </tr>
    <tr>
        <td colspan="2" style="text-align: center">
            <a href="bookmark-list.jsp">돌아가기</a>
        </td>
    </tr>
    </tbody>
</table>

</body>
</html>
