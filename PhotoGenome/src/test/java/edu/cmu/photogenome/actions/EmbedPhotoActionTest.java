package edu.cmu.photogenome.actions;

import java.util.Map;

import org.apache.struts2.StrutsTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.ActionProxy;

import edu.cmu.photogenome.domain.PhotoCategory;
import edu.cmu.photogenome.domain.PhotoComment;

public class EmbedPhotoActionTest extends StrutsTestCase {

	@Test
	public void testAddPhotoComments() throws Exception{
		request.setParameter("photoId", "1");
		request.setParameter("userId", "1000");
		request.setParameter("photoCommentText", "test comment");

		ActionProxy proxy = getActionProxy("/addPhotoComments");
		EmbedPhotoAction action = (EmbedPhotoAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = action.getJsonAddPhotoComments();
		assertNotNull(jsonData);
		PhotoComment photoComment = (PhotoComment) jsonData.get("items");
		assertEquals(1, photoComment.getPhotoId());
		assertEquals("success", result);
	}

	@Test
	public void testAddPhotoCommentsNull() throws Exception {
		request.setParameter("photoId", "-1");

		ActionProxy proxy = getActionProxy("/addPhotoComments");
		//EmbedPhotoAction action = (EmbedPhotoAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals("error", result);
	}

	@Test
	public void testAddPhotoCategories() throws Exception {		
		request.setParameter("photoId", "1");
		request.setParameter("userId", "1000");
		request.setParameter("photoCategoryName", "test category name");
		request.setParameter("photoCategoryText", "test category text");		

		ActionProxy proxy = getActionProxy("/addPhotoCategories");
		EmbedPhotoAction action = (EmbedPhotoAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = action.getJsonAddPhotoCategories();
		assertNotNull(jsonData);
		PhotoCategory photoCategory = (PhotoCategory) jsonData.get("items");
		assertEquals(1, photoCategory.getPhotoId());
		assertEquals("success", result);
	}

	@Test
	public void testAddPhotoCategoriesNull() throws Exception {
		request.setParameter("photoId", "-1");

		ActionProxy proxy = getActionProxy("/addPhotoCategories");
		//EmbedPhotoAction action = (EmbedPhotoAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals("error", result);
	}

	@Test
	public void testEditPhotoComments() throws Exception {

		request.setParameter("photoCommentId", "1");
		request.setParameter("photoCommentText", "test action comment");

		ActionProxy proxy = getActionProxy("/editPhotoComments");
		//EmbedRegionAction action = (EmbedRegionAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals("success", result);
	}

	@Test
	public void testEditPhotoCategories() throws Exception {
		request.setParameter("photoId", "1");
		request.setParameter("photoCategoryName", "test category name");
		request.setParameter("photoCategoryText", "test category text");

		ActionProxy proxy = getActionProxy("/editPhotoCategories");
		//EmbedRegionAction action = (EmbedRegionAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals("success", result);
	}

	@Test
	public void testEditRegionCategories() throws Exception {
		request.setParameter("regionCategoryId", "1");
		request.setParameter("categoryName", "test category name");
		request.setParameter("regionCategoryText", "test category text");

		ActionProxy proxy = getActionProxy("/editRegionCategories");
		//EmbedRegionAction action = (EmbedRegionAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals("success", result);
	}

	@Test
	public void testDeletePhotoComments() throws Exception {
		request.setParameter("photoCommentId", "1");

		ActionProxy proxy = getActionProxy("/deletePhotoComments");
		//EmbedRegionAction action = (EmbedRegionAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals("success", result);
	}

	@Test
	public void testDeletePhotoCategories() throws Exception {
		request.setParameter("photoCategoryId", "1");

		ActionProxy proxy = getActionProxy("/deletePhotoCategories");
		//EmbedRegionAction action = (EmbedRegionAction) proxy.getAction();
		String result = proxy.execute();
		assertEquals("success", result);
	}

}
