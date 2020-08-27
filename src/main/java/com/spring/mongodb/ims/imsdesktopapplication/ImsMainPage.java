package com.spring.mongodb.ims.imsdesktopapplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.mongodb.ims.imsdesktopapplication.exceptions.InvalidInputException;
import com.spring.mongodb.ims.imsdesktopapplication.service.CustomerService;
import com.spring.mongodb.ims.imsdesktopapplication.service.EmployeeService;
import com.spring.mongodb.ims.imsdesktopapplication.service.ImsService;
import com.spring.mongodb.ims.imsdesktopapplication.service.ProductService;
import com.spring.mongodb.ims.imsdesktopapplication.service.TransactionService;
import com.spring.mongodb.ims.imsdesktopapplication.service.impl.EmployeeServiceImpl;
import com.spring.mongodb.ims.imsdesktopapplication.shared.dto.EmployeeDTO;

import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

@SpringBootApplication
public class ImsMainPage extends ImsDesktopApplication {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6112156228697064281L;
	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField passwordField;
	private String error = "";
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	ImsService imsService;
	private JPanel imsPagePanel;
	private JLabel errorMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImsMainPage frame = new ImsMainPage();
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
	public ImsMainPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 883, 622);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#A683E3"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(6, 6, 871, 588);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(Color.decode("#A683E3"));
		layeredPane.add(loginPanel, "name_60650339811448");
		
		JLabel lblLoginPage = new JLabel("DE-DEDON MOTORS INVENTORY ");
		lblLoginPage.setForeground(Color.WHITE);
		lblLoginPage.setFont(new Font("AppleMyungjo", Font.BOLD, 25));
		
		JPanel panel = new JPanel();
		//panel.setBackground(Color.decode("#669999"));
		panel.setBackground(Color.decode("#2dad5a"));
		panel.setBorder(new LineBorder(Color.WHITE, 2));
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setBackground(Color.BLUE);
		
		JLabel lblManagementSystem = new JLabel("MANAGEMENT SYSTEM");
		lblManagementSystem.setForeground(Color.WHITE);
		lblManagementSystem.setFont(new Font("AppleMyungjo", Font.BOLD, 25));
		
		JLabel lblNotHaveAccount = new JLabel("Not have account?");
		lblNotHaveAccount.setForeground(Color.WHITE);
		lblNotHaveAccount.setFont(new Font("AppleMyungjo", Font.PLAIN, 20));
		GroupLayout gl_loginPanel = new GroupLayout(loginPanel);
		gl_loginPanel.setHorizontalGroup(
			gl_loginPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(236)
					.addComponent(lblManagementSystem, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(316, Short.MAX_VALUE))
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addGap(205)
					.addComponent(lblNotHaveAccount)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(376, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_loginPanel.createSequentialGroup()
					.addContainerGap(190, Short.MAX_VALUE)
					.addGroup(gl_loginPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_loginPanel.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_loginPanel.createSequentialGroup()
							.addComponent(lblLoginPage, GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE)
							.addGap(222))))
		);
		gl_loginPanel.setVerticalGroup(
			gl_loginPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_loginPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblLoginPage, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblManagementSystem)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_loginPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNotHaveAccount)
						.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(172))
		);
		
		JLabel lblUserLogin = new JLabel("User Login");
		lblUserLogin.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblUserLogin.setForeground(Color.WHITE);
		
		userName = new JTextField();
		userName.setColumns(10);
		
		passwordField = new JPasswordField();
		
		JLabel lblUserName = new JLabel("User Name or Email Address");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("SansSerif", Font.PLAIN, 18));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblPassword.setForeground(Color.WHITE);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//clear error
				error = "";
				String name = userName.getText();
				char[] pass = passwordField.getPassword();
				String password = new String(pass);
				
				boolean loggedIn = true;
				
				if (name != null && name.length() > 0) {
					if (password != null && password.length() > 0) {
						try {
							imsService.login(name, password);
						} catch (InvalidInputException er) {
							loggedIn = false;
							error = er.getMessage();
						}
					} else {
						loggedIn = false;
						error = "You can't log in with empty password";
					}
				} else {
					loggedIn = false;
					error = "You can't log in with empty user name";
				}
				if(loggedIn) {
					/**
					 * Launch the main page.
					 */
					try {
						error = "";
						layeredPane.removeAll();
						layeredPane.add(imsPagePanel);
						layeredPane.repaint();
						layeredPane.revalidate();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
				//update visuals
				refreshLoginData();
			}
		});
		
		JButton btnExit = new JButton("EXIT");
		
		errorMessage = new JLabel("");
		errorMessage.setFont(new Font("SansSerif", Font.BOLD, 16));
		errorMessage.setForeground(Color.RED);
		errorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnLogin)
							.addGap(18)
							.addComponent(btnExit))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblUserName)
								.addComponent(lblPassword)
								.addComponent(passwordField)
								.addComponent(userName, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))))
					.addGap(108))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(132)
					.addComponent(lblUserLogin)
					.addContainerGap(176, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(errorMessage, GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(lblUserLogin, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(errorMessage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblUserName, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(userName, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		panel.setLayout(gl_panel);
		loginPanel.setLayout(gl_loginPanel);
		
		imsPagePanel = new JPanel();
		layeredPane.add(imsPagePanel, "name_60694851832107");
		
		JLabel lblMainPage = new JLabel("main page");
		GroupLayout gl_imsPagePanel = new GroupLayout(imsPagePanel);
		gl_imsPagePanel.setHorizontalGroup(
			gl_imsPagePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_imsPagePanel.createSequentialGroup()
					.addGap(84)
					.addComponent(lblMainPage)
					.addContainerGap(293, Short.MAX_VALUE))
		);
		gl_imsPagePanel.setVerticalGroup(
			gl_imsPagePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_imsPagePanel.createSequentialGroup()
					.addGap(91)
					.addComponent(lblMainPage)
					.addContainerGap(159, Short.MAX_VALUE))
		);
		imsPagePanel.setLayout(gl_imsPagePanel);
		
		JPanel registerPanel = new JPanel();
		layeredPane.add(registerPanel, "name_61084217787461");
		
		JLabel lblRegisterPanel = new JLabel("register panel");
		GroupLayout gl_registerPanel = new GroupLayout(registerPanel);
		gl_registerPanel.setHorizontalGroup(
			gl_registerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_registerPanel.createSequentialGroup()
					.addGap(112)
					.addComponent(lblRegisterPanel)
					.addContainerGap(265, Short.MAX_VALUE))
		);
		gl_registerPanel.setVerticalGroup(
			gl_registerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_registerPanel.createSequentialGroup()
					.addGap(78)
					.addComponent(lblRegisterPanel)
					.addContainerGap(172, Short.MAX_VALUE))
		);
		registerPanel.setLayout(gl_registerPanel);
	}
	
	private void refreshLoginData() {
		//error
		errorMessage.setText(error);
		
		//clear contents
		userName.setText("");
		passwordField.setText("");
	}
}
