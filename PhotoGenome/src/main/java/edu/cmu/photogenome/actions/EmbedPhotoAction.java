package edu.cmu.photogenome.actions;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import edu.cmu.photogenome.actions.embedinformation.EmbedPhoto;
import edu.cmu.photogenome.dao.PhotoDao;
import edu.cmu.photogenome.dao.PhotoDaoImpl;
import edu.cmu.photogenome.dao.RegionCategoryDao;
import edu.cmu.photogenome.dao.RegionCategoryDaoImpl;
import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.domain.PhotoComment;
import edu.cmu.photogenome.domain.RegionCategory;

public class EmbedPhotoAction extends ActionSupport {


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

	// Additional variables
	private List<String> categoryDetails;
	
	EmbedPhoto embedPhoto = new EmbedPhoto();
	PhotoDao photoDao = new PhotoDaoImpl();
	RegionCategoryDao regionCategoryDao = new RegionCategoryDaoImpl();

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/

	public String addPhotoComments(){
		
		Photo photoObj = photoDao.findById(photoId);

		if(photoObj != null) {
			if(!embedPhoto.addPhotoCategories(photoId, userId, categoryDetails, photoCategoryText))
				return ERROR;
		}else
			return ERROR;

		return SUCCESS;
	}

	/* takes a user object and verifies the login details it into the system
	 * return true if correct*/

	public String addPhotoCategories(){

		Photo photoObj = photoDao.findById(photoId);

		if(photoObj != null) {
			if(!embedPhoto.addPhotoCategories(photoId, userId, categoryDetails, photoCategoryText))
				return ERROR;
		}else
			return ERROR;

		return SUCCESS;
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/

	public boolean editPhotoComments(Photo currentPhoto, PhotoComment currentPhotoComments){
		return false;
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/

	public String editPhotoCategories(){

		Photo photo = photoDao.findById(photoId);

		if(photo != null) {
			if(!embedPhoto.addPhotoCategories(photoId, userId, categoryDetails, photoCategoryText))
				return ERROR;
		}else
			return ERROR;

		return SUCCESS;

	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/

	public String editRegionCategories(){

		RegionCategory region = regionCategoryDao.findById(regionId);

		if(region != null) {
			if(!embedPhoto.editRegionCategories(regionId, photoId, userId, categoryName, regionCategoryText, regionCategoryIsdeleted))
				return ERROR;
		}else
			return ERROR;

		return SUCCESS;

	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/

	public boolean deletePhotoComments(Photo currentPhoto, List<PhotoComment> listPhotoComments){
		return false;
	}


	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/

	public String deletePhotoCategories(int photoId, int photoCategoryId){

		Photo photo = photoDao.findById(photoId);

		if(photo != null) {
			if(!embedPhoto.deletePhotoCategories(photoId, photoCategoryId))
				return ERROR;
		}else
			return ERROR;

		return SUCCESS;
	}



}
