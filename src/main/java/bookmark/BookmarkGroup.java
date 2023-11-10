package bookmark;

import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class BookmarkGroup {
    Integer id;
    Integer order;
    String name;
    String regDate;
    String updateDate;

    private static String driver = "org.sqlite.JDBC";
    private static String dbUrl = "jdbc:sqlite:/Users/sukyungyang/Desktop/ZB_JAVA/DB/mission1/wifi_info";
    private static Connection connection = null;

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

    public static boolean addBookmarkGroup(String name, Integer order) {
        createConnection();
        PreparedStatement ps = null;
        boolean success = false;
        try {
            String sql = "insert into bookmark_group(NAME, \"ORDER\", REG_DATE)  " +
                    "VALUES (?, ?, strftime('%Y-%m-%dT%H:%M:%S', 'now')) ";

            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, order);

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

    public static List<BookmarkGroup> bookmarkGroupList() {
        List<BookmarkGroup> list = new ArrayList<>();
        createConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select *  " +
                    "from bookmark_group  " +
                    "order by \"ORDER\"";

            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            String updateDate = "";
            while (rs.next()) {
                BookmarkGroup bookmarkGroup = new BookmarkGroup();

                bookmarkGroup.setId(rs.getInt("ID"));
                bookmarkGroup.setName(rs.getString("NAME"));
                bookmarkGroup.setOrder(rs.getInt("ORDER"));
                bookmarkGroup.setRegDate(rs.getString("REG_DATE"));
                updateDate = rs.getString("UPDATE_DATE");
                bookmarkGroup.setUpdateDate(updateDate == null ? "" : updateDate);

                list.add(bookmarkGroup);
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
                if (ps != null && !ps.isClosed()) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        closeConnection();
        return list;
    }

    public static BookmarkGroup getBookmarkGroup(Integer id) {
        BookmarkGroup bookmarkGroup = new BookmarkGroup();
        createConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = "select NAME, \"ORDER\" " +
                    "from bookmark_group  " +
                    "where ID = ?";

            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            rs = ps.executeQuery();

            bookmarkGroup.setName(rs.getString("NAME"));
            bookmarkGroup.setOrder(rs.getInt("ORDER"));

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

        return bookmarkGroup;
    }

    public static boolean updateBookmarkGroup(Integer id, String name, Integer order) {
        boolean success = false;

        createConnection();
        PreparedStatement ps = null;

        try {
            String sql = "update bookmark_group  " +
                    "set NAME = ?, \"ORDER\" = ?, UPDATE_DATE = strftime('%Y-%m-%dT%H:%M:%S', 'now')  " +
                    "where ID = ? ";

            ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, order);
            ps.setInt(3, id);

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

    public static boolean deleteBookmarkGroup(Integer id) {
        boolean success = false;

        createConnection();
        PreparedStatement ps = null;

        try {
            String sql = "delete  " +
                    "from bookmark_group  " +
                    "where ID = ? ";

            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

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

}


