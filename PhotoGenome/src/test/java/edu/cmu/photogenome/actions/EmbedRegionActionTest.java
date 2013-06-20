package edu.cmu.photogenome.actions;

import java.util.Map;

import org.apache.struts2.StrutsTestCase;
import org.apache.struts2.util.StrutsTestCaseHelper;
import org.junit.Test;

import com.opensymphony.xwork2.ActionProxy;

import edu.cmu.photogenome.domain.PhotoRegion;
import edu.cmu.photogenome.domain.RegionComment;

public class EmbedRegionActionTest extends StrutsTestCase {

	@Test
	public void testAddPhotoRegion() throws Exception {
		request.setParameter("photoId", "1");
		request.setParameter("userId", "1000");
		request.setParameter("shapeId", "1");
		request.setParameter("regionX", "0");
		request.setParameter("regionY", "0");
		request.setParameter("height", "10");
		request.setParameter("width", "10");
		
		ActionProxy proxy = getActionProxy("/addPhotoRegion");
		EmbedRegionAction action = (EmbedRegionAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = action.getJsonAddPhotoRegion();
		assertNotNull(jsonData);
		PhotoRegion photoRegion = (PhotoRegion) jsonData.get("items");
		assertEquals(1, photoRegion.getPhotoId());
		//assertEquals("success", result);
	}

	@Test
	public void testAddPhotoRegionNull() throws Exception {
		request.setParameter("photoId", "-1");
		
		ActionProxy proxy = getActionProxy("/addPhotoRegion");
		EmbedRegionAction action = (EmbedRegionAction) proxy.getAction();
		String result = proxy.execute();
		//assertEquals("error", result);
	}
	
	@Test
	public void testAddRegionComment() throws Exception {
		request.setParameter("photoId", "1");
		request.setParameter("userId", "1000");
		request.setParameter("regionId", "1");
		request.setParameter("regionCommentText", "SAMPLE TEXT");
		
		ActionProxy proxy = getActionProxy("/addRegionComment");
		EmbedRegionAction action = (EmbedRegionAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = action.getJsonAddRegionComment();
		assertNotNull(jsonData);
		RegionComment regionComment = (RegionComment) jsonData.get("items");
		assertEquals(1, regionComment.getPhotoId());
		//assertEquals("success", result);
	}

	@Test
	public void testAddRegionCommentNull() throws Exception {
		request.setParameter("regionId", "-1");
		
		ActionProxy proxy = getActionProxy("/addRegionComment");
		EmbedRegionAction action = (EmbedRegionAction) proxy.getAction();
		String result = proxy.execute();
		//assertEquals("error", result);
	}
	
	@Test
	public void testDeletePhotoRegion() throws Exception {
		request.setParameter("regionId", "1");
		
		ActionProxy proxy = getActionProxy("/deletePhotoRegion");
		EmbedRegionAction action = (EmbedRegionAction) proxy.getAction();
		String result = proxy.execute();
		//assertEquals("success", result);
	}

	@Test
	public void testDeleteRegionComment() throws Exception {
		request.setParameter("regionCommentId", "1");
		
		ActionProxy proxy = getActionProxy("/deleteRegionComment");
		EmbedRegionAction action = (EmbedRegionAction) proxy.getAction();
		String result = proxy.execute();
		//assertEquals("success", result);
	}

	@Test
	public void testDeleteRegionCoordinate() throws Exception {
		request.setParameter("regionCoordinateId", "1");
		
		ActionProxy proxy = getActionProxy("/deleteRegionCoordinate");
		EmbedRegionAction action = (EmbedRegionAction) proxy.getAction();
		String result = proxy.execute();
		//assertEquals("success", result);
	}

	@Test
	public void testEditRegionComment() throws Exception {
		request.setParameter("regionCommentId", "1");
		request.setParameter("regionCommentText", "SAMPLE TEXT");
		
		ActionProxy proxy = getActionProxy("/editRegionComment");
		EmbedRegionAction action = (EmbedRegionAction) proxy.getAction();
		String result = proxy.execute();
		//assertEquals("success", result);
	}

	@Test
	public void testEditRegionCommentNotExist() throws Exception {
		request.setParameter("regionCommentId", "-1");
		request.setParameter("regionCommentText", "SAMPLE TEXT");
		
		ActionProxy proxy = getActionProxy("/editRegionComment");
		EmbedRegionAction action = (EmbedRegionAction) proxy.getAction();
		String result = proxy.execute();
		//assertEquals("error", result);
	}
	
	@Test
	public void testEditRegionCoordinate() throws Exception {
		request.setParameter("regionCoordinateId", "1");
		request.setParameter("regionX", "0");
		request.setParameter("regionY", "0");
		request.setParameter("height", "10");
		request.setParameter("width", "10");
		
		ActionProxy proxy = getActionProxy("/editRegionCoordinate");
		EmbedRegionAction action = (EmbedRegionAction) proxy.getAction();
		String result = proxy.execute();
		//assertEquals("success", result);
	}

	@Test
	public void testEditRegionCoordinateNotExist() throws Exception {
		request.setParameter("regionCoordinateId", "-1");
		request.setParameter("regionX", "0");
		request.setParameter("regionY", "0");
		request.setParameter("height", "10");
		request.setParameter("width", "10");
		
		ActionProxy proxy = getActionProxy("/editRegionCoordinate");
		EmbedRegionAction action = (EmbedRegionAction) proxy.getAction();
		String result = proxy.execute();
		//assertEquals("error", result);
	}
	
}
