package edu.cmu.photogenome.dao;

import java.util.List;

import edu.cmu.photogenome.domain.PhotoComment;

public interface PhotoCommentDao extends GenericDao<PhotoComment, Integer> {
	
	public List<PhotoComment> findByPhotoId(int photoId);
}
