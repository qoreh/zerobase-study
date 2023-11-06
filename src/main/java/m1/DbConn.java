package m1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Stack;

public class DbConn {
    private static String driver = "org.sqlite.JDBC";
    private static String url = "jdbc:sqlite:wifi.db";
    private static Connection connection = null;
    private final static String KEY = "754177554370756532354255754a6f";




    public static void createConnection() {

        // Driver 로드, 커넥션 객체
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url);
            System.out.println("Connected");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection = null;
            System.out.println("closed");
        }
    }


    public static int getTotalCnt() {
        createConnection();
        String result = "";
        int totalCnt = 0;

        try {
            URL url = new URL("http://openapi.seoul.go.kr:8088/" + KEY + "/json/TbPublicWifiInfo/1/1/");

            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            result = br.readLine();

            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parseString(result);
            JsonObject data = (JsonObject) jsonObject.get("TbPublicWifiInfo");



        } catch (Exception e) {
            e.printStackTrace();
        }



        closeConnection();

        return totalCnt;
    }

    public void loadWifi(Wifi wifi) {

        createConnection();

        PreparedStatement ps = null;

        try {

            String sql = "INSERT INTO info (MGR_NO, WRDOFC, MAIN_NM, ADRES1, ADRES2, INSTL_FLOOR, INSTL_TY, " +
                    " INSTL_MBY, SVC_SE, CMCWR, CNSTC_YEAR, INOUT_DOOR, REMARS3, LNT, LAT, WORK_DTTM) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            ps = connection.prepareStatement(sql);

            ps.setString(1, wifi.getMgrNo());
            ps.setString(2, wifi.getWrdofc());
            ps.setString(3, wifi.getMainNm());
            ps.setString(4, wifi.getAdres1());
            ps.setString(5, wifi.getAdres2());
            ps.setString(6, wifi.getInstlFloor());
            ps.setString(7, wifi.getInstlTy());
            ps.setString(8, wifi.getInstlMby());
            ps.setString(9, wifi.getSvcSe());
            ps.setString(10, wifi.getCmcwr());
            ps.setString(11, wifi.getCnstcYear());
            ps.setString(12, wifi.getInoutDoor());
            ps.setString(13, wifi.getRemars3());
            ps.setString(14, wifi.getLnt());
            ps.setString(15, wifi.getLat());
            ps.setString(16, wifi.getWorkDttm());

            ps.addBatch();
            ps.clearParameters();

            int affected = ps.executeUpdate();

            if (affected > 0) {
                System.out.println("저장 성공");
            } else {
                System.out.println("저장 실패");
            }

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


/*
try {
            connection = DriverManager.getConnection(URL);

            String sql = "INSERT INTO info (MGR_NO, WRDOFC, MAIN_NM, ADRES1, ADRES2, INSTL_FLOOR, INSTL_TY" +
                    ", INSTL_MBY, SVC_SE, CMCWR, CNSTC_YEAR, INOUT_DOOR, REMARS3, LAT, LNT, WORK_DTTM) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


        } catch (SQLException e) {
            e.printStackTrace();
        }
 */