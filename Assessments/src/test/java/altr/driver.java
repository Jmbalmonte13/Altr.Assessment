package altr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;

private class driver { // will fix this tomorrow let's we
	
	   private Connection conn;
	    private TableChecker tableChecker;
	    final String t_name = "Jerome_B";

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

}
