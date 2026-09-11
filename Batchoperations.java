import java.sql.*;
void main() {
    String url ="jdbc:datadirect:sqlserver://10.30.239.16:1433;user=12345;password=XXXX;DATABASENAME=test;";
    String insert="Insert into students(id,name,age,email,department) values (?,?,?,?,?)";
    try(Connection con=DriverManager.getConnection(url)){
        Statement st=con.createStatement();
        st.addBatch("Insert into students(id,name,age,email,department) values (4,'anjali',21,'anjali@gmail.com','CSE')");
        st.addBatch("Insert into students(id,name,age,email,department) values (5,'sai',23,'sai@gmail.com','IT')");
        st.executeBatch();
          PreparedStatement pst=con.prepareStatement(insert);
          for(int i=1;i<=3;i++){
              pst.setInt(1,5+i);
              pst.setString(2,"Student"+i);
              pst.setInt(3,20+i);
              pst.setString(4,"Student@gmail.com");
              pst.setString(5,"CSE");
              pst.addBatch();
          }
          int[] res=pst.executeBatch();
          System.out.println(res.length);
    }
    catch (Exception e) {
        e.printStackTrace();
    }
}