package edu.cmu.photogenome.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {
	
	public void delete(T entity);
	
	public T findById(ID id);
	
	public List<T> findAll();
	
	public void save(T entity);
	
	public void update(T entity);
	
}
