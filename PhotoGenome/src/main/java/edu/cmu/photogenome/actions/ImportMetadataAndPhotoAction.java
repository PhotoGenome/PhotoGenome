package edu.cmu.photogenome.actions;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import edu.cmu.photogenome.business.ImportMetadataAndPhoto;
import edu.cmu.photogenome.util.HibernateUtil;

public class ImportMetadataAndPhotoAction extends ActionSupport {
	
	final Logger log = LoggerFactory.getLogger(ImportMetadataAndPhotoAction.class);
	
	final String jsonKey = getText("json.key");
	
	private String path;
	private int userId;
	
	ImportMetadataAndPhoto importMetadata = new ImportMetadataAndPhoto();
	
	public String importMetadataAndPhoto() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		importMetadata.setSession(session);
		HibernateUtil.beginTransaction(session);
		
		log.info("Uploading photo at {} for user {}", path, userId);
		
		try {
			importMetadata.getPhotoAndMetadata(path, userId);
			HibernateUtil.commitTransaction(session);
		}
		catch(Exception e) {
			log.error(e.getMessage());
			HibernateUtil.rollbackTransaction(session);
		}
		
		return SUCCESS;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
