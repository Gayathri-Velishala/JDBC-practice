import java.sql.*;
import java.util.*;

void main() {
        String url ="jdbc:datadirect:sqlserver://10.30.239.16:1433;user=12345;password=xtxyx;DATABASENAME=test;InitializationString=set QUOTED_IDENTIFIER on";
        String sql="SELECT \"name\" from Employee;";
        try(Connection con=DriverManager.getConnection(url)){
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
}