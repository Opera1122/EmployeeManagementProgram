package emp.dao;

import emp.db.JDBCUtil;
import emp.model.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO {
    public List<Employee> findAllSummaries() throws SQLException {
        String sql = "SELECT number, name FROM employee ORDER BY number";
        try (PreparedStatement statement = JDBCUtil.getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            List<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setNumber(resultSet.getInt("number"));
                employee.setName(resultSet.getString("name"));
                employees.add(employee);
            }
            return employees;
        }
    }

    public Optional<Employee> findByNumber(int number) throws SQLException {
        String sql = "SELECT number, name, birthdate, address, email, tel, position, image FROM employee WHERE number = ?";
        try (PreparedStatement statement = JDBCUtil.getConnection().prepareStatement(sql)) {
            statement.setInt(1, number);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    return Optional.empty();
                }
                return Optional.of(mapRow(resultSet));
            }
        }
    }

    public List<Employee> searchByNumber(int number) throws SQLException {
        String sql = "SELECT number, name FROM employee WHERE number = ?";
        try (PreparedStatement statement = JDBCUtil.getConnection().prepareStatement(sql)) {
            statement.setInt(1, number);
            try (ResultSet resultSet = statement.executeQuery()) {
                return mapSummaries(resultSet);
            }
        }
    }

    public List<Employee> searchByName(String name) throws SQLException {
        String sql = "SELECT number, name FROM employee WHERE name LIKE ? ORDER BY number";
        try (PreparedStatement statement = JDBCUtil.getConnection().prepareStatement(sql)) {
            statement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                return mapSummaries(resultSet);
            }
        }
    }

    public int insert(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee (name, birthdate, address, email, tel, position, image) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = JDBCUtil.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bindWritableFields(statement, employee);
            statement.executeUpdate();
            try (ResultSet keys = statement.getGeneratedKeys()) {
                if (keys.next()) {
                    int number = keys.getInt(1);
                    employee.setNumber(number);
                    return number;
                }
            }
            return 0;
        }
    }

    public boolean update(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET name = ?, birthdate = ?, address = ?, email = ?, tel = ?, position = ?, image = ? WHERE number = ?";
        try (PreparedStatement statement = JDBCUtil.getConnection().prepareStatement(sql)) {
            bindWritableFields(statement, employee);
            statement.setInt(8, employee.getNumber());
            return statement.executeUpdate() == 1;
        }
    }

    public boolean delete(int number) throws SQLException {
        String sql = "DELETE FROM employee WHERE number = ?";
        try (PreparedStatement statement = JDBCUtil.getConnection().prepareStatement(sql)) {
            statement.setInt(1, number);
            return statement.executeUpdate() == 1;
        }
    }

    private static void bindWritableFields(PreparedStatement statement, Employee employee) throws SQLException {
        statement.setString(1, employee.getName());
        statement.setInt(2, employee.getBirthDate());
        statement.setString(3, employee.getAddress());
        statement.setString(4, employee.getEmail());
        statement.setString(5, employee.getTel());
        statement.setString(6, employee.getPosition());
        byte[] image = employee.getImage();
        if (image == null || image.length == 0) {
            statement.setNull(7, Types.BLOB);
        } else {
            statement.setBytes(7, image);
        }
    }

    private static List<Employee> mapSummaries(ResultSet resultSet) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        while (resultSet.next()) {
            Employee employee = new Employee();
            employee.setNumber(resultSet.getInt("number"));
            employee.setName(resultSet.getString("name"));
            employees.add(employee);
        }
        return employees;
    }

    private static Employee mapRow(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setNumber(resultSet.getInt("number"));
        employee.setName(resultSet.getString("name"));
        employee.setBirthDate(resultSet.getInt("birthdate"));
        employee.setAddress(resultSet.getString("address"));
        employee.setEmail(resultSet.getString("email"));
        employee.setTel(resultSet.getString("tel"));
        employee.setPosition(resultSet.getString("position"));
        employee.setImage(resultSet.getBytes("image"));
        return employee;
    }
}
