<%@ page import="bookmark.BookmarkGroup" %><%--
  Created by IntelliJ IDEA.
  User: sukyungyang
  Date: 2023/11/06
  Time: 22:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<%
    Integer id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    Integer order = Integer.parseInt(request.getParameter("order"));

    boolean success = BookmarkGroup.updateBookmarkGroup(id, name, order);
    if (success) {
%>
<script>
    alert("북마크 그룹 정보를 수정하였습니다.");
    window.location.href='bookmark-group.jsp';
</script>
<%
} else {
%>
<script>
    alert("북마크 그룹 정보 수정에 실패하였습니다. 다시 시도해주세요.");
    window.location.href='bookmark-group-edit.jsp';
</script>
<% }; %>
</body>
</html>
