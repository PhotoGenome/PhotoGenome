package edu.cmu.photogenome.business;

import org.junit.Test;

import edu.cmu.photogenome.domain.PhotoRegion;
import edu.cmu.photogenome.domain.RegionComment;
import edu.cmu.photogenome.util.HibernateDbUnitTestCase;

public class EmbedRegionTest extends HibernateDbUnitTestCase {

	@Test
	public void testAddPhotoRegion() {
		EmbedRegion embedRegion = new EmbedRegion(session);
		PhotoRegion photoRegion = null;
		photoRegion = embedRegion.addPhotoRegion(1, 1001, 1, 45, 55, 10, 10);
		assertNotNull(photoRegion);
		assertEquals(1, photoRegion.getPhotoId());
	}

	@Test
	public void testAddPhotoRegionNull() {
		EmbedRegion embedRegion = new EmbedRegion(session);
		PhotoRegion photoRegion = null;
		photoRegion = embedRegion.addPhotoRegion(-1, 1001, 1, 45, 55, 10, 10);
		assertNull(photoRegion);
		//assertEquals(1, photoRegion.getPhotoId());
	}

	@Test
	public void testAddRegionComment() {
		EmbedRegion embedRegion = new EmbedRegion(session);
		RegionComment regionComment = null;
		regionComment = embedRegion.addRegionComment(1, 1001, 1, "test comment");
		assertNotNull(regionComment);
		assertEquals(1, regionComment.getPhotoId());
	}

	@Test
	public void testAddRegionCommentNull() {
		EmbedRegion embedRegion = new EmbedRegion(session);
		RegionComment regionComment = null;
		regionComment = embedRegion.addRegionComment(-1, 1001, 1, "test comment");
		assertNull(regionComment);
	}

	@Test
	public void testEditRegionComment() {
		EmbedRegion embedRegion = new EmbedRegion(session);
		RegionComment regionComment = new RegionComment();
		regionComment.setPhotoId(1);
		regionComment.setRegionCommentId(1);
		regionComment.setRegionCommentText("test comment");
		assertEquals(true, embedRegion.editRegionComment(regionComment));
	}

	@Test
	public void testEditRegionCommentFalse() {
		EmbedRegion embedRegion = new EmbedRegion(session);
		RegionComment regionComment = null;
		assertEquals(false, embedRegion.editRegionComment(regionComment));
	}
	
	@Test
	public void testEditRegionCoordinate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletePhotoRegion() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteRegionComment() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteRegionCoordinate() {
		fail("Not yet implemented");
	}

}
