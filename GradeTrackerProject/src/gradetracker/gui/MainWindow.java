package gradetracker.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.jfree.chart.axis.StandardTickUnitSource;
import org.omg.CORBA.PUBLIC_MEMBER;

import gradetracker.data.AppState;
import gradetracker.data.Subject;
import gradetracker.data.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.util.ArrayList;
import java.util.Vector;
import javax.swing.SwingConstants;
import java.awt.Font;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tblModel;
	//private int targetIB = 40;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		initMainWindowGUI();
		setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AppState.loadFromFile();
		updateTable();
	}
	
	public void initMainWindowGUI() {

		setBounds(100,100,651,301);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 33, 496, 215);
		contentPane.add(scrollPane_1);
		
		table = new JTable();
		JTableHeader header=table.getTableHeader();
		scrollPane_1.setViewportView(table);
		
		tblModel = new DefaultTableModel()
		
		
		// make cells in table non-editable
		{
			   @Override
			   public boolean isCellEditable(int row, int column) {
			       return false;
			   }
		};
		
		tblModel.addColumn("Subject");
		tblModel.addColumn("Teacher");
		tblModel.addColumn("Level");
		
		table.setModel(tblModel);
		
		
		// total width = 496
		table.getColumnModel().getColumn(0).setPreferredWidth(225);
		table.getColumnModel().getColumn(1).setPreferredWidth(225);
		table.getColumnModel().getColumn(2).setPreferredWidth(46);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		header.setDefaultRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		
		AppState.loadFromFile();		
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(528, 100, 117, 29);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openAddWindow();
			}
		});
		getContentPane().add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(528, 141, 117, 29);
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openEditWindow();
			}
		});
		getContentPane().add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(528, 182, 117, 29);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSubject();
			}
		});
		
		JButton btnSort = new JButton("Sort");
		btnSort.setBounds(528, 29, 117, 29);
		contentPane.add(btnSort);
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sortHL();
			}
		});
		getContentPane().add(btnDelete);
		
		updateTable();
		/*
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(528, 6, 117, 29);
		
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		getContentPane().add(btnRefresh);
		*/
		
		JLabel lblNewLabel = new JLabel("Grade Tracker");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(229, 0, 163, 30);
		contentPane.add(lblNewLabel);
		
		JButton btntargetScore = new JButton("Target IB Score");
		btntargetScore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AppState.loadFromFile();
				changeTargetScore(AppState.student.getTargetScore());
			}
		});
		btntargetScore.setBounds(528, 238, 117, 29);
		contentPane.add(btntargetScore);
		
		
		
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent mouseEvent) {
				JTable table = (JTable) mouseEvent.getSource();
				Point point = mouseEvent.getPoint();
				int row = table.rowAtPoint(point);
				if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
					openEditWindow();
				}
			}
		});
		
		getContentPane().paintComponents(getGraphics());
		
		updateTable();
		IBBasedBackground();
		
	}
	
	public void updateTable() {
		tblModel.setRowCount(0);

		for (int i = 0; i < AppState.student.subjects.size(); i++) {
			Vector row = new Vector();
			row.add(AppState.student.subjects.get(i).getSubjectWithHLCheck());
			row.add(AppState.student.subjects.get(i).getTeacher());
			row.add(AppState.student.subjects.get(i).getLevel());
			tblModel.addRow(row);
		}
		
		IBBasedBackground();
		
	}
	
	public void openAddWindow() {
		AddWindow aw = new AddWindow(this);
		aw.setVisible(true);
	}
	
	public void openEditWindow() {
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please select a subject to edit.");
		} else if (table.getSelectedRowCount() > 1){
			JOptionPane.showMessageDialog(contentPane, "Please select only one subject to edit.");
		} else {
			
			//DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
			
			for (int i = 0; i < tblModel.getRowCount(); i++) {
				if (table.isRowSelected(i)) {
					EditWindow ew = new EditWindow(this, i);
					ew.setVisible(true);
					ew.setAlwaysOnTop(true);
					ew.setLocationRelativeTo(null);
					ew.txtEditSubject.setText(AppState.student.subjects.get(i).getSubject());
					ew.txtEditTeacher.setText(AppState.student.subjects.get(i).getTeacher());
					ew.chckbxEditHL.setSelected(AppState.student.subjects.get(i).getHL());
					//ew.lblCurrentLevel.setText(Integer.toString(AppState.student.subjects.get(i).getLevel()));
					ew.cmbxLevel.setSelectedItem(Integer.toString(AppState.student.subjects.get(i).getLevel()));
					ew.tblModel.setRowCount(0);
					ew.writeObservations(i);
					ew.updateEverything(i);
					ew.updateTblTests(i);
					//ew.drawChart(i);
					ew.requestFocusInWindow();
//					System.out.println(i);
//					System.out.println(AppState.student.subjects.get(i).getSubject());
				}
			}
			
			//DefaultTableModel editTblModel = (DefaultTableModel)ew.tblTests.getModel();
			
			
		}
	}
	
	public void deleteSubject() {
		
		if (table.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(contentPane, "Please select a subject to delete.");
		} else if (table.getSelectedRowCount() >1) {
			JOptionPane.showMessageDialog(contentPane,"Please delete one subject at a time.");
		} else {
			//DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
			
			JFrame jf = new JFrame();
			jf.setAlwaysOnTop(true);
			int result = JOptionPane.showConfirmDialog(jf, "Press OK to confirm", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
		
			if (result == JOptionPane.YES_OPTION) {
				for (int i = 0; i < tblModel.getRowCount(); i++) {
					if (table.isRowSelected(i)) {
						tblModel.removeRow(i);
						AppState.student.subjects.remove(i);
						AppState.saveToFile();
					}
				}
			} else if (result == JOptionPane.NO_OPTION){
				dispose();
			}
		}
		
		updateTable();
	}
	
	public void sortHL() {
		
		ArrayList<Subject> sortedSubjects = new ArrayList();
		for (Subject sub :AppState.student.subjects) {
			sortedSubjects.add(sub);
		}
		
		for (int i = 0; i < sortedSubjects.size(); i++) {
			if (sortedSubjects.get(i).getHL()) {
				AppState.student.subjects.remove(i);
				AppState.student.subjects.add(0, sortedSubjects.get(i));
				AppState.saveToFile();
			}
		}
		updateTable();
	}

	public void IBBasedBackground() {
		
		int IBScoreCounter = 0;
		
		for (int i = 0; i < AppState.student.subjects.size(); i++) {
			IBScoreCounter += AppState.student.subjects.get(i).getLevel();
		}
		
		if (IBScoreCounter >= AppState.student.getTargetScore()) {
			Color paleGreenColor = new Color(152,251,152);
			contentPane.setBackground(paleGreenColor);
		} else {
			Color lightRedColor = new Color(255,204,203);
			contentPane.setBackground(lightRedColor);
		}
		
	}
	
	public void changeTargetScore(int currentScore) {
		TargetScore ts = new TargetScore(currentScore, this);
		ts.setVisible(true);
	}
}
