package com.hersa.sample.project.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadFile
 */

public class DownloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		//read input file from absolute path
		String filePath = "C:\\Users\\*****\\Documents\\Files\\fileServlet.txt";
		File downloadFile = new File(filePath);
		FileInputStream inStream = new FileInputStream(downloadFile);
		
	     // obtains ServletContext
        ServletContext context = getServletContext();
		
		//get mimetype
		String mimeType = context.getMimeType(filePath);
		 if (mimeType == null) {        
	            // set to binary type if MIME mapping not found
	            mimeType = "application/octet-stream";
	        }
		 //modify response
		 response.setContentType(mimeType);
		 response.setContentLength((int) downloadFile.length());
		 
		 //force download
		 String headerKey = "Content-Disposition";
		 String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		 response.setHeader(headerKey, headerValue);
		 
		 //get response outputstream
		 
		 OutputStream  outStream = response.getOutputStream();
		 
		 byte[] buffer = new byte[4096];
	        int bytesRead = -1;
		 
	        while ((bytesRead = inStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	        inStream.close();
	        outStream.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
