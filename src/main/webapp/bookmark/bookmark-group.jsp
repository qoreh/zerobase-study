<%@ page import="java.util.List" %>
<%@ page import="bookmark.BookmarkGroup" %><%--
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
        body{
            margin: 20px 5px 0 5px;
            font-size: 14px;
        }

        .hyperlink{
            font-size: 13px;
        }

        button {
            margin-top: 10px;
        }
        table{
            margin-top: 0;
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
    List<BookmarkGroup> list = BookmarkGroup.bookmarkGroupList();
%>
    <h1>북마크 그룹 관리</h1>
    <div class="hyperlink">
        <a href="../index.jsp">홈</a> |
        <a href="../history.jsp">위치 히스토리 목록</a> |
        <a href="../load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
        <a href="bookmark-list.jsp">북마크 보기</a> |
        <a href="bookmark-group.jsp">북마크 그룹 관리</a>
    </div>
    <button onclick="window.location.href='bookmark-group-add.jsp'">북마크 그룹 이름 추가</button>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>북마크 이름</th>
                <th>순서</th>
                <th>등록일자</th>
                <th>수정일자</th>
                <th>비고</th>
            </tr>
        </thead>

        <tbody>
            <% for(BookmarkGroup bookmarkGroup : list) { %>
            <tr>
                <td><%=bookmarkGroup.getId()%></td>
                <td><%=bookmarkGroup.getName()%></td>
                <td><%=bookmarkGroup.getOrder()%></td>
                <td><%=bookmarkGroup.getRegDate()%></td>
                <td><%=bookmarkGroup.getUpdateDate()%></td>
                <td style="text-align: center">
                    <a href="bookmark-group-edit.jsp?id=<%=bookmarkGroup.getId()%>">수정</a>
                    <a href="bookmark-group-delete.jsp?id=<%=bookmarkGroup.getId()%>">삭제</a>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
