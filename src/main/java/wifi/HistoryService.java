package wifi;

import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Setter @Getter
public class HistoryService {

    private static String driver = "org.sqlite.JDBC";
    private static String dbUrl = "jdbc:sqlite:/Users/sukyungyang/Desktop/ZB_JAVA/DB/mission1/wifi_info";
    private static Connection connection = null;

    public static void createConnection() {

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(dbUrl);
            connection.setAutoCommit(false);
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

    public static void saveHistory(String lat, String lnt){
        createConnection();

        PreparedStatement ps = null;

        try {
            String sql = "insert into 'history' (LNT, LAT, CHECK_DATE) " +
                    "values (?, ?, strftime('%Y-%m-%dT%H:%M:%S', 'now')); ";
            ps = connection.prepareStatement(sql);

            ps.setString(1, lnt);
            ps.setString(2, lat);
            ps.executeUpdate();
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


        closeConnection();
    }

    public static List<History> getHistoryList() {
        List<History> list = new ArrayList<>();
        createConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * " +
                    "from history " +
                    "order by ID desc ;";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            int id;
            String lnt = "";
            String lat = "";
            String checkDate = "";
            while (rs.next()) {
                History history = new History();

                id = rs.getInt("ID");
                lnt = rs.getString("LNT");
                lat = rs.getString("LAT");
                checkDate = rs.getString("CHECK_DATE");

                history.setId(id);
                history.setLnt(lnt);
                history.setLat(lat);
                history.setCheckDate(checkDate);

                list.add(history);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null && !ps.isClosed()){
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        closeConnection();
        return list;
    }

    public static History getHistory(Integer id){
        History history = new History();
        createConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * " +
                    "from history " +
                    "where id = ?;";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                history.setId(rs.getInt("ID"));
                history.setLnt(rs.getString("LNT"));
                history.setLat(rs.getString("LAT"));
                history.setCheckDate(rs.getString("CHECK_DATE"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null && !ps.isClosed()){
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        closeConnection();
        return history;
    }

    public static boolean deleteHistory(Integer id) {
        boolean success = false;
        createConnection();

        PreparedStatement ps = null;

        try {
            String sql = "delete " +
                    "from history  " +
                    "where ID = ?";

            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            int affected = ps.executeUpdate();
            if (affected > 0 ) {
                success = true;
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

        closeConnection();

        return success;
    }

}


