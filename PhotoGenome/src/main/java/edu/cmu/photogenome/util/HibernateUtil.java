package edu.cmu.photogenome.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
	}
	
    private static SessionFactory buildSessionFactory(String hibernateCfgXml) {
        try {
            return new Configuration().configure(hibernateCfgXml).buildSessionFactory();
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
    	if(sessionFactory == null)
    		sessionFactory = buildSessionFactory();
    	return sessionFactory;
    }
    
    public static SessionFactory getSessionFactory(String hibernateCfgXml) {
    	if(sessionFactory == null)
    		sessionFactory = buildSessionFactory(hibernateCfgXml);
    	return sessionFactory;
    }
}