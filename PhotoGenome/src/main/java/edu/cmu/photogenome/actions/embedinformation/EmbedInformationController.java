package edu.cmu.photogenome.actions.embedinformation;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import edu.cmu.photogenome.dao.PhotoDao;
import edu.cmu.photogenome.dao.PhotoDaoImpl;
import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.domain.PhotoCategory;
import edu.cmu.photogenome.domain.PhotoComment;
import edu.cmu.photogenome.domain.PhotoRegion;
import edu.cmu.photogenome.domain.RegionCategory;
import edu.cmu.photogenome.domain.RegionComment;
import edu.cmu.photogenome.domain.User;

public class EmbedInformationController extends ActionSupport{

	
	private PhotoDao photoDao = new PhotoDaoImpl();
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean addPhotoComments(Photo currentPhoto, List<PhotoComment> lstPhotoComments){
		
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean addPhotoCategories(Photo currentPhoto, List<PhotoCategory> lstPhotoCategories){
		
		Photo newPhoto = currentPhoto;
		int photoId = newPhoto.getPhotoId();
		photoDao.save(currentPhoto);
    	newUser.setUserFirstName(this.username);
    	
    	
    	return SUCCESS;
	}
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean addPhotoRegions(Photo currentPhoto, List<PhotoRegion> lstPhotoRegions){
		
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean addRegionComments(PhotoRegion currentPhotoRegion, List<RegionComment> lstRegionComments){
		
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean addRegionCategories(PhotoRegion currentPhotoRegion, List<RegionCategory> lstRegionCategories){
		
	}
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean addRegionCoordinates(PhotoRegion currentPhotoRegion, List<RegionCoor> lstRegionCoordinates){
		
	}
	
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean editPhotoComments(Photo currentPhoto, PhotoComment currentPhotoComments){
		
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean editPhotoCategories(Photo currentPhoto,PhotoCategory currentPhotoCategories){
		
	}
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean editPhotoRegions(Photo currentPhoto, PhotoRegion currentPhotoRegions){
		
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean editRegionComments(PhotoRegion currentPhotoRegion, RegionComment currentRegionComments){
		
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean editRegionCategories(PhotoRegion currentPhotoRegion, RegionCategory currentRegionCategories){
		
	}
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean editRegionCoordinates(PhotoRegion currentPhotoRegion, List<RegionCoor> lstRegionCoordinates){
		
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean deletePhotoComments(Photo currentPhoto, List<PhotoComment> lstPhotoComments){
		
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean deletePhotoCategories(Photo currentPhoto, List<PhotoCategory> lstPhotoCategories){
		
	}
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean deletePhotoRegions(Photo currentPhoto, List<PhotoRegion> lstPhotoRegions){
		
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean deleteRegionComments(PhotoRegion currentPhotoRegion, List<RegionComment> lstRegionComments){
		
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean deleteRegionCategories(PhotoRegion currentPhotoRegion, List<RegionCategory> lstRegionCategories){
		
	}
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean deleteRegionCoordinates(PhotoRegion currentPhotoRegion, List<RegionCoor> lstRegionCoordinates){
		
	}


}
