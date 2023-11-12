<%@ page import="bookmark.BookmarkGroup" %>
<%@ page import="bookmark.BookmarkGroupService" %><%--
  Created by IntelliJ IDEA.
  User: sukyungyang
  Date: 2023/11/06
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<%
    String name = request.getParameter("name");
    Integer order = Integer.parseInt(request.getParameter("order"));
    boolean success = BookmarkGroupService.addBookmarkGroup(name, order);
    if (success) {
%>
    <script>
        alert("북마크 그룹 정보를 추가하였습니다.");
        window.location.href='bookmark-group.jsp';
    </script>
<%
    } else {
%>
    <script>
        alert("북마크 그룹 정보 추가에 실패하였습니다. 다시 시도해주세요.");
        window.location.href='bookmark-group-add.jsp';
    </script>
<% }; %>

</body>
</html>
