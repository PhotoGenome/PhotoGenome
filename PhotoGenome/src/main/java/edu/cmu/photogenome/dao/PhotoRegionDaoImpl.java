package edu.cmu.photogenome.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import edu.cmu.photogenome.domain.PhotoRegion;

public class PhotoRegionDaoImpl extends GenericAbstractDaoImpl<PhotoRegion, Integer> implements PhotoRegionDao {

	@SuppressWarnings("unchecked")
	public List<PhotoRegion> findByPhotoId(int photoId) {
		return findAllByCriteria("photoId", photoId);
	}
}
