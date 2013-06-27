package edu.cmu.photogenome.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import edu.cmu.photogenome.business.Search;
import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.util.HibernateUtil;

public class SearchAction extends ActionSupport {

	final Logger log = LoggerFactory.getLogger(SearchAction.class);
	
	final String jsonKey = getText("json.key");
	
	private Search search = new Search();
	
	private Integer photoId;
	private String keywordList; // list of search keywords
	private String photoCategoryIdList; // list of selected photo category ids
	private String photoCommentIdList; // list of selected photo comment ids
	private String regionCategoryIdList; // list of selected region category ids
	private String regionCommentIdList; // list of region comment ids
	
	/** Variables to store/pass JSON data **/
	private Map<String, Object> jsonGetAssociatedPhotos = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetFilteredAssociatedPhotos = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetPhotosByKeywords = new LinkedHashMap<String, Object>();
	
	/**
	 * Get a set of photos based on all of the photo categories, photo comments, region categories, 
	 * and region comments on a photo
	 * 
	 * @return
	 */
	public String getAssociatedPhotos() {
		List<Photo> list = null;
		
		// start transaction
		Session session = HibernateUtil.getSessionFactory().openSession();
		search.setSession(session);
		HibernateUtil.beginTransaction(session);
		
		if((list = search.getAssociatedPhotos(photoId)) != null) {
			jsonGetAssociatedPhotos.put(jsonKey, list);
			HibernateUtil.commitTransaction(session);
			return SUCCESS;
		}
		else {
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}
	}
	
	/**
	 * Get a set of photos based on filtered photo categories, photo comments, region categories, 
	 * and region comments on a photo
	 * 
	 * @return
	 */
	public String getFilteredAssociatedPhotos() {
		List<Photo> list = null;
		List<Integer> photoCategoryIds;
		List<Integer> photoCommentIds;
		List<Integer> regionCategoryIds;
		List<Integer> regionCommentIds;
		
		try {
			// parse request parameter strings into lists of integers
			photoCategoryIds = parseStringToInteger(photoCategoryIdList, ",");
			photoCommentIds = parseStringToInteger(photoCommentIdList, ",");
			regionCategoryIds = parseStringToInteger(regionCategoryIdList, ",");
			regionCommentIds = parseStringToInteger(regionCommentIdList, ",");
		}
		catch(NumberFormatException nfe) {
			log.error(nfe.getMessage(), nfe);
			return null;
		}
		
		// start transaction
		Session session = HibernateUtil.getSessionFactory().openSession();
		search.setSession(session);
		HibernateUtil.beginTransaction(session);
		
		if((list = search.getFilteredAssociatedPhotos(photoCategoryIds, photoCommentIds, 
				regionCategoryIds, regionCommentIds)) != null) {
			jsonGetFilteredAssociatedPhotos.put(jsonKey, list);
			HibernateUtil.commitTransaction(session);
			return SUCCESS;
		}
		else {
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}
	}
	
	/**
	 * Get a set of photos based on a keyword search
	 * 
	 * @return
	 */
	public String getPhotosByKeywords() {
		List<Photo> list = null;
		List<String> keywords;
		
		// parse request parameter strings into list
		keywords = Arrays.asList(keywordList.split(" "));
		
		// start transaction
		Session session = HibernateUtil.getSessionFactory().openSession();
		search.setSession(session);
		HibernateUtil.beginTransaction(session);
		
		if((list = search.getPhotosByKeyword(keywords)) != null) {
			jsonGetPhotosByKeywords.put(jsonKey, list);
			HibernateUtil.commitTransaction(session);
			return SUCCESS;
		}
		else {
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}
	}

	/**
	 * Parse a string into a list of integers
	 * 
	 * @param string	string to parse
	 * @param delimiter	delimiter to use
	 * @return	list of integers
	 * @throws NumberFormatException
	 */
	private List<Integer> parseStringToInteger(String string, String delimiter) throws NumberFormatException {
		List<Integer> list = new ArrayList<Integer>();
		String[] strArray = string.split(delimiter);
		for(String s : strArray)
			list.add(Integer.parseInt(s));
		return list;
	}
	
	public Integer getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

	public String getKeywordList() {
		return keywordList;
	}

	public void setKeywordList(String keywordList) {
		this.keywordList = keywordList;
	}

	public String getPhotoCategoryIdList() {
		return photoCategoryIdList;
	}

	public void setPhotoCategoryIdList(String photoCategoryIdList) {
		this.photoCategoryIdList = photoCategoryIdList;
	}

	public String getPhotoCommentIdList() {
		return photoCommentIdList;
	}

	public void setPhotoCommentIdList(String photoCommentIdList) {
		this.photoCommentIdList = photoCommentIdList;
	}

	public String getRegionCategoryIdList() {
		return regionCategoryIdList;
	}

	public void setRegionCategoryIdList(String regionCategoryIdList) {
		this.regionCategoryIdList = regionCategoryIdList;
	}

	public String getRegionCommentIdList() {
		return regionCommentIdList;
	}

	public void setRegionCommentIdList(String regionCommentIdList) {
		this.regionCommentIdList = regionCommentIdList;
	}

	public Map<String, Object> getJsonGetAssociatedPhotos() {
		return jsonGetAssociatedPhotos;
	}

	public void setJsonGetAssociatedPhotos(
			Map<String, Object> jsonGetAssociatedPhotos) {
		this.jsonGetAssociatedPhotos = jsonGetAssociatedPhotos;
	}

	public Map<String, Object> getJsonGetFilteredAssociatedPhotos() {
		return jsonGetFilteredAssociatedPhotos;
	}

	public void setJsonGetFilteredAssociatedPhotos(
			Map<String, Object> jsonGetFilteredAssociatedPhotos) {
		this.jsonGetFilteredAssociatedPhotos = jsonGetFilteredAssociatedPhotos;
	}

	public Map<String, Object> getJsonGetPhotosByKeywords() {
		return jsonGetPhotosByKeywords;
	}

	public void setJsonGetPhotosByKeywords(
			Map<String, Object> jsonGetPhotosByKeywords) {
		this.jsonGetPhotosByKeywords = jsonGetPhotosByKeywords;
	}
	
}
