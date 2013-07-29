package edu.cmu.photogenome.actions;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import edu.cmu.photogenome.business.ViewInformation;
import edu.cmu.photogenome.domain.ImportedMetadata;
import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.domain.PhotoCategory;
import edu.cmu.photogenome.domain.PhotoComment;
import edu.cmu.photogenome.domain.PhotoRegion;
import edu.cmu.photogenome.domain.RegionCategory;
import edu.cmu.photogenome.domain.RegionComment;
import edu.cmu.photogenome.domain.RegionCoordinate;
import edu.cmu.photogenome.util.HibernateUtil;

/**
 * The <code>ViewInformationAction</code> class passes information 
 * to <code>ViewInformation</code> class to get all the relevant
 * data which needs to be displayed for a photo.
 * 
 * @author PhotoGenome
 *
 */

public class ViewInformationAction extends ActionSupport{

	final Logger log = LoggerFactory.getLogger(ViewInformationAction.class);
	
	private final String jsonKey = getText("json.key");

	private Integer photoId;
	private Integer regionId;
	private Integer userId;
	private Map<String, Object> jsonGetImportedMetadata = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetPhoto = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetPhotos = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetPhotoComments = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetPhotoCategories = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetPhotoRegions = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetRegionComments = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetRegionCategories = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetRegionCoordinates = new LinkedHashMap<String, Object>();

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Map<String, Object> getJsonGetImportedMetadata() {
		return jsonGetImportedMetadata;
	}

	public void setJsonGetImportedMetadata(Map<String, Object> jsonGetImportedMetadata) {
		this.jsonGetImportedMetadata = jsonGetImportedMetadata;
	}

	public Map<String, Object> getJsonGetPhoto() {
		return jsonGetPhoto;
	}

	public void setJsonGetPhoto(Map<String, Object> jsonGetPhoto) {
		this.jsonGetPhoto = jsonGetPhoto;
	}

	public Map<String, Object> getJsonGetPhotos() {
		return jsonGetPhotos;
	}

	public void setJsonGetPhotos(Map<String, Object> jsonGetPhotos) {
		this.jsonGetPhotos = jsonGetPhotos;
	}

	public Map<String, Object> getJsonGetPhotoComments() {
		return jsonGetPhotoComments;
	}

	public void setJsonGetPhotoComments(Map<String, Object> jsonGetPhotoComments) {
		this.jsonGetPhotoComments = jsonGetPhotoComments;
	}

	public Map<String, Object> getJsonGetPhotoCategories() {
		return jsonGetPhotoCategories;
	}

	public void setJsonGetPhotoCategories(Map<String, Object> jsonGetPhotoCategories) {
		this.jsonGetPhotoCategories = jsonGetPhotoCategories;
	}

	public Map<String, Object> getJsonGetPhotoRegions() {
		return jsonGetPhotoRegions;
	}

	public void setJsonGetPhotoRegions(Map<String, Object> jsonGetPhotoRegions) {
		this.jsonGetPhotoRegions = jsonGetPhotoRegions;
	}

	public Map<String, Object> getJsonGetRegionComments() {
		return jsonGetRegionComments;
	}

	public void setJsonGetRegionComments(Map<String, Object> jsonGetRegionComments) {
		this.jsonGetRegionComments = jsonGetRegionComments;
	}

	public Map<String, Object> getJsonGetRegionCategories() {
		return jsonGetRegionCategories;
	}

	public void setJsonGetRegionCategories(
			Map<String, Object> jsonGetRegionCategories) {
		this.jsonGetRegionCategories = jsonGetRegionCategories;
	}

	public Map<String, Object> getJsonGetRegionCoordinates() {
		return jsonGetRegionCoordinates;
	}

	public void setJsonGetRegionCoordinates(
			Map<String, Object> jsonGetRegionCoordinates) {
		this.jsonGetRegionCoordinates = jsonGetRegionCoordinates;
	}

	
	private ViewInformation viewInformation = new ViewInformation();

	/**
	 * Get photo from photoId
	 * 
	 * @return
	 */
	public String getPhoto(){
		Photo photo = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		viewInformation.setSession(session);
		HibernateUtil.beginTransaction(session);
		try {
			if((photo = viewInformation.getPhoto(photoId)) != null) {
				
				// replace photo link path
				try {
					Properties config = new Properties();
					config.load(this.getClass().getClassLoader().getResourceAsStream("ApplicationResources.properties"));
					photo.setPhotoLink(config.getProperty("photoLinkPath") + photo.getPhotoLink());
				}
				catch(IOException ioe) {
					log.error(ioe.getMessage(), ioe);
				}
				
				jsonGetPhoto.put(jsonKey, photo);
				HibernateUtil.rollbackTransaction(session); // only retrieving data, don't save changes
				return SUCCESS;
			}
			else {
				HibernateUtil.rollbackTransaction(session);
				return ERROR;
			}
		} 
		catch(Exception e) {
			log.warn(e.getMessage(), e);
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}
	}
	
	public String getPhotosByUserId() {
		List<Photo> list = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		viewInformation.setSession(session);
		HibernateUtil.beginTransaction(session);
		try {
			if((list = viewInformation.getPhotos("userId", userId)) != null){
				
				// replace photo link path
				try {
					Properties config = new Properties();
					config.load(this.getClass().getClassLoader().getResourceAsStream("ApplicationResources.properties"));
					for(Photo photo : list) {
						photo.setPhotoLink(config.getProperty("photoLinkPath") + photo.getPhotoLink());
					}
				}
				catch(IOException ioe) {
					log.error(ioe.getMessage(), ioe);
				}
				
				jsonGetPhotos.put(jsonKey, list);
				HibernateUtil.rollbackTransaction(session); // only retrieving data, don't save changes
				return SUCCESS;
			}else {
				HibernateUtil.rollbackTransaction(session);
				return ERROR;
			}
		} catch(Exception e){
			log.warn(e.getMessage(), e);
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}
	}
	
	
	public String getImportedMetadataByPhotoId() {
		List<ImportedMetadata> list = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		viewInformation.setSession(session);
		HibernateUtil.beginTransaction(session);
		System.out.println("photoId"+photoId);			
		
		try {
			if((list = viewInformation.getImportedMetadata("photoId", photoId)) != null){
				
				Properties config = new Properties();
				try {
					config.load(this.getClass().getClassLoader().getResourceAsStream("ApplicationResources.properties"));
				}
				catch(IOException ioe) {
					log.error(ioe.getMessage(), ioe);
				}
				jsonGetImportedMetadata.put(jsonKey, list);
				System.out.println("metadata"+list.get(0).getImportedMetadata().toString());			
				System.out.println("metadata"+jsonGetImportedMetadata.toString());			
				
				HibernateUtil.rollbackTransaction(session);
				return SUCCESS;
			}else {
				HibernateUtil.rollbackTransaction(session);
				return ERROR;
			}
		} catch(Exception e){
			log.warn(e.getMessage(), e);
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}
	}
	
	
	/**
	 * Get photo comments from photoId
	 * 
	 * @return 
	 */
	public String getPhotoComments() {
		List<PhotoComment> list = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		viewInformation.setSession(session);
		HibernateUtil.beginTransaction(session);
		try {
			if((list = viewInformation.getPhotoComments(photoId)) != null){
				jsonGetPhotoComments.put(jsonKey, list);
				HibernateUtil.commitTransaction(session);
				return SUCCESS;
			}else {
				HibernateUtil.rollbackTransaction(session);
				return ERROR;
			}
		} catch(Exception e){
			log.warn(e.getMessage(), e);
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}
	}

	/**
	 * Get photo categories from photoId
	 * 
	 * @return
	 */
	public String getPhotoCategories() {
		List<PhotoCategory> list = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		viewInformation.setSession(session);
		HibernateUtil.beginTransaction(session);
		try {
			if((list = viewInformation.getPhotoCategories(photoId)) != null){
				jsonGetPhotoCategories.put(jsonKey, list);
				HibernateUtil.commitTransaction(session);
				return SUCCESS;
			}
			else {
				HibernateUtil.rollbackTransaction(session);
				return ERROR;
			}
		} catch(Exception e) {
			log.warn(e.getMessage(), e);
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}
	}

	/**
	 * Get photo regions from photoId
	 * 
	 * @return
	 */
	public String getPhotoRegions() {
		List<PhotoRegion> list = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		viewInformation.setSession(session);
		HibernateUtil.beginTransaction(session);
		try{
			if((list = viewInformation.getPhotoRegions(photoId)) != null){
				jsonGetPhotoRegions.put(jsonKey, list);
				HibernateUtil.commitTransaction(session);
				return SUCCESS;
			}
			else {
				HibernateUtil.rollbackTransaction(session);
				return ERROR;
			}
		} catch(Exception e){
			log.warn(e.getMessage(), e);
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}
	}

	/**
	 * Get region comments from regionId
	 * 
	 * @return
	 */
	public String getRegionComments(){
		List<RegionComment> list = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		viewInformation.setSession(session);
		HibernateUtil.beginTransaction(session);
		try{
			if((list = viewInformation.getRegionComments(regionId)) != null){
				jsonGetRegionComments.put(jsonKey, list);
				HibernateUtil.commitTransaction(session);
				return SUCCESS;
			}
			else {
				HibernateUtil.rollbackTransaction(session);
				return ERROR;
			}
		} catch(Exception e){
			log.warn(e.getMessage(), e);
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}

	}

	/**
	 * Get region categories from regionId
	 * 
	 * @return
	 */
	public String getRegionCategories(){
		List<RegionCategory> list = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		viewInformation.setSession(session);
		HibernateUtil.beginTransaction(session);
		try{
			if((list = viewInformation.getRegionCategories(regionId)) != null){
				jsonGetRegionCategories.put(jsonKey, list);
				HibernateUtil.commitTransaction(session);
				return SUCCESS;
			}
			else {
				HibernateUtil.rollbackTransaction(session);
				return ERROR;
			}
		} catch(Exception e){
			log.warn(e.getMessage(), e);
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}
	}

	/**
	 * Get region coordinates from regionId
	 * 
	 * @return
	 */
	public String getRegionCoordinates(){
		List<RegionCoordinate> list = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		viewInformation.setSession(session);
		HibernateUtil.beginTransaction(session);
		try {
			if((list = viewInformation.getRegionCoordinates(photoId)) != null){
				jsonGetRegionCoordinates.put(jsonKey, list);
				HibernateUtil.commitTransaction(session);
				return SUCCESS;
			}
			else {
				HibernateUtil.rollbackTransaction(session);
				return ERROR;
			}
		} catch(Exception e){
			log.warn(e.getMessage(), e);
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}
	}

}
