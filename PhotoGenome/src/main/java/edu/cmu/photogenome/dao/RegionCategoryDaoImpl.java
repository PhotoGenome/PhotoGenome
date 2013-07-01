package edu.cmu.photogenome.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import edu.cmu.photogenome.domain.RegionCategory;

public class RegionCategoryDaoImpl extends GenericAbstractDaoImpl<RegionCategory, Integer> implements RegionCategoryDao {

	@SuppressWarnings("unchecked")
	public List<RegionCategory> findByRegionId(int regionId) {
		return findAllByCriteria("regionId", regionId);
	}

}
