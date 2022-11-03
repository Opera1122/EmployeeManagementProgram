package src.jdbc;

import src.Employee;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class EmployeeDAO {
    static Employee employee = new Employee(0, null, 0, null, null, null, null);
    public static ResultSet rs;

    public static void SignUpEmployee(String name, int birthDate, String address, String email, String tel, String position) throws SQLException {
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
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void ShowNumberAndNameTable(DefaultTableModel model) throws SQLException {
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
    }

    public static int SelectedNumberValueFilter(JTable table, int selectedRow, DefaultTableModel model) {
        selectedRow = (int) model.getValueAt(table.getSelectedRow(), 0);

        return selectedRow;
    }

    public static void ShowListToTextFields(
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

            SelectedNumberValueFilter(table, selectedRow, model);

            if (SelectedNumberValueFilter(table, selectedRow, model) == number) {
                infoNumberTextField.setText(String.valueOf(number));
                infoNameTextField.setText(name);
                infoBirthDateTextField.setText(String.valueOf(birthDate));
                infoAddressTextField.setText(address);
                infoEmailTextField.setText(email);
                infoTelTextField.setText(tel);
                infoPositionTextField.setText(position);
            }
        }

        stmt.close();
    }

    public static void EditEmployee(
            JTable table,
            int selectedRow,
            DefaultTableModel model,
            String name,
            int birthDate,
            String address,
            String email,
            String tel,
            String position,
            int number
    ) {
        JDBCUtill.makeConnection();

        String sql = "update employee set number=?, name=?, birthdate=?, address=?, email=?, tel=?, position=? where number=?";

        selectedRow = SelectedNumberValueFilter(table, selectedRow, model);

        try {
            PreparedStatement pstmt = JDBCUtill.makeConnection().prepareStatement(sql);
            employee.setNumber(number);
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
            pstmt.setInt(8, selectedRow);

            int row = pstmt.executeUpdate();

//            if (row==1)
//                수정 성공
//            else
//                실패

            pstmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void DeleteEmployee(JTable table, int selectedRow, DefaultTableModel model) {
        JDBCUtill.makeConnection();

        String sql="delete from employee where number=?";
        selectedRow = SelectedNumberValueFilter(table, selectedRow, model);

        try {
            PreparedStatement pstmt = JDBCUtill.makeConnection().prepareStatement(sql);
            employee.setNumber(selectedRow);
            pstmt.setInt(1, employee.getNumber());

            int row = pstmt.executeUpdate();

//            if (row==1)
//                삭제 성공
//            else
//                실패

            pstmt.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
