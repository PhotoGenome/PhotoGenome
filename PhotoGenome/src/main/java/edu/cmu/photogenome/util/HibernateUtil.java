package edu.cmu.photogenome.util;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.context.ManagedSessionContext;

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
    	//System.out.println("GETTING SESSION FACTORY");
    	if(sessionFactory == null)
    		sessionFactory = buildSessionFactory();
    	return sessionFactory;
    }
    
    public static SessionFactory getSessionFactory(String hibernateCfgXml) {
    	//System.out.println("GETTING SESSION FACTORY 2");
    	if(sessionFactory == null)
    		sessionFactory = buildSessionFactory(hibernateCfgXml);
    	return sessionFactory;
    }
    
    public static void beginTransaction(Session session) {
        session.setFlushMode(FlushMode.MANUAL);
        ManagedSessionContext.bind((org.hibernate.classic.Session) session);
        session.beginTransaction();
    }
    
    public static void commitTransaction(Session session) {
		ManagedSessionContext.unbind(sessionFactory);
		session.flush();
		session.getTransaction().commit();
		session.close();
    }
    
    public static void rollbackTransaction(Session session) {
		ManagedSessionContext.unbind(sessionFactory);
		session.flush();
		session.getTransaction().rollback();
		session.close();
    }
}