import java.util.*;
import java.sql.*;
class DatabaseMetadata{
    public static void main(String[] args){
        String url ="jdbc:datadirect:sqlserver://10.30.239.16:1433;user=12345;password=xyxyx;DATABASENAME=test;";
        try(Connection con = DriverManager.getConnection(url)){
            DatabaseMetaData dbm =con.getMetaData();
            System.out.println("TABLES");
            ResultSet tables =dbm.getTables("test","dbo","%",new String[]{"TABLE"});

            while (tables.next()) {
                System.out.println("Table: " +tables.getString("TABLE_NAME"));
                System.out.println("Schema: " +tables.getString("TABLE_SCHEM"));
                System.out.println("Type: " +tables.getString("TABLE_TYPE"));
                System.out.println();
            }

            System.out.println("COLUMNS");
            ResultSet col=dbm.getColumns("test","dbo","%","%");
            while(col.next()){
                System.out.println("Table Name "+col.getString("TABLE_NAME"));
                System.out.println("Column Name "+col.getString("COLUMN_NAME"));
                System.out.println("Type "+col.getString("TYPE_NAME"));
                System.out.println("Size of column: "+col.getInt("COLUMN_SIZE"));
                System.out.println("Nullable: "+col.getInt("NULLABLE"));
                System.out.println();
            }
            System.out.println("PRIMARY KEYS");
            ResultSet pk=dbm.getPrimaryKeys("test","dbo","students");
            while(pk.next()){
                System.out.println("Table: "+pk.getString("TABLE_NAME"));
                System.out.println("Column: "+pk.getString("COLUMN_NAME"));
                System.out.println("sequence: "+pk.getString("KEY_SEQ"));
                System.out.println("Constraint: "+pk.getString("PK_NAME"));
                System.out.println();
            }

            System.out.println("procedures");
            // here pattern represents the pattern of the procedure not the table
            ResultSet pd=dbm.getProcedures("test","dbo","%student%");
            while(pd.next()){
                System.out.println("Procedure Name: "+pd.getString("PROCEDURE_NAME"));
                System.out.println();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}