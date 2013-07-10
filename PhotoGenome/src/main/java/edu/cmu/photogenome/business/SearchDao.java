package edu.cmu.photogenome.business;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.util.ConfigUtil;

public class SearchDao {
	
	final Logger log = LoggerFactory.getLogger(SearchDao.class);
	
	protected Session session;
	
	public SearchDao() {
		
	}
	
	public SearchDao(Session session) {
		this();
		setSession(session);
	}
	
	/**
	 * Search for the photos which match the list of categories
	 * 
	 * @param photoId		the source photo
	 * @param categories	the categories to match against
	 * @param maxMatches	the max number of matching photos to return
	 * @return a list of matching photos up to the specified maximum number
	 */
	public List<Photo> searchFilteredAssociatedPhotos(int photoId, List<String> categories, int maxMatches) {
		// load application properties
		Properties config = ConfigUtil.getApplicationProperties();
		if(config == null)
			return null;
		
		// use filtered photo associations query
		String queryString = config.getProperty("search.sql.query.filteredAssociations");
		
		Query query = session.createSQLQuery(queryString)
				.addEntity(Photo.class)
				.setParameter("photoId", photoId) // set photo id
				.setParameter("maxMatches", maxMatches) // set maximum number of matches
				.setParameter("categoryKeywords", categories); // set categories to match against
		
		// execute query
		List<Photo> result = query.list();
		return result;
	}
	
	public Session getSession() {
		return session;
	}
	
	public void setSession(Session session) {
		this.session = session;
	}
}
