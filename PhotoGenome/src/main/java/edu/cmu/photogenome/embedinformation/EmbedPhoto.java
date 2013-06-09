package edu.cmu.photogenome.embedinformation;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cmu.photogenome.dao.PhotoCategoryDao;
import edu.cmu.photogenome.dao.PhotoCategoryDaoImpl;
import edu.cmu.photogenome.dao.PhotoCommentDao;
import edu.cmu.photogenome.dao.PhotoCommentDaoImpl;
import edu.cmu.photogenome.dao.RegionCategoryDao;
import edu.cmu.photogenome.dao.RegionCategoryDaoImpl;
import edu.cmu.photogenome.domain.PhotoCategory;
import edu.cmu.photogenome.domain.PhotoComment;
import edu.cmu.photogenome.domain.RegionCategory;

public class EmbedPhoto {

	final Logger log = LoggerFactory.getLogger(EmbedPhoto.class);

	private PhotoCategoryDao photoCategoryDao = new PhotoCategoryDaoImpl();
	private RegionCategoryDao regionCategoryDao = new RegionCategoryDaoImpl();
	private PhotoCommentDao photoCommentDao = new PhotoCommentDaoImpl();


	/**
	 * Add comments
	 * 
	 * @param photoId
	 * @param userId
	 * @param comment
	 * @return true if comment is added, otherwise false
	 */
	public boolean addPhotoComments(int photoId, int userId, String comment){
		PhotoComment photoComment = new PhotoComment();

		photoComment.setPhotoId(photoId);
		photoComment.setUserId(userId);
		photoComment.setPhotoCommentText(comment);

		if(photoCommentDao.save(photoComment)) {
			log.debug("Photo comments saved for photo ", photoComment.getPhotoId());	
			return true;
		}
		else {
			log.error("Failed to save photo comments for photo ", photoComment.getPhotoId());
			return false;
		}
	}

	/**
	 * Add a new photo category
	 * 
	 * @param photoId
	 * @param userId
	 * @param categoryDetails
	 * @param photoCategoryText
	 * @return	true if category is added, otherwise false
	 */

	public boolean addPhotoCategories(int photoId, int userId, List<String> categoryDetails,
			String photoCategoryText){
		PhotoCategory category = new PhotoCategory(photoId, userId, new Date());

		for (String details : categoryDetails){

			String[] categoryNameandText = details.split(":");
			category.setPhotoCategoryName(categoryNameandText[0]);
			category.setPhotoCategoryText(categoryNameandText[1]);

			if(!photoCategoryDao.save(category)) {
				log.error("Failed to add photo category for photo ", category.getPhotoId());
				return false;
			}
		}
		log.debug("Photo category added for photo ", category.getPhotoId());
		return true;
	}

	/**
	 * Update photo comments 
	 * 
	 * @param photoComment
	 * @return	true if comment is updated, otherwise false
	 */

	public boolean editPhotoComments(PhotoComment photoComment){

		if(photoCommentDao.update(photoComment)) {
			log.debug("Photo comments updated for photo ", photoComment.getPhotoId());	
			return true;
		}
		else {
			log.error("Failed to update photo comments for photo ", photoComment.getPhotoId());
			return false;
		}

	}


	/**
	 * Delete a photo comment
	 * 
	 * @param photoCommentId
	 * @return true if comment is deleted, otherwise false
	 */

	public boolean deletePhotoComments(int photoCommentId){
		PhotoComment photoComment;

		if((photoComment = photoCommentDao.findById(photoCommentId)) != null) {
			if (!photoCommentDao.delete(photoComment)) {
				log.error("Failed to delete photo comments for photo ", photoComment.getPhotoId());
				return false;
			}
			else {
				log.debug("Photo comments deletd for photo ", photoComment.getPhotoId());
				return true;
			}
		}
		log.debug("Photo does not exist, no comments deleted for ", photoCommentId);
		return true;
	}


	/**
	 * Update photo categories
	 * 
	 * @param photoCategory
	 * @return	true if category is updated, otherwise false
	 */

	public boolean editPhotoCategories(PhotoCategory photoCategory){

		if(!photoCategoryDao.update(photoCategory)) {
			log.error("Failed to update category for photo ", photoCategory.getPhotoId());
			return false; 
			}
		
		log.debug("Photo category updated for photo ", photoCategory.getPhotoId());
		return true;

	}


	/**
	 * Update region categories
	 * 
	 * @param regionCategory
	 * @return	true if category is updated, otherwise false
	 */

	public boolean editRegionCategories(RegionCategory regionCategory){

		if(!regionCategoryDao.update(regionCategory)) {
			log.error("Failed to update region category for photo ", regionCategory.getPhotoId());
			return false;
		}
		log.debug("Region category updated for photo with category id", regionCategory.getPhotoId());
		return true;
	}


	/**
	 * Delete photo categories
	 * 
	 * @param photoCategoryId
	 * @return	true if category is deleted, otherwise false
	 */

	public boolean deletePhotoCategories(int photoCategoryId){

		PhotoCategory category;
		if((category = photoCategoryDao.findById(photoCategoryId)) != null)
		{
			if (!photoCategoryDao.delete(category)) {
				log.error("Failed to delete category for photo with category Id ", photoCategoryId);
				return false;
			}
			else {
				log.debug("Photo category updated for photo with category id ", photoCategoryId);
				return true;
			}
		}

		log.debug("Photo does not exist, no category updated category id ", photoCategoryId);
		return true;
	}

}
