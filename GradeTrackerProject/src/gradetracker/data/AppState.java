package gradetracker.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppState extends Student {
	
	private static final String filename = "grade1-tracker.ser"; //choose a good filename
	public static Student student=null;		
	
	public static Student getData() {
		//If they forgot to load it from file before getting it, do that first
		if(student==null) loadFromFile();
		//return the data
		return student;
	}
	
	public static void saveToFile() {
		//Save the data to a file
		try {
		FileOutputStream os=new FileOutputStream(filename);
		ObjectOutputStream oos=new ObjectOutputStream(os);
		oos.writeObject(student);
		os.close();
		} catch (Exception e) {e.printStackTrace();}
		
	}
	
	public static void loadFromFile() {
		//Load the data from a file
		try {
		FileInputStream is=new FileInputStream(filename);
		ObjectInputStream ois=new ObjectInputStream(is);
		student=(Student)ois.readObject();
		is.close();
		} catch (Exception e) {
			e.printStackTrace();
			//OK there is an error with the file (or it doesn't exist yet)
			//Make a new empty Student and save it to file for next time
			student=new Student();
			saveToFile();
		}
		
	}
	
	
}
