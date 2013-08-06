package edu.cmu.photogenome.util;

import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 * Concrete class for instantiating DBUnit test cases with a given set of data to use
 */
public class DBTestCaseImpl extends DBTestCase {

	private String xmlDataSet;
	
	public DBTestCaseImpl() {
		super();
	}
	
	public DBTestCaseImpl(String xmlDataSet) {
		super();
		this.xmlDataSet = xmlDataSet;
	}
	
	@Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream(this.xmlDataSet));  
    }
	
	public void doSetup() throws Exception {
		super.setUp();
	}
	
	public void doTearDown() throws Exception {
		super.tearDown();
	}
	
	public String getXmlDataSet() {
		return xmlDataSet;
	}

	public void setXmlDataSet(String xmlDataSet) {
		this.xmlDataSet = xmlDataSet;
	}

}
