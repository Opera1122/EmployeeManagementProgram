package src.jdbc;

import src.Employee;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDAO {
    static Employee employee = new Employee(0, null, 0, null, null, null, null);
    public static void signUpEmployee(String name, int birthDate, String address, String email, String tel, String position) throws SQLException {
        JDBCUtill.makeConnection();

        String autoSql = "alter table employee auto_increment = 1;";
        Statement stmt = JDBCUtill.makeConnection().createStatement();
        stmt.executeUpdate(autoSql);

        String insertSql = "insert into employee values(?, ?, ?, ?, ?, ?, ?);";

        try {
            PreparedStatement pstmt = JDBCUtill.makeConnection().prepareStatement(insertSql);
            employee.setNumber(0);
            pstmt.setInt(1, employee.getNumber());
            employee.setName(name);
            pstmt.setString(2, employee.getName());
            employee.setBirthDate(birthDate);
            pstmt.setInt(3, employee.getBirthDate());
            employee.setAddress(address);
            pstmt.setString(4, employee.getAddress());
            employee.setEmail(email);
            pstmt.setString(5, employee.getEmail());
            employee.setTel(tel);
            pstmt.setString(6, employee.getTel());
            employee.setPosition(position);
            pstmt.setString(7, employee.getPosition());

            int row = pstmt.executeUpdate();

//            if (row==1) {
//                성공
//            } else {
//                실패
//            }

            stmt.close();
            pstmt.close();
            JDBCUtill.makeConnection().close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
