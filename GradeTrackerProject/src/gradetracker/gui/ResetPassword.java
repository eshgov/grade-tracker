package gradetracker.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gradetracker.data.AppState;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class ResetPassword extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField pswrdCurrent;
	private JPasswordField pswrdNew;
	private JPasswordField pswrdConfirm;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the dialog.
	 */
	public ResetPassword(loginWindow parent, char[] currentPassword) {
		setBounds(100, 100, 287, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 288, 233);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		
		pswrdCurrent = new JPasswordField();
		pswrdCurrent.setBounds(54, 43, 170, 26);
		contentPanel.add(pswrdCurrent);
		
		pswrdNew = new JPasswordField();
		pswrdNew.setBounds(54, 109, 170, 26);
		contentPanel.add(pswrdNew);
		
		JLabel lblNewLabel = new JLabel("Current Password:");
		lblNewLabel.setBounds(62, 26, 133, 16);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setBounds(64, 81, 120, 16);
		contentPanel.add(lblNewPassword);
		
		lblStatus = new JLabel("");
		lblStatus.setForeground(Color.RED);
		lblStatus.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblStatus.setBounds(62, 208, 146, 16);
		contentPanel.add(lblStatus);
		
		pswrdConfirm = new JPasswordField();
		pswrdConfirm.setBounds(54, 170, 170, 26);
		contentPanel.add(pswrdConfirm);
		
		JLabel lblReenterPassword = new JLabel("Re-enter Password:");
		lblReenterPassword.setBounds(64, 150, 146, 16);
		contentPanel.add(lblReenterPassword);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 233, 288, 39);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						savePassword(currentPassword, parent);
					}
				});
				okButton.setBounds(159, 5, 75, 29);
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
				cancelButton.setBounds(44, 5, 86, 29);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void savePassword(char[] currentPassword, loginWindow parent) {
		AppState.loadFromFile();
		if (Arrays.equals(pswrdCurrent.getPassword(), currentPassword) && Arrays.equals(pswrdNew.getPassword(), pswrdConfirm.getPassword()) && (pswrdNew.getPassword() != null && pswrdConfirm.getPassword() != null)) {
			currentPassword = pswrdConfirm.getPassword();
			parent.setVisible(true);
			AppState.student.setPassword(currentPassword);
			AppState.saveToFile();
			dispose();
		} else {
			lblStatus.setText("Please check all fields.");
		}
	}
}
