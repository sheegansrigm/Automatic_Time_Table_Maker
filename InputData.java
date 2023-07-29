import java.util.*;
import java.sql.*;
public class InputData {
    public static void main(String args[]){
        Scanner s=new Scanner(System.in);
        String jdbcDriver = "jdbc:mysql://localhost:3306/timetable";
        String user = "user";
        String pass = "pass123";
        try (Connection con = DriverManager.getConnection(jdbcDriver, user,pass))
        {
            String tableName="COURSES";
            System.out.println("How many courses?");
            String num=s.nextLine();
            int n=Integer.parseInt(num);
            for(int i=1;i<=n;i++){
                String sql = "INSERT INTO " + tableName + " (periods,course_name,faculty) VALUES (?,?,?)";
                PreparedStatement stmt = con.prepareStatement(sql);
                String course_name=s.nextLine();
                String faculty=s.nextLine();
                int periods=Integer.parseInt(s.nextLine());
                stmt.setInt(1,periods);
                stmt.setString(2,course_name);
                stmt.setString(3,faculty);
                stmt.executeUpdate();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        s.close();
    }
}
