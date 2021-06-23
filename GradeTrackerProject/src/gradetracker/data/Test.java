package gradetracker.data;

import java.io.Serializable;
import java.util.Date; 

public class Test extends Subject implements Serializable{
	private static final long serialVersionUID = 1L;
	private String testTopic;
	private String testDate;
	private String testType;
	private int testLevel;
	private int testScore;
	private int testOutOf;
	private String testReflection;
	private int percentage;
	private int id;
	
	public Test(String top, String d, String typ, int l, int s, int o, String r, int p, int id) {
		this.testTopic = top;
		this.testDate = d;
		this.testType = typ;
		this.testLevel = l;
		this.testScore = s;
		this.testOutOf = o;
		this.testReflection = r;
		this.percentage = p;
		this.id = id;
	}
	
	public Test() {
		
	}
	
	public String getTopic() {
		return testTopic;
	}
	
	public String getDate() {
		return testDate;
	}
	
	public String getType() {
		return testType;
	}
	
	public int getTestLevel() {
		return testLevel;
	}
	
	public int getScore() {
		return testScore;
	}
	
	public int getOutOf() {
		return testOutOf;
	}
	
	public String getReflection() {
		return testReflection;
	}
	
	public int getPercentage() {
		return percentage;
	}
	
	public void setTopic(String setter) {
		testTopic = setter;
	}
	
	public void setDate(String setter) {
		testDate = setter;
	}
	
	public void setTestLevel(int setter) {
		testLevel = setter;
	}
	
	public void setScore(int setter) {
		testScore = setter;
	}
	
	public void setOutOf(int setter) {
		testOutOf = setter;
	}
	
	public void setType(String setter) {
		testType = setter;
	}
	
	public void setReflection(String setter) {
		testReflection = setter;
	}
	
	public void setPercentage(int setter) {
		percentage = setter;
	}
	
	public int getID() {
		return id;
	}
	
	
}