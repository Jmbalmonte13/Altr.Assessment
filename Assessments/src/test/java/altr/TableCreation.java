package altr;

import java.sql.*;

public class TableCreation {

	 public static void main(String[] args) {
	        String DB_URL = "jdbc:mysql://localhost:3306/testing";
	       final String DB_USER = "root";
	       final String DB_PASSWORD = "ANSKk08aPEDbFjDO";
	       final String Table_Name = "JeromeB13";

	        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
	            createTable(conn, Table_Name);
	            insertRecords(conn, Table_Name);
	        } catch (SQLException e) {
	            System.err.println("Error: " + e.getMessage());
	        }
	    }

	    public static void createTable(Connection conn, String tableName) throws SQLException {
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
	            System.out.println("Table created successfully.");
	        }
	    }

	    public static void insertRecords(Connection conn, String tableName) throws SQLException {
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
	            int rowsInserted = insertStmt.executeUpdate(insertSql);
	            if (rowsInserted == 10) {
	                System.out.println("All records inserted successfully.");
	            }
	        }
	    }
	}


