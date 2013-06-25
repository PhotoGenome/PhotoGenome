package edu.cmu.photogenome.actions;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.StrutsTestCase;
import org.junit.Test;

import com.opensymphony.xwork2.ActionProxy;

import edu.cmu.photogenome.domain.Photo;

public class UploadPhotoActionTest extends StrutsTestCase{

	@Test
	public void testUploadPhoto() throws Exception {
		
		ArrayList<File> listOfFiles = new ArrayList<File>();
		
		File testFile1 = new File("C://test1.jpg");
		File testFile2 = new File("C://test2.jpg");
		
		listOfFiles.add(testFile1);
		listOfFiles.add(testFile2);
		
		request.setParameter("userId", "1000");
		//request.Parameter("fileList", listOfFiles);//TODO cannot add list of files

		ActionProxy proxy = getActionProxy("/uploadPhoto");
		UploadPhotoAction action = (UploadPhotoAction) proxy.getAction();
		String result = proxy.execute();
		Map<String, Object> jsonData = action.getJsonUploadPhoto();
		assertNotNull(jsonData);
		Photo photo = (Photo) jsonData.get("items");
		assertEquals(1, photo.getPhotoId().intValue());
		assertEquals("success", result);
	}

	@Test
	public void testDeletePhoto() {
		fail("Not yet implemented");
	}

}
