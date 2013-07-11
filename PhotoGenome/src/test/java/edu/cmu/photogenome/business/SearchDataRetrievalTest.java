package edu.cmu.photogenome.business;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.util.HibernateDbUnitTestCase;

public class SearchDataRetrievalTest extends HibernateDbUnitTestCase {

	@Test
	public void testSearchFilteredAssociatedPhotos() {
		int photoId = 1;
		int maxMatches = 3;
		List<String> categories = new ArrayList<String>();
		
		SearchDataRetrieval search = new SearchDataRetrieval(session);
		List<Photo> results = search.searchFilteredAssociatedPhotos(photoId, categories, maxMatches);
		
		assertNotNull(results);
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
