import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class connections{
    public static void main(String[] args) {
        String url = "jdbc:datadirect:sqlserver://10.30.239.16:1433/test";
//        String username = "12345";
//        String password = "xyxyx";

        Properties properties = new Properties();
        properties.setProperty("user","12345");
        properties.setProperty("password","xxxx");
        try {
            //url+ properties
//            Connection connection =DriverManager.getConnection(url,username,password);
            Connection connection =DriverManager.getConnection(url,properties);
            System.out.println("Connected!");
            connection.close();
            System.out.println("Connection closed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

