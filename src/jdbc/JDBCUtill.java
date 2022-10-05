package src.jdbc;

import src.Admin;
import src.LoginError;
import src.Test;

import javax.swing.*;
import java.sql.*;

public class JDBCUtill {
    public static Connection makeConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url="jdbc:mysql://localhost:3306/employeedb?serverTimezone=Asia/Seoul";
            String userId="root";
            String password="1234";

            con = DriverManager.getConnection(url, userId, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public static void loginProcess(String id, String password) throws SQLException {
        Admin admin = new Admin(null, null, 0, null, null, null);
        boolean inputMatchCheck = false;
        JDBCUtill.makeConnection();

        String sql = "select id, pw from employeedb.admin";
        Statement stmt = JDBCUtill.makeConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            admin.setId(rs.getString(1));
            admin.setPw(rs.getString(2));

            if (id.equals(admin.getId()) && password.equals(admin.getPw())) {
                inputMatchCheck = true;
            }

            if (inputMatchCheck == true) {
                dispose();
                new Test();
//                new MenuSelection();
            } else {
                new LoginError();
            }
        }
    }
}
