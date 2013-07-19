package edu.cmu.photogenome.util;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.*;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.*;

/**
 * This class is only used as a utility to export data from a database into
 * an XML file that can be read by the DBUnit test cases
 */
public class DatabaseExportSample
{
    public static void main(String[] args) throws Exception
    {
        // database connection
        Class driverClass = Class.forName("com.mysql.jdbc.Driver");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/PG_DB", "root", "root");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        // full database export
        IDataSet fullDataSet = connection.createDataSet();
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream("C:\\PG_Test_Data.xml"));
    }
}