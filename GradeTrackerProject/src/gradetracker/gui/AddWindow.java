package gradetracker.gui;
//package gradetracker.data;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.data.category.DefaultCategoryDataset;
import org.omg.CORBA.PUBLIC_MEMBER;

import gradetracker.data.Student;
import gradetracker.data.AppState;
import gradetracker.data.Subject;
import gradetracker.data.Test;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class AddWindow extends JFrame {

	private JPanel contentPane;
	private JTextField txtSubjectName;
	private JTextField txtTeacherName;
	private JCheckBox chckbxAddHL = new JCheckBox("HL");;
	private JComboBox cmbxAddLevel = new JComboBox();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public AddWindow(MainWindow parent) {
		initAddWindowGUI(parent);
	}

	public void initAddWindowGUI(MainWindow parent) {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);

		txtSubjectName = new JTextField();
		txtSubjectName.setBounds(30, 46, 400, 35);
		contentPane.add(txtSubjectName);
		txtSubjectName.setColumns(10);

		JLabel lblNewLabel = new JLabel("Enter subject name here:");
		lblNewLabel.setBounds(52, 18, 242, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Enter teacher name here:");
		lblNewLabel_1.setBounds(52, 99, 185, 16);
		contentPane.add(lblNewLabel_1);

		txtTeacherName = new JTextField();
		txtTeacherName.setBounds(30, 130, 400, 35);
		contentPane.add(txtTeacherName);
		txtTeacherName.setColumns(10);

		chckbxAddHL.setBounds(357, 177, 68, 23);
		contentPane.add(chckbxAddHL);

		cmbxAddLevel.setBounds(134, 177, 61, 27);
		cmbxAddLevel.setModel(new DefaultComboBoxModel(new String[] {"7","6","5","4","3","2","1"}));
		contentPane.add(cmbxAddLevel);

		JButton btnAddSave = new JButton("Save");
		btnAddSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSubject(parent);
			}
		});

		btnAddSave.setBounds(313, 238, 117, 29);
		contentPane.add(btnAddSave);

		JButton btnAddCancel = new JButton("Cancel");
		btnAddCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openMainWindow(parent);
			}
		});

		btnAddCancel.setBounds(30, 238, 117, 29);
		contentPane.add(btnAddCancel);

		JLabel lblNewLabel_2 = new JLabel("Level:");
		lblNewLabel_2.setBounds(62, 181, 61, 16);
		contentPane.add(lblNewLabel_2);

		txtTeacherName.addKeyListener
		(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					addSubject(parent);
				}
			}
		}
				);

		getRootPane().setDefaultButton(btnAddSave);
	}

	public void addSubject(MainWindow parent) {
		if (txtSubjectName.getText().equals("") || txtSubjectName.getText() == null) {
			JOptionPane.showMessageDialog(contentPane, "Please enter a subject name.");
		} else {

			String addSubjectName = txtSubjectName.getText();
			String addTeacher = txtTeacherName.getText();
			int addLevel = Integer.parseInt((cmbxAddLevel.getSelectedItem().toString()));
			boolean addHlSelected = chckbxAddHL.isSelected();
			ArrayList<Test> newTestArrayList = new ArrayList();
			DefaultCategoryDataset newDataset = new DefaultCategoryDataset();

			AppState.student.subjects.add(new Subject(addSubjectName, addTeacher, addLevel, addHlSelected, newTestArrayList, newDataset));

			// CHECK METHOD BELOW
			//		if (Student.subjects.size()==2) {
			//			System.out.println(Student.subjects.get(1).subjectName);
			//		}	
			//System.out.println(Student.subjects.size());
			//System.out.println("added new subject");

			AppState.saveToFile();
			openMainWindow(parent);
		}
	}

	public void openMainWindow(MainWindow parent) {
		parent.setVisible(true);
		parent.updateTable();
		dispose();
	}
}
