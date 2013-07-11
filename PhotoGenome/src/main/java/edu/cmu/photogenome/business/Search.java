package edu.cmu.photogenome.business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.hibernate.Query;
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
import edu.cmu.photogenome.util.ConfigUtil;

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
	
	private SearchDataRetrieval searchDataRetrieval;
	
	public Search() {
		photoDao = new PhotoDaoImpl();
		photoCategoryDao = new PhotoCategoryDaoImpl();
		photoCommentDao = new PhotoCommentDaoImpl();
		regionCategoryDao = new RegionCategoryDaoImpl();
		regionCommentDao = new RegionCommentDaoImpl();
		
		searchDataRetrieval = new SearchDataRetrieval();
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
		
		searchDataRetrieval.setSession(session);
	}
	
	/**
	 * Return a list of photos whose categories or comments are similar to the given photo
	 * 
	 * @param photoId the photo to match against
	 * @return a list of matching photo entities
	 */
	public List<Photo> getAssociatedPhotos(int photoId) {
		// get all photo and region categories for the photo
		List<PhotoCategory> photoCategories = photoCategoryDao.findAllByCriteria("photoId", photoId);
		List<RegionCategory> regionCategories = regionCategoryDao.findAllByCriteria("photoId", photoId);
		
		List<Integer> photoCategoryIds = new ArrayList<Integer>();
		List<Integer> regionCategoryIds = new ArrayList<Integer>();
		
		// get lists of category ids
		for(PhotoCategory p : photoCategories)
			photoCategoryIds.add(p.getPhotoCategoryId());
		for(RegionCategory r : regionCategories)
			regionCategoryIds.add(r.getRegionCategoryId());
		
		return getFilteredAssociatedPhotosByCategoryId(photoId, photoCategoryIds, regionCategoryIds);
	}
	
	/**
	 * Return a list of photos whose categories or comments are similar to the given categories and comments 
	 * for the given photo
	 * 
	 * @param photoId				source photo to match against
	 * @param photoCategoryIdList	list of photo categories to be matched against
	 * @param photoCommentIdList	list of photo comments to be matched against
	 * @param regionCategoryIdList	list of region categories to be matched against
	 * @param regionCommentIdList	list of region comments to be matched against
	 * @return a list of matching photo entities
	 */
	public List<Photo> getFilteredAssociatedPhotosByCategoryId(int photoId, List<Integer> photoCategoryIdList, List<Integer> regionCategoryIdList) {
		List<String> photoKeywords = new ArrayList<String>();
		List<String> regionKeywords = new ArrayList<String>();
		
		// retrieve the categories
		List<PhotoCategory> photoCategoryList = photoCategoryDao.findByIds(photoCategoryIdList);
		List<RegionCategory> regionCategoryList = regionCategoryDao.findByIds(regionCategoryIdList);
		
		// add the category names and text to the list of keywords
		for(PhotoCategory p : photoCategoryList)
			photoKeywords.add(mergeCategoryData(p.getPhotoCategoryName(), p.getPhotoCategoryText()));
		for(RegionCategory r : regionCategoryList)
			regionKeywords.add(mergeCategoryData(r.getCategoryName(), r.getRegionCategoryText()));
		
		// search for photos using the category keyword list
		return getFilteredAssociatedPhotosByCategoryValue(photoId, photoKeywords, regionKeywords);
	}
	
	/**
	 * Return a list of photos whose categories or comments are similar to the given categories and comments for the
	 * given photo
	 * 
	 * @param photoId				source photo to match against
	 * @param photoCategoryList		list of photo category data
	 * @param regionCategoryList	list of region category data
	 * @return a list of matching photo entities
	 */
	public List<Photo> getFilteredAssociatedPhotosByCategoryValue(int photoId, List<String> photoCategoryList, List<String> regionCategoryList) {
		List<String> categories = new ArrayList<String>();
		
		//combine photo and region categories into single list
		categories.addAll(photoCategoryList);
		categories.addAll(regionCategoryList);
		
		Properties config = ConfigUtil.getApplicationProperties();
		if(config == null)
			return null;
		
		// get maximum number of matches
		int maxMatches = Integer.parseInt(config.getProperty("search.maxMatches"));
		
		// perform actual search for associated photos
		return searchDataRetrieval.searchFilteredAssociatedPhotos(photoId, categories, maxMatches);
	}
	
	/**
	 * Return a list of photos whose categories or comments match ANY of the keywords
	 * 
	 * @param keywords the keywords to match against photos
	 * @return a list of matching photo entities
	 */
	public List<Photo> getPhotosByKeyword(List<String> keywords) {
		Properties config = ConfigUtil.getApplicationProperties();
		if(config == null)
			return null;
		
		// get maximum number of matches
		int maxMatches = Integer.parseInt(config.getProperty("search.maxMatches"));
		
		return searchDataRetrieval.searchPhotosByKeyword(keywords, maxMatches);
	}
	
	/**
	 * Remove all whitespace and concatenate the name and text of a category
	 * 
	 * @param name	category name
	 * @param text	category text
	 * @return	string concatenation of category name and text
	 */
	private String mergeCategoryData(final String name, final String text) {
		// remove all whitespace
		String newName = name.replaceAll("\\s", "");
		String newText = text.replaceAll("\\s", "");
		
		return newName + newText;
	}
}
