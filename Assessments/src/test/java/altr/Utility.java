package altr;

import static org.junit.Assert.assertTrue;


import org.junit.*;
import static org.junit.Assert.*;

import java.sql.*;

public class Utility {

    private Connection conn;
    private final String table_name = "Jerome_B440";

    @Before
    public void setUp() throws SQLException {
        String DB_URL = "jdbc:mysql://localhost:3306/testing";
        final String DB_USER = "root";
        final String DB_PASSWORD = "ANSKk08aPEDbFjDO";

        conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @After
    public void tearDown() throws SQLException {
        conn.close();
    }

    @Test
    public void testCreateAndInsertRecords() throws SQLException {
        createTable(conn, table_name);
        insertRecords(conn, table_name);
        assertTrue(tableExists(table_name));
        assertTrue(recordCount(table_name) == 10);
    }

    private void createTable(Connection conn, String tableName) throws SQLException {
        String createTableSql = "CREATE TABLE " + tableName + " (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(50), " +
                "age INT, " +
                "email VARCHAR(50), " +
                "phone VARCHAR(20), " +
                "PRIMARY KEY (id), " +
                "INDEX idx_name (name), " +
                "INDEX idx_email (email)" +
                ")";
        try (Statement createTableStmt = conn.createStatement()) {
            createTableStmt.executeUpdate(createTableSql);
        }
    }

    private void insertRecords(Connection conn, String tableName) throws SQLException {
        String insertSql = "INSERT INTO " + tableName + " (name, age, email, phone) VALUES " +
                "('John Smith', 25, 'john@example.com', '555-1234')," +
                "('Jane Doe', 30, 'jane@example.com', '555-5678')," +
                "('Bob Johnson', 40, 'bob@example.com', '555-9012')," +
                "('Sally Brown', 35, 'sally@example.com', '555-3456')," +
                "('Mike Jones', 20, 'mike@example.com', '555-7890')," +
                "('Lisa Davis', 45, 'lisa@example.com', '555-2345')," +
                "('David Lee', 28, 'david@example.com', '555-6789')," +
                "('Karen Taylor', 50, 'karen@example.com', '555-0123')," +
                "('Tom Wilson', 33, 'tom@example.com', '555-4567')," +
                "('Emily Green', 27, 'emily@example.com', '555-8901')";
        try (Statement insertStmt = conn.createStatement()) {
            insertStmt.executeUpdate(insertSql);
        }
    }

    private boolean tableExists(String tableName) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(
                    "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'testing' AND table_name = '"
                            + tableName + "'");
            rs.next();
            int count = rs.getInt(1);
            return count == 1;
        }
    }

    private int recordCount(String tableName) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
            rs.next();
            return rs.getInt(1);
        }
    }
}
	

