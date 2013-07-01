package edu.cmu.photogenome.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import edu.cmu.photogenome.domain.PhotoCategory;

public class PhotoCategoryDaoImpl extends GenericAbstractDaoImpl<PhotoCategory, Integer> implements PhotoCategoryDao {

	@SuppressWarnings("unchecked")
	public List<PhotoCategory> findByPhotoId(int photoId) {
		return findAllByCriteria("photoId", photoId);
	}
}
