<%@ page import="m1.Wifi" %>
<%@ page import="java.util.List" %>
<%@ page import="m1.WifiService" %>
<%@ page import="m1.History" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>와이파이 정보 구하기</title>
  <script src="js/myPosition.js"></script>
  <script src="js/function.js" defer></script>
  <script>
    window.onload = function () {
      chooseTable();
    }
  </script>
  <style>
    body{
      margin: 20px 5px 0 5px;
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

    td {
      border: 1px solid #eaeaea;
      height: 35px;
      padding: 0 7px 0 7px;
    }
    #exist-pos tr:nth-child(even) {
      background-color: #f6f6f6;
    }
    #exist-pos tr:hover {
      background-color: #e0e0e0;
    }

  </style>
</head>
<body>
  <%

    String latParam = request.getParameter("lat");
    String lntParam = request.getParameter("lnt");
    List<Wifi> wifiList = null;

    if (latParam != null && lntParam != null) {
      wifiList = WifiService.getAroundWifi(latParam, lntParam);
      History.saveHistory(latParam, lntParam);
    }
  %>


  <h1>와이파이 정보 구하기</h1>
  <div class="hyperlink">
    <a href="index.jsp" >홈</a> |
    <a href="history.jsp">위치 히스토리 목록</a> |
    <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
    <a href="bookmark/bookmark.jsp">북마크 보기</a> |
    <a href="bookmark/bookmark-group.jsp">북마크 그룹 관리</a>
  </div>
  <br>


  <span>LAT: </span>
  <input type="text" id="LAT" value=<%=request.getParameter("lat") == null ? "0.0" : request.getParameter("lat")%>>
  <span>, LNT: </span>
  <input type="text" id="LNT" value=<%=request.getParameter("lnt") == null ? "0.0" : request.getParameter("lnt")%>>

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

        <% if (wifiList != null) {
            for (Wifi wifi : wifiList) {

        %>
        <tr>
          <td><%=wifi.getDist()%></td>
          <td><%=wifi.getMgrNo()%></td>
          <td><%=wifi.getWrdofc()%></td>
          <td>
            <a href="detail.jsp?mgrNo=<%=wifi.getMgrNo()%>&lat=<%=latParam%>&lnt=<%=lntParam%>">
              <%=wifi.getMainNm()%>
            </a>
          </td>
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
        </tr>
        <%
            }
          }
            %>

    </tbody>

  </table>

</body>
</html>