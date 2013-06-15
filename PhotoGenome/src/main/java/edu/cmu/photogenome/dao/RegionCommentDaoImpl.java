package edu.cmu.photogenome.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import edu.cmu.photogenome.domain.RegionComment;

public class RegionCommentDaoImpl extends GenericAbstractDaoImpl<RegionComment, Integer> implements RegionCommentDao {

	@SuppressWarnings("unchecked")
	public List<RegionComment> findByRegionId(int regionId) {
		List<RegionComment> list = null;
		try {
			session.beginTransaction();
			list = (List<RegionComment>) session.createCriteria(type).add(Restrictions.eq("regionId", regionId)).list();
			session.getTransaction().commit();
		}
		catch(Exception e) {
			session.getTransaction().rollback();
			log.warn(e.getMessage(), e);
		}
		
		return list;
	}
}
