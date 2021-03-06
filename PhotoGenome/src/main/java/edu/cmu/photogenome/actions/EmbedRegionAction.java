package edu.cmu.photogenome.actions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import edu.cmu.photogenome.business.EmbedRegion;
import edu.cmu.photogenome.business.ViewInformation;
import edu.cmu.photogenome.domain.PhotoRegion;
import edu.cmu.photogenome.domain.RegionCategory;
import edu.cmu.photogenome.domain.RegionComment;
import edu.cmu.photogenome.domain.RegionCoordinate;
import edu.cmu.photogenome.util.HibernateUtil;
 
/**
 * The <code>EmbedRegionAction</code> class passes information to 
 * <code>EmbedRegion</code> class to embed region information for 
 * photos including adding, editing and deleting region categories 
 * and comments.
 *  
 * @author PhotoGenome
 *
 */

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
	private Integer regionCategoryId;
	private String categoryName;

	private String regionCategoryText;
	private Integer regionCoordinateId;
	private int regionX;
	private int regionY;
	private int height;
	private int width;
	
	/** Variables to store/pass JSON data **/
	private Map<String, Object> jsonAddPhotoRegion = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonAddRegionComment = new LinkedHashMap<String, Object>();
	private Map<String, Object> jsonAddRegionCategory = new LinkedHashMap<String, Object>();
	
	public String addPhotoRegion() {
		PhotoRegion region;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		embedRegion.setSession(session);
		ViewInformation viewInfo = new ViewInformation(session);
		HibernateUtil.beginTransaction(session);
		
		if(viewInfo.getPhoto(photoId) == null) {
			HibernateUtil.rollbackTransaction(session);
			return SUCCESS;
		}
		else {
			if((region = embedRegion.addPhotoRegion(photoId, userId, shapeId, regionX, regionY, height, width)) != null) {
				jsonAddPhotoRegion.put(jsonKey, region);
				HibernateUtil.commitTransaction(session);
				return SUCCESS;
			}
			else {
				HibernateUtil.rollbackTransaction(session);
				return SUCCESS;
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
			return SUCCESS;
		}
		else {
			if((regionComment = embedRegion.addRegionComment(photoId, userId, regionId, regionCommentText)) !=null) {
				jsonAddRegionComment.put(jsonKey, regionComment);
				HibernateUtil.commitTransaction(session);
				return SUCCESS;
			}
			else {
				HibernateUtil.rollbackTransaction(session);
				return SUCCESS;
			}
		}
	}
	
	/**
	 * Add a region category
	 * 
	 * @return success if added, otherwise error
	 */
	public String addRegionCategory(){

		RegionCategory category = null;
		List<SimpleEntry<String, String>> categoryDetails = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		embedRegion.setSession(session);
		ViewInformation viewInfo = new ViewInformation(session);
		HibernateUtil.beginTransaction(session);
		
		if(viewInfo.getPhotoRegion(regionId) == null) {
			HibernateUtil.rollbackTransaction(session);
			return SUCCESS;
		}
		else {
			categoryDetails = new ArrayList<SimpleEntry<String, String>>();
			categoryDetails.add(new SimpleEntry<String, String>(categoryName, regionCategoryText));
			if((category = embedRegion.addRegionCategory(regionId, photoId, userId, categoryDetails)) != null) {
				jsonAddRegionCategory.put(jsonKey, category);
				HibernateUtil.commitTransaction(session);
				return SUCCESS;
			}
			else {
				HibernateUtil.rollbackTransaction(session);
				return SUCCESS;
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
			return SUCCESS;
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
			return SUCCESS;
		}
	}
	
	/**
	 * Delete a region category
	 * 
	 * @return success if deleted, otherwise error
	 */
	public String deleteRegionCategory() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		embedRegion.setSession(session);
		HibernateUtil.beginTransaction(session);

		if(embedRegion.deleteRegionCategory(regionCategoryId)) {
			HibernateUtil.commitTransaction(session);
			return SUCCESS;
		}
		else {
			HibernateUtil.rollbackTransaction(session);
			return SUCCESS;
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
			return SUCCESS;
		}
	}
	
	public String editRegionComment() {
		RegionComment comment = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		embedRegion.setSession(session);
		ViewInformation viewInfo = new ViewInformation(session);
		HibernateUtil.beginTransaction(session);
		
		if((comment = viewInfo.getRegionComment(regionCommentId)) == null) {
			HibernateUtil.rollbackTransaction(session);
			return SUCCESS;
		}
		else { // update the comment
			comment.setRegionCommentText(regionCommentText);
			if(embedRegion.editRegionComment(comment)) {
				HibernateUtil.commitTransaction(session);
				return SUCCESS;
			}
			else {
				HibernateUtil.rollbackTransaction(session);
				return SUCCESS;
			}
		}
	}
	
	/**
	 * Edit region category
	 * 
	 * @return true if region category is updated, otherwise false
	 */
	public String editRegionCategory() {
		RegionCategory regionCategory = null;
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		embedRegion.setSession(session);
		ViewInformation viewInfo = new ViewInformation(session);
		HibernateUtil.beginTransaction(session);
		
		if((regionCategory = viewInfo.getRegionCategory(regionCategoryId)) == null) {
			HibernateUtil.rollbackTransaction(session);
			return SUCCESS;
		}
		else {
			regionCategory.setCategoryName(categoryName);
			regionCategory.setRegionCategoryText(regionCategoryText);
			if (embedRegion.editRegionCategory(regionCategory)) {
				HibernateUtil.commitTransaction(session);
				return SUCCESS;
			}
			else {
				HibernateUtil.rollbackTransaction(session);
				return SUCCESS;
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
			return SUCCESS;
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
				return SUCCESS;
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

	public Integer getRegionCategoryId() {
		return regionCategoryId;
	}

	public void setRegionCategoryId(Integer regionCategoryId) {
		this.regionCategoryId = regionCategoryId;
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
	
	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getRegionCategoryText() {
		return regionCategoryText;
	}

	public void setRegionCategoryText(String regionCategoryText) {
		this.regionCategoryText = regionCategoryText;
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

	public Map<String, Object> getJsonAddRegionCategory() {
		return jsonAddRegionCategory;
	}

	public void setJsonAddRegionCategory(Map<String, Object> jsonAddRegionCategory) {
		this.jsonAddRegionCategory = jsonAddRegionCategory;
	}
	
}
