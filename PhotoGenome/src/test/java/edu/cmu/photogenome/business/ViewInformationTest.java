package edu.cmu.photogenome.business;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.util.HibernateDbUnitTestCase;
import edu.cmu.photogenome.util.HibernateUtil;

public class ViewInformationTest extends HibernateDbUnitTestCase {

	@Test
	public void testGetPhoto() {
		ViewInformation vi = new ViewInformation();
		Photo photo = null;
		photo = vi.getPhoto(1);
		assertNotNull(photo);
		//assertTrue(true);
	}

	@Test
	public void testGetPhotoComments() {
		fail("Not yet implemented");
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
