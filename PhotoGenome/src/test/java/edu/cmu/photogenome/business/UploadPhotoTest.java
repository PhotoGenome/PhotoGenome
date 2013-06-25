package edu.cmu.photogenome.business;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.util.HibernateDbUnitTestCase;

public class UploadPhotoTest extends HibernateDbUnitTestCase {

	 @Rule
	 public TemporaryFolder testFolder = new TemporaryFolder();
	
	/**
	 * Test uploading a photo, which includes saving the file and creating a database record
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSavePhoto() throws Exception {
		int userId = 1000;
		ArrayList<String> nameList = new ArrayList<String>();
		nameList.add("test image 1");
		nameList.add("test image 2");
		List<File> inList = new ArrayList<File>();
		for(String name : nameList)
			inList.add(new File(this.getClass().getClassLoader().getResource("images/" + name + ".jpg").toURI()));
		
		UploadPhoto upload = new UploadPhoto(session);
		List<Photo> outList = null;
		outList = upload.savePhoto(userId, inList);
		assertNotNull(outList);
		for(int i = 0; i < outList.size(); i++) {
			Photo photo = outList.get(i);
			assertEquals(userId, photo.getUserId());
			assertEquals(nameList.get(i), photo.getPhotoName());
			String photoLink = String.valueOf(photo.getPhotoTimestamp().getTime()) + String.valueOf(photo.getPhotoId() + ".jpg");
			assertEquals(photoLink, photo.getPhotoLink());
		}
	}
	
}
