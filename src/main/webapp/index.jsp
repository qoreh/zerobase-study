<%@ page import="m1.Wifi" %>
<%@ page import="java.util.List" %>
<%@ page import="m1.WifiService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
  <script src="js/myPosition.js"></script>
  <style>
    body{
      margin: 0;
      font-size: 14px;
    }

    .hyperlink{
      font-size: 13px;
    }

    input {
      font-size: 11px;
    }

    table{
      margin-top: 8px;
      width: 100%;
      font-size: 12px;
      font-weight: normal;
      border: 1px solid #d7d7d7;
      border-collapse: collapse;
    }

    thead {
      background-color: #00a176;
      color: #FFFFFF;
      height: 34px;
    }

    th {
      border: 1px solid #FFFFFF;
    }

  </style>
</head>
<body>
  <%
    String lat = request.getParameter("lat");
    String lnt = request.getParameter("lnt");


    List<Wifi> wifiList = WifiService.getAroundWifi();

  %>


  <h1>와이파이 정보 구하기</h1>
  <div class="hyperlink">
    <a href="index.jsp" >홈</a> |
    <a href="history.jsp">위치 히스토리 목록</a> |
    <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
  </div>
  <br>


  <span>LAT: </span>
  <input type="text" id="LAT" value="" placeholder="0.0">
  <span>, LNT: </span>
  <input type="text" id="LNT" value="" placeholder="0.0">

  <input type="button" value="내 위치 가져오기" onclick="getLocation()">
  <input type="button" value="근처 WIFI 정보 보기" onclick="updateUrl()">




  <table>
    <thead>
      <tr>
        <th>거리(Km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
      </tr>
    </thead>


    <tbody id="empty" style="display: none">
      <tr>
        <td colspan="17" style="text-align: center; padding: 15px 0 15px 0;">위치 정보를 입력한 후에 조회해 주세요.</td>
      </tr>
    </tbody>

    <tbody id="exist-pos" style="display: none">
      <tr>
        <% for (Wifi wifi : wifiList) { %>

        <td><%=wifi.getDist()%></td>
        <td><%=wifi.getMrgNo()%></td>
        <td><%=wifi.getWrdofc()%></td>
        <td><%=wifi.getMainNm()%></td>
        <td><%=wifi.getAdres1()%></td>
        <td><%=wifi.getAdres2()%></td>
        <td><%=wifi.getInstlFloor()%></td>
        <td><%=wifi.getInstlTy()%></td>
        <td><%=wifi.getInstlMby()%></td>
        <td><%=wifi.getSvcSe()%></td>
        <td><%=wifi.getCmcwr()%></td>
        <td><%=wifi.getCnstcYear()%></td>
        <td><%=wifi.getInoutDoor()%></td>
        <td><%=wifi.getRemars3()%></td>
        <td><%=wifi.getLnt()%></td>
        <td><%=wifi.getLat()%></td>
        <td><%=wifi.getWorkDttm()%></td>

        <% } %>
      </tr>
    </tbody>

  </table>

</body>
</html>