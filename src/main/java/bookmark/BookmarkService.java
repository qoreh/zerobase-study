package bookmark;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookmarkService {

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

    public static boolean addBookmark(Integer id, String mgrNo) {
        boolean success = false;
        createConnection();

        PreparedStatement ps = null;

        try {
            String sql = "insert into bookmark(GROUP_NAME, WIFI_NAME, REG_DATE)  "
                    + "VALUES ((select name from bookmark_group where id = ?),  " +
                    "(select MAIN_NM from info where info.MGR_NO = ?),  " +
                    "strftime('%Y-%m-%dT%H:%M:%S', 'now'))";

            ps = connection.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, mgrNo);

            int affected = ps.executeUpdate();
            if (affected > 0) {
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

    public static List<Bookmark> getBookmarkList() {
        List<Bookmark> bookmarkList = new ArrayList<>();
        createConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select *  " +
                    "from bookmark ";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Bookmark bookmark = new Bookmark();

                bookmark.setId(rs.getInt("ID"));
                bookmark.setGroupName(rs.getString("GROUP_NAME"));
                bookmark.setWifiName(rs.getString("WIFI_NAME"));
                bookmark.setRegDate(rs.getString("REG_DATE"));

                bookmarkList.add(bookmark);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

            try {
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        closeConnection();
        return bookmarkList;
    }

    public static Bookmark getBookmark(Integer id) {
        Bookmark bookmark = new Bookmark();
        createConnection();

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select * " +
                    "from bookmark " +
                    "where ID = ? ";

            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            bookmark.setId(rs.getInt("ID"));
            bookmark.setGroupName(rs.getString("GROUP_NAME"));
            bookmark.setWifiName(rs.getString("WIFI_NAME"));
            bookmark.setRegDate(rs.getString("REG_DATE"));

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
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        closeConnection();
        return bookmark;
    }

    public static boolean deleteBookmark(Integer id) {
        boolean success = false;
        createConnection();

        PreparedStatement ps = null;

        try {
            String sql = "delete " +
                    "from bookmark  " +
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

    public static boolean deleteBookmarkWithBookmarkGroup(Integer id) {
        boolean success = false;
        createConnection();

        PreparedStatement ps = null;

        try {
            String sql = "delete " +
                    "from bookmark " +
                    "where GROUP_NAME = (select NAME from bookmark_group where ID = ?)";

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
