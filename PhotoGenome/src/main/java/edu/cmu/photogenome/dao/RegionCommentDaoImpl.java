package edu.cmu.photogenome.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import edu.cmu.photogenome.domain.RegionComment;

public class RegionCommentDaoImpl extends GenericAbstractDaoImpl<RegionComment, Integer> implements RegionCommentDao {

	@SuppressWarnings("unchecked")
	public List<RegionComment> findByRegionId(int regionId) {
		return findAllByCriteria("regionId", regionId);
	}
}
