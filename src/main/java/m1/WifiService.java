package m1;

import com.google.gson.*;
import okhttp3.*;
import okhttp3.Request;

import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class WifiService {

    private static String driver = "org.sqlite.JDBC";
    private static String dbUrl = "jdbc:sqlite:/Users/sukyungyang/Desktop/ZB_JAVA/DB/mission1/wifi_info";
    private static Connection connection = null;
    private final static String KEY = "754177554370756532354255754a6f";



    public static void createConnection() {

        // Driver 로드, 커넥션 객체
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(dbUrl);
            connection.setAutoCommit(false);
//            System.out.println("Connected");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void closeConnection() {
        try {
            connection.commit();
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection = null;
//            System.out.println("closed");
        }
    }


    public static int totalCnt(){
        int total = 0;
        String url = "http://openapi.seoul.go.kr:8088/" + KEY + "/json/TbPublicWifiInfo/1/1/";

        try {
            OkHttpClient client = new OkHttpClient();
            Request builder = new Request.Builder().url(url).build();
            Response response = client.newCall(builder).execute();

            if (response.isSuccessful() && response.body() != null) {
                String data = response.body().string();

                JsonParser parser = new JsonParser();
                JsonObject jsonObject = parser.parse(data).getAsJsonObject();
                JsonObject tbPublicWifi = jsonObject.getAsJsonObject("TbPublicWifiInfo");
                total = tbPublicWifi.get("list_total_count").getAsInt();
            }

            response.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    public static void loadWifi() {
        int total = totalCnt();
        int start = 1;
        int end = 1000;
        int loopCnt = (int)Math.ceil((double) total / 1000);
        String url = "";

        OkHttpClient client = new OkHttpClient();
        createConnection();

        for (int i = 0; i < loopCnt; i++) {
            url = "http://openapi.seoul.go.kr:8088/" + KEY + "/json/TbPublicWifiInfo/" + start + "/" + end + "/";
            Request builder = new Request.Builder()
                    .url(url)
                    .build();

            try {
                Response response = client.newCall(builder).execute();
                if (response.isSuccessful() && response.body() != null) {
                    String data = response.body().string();

                    JsonParser parser = new JsonParser();
                    JsonObject object = parser.parse(data).getAsJsonObject();
                    JsonObject tbPublicWifiInfo = object.getAsJsonObject("TbPublicWifiInfo");
                    JsonArray row = tbPublicWifiInfo.getAsJsonArray("row");

                    insertInfo(row);
                }

                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            start = end + 1;
            if (end + 1000 <= total) {
                end += 1000;
            } else {
                end = start - 1 + (total % 1000);
            }
        }
        closeConnection();

    }

    private static void insertInfo(JsonArray array) {

        String mgrNo = "";
        String wrdofc = "";
        String mainNm = "";
        String adres1 = "";
        String adres2 = "";
        String instlFloor = "";
        String instlTy = "";
        String instlMby = "";
        String svcSe = "";
        String cmcwr = "";
        String cnstcYear = "";
        String inoutDoor = "";
        String remars3 = "";
        String lat = "";
        String lnt = "";
        String workDttm = "";

        JsonObject tmp = null;

        PreparedStatement ps = null;
        try {
//            connection.setAutoCommit(false);(connection메서드로 옮김)

            String sql = "INSERT INTO 'info' (MGR_NO, WRDOFC, MAIN_NM, ADRES1, ADRES2, INSTL_FLOOR, INSTL_TY, " +
                    " INSTL_MBY, SVC_SE, CMCWR, CNSTC_YEAR, INOUT_DOOR, REMARS3, LNT, LAT, WORK_DTTM) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(sql);

        for (int i = 0; i < array.size(); i++) {
            tmp = (JsonObject) array.get(i);

            mgrNo = tmp.getAsJsonObject().get("X_SWIFI_MGR_NO").getAsString();
            wrdofc = tmp.getAsJsonObject().get("X_SWIFI_WRDOFC").getAsString();
            mainNm = tmp.getAsJsonObject().get("X_SWIFI_MAIN_NM").getAsString();
            adres1 = tmp.getAsJsonObject().get("X_SWIFI_ADRES1").getAsString();
            adres2 = tmp.getAsJsonObject().get("X_SWIFI_ADRES2").getAsString();
            instlFloor = tmp.getAsJsonObject().get("X_SWIFI_INSTL_FLOOR").getAsString();
            instlTy = tmp.getAsJsonObject().get("X_SWIFI_INSTL_TY").getAsString();
            instlMby = tmp.getAsJsonObject().get("X_SWIFI_INSTL_MBY").getAsString();
            svcSe = tmp.getAsJsonObject().get("X_SWIFI_SVC_SE").getAsString();
            cmcwr = tmp.getAsJsonObject().get("X_SWIFI_CMCWR").getAsString();
            cnstcYear = tmp.getAsJsonObject().get("X_SWIFI_CNSTC_YEAR").getAsString();
            inoutDoor = tmp.getAsJsonObject().get("X_SWIFI_INOUT_DOOR").getAsString();
            remars3 = tmp.getAsJsonObject().get("X_SWIFI_REMARS3").getAsString();
            lnt = tmp.getAsJsonObject().get("LAT").getAsString(); // 일부러 바꿔서 넣어줌 open api x좌표 y좌표 거꾸로 기입되어있음
            lat = tmp.getAsJsonObject().get("LNT").getAsString();
            workDttm = tmp.getAsJsonObject().get("WORK_DTTM").getAsString();

            ps.setString(1, mgrNo);
            ps.setString(2, wrdofc);
            ps.setString(3, mainNm);
            ps.setString(4, adres1);
            ps.setString(5, adres2);
            ps.setString(6, instlFloor);
            ps.setString(7, instlTy);
            ps.setString(8, instlMby);
            ps.setString(9, svcSe);
            ps.setString(10, cmcwr);
            ps.setString(11, cnstcYear);
            ps.setString(12, inoutDoor);
            ps.setString(13, remars3);
            ps.setString(14, lnt);
            ps.setString(15, lat);
            ps.setString(16, workDttm);

            ps.addBatch();
            ps.clearParameters();
        }

        int[] affected = ps.executeBatch();
/*
            for (int i = 0; i < affected.length; i++) {
                if (affected[i] <= 0) {
                    System.out.println("저장 실패");
                }
            }

 */

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    /**
     * 근처 와이파이 정보 가져오기
     * @param myLat 내 Y좌표
     * @param myLnt 내 X좌표
     * @return
     */
    public static List<Wifi> getAroundWifi(String myLat, String myLnt){
        List<Wifi> list = new ArrayList<>();
        createConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select round(abs(6371 * acos(cos(radians(info.LAT)) * cos(radians(?)) * cos(radians(?) " +
                    "- radians(info.LNT)) + sin(radians(info.LAT)) * sin(radians(?)))), 4) as DISTANCE " +
                    ", * " +
                    "from info  " +
                    "where LAT != 0 and LNT != 0  " +
                    "order by DISTANCE  " +
                    "limit 20 offset 0; ";
            ps = connection.prepareStatement(sql);

            ps.setString(1, myLat);
            ps.setString(2, myLnt);
            ps.setString(3, myLat);
            rs = ps.executeQuery();

            String dist = "";
            String mgrNo = "";
            String wrdofc = "";
            String mainNm = "";
            String adres1 = "";
            String adres2 = "";
            String instlFloor = "";
            String instlTy = "";
            String instlMby = "";
            String svcSe = "";
            String cmcwr = "";
            String cnstcYear = "";
            String inoutDoor = "";
            String remars3 = "";
            String lat = "";
            String lnt = "";
            String workDttm = "";
            while (rs.next()) {

                dist = rs.getString("DISTANCE");
                mgrNo = rs.getString("MGR_NO");
                wrdofc = rs.getString("WRDOFC");
                mainNm = rs.getString("MAIN_NM");
                adres1 = rs.getString("ADRES1");
                adres2 = rs.getString("ADRES2");
                instlFloor = rs.getString("INSTL_FLOOR");
                instlTy = rs.getString("INSTL_TY");
                instlMby = rs.getString("INSTL_MBY");
                svcSe = rs.getString("SVC_SE");
                cmcwr = rs.getString("CMCWR");
                cnstcYear = rs.getString("CNSTC_YEAR");
                inoutDoor = rs.getString("INOUT_DOOR");
                remars3 = rs.getString("REMARS3");
                lnt = rs.getString("LNT");
                lat = rs.getString("LAT");
                workDttm = rs.getString("WORK_DTTM");

                Wifi wifi = new Wifi();
                wifi.setDist(dist);
                wifi.setMgrNo(mgrNo);
                wifi.setWrdofc(wrdofc);
                wifi.setMainNm(mainNm);
                wifi.setAdres1(adres1);
                wifi.setAdres2(adres2);
                wifi.setInstlFloor(instlFloor);
                wifi.setInstlTy(instlTy);
                wifi.setInstlMby(instlMby);
                wifi.setSvcSe(svcSe);
                wifi.setCmcwr(cmcwr);
                wifi.setCnstcYear(cnstcYear);
                wifi.setInoutDoor(inoutDoor);
                wifi.setRemars3(remars3);
                wifi.setLnt(lnt);
                wifi.setLat(lat);
                wifi.setWorkDttm(workDttm);

                list.add(wifi);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        closeConnection();
        return list;
    }

    public static Wifi getDetail(String mgrNo, String lat, String lnt){
        Wifi wifi = new Wifi();
        createConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select round(abs(6371 * acos(cos(radians(info.LAT)) * cos(radians(?)) * " +
                    "cos(radians(?) - radians(info.LNT)) + sin(radians(info.LAT)) * sin(radians(?)))), 4) as DISTANCE  " +
                    ", *  " +
                    "from info " +
                    "where MGR_NO = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, lat);
            ps.setString(2, lnt);
            ps.setString(3, lat);
            ps.setString(4, mgrNo);

            rs = ps.executeQuery();

            if (rs != null) {
                wifi.setDist(rs.getString("DISTANCE"));
                wifi.setMgrNo(rs.getString("MGR_NO"));
                wifi.setWrdofc(rs.getString("WRDOFC"));
                wifi.setMainNm(rs.getString("MAIN_NM"));
                wifi.setAdres1(rs.getString("ADRES1"));
                wifi.setAdres2(rs.getString("ADRES2"));
                wifi.setInstlFloor(rs.getString("INSTL_FLOOR"));
                wifi.setInstlTy(rs.getString("INSTL_TY"));
                wifi.setInstlMby(rs.getString("INSTL_MBY"));
                wifi.setSvcSe(rs.getString("SVC_SE"));
                wifi.setCmcwr(rs.getString("CMCWR"));
                wifi.setCnstcYear(rs.getString("CNSTC_YEAR"));
                wifi.setInoutDoor(rs.getString("INOUT_DOOR"));
                wifi.setRemars3(rs.getString("REMARS3"));
                wifi.setLnt(rs.getString("LNT"));
                wifi.setLat(rs.getString("LAT"));
                wifi.setWorkDttm(rs.getString("WORK_DTTM"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        closeConnection();
        return wifi;
    }






   // 안씀
    public static void updateDistance(Double myLat, Double myLnt) {

        PreparedStatement ps = null;

        try {
            String sql = "update info " +
                    "set DISTANCE = round(abs(6371 * acos(cos(radians(info.LAT)) * " +
                    "cos(radians(?)) * cos(radians(?) - radians(info.LNT))  " +
                    "                     + sin(radians(info.LAT)) * sin(radians(?)))), 4)  " +
                    "where  LAT != 0 and LNT !=0;";

            ps = connection.prepareStatement(sql);
            ps.setDouble(1, myLat);
            ps.setDouble(2, myLnt);
            ps.setDouble(3, myLat);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
