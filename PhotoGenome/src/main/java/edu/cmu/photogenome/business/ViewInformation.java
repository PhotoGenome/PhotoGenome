package edu.cmu.photogenome.business;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cmu.photogenome.dao.PhotoCategoryDao;
import edu.cmu.photogenome.dao.PhotoCategoryDaoImpl;
import edu.cmu.photogenome.dao.PhotoCommentDao;
import edu.cmu.photogenome.dao.PhotoCommentDaoImpl;
import edu.cmu.photogenome.dao.PhotoDao;
import edu.cmu.photogenome.dao.PhotoDaoImpl;
import edu.cmu.photogenome.dao.PhotoRegionDao;
import edu.cmu.photogenome.dao.PhotoRegionDaoImpl;
import edu.cmu.photogenome.dao.RegionCategoryDao;
import edu.cmu.photogenome.dao.RegionCategoryDaoImpl;
import edu.cmu.photogenome.dao.RegionCommentDao;
import edu.cmu.photogenome.dao.RegionCommentDaoImpl;
import edu.cmu.photogenome.dao.RegionCoordinateDao;
import edu.cmu.photogenome.dao.RegionCoordinateDaoImpl;
import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.domain.PhotoCategory;
import edu.cmu.photogenome.domain.PhotoComment;
import edu.cmu.photogenome.domain.PhotoRegion;
import edu.cmu.photogenome.domain.RegionCategory;
import edu.cmu.photogenome.domain.RegionComment;
import edu.cmu.photogenome.domain.RegionCoordinate;

public class ViewInformation {
	
	final Logger log = LoggerFactory.getLogger(ViewInformation.class);
	
	private PhotoDao photoDao;
	private PhotoCommentDao photoCommentDao;
	private PhotoCategoryDao photoCategoryDao;
	private PhotoRegionDao photoRegionDao;
	private RegionCommentDao regionCommentDao;
	private RegionCategoryDao regionCategoryDao;
	private RegionCoordinateDao regionCoordinateDao;
	
	public ViewInformation() {
		photoDao = new PhotoDaoImpl();
		photoCommentDao = new PhotoCommentDaoImpl();
		photoCategoryDao = new PhotoCategoryDaoImpl();
		photoRegionDao = new PhotoRegionDaoImpl();
		regionCommentDao = new RegionCommentDaoImpl();
		regionCategoryDao =new RegionCategoryDaoImpl();
		regionCoordinateDao = new RegionCoordinateDaoImpl();
	}
	
	/**
	 * Get the photo for the given photo id
	 * 
	 * @param photoId
	 * @return the photo, null if the photo id is not found
	 */
	public Photo getPhoto(int photoId) {
		return photoDao.findById(photoId);
	}
	
	/**
	 * Get the list of photo comments for the given photo id
	 * 
	 * @param photoId
	 * @return list of photo comments, null if photo not found
	 */
	public List<PhotoComment> getPhotoComments(int photoId) {
		return photoCommentDao.findByPhotoId(photoId);
	}

	/**
	 * Get the list of photo categories for the given photo id
	 * 
	 * @param photoId
	 * @return list of photo categories, null if photo not found
	 */
	public List<PhotoCategory> getPhotoCategories(int photoId) {
		return photoCategoryDao.findByPhotoId(photoId);
	}
	
	/**
	 * Get the list of photo regions for the given photo id
	 * 
	 * @param photoId
	 * @return list of photo regions, null if photo not found
	 */
	public List<PhotoRegion> getPhotoRegions(int photoId) {
		return photoRegionDao.findByPhotoId(photoId);
	}

	/**
	 * Get the list of region comments for the given region id
	 * 
	 * @param regionId
	 * @return list of region comments, null if region not fond
	 */
	public List<RegionComment> getRegionComments(int regionId) {
		return regionCommentDao.findByRegionId(regionId);
	}

	/**
	 * Get the list of region categories for the given region id
	 * 
	 * @param regionId
	 * @return list of region coordinates, null if region not found
	 */
	public List<RegionCategory> getRegionCategories(int regionId){
		return regionCategoryDao.findByRegionId(regionId);
	}
	
	/**
	 * Get the list of region coordinates for the given region id
	 * 
	 * @param regionId
	 * @return list of region coordinates, null if region not found
	 */
	public List<RegionCoordinate> getRegionCoordinates(int regionId){
		return regionCoordinateDao.findByRegionId(regionId);
	}
}