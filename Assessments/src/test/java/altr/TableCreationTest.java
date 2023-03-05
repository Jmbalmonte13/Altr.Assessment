package altr;

import org.junit.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.sql.*;

public class TableCreationTest {

	private Connection conn;
	private TableChecker tableChecker;
	String t_name = "Jerome_B15";
	String tempTable = "Jeromeb_13";

	@Before
	public void setUp() throws SQLException {
		String DB_URL = "jdbc:mysql://localhost:3306/testing";
		final String DB_USER = "root";
		final String DB_PASSWORD = "ANSKk08aPEDbFjDO";

		conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		tableChecker = new TableChecker(conn);

	}

	@After
	public void tearDown() throws SQLException {

		conn.close();

	}

	@Test
	public void testTableExists() throws SQLException {
		assertTrue(tableChecker.tableExists(t_name));
		assertFalse(tableChecker.tableExists("nonexistent_table"));
	}

	@Test
	public void testIndexExists() throws SQLException {
		assertTrue(tableChecker.indexExists(t_name, "idx_name"));
		assertTrue(tableChecker.indexExists(t_name, "idx_email"));
		assertFalse(tableChecker.indexExists(t_name, "idx_nonexistent"));
	}

	@Test
	public void testColumnExists() throws SQLException {
		assertTrue(tableChecker.columnExists(t_name, "id"));
		assertTrue(tableChecker.columnExists(t_name, "name"));
		assertTrue(tableChecker.columnExists(t_name, "email"));
		assertFalse(tableChecker.columnExists(t_name, "nonexistent_column"));
	}

	@Test
	public void testRecordCount() throws SQLException {
		assertTrue(tableChecker.recordCount(t_name) == 10);
	}

	@Test
	public void testInsertRecord() throws SQLException {
		int initialRecordCount = tableChecker.recordCount(t_name);
		tableChecker.insertRecord(t_name, "Test User", "testuser@test.com");
		assertTrue(tableChecker.recordCount(t_name) == initialRecordCount + 1);
	}

	@Test
	public void testTableDrop() throws SQLException {
		tableChecker.createTable(tempTable, "id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), email VARCHAR(50)");
		assertTrue(tableChecker.tableExists(tempTable));
		tableChecker.dropTable(tempTable);
		assertFalse(tableChecker.tableExists(tempTable));
	}


}
