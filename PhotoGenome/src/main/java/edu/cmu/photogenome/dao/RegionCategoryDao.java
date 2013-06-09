package edu.cmu.photogenome.dao;

import java.util.List;

import edu.cmu.photogenome.domain.RegionCategory;

public interface RegionCategoryDao extends GenericDao<RegionCategory, Integer> {
	
	public List<RegionCategory> findByRegionId(int regionId);
}
