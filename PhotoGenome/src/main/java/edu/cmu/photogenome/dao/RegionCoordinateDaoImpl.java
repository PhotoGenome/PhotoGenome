package edu.cmu.photogenome.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import edu.cmu.photogenome.domain.RegionCoordinate;

public class RegionCoordinateDaoImpl extends GenericAbstractDaoImpl<RegionCoordinate, Integer> implements RegionCoordinateDao {

	@SuppressWarnings("unchecked")
	public List<RegionCoordinate> findByPhotoId(int photoId) {
		return findAllByCriteria("photoId", photoId);
	}
	
	@SuppressWarnings("unchecked")
	public List<RegionCoordinate> findByRegionId(int regionId) {
		return findAllByCriteria("regionId", regionId);
	}
}
