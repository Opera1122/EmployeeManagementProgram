package src.jdbc;

import src.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeDAO {
    static Employee employee = new Employee(0, null, 0, null, null, null, null);
    public static ResultSet rs;
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

    public static void showNumberAndNameTable(DefaultTableModel model) throws SQLException {
        JDBCUtill.makeConnection();

        String sql = "select number, name from employeedb.employee";
        Statement stmt = JDBCUtill.makeConnection().createStatement();
        rs = stmt.executeQuery(sql);

        while(rs.next()) {
            employee.setNumber(rs.getInt(1));
            employee.setName(rs.getString(2));

            model.addRow(new Object[]{employee.getNumber(), employee.getName()});
        }

        stmt.close();
        JDBCUtill.makeConnection().close();
    }

    public static void showListToTextFields(
            JTable table,
            int selectedRow,
            DefaultTableModel model,
            JTextField infoNumberTextField,
            JTextField infoNameTextField,
            JTextField infoBirthDateTextField,
            JTextField infoAddressTextField,
            JTextField infoEmailTextField,
            JTextField infoTelTextField,
            JTextField infoPositionTextField
    ) throws SQLException {
        JDBCUtill.makeConnection();

        String sql = "select * from employeedb.employee";
        Statement stmt = JDBCUtill.makeConnection().createStatement();
        rs = stmt.executeQuery(sql);

        while(rs.next()) {
            employee.setNumber(rs.getInt(1));
            employee.setName(rs.getString(2));
            employee.setBirthDate(rs.getInt(3));
            employee.setAddress(rs.getString(4));
            employee.setEmail(rs.getString(5));
            employee.setTel(rs.getString(6));
            employee.setPosition(rs.getString(7));

            int number = employee.getNumber();
            String name = employee.getName();
            int birthDate = employee.getBirthDate();
            String address = employee.getAddress();
            String email = employee.getEmail();
            String tel = employee.getTel();
            String position = employee.getPosition();

            selectedRow = (int) model.getValueAt(table.getSelectedRow(),0);

            if (selectedRow == number) {
                infoNumberTextField.setText(String.valueOf(number));
                infoNameTextField.setText(name);
                infoBirthDateTextField.setText(String.valueOf(birthDate));
                infoAddressTextField.setText(address);
                infoEmailTextField.setText(email);
                infoTelTextField.setText(tel);
                infoPositionTextField.setText(position);
            }
//            for (int i = 0; i <= selectedRow; i++) {
//                System.out.println(number);
//                if (selectedRow == number) {
//                    infoNumberTextField.setText(String.valueOf(number));
//                    infoNameTextField.setText(name);
//                    infoBirthDateTextField.setText(String.valueOf(birthDate));
//                    infoAddressTextField.setText(address);
//                    infoEmailTextField.setText(email);
//                    infoTelTextField.setText(tel);
//                    infoPositionTextField.setText(position);
//                }
//            }
        }

        stmt.close();
        JDBCUtill.makeConnection().close();
    }
}
