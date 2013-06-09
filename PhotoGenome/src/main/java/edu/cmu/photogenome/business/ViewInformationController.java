package edu.cmu.photogenome.business;

import java.util.List;

import edu.cmu.photogenome.data.dto.Photo;
import edu.cmu.photogenome.data.dto.*;

public class ViewInformationController {

	/* On submit on User login this will 
	 * fetch the user login details and create a user 
	 * object with it*/
	
	public Photo selectPhoto(int iPhotoId){
		
	}
	
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public List<PhotoComment> getPhotoComments(Photo currentPhoto){
		
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public List<PhotoCategory> getPhotoCategories(Photo currentPhoto){
		
	}
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public List<PhotoRegion> getPhotoRegions(Photo currentPhoto){
		
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public List<RegionComment> getRegionComments(PhotoRegion currentPhotoRegion){
		
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public List<RegionCategory> getRegionCategories(PhotoRegion currentPhotoRegion){
		
	}
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public List<RegionCoor> getRegionCoordinates(PhotoRegion currentPhotoRegion){
		
	}
}
