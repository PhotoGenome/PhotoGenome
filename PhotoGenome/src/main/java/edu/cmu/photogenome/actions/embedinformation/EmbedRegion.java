package edu.cmu.photogenome.actions.embedinformation;

import java.util.Date;

import edu.cmu.photogenome.dao.PhotoRegionDao;
import edu.cmu.photogenome.dao.PhotoRegionDaoImpl;
import edu.cmu.photogenome.dao.RegionCommentDao;
import edu.cmu.photogenome.dao.RegionCommentDaoImpl;
import edu.cmu.photogenome.dao.RegionCoordinateDao;
import edu.cmu.photogenome.dao.RegionCoordinateDaoImpl;
import edu.cmu.photogenome.domain.PhotoRegion;
import edu.cmu.photogenome.domain.RegionComment;
import edu.cmu.photogenome.domain.RegionCoordinate;

public class EmbedRegion {

	private PhotoRegionDao photoRegionDao;
	private RegionCommentDao regionCommentDao;
	private RegionCoordinateDao regionCoordinateDao;

	public EmbedRegion() {
		photoRegionDao = new PhotoRegionDaoImpl();
		regionCommentDao = new RegionCommentDaoImpl();
		regionCoordinateDao = new RegionCoordinateDaoImpl();
	}
	
	/**
	 * Save a region marked on a photo
	 * 
	 * @param photoId
	 * @param userId
	 * @param shapeId
	 * @param regionX
	 * @param regionY
	 * @param height
	 * @param width
	 * @return true if the region is saved, else false
	 */
	public boolean addPhotoRegion(int photoId, int userId, int shapeId, int regionX, int regionY, 
			int height, int width) {
		PhotoRegion region = null;
		if((region = addPhotoRegion(photoId, userId, shapeId)) == null) // try to save the region
			return false;
		else {
			RegionCoordinate coordinate = null;
			if((coordinate = addRegionCoordinate(region.getRegionId(), photoId, userId, regionX, regionY, 
					height, width)) == null) { // try to save the coordinates
				photoRegionDao.delete(region); // delete region if coordinates failed to save
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Private helper method to save a photo region
	 * 
	 * @param photoId
	 * @param userId
	 * @param shapeId
	 * @return the saved photo region, or null if failed to save
	 */
	private PhotoRegion addPhotoRegion(int photoId, int userId, int shapeId) {
		PhotoRegion region = new PhotoRegion(photoId, userId, shapeId, new Date());
		
		if(photoRegionDao.save(region))
			return region;
		else
			return null;
	}

	/**
	 * Save a comment on a region
	 * 
	 * @param photoId
	 * @param userId
	 * @param regionId
	 * @param regionCommentText
	 * @return true if the comment was saved, else false
	 */
	public boolean addRegionComment(int photoId, int userId, int regionId, String regionCommentText) {
		RegionComment comment = new RegionComment(photoId, regionId, userId, new Date());
		comment.setRegionCommentText(regionCommentText);
		
		if(regionCommentDao.save(comment))
			return true;
		else
			return false;
	}
	

	/**
	 * Private helper method to save a set of region coordinates
	 * 
	 * @param regionId
	 * @param photoId
	 * @param userId
	 * @param regionX
	 * @param regionY
	 * @param height
	 * @param width
	 * @return the saved region coordinates, or null if failed to save
	 */
	private RegionCoordinate addRegionCoordinate(int regionId, int photoId, int userId, int regionX, int regionY, 
			int height, int width){
		RegionCoordinate coordinate = new RegionCoordinate(regionId, photoId, userId, regionX, regionY, 
				height, width, new Date());
		
		if(regionCoordinateDao.save(coordinate))
				return coordinate;
		else
			return null;
	}
	

	/**
	 * Edit the text of a region comment
	 * 
	 * @param comment
	 * @return true if the comment is updated, else false
	 */
	public boolean editRegionComment(RegionComment comment){
		if(regionCommentDao.update(comment))
			return true;
		else
			return false;
	}

	/**
	 * Edit a region coordinate
	 * @param coordinate
	 * @return true if the coordinate is updated, else false
	 */
	public boolean editRegionCoordinate(RegionCoordinate coordinate){
		if(regionCoordinateDao.update(coordinate))
			return true;
		else
			return false;
	}

	/**
	 * Delete a photo region
	 * @param regionId
	 * @return true if the region no longer exists, else false
	 */
	public boolean deletePhotoRegion(int regionId){
		PhotoRegion region;
		if((region = photoRegionDao.findById(regionId)) != null) {
			if(!photoRegionDao.delete(region))
				return false;
		}
		
		return true;
	}

	/**
	 * Delete a region comment
	 * @param commentId
	 * @return true if the region comment no longer exists, else false
	 */
	public boolean deleteRegionComment(int regionCommentId){
		RegionComment comment;
		if((comment = regionCommentDao.findById(regionCommentId)) != null) {
			if(!regionCommentDao.delete(comment))
				return false;
		}
		
		return true;
	}

	/**
	 * Delete a region coordinate
	 * @param coordinateId
	 * @return true if the region coordinate no longer exists, else false
	 */
	public boolean deleteRegionCoordinate(int regionCoodinateId) {
		RegionCoordinate coordinate;
		if((coordinate = regionCoordinateDao.findById(regionCoodinateId)) != null) {
			if(!regionCoordinateDao.delete(coordinate))
				return false;
		}
		
		return true;		
	}
}
