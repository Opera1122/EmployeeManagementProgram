package emp.dao;

import emp.db.JDBCUtil;
import emp.model.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AdminDAO {
    public Optional<Admin> findByCredentials(String id, String password) throws SQLException {
        String sql = "SELECT id, pw, birthdate, name, address, tel FROM admin WHERE id = ? AND pw = ?";
        try (PreparedStatement statement = JDBCUtil.getConnection().prepareStatement(sql)) {
            statement.setString(1, id);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }
                Admin admin = new Admin();
                admin.setId(resultSet.getString("id"));
                admin.setPw(resultSet.getString("pw"));
                admin.setBirthDate(resultSet.getInt("birthdate"));
                admin.setName(resultSet.getString("name"));
                admin.setAddress(resultSet.getString("address"));
                admin.setTel(resultSet.getString("tel"));
                return Optional.of(admin);
            }
        }
    }
}
