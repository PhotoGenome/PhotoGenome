package edu.cmu.photogenome.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory buildSessionFactory(String hibernateCfgXml) {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure(hibernateCfgXml).buildSessionFactory();
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory(String hibernateCfgXml) {
        return buildSessionFactory(hibernateCfgXml);
    }

}