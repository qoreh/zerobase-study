package m1;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Test {

    public static void main(String[] args) {


        WifiService.loadWifi();





/*
        String key = "754177554370756532354255754a6f";
        String result = "";
        Gson gson = new Gson();
        int start = 1;
        int end = 1000;


        try {
            URL url = new URL("http://openapi.seoul.go.kr:8088/" + key + "/json/TbPublicWifiInfo/"
                                + start + "/" + end + "/");
            // scanner 보다 많은 양의 데이터를 다룰 때 좋음
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
            result = br.readLine();
//            System.out.println(result);

            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parseString(result);
            JsonObject data = (JsonObject) jsonObject.get("TbPublicWifiInfo");
            JsonArray array = data.getAsJsonArray("row");
            String mrgNo = array.get(0).getAsJsonObject().get("X_SWIFI_MGR_NO").getAsString();

            String wrdofc = "";
            String mainNm = " ";
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



            System.out.println(mrgNo);

        } catch (Exception e) {
            e.printStackTrace();
        }

 */
    }




}
