package altr;

import java.sql.*;
import org.junit.*;

public class TableChecker {

	private Connection conn;

	public TableChecker(Connection conn) {
	}

	public boolean tableExists(String tableName) throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'testing' AND table_name = '"
							+ tableName + "'");
			rs.next();
			int count = rs.getInt(1);
			return count == 1;
		}
	}

	public int recordCount(String tableName) throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + tableName);
			rs.next();
			return rs.getInt(1);
		}
	}

	public boolean columnExists(String tableName, String columnName) throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"SELECT COUNT(*) FROM information_schema.columns WHERE table_schema = 'testing' AND table_name = '"
							+ tableName + "' AND column_name = '" + columnName + "'");
			rs.next();
			int count = rs.getInt(1);
			return count == 1;
		}
	}

	public void createTable(String tableName, String columns) throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate("CREATE TABLE " + tableName + " (" + columns + ")");
		}
	}

	public boolean indexExists(String tableName, String indexName) throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			ResultSet rs = stmt.executeQuery(
					"SELECT COUNT(*) FROM information_schema.statistics WHERE table_schema = 'testing' AND table_name = '"
							+ tableName + "' AND index_name = '" + indexName + "'");
			rs.next();
			int count = rs.getInt(1);
			return count == 1;
		}
	}

	public void dropTable(String tableName) throws SQLException {
		try (Statement stmt = conn.createStatement()) {
			stmt.executeUpdate("DROP TABLE " + tableName);
		}
	}

	public void insertRecord(String tableName, String name, String email) throws SQLException {
		try (PreparedStatement stmt = conn
				.prepareStatement("INSERT INTO " + tableName + " (name, email) VALUES (?, ?)")) {
			stmt.setString(1, name);
			stmt.setString(2, email);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected == 0) {
				throw new SQLException("Insert failed");
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new SQLException("Duplicate entry for email: " + email, e);
		}
	}

}
