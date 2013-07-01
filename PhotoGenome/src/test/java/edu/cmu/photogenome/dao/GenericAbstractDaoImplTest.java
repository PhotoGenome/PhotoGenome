package edu.cmu.photogenome.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import edu.cmu.photogenome.domain.Photo;
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
	public void testFindByIds() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(1);
		idList.add(3);
		List<Photo> photoList = null;
		photoList = photoDao.findByIds(idList);
		assertNotNull(photoList);
		assertEquals(2, photoList.size());
		assertEquals(1, photoList.get(0).getPhotoId().intValue());
		assertEquals(3, photoList.get(1).getPhotoId().intValue());
	}
	
	@Test
	public void testFindByIdsEmpty() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		List<Integer> idList = new ArrayList<Integer>();
		idList.add(-1);
		List<Photo> photoList = null;
		photoList = photoDao.findByIds(idList);
		assertNotNull(photoList);
		assertEquals(0, photoList.size());
	}
	
	@Test
	public void testFindAll() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		List<Photo> list = null;
		list = photoDao.findAll();
		assertNotNull(list);
		assertEquals(2, list.size());
	}
	
	@Test
	public void testFindAllByCriteria() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		List<Photo> list = null;
		list = photoDao.findAllByCriteria("userId", 1000);
		assertNotNull(list);
		assertEquals(2, list.size());
		for(Photo p : list)
			assertEquals(1000, p.getUserId());
	}
	
	@Test
	public void testFindAllByCriteriaEmpty() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		List<Photo> list = null;
		list = photoDao.findAllByCriteria("userId", -1);
		assertNotNull(list);
		assertEquals(0, list.size());
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
