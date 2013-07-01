package edu.cmu.photogenome.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.util.StopWatch;

import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.util.HibernateDbUnitTestCase;

/**
 * Performance tests for DAO class
 */
public class DaoPerformanceTest extends HibernateDbUnitTestCase {
	
	private int deleteTimeSeconds = 5;
	private int findTimeSeconds = 5;
	private int saveTimeSeconds = 5;
	private int updateTimeSeconds = 5;
	
	@Test
	public void testDelete() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		Photo photo = photoDao.findById(1);
		assertNotNull(photo);

		StopWatch timer = new StopWatch();
		timer.start();
		boolean result = photoDao.delete(photo);
		timer.stop();
		assertTrue(result);
		assertTrue(timer.getTotalTimeSeconds() <= deleteTimeSeconds);
		System.out.println(timer.getTotalTimeSeconds());
	}
	
	@Test
	public void testFindById() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		
		StopWatch timer = new StopWatch();
		timer.start();
		Photo photo = photoDao.findById(1);
		timer.stop();
		assertTrue(timer.getTotalTimeSeconds() <= findTimeSeconds);
		System.out.println(timer.getTotalTimeSeconds());
	}
	
	@Test
	public void testFindByIds() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		List<Photo> list = null;
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(2);
		
		StopWatch timer = new StopWatch();
		timer.start();
		list = photoDao.findByIds(ids);
		timer.stop();
		assertTrue(timer.getTotalTimeSeconds() <= findTimeSeconds);
		System.out.println(timer.getTotalTimeSeconds());
	}
	
	@Test
	public void testFindAll() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		List<Photo> list = null;
		
		StopWatch timer = new StopWatch();
		timer.start();
		list = (ArrayList<Photo>) photoDao.findAll();
		timer.stop();
		assertTrue(timer.getTotalTimeSeconds() <= findTimeSeconds);
		System.out.println(timer.getTotalTimeSeconds());
	}
	
	@Test
	public void testFindAllByCriteria() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		List<Photo> list = null;
		
		StopWatch timer = new StopWatch();
		timer.start();
		list = photoDao.findAllByCriteria("userId", 1000);
		timer.stop();
		assertTrue(timer.getTotalTimeSeconds() <= findTimeSeconds);
		System.out.println(timer.getTotalTimeSeconds());
	}
	
	@Test
	public void testSave() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		Photo photo = new Photo(1000, new Date());
		
		StopWatch timer = new StopWatch();
		timer.start();
		photoDao.save(photo);
		timer.stop();
		assertTrue(timer.getTotalTimeSeconds() <= saveTimeSeconds);
		System.out.println(timer.getTotalTimeSeconds());
	}

	@Test
	public void testUpdate() {
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		int photoId = 1;
		String photoName = "Photo Name";
		Photo photo = photoDao.findById(photoId);
		photo.setPhotoName(photoName);
		
		StopWatch timer = new StopWatch();
		timer.start();
		boolean result = photoDao.update(photo);
		timer.stop();
		assertTrue(timer.getTotalTimeSeconds() <= updateTimeSeconds);
		System.out.println(timer.getTotalTimeSeconds());
		assertTrue(result);
	}

}
