package edu.cmu.photogenome.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import edu.cmu.photogenome.domain.PhotoComment;

public class PhotoCommentDaoImpl extends GenericAbstractDaoImpl<PhotoComment, Integer> implements PhotoCommentDao {

	@SuppressWarnings("unchecked")
	public List<PhotoComment> findByPhotoId(int photoId) {
		List<PhotoComment> list = null;
		try {
			session.beginTransaction();
			list = (List<PhotoComment>) session.createCriteria(type).add(Restrictions.eq("photoId", photoId)).list();
			session.getTransaction().commit();
		}
		catch(Exception e) {
			session.getTransaction().rollback();
			log.warn(e.getMessage(), e);
		}
		
		return list;
	}

}
