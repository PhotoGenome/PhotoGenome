package edu.cmu.photogenome.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import edu.cmu.photogenome.domain.PhotoRegion;

public class PhotoRegionDaoImpl extends GenericAbstractDaoImpl<PhotoRegion, Integer> implements PhotoRegionDao {

	@SuppressWarnings("unchecked")
	public List<PhotoRegion> findByPhotoId(int photoId) {
		List<PhotoRegion> list = null;
		try {
			list = (List<PhotoRegion>) session.createCriteria(type).add(Restrictions.eq("photoId", photoId)).list();
		}
		catch(Exception e) {
			log.warn(e.getMessage(), e);
		}
		
		return list;
	}
}
