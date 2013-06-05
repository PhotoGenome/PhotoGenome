package edu.cmu.photogenome.actions.embedinformation;

import java.util.Date;
import java.util.List;

import edu.cmu.photogenome.dao.PhotoCategoryDao;
import edu.cmu.photogenome.dao.PhotoCategoryDaoImpl;
import edu.cmu.photogenome.dao.RegionCategoryDao;
import edu.cmu.photogenome.dao.RegionCategoryDaoImpl;
import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.domain.PhotoCategory;
import edu.cmu.photogenome.domain.PhotoComment;
import edu.cmu.photogenome.domain.PhotoRegion;
import edu.cmu.photogenome.domain.RegionCategory;

public class EmbedPhoto {

	private PhotoCategoryDao photoCategoryDao = new PhotoCategoryDaoImpl();
	private RegionCategoryDao regionCategoryDao = new RegionCategoryDaoImpl();
	
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean addPhotoComments(Photo currentPhoto, List<PhotoComment> listPhotoComments){
		return false;
	}
	
	/* takes a user object and verifies the login details it into the system
	 * return true if correct*/
	
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
	
	/* takes a user object and verifies the login details it into the system
	 * return true if correct*/
	
	public boolean addPhotoCategories(Photo currentPhoto, List<PhotoCategory> listPhotoCategories){
		return false;
	}
	
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean editPhotoComments(Photo currentPhoto, PhotoComment currentPhotoComments){
		return false;
	}

	/* takes a user object and verifies its login details it into the system
		 * return true if correct*/
		
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
		
		
		/* takes a user object and verifies its login details it into the system
		 * return true if correct*/
		
		public boolean editPhotoCategories(Photo currentPhoto,PhotoCategory currentPhotoCategories){
			return false;
		}

		/* takes a user object and verifies its login details it into the system
			 * return true if correct*/
			
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
		
			
			/* takes a user object and verifies its login details it into the system
			 * return true if correct*/
			
			public boolean editRegionCategories(PhotoRegion currentPhotoRegion, RegionCategory currentRegionCategories){
				return false;
			}
			
			/* takes a user object and verifies its login details it into the system
			 * return true if correct*/
			
			public boolean deletePhotoComments(Photo currentPhoto, List<PhotoComment> listPhotoComments){
				return false;
			}

			
			/* takes a user object and verifies its login details it into the system
			 * return true if correct*/
			
			public boolean deletePhotoCategories(int photoId, int photoCategoryId){
				
				PhotoCategory category = new PhotoCategory();
				
				category.setPhotoId(photoId);
				category.setPhotoCategoryId(photoCategoryId);
				
				if(!photoCategoryDao.delete(category))
					return false;
				
				return true;
			}
			
			/* takes a user object and verifies its login details it into the system
			 * return true if correct*/
			
			public boolean deletePhotoCategories(Photo currentPhoto, List<PhotoCategory> listPhotoCategories){
				return false;
			}
			
			
		
		
	
	
}
