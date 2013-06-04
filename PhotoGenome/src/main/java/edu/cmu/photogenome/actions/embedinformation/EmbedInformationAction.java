package edu.cmu.photogenome.actions.embedinformation;

import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import edu.cmu.photogenome.dao.*;
import edu.cmu.photogenome.domain.*;

public class EmbedInformationAction extends ActionSupport {

	private PhotoRegionDao photoRegionDao = new PhotoRegionDaoImpl();
	private RegionCommentDao regionCommentDao = new RegionCommentDaoImpl();
	private RegionCategoryDao regionCategoryDao = new RegionCategoryDaoImpl();
	private RegionCoordinateDao regionCoordinateDao = new RegionCoordinateDaoImpl();
	
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean addPhotoComments(Photo currentPhoto, List<PhotoComment> listPhotoComments){
		return false;
	}

	/* takes a user object and verifies the login details it into the system
	 * return true if correct*/
	
	public boolean addPhotoCategories(Photo currentPhoto, List<PhotoCategory> listPhotoCategories){
		return false;
	}
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean addPhotoRegion(int photoId, int userId, int shapeId, int regionX, int regionY, 
			int height, int width) {
		PhotoRegion region = new PhotoRegion(photoId, userId, shapeId, new Date());
		if(!photoRegionDao.save(region))
			return false;
		else {
			RegionCoordinate coordinate = new RegionCoordinate(region.getRegionId(), photoId, userId, 
				regionX, regionY, height, width, new Date());
			if(!regionCoordinateDao.save(coordinate)) {
				photoRegionDao.delete(region);
				return false;
			}
		}
		
		return true;
	}
	
	public boolean addPhotoRegions(Photo currentPhoto, List<PhotoRegion> listPhotoRegions){
		for(PhotoRegion region : listPhotoRegions) {
			if(!photoRegionDao.save(region))
				return false;
		}
		
		return true;
	}
	
	public boolean addPhotoRegions(int photoId, int userId, int shapeId){
		PhotoRegion region = new PhotoRegion(photoId, userId, shapeId, new Date());
		return photoRegionDao.save(region);
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean addRegionComments(PhotoRegion currentPhotoRegion, List<RegionComment> listRegionComments){
		if(photoRegionDao.findById(currentPhotoRegion.getRegionId()) == null)
			return false;
		
		for(RegionComment regionComment : listRegionComments) {
			if(!regionCommentDao.save(regionComment))
				return false;
		}
		
		return true;
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean addRegionCategories(PhotoRegion currentPhotoRegion, List<RegionCategory> listRegionCategories){
		if(photoRegionDao.findById(currentPhotoRegion.getRegionId()) == null)
			return false;
		
		for(RegionCategory regionCategory : listRegionCategories) {
			if(!regionCategoryDao.save(regionCategory))
				return false;
		}
		
		return true;
	}
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean addRegionCoordinates(PhotoRegion currentPhotoRegion, List<RegionCoordinate> listRegionCoordinates){
		if(photoRegionDao.findById(currentPhotoRegion.getRegionId()) == null)
			return false;
		
		for(RegionCoordinate regionCoordinate : listRegionCoordinates) {
			if(!regionCoordinateDao.save(regionCoordinate))
				return false;
		}
		
		return true;
	}
	
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean editPhotoComments(Photo currentPhoto, PhotoComment currentPhotoComments){
		return false;
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean editPhotoCategories(Photo currentPhoto,PhotoCategory currentPhotoCategories){
		return false;
	}
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean editPhotoRegions(Photo currentPhoto, PhotoRegion currentPhotoRegions){
		return false;
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean editRegionComments(PhotoRegion currentPhotoRegion, RegionComment currentRegionComments){
		return false;
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean editRegionCategories(PhotoRegion currentPhotoRegion, RegionCategory currentRegionCategories){
		return false;
	}
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean editRegionCoordinates(PhotoRegion currentPhotoRegion, List<RegionCoordinate> listRegionCoordinates){
		return false;
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean deletePhotoComments(Photo currentPhoto, List<PhotoComment> listPhotoComments){
		return false;
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean deletePhotoCategories(Photo currentPhoto, List<PhotoCategory> listPhotoCategories){
		return false;
	}
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean deletePhotoRegions(Photo currentPhoto, List<PhotoRegion> listPhotoRegions){
		return false;
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean deleteRegionComments(PhotoRegion currentPhotoRegion, List<RegionComment> listRegionComments){
		return false;
	}

	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean deleteRegionCategories(PhotoRegion currentPhotoRegion, List<RegionCategory> listRegionCategories){
		return false;
	}
	/* takes a user object and verifies its login details it into the system
	 * return true if correct*/
	
	public boolean deleteRegionCoordinates(PhotoRegion currentPhotoRegion, List<RegionCoordinate> listRegionCoordinates){
		return false;
	}


}
