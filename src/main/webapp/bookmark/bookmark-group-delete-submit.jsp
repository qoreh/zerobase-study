<%@ page import="bookmark.BookmarkGroup" %><%--
  Created by IntelliJ IDEA.
  User: sukyungyang
  Date: 2023/11/06
  Time: 22:33
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
    boolean success = BookmarkGroup.deleteBookmarkGroup(id);
    if (success) {
%>
<script>
    alert("북마크 그룹 정보를 삭제하였습니다.");
    window.location.href='bookmark-group.jsp';
</script>
<%
} else {
%>
<script>
    alert("북마크 그룹 정보 삭제에 실패하였습니다. 다시 시도해주세요.");
    window.location.href='bookmark-group-delete.jsp';
</script>
<% }; %>
</body>
</html>
