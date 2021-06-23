package gradetracker.data;

import java.io.Serializable;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.omg.PortableServer.THREAD_POLICY_ID;

public class Subject extends Student implements Serializable{
	private static final long serialVersionUID = 1L;
	private String subjectName;
	private String teacher;
	private int level;
	private boolean hl;
	public ArrayList<Test> tests = new ArrayList();
	private DefaultCategoryDataset dataset;

	public Subject(String s, String t, int l, boolean hl, ArrayList<Test> tAL, DefaultCategoryDataset d) {
		this.subjectName = s;
		this.teacher = t;
		this.level = l;
		this.hl = hl;
		this.tests = tAL;
		this.dataset = d;
	}

	public Subject() {

	}

	public String getTeacher() {
		return teacher;
	}

	public DefaultCategoryDataset getDataset() {
		return dataset;
	}

	public JFreeChart getChart(int index) {
		dataset.clear();
		for (int i = 0; i < AppState.student.subjects.get(index).tests.size(); i++) {
			dataset.addValue(AppState.student.subjects.get(index).tests.get(i).getPercentage(), tests.get(i).getTopic(), "");
		}

		JFreeChart chart = ChartFactory.createBarChart3D(
				"Test Results: "+ AppState.student.subjects.get(index).getSubject(),
				"Tests",
				"Result (%)",
				dataset,
				PlotOrientation.VERTICAL,
				false,false,false);

		ValueAxis rangeAxis = (ValueAxis) chart.getCategoryPlot().getRangeAxis();
		rangeAxis.setRange(0,100);

		return chart;

	}

	public int getLevel() {
		return level;
	}

	public boolean getHL() {
		return hl;
	}

	public String getSubjectWithHLCheck(){
		if (hl) {
			return "HL "+ subjectName;
		} else {
			return "SL " + subjectName;
		}
	}

	public String getSubject() {
		return subjectName;
	}

	public void setSubject(String subjectSet) {
		subjectName = subjectSet;
	}

	public void setTeacher(String teacherSet) {
		teacher = teacherSet;
	}

	public void setHL (Boolean hlSet) {
		hl = hlSet;
	}

	public void setLevel(int levelSet) {
		level = levelSet;
	}

	public String getObservations(int index){
		String higherObservations = "You did well in these tests: ";
		String lowerObservations = "You could improve in these tests: ";
		String observations = "";
		int average = 0;
		int total = 0;
		ArrayList<String> aboveAverage = new ArrayList();
		ArrayList<String> belowAverage = new ArrayList();
		if (AppState.student.subjects.get(index).tests.size() > 1) {
			for (int i = 0; i < AppState.student.subjects.get(index).tests.size(); i++) {
				total += AppState.student.subjects.get(index).tests.get(i).getPercentage();
			}

			average = total/(AppState.student.subjects.get(index).tests.size());

			for (int i = 0; i < AppState.student.subjects.get(index).tests.size(); i++) {
				if (AppState.student.subjects.get(index).tests.get(i).getPercentage() >= average) {
					aboveAverage.add(AppState.student.subjects.get(index).tests.get(i).getTopic());
				} else {
					belowAverage.add(AppState.student.subjects.get(index).tests.get(i).getTopic());
				}
			}

			for (String t: aboveAverage) {
				if(t.equals(aboveAverage.get(aboveAverage.size()-1))) {
					higherObservations += t;
				} else {
				higherObservations += t + ", ";
				}
			}

			for (String t: belowAverage) {
				if(t.equals(belowAverage.get(belowAverage.size()-1))) {
					lowerObservations += t;
				} else {
				lowerObservations += t + ", ";
				}
			}
			observations = ""+higherObservations+"\n"+lowerObservations;
		} else {
			observations = "";
		}
		return observations;
	}
}
