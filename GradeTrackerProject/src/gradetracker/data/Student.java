package gradetracker.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
	private static final long serialVersionUID = 1L;
	public ArrayList<Subject> subjects = new ArrayList();
	private char[] password;
	private int targetScore;
	
	Student() {
		password = new char[] {};
		targetScore = 0;
	}
	
	
	public char[] getPassword() {
		return password;
	}
	
	public void setPassword(char[] setter) {
		password = setter;
	}
	
	public int getTargetScore() {
		return targetScore;
	}
	
	public void setTargetScore(int setter) {
		targetScore = setter;
	}
}
