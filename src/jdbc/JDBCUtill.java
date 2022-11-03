package src.jdbc;

import src.exception.ErrorHandler;

import java.sql.*;


public class JDBCUtill {

    private static Connection connection = null;

    private static final String className = "com.mysql.cj.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/employeedb?serverTimezone=Asia/Seoul";
    private static final String userId = "root";
    private static final String password = "1234";

    private JDBCUtill() {

    }

    public static Connection makeConnection() {
        if (connection == null) {
            try {
                Class.forName(className);
                connection = DriverManager.getConnection(url, userId, password);
            } catch (SQLException | ClassNotFoundException e) {
                ErrorHandler.error1();

                System.out.println("JDBCUtill/makeConnection - 에러 발생: " + e.getMessage());
            }
        }

        return connection;
    }
}