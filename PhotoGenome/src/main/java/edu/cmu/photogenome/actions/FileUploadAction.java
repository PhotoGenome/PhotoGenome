package edu.cmu.photogenome.actions;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.opensymphony.xwork2.ActionSupport;

public class FileUploadAction extends ActionSupport{

	private File fileUpload;
	private String fileUploadContentType;
	private String fileUploadFileName;

	public String getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String execute() throws Exception{
		try {
            String filePath = "C:\\Users\\apoorvijain\\git\\PhotoGenome\\PhotoGenome\\Images\\";
            System.out.println("Server path:" + filePath);
            File fileToCreate = new File(filePath, this.fileUploadFileName);
 
            FileUtils.copyFile(this.fileUpload, fileToCreate);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
 
            return INPUT;
        }
		return SUCCESS;
		
	}
	
	public String display() {
		return NONE;
	}
	
}