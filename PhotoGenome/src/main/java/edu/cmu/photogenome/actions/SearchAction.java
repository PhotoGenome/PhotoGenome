package edu.cmu.photogenome.actions;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import edu.cmu.photogenome.business.Search;

public class SearchAction extends ActionSupport {

	final Logger log = LoggerFactory.getLogger(SearchAction.class);
	
	final String jsonKey = getText("json.key");
	
	private Search search = new Search();
	
	private String photoId;
	private String keywordList; // list of search keywords
	private String photoCategoryIdList; // list of selected photo category ids
	private String photoCommentIdList; // list of selected photo comment ids
	private String regionCategoryIdList; // list of selected region category ids
	private String regionCommentIdList; // list of region comment ids
	
	/** Variables to store/pass JSON data **/
	private Map<String, Object> jsonGetAssociatedPhotos = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetFilteredAssociatedPhotos = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetPhotosByKeywords = new LinkedHashMap<String, Object>();
	
	public String getAssociatedPhotos() {
		search.getAssociatedPhotos(photoId);
	}
	
	public String getFilteredAssociatedPhotos() {

	}
	
	public String getPhotosByKeywords() {
		
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
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
