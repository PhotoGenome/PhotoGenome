package edu.cmu.photogenome.business;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.domain.PhotoComment;
import edu.cmu.photogenome.util.HibernateDbUnitTestCase;
import edu.cmu.photogenome.util.HibernateUtil;

public class ViewInformationTest extends HibernateDbUnitTestCase {

	@Test
	public void testGetPhoto() {
		ViewInformation vi = new ViewInformation();
		Photo photo = null;
		photo = vi.getPhoto(1);
		assertNotNull(photo);
		assertEquals(1, photo.getPhotoId().intValue());
	}

	@Test
	public void testGetPhotoNull() {
		ViewInformation vi = new ViewInformation();
		Photo photo = null;
		photo = vi.getPhoto(-1);
		assertNull(photo);
	}
	
	@Test
	public void testGetPhotoComments() {
		ViewInformation vi = new ViewInformation();
		List<PhotoComment> commentsList = null;
		commentsList = (ArrayList<PhotoComment>) vi.getPhotoComments(1);
		assertNotNull(commentsList);
		assertEquals(1, commentsList.size());
		assertEquals(1, commentsList.get(0).getPhotoCommentId().intValue());
	}

	@Test
	public void testGetPhotoCommentsNull() {
		ViewInformation vi = new ViewInformation();
		List<PhotoComment> commentsList = null;
		commentsList = (ArrayList<PhotoComment>) vi.getPhotoComments(-1);
		assertNotNull(commentsList);
		assertEquals(0, commentsList.size());
	}
	
	@Test
	public void testGetPhotoCategories() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPhotoRegions() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRegionComments() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRegionCategories() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRegionCoordinates() {
		fail("Not yet implemented");
	}

}
