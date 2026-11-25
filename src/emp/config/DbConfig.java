package emp.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public final class DbConfig {
    public static final String HOST;
    public static final String DATABASE;
    public static final String USER;
    public static final String PASSWORD;

    static {
        Properties properties = defaultProperties();
        loadFromClasspath(properties, "/emp/config/db.properties");
        load(properties, Paths.get("config", "db.properties"));

        HOST = properties.getProperty("host", "localhost:3306").trim();
        DATABASE = properties.getProperty("database", "employeedb").trim();
        USER = properties.getProperty("user", "root").trim();
        PASSWORD = properties.getProperty("password", "1234");
    }

    private DbConfig() {
    }

    public static String serverUrl() {
        return "jdbc:mysql://" + HOST
                + "/?serverTimezone=Asia/Seoul&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true";
    }

    private static Properties defaultProperties() {
        Properties properties = new Properties();
        properties.setProperty("host", "localhost:3306");
        properties.setProperty("database", "employeedb");
        properties.setProperty("user", "root");
        properties.setProperty("password", "1234");
        return properties;
    }

    private static void load(Properties properties, Path path) {
        if (!Files.exists(path)) {
            return;
        }
        try (InputStream input = Files.newInputStream(path)) {
            properties.load(input);
        } catch (IOException ignored) {
        }
    }

    private static void loadFromClasspath(Properties properties, String resource) {
        try (InputStream input = DbConfig.class.getResourceAsStream(resource)) {
            if (input != null) {
                properties.load(input);
            }
        } catch (IOException ignored) {
        }
    }
}
