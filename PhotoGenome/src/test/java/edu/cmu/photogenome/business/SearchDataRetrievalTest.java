package edu.cmu.photogenome.business;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.util.HibernateDbUnitTestCase;

public class SearchDataRetrievalTest extends HibernateDbUnitTestCase {

	@Test
	public void testSearchAssociatedPhotos() {
		int photoId = 1;
		int maxMatches = 3;
		
		SearchDataRetrieval search = new SearchDataRetrieval(session);
		List<Photo> results = search.searchAssociatedPhotos(photoId, maxMatches);
		
		assertNotNull(results);
	}

	@Test
	public void testSearchAssociatedPhotosEmptyResults() {
		int photoId = -1;
		int maxMatches = 3;
		
		SearchDataRetrieval search = new SearchDataRetrieval(session);
		List<Photo> results = search.searchAssociatedPhotos(photoId, maxMatches);
		
		assertNotNull(results);
		assertEquals(0, results.size());
	}
	
	@Test
	public void testSearchFilteredAssociatedPhotos() {
		int photoId = 1;
		int maxMatches = 3;
		List<String> categories = new ArrayList<String>();
		SearchDataRetrieval search = new SearchDataRetrieval(session);
		List<Photo> results = null;
		
		categories.add("buildingWeanHall");
		results = search.searchFilteredAssociatedPhotos(photoId, categories, maxMatches);
		assertNotNull(results);
		assertEquals(1, results.size());
		System.out.println("size = " + results.size());
		for(Photo p : results)
			System.out.println(p.getPhotoId());
		
		categories.clear();
		categories.add("buildingHamerschlagHall");
		results = search.searchFilteredAssociatedPhotos(photoId, categories, maxMatches);
		assertNotNull(results);
		assertEquals(1, results.size());
		System.out.println("size = " + results.size());
		for(Photo p : results)
			System.out.println(p.getPhotoId());
		
		categories.clear();
		categories.add("personTyler");
		results = search.searchFilteredAssociatedPhotos(photoId, categories, maxMatches);
		assertNotNull(results);
		assertEquals(2, results.size());
		System.out.println("size = " + results.size());
		for(Photo p : results)
			System.out.println(p.getPhotoId());
		
		categories.add("buildingHamerschlagHall");
		results = search.searchFilteredAssociatedPhotos(photoId, categories, maxMatches);
		assertNotNull(results);
		assertEquals(2, results.size());
		System.out.println("size = " + results.size());
		for(Photo p : results)
			System.out.println(p.getPhotoId());
	}

	@Test
	public void testSearchFilteredAssociatedPhotosEmptyResults() {
		int photoId = 1;
		int maxMatches = 3;
		List<String> categories = new ArrayList<String>();
		
		SearchDataRetrieval search = new SearchDataRetrieval(session);
		List<Photo> results = search.searchFilteredAssociatedPhotos(photoId, categories, maxMatches);
		
		assertNotNull(results);
		assertEquals(0, results.size());
	}
	
	@Test
	public void testSearchPhotosByKeyword() {
		int maxMatches = 3;
		List<String> keywords = new ArrayList<String>();
		
		SearchDataRetrieval search = new SearchDataRetrieval(session);
		List<Photo> results = search.searchPhotosByKeyword(keywords, maxMatches);
		
		assertNotNull(results);
	}

	@Test
	public void testSearchPhotosByKeywordEmptyResults() {
		int maxMatches = 3;
		List<String> keywords = new ArrayList<String>();
		
		SearchDataRetrieval search = new SearchDataRetrieval(session);
		List<Photo> results = search.searchPhotosByKeyword(keywords, maxMatches);
		
		assertNotNull(results);
		assertEquals(0, results.size());
	}
	
}
