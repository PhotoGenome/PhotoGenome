package edu.cmu.photogenome.actions;

import java.util.List;
import java.util.Map;

import org.apache.struts2.StrutsTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.ActionProxy;

import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.domain.PhotoCategory;
import edu.cmu.photogenome.domain.PhotoComment;
import edu.cmu.photogenome.domain.PhotoRegion;
import edu.cmu.photogenome.domain.RegionCategory;
import edu.cmu.photogenome.domain.RegionComment;
import edu.cmu.photogenome.domain.RegionCoordinate;

public class ViewInformationActionTest extends StrutsTestCase {
	
	/**
	 * Test getting a photo
	 * @throws Exception
	 */
	@Test
	public void testGetPhoto() throws Exception {
		request.setParameter("photoId", "1");
		ActionProxy proxy = getActionProxy("/getPhoto");
		ViewInformationAction viewInfoAction = (ViewInformationAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = viewInfoAction.getJsonGetPhoto();
		assertNotNull(jsonData);
		List<Photo> list = (List<Photo>) jsonData.get("items");
		assertEquals(1, list.size());
		Photo photo = list.get(0);
		assertEquals(1, photo.getPhotoId().intValue());
		assertEquals("success", result);
	}
	
	/**
	 * Test getting non-existent photo
	 * @throws Exception
	 */
	@Test
	public void testGetPhotoEmpty() throws Exception {
		request.setParameter("photoId", "-1");
		ActionProxy proxy = getActionProxy("/getPhoto");
		ViewInformationAction viewInfoAction = (ViewInformationAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = viewInfoAction.getJsonGetPhoto();
		assertNotNull(jsonData);
		List list = (List) jsonData.get("items");
		assertEquals(0, list.size());
		assertEquals("success", result);
	}
	
	/**
	 * Test getting list of photo comments
	 * @throws Exception
	 */
	@Test
	public void testGetPhotoComments() throws Exception {
		request.setParameter("photoId", "1");
		ActionProxy proxy = getActionProxy("/getPhotoComments");
		ViewInformationAction viewInfoAction = (ViewInformationAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = viewInfoAction.getJsonGetPhotoComments();
		assertNotNull(jsonData);
		List<PhotoComment> list = (List<PhotoComment>) jsonData.get("items");
		assertEquals(1, list.size());
		for(PhotoComment p : list)
			assertEquals(1, p.getPhotoId());
		assertEquals("success", result);
	}

	/**
	 * Test getting empty list of photo comments
	 * @throws Exception
	 */
	@Test
	public void testGetPhotoCommentsEmpty() throws Exception {
		request.setParameter("photoId", "-1");
		ActionProxy proxy = getActionProxy("/getPhotoComments");
		ViewInformationAction viewInfoAction = (ViewInformationAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = viewInfoAction.getJsonGetPhotoComments();
		assertNotNull(jsonData);
		List list = (List) jsonData.get("items");
		assertEquals(0, list.size());
		assertEquals("success", result);
	}
	
	/**
	 * Test getting photo categories
	 * @throws Exception
	 */
	@Test
	public void testGetPhotoCategories() throws Exception {
		request.setParameter("photoId", "1");
		ActionProxy proxy = getActionProxy("/getPhotoCategories");
		ViewInformationAction viewInfoAction = (ViewInformationAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = viewInfoAction.getJsonGetPhotoCategories();
		assertNotNull(jsonData);
		List<PhotoCategory> list = (List<PhotoCategory>) jsonData.get("items");
		assertEquals(2, list.size());
		for(PhotoCategory p : list)
			assertEquals(1, p.getPhotoId());
		assertEquals("success", result);
	}

	/**
	 * Test getting empty list of photo categories
	 * @throws Exception
	 */
	@Test
	public void testGetPhotoCategoriesEmpty() throws Exception {
		request.setParameter("photoId", "-1");
		ActionProxy proxy = getActionProxy("/getPhotoCategories");
		ViewInformationAction viewInfoAction = (ViewInformationAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = viewInfoAction.getJsonGetPhotoCategories();
		assertNotNull(jsonData);
		List list = (List) jsonData.get("items");
		assertEquals(0, list.size());
		assertEquals("success", result);
	}
	
	/**
	 * Test getting photo regions
	 * @throws Exception
	 */
	@Test
	public void testGetPhotoRegions() throws Exception {
		request.setParameter("photoId", "1");
		ActionProxy proxy = getActionProxy("/getPhotoRegions");
		ViewInformationAction viewInfoAction = (ViewInformationAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = viewInfoAction.getJsonGetPhotoRegions();
		assertNotNull(jsonData);
		List<PhotoRegion> list = (List<PhotoRegion>) jsonData.get("items");
		assertEquals(2, list.size());
		for(PhotoRegion p : list)
			assertEquals(1, p.getPhotoId());
		assertEquals("success", result);
	}

	/**
	 * Test getting empty list of photo regions
	 * @throws Exception
	 */
	@Test
	public void testGetPhotoRegionsEmpty() throws Exception {
		request.setParameter("photoId", "-1");
		ActionProxy proxy = getActionProxy("/getPhotoRegions");
		ViewInformationAction viewInfoAction = (ViewInformationAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = viewInfoAction.getJsonGetPhotoRegions();
		assertNotNull(jsonData);
		List list = (List) jsonData.get("items");
		assertEquals(0, list.size());
		assertEquals("success", result);
	}
	
	/**
	 * Test getting region comments
	 * @throws Exception
	 */
	@Test
	public void testGetRegionComments() throws Exception {
		request.setParameter("regionId", "1");
		ActionProxy proxy = getActionProxy("/getRegionComments");
		ViewInformationAction viewInfoAction = (ViewInformationAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = viewInfoAction.getJsonGetRegionComments();
		assertNotNull(jsonData);
		List<RegionComment> list = (List<RegionComment>) jsonData.get("items");
		assertEquals(1, list.size());
		for(RegionComment r : list)
			assertEquals(1, r.getRegionId());
		assertEquals("success", result);
	}

	/**
	 * Test getting empty list of region comments
	 * @throws Exception
	 */
	@Test
	public void testGetRegionCommentsEmpty() throws Exception {
		request.setParameter("regionId", "-1");
		ActionProxy proxy = getActionProxy("/getRegionComments");
		ViewInformationAction viewInfoAction = (ViewInformationAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = viewInfoAction.getJsonGetRegionComments();
		assertNotNull(jsonData);
		List list = (List) jsonData.get("items");
		assertEquals(0, list.size());
		assertEquals("success", result);
	}
	
	/**
	 * Testing getting region categories
	 * @throws Exception
	 */
	@Test
	public void testGetRegionCategories() throws Exception {
		request.setParameter("regionId", "1");
		ActionProxy proxy = getActionProxy("/getRegionCategories");
		ViewInformationAction viewInfoAction = (ViewInformationAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = viewInfoAction.getJsonGetRegionCategories();
		assertNotNull(jsonData);
		List<RegionCategory> list = (List<RegionCategory>) jsonData.get("items");
		assertEquals(1, list.size());
		for(RegionCategory r : list)
			assertEquals(1, r.getRegionId());
		assertEquals("success", result);
	}

	/**
	 * Test getting empty list of region categories
	 * @throws Exception
	 */
	@Test
	public void testGetRegionCategoriesEmpty() throws Exception {
		request.setParameter("regionId", "-1");
		ActionProxy proxy = getActionProxy("/getRegionCategories");
		ViewInformationAction viewInfoAction = (ViewInformationAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = viewInfoAction.getJsonGetRegionCategories();
		assertNotNull(jsonData);
		List list = (List) jsonData.get("items");
		assertEquals(0, list.size());
		assertEquals("success", result);
	}
	
	/**
	 * Test getting region coordinates
	 * @throws Exception
	 */
	@Test
	public void testGetRegionCoordinates() throws Exception {
		request.setParameter("photoId", "1");
		ActionProxy proxy = getActionProxy("/getRegionCoordinates");
		ViewInformationAction viewInfoAction = (ViewInformationAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = viewInfoAction.getJsonGetRegionCoordinates();
		assertNotNull(jsonData);
		List<RegionCoordinate> list = (List<RegionCoordinate>) jsonData.get("items");
		assertEquals(2, list.size());
		for(RegionCoordinate r : list)
			assertEquals(1, r.getPhotoId());
		assertEquals("success", result);
	}

	/**
	 * Test getting empty list of region coordinates
	 * @throws Exception
	 */
	@Test
	public void testGetRegionCoordinatesEmpty() throws Exception {
		request.setParameter("photoId", "-1");
		ActionProxy proxy = getActionProxy("/getRegionCoordinates");
		ViewInformationAction viewInfoAction = (ViewInformationAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = viewInfoAction.getJsonGetRegionCoordinates();
		assertNotNull(jsonData);
		List list = (List) jsonData.get("items");
		assertEquals(0, list.size());
		assertEquals("success", result);
	}
	
}
