package edu.cmu.photogenome.util;

import org.apache.struts2.StrutsTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;

/**
 * Abstract class for testing Struts action classes, which also uses DBUnit to perform 
 * a clean insert of data before the execution of each test.
 */
public abstract class StrutsDbUnitTestCase extends StrutsTestCase {

	private DBTestCaseImpl dbTestCase = new DBTestCaseImpl("PG_Test_Data.xml");
	
	public StrutsDbUnitTestCase() {
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.jdbc.Driver");  
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://pg-db.c41slebym6tm.us-east-1.rds.amazonaws.com:3306/pg_db");  
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "msit2013");
	}
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.dbTestCase.doSetup();
	}
}
