package edu.cmu.photogenome.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cmu.photogenome.dao.PhotoDao;
import edu.cmu.photogenome.dao.PhotoDaoImpl;
import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.util.ConfigUtil;


/**
 * The <code>UploadPhoto</code> class provides functionality to upload multiple 
 * photos and to delete photos. Uploading a photo saves the image file and persists 
 * a photo entity in the database.
 * 
 */

public class UploadPhoto {

	final Logger log = LoggerFactory.getLogger(UploadPhoto.class);
	
	private PhotoDao photoDao;
	
	public UploadPhoto() {
		photoDao = new PhotoDaoImpl();
	}
	
	public UploadPhoto(Session session) {
		this();
		setSession(session);
	}
	
	/**
	 * Set the Hibernate session to use when calling DAOs
	 * 
	 * @param session
	 */
	public void setSession(Session session) {
		photoDao.setSession(session);
	}
	
	/**
	 * Delete a photo entity and the associated photo file
	 * 
	 * @param photoId
	 * @return
	 */
	public boolean deletePhoto(int photoId){
		Photo photo;
		if((photo = photoDao.findById(photoId)) != null) {
			if(!deletePhotoFile(photo.getPhotoLink())) // try to delete the photo file
				log.error("Failed to delete file with name {}", photo.getPhotoLink());
			
			log.debug("Deleting photo with ID = {}", photoId);
			if(!photoDao.delete(photo))
				return false;
		}
		else
			log.debug("Photo with ID = {} does not exist. Nothing to delete", photoId);
		
		return true;
	}
	
	/**
	 * Delete a photo file
	 * 
	 * @param name	file name
	 * @return true if deleted, else false
	 */
	private boolean deletePhotoFile(String name) {
		Properties config = ConfigUtil.getApplicationProperties(); // try to load config properties
		if(config == null)
			return false;
		
		//String path = config.getProperty("photoLinkPath");
		String path = System.getProperty("user.dir") + "\\src\\main\\webapp\\jsp\\";
		File file = new File(path, name);

		return file.delete();
	}
	
	/**
	 * Save a list of photo files and create database entries for each of them
	 * 
	 * @param userId	user who uploaded the photos
	 * @param fileList	list of files to be saved
	 * @return list of Photo entities that were saved
	 */
	public List<Photo> savePhoto(int userId, List<File> fileList) {
		List<Photo> photoList = new ArrayList<Photo>();
		
		for(File file : fileList) {
			Photo photo = savePhoto(userId, FilenameUtils.getBaseName(file.getName()), file);
			if(photo != null)
				photoList.add(photo);
			else
				return null;
		}
		
		return photoList;
	}
	
	/**
	 * Save a photo file and create a database entry for it
	 * 
	 * @param userId	user who uploaded the photo
	 * @param photoName	photo name
	 * @param photoFile photo file to be saved
	 * @return Photo entity if successful, else null
	 */
	public Photo savePhoto(int userId, String photoName, File photoFile) {
		Photo photo = new Photo(userId, new Date());

		log.debug("Saving photo with userId={}, photoName={}", userId, photoName);
		if(photoDao.save(photo)) {
			if(savePhotoLink(photoFile, photo))// Save the photo file to some location
				return photo;
			else
				return null;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Save a file to disk and set the photo link path on the photo entity
	 * 
	 * @param file	the file to save
	 * @param photo	the photo whose link should be set to the saved file path
	 * @return	true if successful, else false
	 */
	private boolean savePhotoLink(File file, Photo photo) {
		Properties config = ConfigUtil.getApplicationProperties(); // try to load config properties
		if(config == null)
			return false;

		//String path = config.getProperty("photoLinkPath"); // path is stored in config file
		String path = System.getProperty("user.dir") + "\\src\\main\\webapp\\jsp\\";
		String name = getPhotoLinkUniqueName(photo) + config.getProperty("photoFileExtension");
		photo.setPhotoLink(name); // photo link name is unique name appended by file extension set in app properties

		FileInputStream in = null;
		FileOutputStream out = null;
		
		try {
			File outFile = new File(path, name);
			System.out.println(outFile.getAbsolutePath());
			if(!outFile.exists()) // create the file
				outFile.createNewFile();
			
			in = new FileInputStream(file); // stream to source file
			out = new FileOutputStream(outFile, false); // stream to dest file
			
			log.debug("Writing file from {} to {}", file.getAbsolutePath(), outFile.getAbsolutePath());
			IOUtils.copy(in, out); // write from the input stream to the output stream
			log.debug("Saved photo file with name = {} to path = {}", name, path);
		} 
		catch (IOException ioe) {
			log.error(ioe.getMessage(), ioe);
			return false;
		}
		finally { // close resources
			try {
				if(in != null)
					in.close();
				if(out != null) {
					out.flush();
					out.close();
				}
			}
			catch (IOException ioe) {
				log.warn(ioe.getMessage(), ioe);
			}
		}
		
		return true;
	}
	
	/**
	 * Get a unique name for saving the photo file. Uses timestamp with photo id appended as the key.
	 * 
	 * @param photo the photo to save
	 * @return string representing a unique key
	 */
	private String getPhotoLinkUniqueName(Photo photo) {
		return String.valueOf(photo.getPhotoTimestamp().getTime()) + String.valueOf(photo.getPhotoId());
	}
	
}
