package edu.cmu.photogenome.actions;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONWriter;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import edu.cmu.photogenome.business.ViewInformation;
import edu.cmu.photogenome.dao.PhotoCommentDao;
import edu.cmu.photogenome.dao.PhotoCommentDaoImpl;
import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.domain.PhotoCategory;
import edu.cmu.photogenome.domain.PhotoComment;
import edu.cmu.photogenome.domain.PhotoRegion;
import edu.cmu.photogenome.domain.RegionCategory;
import edu.cmu.photogenome.domain.RegionComment;
import edu.cmu.photogenome.domain.RegionCoordinate;
import edu.cmu.photogenome.util.HibernateUtil;

public class ViewInformationAction extends ActionSupport{

	final Logger log = LoggerFactory.getLogger(ViewInformationAction.class);
	
	private final String jsonKey = getText("json.key");

	private Integer photoId;
	private Integer regionId;

	/** Variables to store/pass JSON data **/
	private Map<String, Object> jsonGetPhoto = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetPhotoComments = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetPhotoCategories = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetPhotoRegions = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetRegionComments = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetRegionCategories = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonGetRegionCoordinates = new LinkedHashMap<String, Object>();

	private ViewInformation viewInformation = new ViewInformation();

	PhotoCommentDao photoCommentDao = new PhotoCommentDaoImpl();

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
				//List<Photo>
				jsonGetPhoto.put(jsonKey, photo);
				HibernateUtil.commitTransaction(session);
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
//			System.out.println("PHOTO COMMENT= " + photoCommentDao.findById(1).getPhotoCommentText());
			if((list = viewInformation.getPhotoComments(photoId)) != null){
//				System.out.println(viewInformation.getPhotoComments(photoId).size());
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
	
	public Map<String, Object> getJsonGetPhoto() {
		return jsonGetPhoto;
	}

	public void setJsonGetPhoto(Map<String, Object> jsonGetPhoto) {
		this.jsonGetPhoto = jsonGetPhoto;
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
}
