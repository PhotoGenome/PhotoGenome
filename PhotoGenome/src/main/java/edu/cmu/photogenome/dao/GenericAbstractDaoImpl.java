package edu.cmu.photogenome.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

import edu.cmu.photogenome.util.HibernateUtil;

public abstract class GenericAbstractDaoImpl <T, ID extends Serializable> implements GenericDao<T, ID> {

	final Logger log = LoggerFactory.getLogger(GenericAbstractDaoImpl.class);
	
	//@SessionTarget
	protected Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	
	//@TransactionTarget
	//Transaction transaction;
	
    protected Class<T> type;

    @SuppressWarnings("unchecked")
	public GenericAbstractDaoImpl() {
        type = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
	
    public boolean delete(T entity) {
    	boolean result = false;
    	
    	try {
    		session.beginTransaction();
			session.delete(entity);
			result = true;
			session.getTransaction().commit();
		}
		catch(Exception e) {
			session.getTransaction().rollback();
			log.warn(e.getMessage(), e);
		}
    	
    	return result;
    }
    
	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		T entity = null;

		try {
			session.beginTransaction();
			entity = (T) session.get(type, id);
			session.getTransaction().commit();
		}
		catch(Exception e) {
			session.getTransaction().rollback();
			log.warn(e.getMessage(), e);
		}
		
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List<T> list = null;
		
		try {
			session.beginTransaction();
			list = (List<T>) session.createCriteria(type).list();
			session.getTransaction().commit();
		}
		catch(Exception e) {
			session.getTransaction().rollback();
			log.warn(e.getMessage(), e);
		}
		
		return list;
	}
	
	public boolean save(T entity) {
		boolean result = false;
		
		try {
			session.beginTransaction();
			session.save(entity);
			result = true;
			session.getTransaction().commit();
		}
		catch(Exception e) {
			session.getTransaction().rollback();
			log.warn(e.getMessage(), e);
		}
		
		return result;
	}
	
	public boolean update(T entity) {
		boolean result = false;
		
		try {
			session.beginTransaction();
			session.saveOrUpdate(entity);
			result = true;
			session.getTransaction().commit();
		}
		catch(Exception e) {
			session.getTransaction().rollback();
			log.warn(e.getMessage(), e);
		}
		
		return result;
	}
}
