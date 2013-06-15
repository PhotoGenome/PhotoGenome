package edu.cmu.photogenome.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import edu.cmu.photogenome.domain.RegionCategory;

public class RegionCategoryDaoImpl extends GenericAbstractDaoImpl<RegionCategory, Integer> implements RegionCategoryDao {

	@SuppressWarnings("unchecked")
	public List<RegionCategory> findByRegionId(int regionId) {
		List<RegionCategory> list = null;
		try {
			session.beginTransaction();
			list = (List<RegionCategory>) session.createCriteria(type).add(Restrictions.eq("regionId", regionId)).list();
			session.getTransaction().commit();
		}
		catch(Exception e) {
			session.getTransaction().rollback();
			log.warn(e.getMessage(), e);
		}
		
		return list;
	}

}
