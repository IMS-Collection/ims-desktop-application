package com.spring.mongodb.ims.imsdesktopapplication;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.mongodb.ims.imsdesktopapplication.exceptions.InvalidInputException;
import com.spring.mongodb.ims.imsdesktopapplication.service.CustomerService;
import com.spring.mongodb.ims.imsdesktopapplication.service.EmployeeService;
import com.spring.mongodb.ims.imsdesktopapplication.service.ImsService;
import com.spring.mongodb.ims.imsdesktopapplication.service.ProductService;
import com.spring.mongodb.ims.imsdesktopapplication.service.TransactionService;
import com.spring.mongodb.ims.imsdesktopapplication.shared.dto.EmployeeDTO;

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
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField userNameField;
	private JTextField emailField;
	private JPasswordField passwordRegisterField;
	private JPasswordField confirmPasswordField;
	private JLabel errorRegisterMessage;
	private JPanel registerPanel;

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
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					error = "";
					layeredPane.removeAll();
					layeredPane.add(registerPanel);
					layeredPane.repaint();
					layeredPane.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
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
		
		registerPanel = new JPanel();
		registerPanel.setBackground(Color.decode("#A683E3"));
		layeredPane.add(registerPanel, "name_61084217787461");
		
		JLabel lblRegisterPanel = new JLabel("REGISTRATION PAGE");
		lblRegisterPanel.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		lblRegisterPanel.setForeground(Color.WHITE);
		
		JLabel lblDededonMotorsInventory = new JLabel("DE-DEDON MOTORS INVENTORY MANAGEMENT SYSTEM");
		lblDededonMotorsInventory.setForeground(Color.WHITE);
		lblDededonMotorsInventory.setFont(new Font("AppleMyungjo", Font.BOLD, 20));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.decode("#2dad5a"));
		GroupLayout gl_registerPanel = new GroupLayout(registerPanel);
		gl_registerPanel.setHorizontalGroup(
			gl_registerPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_registerPanel.createSequentialGroup()
					.addGroup(gl_registerPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_registerPanel.createSequentialGroup()
							.addGap(291)
							.addComponent(lblRegisterPanel))
						.addGroup(gl_registerPanel.createSequentialGroup()
							.addGap(116)
							.addComponent(lblDededonMotorsInventory, GroupLayout.PREFERRED_SIZE, 616, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_registerPanel.createSequentialGroup()
							.addGap(131)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 520, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(139, Short.MAX_VALUE))
		);
		gl_registerPanel.setVerticalGroup(
			gl_registerPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_registerPanel.createSequentialGroup()
					.addComponent(lblDededonMotorsInventory, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRegisterPanel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblEmployeeRegistration = new JLabel("Employee Registration");
		lblEmployeeRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployeeRegistration.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblEmployeeRegistration.setForeground(Color.WHITE);
		
		errorRegisterMessage = new JLabel("");
		errorRegisterMessage.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		errorRegisterMessage.setHorizontalAlignment(SwingConstants.CENTER);
		errorRegisterMessage.setForeground(Color.RED);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(Color.WHITE);
		
		firstNameField = new JTextField();
		firstNameField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(Color.WHITE);
		
		lastNameField = new JTextField();
		lastNameField.setColumns(10);
		
		JLabel lblUserName_1 = new JLabel("User Name");
		lblUserName_1.setForeground(Color.WHITE);
		
		userNameField = new JTextField();
		userNameField.setColumns(10);
		
		JLabel lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setForeground(Color.WHITE);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setForeground(Color.WHITE);
		
		passwordRegisterField = new JPasswordField();
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setForeground(Color.WHITE);
		
		confirmPasswordField = new JPasswordField();
		
		JCheckBox chckbxShowPassword = new JCheckBox("Show Password");
		chckbxShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxShowPassword.isSelected()) {
					passwordRegisterField.setEchoChar((char)0);
					confirmPasswordField.setEchoChar((char)0);
				} else {
					passwordRegisterField.setEchoChar('*');
					confirmPasswordField.setEchoChar('*');
				}
			}
		});
		chckbxShowPassword.setForeground(Color.WHITE);
		
		JButton btnRegister_1 = new JButton("REGISTER");
		btnRegister_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerActionPerformed(e);
			}
		});
		
		JButton btnBack = new JButton("BACK");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					error = "";
					layeredPane.removeAll();
					layeredPane.add(loginPanel);
					layeredPane.repaint();
					layeredPane.revalidate();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(errorRegisterMessage, GroupLayout.PREFERRED_SIZE, 504, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(16)
							.addComponent(lblEmployeeRegistration, GroupLayout.PREFERRED_SIZE, 468, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(24)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(firstNameField, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
								.addComponent(lblFirstName)
								.addComponent(lblLastName)
								.addComponent(lastNameField)
								.addComponent(lblUserName_1)
								.addComponent(userNameField)
								.addComponent(lblEmailAddress)
								.addComponent(emailField)
								.addComponent(lblPassword_1)
								.addComponent(passwordRegisterField)
								.addComponent(lblConfirmPassword)
								.addComponent(confirmPasswordField)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(chckbxShowPassword)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnRegister_1))
								.addComponent(btnBack))))
					.addContainerGap(10, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblEmployeeRegistration)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(errorRegisterMessage)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblFirstName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(firstNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblLastName)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lastNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblUserName_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblEmailAddress)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPassword_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordRegisterField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblConfirmPassword)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(confirmPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxShowPassword)
						.addComponent(btnRegister_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBack)
					.addContainerGap(7, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		registerPanel.setLayout(gl_registerPanel);
	}
	
	private void registerActionPerformed(ActionEvent e) {
		
		error = "";
		String firstName = firstNameField.getText().trim();
		String lastName = lastNameField.getText().trim();
		String userName = userNameField.getText().trim();
		String email = emailField.getText().trim();
		char [] employeePassword1 = passwordRegisterField.getPassword();
		String password1 = new String(employeePassword1);
		char [] employeePassword2 = confirmPasswordField.getPassword();
		String password2 = new String(employeePassword2);
		boolean status = true;
		
		if (password1 == null || password1.length() == 0 
				|| password2 == null || password2.length() == 0) {
			error = "The password field is empty.";
		} else {
			if (password1.equals(password2)) {
				try {
					EmployeeDTO employeeDTO = new EmployeeDTO();
					employeeDTO.setFirstName(firstName);
					employeeDTO.setLastName(lastName);
					employeeDTO.setUserName(userName);
					employeeDTO.setEmail(email);
					employeeDTO.setPassword(password1);
					employeeService.createEmployee(employeeDTO);
				} catch (InvalidInputException er) {
					status = false;
					error = er.getMessage();
				}
				if(status) {
					JOptionPane.showMessageDialog(ImsMainPage.getFrame(), "successfully registered", "Registration Status",
							JOptionPane.CLOSED_OPTION);
				}
			} else {
				error = "The two password are not equal.";
			}
		}
		refreshRegisterData();
	}
	
	private void refreshLoginData() {
		//error
		errorMessage.setText(error);
		
		//clear contents
		userName.setText("");
		passwordField.setText("");
	}
	
	private void refreshRegisterData() {
		//error
		errorRegisterMessage.setText(error);
		
		firstNameField.setText("");
		lastNameField.setText("");
		userNameField.setText("");
		emailField.setText("");
		passwordRegisterField.setText("");
		confirmPasswordField.setText("");
	}
}
