package edu.cmu.photogenome.dao;

import java.util.List;

import edu.cmu.photogenome.domain.RegionCoordinate;

public interface RegionCoordinateDao extends GenericDao<RegionCoordinate, Integer> {
	
	public List<RegionCoordinate> findByPhotoId(int photoId);
	
	public List<RegionCoordinate> findByRegionId(int regionId);
}
