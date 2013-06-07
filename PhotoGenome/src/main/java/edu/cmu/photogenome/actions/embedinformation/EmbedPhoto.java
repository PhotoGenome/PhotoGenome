package edu.cmu.photogenome.actions.embedinformation;

import java.util.Date;
import java.util.List;

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

		if(photoCommentDao.save(photoComment))
			return true;
		else
			return false;
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

			if(!photoCategoryDao.save(category))
				return false;
		}

		return true;
	}

	/**
	 * Update photo comments 
	 * 
	 * @param photoComment
	 * @return	true if comment is updated, otherwise false
	 */

	public boolean editPhotoComments(PhotoComment photoComment){
		
		if(photoCommentDao.update(photoComment))
			return true;
		else
			return false;
		
	}


	/**
	 * Delete a photo comment
	 * 
	 * @param photoCommentId
	 * @return true if comment is deleted, otherwise false
	 */

	public boolean deletePhotoComments(int photoCommentId){
		PhotoComment photoComment;

		if((photoComment = photoCommentDao.findById(photoCommentId)) != null)
			return photoCommentDao.delete(photoComment);
		
		return true;
	}


	/**
	 * Update photo categories
	 * 
	 * @param photoCategory
	 * @return	true if category is updated, otherwise false
	 */

	public boolean editPhotoCategories(PhotoCategory photoCategory){

		if(!photoCategoryDao.update(photoCategory))
			return false;
		return true;

	}


	/**
	 * Update region categories
	 * 
	 * @param regionCategory
	 * @return	true if category is updated, otherwise false
	 */

	public boolean editRegionCategories(RegionCategory regionCategory){

		if(!regionCategoryDao.update(regionCategory))
			return false;

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
			return photoCategoryDao.delete(category);
		}

		return true;
	}

}
