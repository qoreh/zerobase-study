package m1;

import com.google.gson.*;
import okhttp3.*;
import okhttp3.Request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

        String mrgNo = "";
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

            mrgNo = tmp.getAsJsonObject().get("X_SWIFI_MGR_NO").getAsString();
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
            lnt = tmp.getAsJsonObject().get("LNT").getAsString();
            lat = tmp.getAsJsonObject().get("LAT").getAsString();
            workDttm = tmp.getAsJsonObject().get("WORK_DTTM").getAsString();

            ps.setString(1, mrgNo);
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



}
