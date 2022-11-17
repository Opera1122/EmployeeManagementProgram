package src.jdbc;

import src.jdbc.dto.Admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminDAO {
    public static boolean inputMatchCheck = false;
    public static void LoginProcess(String id, String password) throws SQLException {
        Admin admin = new Admin(null, null, 0, null, null, null);
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
        }
    }
}
