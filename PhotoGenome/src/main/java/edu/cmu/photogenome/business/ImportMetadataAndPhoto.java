package edu.cmu.photogenome.business;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
import edu.cmu.photogenome.util.HibernateUtil;

/**
 * The <code>ImportMetadataAndPhoto</code> class provides functionality to upload multiple 
 * photos along with associated XML data.
 */

public class ImportMetadataAndPhoto {

	final Logger log = LoggerFactory.getLogger(UploadPhoto.class);
	
	private PhotoDao photoDao;
	private UploadPhoto uploadPhoto;
	private ImportedMetadata ipmObj;
	private ImportedMetadataDao  importedMetadataDao;
	
	private int xmlFileCount;
	private int imageFileCount;
	
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
	
	public void getPhotoAndMetadata(String directoryPath, int userId) {
		xmlFileCount = 0;
		imageFileCount = 0;
		processDir(new File(directoryPath), userId);
		System.out.println("Total XML: " + xmlFileCount);
		System.out.println("Total Images: " + imageFileCount);
	}
	
	private void processDir(File dir, int userId) {
		String currentPhotoMetadata = null;
		Photo currentPhoto = null;
		File[] files = dir.listFiles();
		
		for (File file : files) {
           if(file.isDirectory())
        	   processDir(file, userId);
           else if(file.isFile()) {
        	   if(file.getName().endsWith(".mods.xml"))
        		   currentPhotoMetadata = processXmlFile(file);
        	   if(file.getName().endsWith(".jpg") && !file.getName().endsWith(".thumb.jpg"))
        		   currentPhoto = processImageFile(file, userId);
           }
        }
		
	  	// save imported metadata
    	if(currentPhoto != null && currentPhotoMetadata != null) {
    		ipmObj= new ImportedMetadata(currentPhoto.getPhotoId(), currentPhotoMetadata);
    		System.out.println(importedMetadataDao.save(ipmObj));
    	}
	}
	
	private String processXmlFile(File file) {
		String currentPhotoMetadata = null;
		
		System.out.println("XML File: " + file.getName());
		currentPhotoMetadata = getMetadataFromFile(file.getAbsolutePath());
    	System.out.println("XML metadata: " + currentPhotoMetadata);
    	xmlFileCount++;
		
		return currentPhotoMetadata;
	}
	
	private Photo processImageFile(File file, int userId) {
        Photo currentPhoto = null;
        
        // upload code
        System.out.println("Jpg File: " + file.getName());
        currentPhoto = uploadPhoto.savePhoto(userId, file.getName(), file);
        imageFileCount++;
     	
     	return currentPhoto;
	}
	
	private String getMetadataFromFile(String xmlFileName ){
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
				return nNode.getTextContent().replaceAll("\\s"," ");
			}
		} catch (Exception e) {
	    	e.printStackTrace();
	    }
	    
	    return "no data";
	}
	
	public static void main(String argv[]) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
		   	ImportMetadataAndPhoto imObj= new ImportMetadataAndPhoto(session);
		   	HibernateUtil.beginTransaction(session);
		   	
		   	if(argv.length < 2)
		   		throw new Exception("Need to enter directory path and user id as input arguments");
		   	imObj.getPhotoAndMetadata(argv[0], Integer.parseInt(argv[1]));
		   	
		   	HibernateUtil.commitTransaction(session);
		} catch (Exception e) {
		   	HibernateUtil.rollbackTransaction(session);
			e.printStackTrace();
		}
	}
	 
}
