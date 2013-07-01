package edu.cmu.photogenome.business;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cmu.photogenome.dao.PhotoCategoryDao;
import edu.cmu.photogenome.dao.PhotoCategoryDaoImpl;
import edu.cmu.photogenome.dao.PhotoCommentDao;
import edu.cmu.photogenome.dao.PhotoCommentDaoImpl;
import edu.cmu.photogenome.dao.PhotoDao;
import edu.cmu.photogenome.dao.PhotoDaoImpl;
import edu.cmu.photogenome.dao.RegionCategoryDao;
import edu.cmu.photogenome.dao.RegionCategoryDaoImpl;
import edu.cmu.photogenome.dao.RegionCommentDao;
import edu.cmu.photogenome.dao.RegionCommentDaoImpl;
import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.domain.PhotoCategory;
import edu.cmu.photogenome.domain.RegionCategory;

/**
 * Class for searching for photos which are similar to:
 * - another photo based on its categories and comments
 * - a set of categories and/or comments
 * - a set of keywords
 */
public class Search {
	
	final Logger log = LoggerFactory.getLogger(Search.class);
	
	private PhotoDao photoDao;
	private PhotoCategoryDao photoCategoryDao;
	private PhotoCommentDao photoCommentDao;
	private RegionCategoryDao regionCategoryDao;
	private RegionCommentDao regionCommentDao;
	
	public Search() {
		photoDao = new PhotoDaoImpl();
		photoCategoryDao = new PhotoCategoryDaoImpl();
		photoCommentDao = new PhotoCommentDaoImpl();
		regionCategoryDao = new RegionCategoryDaoImpl();
		regionCommentDao = new RegionCommentDaoImpl();
	}
	
	public Search(Session session) {
		this();
		setSession(session);
	}
	
	/**
	 * Set the Hibernate session to use when calling DAOs
	 * 
	 * @param session
	 */
	public void setSession(Session session) {
		photoDao.setSession(session);
		photoCategoryDao.setSession(session);
		photoCommentDao.setSession(session);
		regionCategoryDao.setSession(session);
		regionCommentDao.setSession(session);
	}
	
	/**
	 * Return a list of photos whose categories or comments are similar to the given photo
	 * 
	 * @param photoId the photo to match against
	 * @return a list of matching photo entities
	 */
	public List<Photo> getAssociatedPhotos(int photoId) {
		
	}
	
	/**
	 * Return a list of photos whose categories or comments are similar to the given categories and comments
	 * 
	 * @param photoCategoryIdList	list of photo categories to be matched against
	 * @param photoCommentIdList	list of photo comments to be matched against
	 * @param regionCategoryIdList	list of region categories to be matched against
	 * @param regionCommentIdList	list of region comments to be matched against
	 * @return a list of matching photo entities
	 */
	public List<Photo> getFilteredAssociatedPhotos(List<Integer> photoCategoryIdList, List<Integer> regionCategoryIdList) {
		List<String> keywords = new ArrayList<String>();
		
		// retrieve the categories
		List<PhotoCategory> photoCategoryList = photoCategoryDao.findByIds(photoCategoryIdList);
		List<RegionCategory> regionCategoryList = regionCategoryDao.findByIds(regionCategoryIdList);
		
		// add the category names and text to the list of keywords
		for(PhotoCategory p : photoCategoryList)
			keywords.add(mergeCategoryData(p.getPhotoCategoryName(), p.getPhotoCategoryText()));
		for(RegionCategory r : regionCategoryList)
			keywords.add(mergeCategoryData(r.getCategoryName(), r.getRegionCategoryText()));
		
		// search for photos using the category keyword list
		return getPhotosByKeyword(keywords);
	}
	
	/**
	 * Return a list of photos whose categories or comments match ANY of the keywords
	 * 
	 * @param keywords the keywords to match against photos
	 * @return a list of matching photo entities
	 */
	public List<Photo> getPhotosByKeyword(List<String> keywords) {
		
	}
	
	/**
	 * Remove all whitespace and concatenate the name and text of a category
	 * 
	 * @param name	category name
	 * @param text	category text
	 * @return	string concatenation of category name and text
	 */
	private String mergeCategoryData(String name, String text) {
		// remove all whitespace
		name = name.replaceAll("\\s", "");
		text = text.replaceAll("\\s", "");
		
		return name + text;
	}
}
