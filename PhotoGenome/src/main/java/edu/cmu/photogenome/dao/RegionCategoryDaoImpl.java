package edu.cmu.photogenome.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import edu.cmu.photogenome.domain.RegionCategory;

public class RegionCategoryDaoImpl extends GenericAbstractDaoImpl<RegionCategory, Integer> implements RegionCategoryDao {

	@SuppressWarnings("unchecked")
	public List<RegionCategory> findByRegionId(int regionId) {
		List<RegionCategory> list = null;
		try {
			list = (List<RegionCategory>) session.createCriteria(type).add(Restrictions.eq("regionId", regionId)).list();
		}
		catch(Exception e) {
			log.warn(e.getMessage(), e);
		}
		
		return list;
	}

}
