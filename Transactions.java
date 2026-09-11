import java.sql.*;
void main() {
    String url ="jdbc:datadirect:sqlserver://10.30.239.16:1433;user=12345;password=xyxyx;DATABASENAME=test;";
    String sql="Update students set age=? where id=?";
    try(Connection con=DriverManager.getConnection(url)){
        con.setAutoCommit(false);
        PreparedStatement pst=con.prepareStatement(sql);
        Statement st=con.createStatement();
        Savepoint sp=null;
        try{
            pst.setInt(1,28);
            pst.setInt(2,5);
            pst.executeUpdate();
            pst.setInt(1,32);
            pst.setInt(2,22);
            pst.executeUpdate();
            sp = con.setSavepoint("FIRST_PART");

            st.executeUpdate("UPDTE students " +"SET age = 24 " +"WHERE id = 3");
            st.executeUpdate("UPDATE students " +"SET age = 25 " +"WHERE id = 4");
            con.commit();
            System.out.println("Both updates commited");
        }
        catch(SQLException e){
            if(sp!=null){
                con.rollback(sp);
            }
            else
            con.rollback();
            System.out.println("Transactions roll backed");
        }
    }
    catch (Exception e) {
        e.printStackTrace();
    }
}