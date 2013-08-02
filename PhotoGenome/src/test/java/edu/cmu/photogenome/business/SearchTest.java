package edu.cmu.photogenome.business;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.util.HibernateDbUnitTestCase;

public class SearchTest extends HibernateDbUnitTestCase{

	@Test
	public void testGetAssociatedPhotos() {
		int photoId = 1;
		
		Search search = new Search(session);
		List<Photo> results = search.getAssociatedPhotos(photoId);
		
		assertNotNull(results);
	}
	
	@Test
	public void testGetAssociatedPhotosEmptyResults() {
		int photoId = -1;
		
		Search search = new Search(session);
		List<Photo> results = search.getAssociatedPhotos(photoId);
		
		assertNotNull(results);
		assertEquals(0, results.size());
	}

	@Test
	public void testGetFilteredAssociatedPhotosByCategoryId() {
		int photoId = 1;
		List<Integer> photoCategoryIdList = new ArrayList<Integer>();
		List<Integer> regionCategoryIdList = new ArrayList<Integer>();
		Search search = new Search(session);
		List<Photo> results = null;
		
		photoCategoryIdList.add(1);
		regionCategoryIdList.add(1);
		results = search.getFilteredAssociatedPhotosByCategoryId(photoId, photoCategoryIdList, regionCategoryIdList);
		assertNotNull(results);
		assertEquals(2, results.size());
		System.out.println("size = " + results.size());
		for(Photo p : results)
			System.out.println(p.getPhotoId());
	}

	@Test
	public void testGetFilteredAssociatedPhotosByCategoryIdEmptyResults() {
		int photoId = 1;
		List<Integer> photoCategoryIdList = new ArrayList<Integer>();
		List<Integer> regionCategoryIdList = new ArrayList<Integer>();
		Search search = new Search(session);
		List<Photo> results = null;
		
		results = search.getFilteredAssociatedPhotosByCategoryId(photoId, photoCategoryIdList, regionCategoryIdList);
		assertNotNull(results);
		assertEquals(0, results.size());
	}
	
	@Test
	public void testGetFilteredAssociatedPhotosByCategoryValue() {
		int photoId = 1;
		List<String> photoCategoryList = new ArrayList<String>();
		List<String> regionCategoryList = new ArrayList<String>();
		Search search = new Search(session);
		List<Photo> results = null;
		
		photoCategoryList.add("buildingHamerschlagHall");
		regionCategoryList.add("personTyler");
		results = search.getFilteredAssociatedPhotosByCategoryValue(photoId, photoCategoryList, regionCategoryList);
		assertNotNull(results);
		assertEquals(2, results.size());
		System.out.println("size = " + results.size());
		for(Photo p : results)
			System.out.println(p.getPhotoId());
	}

	@Test
	public void testGetFilteredAssociatedPhotosByCategoryValueEmptyResults() {
		int photoId = 1;
		List<String> photoCategoryList = new ArrayList<String>();
		List<String> regionCategoryList = new ArrayList<String>();
		Search search = new Search(session);
		List<Photo> results = null;
		
		results = search.getFilteredAssociatedPhotosByCategoryValue(photoId, photoCategoryList, regionCategoryList);
		assertNotNull(results);
		assertEquals(0, results.size());
	}
	
	@Test
	public void testGetFilteredAssociatedPhotosByCategoryValueNullPhoto() {
		int photoId = -1;
		List<String> photoCategoryList = new ArrayList<String>();
		List<String> regionCategoryList = new ArrayList<String>();
		Search search = new Search(session);
		List<Photo> results = null;
		
		results = search.getFilteredAssociatedPhotosByCategoryValue(photoId, photoCategoryList, regionCategoryList);
		assertNotNull(results);
		assertEquals(0, results.size());
	}
	
	@Test
	public void testGetPhotosByKeyword() {
		int photoId = 1;
		
		Search search = new Search(session);
		List<Photo> results = search.getAssociatedPhotos(photoId);
		
		assertNotNull(results);
	}

	@Test
	public void testGetPhotosByKeywordEmptyResults() {
		int photoId = 1;
		
		Search search = new Search(session);
		List<Photo> results = search.getAssociatedPhotos(photoId);
		
		assertNotNull(results);
		assertEquals(0, results.size());
	}
}
