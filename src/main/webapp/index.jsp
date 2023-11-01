<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
  <style>
    body{
      margin: 0;
      font-size: 14px;
    }

    .hyperlink{
      font-size: 13px;
    }

    input {
      width: 100px;
      font-size: 12px;
    }

    button{
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
  <h1>와이파이 정보 구하기</h1>
  <div class="hyperlink">
    <a href="index.jsp" >홈</a> |
    <a href="history.jsp">위치 히스토리 목록</a> |
    <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
  </div>
  <br>

  <span>LAT: </span>
  <input type="text" placeholder="0.0">
  <span>, LNT: </span>
  <input type="text" placeholder="0.0">
  <button>내 위치 가져오기</button>
  <button>근처 WIFI 정보 보기</button>

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

    <tbody>
      <tr>
        <td colspan="17" style="text-align: center; padding: 15px 0 15px 0;">위치 정보를 입력한 후에 조회해 주세요.</td>
      </tr>
    </tbody>
  </table>

</body>
</html>