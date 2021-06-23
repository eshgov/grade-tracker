package gradetracker.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gradetracker.data.AppState;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;

public class TargetScore extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtTarget;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public TargetScore(int currentScore, MainWindow parent) {
		setBounds(100, 100, 194, 214);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 194, 147);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		AppState.loadFromFile();
		txtTarget = new JTextField();
		txtTarget.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		txtTarget.setText(""+currentScore);
		txtTarget.setBounds(29, 82, 130, 26);
		contentPanel.add(txtTarget);
		txtTarget.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Target Score:");
		lblNewLabel.setBounds(51, 46, 82, 16);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 147, 194, 39);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int newScore = Integer.parseInt(txtTarget.getText());
						AppState.student.setTargetScore(newScore);
						AppState.saveToFile();
						parent.setVisible(true);
						parent.IBBasedBackground();
						dispose();
					}
				});
				okButton.setBounds(113, 5, 75, 29);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setBounds(6, 5, 86, 29);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
