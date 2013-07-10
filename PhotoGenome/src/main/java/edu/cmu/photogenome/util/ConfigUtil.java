package edu.cmu.photogenome.util;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *	Utility class for common configuration related tasks
 */
public class ConfigUtil {
	
	final static Logger log = LoggerFactory.getLogger(ConfigUtil.class);
	
	/**
	 * Helper method to return properties object with access to ApplicationResources.properties
	 * 
	 * @return properties object
	 */
	public static Properties getApplicationProperties() {
		Properties config = new Properties();
		try {
			config.load(ConfigUtil.class.getClassLoader().getResourceAsStream("ApplicationResources.properties"));
			return config;
		}
		catch(IOException ioe) {
			log.warn(ioe.getMessage(), ioe);
			return null;
		}
	}
}
