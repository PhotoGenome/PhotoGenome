package edu.cmu.photogenome.business;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.domain.PhotoCategory;
import edu.cmu.photogenome.domain.PhotoComment;
import edu.cmu.photogenome.domain.PhotoRegion;
import edu.cmu.photogenome.domain.RegionCategory;
import edu.cmu.photogenome.domain.RegionComment;
import edu.cmu.photogenome.domain.RegionCoordinate;
import edu.cmu.photogenome.util.HibernateDbUnitTestCase;

/**
 *	JUnit test case for View Information business logic
 */
public class ViewInformationTest extends HibernateDbUnitTestCase {

	@Test
	public void testGetPhoto() {
		ViewInformation vi = new ViewInformation(session);
		Photo photo = null;
		photo = vi.getPhoto(1);
		assertNotNull(photo);
		assertEquals(1, photo.getPhotoId().intValue());
	}

	@Test
	public void testGetPhotoNull() {
		ViewInformation vi = new ViewInformation(session);
		Photo photo = null;
		photo = vi.getPhoto(-1);
		assertNull(photo);
	}
	
	@Test
	public void testGetPhotoComments() {
		ViewInformation vi = new ViewInformation(session);
		List<PhotoComment> list = null;
		list = (ArrayList<PhotoComment>) vi.getPhotoComments(1);
		assertNotNull(list);
		assertEquals(1, list.size());
		assertEquals(1, list.get(0).getPhotoCommentId().intValue());
	}

	@Test
	public void testGetPhotoCommentsNull() {
		ViewInformation vi = new ViewInformation(session);
		List<PhotoComment> list = null;
		list = (ArrayList<PhotoComment>) vi.getPhotoComments(-1);
		assertNotNull(list);
		assertEquals(0, list.size());
	}
	
	@Test
	public void testGetPhotoCategories() {
		ViewInformation vi = new ViewInformation(session);
		List<PhotoCategory> list = null;
		list = (ArrayList<PhotoCategory>) vi.getPhotoCategories(1);
		assertNotNull(list);
		assertEquals(2, list.size());
		assertEquals(1, list.get(0).getPhotoCategoryId().intValue());
	}

	@Test
	public void testGetPhotoCategoriesNull() {
		ViewInformation vi = new ViewInformation(session);
		List<PhotoCategory> list = null;
		list = (ArrayList<PhotoCategory>) vi.getPhotoCategories(-1);
		assertNotNull(list);
		assertEquals(0, list.size());
	}
	
	@Test
	public void testGetPhotoRegions() {
		ViewInformation vi = new ViewInformation(session);
		List<PhotoRegion> list = null;
		list = (ArrayList<PhotoRegion>) vi.getPhotoRegions(1);
		assertNotNull(list);
		assertEquals(2, list.size());
		assertEquals(1, list.get(0).getRegionId().intValue());
	}

	@Test
	public void testGetPhotoRegionsNull() {
		ViewInformation vi = new ViewInformation(session);
		List<PhotoRegion> list = null;
		list = (ArrayList<PhotoRegion>) vi.getPhotoRegions(-1);
		assertNotNull(list);
		assertEquals(0, list.size());
	}
	
	@Test
	public void testGetRegionComments() {
		ViewInformation vi = new ViewInformation(session);
		List<RegionComment> list = null;
		list = (ArrayList<RegionComment>) vi.getRegionComments(1);
		assertNotNull(list);
		assertEquals(1, list.size());
		assertEquals(1, list.get(0).getRegionCommentId().intValue());
	}

	@Test
	public void testGetRegionCommentsNull() {
		ViewInformation vi = new ViewInformation(session);
		List<RegionComment> list = null;
		list = (ArrayList<RegionComment>) vi.getRegionComments(-1);
		assertNotNull(list);
		assertEquals(0, list.size());
	}

	@Test
	public void testGetRegionCategories() {
		ViewInformation vi = new ViewInformation(session);
		List<RegionCategory> list = null;
		list = (ArrayList<RegionCategory>) vi.getRegionCategories(1);
		assertNotNull(list);
		assertEquals(1, list.size());
		assertEquals(1, list.get(0).getRegionCategoryId().intValue());
	}

	@Test
	public void testGetRegionCategoriesNull() {
		ViewInformation vi = new ViewInformation(session);
		List<RegionCategory> list = null;
		list = (ArrayList<RegionCategory>) vi.getRegionCategories(-1);
		assertNotNull(list);
		assertEquals(0, list.size());
	}

	@Test
	public void testGetRegionCoordinates() {
		ViewInformation vi = new ViewInformation(session);
		List<RegionCoordinate> list = null;
		list = (ArrayList<RegionCoordinate>) vi.getRegionCoordinates(1);
		assertNotNull(list);
		assertEquals(1, list.size());
		assertEquals(1, list.get(0).getRegionCoordinateId().intValue());
	}

	@Test
	public void testGetRegionCoordinatesNull() {
		ViewInformation vi = new ViewInformation(session);
		List<RegionCoordinate> list = null;
		list = (ArrayList<RegionCoordinate>) vi.getRegionCoordinates(-1);
		assertNotNull(list);
		assertEquals(0, list.size());
	}
	
}
