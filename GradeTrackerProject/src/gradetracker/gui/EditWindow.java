package gradetracker.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.VerticalAlignment;
import org.w3c.dom.css.Counter;

import gradetracker.data.AppState;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.lang.model.element.VariableElement;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class EditWindow extends JFrame {

	private JPanel contentPane;
	public JTextField txtEditSubject;
	public JTextField txtEditTeacher;
	public JTable tblTests;
	public JCheckBox chckbxEditHL;
	//public int editSubjectIndex;
	public JLabel lblCurrentLevel;
	public DefaultTableModel tblModel;
	public DefaultCategoryDataset dataset;
	public JPanel graphPanel;
	public ChartPanel chartPanel;
	public JFreeChart chart;
	public JTextArea txtObservations;
	private DefaultRowSorter sorter;
	private JTextField txtSearch;
	private JScrollPane observationsScrollPane;
	public JComboBox cmbxLevel;
	

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public EditWindow(MainWindow parent, int editSubjectIndex) {
		initEditWindowGUI(parent, editSubjectIndex);
	}
	
	public void initEditWindowGUI(MainWindow parent, int editSubjectIndex) {

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		drawChart(editSubjectIndex);
		
		
		txtSearch = new JTextField();
		txtSearch.setBounds(454, 133, 130, 26);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);
		
		cmbxLevel = new JComboBox();
		cmbxLevel.setBounds(459, 339, 67, 27);
		cmbxLevel.setModel(new DefaultComboBoxModel(new String[] {"7","6","5","4","3","2","1"}));
		//cmbxLevel.setSelectedItem(AppState.student.subjects.get(editSubjectIndex).getLevel());
		contentPane.add(cmbxLevel);
		
		JLabel lblNewLabel = new JLabel("Subjects and Assesments");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel.setBounds(24, 19, 311, 56);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Editing");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(591, 34, 67, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Subject:");
		lblNewLabel_2.setBounds(24, 89, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Teacher:");
		lblNewLabel_3.setBounds(24, 129, 61, 16);
		contentPane.add(lblNewLabel_3);
		
		txtEditSubject = new JTextField();
		txtEditSubject.setBounds(97, 87, 238, 26);
		contentPane.add(txtEditSubject);
		txtEditSubject.setColumns(10);
		
		txtEditTeacher = new JTextField();
		txtEditTeacher.setBounds(97, 124, 238, 26);
		contentPane.add(txtEditTeacher);
		txtEditTeacher.setColumns(10);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(354, 166, 278, 165);
		getContentPane().add(scrollPane);
		
		tblTests = new JTable();
		tblTests.setBounds(354, 166, 278, 165);
		//contentPane.add(tblTests);
		scrollPane.setViewportView(tblTests);;
		
		tblModel = new DefaultTableModel()		
				// make cells in table non-editable
				{
					   @Override
					   public boolean isCellEditable(int row, int column) {
					       return false;
					   }
					};
		
		tblModel.addColumn("Date");
		tblModel.addColumn("Topic");
		tblModel.addColumn("Type");
		tblModel.addColumn("%");
		// invisible column:
		tblModel.addColumn("ID");
	
		sorter = new TableRowSorter<>(tblModel);
		tblTests = new JTable(tblModel);
		tblTests.setRowSorter(sorter);
		
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				String text = txtSearch.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				String text = txtSearch.getText();
                if (text.trim().length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet.");
			}
		});
		
		// total width of tblTests = 278
		tblTests.getColumnModel().getColumn(0).setPreferredWidth(67);
		tblTests.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblTests.getColumnModel().getColumn(2).setPreferredWidth(75);
		tblTests.getColumnModel().getColumn(3).setPreferredWidth(36);
		//make last column invisible
		tblTests.getColumnModel().getColumn(4).setPreferredWidth(0);
		tblTests.getColumnModel().getColumn(4).setMinWidth(0);
		tblTests.getColumnModel().getColumn(4).setMaxWidth(0);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
		for (int i=0; i < tblTests.getColumnCount(); i++) {
			tblTests.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		
		JTableHeader header = tblTests.getTableHeader();
		header.setDefaultRenderer(centerRenderer);
		scrollPane.setViewportView(tblTests);
		AppState.loadFromFile();
				
				
		JButton btnNewTest = new JButton("New");
		btnNewTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openNewTestWindow(editSubjectIndex);
			}
		});
		btnNewTest.setBounds(633, 188, 95, 29);
		contentPane.add(btnNewTest);
		
		JButton btnEditTest = new JButton("Edit");
		btnEditTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openEditTestWindow(editSubjectIndex);
			}
		});
		btnEditTest.setBounds(633, 229, 95, 29);
		contentPane.add(btnEditTest);
		
		JButton btnDeleteTest = new JButton("Delete");
		btnDeleteTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteTest(editSubjectIndex);
				drawChart(editSubjectIndex);
			}
		});
		
		//tblTests.requestFocusInWindow();
		
		btnDeleteTest.setBounds(633, 270, 95, 29);
		contentPane.add(btnDeleteTest);
		
		chckbxEditHL = new JCheckBox("HL");
		chckbxEditHL.setBounds(395, 105, 67, 23);
		contentPane.add(chckbxEditHL);
		
		JLabel lblNewLabel_4 = new JLabel("Current level:");
		lblNewLabel_4.setBounds(354, 343, 108, 16);
		contentPane.add(lblNewLabel_4);
		
		/*lblCurrentLevel = new JLabel("0");
		lblCurrentLevel.setForeground(Color.BLUE);
		lblCurrentLevel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblCurrentLevel.setBounds(547, 343, 61, 16);
		contentPane.add(lblCurrentLevel);
		*/
		JLabel lblNewLabel_6 = new JLabel("Observations:");
		lblNewLabel_6.setBounds(24, 387, 118, 16);
		contentPane.add(lblNewLabel_6);
		
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveEditedSubject(parent, editSubjectIndex);
			}
		});
		
		
		btnSave.setBounds(611, 510, 117, 29);
		contentPane.add(btnSave);
		
		JButton btnEditCancel = new JButton("Cancel");
		btnEditCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openMainWindow(parent);
			}
		});
		
		btnEditCancel.setBounds(6, 510, 117, 29);
		contentPane.add(btnEditCancel);
		
		updateTblTests(editSubjectIndex);
		
		/*JButton btnEditRefresh = new JButton("Refresh");
		btnEditRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTblTests(editSubjectIndex);
				drawChart(editSubjectIndex);
			}
		});
		
		btnEditRefresh.setBounds(635, 162, 99, 29);
		contentPane.add(btnEditRefresh);
		*/
		JLabel lblNewLabel_5 = new JLabel("Search:");
		lblNewLabel_5.setBounds(379, 138, 61, 16);
		contentPane.add(lblNewLabel_5);
		
		txtObservations = new JTextArea("Observations go here.");
		//txtObservations.setBounds(24, 413, 691, 92);
		//contentPane.add(txtObservations);
		//txtObservations.setBounds(24, 417, 688, 81);
		txtObservations.setColumns(25);
		txtObservations.setRows(5);
		txtObservations.setEditable(false);
		//contentPane.add(txtObservations);
		
		
		
		txtObservations.setLineWrap(true);
		txtObservations.setWrapStyleWord(true);
		
		observationsScrollPane = new JScrollPane();
		observationsScrollPane.setBounds(24, 413, 691, 92);
		observationsScrollPane.setViewportView(txtObservations);
		observationsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		observationsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		contentPane.add(observationsScrollPane);
		
		//contentPane.add(chartPanel);
		//contentPane.pack();
		//contentPane.repaint();
		
		/*
		graphPanel = new JPanel();
		graphPanel.setBounds(24, 166, 311, 165);
		contentPane.add(graphPanel);
		*/
		tblTests.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable tblTests = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = tblTests.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && tblTests.getSelectedRow() != -1) {
					openEditTestWindow(editSubjectIndex);
				}
			}
		});
		
		
		updateEverything(editSubjectIndex);
		updateTblTests(editSubjectIndex);
		drawChart(editSubjectIndex);
		//createGraph();
		
		
		getRootPane().setDefaultButton(btnSave);
		
		
	}
	
	
	public void updateEverything(int editSubjectIndex) {
		updateTblTests(editSubjectIndex);
		drawChart(editSubjectIndex);
	}
	
	public void saveEditedSubject(MainWindow parent, int editSubjectIndex) {
		String editSubjectName = txtEditSubject.getText();
		String editTeacherName = txtEditTeacher.getText();
		boolean editHL = chckbxEditHL.isSelected();
		int editLevel = Integer.parseInt(cmbxLevel.getSelectedItem().toString());
		
		AppState.student.subjects.get(editSubjectIndex).setSubject(editSubjectName);
		AppState.student.subjects.get(editSubjectIndex).setTeacher(editTeacherName);
		AppState.student.subjects.get(editSubjectIndex).setHL(editHL);
		AppState.student.subjects.get(editSubjectIndex).setLevel(editLevel);
		
		AppState.saveToFile();
		
		openMainWindow(parent);
		
	}
	
	public void openMainWindow(MainWindow parent) {
		
		
		parent.setVisible(true);
		parent.updateTable();
		dispose();
		
	}
	
	public void updateTblTests(int editSubjectIndex) {
		
		
		tblModel.setRowCount(0);
		
		AppState.loadFromFile();
		//int counter = 0;
		
		for (int i = 0; i < AppState.student.subjects.get(editSubjectIndex).tests.size(); i++) {
			Vector row = new Vector();
			row.add(AppState.student.subjects.get(editSubjectIndex).tests.get(i).getDate());
			row.add(AppState.student.subjects.get(editSubjectIndex).tests.get(i).getTopic());
			row.add(AppState.student.subjects.get(editSubjectIndex).tests.get(i).getType());
			row.add(AppState.student.subjects.get(editSubjectIndex).tests.get(i).getPercentage());
			row.add(AppState.student.subjects.get(editSubjectIndex).tests.get(i).getID());
			//System.out.println(AppState.student.subjects.get(editSubjectIndex).getSubject());
			//System.out.println(AppState.student.subjects.get(editSubjectIndex).tests.get(i).getTopic());
			tblModel.addRow(row);
			//counter += 1;
		}
		
		//System.out.println(counter);
		
		tblTests.repaint();
		//createGraph();
		drawChart(editSubjectIndex);
	}
	
	public void openNewTestWindow(int editSubjectIndex) {
		TestWindow tw = new TestWindow(this);
		tw.setVisible(true);
		tw.setAlwaysOnTop(true);
		tw.setLocationRelativeTo(null);
		
		tw.newTest = true;
		tw.lblTestMode.setText("Adding");
		tw.subjectIndex = editSubjectIndex;
	}
	
	public void openEditTestWindow(int editSubjectIndex) {
		if (tblTests.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please select a test to edit.");
		} else if (tblTests.getSelectedRowCount() > 1){
			JOptionPane.showMessageDialog(contentPane, "Please select only one test to edit.");
		} else {
			TestWindow tw = new TestWindow(this);
			tw.setVisible(true);
			tw.setAlwaysOnTop(true);
			tw.setLocationRelativeTo(null);
			//DefaultTableModel tblModel = (DefaultTableModel)tblTests.getModel();
			tw.newTest = false;
			tw.lblTestMode.setText("Editing");
			
			for (int i = 0; i < tblModel.getRowCount(); i++) {
				if (tblTests.isRowSelected(i)) {
					int testID = (int) tblTests.getValueAt(i, 4);
					//System.out.println(testID);
					for (int j = 0; j < AppState.student.subjects.get(editSubjectIndex).tests.size(); j++) {
						if (testID == AppState.student.subjects.get(editSubjectIndex).tests.get(j).getID()) {
							tw.txtTestTopic.setText(AppState.student.subjects.get(editSubjectIndex).tests.get(j).getTopic());
							tw.txtDate.setText(""+AppState.student.subjects.get(editSubjectIndex).tests.get(j).getDate());
							tw.txtScore.setText(Integer.toString(AppState.student.subjects.get(editSubjectIndex).tests.get(j).getScore()));
							tw.txtOutOf.setText(Integer.toString(AppState.student.subjects.get(editSubjectIndex).tests.get(j).getOutOf()));
							tw.txtReflection.setText(AppState.student.subjects.get(editSubjectIndex).tests.get(j).getReflection());
							tw.cmbxTestType.setSelectedItem(AppState.student.subjects.get(editSubjectIndex).tests.get(j).getType());
							tw.cmbxTestLevel.setSelectedItem(Integer.toString(AppState.student.subjects.get(editSubjectIndex).tests.get(j).getTestLevel()));
							tw.editTestIndex = j;
							tw.subjectIndex = editSubjectIndex;
						}
					}
					
//					for (int j = 0; j < AppState.student.subjects.get(editSubjectIndex).tests.size(); j++) {
//						if (AppState.student.subjects.get(editSubjectIndex).tests.get(j).getID() == testID) {
//							
//						}
//					}
				}
			}
			tw.requestFocusInWindow();
		}
	}
	
	public void deleteTest(int editSubjectIndex) {
		if (tblTests.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please select a test to delete.");
		} else if (tblTests.getSelectedRowCount() >1) {
			JOptionPane.showMessageDialog(contentPane,"Please delete one test at a time.");
		} else {
			//DefaultTableModel tblModel = (DefaultTableModel)tblTests.getModel();
			
			JFrame jf = new JFrame();
			jf.setAlwaysOnTop(true);
			int result = JOptionPane.showConfirmDialog(jf, "Press OK to confirm", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
			
			if (result == JOptionPane.YES_OPTION) {
				for (int i = 0; i < tblModel.getRowCount(); i++) {
					if (tblTests.isRowSelected(i)) {
						tblModel.removeRow(i);
						AppState.student.subjects.get(editSubjectIndex).tests.remove(i);
						AppState.saveToFile();
						drawChart(editSubjectIndex);
						writeObservations(editSubjectIndex);
					}
				}
			} else if (result == JOptionPane.NO_OPTION){
				dispose();
			}
		}
		
		updateTblTests(editSubjectIndex);
		drawChart(editSubjectIndex);
	}
	
	// CREATING GRAPH
	
	public void drawChart(int index) {
		chart = AppState.student.subjects.get(index).getChart(index);
			
		chartPanel = new ChartPanel(chart);
		chartPanel.setSize(311,200);
		chartPanel.setLocation(24, 166);
		contentPane.add(chartPanel);
		
	}
	
	public void writeObservations(int editSubjectIndex) {
		txtObservations.setText(AppState.student.subjects.get(editSubjectIndex).getObservations(editSubjectIndex));
	}
}
