package src.jdbc;

import src.Employee;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDAO {
    static Employee employee = new Employee(0, null, 0, null, null, null, null, null);
    public static void signUpEmployee(String name, int birthDate, String address, String email, String tel, Object image, String position) {
        JDBCUtill.makeConnection();

        String sql="insert into employee values(?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement pstmt = JDBCUtill.makeConnection().prepareStatement(sql);
            employee.setName(name);
            pstmt.setString(1, employee.getName());
            employee.setBirthDate(birthDate);
            pstmt.setInt(2, employee.getBirthDate());
            employee.setAddress(address);
            pstmt.setString(3, employee.getAddress());
            employee.setEmail(email);
            pstmt.setString(4, employee.getEmail());
            employee.setTel(tel);
            pstmt.setString(5, employee.getTel());
            employee.setImage(image);
            pstmt.setObject(6, employee.getImage());
            employee.setPosition(position);
            pstmt.setString(7, employee.getPosition());

            int row = pstmt.executeUpdate();

//            if (row==1) {
//                성공
//            } else {
//                실패
//            }

            pstmt.close();
            JDBCUtill.makeConnection().close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
