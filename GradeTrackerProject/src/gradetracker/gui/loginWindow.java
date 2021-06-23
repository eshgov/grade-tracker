package gradetracker.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import gradetracker.data.AppState;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class loginWindow extends JFrame {

	private JPanel contentPane;
	private JPasswordField pswrdInput;
	//private char[] correctPassword = AppState.student.password;
	private JLabel lblMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginWindow frame = new loginWindow();
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
	public loginWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 316, 272);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Grade Tracker Login");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(51, 6, 193, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Password:");
		lblNewLabel_1.setBounds(17, 53, 111, 16);
		contentPane.add(lblNewLabel_1);
		
		pswrdInput = new JPasswordField();
		pswrdInput.setBounds(36, 81, 219, 32);
		pswrdInput.setBorder(new MatteBorder(0,0,1,0, Color.BLACK));
		pswrdInput.setOpaque(false);
		contentPane.add(pswrdInput);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnLogin.setBounds(69, 197, 167, 29);
		contentPane.add(btnLogin);
		
		lblMessage = new JLabel("");
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBackground(Color.RED);
		lblMessage.setBounds(44, 169, 200, 16);
		contentPane.add(lblMessage);
		
		JButton btnresetPassword = new JButton("Reset Password");
		btnresetPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			resetPassword();
			}
		});
		btnresetPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		btnresetPassword.setBounds(27, 125, 111, 26);
		contentPane.add(btnresetPassword);
		
		pswrdInput.addKeyListener
	      (new KeyAdapter() {
	         public void keyPressed(KeyEvent e) {
	           int key = e.getKeyCode();
	           if (key == KeyEvent.VK_ENTER) {
	              login();
	              }
	           }
	         }
	      );
	}
	
	
	public void login() {
		AppState.loadFromFile();
		//System.out.println(pswrdInput.getPassword());
		//System.out.println(AppState.student.getPassword());
		if (Arrays.equals(pswrdInput.getPassword(), AppState.student.getPassword())) {
			MainWindow mw = new MainWindow();
			mw.setVisible(true);
			dispose();
		} else {
			lblMessage.setText("Incoreect Password.");
		}
	}
	
	public void resetPassword() {
		AppState.loadFromFile();
		ResetPassword rp = new ResetPassword(this, AppState.student.getPassword());
		rp.setVisible(true);
	}
}
