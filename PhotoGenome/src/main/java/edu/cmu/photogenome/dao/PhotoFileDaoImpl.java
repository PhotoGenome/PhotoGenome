package edu.cmu.photogenome.dao;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.cmu.photogenome.domain.PhotoFile;

public class PhotoFileDaoImpl implements PhotoFileDao {

	public boolean delete(PhotoFile photo) {
		File file = new File(photo.getPhotoLink());
		
		if(file.exists()) {
			if(!file.delete())
				return false;
		}
		
		return true;
	}

	public PhotoFile retrievePhoto(String location) {
		PhotoFile photo = null;
		
		Image image = null;
		try {
			image = ImageIO.read(new File(location));
			photo = new PhotoFile();
			photo.setImage(image);
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return photo;
	}

	public boolean save(PhotoFile photo) {
		try {
			ImageIO.write((BufferedImage)photo.getImage(), photo.getFileExt(), new File(photo.getPhotoLink()));
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
		
		return true;
	}

}
