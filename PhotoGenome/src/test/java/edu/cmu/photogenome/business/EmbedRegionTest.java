package edu.cmu.photogenome.business;

import java.util.Date;

import org.junit.Test;

import edu.cmu.photogenome.domain.PhotoRegion;
import edu.cmu.photogenome.domain.RegionComment;
import edu.cmu.photogenome.domain.RegionCoordinate;
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
		regionComment.setRegionCommentTimestamp(new Date());
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
		EmbedRegion embedRegion = new EmbedRegion(session);
		RegionCoordinate coordinate = new RegionCoordinate();
		coordinate.setPhotoId(1);
		coordinate.setRegionCoordinateTimestamp(new Date());
		coordinate.setRegionCoordinateId(1);
		coordinate.setRegionId(1);
		coordinate.setRegionX(15);
		coordinate.setRegionY(25);
		coordinate.setUserId(1001);
		assertEquals(true, embedRegion.editRegionCoordinate(coordinate));
	}

	@Test
	public void testEditRegionCoordinateFalse() {
		EmbedRegion embedRegion = new EmbedRegion(session);
		RegionCoordinate coordinate = new RegionCoordinate();
		coordinate.setRegionCoordinateId(-1);
		assertEquals(false, embedRegion.editRegionCoordinate(coordinate));
	}

	@Test
	public void testDeletePhotoRegion() {
		EmbedRegion embedRegion = new EmbedRegion(session);
		assertEquals(true, embedRegion.deletePhotoRegion(1));
	}

	@Test
	public void testDeletePhotoRegionFalse() {
		EmbedRegion embedRegion = new EmbedRegion(session);
		assertEquals(false, embedRegion.deletePhotoRegion(-1));
	}

	@Test
	public void testDeleteRegionComment() {
		EmbedRegion embedRegion = new EmbedRegion(session);
		assertEquals(true, embedRegion.deleteRegionComment(1));
	}

	@Test
	public void testDeleteRegionCommentFalse() {
		EmbedRegion embedRegion = new EmbedRegion(session);
		assertEquals(true, embedRegion.deleteRegionComment(-1));
	}

	@Test
	public void testDeleteRegionCoordinate() {
		EmbedRegion embedRegion = new EmbedRegion(session);
		assertEquals(true, embedRegion.deleteRegionCoordinate(1));
	}
	
	@Test
	public void testDeleteRegionCoordinateFalse() {
		EmbedRegion embedRegion = new EmbedRegion(session);
		assertEquals(false, embedRegion.deleteRegionCoordinate(-1));
	}

}
