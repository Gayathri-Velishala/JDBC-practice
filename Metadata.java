import java.util.*;
import java.sql.*;
class Metadata{
    public static void main(String[] args){
        String url ="jdbc:datadirect:sqlserver://10.30.239.16:1433;user=12345;password=xyxyx;DATABASENAME=test;";
        String sql="select * from students where id=? and age=?";
        String select="select id,name,age,email from students";
        try(Connection con=DriverManager.getConnection(url)){
            PreparedStatement pst=con.prepareStatement(sql);
            ParameterMetaData pm=pst.getParameterMetaData();
            System.out.println("Number of parameters "+pm.getParameterCount());
            System.out.println("Type of parameter "+pm.getParameterTypeName(1));
            System.out.println("Mode of parameter "+pm.getParameterMode(2));


            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(select);
            ResultSetMetaData rsm=rs.getMetaData();
            System.out.println("Number of columns "+rsm.getColumnCount());
            System.out.println("Name of column 1 "+rsm.getColumnName(1));
            System.out.println("Type of column 1 "+rsm.getColumnTypeName(1));
            System.out.println("class name "+rsm.getColumnClassName(1));
            System.out.println("Display size "+rsm.getColumnDisplaySize(2));
            System.out.println(rsm.isNullable(2));

            //DatabaseMetadata
//            For table/schema information such as primary keys, indexes, foreign keys, etc., DatabaseMetaData is the appropriate tool.
            DatabaseMetaData dbm=con.getMetaData();
            System.out.println(dbm.getDatabaseProductName());
            System.out.println(dbm.getDatabaseProductVersion());
            System.out.println(dbm.getDriverName());
            System.out.println(dbm.getDriverVersion());
            System.out.println(dbm.getUserName());
            System.out.println(dbm.getURL());
            System.out.println(dbm.supportsTransactions());
            System.out.println(dbm.supportsStoredProcedures());
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}