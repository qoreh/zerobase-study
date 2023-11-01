package m1;

public class ConnectTest {

    public static void main(String[] args) {
//"은평구”, "갈현1동주민센터”, "갈현동 갈현로 301", "갈현1동 1층", "", "7-1. 공공 - 행정",
// "의견(자치)", "공공WiFi", "자가망_U무선망"	, "2011", "실내", "", "126.9167"	,
// "37.62364", "2023-10-27 10:58:41.0"
        /*
        Wifi wifi = new Wifi();

        wifi.setMrgNo("---EP000001");
        wifi.setWrdofc("은평구");
        wifi.setMainNm("갈현1동주민센터");
        wifi.setAdres1("갈현동 갈현로 301");
        wifi.setAdres2("갈현1동 1층");
        wifi.setInstlFloor("");
        wifi.setInstlTy("7-1. 공공 - 행정");
        wifi.setInstlMby("의견(자치)");
        wifi.setSvcSe("공공WiFi");
        wifi.setCmcwr("자가망_U무선망");
        wifi.setCnstcYear("2011");
        wifi.setInoutDoor("실내");
        wifi.setRemars3("");
        wifi.setLat("126.9167");
        wifi.setLnt("37.62364");
        wifi.setWorkDttm("2023-10-27 10:58:41.0");

       DbConn dbConn = new DbConn();
       dbConn.insert(wifi);

         */
        DbConn.createConnection();
        DbConn.closeConnection();

    }
}
