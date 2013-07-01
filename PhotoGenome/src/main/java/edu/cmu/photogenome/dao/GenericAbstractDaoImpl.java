package edu.cmu.photogenome.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.StaleStateException;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GenericAbstractDaoImpl <T, ID extends Serializable> implements GenericDao<T, ID> {

	final Logger log = LoggerFactory.getLogger(GenericAbstractDaoImpl.class);
	
	protected Session session;
	
    protected Class<T> type;

    @SuppressWarnings("unchecked")
	public GenericAbstractDaoImpl() {
        type = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    /**
     * Delete a persistent entity
     */
    public boolean delete(T entity) {
    	boolean result = false;
    	
    	try {
			session.delete(entity);
			result = true;
		}
    	catch(StaleStateException sse) { // the object trying to be deleted does not exist
    		log.warn(sse.getMessage(), sse);
    		result = true; // object does not exist, so return true
    	}
		catch(Exception e) {
			log.warn(e.getMessage(), e);
		}
    	
    	return result;
    }
    
    /**
     * Find an entity using a unique ID
     */
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
	
	/**
	 * Find a list of entities matching the list of unique IDs
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByIds(List<ID> ids) {
		List<T> list = null;
		
		ClassMetadata meta = session.getSessionFactory().getClassMetadata(type); // get the primary key for the current entity
		try {
			list = (List<T>) session.createCriteria(type).add(Restrictions.in(meta.getIdentifierPropertyName(), ids)).list();
		}
		catch(Exception e) {
			log.warn(e.getMessage(), e);
		}
		
		return list;
	}
	
	/**
	 * Find all entities
	 */
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
	
	/**
	 * Find all entities where the given property matches the given value
	 * 
	 * @param propertyName
	 * @param value
	 * @return List of matching entities, empty list if none match
	 */
	@SuppressWarnings("unchecked")
	public List<T> findAllByCriteria(String propertyName, Object value) {
		List<T> list = null;
		
		try {
			list = (List<T>) session.createCriteria(type).add(Restrictions.eq(propertyName, value)).list();
		}
		catch(Exception e) {
			log.warn(e.getMessage(), e);
		}
		
		return list;
	}
	
	/**
	 * Save the persistent entity
	 */
	public boolean save(T entity) {
		boolean result = false;
		
		try {
			session.save(entity);
			result = true;
		}
		catch(Exception e) {
			log.warn(e.getMessage(), e);
		}
		
		return result;
	}
	
	/**
	 * Update the persistent entity
	 */
	public boolean update(T entity) {
		boolean result = false;
		
		try {
			session.update(entity);
			result = true;
		}
		catch(Exception e) {
			log.warn(e.getMessage(), e);
		}
		
		return result;
	}
	
    public Session getSession() {
    	return session;
    }
    
    public void setSession(Session session) {
    	this.session = session;
    }
}
