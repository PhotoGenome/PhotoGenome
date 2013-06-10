package edu.cmu.photogenome.actions;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
	
	private Integer photoCategoryId;
	private int photoId;
	private int userId;
	private String photoCategoryName;
	private String photoCategoryText;
	private Date photoCategoryTimestamp;
	private Boolean photoCategoryIsdeleted;

	private Integer regionCategoryId;
	private int regionId;
	private String categoryName;
	private String regionCategoryText;
	private Date regionCategoryTimestamp;
	private Boolean regionCategoryIsdeleted;

	private int photoCommentId;
	private String photoCommentText;
	private Date photoCommentTimestamp;
	private Boolean photoCommentIsdeleted;
	private String photoCommentOption1;
	private String photoCommentOption2;
	private String photoCommentOption3;
	private String photoCommentOption4;
	private String photoCommentOption5;

	// Additional variables
	private List<String> categoryDetails;
	
	EmbedPhoto embedPhoto = new EmbedPhoto();
	PhotoDao photoDao = new PhotoDaoImpl();
	RegionCategoryDao regionCategoryDao = new RegionCategoryDaoImpl();
	PhotoCategoryDao photoCategoryDao = new PhotoCategoryDaoImpl();
	PhotoCommentDao photoCommentDao = new PhotoCommentDaoImpl(); 

	/**
	 * Add comments to a photo
	 * 
	 * @return
	 */

	public String addPhotoComments(){

		Photo photoObj = photoDao.findById(photoId);

		if(photoObj != null) {
			if(!embedPhoto.addPhotoComments(photoId, userId, photoCommentText))
				return ERROR;
		}else
			return ERROR;

		return SUCCESS;
	}

	/**
	 * Add category to a photo
	 * 
	 * @return true if category is added, otherwise false
	 */

	public String addPhotoCategories(){

		Photo photoObj = photoDao.findById(photoId);

		if(photoObj != null) {
			if(!embedPhoto.addPhotoCategories(photoId, userId, categoryDetails, photoCategoryText))
				return ERROR;
		}else
			return ERROR;

		return SUCCESS;
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
