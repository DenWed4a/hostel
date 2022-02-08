package com.epam.ds.hostel.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main2 {
	

	public static void main(String[] args) throws IOException  {
		
	FileInputStream inputStream = new FileInputStream("./src/main/webapp/images/usersImages/23.jpg");
	byte[] array = new byte[inputStream.available()];
	System.out.println(array.length);
	for(int i = 0; i < array.length; i++) {
		array[i] = (byte)inputStream.read();
		System.out.println(array[i]);
	}
	inputStream.close();
	
	FileOutputStream fileOutputStream = new FileOutputStream("./src/webapp/images/usersImages/23.jpg");
		for(int i = 0; i < array.length; i++) {
			fileOutputStream.write(array[i]);
		}
	fileOutputStream.close();
	}

}
