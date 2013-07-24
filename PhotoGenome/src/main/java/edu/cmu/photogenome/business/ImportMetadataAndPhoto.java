package edu.cmu.photogenome.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import edu.cmu.photogenome.dao.ImportedMetadataDao;
import edu.cmu.photogenome.dao.ImportedMetadataDaoImpl;
import edu.cmu.photogenome.dao.PhotoDao;
import edu.cmu.photogenome.dao.PhotoDaoImpl;
import edu.cmu.photogenome.domain.ImportedMetadata;
import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.util.ConfigUtil;
import edu.cmu.photogenome.util.HibernateUtil;


/**
 * The <code>UploadPhoto</code> class provides functionality to upload multiple 
 * photos and to delete photos. Uploading a photo saves the image file, creates 
 * a category metadata file for the photo, and persists a photo entity in the 
 * database.
 * 
 * @author PhotoGenome
 *
 */

public class ImportMetadataAndPhoto {

	final Logger log = LoggerFactory.getLogger(UploadPhoto.class);
	
	private PhotoDao photoDao;
	private UploadPhoto uploadPhoto;
	private ImportedMetadata ipmObj;
	private ImportedMetadataDao  importedMetadataDao;
	public ImportMetadataAndPhoto() {
		photoDao = new PhotoDaoImpl();
		uploadPhoto= new UploadPhoto();
		importedMetadataDao = new ImportedMetadataDaoImpl();
	}
	
	public ImportMetadataAndPhoto(Session session) {
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
		uploadPhoto.setSession(session);
		importedMetadataDao.setSession(session);
	}
	
	public  void getPhotoAndMetadata(String directoryPath, int userId){
		 File[] files = new File(directoryPath).listFiles();
		    showFiles(files,userId);
	}
	public  void showFiles(File[] files, int userId) {
		//Added b hemant
		
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		ImportMetadataAndPhoto importData = new ImportMetadataAndPhoto(session); 
//		
//		importData.setSession(session);
		//end hemant
		
		
	     int iNoOfFiles=0;
	        String currentPhotoMetadata="";
	        Photo currentPhoto=null;
		for (File file : files) {
	        if (file.isDirectory()) {
	            System.out.println("Directory: " + file.getName());
	       	 File[] innerfiles = file.listFiles();
	     	 for (File innerfile : innerfiles) {
	        	 	
	           if(innerfile.getName().endsWith(".mods.xml"))
	           {
	        	   ++iNoOfFiles;
	        	   System.out.println("XML File: " + innerfile.getName());
	        	   currentPhotoMetadata=getMetadataFromFile(innerfile.getAbsolutePath());
	           }
	           else  if(innerfile.getName().endsWith(".jpg") && !innerfile.getName().endsWith(".thumb.jpg"))
	 	          {
	        	   /* upload code */
	        	   System.out.println("Jpg File: " + innerfile.getName());
	        	   currentPhoto = uploadPhoto.savePhoto(userId,innerfile.getName(),innerfile);
	        	   /* insert imported metadata code */
	        	   System.out.println("XML metadata: " + currentPhotoMetadata);
		        ipmObj= new ImportedMetadata(currentPhoto.getPhotoId(),currentPhotoMetadata);
		        importedMetadataDao.save(ipmObj);
	 	          }
	           else{
	        	   System.out.println("Any other File: " + innerfile.getName());
			        
	           }
	        }
	      }
	    }
	  System.out.println("Total XML:"+iNoOfFiles);  
	}
	public  String getMetadataFromFile(String xmlFileName ){
	    try {
		File fXmlFile = new File(xmlFileName);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("mods:mods");
		for (int temp = 0; temp < nList.getLength();) {
			Node nNode = nList.item(temp);
		 		System.out.println("--Data--"+nNode.getTextContent().replaceAll("\\s"," "));
		return 	 nNode.getTextContent().replaceAll("\\s"," ");
		}
		} catch (Exception e) {
	    	e.printStackTrace();
	        }
	    return 	 "no data";
		
	}
	
	 public static void main(String argv[]) {
			Session session = HibernateUtil.getSessionFactory().openSession();
		    try {
		    	ImportMetadataAndPhoto imObj= new ImportMetadataAndPhoto(session);
		    	HibernateUtil.beginTransaction(session);
		    	imObj.getPhotoAndMetadata("C:\\Users\\apoorvijain\\Downloads\\rewynder_from_pitt_archives\\rewynder_from_pitt_archives\\",1000);
		    	HibernateUtil.commitTransaction(session);
		    } catch (Exception e) {
		    	HibernateUtil.rollbackTransaction(session);
			e.printStackTrace();
		    }
		  
		  }


	
		
}
