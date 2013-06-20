package edu.cmu.photogenome.business;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import edu.cmu.photogenome.domain.PhotoCategory;
import edu.cmu.photogenome.domain.PhotoComment;
import edu.cmu.photogenome.domain.RegionCategory;
import edu.cmu.photogenome.util.HibernateDbUnitTestCase;

public class EmbedPhotoTest extends HibernateDbUnitTestCase{

	@Test
	public void testAddPhotoComment() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		PhotoComment photoComment = null;
		photoComment = embedPhoto.addPhotoComment(1, 1000, "testing");
		assertNotNull(photoComment);
		assertEquals(1, photoComment.getPhotoId());
	}

/*	@Test
	public void testAddPhotoCommentNull() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		PhotoComment photoComment = null;
		photoComment = embedPhoto.addPhotoComment(-1, 1000, "testing");
		assertNull(photoComment);
	}*/

	@Test
	public void testAddPhotoCategory() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		PhotoCategory photoCategory = null;

		List<SimpleEntry<String, String>> categoryDetails = new ArrayList<SimpleEntry<String, String>>();
		SimpleEntry<String, String> pair1 = new SimpleEntry<String, String>("Name","ABC");
		SimpleEntry<String, String> pair2 = new SimpleEntry<String, String>("Location","NYC");
		categoryDetails.add(pair1);
		categoryDetails.add(pair2);

		photoCategory = embedPhoto.addPhotoCategory(1, 1000, categoryDetails);

		assertNotNull(photoCategory);
		assertEquals(1, photoCategory.getPhotoId());
	}

	@Test
	public void testAddPhotoCategoryNull() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		PhotoCategory photoCategory = null;
		
		List<SimpleEntry<String, String>> categoryDetails = new ArrayList<SimpleEntry<String, String>>();
		SimpleEntry<String, String> pair1 = new SimpleEntry<String, String>("Name","ABC");
		SimpleEntry<String, String> pair2 = new SimpleEntry<String, String>("Location","NYC");
		categoryDetails.add(pair1);
		categoryDetails.add(pair2);
		
		photoCategory = embedPhoto.addPhotoCategory(-1, 1000, categoryDetails);

		assertNull(photoCategory);

	}

	@Test
	public void testEditPhotoComment() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		PhotoComment photoComment = new PhotoComment();
		photoComment.setPhotoId(1);
		photoComment.setPhotoCommentId(1);
		photoComment.setUserId(1000);
		photoComment.setPhotoCommentTimestamp(new Date());
		photoComment.setPhotoCommentText("test comment");
		assertEquals(true, embedPhoto.editPhotoComment(photoComment));

	}

	@Test
	public void testEditPhotoCommentsFalse() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		PhotoComment photoComment = new PhotoComment();
		photoComment.setPhotoCommentId(-1);
		assertEquals(false, embedPhoto.editPhotoComment(photoComment));
	}

	@Test
	public void testDeletePhotoComment() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		assertEquals(true, embedPhoto.deletePhotoComment(1));
	}

	@Test
	public void testDeletePhotoCommentFalse() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		assertEquals(true, embedPhoto.deletePhotoComment(0));
	}

	@Test
	public void testEditPhotoCategory() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		PhotoCategory category = new PhotoCategory();
		category.setPhotoId(1);
		category.setUserId(1000);
		category.setPhotoCategoryTimestamp(new Date());
		category.setPhotoCategoryId(1);
		category.setPhotoCategoryText("Test Text");
		category.setPhotoCategoryName("Test Name");
		assertEquals(true, embedPhoto.editPhotoCategory(category));
	}

	/*@Test
	public void testEditPhotoCategoryFalse() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		PhotoCategory category = new PhotoCategory();
		//category.setPhotoId(1);
		category.setPhotoCategoryId(-1);
		category.setPhotoCategoryText("Test Text");
		category.setPhotoCategoryName("Test Name");
		assertEquals(false, embedPhoto.editPhotoCategory(category));
	}*/

	@Test
	public void testEditRegionCategory() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		RegionCategory category = new RegionCategory();
		category.setRegionId(1);
		category.setRegionCategoryId(1);
		category.setPhotoId(1);
		category.setUserId(1000);
		category.setRegionCategoryTimestamp(new Date());
		category.setCategoryName("Test Name");
		category.setRegionCategoryText("Test Text");
		assertEquals(true, embedPhoto.editRegionCategory(category));
	}

	@Test
	public void testEditRegionCategoryFalse() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		RegionCategory regionCategory = new RegionCategory();
		regionCategory.setRegionCategoryId(-1);
		assertEquals(false, embedPhoto.editRegionCategory(regionCategory));
	}

	@Test
	public void testDeletePhotoCategory() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		assertEquals(true, embedPhoto.deletePhotoCategory(1));
	}

	@Test
	public void testDeletePhotoCategoryFalse() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		assertEquals(false, embedPhoto.deletePhotoCategory(-1));
	}

}
