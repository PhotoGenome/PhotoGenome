package edu.cmu.photogenome.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.List;

import org.hibernate.Session;
import org.junit.Test;

import edu.cmu.photogenome.dao.ImportedMetadataDao;
import edu.cmu.photogenome.dao.ImportedMetadataDaoImpl;
import edu.cmu.photogenome.dao.PhotoDao;
import edu.cmu.photogenome.dao.PhotoDaoImpl;
import edu.cmu.photogenome.domain.ImportedMetadata;
import edu.cmu.photogenome.domain.Photo;
import edu.cmu.photogenome.util.HibernateDbUnitTestCase;
import edu.cmu.photogenome.util.HibernateUtil;

public class ImportMetadataAndPhotoTest extends HibernateDbUnitTestCase {

	@Test
	public void testImportMetadataAndPhoto() throws Exception {
		int userId = 1002;
		File dir = new File(this.getClass().getClassLoader().getResource("imported_images").toURI());
		
		ImportMetadataAndPhoto importMetadata = new ImportMetadataAndPhoto(session);
		importMetadata.getPhotoAndMetadata(dir.toString(), userId);
		
		PhotoDao photoDao = new PhotoDaoImpl();
		photoDao.setSession(session);
		List<Photo> photos = photoDao.findAllByCriteria("userId", userId);
		assertNotNull(photos);
		assertEquals(2, photos.size());
		
		ImportedMetadataDao importDao = new ImportedMetadataDaoImpl();
		importDao.setSession(session);
		List<ImportedMetadata> importDataList = importDao.findAllByCriteria("photoId", photos.get(0).getPhotoId());
		importDataList.addAll(importDao.findAllByCriteria("photoId", photos.get(1).getPhotoId()));
		for(ImportedMetadata importData : importDataList) {
			System.out.println(importData.getImportedMetadata());
			assertNotNull(importData.getImportedMetadata());
		}
		
	}

}
