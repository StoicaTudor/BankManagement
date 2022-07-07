package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCConnectionWrapper {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/";

    private static final String USER = "Citadin2";
    private static final String PASS = "Aaladin2000-";
    private static final int TIMEOUT = 5;

    private Connection connection;

    public JDBCConnectionWrapper(String schema, boolean createTables) {
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL + schema, USER, PASS);

            if (createTables) {
                createTables();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTables() throws SQLException {
        Statement statement = connection.createStatement();

        String sql = getSchema();
        statement.execute(sql);
    }

    public boolean testConnection() throws SQLException {
        return connection.isValid(TIMEOUT);
    }

    public Connection getConnection() {
        return connection;
    }

    private String getSchema() {

        StringBuilder schema = new StringBuilder();

        try {
            File myObj = new File("/home/citadin/Desktop/DB");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                schema.append(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return schema.toString();
    }

    public static void main(String[] args) {
        JDBCConnectionWrapper wrapper = new JDBCConnectionWrapper("Bank", false);
        try {
            wrapper.testConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}