package edu.cmu.photogenome.dao;

import java.util.List;

import edu.cmu.photogenome.domain.PhotoRegion;

public interface PhotoRegionDao extends GenericDao<PhotoRegion, Integer> {
	
	public List<PhotoRegion> findByPhotoId(int photoId);
}
