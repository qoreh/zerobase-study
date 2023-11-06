<%@ page import="m1.Wifi" %>
<%@ page import="m1.WifiService" %><%--
  Created by IntelliJ IDEA.
  User: sukyungyang
  Date: 2023/11/03
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <script src="js/function.js"></script>
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
        td:hover {
            background-color: #e0e0e0;
        }

    </style>
</head>
<body>
    <%
        String mgrNo = request.getParameter("mgrNo");
        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");
        Wifi wifi = WifiService.getDetail(mgrNo, lat, lnt);
    %>

    <h1>와이파이 상세 정보</h1>
    <div class="hyperlink">
        <a href="index.jsp" >홈</a> |
        <a href="history.jsp">위치 히스토리 목록</a> |
        <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
        <a href="bookmark/bookmark.jsp">북마크 보기</a> |
        <a href="bookmark/bookmark-group.jsp">북마크 그룹 관리</a>
    </div>

    <table>
        <tbody>
            <tr>
                <th>거리(Km)</th>
                <td><%=wifi.getDist()%></td>
            </tr>
            <tr>
                <th>관리번호</th>
                <td><%=wifi.getMgrNo()%></td>
            </tr>
            <tr>
                <th>자치구</th>
                <td><%=wifi.getWrdofc()%></td>
            </tr>
            <tr>
                <th>와이파이명</th>
                <td>
                    <a href="#" onclick="reload(); return false"><%=wifi.getMainNm()%> </a>
                </td>
            </tr>
            <tr>
                <th>도로명주소</th>
                <td><%=wifi.getAdres1()%></td>
            </tr>
            <tr>
                <th>상세주소</th>
                <td><%=wifi.getAdres2()%></td>
            </tr>
            <tr>
                <th>설치위치(층)</th>
                <td><%=wifi.getInstlFloor()%></td>
            </tr>
            <tr>
                <th>설치유형</th>
                <td><%=wifi.getInstlTy()%></td>
            </tr>
            <tr>
                <th>설치기관</th>
                <td><%=wifi.getInstlMby()%></td>
            </tr>
            <tr>
                <th>서비스구분</th>
                <td><%=wifi.getSvcSe()%></td>
            </tr>
            <tr>
                <th>망종류</th>
                <td><%=wifi.getCmcwr()%></td>
            </tr>
            <tr>
                <th>설치년도</th>
                <td><%=wifi.getCnstcYear()%></td>
            </tr>
            <tr>
                <th>실내외구분</th>
                <td><%=wifi.getInoutDoor()%></td>
            </tr>
            <tr>
                <th>WIFIW접속환경</th>
                <td><%=wifi.getRemars3()%></td>
            </tr>
            <tr>
                <th>X좌표</th>
                <td><%=wifi.getLnt()%></td>
            </tr>
            <tr>
                <th>Y좌표</th>
                <td><%=wifi.getLat()%></td>
            </tr>
            <tr>
                <th>작업일자</th>
                <td><%=wifi.getWorkDttm()%></td>
            </tr>

        </tbody>
    </table>

</body>
</html>
