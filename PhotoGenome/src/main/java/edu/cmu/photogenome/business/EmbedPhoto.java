package edu.cmu.photogenome.business;

import java.util.AbstractMap.SimpleEntry;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.hibernate.Session;
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
	 * Default constructor for initializing DAO's
	 */
	public EmbedPhoto() {
		photoCategoryDao = new PhotoCategoryDaoImpl();
		regionCategoryDao = new RegionCategoryDaoImpl();
		photoCommentDao = new PhotoCommentDaoImpl();	
	}
	
	/**
	 * Constructor that also sets the Hibernate session to be used
	 * 
	 * @param session	Hibernate session to use when calling DAOs
	 */
	public EmbedPhoto(Session session) {
		this();
		setSession(session);
	}
	
	/**
	 * Set the Hibernate session to use when calling DAOs
	 * 
	 * @param session
	 */
	public void setSession(Session session) {
		photoCategoryDao.setSession(session);
		regionCategoryDao.setSession(session);
		photoCommentDao.setSession(session);
	}
	
	/**
	 * Add comments
	 * 
	 * @param photoId
	 * @param userId
	 * @param comment
	 * @return newly created photoComment, otherwise null
	 */
	public PhotoComment addPhotoComment(int photoId, int userId, String comment){
		PhotoComment photoComment = new PhotoComment();

		photoComment.setPhotoId(photoId);
		photoComment.setUserId(userId);
		photoComment.setPhotoCommentText(comment);
		photoComment.setPhotoCommentTimestamp(new Date());

		if(photoCommentDao.save(photoComment)) {
			log.debug("Photo comments saved for photo ", photoComment.getPhotoId());	
			return photoComment;
		}
		else {
			log.error("Failed to save photo comments for photo ", photoComment.getPhotoId());
			return null;
		}
	}

	/**
	 * Add a new photo category
	 * 
	 * @param photoId
	 * @param userId
	 * @param categoryDetails
	 * @param photoCategoryText
	 * @return newly created category, otherwise null
	 */

	public PhotoCategory addPhotoCategory(int photoId, int userId, List<SimpleEntry<String, String>> categoryDetails){
		PhotoCategory category = new PhotoCategory(photoId, userId, new Date());

		for (SimpleEntry<String, String> details : categoryDetails){

			category.setPhotoCategoryName(details.getKey());
			category.setPhotoCategoryText(details.getValue());
			category.setUserId(userId);
			category.setPhotoId(photoId);

			if(!photoCategoryDao.save(category)) {
				log.error("Failed to add photo category for photo ", category.getPhotoId());
				return null;
			}
		}
		log.debug("Photo category added for photo ", category.getPhotoId());
		return category;
	}

	/**
	 * Update photo comments 
	 * 
	 * @param photoComment
	 * @return	true if comment is updated, otherwise false
	 */

	public boolean editPhotoComment(PhotoComment photoComment){

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

	public boolean deletePhotoComment(int photoCommentId){
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

	public boolean editPhotoCategory(PhotoCategory photoCategory){

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

	public boolean editRegionCategory(RegionCategory regionCategory){

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

	public boolean deletePhotoCategory(int photoCategoryId){

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
