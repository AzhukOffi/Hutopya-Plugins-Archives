import java.sql.*;
import javax.swing.*;
public class mysqlTest {
    Connection conn = null;
    public static Connection ConnectDb()    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://server_name/database_name","user_name","user_password");
            return conn;
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Cant connect to db");
            return null;
        }
    }
}