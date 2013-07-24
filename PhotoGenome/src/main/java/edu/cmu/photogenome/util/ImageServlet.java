package edu.cmu.photogenome.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet class invoked whenever an image needs to be displayed. Reads the requested 
 * image from its location and writes the image data to the response.
 */
public class ImageServlet extends HttpServlet {
	
	private static final int DEFAULT_BUFFER_SIZE = 10240;
	private static final String IMAGE_NOT_AVAILABLE = "image_not_available.jpg";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Properties config = ConfigUtil.getApplicationProperties();
		String imagePath = config.getProperty("photoFilePath");
		
		String requestedImage = request.getPathInfo(); // get name of requested image
		// if requested path is null, use default image instead
		if(requestedImage == null)
			requestedImage = IMAGE_NOT_AVAILABLE;

		File image = new File(imagePath, URLDecoder.decode(requestedImage, "UTF-8"));
		// if requested image does not exist, use default image instead
		if(!image.exists())
			image = new File(imagePath, IMAGE_NOT_AVAILABLE);
		
		String contentType = getServletContext().getMimeType(image.getName());
		
		// set http response info
        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + image.getName() + "\"");
        
        // write image data to response
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        try {
            input = new BufferedInputStream(new FileInputStream(image), DEFAULT_BUFFER_SIZE);
            output = new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);

            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
        } 
        finally {
        	// try to close input stream
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // try to close output stream
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
}
