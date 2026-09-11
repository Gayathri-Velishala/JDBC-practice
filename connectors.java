import com.ddtek.jdbcx.sqlserver.SQLServerDataSource;
import java.sql.*;
import java.util.Properties;

public class connectors{
    public static void main(String args[]) {
//    String url ="jdbc:datadirect:sqlserver://10.30.239.16:1433;user=12345;password=12345;DATABASENAME=test;FetchTSWTZAsTimestamp=true;";
        String urlp = "jdbc:datadirect:sqlserver://10.30.239.16:1433;DATABASENAME=test";
        Properties prop = new Properties();
        prop.setProperty("user", "12335");
        prop.setProperty("password", "xxxxx");
        prop.setProperty("FetchTSWTZAsTimestamp", "true");

        try (Connection con = DriverManager.getConnection(urlp, prop)) {

            System.out.println("Connected");

            String sql = "SELECT * FROM datetimeoffset";

            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                while (rs.next()) {
                    Object o = rs.getObject(2);
                    System.out.println("Type: " + o.getClass().getName());
                }
            }

        } catch (Exception e){
            e.printStackTrace();
        }


        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("10.30.239.16");
        ds.setPortNumber(1433);
        ds.setUser("12345");
        ds.setPassword("xyxyx");
        ds.setDatabaseName("test");
        ds.setFetchTSWTZasTimestamp("false");
        String sql= """
                IF OBJECT_ID('datetimeoffset', 'U') IS NOT NULL
                                DROP TABLE datetimeoffset;
                CREATE TABLE datetimeoffset (id INT IDENTITY(1,1) PRIMARY KEY,dt DATETIMEOFFSET(7));""";
        String insert="INSERT INTO datetimeoffset(dt) values ('2026-09-07 10:11:20.1234567 +5:30')";
        try (Connection con = ds.getConnection()) {
            Statement st=con.createStatement();
            System.out.println("Connected");
            System.out.println(ds.getFetchTSWTZasTimestamp());
    //        st.execute(sql);
    //        int r=st.executeUpdate(insert);
            ResultSet rs=st.executeQuery("Select * from datetimeoffset;");
            ResultSetMetaData rsm=rs.getMetaData();
            System.out.println("SQL TYPE: "+rsm.getColumnTypeName(2));
            while(rs.next()){
                Object o=rs.getObject(2);
                System.out.println(o.getClass().getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}