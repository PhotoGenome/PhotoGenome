package edu.cmu.photogenome.actions;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONWriter;

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

public class ViewInformationAction extends ActionSupport{

	private final String jsonKey  = getText("json.key");

	private Integer photoId;

	public Integer getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

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

	/**Get photo using photoId
	 * 
	 * @return
	 */
	public String getPhoto(){

		JSONWriter jsonWriter = null;
		Photo photo = null;

		try{
			jsonWriter = new JSONWriter();
			if((photo = viewInformation.getPhoto(photoId)) != null){
				//jsonGetPhoto.put(String.valueOf(photo.getPhotoId()), jsonWriter.write(photo));
				jsonGetPhoto.put(jsonKey, photo);
				return SUCCESS;
			}else {
				return ERROR;
			}
		}catch(Exception e){
			return ERROR;
		}

	}

	/**
	 * Get photo comments
	 * 
	 * @return 
	 */
	public String getPhotoComments(){


		JSONWriter jsonWriter = null;
		List<PhotoComment> list = null;
		System.out.println("PHOTO ID = " + photoId);
		try{
			jsonWriter = new JSONWriter();
			//System.out.println("PHOTO COMMENT= " + photoCommentDao.findById(1).getPhotoCommentText());

			if((list = viewInformation.getPhotoComments(photoId)) != null){
				System.out.println(viewInformation.getPhotoComments(photoId).size());
				//for (PhotoComment comment : list){
				jsonGetPhotoComments.put(jsonKey, list);
				System.out.println(jsonWriter.write(jsonGetPhotoComments));
				//}
				return SUCCESS;
			}else {
				System.out.println("error");
				return ERROR;
			}
		}catch(JSONException e){
			e.printStackTrace();
			return ERROR;
		} catch(Exception e){
			e.printStackTrace();
			return ERROR;
		}

	}

	/**
	 * Get photo categories
	 * 
	 * @return
	 */

	public String getPhotoCategories(){

		JSONWriter jsonWriter = null;
		List<PhotoCategory> list = null;

		try{
			jsonWriter = new JSONWriter();
			if((list = viewInformation.getPhotoCategories(photoId)) != null){

				//for (PhotoCategory category : list){
				//jsonGetPhotoCategories.put(String.valueOf(category.getPhotoCategoryId()), jsonWriter.write(category));
				jsonGetPhotoCategories.put(jsonKey, list);
				//}

				return SUCCESS;
			}else {
				return ERROR;
			}
		}catch(Exception e){
			return ERROR;
		}

	}

	/**
	 * Get photo regions
	 * 
	 * @return
	 */
	public String getPhotoRegions(){

		JSONWriter jsonWriter = null;
		List<PhotoRegion> list = null;

		try{
			jsonWriter = new JSONWriter();
			if( (list = viewInformation.getPhotoRegions(photoId)) != null){

				//for (PhotoRegion region : list){
				//	jsonGetPhotoRegions.put(String.valueOf(region.getRegionId()), jsonWriter.write(region));
				//}
				jsonGetPhotoRegions.put(jsonKey, list);
				return SUCCESS;
			}else {
				return ERROR;
			}
		}catch(Exception e){
			return ERROR;
		}

	}

	/**
	 * Get region comments
	 * 
	 * @return
	 */

	public String getRegionComments(){

		JSONWriter jsonWriter = null;
		List<RegionComment> list = null;

		try{
			jsonWriter = new JSONWriter();
			if( (list = viewInformation.getRegionComments(photoId)) != null){

				//for (RegionComment regionComment : list){
				//	jsonGetRegionComments.put(String.valueOf(regionComment.getRegionCommentId()), jsonWriter.write(regionComment));
				//}
				jsonGetRegionComments.put(jsonKey, list);
				return SUCCESS;
			}else {
				return ERROR;
			}
		}catch(Exception e){
			return ERROR;
		}

	}

	/**
	 * Get region categories
	 * 
	 * @return
	 */

	public String getRegionCategories(){

		JSONWriter jsonWriter = null;
		List<RegionCategory> list = null;

		try{
			jsonWriter = new JSONWriter();
			if( (list = viewInformation.getRegionCategories((photoId))) != null){

				//for (RegionCategory regionCategory : list){
				//	jsonGetRegionCategories.put(String.valueOf(regionCategory.getRegionCategoryId()), jsonWriter.write(regionCategory));
				//}

				jsonGetRegionCategories.put(jsonKey, list);

				return SUCCESS;
			}else {
				return ERROR;
			}
		}catch(Exception e){
			return ERROR;
		}

	}

	/**
	 * Get region co-ordinates
	 * 
	 * @return
	 */

	public String getRegionCoordinates(){

		JSONWriter jsonWriter = null;
		List<RegionCoordinate> list = null;

		try{
			jsonWriter = new JSONWriter();
			if( (list = viewInformation.getRegionCoordinates((photoId))) != null){

				//for (RegionCoordinate regionCoordinate: list){
				//	jsonGetRegionCoordinates.put(String.valueOf(regionCoordinate.getRegionCoordinateId()),jsonWriter.write(regionCoordinate));
				//}
				jsonGetRegionCoordinates.put(jsonKey, list);
				return SUCCESS;
			}else {
				return ERROR;
			}
		}catch(Exception e){
			return ERROR;
		}

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
