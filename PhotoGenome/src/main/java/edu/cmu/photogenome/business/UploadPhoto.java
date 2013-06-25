package edu.cmu.photogenome.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cmu.photogenome.dao.PhotoDao;
import edu.cmu.photogenome.dao.PhotoDaoImpl;
import edu.cmu.photogenome.domain.Photo;

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
	
	public List<Photo> savePhoto(int userId, List<File> fileList) {
		List<Photo> photoList = new ArrayList<Photo>();
		
		for(File file : fileList) {
			Photo photo = savePhoto(userId, file.getName(), file);
			if(photo != null)
				photoList.add(photo);
			else
				return null;
		}
		
		return photoList;
	}
	
	private Photo savePhoto(int userId, String photoName, File photoFile) {
		Photo photo = new Photo(userId, new Date());
		photo.setPhotoName(photoName);

		log.debug("Saving photo with userId={}, photoName={}", userId, photoName);
		if(photoDao.save(photo)) {
			if(savePhotoLink(photoFile, photo)// Save the photo file to some location
					&& !savePhotoMetadata(photo.getPhotoId())) // Save category data for the photo to a file
				return photo;
			else
				return null;
		}
		else {
			return null;
		}
	}
	
	private boolean savePhotoLink(File file, Photo photo) {
		Properties config = new Properties();
		
		try {
			config.load(this.getClass().getClassLoader().getResourceAsStream("ApplicationResources.properties"));
		}
		catch(IOException ioe) {
			log.error(ioe.getMessage(), ioe);
			return false;
		}
		
		String path = config.getProperty("photoLinkPath");
		String name = getPhotoLinkUniqueName(photo);
		photo.setPhotoLink(name);
		
		FileInputStream in = null;
		FileOutputStream out = null;
		
		try {
			File outFile = new File(path, name);
			
			if(!outFile.exists()) // create the file
				outFile.createNewFile();
			
			in = new FileInputStream(file);
			out = new FileOutputStream(new File(path, name), false);
			int data;
			while((data = in.read()) != -1)
				out.write(data);
		} 
		catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
			return false;
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
	
	private boolean savePhotoMetadata(int photoId) {
		return false;
	}
}
