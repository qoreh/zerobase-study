<%@ page import="bookmark.BookmarkService" %><%--
  Created by IntelliJ IDEA.
  User: sukyungyang
  Date: 2023/11/08
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
<%
    Integer id = Integer.parseInt(request.getParameter("group-name"));
    String mgrNo = request.getParameter("mgrNo");
    String lat = request.getParameter("lat");
    String lnt = request.getParameter("lnt");
    boolean success = BookmarkService.addBookmark(id, mgrNo);
    if (success) {
%>
<script>
    alert("북마크 정보를 추가하였습니다.");
    window.location.href='bookmark-list.jsp';
</script>
<%
} else {
%>
<script>
    alert("북마크 정보 추가에 실패하였습니다. 다시 시도해주세요.");
    window.location.href='/detail.jsp?mgrNo=<%=mgrNo%>&lat=<%=lat%>&lnt=<%=lnt%>';
</script>
<% }; %>

</body>
</html>
