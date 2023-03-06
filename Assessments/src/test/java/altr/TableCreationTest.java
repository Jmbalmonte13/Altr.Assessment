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
	final String tempTable = "temp_table";
	String t_name = "rename_table_here";
	

	@Before
	public void setUp() throws SQLException {
		String DB_Url = "jdbc:mysql://localhost:3306/testing";
		final String DB_user = "root";
	    final String DB_pass = "ANSKk08aPEDbFjDO";

		conn = DriverManager.getConnection(DB_Url, DB_user , DB_pass);
		tableChecker = new TableChecker(conn);

	}

	@After
	public void tearDown() throws SQLException {

		conn.close();

	}

	@Test
	public void testInsertRecord() throws SQLException {
		int initialRecordCount = tableChecker.recordCount(t_name);
		tableChecker.insertRecord(t_name, "Test User", "testuser@test.com");
		assertTrue(tableChecker.recordCount(t_name) == initialRecordCount + 1);
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
	public void testTableDrop() throws SQLException {
		tableChecker.createTable(tempTable, "id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), email VARCHAR(50)");
		assertTrue(tableChecker.tableExists(tempTable));
		tableChecker.dropTable(tempTable);
		assertFalse(tableChecker.tableExists(tempTable));
	}

	@Test(expected = SQLException.class) // negative test
	public void testTableDoesNotExist() throws SQLException {
		tableChecker.recordCount("nonexistent_table");
	}

	@Test(expected = SQLException.class) // negative test
	public void testCreateTableWithNoColumns() throws SQLException {
		tableChecker.createTable(t_name, "");
	}
}
