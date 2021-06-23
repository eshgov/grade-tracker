package gradetracker.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import gradetracker.data.AppState;
import gradetracker.data.Test;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFormattedTextField;
import java.awt.Font;

public class TestWindow extends JFrame {

	private JPanel contentPane;
	public JTextField txtTestTopic;
	public JTextArea txtReflection;
	public JTextField txtScore;
	public JTextField txtOutOf;
	public JTextField txtDate;
	public boolean newTest;
	public JLabel lblTestMode;
	public int editTestIndex;
	public int subjectIndex;
	public JComboBox cmbxTestLevel;
	public JComboBox cmbxTestType;
	private JScrollPane reflectionScrollPane;
	private JLabel lblwordCount;
	private String pattern = "dd/MM/yyyy";
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	private JButton btnCalender;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TestWindow(EditWindow parent) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 440, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		lblwordCount = new JLabel(wordCount());
		lblwordCount.setHorizontalAlignment(SwingConstants.RIGHT);
		//lblwordCount.setText(wordCount());
		lblwordCount.setBounds(342, 82, 61, 16);
		contentPane.add(lblwordCount);
		
		JLabel lblNewLabel = new JLabel("Topic:");
		lblNewLabel.setBounds(16, 6, 61, 16);
		contentPane.add(lblNewLabel);
		
		txtTestTopic = new JTextField();
		txtTestTopic.setBounds(16, 34, 130, 26);
		contentPane.add(txtTestTopic);
		txtTestTopic.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Reflection:");
		lblNewLabel_1.setBounds(169, 82, 92, 16);
		contentPane.add(lblNewLabel_1);
		
		cmbxTestLevel = new JComboBox();
		cmbxTestLevel.setBounds(275, 239, 61, 27);
		cmbxTestLevel.setModel(new DefaultComboBoxModel(new String[] {"7","6","5","4","3","2","1"}));
		contentPane.add(cmbxTestLevel);
		
		DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
		listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		
		cmbxTestType = new JComboBox();
		cmbxTestType.setBounds(218, 35, 139, 27);
		cmbxTestType.setModel(new DefaultComboBoxModel(new String[] {"Unit Test","Classwork", "Homework", "Practice IA", "IA", "Mock Exam", "Exam"}));
		contentPane.add(cmbxTestType);
		cmbxTestType.setRenderer(listRenderer);

		txtReflection = new JTextArea();
		txtReflection.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				lblwordCount.setText(wordCount());
			}
		});
		txtReflection.setColumns(25);
		txtReflection.setRows(5);
		//txtReflection.setBounds(169, 110, 250, 117);
		//txtReflection.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtReflection.setLineWrap(true);
		txtReflection.setWrapStyleWord(true);
		
		reflectionScrollPane = new JScrollPane();
		reflectionScrollPane.setBounds(169, 102, 234, 130);
		reflectionScrollPane.setViewportView(txtReflection);
		reflectionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		reflectionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		contentPane.add(reflectionScrollPane);
		//contentPane.add(txtReflection);
		
		JLabel lblNewLabel_2 = new JLabel("Score:");
		lblNewLabel_2.setBounds(16, 151, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		txtScore = new JTextField();
		txtScore.setBounds(16, 168, 130, 26);
		contentPane.add(txtScore);
		txtScore.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Out of:");
		lblNewLabel_3.setBounds(16, 206, 61, 16);
		contentPane.add(lblNewLabel_3);
		
		txtOutOf = new JTextField();
		txtOutOf.setBounds(16, 234, 130, 26);
		contentPane.add(txtOutOf);
		txtOutOf.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openEditWindow(parent);
				//dispose();
			}
		});
		btnCancel.setBounds(6, 281, 117, 29);
		contentPane.add(btnCancel);
		
		JButton btnSaveTest = new JButton("Save");
		btnSaveTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (newTest) {
					addNewTest(parent);
				} else {
					saveEditedTest(parent);
				}
			}
		});
		btnSaveTest.setBounds(317, 281, 117, 29);
		contentPane.add(btnSaveTest);
		
		lblTestMode = new JLabel("Editing");
		lblTestMode.setForeground(Color.RED);
		lblTestMode.setBounds(373, 6, 61, 16);
		contentPane.add(lblTestMode);
		
		JLabel lblNewLabel_4 = new JLabel("Date:");
		lblNewLabel_4.setBounds(16, 69, 61, 16);
		contentPane.add(lblNewLabel_4);
		
		txtDate = new JTextField();
		txtDate.setBounds(16, 86, 130, 26);
		contentPane.add(txtDate);
		txtDate.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Level:");
		lblNewLabel_5.setBounds(218, 244, 61, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Type:");
		lblNewLabel_6.setBounds(180, 39, 61, 16);
		contentPane.add(lblNewLabel_6);
		
		getRootPane().setDefaultButton(btnSaveTest);
		
		btnCalender = new JButton("Calender");
		btnCalender.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		btnCalender.setBounds(29, 116, 117, 23);
		contentPane.add(btnCalender);
		
//		final JFrame f = new JFrame();
//		f.isAlwaysOnTop();
//		f.setLocationRelativeTo(this);
//		f.pack();
		
		btnCalender.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                txtDate.setText(new DatePicker(TestWindow.this).setPickedDate());
                //TestWindow.this.setAlwaysOnTop(true);
            }
        });
		
	}
	
	public void addNewTest(EditWindow parent) {
		
		String testTopic = txtTestTopic.getText();
		String date = txtDate.getText();
		//Date date = simpleDateFormat.parse(txtDate.getText());
//		try {
//			date = simpleDateFormat.parse(txtDate.getText());
//		} catch (ParseException e) {
//			JOptionPane.showMessageDialog(contentPane, "Please input date in correct format (dd/MM/yyyy).");
//		}
		int score = Integer.parseInt(txtScore.getText());
		int outOf = Integer.parseInt(txtOutOf.getText());
		String testType = cmbxTestType.getSelectedItem().toString();
		String reflection = txtReflection.getText();
		int testLevel = Integer.parseInt(cmbxTestLevel.getSelectedItem().toString());
		float floatPercentage = ((float)score/(float)outOf)*100;
		int integerPercentage = Math.round(floatPercentage);
		int id = (int) (Math.random()*1000000);
		
		/*int integerPercentage = calculatePercentage(score, outOf);
		if (integerPercentage == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please ensure that your score is not more than the maximum score.");
		}*/

		AppState.student.subjects.get(subjectIndex).tests.add(new Test(testTopic, date, testType, testLevel, score, outOf, reflection, integerPercentage, id));
		AppState.saveToFile();
		//dispose();
		openEditWindow(parent);
		
	}
	
	public void openEditWindow(EditWindow parent) {
		
		parent.setVisible(true);
		//parent.editSubjectIndex = subjectIndex;
		parent.txtEditSubject.setText(AppState.student.subjects.get(subjectIndex).getSubject());
		parent.txtEditTeacher.setText(AppState.student.subjects.get(subjectIndex).getTeacher());
		parent.chckbxEditHL.setSelected(AppState.student.subjects.get(subjectIndex).getHL());
		//parent.lblCurrentLevel.setText(Integer.toString(AppState.student.subjects.get(subjectIndex).getLevel()));
		parent.updateTblTests(subjectIndex);
		parent.drawChart(subjectIndex);
		parent.writeObservations(subjectIndex);
		dispose();
	}
	
	public void saveEditedTest(EditWindow parent) {
		String testTopic = txtTestTopic.getText();
		String date = txtDate.getText();
		//Date date = simpleDateFormat.parse(txtDate.getText());
//		try {
//			date = simpleDateFormat.parse(txtDate.getText());
//		} catch (ParseException e) {
//			JOptionPane.showMessageDialog(contentPane, "Please input date in correct format (dd/MM/yyyy).");
//		}
		int score = Integer.parseInt(txtScore.getText());
		int outOf = Integer.parseInt(txtOutOf.getText());
		String testType = cmbxTestType.getSelectedItem().toString();
		String reflection = txtReflection.getText();
		int testLevel = Integer.parseInt(cmbxTestLevel.getSelectedItem().toString());
		
		
		if (score > outOf) {
			JOptionPane.showMessageDialog(contentPane, "Please ensure that your score is not more than the maximum score.");
		} else {
			float floatPercentage = ((float)score/(float)outOf)*100;
			int integerPercentage = Math.round(floatPercentage);
			AppState.student.subjects.get(subjectIndex).tests.get(editTestIndex).setTopic(testTopic);
			AppState.student.subjects.get(subjectIndex).tests.get(editTestIndex).setDate(date);
			AppState.student.subjects.get(subjectIndex).tests.get(editTestIndex).setScore(score);
			AppState.student.subjects.get(subjectIndex).tests.get(editTestIndex).setOutOf(outOf);
			AppState.student.subjects.get(subjectIndex).tests.get(editTestIndex).setType(testType);
			AppState.student.subjects.get(subjectIndex).tests.get(editTestIndex).setReflection(reflection);
			AppState.student.subjects.get(subjectIndex).tests.get(editTestIndex).setTestLevel(testLevel);
			AppState.student.subjects.get(subjectIndex).tests.get(editTestIndex).setPercentage(integerPercentage);
			
			AppState.saveToFile();

			openEditWindow(parent);
		}
	}
	
	public int calculatePercentage(int s, int o) {
		// check that score is not more than outOf
		if (s > o) {
			return -1;
		} else {
			float floatPercentage = ((float)s/(float)o)*100;
			int integerPercentage = Math.round(floatPercentage);
			return integerPercentage;
		}
	}
	
	public String wordCount() {
		if (txtReflection == null || txtReflection.getText().equals("")) {
			return "";
		} else {
			String input = txtReflection.getText();
			String[] word = input.split("\\s");
			return "" + word.length;
		}
	}
}
