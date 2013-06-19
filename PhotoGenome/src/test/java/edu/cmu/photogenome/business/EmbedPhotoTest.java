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
	public void testAddPhotoComments() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		PhotoComment photoComment = null;
		photoComment = embedPhoto.addPhotoComments(1, 1, "testing");
		assertNotNull(photoComment);
		assertEquals(1, photoComment.getPhotoId());
	}

	@Test
	public void testAddPhotoCommentsNull() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		PhotoComment photoComment = null;
		photoComment = embedPhoto.addPhotoComments(0, 0, "testing");
		assertNull(photoComment);
	}

	@Test
	public void testAddPhotoCategories() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		PhotoCategory photoCategory = null;

		List<SimpleEntry<String, String>> categoryDetails = new ArrayList<SimpleEntry<String, String>>();
		SimpleEntry<String, String> pair1 = new SimpleEntry<String, String>("Name","Hamerschlag");
		SimpleEntry<String, String> pair2 = new SimpleEntry<String, String>("Location","Pittsburgh");
		categoryDetails.add(pair1);
		categoryDetails.add(pair2);

		photoCategory = embedPhoto.addPhotoCategories(1, 1, categoryDetails);

		assertNotNull(photoCategory);
		assertEquals(1, photoCategory.getPhotoId());
	}

	@Test
	public void testAddPhotoCategoriesNull() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		PhotoCategory photoCategory = null;
		photoCategory = embedPhoto.addPhotoCategories(1, 1, null);

		assertNull(photoCategory);

	}

	@Test
	public void testEditPhotoComments() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		PhotoComment photoComment = new PhotoComment();
		photoComment.setPhotoCommentTimestamp(new Date());
		photoComment.setPhotoCommentText("test comment");
		assertEquals(true, embedPhoto.editPhotoComments(photoComment));

	}

	@Test
	public void testEditPhotoCommentsFalse() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		PhotoComment photoComment = null;
		assertEquals(false, embedPhoto.editPhotoComments(photoComment));
	}

	@Test
	public void testDeletePhotoComments() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		assertEquals(true, embedPhoto.deletePhotoComments(1));
	}

	@Test
	public void testDeletePhotoCommentsFalse() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		assertEquals(true, embedPhoto.deletePhotoComments(0));
	}

	@Test
	public void testEditPhotoCategories() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		PhotoCategory category = new PhotoCategory();
		//category.setPhotoId(1);
		category.setPhotoCategoryId(1);
		category.setPhotoCategoryText("Test Text");
		category.setPhotoCategoryName("Test Name");
		assertEquals(true, embedPhoto.editPhotoCategories(category));
	}

	@Test
	public void testEditPhotoCategoriesFalse() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		PhotoCategory category = new PhotoCategory();
		//category.setPhotoId(1);
		category.setPhotoCategoryId(-1);
		category.setPhotoCategoryText("Test Text");
		category.setPhotoCategoryName("Test Name");
		assertEquals(false, embedPhoto.editPhotoCategories(category));
	}

	@Test
	public void testEditRegionCategories() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		RegionCategory category = new RegionCategory();
		category.setRegionCategoryId(1);
		category.setCategoryName("Test Name");
		category.setRegionCategoryText("Test Text");
		assertEquals(true, embedPhoto.editRegionCategories(category));
	}

	@Test
	public void testEditRegionCategoriesFalse() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		assertEquals(false, embedPhoto.editRegionCategories(null));
	}

	@Test
	public void testDeletePhotoCategories() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		assertEquals(true, embedPhoto.deletePhotoCategories(1));
	}

	@Test
	public void testDeletePhotoCategoriesFalse() {
		EmbedPhoto embedPhoto = new EmbedPhoto(session);
		assertEquals(false, embedPhoto.deletePhotoCategories(-1));
	}

}
