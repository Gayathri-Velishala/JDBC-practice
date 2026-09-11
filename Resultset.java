import java.sql.*;
void main() {
    String url ="jdbc:datadirect:sqlserver://10.30.239.16:1433;user=12345;password=xyxyx;DATABASENAME=test;";
    try(Connection con=DriverManager.getConnection(url)){

        // Scrollable result set , read only
        Statement st1=con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );
        ResultSet rs=st1.executeQuery("select name from students");
        rs.next();
        System.out.println(rs.getString(1));
        rs.last();
        System.out.println(rs.getString("name"));
        rs.previous();
        System.out.println(rs.getString("name"));
        rs.first();
        System.out.println(rs.getString("name"));
        // absolute is used to move to a specific row and negative indexes are used to move from the last row -1 is the last row
        rs.absolute(3);
        System.out.println(rs.getString("name"));
        System.out.println(rs.getRow());
        System.out.println(rs.isFirst());
        System.out.println(rs.isLast());
        // relative specifies the position to move from the current row
        rs.relative(1);
        System.out.println(rs.getRow());


        // UPDATABLE RESULT SET
        Statement st=con.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        );
        ResultSet rs1=st.executeQuery("Select id,name from students");
        rs1.absolute(3);
        rs1.updateString("name","meghana"); //This is used to update the data in result set not directly into database
//        rs.updateInt("age",25); this is not possible only the selected columns in the query can be updated
        rs1.updateRow(); // This method updates the data in the database
        System.out.println(rs1.getString("name"));
        rs1.last();
        rs1.deleteRow();
        rs1.last();
        System.out.println(rs1.getString(2));
    }
    catch(Exception e){
        e.printStackTrace();
    }
}