import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.*;
class crud{
    public static void main(String[] args){
        String url ="jdbc:datadirect:sqlserver://10.30.239.16:1433;user=12345;password=xxxxx;DATABASENAME=test;";
        String create= """
            CREATE TABLE students (
            id INT PRIMARY KEY,
            name VARCHAR(100),
            age INT,
            email VARCHAR(100),
            department VARCHAR(50)
        );""";
        String insert= """
                Insert into students(id,name,age,email,department) values
                (2,'vyshu',25,'vyshu@gamil.com','IT');
                """;
        String select="select id,name,email from students";
//        String update="update students set age=26 where id=2";
        String delete="Delete from students where id=2";
        String stored= """
                CREATE PROCEDURE get_student
                    @student_id INT
                AS
                BEGIN
                    SELECT *
                    FROM students
                    WHERE id = @student_id;
                END""";
        String insertc= """
                CREATE OR ALTER PROCEDURE add_student
                    @id INT,
                    @name VARCHAR(100),
                    @age INT,
                    @email VARCHAR(100),
                    @department VARCHAR(50),
                    @new_id INT OUTPUT
                AS
                BEGIN
                    INSERT INTO students
                        (id,name, age, email, department)
                    VALUES
                        (@id,@name, @age, @email, @department);
                    SET @new_id = @id; 
                    END;""";

        String read= """
                CREATE PROCEDURE get_all_students
                AS
                BEGIN
                SELECT id,name,age,email,department FROM students;
                END;""";

        String update= """
                CREATE OR ALTER PROCEDURE update_student
                    @student_id INT,
                    @name VARCHAR(100),
                    @age INT,
                    @email VARCHAR(100),
                    @department VARCHAR(50),
                    @rows_updated INT OUTPUT
                AS
                BEGIN
                UPDATE students SET name = @name,age = @age,email = @email,department = @department WHERE id = @student_id;
                SET @rows_updated = @@ROWCOUNT;
                END;""";
        try(Connection connection = DriverManager.getConnection(url)){
            Statement st=connection.createStatement();
//            st.execute(update);
//            System.out.println("executed");
            //insert
//            int rows=st.executeUpdate(insert);
//            System.out.println(rows);
           //select
            ResultSet rs=st.executeQuery(select);
            while(rs.next()){
                System.out.println("Id: "+rs.getInt(1));
                System.out.println("Name: "+rs.getString(2));
                System.out.println("Email: "+rs.getString(3));

            }
            //update
            int rows=st.executeUpdate(delete);
            System.out.println(rows);

            //STORED PROCEDURES
//            CallableStatement cs =connection.prepareCall("{call add_student(?,?,?,?,?,?)}");
//            cs.setInt(1,3);
//            cs.setString(2,"Meghana");
//            cs.setInt(3,20);
//            cs.setString(4,"megh@gamil.com");
//            cs.setString(5,"ML");
//            cs.registerOutParameter(6,Types.INTEGER);
//            cs.execute();
//            int id=cs.getInt(6);
//            System.out.println("Student created with id "+id);
            //select
            CallableStatement cs1=connection.prepareCall("{call get_all_students}");
            ResultSet rs1=cs1.executeQuery();
            while(rs1.next()){
                System.out.println(rs1.getInt(1)+" "+rs1.getString(2));
            }

            //update
            CallableStatement cs=connection.prepareCall("{call update_student(?,?,?,?,?,?)}");
            cs.setInt(1,3);
            cs.setString(2,"meghanaa");
            cs.setInt(3,21);
            cs.setString(4,"Meghana@gamil.com");
            cs.setString(5,"AIML");
            cs.registerOutParameter(6, Types.INTEGER);
            int rows1=cs.executeUpdate();
            System.out.println(rows1);
        }

        catch(Exception e){
            e.printStackTrace();
        }
    }
}