package edu.cmu.photogenome.util;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.ManagedSessionContext;

/**
 * Utility class for retrieving a Hibernate @SessionFactory and for starting, committing, and rolling
 * back Hibernate @Session transactions.
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	/**
	 * Create a new @SessionFactory using the default Hibernate config file
	 * 
	 * @return	@SessionFactory instance
	 */
	private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
	}
	
	/**
	 * Create a new @SessionFactory using the given Hibernate config file
	 * 
	 * @param hibernateCfgXml	Hibernate config file to use
	 * @return	@SessionFactory instanc
	 */
    private static SessionFactory buildSessionFactory(String hibernateCfgXml) {
        try {
            return new Configuration().configure(hibernateCfgXml).buildSessionFactory();
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    /**
     * Retrieve a @SessionFactory using the default Hibernate config file
     * 
     * @return	@SessionFactory instance
     */
    public static SessionFactory getSessionFactory() {
    	if(sessionFactory == null)
    		sessionFactory = buildSessionFactory();
    	return sessionFactory;
    }
    
    /**
     * Retrieve a @SessionFactory using the given Hibernate config file
     * 
     * @param hibernateCfgXml	Hibernate config file to use
     * @return @SessionFactory instance
     */
    public static SessionFactory getSessionFactory(String hibernateCfgXml) {
    	if(sessionFactory == null)
    		sessionFactory = buildSessionFactory(hibernateCfgXml);
    	return sessionFactory;
    }
    
    /**
     * Begin a Hibernate transaction
     * 
     * @param session
     */
    public static void beginTransaction(Session session) {
        session.setFlushMode(FlushMode.MANUAL);
        ManagedSessionContext.bind((org.hibernate.classic.Session) session);
        session.beginTransaction();
    }
    
    /**
     * Commit a Hibernate transaction
     * 
     * @param session
     */
    public static void commitTransaction(Session session) {
		ManagedSessionContext.unbind(sessionFactory);
		session.flush();
		session.getTransaction().commit();
		session.close();
    }
    
    /**
     * Rollback a Hibernate transaction
     * 
     * @param session
     */
    public static void rollbackTransaction(Session session) {
		ManagedSessionContext.unbind(sessionFactory);
		session.flush();
		session.getTransaction().rollback();
		session.close();
    }
}