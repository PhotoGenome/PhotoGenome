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
	public void testGetPhotoComment() {
		ViewInformation vi = new ViewInformation(session);
		PhotoComment photoComment = null;
		photoComment = vi.getPhotoComment(1);
		assertNotNull(photoComment);
		assertEquals(1, photoComment.getPhotoCommentId().intValue());
	}
	
	@Test
	public void testGetPhotoCommentNull() {
		ViewInformation vi = new ViewInformation(session);
		PhotoComment photoComment = null;
		photoComment = vi.getPhotoComment(-1);
		assertNull(photoComment);
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
	public void testGetPhotoCategory() {
		ViewInformation vi = new ViewInformation(session);
		PhotoCategory photoCategory = null;
		photoCategory = vi.getPhotoCategory(1);
		assertNotNull(photoCategory);
		assertEquals(1, photoCategory.getPhotoCategoryId().intValue());
	}
	
	@Test
	public void testGetPhotoCategoryNull() {
		ViewInformation vi = new ViewInformation(session);
		PhotoCategory photoCategory = null;
		photoCategory = vi.getPhotoCategory(-1);
		assertNull(photoCategory);
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
	public void testGetPhotoRegion() {
		ViewInformation vi = new ViewInformation(session);
		PhotoRegion photoRegion = null;
		photoRegion = vi.getPhotoRegion(1);
		assertNotNull(photoRegion);
		assertEquals(1, photoRegion.getRegionId().intValue());
	}
	
	@Test
	public void testGetPhotoRegionNull() {
		ViewInformation vi = new ViewInformation(session);
		PhotoRegion photoRegion = null;
		photoRegion = vi.getPhotoRegion(-1);
		assertNull(photoRegion);
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
	public void testGetRegionComment() {
		ViewInformation vi = new ViewInformation(session);
		RegionComment regionComment = null;
		regionComment = vi.getRegionComment(1);
		assertNotNull(regionComment);
		assertEquals(1, regionComment.getRegionCommentId().intValue());
	}
	
	@Test
	public void testGetRegionCommentNull() {
		ViewInformation vi = new ViewInformation(session);
		RegionComment regionComment = null;
		regionComment = vi.getRegionComment(-1);
		assertNull(regionComment);
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
	public void testGetRegionCategory() {
		ViewInformation vi = new ViewInformation(session);
		RegionCategory regionCategory = null;
		regionCategory = vi.getRegionCategory(1);
		assertNotNull(regionCategory);
		assertEquals(1, regionCategory.getRegionCategoryId().intValue());
	}
	
	@Test
	public void testGetRegionCategoryNull() {
		ViewInformation vi = new ViewInformation(session);
		RegionCategory regionCategory = null;
		regionCategory = vi.getRegionCategory(-1);
		assertNull(regionCategory);
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
	public void testGetRegionCoordinate() {
		ViewInformation vi = new ViewInformation(session);
		RegionCoordinate regionCoordinate = null;
		regionCoordinate = vi.getRegionCoordinate(1);
		assertNotNull(regionCoordinate);
		assertEquals(1, regionCoordinate.getRegionCoordinateId().intValue());
	}
	
	@Test
	public void testGetRegionCoordinateNull() {
		ViewInformation vi = new ViewInformation(session);
		RegionCoordinate regionCoordinate = null;
		regionCoordinate = vi.getRegionCoordinate(-1);
		assertNull(regionCoordinate);
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
