package edu.cmu.photogenome.dao;

import java.util.List;

import edu.cmu.photogenome.domain.PhotoCategory;

public interface PhotoCategoryDao extends GenericDao<PhotoCategory, Integer> {
	
	public List<PhotoCategory> findByPhotoId(int photoId);
}
