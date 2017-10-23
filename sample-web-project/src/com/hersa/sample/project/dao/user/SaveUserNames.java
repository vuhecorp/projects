package com.hersa.sample.project.dao.user;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SaveUserNames implements Runnable {
	
	List<User> userList = new ArrayList<User>();

	public SaveUserNames(List<User> userList){
		this.userList = userList;
	}
	
	@Override
	public void run() {
		writeEmailFile();
	}
	
	private void writeEmailFile() {
		Date now = new Date();
		File userFile = new File("C:\\temp\\testFile-" + now.getTime() + ".txt");
		FileWriter fw = null;
		PrintWriter writer = null;
		try {
			// if file doesn't exists, then create it
			if (!userFile.exists()) {
				userFile.createNewFile();
			}
			
			fw = new FileWriter(userFile);
			writer = new PrintWriter(fw);
			writer.println("-------------------------");
			writer.println("User Email List");
			writer.println("-------------------------");
			writer.println();
			
			//write each email into the file.
			int count = 1;
			for (User user : userList) {
				String data = user.getEmail().trim();
				writer.println(count + ". " + data);
				count++;
			}
			
			writer.println();
			writer.println("-------------------------");
			writer.println("Total Emails: " + userList.size());
			writer.println("-------------------------");

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.flush();
			writer.close();
		}
	}
}
