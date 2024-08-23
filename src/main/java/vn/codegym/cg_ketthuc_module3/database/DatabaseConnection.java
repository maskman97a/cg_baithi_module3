package vn.codegym.cg_ketthuc_module3.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final static String URL = "jdbc:mysql://localhost:3306/";
    private final static String SCHEMA = "phongtro";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "123456a@";

    private final Connection connection;
    private String schemaName;
    private final static DatabaseConnection instance = new DatabaseConnection();

    private DatabaseConnection() {
        this.connection = initConnection();
        this.schemaName = SCHEMA;
    }

    private static Connection initConnection() {
        Connection connection;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL + SCHEMA, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static DatabaseConnection getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
