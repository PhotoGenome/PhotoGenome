package edu.cmu.photogenome.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.domain.PhotoComment;
import edu.cmu.photogenome.util.HibernateDbUnitTestCase;

/**
 * Uses the PhotoDaoImpl class to test the GenericAbstractDaoImpl class methods
 */
public class GenericAbstractDaoImplTest extends HibernateDbUnitTestCase {

	@Test
	public void testDelete() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		Photo photo = photoDao.findById(1);
		assertNotNull(photo);
		boolean result = photoDao.delete(photo);
		assertTrue(result);
		photo = photoDao.findById(1);
		assertNull(photo);
	}
	
	@Test
	public void testFindById() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		Photo photo = photoDao.findById(1);
		assertNotNull(photo);
	}
	
	@Test
	public void testFindByIdNull() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		Photo photo = photoDao.findById(-1);
		assertNull(photo);
	}

	@Test
	public void testFindAll() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		List<Photo> list = null;
		list = (ArrayList<Photo>) photoDao.findAll();
		assertNotNull(list);
		assertEquals(2, list.size());
	}
	
	@Test
	public void testSave() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		Photo photo = new Photo();
		photo.setUserId(1000);
		photo.setPhotoTimestamp(new Date());
		photoDao.save(photo);
		photo = photoDao.findById(photo.getPhotoId());
		assertNotNull(photo);
	}

	@Test
	public void testUpdate() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		int photoId = 1;
		String photoName = "Photo Name";
		Photo photo = photoDao.findById(photoId);
		photo.setPhotoName(photoName);
		boolean result = photoDao.update(photo);
		assertTrue(result);
		photo = photoDao.findById(photoId);
		assertEquals(photoName, photo.getPhotoName());
	}

}
