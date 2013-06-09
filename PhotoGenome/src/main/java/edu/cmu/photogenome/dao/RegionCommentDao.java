package edu.cmu.photogenome.dao;

import java.util.List;

import edu.cmu.photogenome.domain.RegionComment;

public interface RegionCommentDao extends GenericDao<RegionComment, Integer> {
	
	public List<RegionComment> findByRegionId(int regionId);
}
