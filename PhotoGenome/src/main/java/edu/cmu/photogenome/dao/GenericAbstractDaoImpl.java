package edu.cmu.photogenome.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.googlecode.s2hibernate.struts2.plugin.annotations.SessionTarget;
import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;

public abstract class GenericAbstractDaoImpl <T, ID extends Serializable> implements GenericDao<T, ID> {

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
	
    public void delete(T entity) {
    	try {
			session.delete(entity);
		}
		catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
    }
    
	public T findById(ID id) {
		T entity = null;
		
		try {
			entity = (T) session.get(type, id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	
	public List<T> findAll() {
		List<T> list = null;
		try {
			list = (List<T>) session.createCriteria(type).list();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void save(T entity) {
		try {
			session.save(entity);
		}
		catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}
	
	public void update(T entity) {
		try {
			session.saveOrUpdate(entity);
		}
		catch(Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}
	}
}
