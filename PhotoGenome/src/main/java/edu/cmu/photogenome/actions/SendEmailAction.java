package edu.cmu.photogenome.actions;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.io.FileUtils;

import edu.cmu.photogenome.util.EmailUtil;

public class SendEmailAction {

	// SMTP properties - fetched from struts.xml
	private String host;
	private String port;
	private String userName;
	private String password;

	// file upload properties - fetched by interceptor fileUpload
	private File fileUpload;
	private String fileUploadFileName;
	private String fileUploadContentType;

	// e-mail fields - fetched from EmailForm.jsp
	private String recipient;
	private String subject;
	private String message;

	public String doSendEmail() throws IOException, AddressException, MessagingException {
		File saveFile = null;
		String tempPath = System.getProperty("java.io.tmpdir");
		saveFile = new File(tempPath + File.separator + fileUploadFileName);
		FileUtils.copyFile(fileUpload, saveFile);

		EmailUtil.sendEmail(host, port, userName, password, recipient,
				subject, message, saveFile);

		if (saveFile != null) {
			saveFile.delete();
		}
		
		return "success";
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public File getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(File fileUpload) {
		this.fileUpload = fileUpload;
	}

	public String getFileUploadFileName() {
		return fileUploadFileName;
	}

	public void setFileUploadFileName(String fileUploadFileName) {
		this.fileUploadFileName = fileUploadFileName;
	}

	public String getFileUploadContentType() {
		return fileUploadContentType;
	}

	public void setFileUploadContentType(String fileUploadContentType) {
		this.fileUploadContentType = fileUploadContentType;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}