<%@ page import="bookmark.BookmarkService" %>
<%@ page import="java.util.List" %>
<%@ page import="bookmark.Bookmark" %>
<%@ page import="wifi.WifiService" %><%--
  Created by IntelliJ IDEA.
  User: sukyungyang
  Date: 2023/11/05
  Time: 22:24
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

        button {
            margin-top: 10px;
        }

        table {
            margin-top: 10px;
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

        tr:nth-child(even) {
            background-color: #f6f6f6;
        }

        tr:hover {
            background-color: #e0e0e0;
        }
    </style>
</head>

<body>
<%
    List<Bookmark> bookmarkList = BookmarkService.getBookmarkList();
%>
<h1>북마크 목록</h1>
<div class="hyperlink">
    <a href="../index.jsp">홈</a> |
    <a href="../history.jsp">위치 히스토리 목록</a> |
    <a href="../load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
    <a href="bookmark-list.jsp">북마크 보기</a> |
    <a href="bookmark-group.jsp">북마크 그룹 관리</a>
</div>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>북마크 이름</th>
        <th>와이파이명</th>
        <th>등록일자</th>
        <th>비고</th>
    </tr>
    </thead>

    <tbody>
    <% for (Bookmark bookmark : bookmarkList) { %>
    <tr>
        <td><%=bookmark.getId()%></td>
        <td><%=bookmark.getGroupName()%></td>
        <td>
            <a href="bookmark-detail.jsp?id=<%=bookmark.getId()%>">
                <%=bookmark.getWifiName()%>
            </a>
        </td>
        <td><%=bookmark.getRegDate()%></td>
        <td style="text-align: center">
            <a href="bookmark-delete.jsp?id=<%=bookmark.getId()%>">삭제</a>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>

</body>
</html>
