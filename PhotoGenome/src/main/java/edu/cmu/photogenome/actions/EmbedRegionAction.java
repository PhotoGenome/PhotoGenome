package edu.cmu.photogenome.actions;

import java.util.LinkedHashMap;
import java.util.Map;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import edu.cmu.photogenome.business.EmbedRegion;
import edu.cmu.photogenome.business.ViewInformation;
import edu.cmu.photogenome.dao.PhotoDao;
import edu.cmu.photogenome.dao.PhotoDaoImpl;
import edu.cmu.photogenome.dao.PhotoRegionDao;
import edu.cmu.photogenome.dao.PhotoRegionDaoImpl;
import edu.cmu.photogenome.dao.RegionCommentDao;
import edu.cmu.photogenome.dao.RegionCommentDaoImpl;
import edu.cmu.photogenome.dao.RegionCoordinateDao;
import edu.cmu.photogenome.dao.RegionCoordinateDaoImpl;
import edu.cmu.photogenome.domain.PhotoRegion;
import edu.cmu.photogenome.domain.RegionComment;
import edu.cmu.photogenome.domain.RegionCoordinate;
import edu.cmu.photogenome.util.HibernateUtil;
 
public class EmbedRegionAction extends ActionSupport {

	final Logger log = LoggerFactory.getLogger(EmbedRegionAction.class);
	
	final String jsonKey = getText("json.key");
	
	private EmbedRegion embedRegion = new EmbedRegion();
	
	private Integer regionId;
	private int photoId;
	private int userId;
	private int shapeId;

	private Integer regionCommentId;
	private String regionCommentText;
		
	private Integer regionCoordinateId;
	private int regionX;
	
	private int regionY;
	private int height;
	private int width;
	
	/** Variables to store/pass JSON data **/
	private Map<String, Object> jsonAddPhotoRegion = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonAddRegionComment = new LinkedHashMap<String, Object>();
	
	public String addPhotoRegion() {
		PhotoRegion region;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		embedRegion.setSession(session);
		ViewInformation viewInfo = new ViewInformation(session);
		HibernateUtil.beginTransaction(session);
		
		if(viewInfo.getPhoto(photoId) == null) {
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}
		else {
			if((region = embedRegion.addPhotoRegion(photoId, userId, shapeId, regionX, regionY, height, width)) != null) {
				jsonAddPhotoRegion.put(jsonKey, region);
				HibernateUtil.commitTransaction(session);
				return SUCCESS;
			}
			else {
				HibernateUtil.rollbackTransaction(session);
				return ERROR;
			}
		}
	}
	
	public String addRegionComment() {
		RegionComment regionComment;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		embedRegion.setSession(session);
		ViewInformation viewInfo = new ViewInformation(session);
		HibernateUtil.beginTransaction(session);
		
		if(viewInfo.getPhotoRegion(regionId) == null) {
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}
		else {
			if((regionComment = embedRegion.addRegionComment(photoId, userId, regionId, regionCommentText))!=null) {
				jsonAddRegionComment.put(jsonKey, regionComment);
				HibernateUtil.commitTransaction(session);
				return SUCCESS;
			}
			else {
				HibernateUtil.rollbackTransaction(session);
				return ERROR;
			}
		}
	}
	
	public String deletePhotoRegion() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		embedRegion.setSession(session);
		HibernateUtil.beginTransaction(session);
		
		if(embedRegion.deletePhotoRegion(regionId)) {
			HibernateUtil.commitTransaction(session);
			return SUCCESS;
		}
		else {
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}
	}
	
	public String deleteRegionComment() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		embedRegion.setSession(session);
		HibernateUtil.beginTransaction(session);
		
		if(embedRegion.deleteRegionComment(regionCommentId)) {
			HibernateUtil.commitTransaction(session);
			return SUCCESS;
		}
		else {
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}
	}
	
	public String deleteRegionCoordinate() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		embedRegion.setSession(session);
		HibernateUtil.beginTransaction(session);
		
		if(embedRegion.deleteRegionCoordinate(regionCoordinateId)) {
			HibernateUtil.commitTransaction(session);
			return SUCCESS;
		}
		else {
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}
	}
	
	public String editRegionComment() {
		RegionComment comment = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		embedRegion.setSession(session);
		ViewInformation viewInfo = new ViewInformation(session);
		HibernateUtil.beginTransaction(session);
		
		if((comment = viewInfo.getRegionComment(regionCommentId)) == null)
			return ERROR;
		else { // update the comment
			comment.setRegionCommentText(regionCommentText);
			if(embedRegion.editRegionComment(comment)) {
				HibernateUtil.commitTransaction(session);
				return SUCCESS;
			}
			else {
				HibernateUtil.rollbackTransaction(session);
				return ERROR;
			}
		}
	}
	
	public String editRegionCoordinate() {
		RegionCoordinate coordinate = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		embedRegion.setSession(session);
		ViewInformation viewInfo = new ViewInformation(session);
		HibernateUtil.beginTransaction(session);
		
		if((coordinate = viewInfo.getRegionCoordinate(regionCoordinateId)) == null) {
			HibernateUtil.rollbackTransaction(session);
			return ERROR;
		}
		else { // update the coordinate
			coordinate.setRegionX(regionX);
			coordinate.setRegionY(regionY);
			coordinate.setHeight(height);
			coordinate.setWidth(width);
			if(embedRegion.editRegionCoordinate(coordinate)) {
				HibernateUtil.commitTransaction(session);
				return SUCCESS;
			}
			else {
				HibernateUtil.rollbackTransaction(session);
				return ERROR;
			}
		}
	}
	
	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getShapeId() {
		return shapeId;
	}

	public void setShapeId(int shapeId) {
		this.shapeId = shapeId;
	}

	public Integer getRegionCommentId() {
		return regionCommentId;
	}

	public void setRegionCommentId(Integer regionCommentId) {
		this.regionCommentId = regionCommentId;
	}

	public String getRegionCommentText() {
		return regionCommentText;
	}

	public void setRegionCommentText(String regionCommentText) {
		this.regionCommentText = regionCommentText;
	}

	public Integer getRegionCoordinateId() {
		return regionCoordinateId;
	}

	public void setRegionCoordinateId(Integer regionCoordinateId) {
		this.regionCoordinateId = regionCoordinateId;
	}

	public int getRegionX() {
		return regionX;
	}

	public void setRegionX(int regionX) {
		this.regionX = regionX;
	}

	public int getRegionY() {
		return regionY;
	}

	public void setRegionY(int regionY) {
		this.regionY = regionY;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public Map<String, Object> getJsonAddPhotoRegion() {
		return jsonAddPhotoRegion;
	}

	public void setJsonAddPhotoRegion(Map<String, Object> jsonAddPhotoRegion) {
		this.jsonAddPhotoRegion = jsonAddPhotoRegion;
	}

	public Map<String, Object> getJsonAddRegionComment() {
		return jsonAddRegionComment;
	}

	public void setJsonAddRegionComment(Map<String, Object> jsonAddRegionComment) {
		this.jsonAddRegionComment = jsonAddRegionComment;
	}
}
