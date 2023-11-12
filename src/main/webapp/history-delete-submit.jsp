<%@ page import="wifi.History" %>
<%@ page import="wifi.HistoryService" %><%--
  Created by IntelliJ IDEA.
  User: sukyungyang
  Date: 2023/11/10
  Time: 21:04
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
    boolean success = HistoryService.deleteHistory(id);
    if (success) {
%>
<script>
    alert("히스토리를 삭제하였습니다.");
    window.location.href='history.jsp';
</script>
<%
} else {
%>
<script>
    alert("히스토리 삭제에 실패하였습니다. 다시 시도해주세요.");
    window.location.href='history-delete.jsp?id=<%=id%>';
</script>
<% }; %>

</body>
</html>
