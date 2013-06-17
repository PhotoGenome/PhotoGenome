package edu.cmu.photogenome.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import edu.cmu.photogenome.domain.PhotoComment;

public class PhotoCommentDaoImpl extends GenericAbstractDaoImpl<PhotoComment, Integer> implements PhotoCommentDao {

	@SuppressWarnings("unchecked")
	public List<PhotoComment> findByPhotoId(int photoId) {
		List<PhotoComment> list = null;
		try {
			list = (List<PhotoComment>) session.createCriteria(type).add(Restrictions.eq("photoId", photoId)).list();
		}
		catch(Exception e) {
			log.warn(e.getMessage(), e);
		}
		
		return list;
	}

}
