package edu.cmu.photogenome.domain;

import java.awt.Image;

public class PhotoFile {

	private Integer photoId;
	private String photoLink;
	private Image image;
	private String fileExt;

	public PhotoFile() {
		
	}
	
	public PhotoFile(Integer photoId, String photoLink, String fileExt) {
		this.photoId = photoId;
		this.photoLink = photoLink;
		this.fileExt = fileExt;
	}

	public Integer getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

	public String getPhotoLink() {
		return photoLink;
	}

	public void setPhotoLink(String photoLink) {
		this.photoLink = photoLink;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
