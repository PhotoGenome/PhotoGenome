package edu.cmu.photogenome.dao;

import edu.cmu.photogenome.domain.PhotoFile;

public interface PhotoFileDao {
	
	public boolean delete(PhotoFile photo);
	
	public PhotoFile retrievePhoto(String location);
	
	public boolean save(PhotoFile photo);
	
}
