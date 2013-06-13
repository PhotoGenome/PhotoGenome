package edu.cmu.photogenome.actions;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import edu.cmu.photogenome.business.EmbedPhoto;
import edu.cmu.photogenome.dao.PhotoCategoryDao;
import edu.cmu.photogenome.dao.PhotoCategoryDaoImpl;
import edu.cmu.photogenome.dao.PhotoCommentDao;
import edu.cmu.photogenome.dao.PhotoCommentDaoImpl;
import edu.cmu.photogenome.dao.PhotoDao;
import edu.cmu.photogenome.dao.PhotoDaoImpl;
import edu.cmu.photogenome.dao.RegionCategoryDao;
import edu.cmu.photogenome.dao.RegionCategoryDaoImpl;
import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.domain.PhotoCategory;
import edu.cmu.photogenome.domain.PhotoComment;
import edu.cmu.photogenome.domain.RegionCategory;

public class EmbedPhotoAction extends ActionSupport {

	final Logger log = LoggerFactory.getLogger(EmbedPhotoAction.class);
	
	final String jsonKey = getText("json.key");
	
	private Integer photoCategoryId;
	private int photoId;
	private int userId;
	private String photoCategoryName;
	private String photoCategoryText;

	private Integer regionCategoryId;
	private String categoryName;
	private String regionCategoryText;

	private int photoCommentId;
	private String photoCommentText;
	
	
	public Integer getPhotoCategoryId() {
		return photoCategoryId;
	}

	public void setPhotoCategoryId(Integer photoCategoryId) {
		this.photoCategoryId = photoCategoryId;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getPhotoCategoryName() {
		return photoCategoryName;
	}

	public void setPhotoCategoryName(String photoCategoryName) {
		this.photoCategoryName = photoCategoryName;
	}

	public String getPhotoCategoryText() {
		return photoCategoryText;
	}

	public void setPhotoCategoryText(String photoCategoryText) {
		this.photoCategoryText = photoCategoryText;
	}

	public Integer getRegionCategoryId() {
		return regionCategoryId;
	}

	public void setRegionCategoryId(Integer regionCategoryId) {
		this.regionCategoryId = regionCategoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getRegionCategoryText() {
		return regionCategoryText;
	}

	public void setRegionCategoryText(String regionCategoryText) {
		this.regionCategoryText = regionCategoryText;
	}

	public int getPhotoCommentId() {
		return photoCommentId;
	}

	public void setPhotoCommentId(int photoCommentId) {
		this.photoCommentId = photoCommentId;
	}

	public String getPhotoCommentText() {
		return photoCommentText;
	}

	public void setPhotoCommentText(String photoCommentText) {
		this.photoCommentText = photoCommentText;
	}

	EmbedPhoto embedPhoto = new EmbedPhoto();
	PhotoDao photoDao = new PhotoDaoImpl();
	RegionCategoryDao regionCategoryDao = new RegionCategoryDaoImpl();
	PhotoCategoryDao photoCategoryDao = new PhotoCategoryDaoImpl();
	PhotoCommentDao photoCommentDao = new PhotoCommentDaoImpl();

	/** Variables to store/pass JSON data **/
	private Map<String, Object> jsonAddPhotoComments = new LinkedHashMap<String, Object>();
	public Map<String, Object> getJsonAddPhotoComments() {
		return jsonAddPhotoComments;
	}

	public void setJsonAddPhotoComments(Map<String, Object> jsonAddPhotoComments) {
		this.jsonAddPhotoComments = jsonAddPhotoComments;
	}

	public Map<String, Object> getJsonAddPhotoCategories() {
		return jsonAddPhotoCategories;
	}

	public void setJsonAddPhotoCategories(Map<String, Object> jsonAddPhotoCategories) {
		this.jsonAddPhotoCategories = jsonAddPhotoCategories;
	}


	private Map<String, Object> jsonAddPhotoCategories = new LinkedHashMap<String, Object>();

	/**
	 * Add comments to a photo
	 * 
	 * @return
	 */

	public String addPhotoComments(){

		Photo photo = null;
		PhotoComment photoComment = null;

		try{
			photo = photoDao.findById(photoId);

			if(photo != null) {
				if((photoComment = embedPhoto.addPhotoComments(photoId, userId, photoCommentText)) == null)
					return ERROR;
			}else
				return ERROR;

			jsonAddPhotoComments.put(jsonKey, photoComment);
			return SUCCESS;

		}catch(Exception ex) {
			return ERROR;
		}
	}

	/**
	 * Add category to a photo
	 * 
	 * @return true if category is added, otherwise false
	 */

	public String addPhotoCategories(){

		Photo photo = null;
		PhotoCategory photoCategory = null;
		List<SimpleEntry<String, String>> categoryList = null;
		
		try{
			categoryList = new ArrayList<SimpleEntry<String, String>>();
			photo = photoDao.findById(photoId);
			
		if(photo != null) {
			categoryList.add(new SimpleEntry<String, String>(photoCategoryName, photoCategoryText));
					
			if((photoCategory = embedPhoto.addPhotoCategories(photoId, userId, categoryList))==null)
				return ERROR;
		}else{

			return ERROR;
		}
		
		jsonAddPhotoCategories.put(jsonKey, photoCategory);		
		return SUCCESS;
		
		}catch(Exception ex)
		{
			return ERROR;
		}
	}

	/**
	 * Update photo comments
	 * 
	 * @return true if comment is updated, otherwise false
	 */

	public String editPhotoComments(){

		PhotoComment photoComment = null;
		if((photoComment = photoCommentDao.findById(photoCommentId)) == null)
			return "invalid_photo_comment";
		else {
			photoComment.setPhotoCommentTimestamp(new Date());
			photoComment.setPhotoCommentText(photoCommentText);
			if (embedPhoto.editPhotoComments(photoComment))
				return SUCCESS;
			else 
				return ERROR;
		}
	}

	/**
	 * Update photo category
	 * 
	 * @return true if category is updated, otherwise false
	 */

	public String editPhotoCategories(){

		PhotoCategory photoCategory = photoCategoryDao.findById(photoId);

		if(photoCategory != null) {
			photoCategory.setPhotoCategoryName(photoCategoryName);
			photoCategory.setPhotoCategoryText(photoCategoryText);
			if(!embedPhoto.editPhotoCategories(photoCategory))
				return ERROR;
		}else
			return "invalid_photo_category";

		return SUCCESS;

	}

	/**
	 * Update region category
	 * 
	 * @return true if region category is updated, otherwise false
	 */

	public String editRegionCategories(){

		RegionCategory regionCategory = null;
		if((regionCategory = regionCategoryDao.findById(regionCategoryId)) == null)
			return "invalid_region_category";
		else {
			regionCategory.setCategoryName(categoryName);
			regionCategory.setRegionCategoryText(regionCategoryText);
			if (embedPhoto.editRegionCategories(regionCategory))
				return SUCCESS;
			else 
				return ERROR;
		}
	}

	/**
	 * Delete a photo comment
	 * 
	 * @return true if comment is deleted, otherwise false
	 */

	public String deletePhotoComments(){
		if(embedPhoto.deletePhotoComments(photoCommentId))
			return SUCCESS;
		else
			return ERROR;
	}


	/**
	 * Delete a photo category
	 * 
	 * @return true if photo category is deleted, otherwise false
	 */

	public String deletePhotoCategories(){

		if(embedPhoto.deletePhotoCategories(photoCategoryId))
			return SUCCESS;
		else
			return ERROR;
	}

}
