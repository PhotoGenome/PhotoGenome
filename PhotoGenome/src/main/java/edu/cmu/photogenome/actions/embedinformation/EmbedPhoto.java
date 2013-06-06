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


	public boolean addPhotoComments(int photoId, int userId){
		PhotoComment photoComment = new PhotoComment();

		photoComment.setPhotoId(photoId);
		photoComment.setUserId(userId);

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
	 * @param currentPhoto
	 * @param currentPhotoComments
	 * @return	true if comment is updated, otherwise false
	 */

	public boolean editPhotoComments(int photoId, int userId){
		PhotoComment photoComment = new PhotoComment();

		photoComment.setPhotoId(photoId);
		photoComment.setUserId(userId);

		if(photoCommentDao.update(photoComment))
			return true;
		else
			return false;
	}


	/**
	 * Delete a photo comment
	 * 
	 * @param photoId
	 * @param userId
	 * @return	true if comment is deleted, otherwise false
	 */

	public boolean deletePhotoComments(int photoId, int userId){
		PhotoComment photoComment = new PhotoComment();

		photoComment.setPhotoId(photoId);
		photoComment.setUserId(userId);

		if(photoCommentDao.delete(photoComment))
			return true;
		else
			return false;
	}


	/**
	 * Update photo categories
	 * 
	 * @param photoId
	 * @param userId
	 * @param photoCategoryName
	 * @param photoCategoryText
	 * @return	true if category is updated, otherwise false
	 */

	public boolean editPhotoCategories(int photoId, int userId, String photoCategoryName,
			String photoCategoryText){

		PhotoCategory category = new PhotoCategory();
		category.setPhotoId(photoId);
		category.setUserId(userId);
		category.setPhotoCategoryName(photoCategoryName);
		category.setPhotoCategoryText(photoCategoryText);

		if(!photoCategoryDao.update(category))
			return false;
		return true;

	}


	/**
	 * Update region categories
	 * 
	 * @param regionId
	 * @param photoId
	 * @param userId
	 * @param categoryName
	 * @param regionCategoryText
	 * @param regionCategoryIsdeleted
	 * @return	true if category is updated, otherwise false
	 */

	public boolean editRegionCategories(int regionId, int photoId, int userId,
			String categoryName, String regionCategoryText,
			Boolean regionCategoryIsdeleted){

		RegionCategory category = new RegionCategory();
		category.setRegionId(regionId);
		category.setPhotoId(photoId);
		category.setCategoryName(categoryName);
		category.setRegionCategoryText(regionCategoryText);

		if(!regionCategoryDao.update(category))
			return false;

		return true;
	}


	/**
	 * Delete photo categories
	 * 
	 * @param photoId
	 * @param photoCategoryId
	 * @return	true if category is deleted, otherwise false
	 */

	public boolean deletePhotoCategories(int photoId, int photoCategoryId){

		PhotoCategory category = new PhotoCategory();

		category.setPhotoId(photoId);
		category.setPhotoCategoryId(photoCategoryId);

		if(!photoCategoryDao.delete(category))
			return false;

		return true;
	}

}
