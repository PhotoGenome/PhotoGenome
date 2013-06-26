package edu.cmu.photogenome.actions;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import edu.cmu.photogenome.business.UploadPhoto;
import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.util.HibernateUtil;

public class UploadPhotoAction extends ActionSupport {

	final Logger log = LoggerFactory.getLogger(UploadPhotoAction.class);
	final String jsonKey = getText("json.key");

	private int userId;
	private int photoId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Map<String, Object> getJsonUploadPhoto() {
		return jsonUploadPhoto;
	}

	public void setJsonUploadPhoto(Map<String, Object> jsonUploadPhoto) {
		this.jsonUploadPhoto = jsonUploadPhoto;
	}

	public int getPhotoId() {
		return photoId;
	}

	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}

	private List<File> fileList;
	public List<File> getFileList() {
		return fileList;
	}

	public void setFileList(List<File> fileList) {
		this.fileList = fileList;
	}

	public List<Photo> getPhotoList() {
		return photoList;
	}

	public void setPhotoList(List<Photo> photoList) {
		this.photoList = photoList;
	}

	private List<Photo> photoList;

	UploadPhoto upload = new UploadPhoto();

	private Map<String, Object> jsonUploadPhoto = new LinkedHashMap<String, Object>();

	/**
	 * Upload single/multiple photos for a particular user id
	 * 
	 * @return list of photos saved to the database
	 */
	public String uploadPhoto(){

		Session session = HibernateUtil.getSessionFactory().openSession();
		upload.setSession(session);
		HibernateUtil.beginTransaction(session);

		//userId = 1000; // TODO remove this
		
		try{
			photoList = upload.savePhoto(userId, fileList);
			
			if(photoList == null) {
					HibernateUtil.rollbackTransaction(session);
					return ERROR;
			}

			jsonUploadPhoto.put(jsonKey, photoList);
			HibernateUtil.commitTransaction(session);
			return SUCCESS;

		}catch(Exception ex) {
			return ERROR;
		}
	}
	
	/**
	 * Delete a photo based on the userId and photoId
	 * 
	 * @return true if photo is deleted, otherwise false
	 */
	
	public String deletePhoto() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		upload.setSession(session);
		HibernateUtil.beginTransaction(session);

		try{
			if(!(upload.deletePhoto(photoId))) {
					HibernateUtil.rollbackTransaction(session);
					return ERROR;
			}

			HibernateUtil.commitTransaction(session);
			return SUCCESS;

		}catch(Exception ex) {
			return ERROR;
		}
	}
}
