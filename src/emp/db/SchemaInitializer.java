package emp.db;

import emp.config.DbConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class SchemaInitializer {
    private SchemaInitializer() {
    }

    public static void ensureSchema() throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS admin ("
                            + "id VARCHAR(45) NOT NULL,"
                            + "pw VARCHAR(45) NOT NULL,"
                            + "birthdate INT NOT NULL,"
                            + "name VARCHAR(45) NOT NULL,"
                            + "address VARCHAR(45) NOT NULL,"
                            + "tel VARCHAR(45) NOT NULL,"
                            + "PRIMARY KEY (id)"
                            + ")"
            );
            statement.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS employee ("
                            + "number INT NOT NULL AUTO_INCREMENT,"
                            + "name VARCHAR(45) NOT NULL,"
                            + "birthdate INT NOT NULL,"
                            + "address VARCHAR(45) NOT NULL,"
                            + "email VARCHAR(45) NOT NULL,"
                            + "tel VARCHAR(45) NOT NULL,"
                            + "position VARCHAR(45) NOT NULL,"
                            + "image LONGBLOB NULL,"
                            + "PRIMARY KEY (number)"
                            + ")"
            );
        }
        addImageColumnIfMissing(connection);
        seedAdminIfEmpty(connection);
        seedEmployeeIfEmpty(connection);
    }

    private static void addImageColumnIfMissing(Connection connection) throws SQLException {
        String sql = "SELECT COUNT(*) FROM information_schema.COLUMNS "
                + "WHERE TABLE_SCHEMA = ? AND TABLE_NAME = 'employee' AND COLUMN_NAME = 'image'";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, DbConfig.DATABASE);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next() && resultSet.getInt(1) == 0) {
                    try (Statement alter = connection.createStatement()) {
                        alter.executeUpdate("ALTER TABLE employee ADD COLUMN image LONGBLOB NULL");
                    }
                }
            }
        }
    }

    private static void seedAdminIfEmpty(Connection connection) throws SQLException {
        if (count(connection, "SELECT COUNT(*) FROM admin") > 0) {
            return;
        }
        String sql = "INSERT INTO admin (id, pw, birthdate, name, address, tel) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "admin");
            statement.setString(2, "1234");
            statement.setInt(3, 19800504);
            statement.setString(4, "홍길동");
            statement.setString(5, "서울특별시");
            statement.setString(6, "010-1234-5678");
            statement.executeUpdate();
        }
    }

    private static void seedEmployeeIfEmpty(Connection connection) throws SQLException {
        if (count(connection, "SELECT COUNT(*) FROM employee") > 0) {
            return;
        }
        String sql = "INSERT INTO employee (name, birthdate, address, email, tel, position) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "심청이");
            statement.setInt(2, 19900211);
            statement.setString(3, "인천광역시");
            statement.setString(4, "simchung123@gmail.com");
            statement.setString(5, "010-1111-1111");
            statement.setString(6, "사원");
            statement.executeUpdate();
        }
    }

    private static int count(Connection connection, String sql) throws SQLException {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            resultSet.next();
            return resultSet.getInt(1);
        }
    }
}
