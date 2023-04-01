import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButils {

    public static String DBurl = "jdbc:mysql://localhost:3306/?user=root";
    public static String DBusername = "root";
    public static String DBpassword = "root";
    public static Connection getConnection()  {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBurl,DBusername, DBpassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
