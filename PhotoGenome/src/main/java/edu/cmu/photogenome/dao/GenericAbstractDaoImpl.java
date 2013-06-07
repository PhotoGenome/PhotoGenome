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

public abstract class GenericAbstractDaoImpl <T, ID extends Serializable> implements GenericDao<T, ID> {

	final Logger log = LoggerFactory.getLogger(GenericAbstractDaoImpl.class);
	
	@SessionTarget
	Session session;
	
	@TransactionTarget
	Transaction transaction;
	
    protected Class<T> type;

    @SuppressWarnings("unchecked")
	public GenericAbstractDaoImpl() {
        type = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
	
    public boolean delete(T entity) {
    	boolean result = false;
    	
    	try {
			session.delete(entity);
			result = true;
		}
		catch(Exception e) {
			transaction.rollback();
			log.warn(e.getMessage(), e);
		}
    	
    	return result;
    }
    
	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		T entity = null;
		
		try {
			entity = (T) session.get(type, id);
		}
		catch(Exception e) {
			log.warn(e.getMessage(), e);
		}
		
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List<T> list = null;
		
		try {
			list = (List<T>) session.createCriteria(type).list();
		}
		catch(Exception e) {
			log.warn(e.getMessage(), e);
		}
		
		return list;
	}
	
	public boolean save(T entity) {
		boolean result = false;
		
		try {
			session.save(entity);
			result = true;
		}
		catch(Exception e) {
			transaction.rollback();
			log.warn(e.getMessage(), e);
		}
		
		return result;
	}
	
	public boolean update(T entity) {
		boolean result = false;
		
		try {
			session.saveOrUpdate(entity);
			result = true;
		}
		catch(Exception e) {
			transaction.rollback();
			log.warn(e.getMessage(), e);
		}
		
		return result;
	}
}
