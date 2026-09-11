import com.ddtek.jdbcx.sqlserver.SQLServerDataSource;
import java.sql.*;

class datasource{
    public static void main(String[] args) {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.30.239.16");
        dataSource.setPortNumber(1433);
        dataSource.setUser("12345");
        dataSource.setPassword("xyxyx");
        dataSource.setDatabaseName("test");

        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}