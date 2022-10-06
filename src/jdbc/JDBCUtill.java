package src.jdbc;

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
}
