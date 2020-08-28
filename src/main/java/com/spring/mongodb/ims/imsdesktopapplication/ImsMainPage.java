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
import javax.swing.JTextArea;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

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
	private JTextField textFieldProductQuantity;
	private JTextField textFieldProductPrice;
	private JTextField textFieldProductName;
	private JTextField textFieldUpdateProductName;
	private JTextField textFieldUpdateProductPrice;
	private JTextField textFieldUpdateProductQuantity;
	private JTextField textFieldTransactionCustomerID;
	private JTextField textFieldTransactionProductQuantity;
	private JTextField textFieldAmountPaid;
	private JTextField textField_9;
	private JTextField textFieldCustomerName;
	private JTextField textFieldCustomerID;
	private JTextField textFieldUpdateCustomerName;
	private JTextField textFieldUpdateCustomerID;
	private JTextField textFieldCurrentPaid;
	private JTextField textFieldUpdatePaid;

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
		setBounds(100, 100, 965, 649);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#d3d2d4"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(6, 6, 953, 615);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(Color.decode("#d3d2d4"));
		layeredPane.add(loginPanel, "name_60650339811448");
		
		JLabel lblLoginPage = new JLabel("DE-DEDON MOTORS INVENTORY ");
		lblLoginPage.setForeground(Color.decode("#2dad5a"));
		lblLoginPage.setFont(new Font("AppleMyungjo", Font.BOLD, 25));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.decode("#d3d2d4"));
		panel.setBorder(new LineBorder(Color.decode("#2dad5a"), 2));
		
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
		lblManagementSystem.setForeground(Color.decode("#2dad5a"));
		lblManagementSystem.setFont(new Font("AppleMyungjo", Font.BOLD, 25));
		
		JLabel lblNotHaveAccount = new JLabel("Not have account?");
		lblNotHaveAccount.setForeground(Color.BLACK);
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
		lblUserLogin.setForeground(Color.BLACK);
		
		userName = new JTextField();
		userName.setColumns(10);
		
		passwordField = new JPasswordField();
		
		JLabel lblUserName = new JLabel("User Name or Email Address");
		lblUserName.setForeground(Color.BLACK);
		lblUserName.setFont(new Font("SansSerif", Font.PLAIN, 18));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblPassword.setForeground(Color.BLACK);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setForeground(Color.decode("#2dad5a"));
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
		btnExit.setForeground(Color.RED);
		
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
		imsPagePanel.setForeground(Color.decode("#d3d2d4"));
		layeredPane.add(imsPagePanel, "name_60694851832107");
		imsPagePanel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(47, 79, 79));
		panel_2.setBounds(6, 6, 941, 78);
		imsPagePanel.add(panel_2);
		
		JLabel label = new JLabel("In God We Trust");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.PINK);
		label.setFont(new Font("Tahoma", Font.ITALIC, 22));
		label.setBounds(247, 38, 162, 27);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("De-Don Motors Inventory Management System");
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		label_1.setBounds(48, 6, 794, 36);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		label_2.setBounds(296, 71, 618, 20);
		panel_2.add(label_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(85, 107, 47));
		panel_3.setBounds(6, 81, 176, 501);
		imsPagePanel.add(panel_3);
		
		JLabel lblProducts = new JLabel("Products");
		lblProducts.setForeground(Color.GREEN);
		lblProducts.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblProducts.setBackground(new Color(85, 107, 47));
		
		JLabel lblDashboard = new JLabel("Dashboard");
		lblDashboard.setForeground(Color.WHITE);
		lblDashboard.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDashboard.setBackground(new Color(85, 107, 47));
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.setForeground(Color.MAGENTA);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnLogout.setBorder(new LineBorder(new Color(0, 0, 255)));
		
		JLabel lblTransaction = new JLabel("Transactions");
		lblTransaction.setForeground(Color.GREEN);
		lblTransaction.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTransaction.setBackground(new Color(85, 107, 47));
		
		JLabel lblAccounts = new JLabel("Accounts");
		lblAccounts.setForeground(Color.GREEN);
		lblAccounts.setFont(new Font("Tahoma", Font.BOLD, 24));
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setForeground(Color.MAGENTA);
		lblWelcome.setFont(new Font("Times New Roman", Font.BOLD, 36));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_3.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(btnLogout)
						.addComponent(lblAccounts, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblProducts, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblDashboard, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblWelcome, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
							.addComponent(lblTransaction, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addContainerGap(111, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addComponent(lblWelcome)
					.addGap(18)
					.addComponent(lblDashboard)
					.addGap(18)
					.addComponent(lblProducts)
					.addGap(28)
					.addComponent(lblTransaction)
					.addGap(18)
					.addComponent(lblAccounts)
					.addGap(40)
					.addComponent(btnLogout)
					.addGap(183))
		);
		panel_3.setLayout(gl_panel_3);
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(183, 81, 764, 528);
		imsPagePanel.add(layeredPane_1);
		layeredPane_1.setLayout(new CardLayout(0, 0));
		
		JPanel dashBoardPanel = new JPanel();
		layeredPane_1.add(dashBoardPanel, "name_118134937110699");
		
		JTextArea textArea = new JTextArea();
		textArea.setText("Warmly welcome to De Don Motors Inventory \r\nManagement Application. Please, feel free explore \r\nand thoroughly test the app and get to me with the\r\n comhrehensive feed back including bugs!");
		textArea.setForeground(new Color(128, 128, 0));
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
		GroupLayout gl_dashBoardPanel = new GroupLayout(dashBoardPanel);
		gl_dashBoardPanel.setHorizontalGroup(
			gl_dashBoardPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 830, Short.MAX_VALUE)
				.addGroup(gl_dashBoardPanel.createSequentialGroup()
					.addGap(59)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 639, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(132, Short.MAX_VALUE))
		);
		gl_dashBoardPanel.setVerticalGroup(
			gl_dashBoardPanel.createParallelGroup(Alignment.LEADING)
				.addGap(0, 509, Short.MAX_VALUE)
				.addGroup(gl_dashBoardPanel.createSequentialGroup()
					.addGap(65)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(190, Short.MAX_VALUE))
		);
		dashBoardPanel.setLayout(gl_dashBoardPanel);
		
		JPanel productsPanel = new JPanel();
		layeredPane_1.add(productsPanel, "name_118179738325051");
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new LineBorder(new Color(0, 0, 255)));
		
		JLabel label_8 = new JLabel("Add Product");
		label_8.setForeground(new Color(0, 100, 0));
		label_8.setFont(new Font("Dialog", Font.BOLD, 24));
		label_8.setBounds(73, 18, 158, 20);
		panel_6.add(label_8);
		
		JLabel label_9 = new JLabel("Name");
		label_9.setBounds(13, 57, 41, 20);
		panel_6.add(label_9);
		
		JLabel label_10 = new JLabel("Price");
		label_10.setBounds(13, 101, 34, 20);
		panel_6.add(label_10);
		
		JLabel label_11 = new JLabel("Quantity");
		label_11.setBounds(13, 145, 60, 20);
		panel_6.add(label_11);
		
		JButton btnAddProduct = new JButton("ADD PRODUCT");
		btnAddProduct.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		btnAddProduct.setBounds(88, 177, 143, 29);
		panel_6.add(btnAddProduct);
		
		textFieldProductQuantity = new JTextField();
		textFieldProductQuantity.setColumns(10);
		textFieldProductQuantity.setBorder(new LineBorder(new Color(0, 0, 255)));
		textFieldProductQuantity.setBounds(88, 142, 200, 26);
		panel_6.add(textFieldProductQuantity);
		
		textFieldProductPrice = new JTextField();
		textFieldProductPrice.setColumns(10);
		textFieldProductPrice.setBorder(new LineBorder(new Color(0, 0, 255)));
		textFieldProductPrice.setBounds(88, 98, 200, 26);
		panel_6.add(textFieldProductPrice);
		
		textFieldProductName = new JTextField();
		textFieldProductName.setColumns(10);
		textFieldProductName.setBorder(new LineBorder(new Color(0, 0, 255)));
		textFieldProductName.setBounds(88, 54, 200, 26);
		panel_6.add(textFieldProductName);
		
		JLabel label_12 = new JLabel("Products");
		label_12.setForeground(new Color(0, 128, 0));
		label_12.setFont(new Font("Dialog", Font.BOLD, 24));
		label_12.setBounds(94, 242, 158, 29);
		panel_6.add(label_12);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLUE);
		separator.setBounds(0, 230, 324, 20);
		panel_6.add(separator);
		
		JComboBox<String> comboBoxProduct = new JComboBox<String>();
		comboBoxProduct.setBorder(new LineBorder(new Color(0, 0, 255)));
		comboBoxProduct.setBounds(88, 277, 200, 26);
		panel_6.add(comboBoxProduct);
		
		textFieldUpdateProductName = new JTextField();
		textFieldUpdateProductName.setColumns(10);
		textFieldUpdateProductName.setBorder(new LineBorder(new Color(0, 0, 255)));
		textFieldUpdateProductName.setBounds(88, 319, 200, 26);
		panel_6.add(textFieldUpdateProductName);
		
		textFieldUpdateProductPrice = new JTextField();
		textFieldUpdateProductPrice.setColumns(10);
		textFieldUpdateProductPrice.setBorder(new LineBorder(new Color(0, 0, 255)));
		textFieldUpdateProductPrice.setBounds(88, 361, 200, 26);
		panel_6.add(textFieldUpdateProductPrice);
		
		textFieldUpdateProductQuantity = new JTextField();
		textFieldUpdateProductQuantity.setColumns(10);
		textFieldUpdateProductQuantity.setBorder(new LineBorder(new Color(0, 0, 255)));
		textFieldUpdateProductQuantity.setBounds(88, 403, 200, 26);
		panel_6.add(textFieldUpdateProductQuantity);
		
		JLabel label_13 = new JLabel("Product");
		label_13.setBounds(13, 280, 69, 20);
		panel_6.add(label_13);
		
		JLabel label_14 = new JLabel("Name");
		label_14.setBounds(13, 322, 69, 20);
		panel_6.add(label_14);
		
		JLabel label_15 = new JLabel("Price");
		label_15.setBounds(13, 364, 69, 20);
		panel_6.add(label_15);
		
		JLabel label_16 = new JLabel("Quantity");
		label_16.setBounds(13, 409, 69, 20);
		panel_6.add(label_16);
		
		JButton btnUpdateProduct = new JButton("UPDATE");
		btnUpdateProduct.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		btnUpdateProduct.setBounds(88, 437, 102, 29);
		panel_6.add(btnUpdateProduct);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		btnDelete.setBounds(199, 437, 89, 29);
		panel_6.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label_17 = new JLabel("Click each column to sort the items");
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_productsPanel = new GroupLayout(productsPanel);
		gl_productsPanel.setHorizontalGroup(
			gl_productsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_productsPanel.createSequentialGroup()
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_productsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 404, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_17, GroupLayout.PREFERRED_SIZE, 443, GroupLayout.PREFERRED_SIZE)))
		);
		gl_productsPanel.setVerticalGroup(
			gl_productsPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_6, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
				.addGroup(gl_productsPanel.createSequentialGroup()
					.addGap(6)
					.addComponent(label_17, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 449, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		productsPanel.setLayout(gl_productsPanel);
		
		JPanel transactionsPanel = new JPanel();
		transactionsPanel.setLayout(null);
		layeredPane_1.add(transactionsPanel, "name_118242517273668");
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new LineBorder(new Color(0, 0, 255)));
		panel_8.setBounds(0, 0, 321, 509);
		transactionsPanel.add(panel_8);
		
		JLabel label_18 = new JLabel("Customer ID");
		label_18.setBounds(15, 31, 98, 20);
		panel_8.add(label_18);
		
		textFieldTransactionCustomerID = new JTextField();
		textFieldTransactionCustomerID.setColumns(10);
		textFieldTransactionCustomerID.setBounds(128, 28, 178, 26);
		panel_8.add(textFieldTransactionCustomerID);
		
		JButton btnOk = new JButton("SIGN IN");
		btnOk.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnOk.setBounds(128, 61, 80, 29);
		panel_8.add(btnOk);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 101, 321, 10);
		panel_8.add(separator_1);
		
		JLabel label_19 = new JLabel("Add Products for Purchase");
		label_19.setBounds(15, 127, 193, 20);
		panel_8.add(label_19);
		
		JLabel label_20 = new JLabel("Product");
		label_20.setBounds(15, 178, 69, 20);
		panel_8.add(label_20);
		
		JComboBox<String> comboBoxTransactionProduct = new JComboBox<String>();
		comboBoxTransactionProduct.setBounds(128, 175, 178, 26);
		panel_8.add(comboBoxTransactionProduct);
		
		JLabel label_21 = new JLabel("Quantity");
		label_21.setBounds(15, 226, 69, 20);
		panel_8.add(label_21);
		
		textFieldTransactionProductQuantity = new JTextField();
		textFieldTransactionProductQuantity.setColumns(10);
		textFieldTransactionProductQuantity.setBounds(128, 223, 178, 26);
		panel_8.add(textFieldTransactionProductQuantity);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnAdd.setBounds(128, 265, 115, 29);
		panel_8.add(btnAdd);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 309, 321, 10);
		panel_8.add(separator_2);
		
		JButton btnSubmit = new JButton("SUBMIT");
		btnSubmit.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnSubmit.setBounds(128, 380, 115, 29);
		panel_8.add(btnSubmit);
		
		JLabel label_22 = new JLabel("AmoundPaid");
		label_22.setBounds(15, 335, 115, 20);
		panel_8.add(label_22);
		
		textFieldAmountPaid = new JTextField();
		textFieldAmountPaid.setColumns(10);
		textFieldAmountPaid.setBounds(128, 335, 178, 26);
		panel_8.add(textFieldAmountPaid);
		
		JLabel label_23 = new JLabel("");
		label_23.setForeground(Color.BLUE);
		label_23.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_23.setBounds(208, 127, 98, 32);
		panel_8.add(label_23);
		
		JButton btnSignOut = new JButton("SIGN OUT");
		btnSignOut.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnSignOut.setBounds(215, 61, 91, 29);
		panel_8.add(btnSignOut);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnClear.setBounds(128, 456, 115, 29);
		panel_8.add(btnClear);
		
		JLabel label_24 = new JLabel("");
		label_24.setForeground(Color.GRAY);
		label_24.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_24.setBounds(128, 0, 178, 20);
		panel_8.add(label_24);
		
		JButton btnShowReceipt = new JButton("SHOW RECEIPT");
		btnShowReceipt.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnShowReceipt.setBounds(128, 422, 115, 29);
		panel_8.add(btnShowReceipt);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(336, 0, 422, 454);
		transactionsPanel.add(scrollPane_1);
		
		JButton btnUpdateQuantity = new JButton("Update");
		btnUpdateQuantity.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnUpdateQuantity.setBounds(470, 464, 111, 29);
		transactionsPanel.add(btnUpdateQuantity);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(596, 465, 102, 26);
		transactionsPanel.add(textField_9);
		
		JLabel label_25 = new JLabel("");
		label_25.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_25.setBounds(713, 468, 102, 20);
		transactionsPanel.add(label_25);
		
		JButton btnRemove = new JButton("REMOVE");
		btnRemove.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnRemove.setBounds(336, 464, 111, 29);
		transactionsPanel.add(btnRemove);
		
		JPanel accountsPanel = new JPanel();
		accountsPanel.setLayout(null);
		layeredPane_1.add(accountsPanel, "name_118280808053760");
		
		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBorder(new LineBorder(new Color(0, 0, 255)));
		panel_10.setBounds(0, 0, 338, 493);
		accountsPanel.add(panel_10);
		
		JLabel label_26 = new JLabel("Register Customer");
		label_26.setForeground(new Color(0, 128, 0));
		label_26.setFont(new Font("Dialog", Font.BOLD, 24));
		label_26.setBounds(62, 16, 231, 30);
		panel_10.add(label_26);
		
		JLabel label_27 = new JLabel("Name");
		label_27.setBounds(15, 62, 69, 20);
		panel_10.add(label_27);
		
		textFieldCustomerName = new JTextField();
		textFieldCustomerName.setColumns(10);
		textFieldCustomerName.setBounds(131, 62, 192, 26);
		panel_10.add(textFieldCustomerName);
		
		JLabel label_28 = new JLabel("Customer ID");
		label_28.setBounds(15, 109, 90, 20);
		panel_10.add(label_28);
		
		textFieldCustomerID = new JTextField();
		textFieldCustomerID.setColumns(10);
		textFieldCustomerID.setBounds(131, 104, 192, 26);
		panel_10.add(textFieldCustomerID);
		
		JButton btnRegisterCustomer = new JButton("REGISTER");
		btnRegisterCustomer.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnRegisterCustomer.setBounds(131, 150, 115, 29);
		panel_10.add(btnRegisterCustomer);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBorder(null);
		separator_3.setBackground(Color.BLUE);
		separator_3.setBounds(0, 196, 338, 9);
		panel_10.add(separator_3);
		
		JLabel label_29 = new JLabel("Customer Details");
		label_29.setForeground(new Color(0, 128, 0));
		label_29.setFont(new Font("Dialog", Font.BOLD, 24));
		label_29.setBounds(62, 210, 231, 30);
		panel_10.add(label_29);
		
		JComboBox<String> comboBoxCustomer = new JComboBox<String>();
		comboBoxCustomer.setBounds(131, 252, 192, 26);
		panel_10.add(comboBoxCustomer);
		
		JLabel label_30 = new JLabel("Customer");
		label_30.setBounds(15, 256, 69, 20);
		panel_10.add(label_30);
		
		textFieldUpdateCustomerName = new JTextField();
		textFieldUpdateCustomerName.setColumns(10);
		textFieldUpdateCustomerName.setBounds(131, 294, 192, 26);
		panel_10.add(textFieldUpdateCustomerName);
		
		JLabel label_31 = new JLabel("Name");
		label_31.setBounds(15, 297, 69, 20);
		panel_10.add(label_31);
		
		textFieldUpdateCustomerID = new JTextField();
		textFieldUpdateCustomerID.setColumns(10);
		textFieldUpdateCustomerID.setBounds(131, 336, 192, 26);
		panel_10.add(textFieldUpdateCustomerID);
		
		JLabel label_32 = new JLabel("Customer ID");
		label_32.setBounds(15, 339, 101, 20);
		panel_10.add(label_32);
		
		JButton btnDelete_1 = new JButton("DELETE");
		btnDelete_1.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnDelete_1.setBounds(233, 378, 90, 29);
		panel_10.add(btnDelete_1);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnUpdate.setBounds(131, 378, 93, 29);
		panel_10.add(btnUpdate);
		
		JLabel label_33 = new JLabel("Transactions");
		label_33.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_33.setBounds(353, 16, 239, 20);
		accountsPanel.add(label_33);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(353, 52, 433, 385);
		accountsPanel.add(scrollPane_2);
		
		JButton button_16 = new JButton("UPDATE");
		button_16.setBorder(new LineBorder(new Color(128, 0, 0)));
		button_16.setBounds(497, 473, 115, 20);
		accountsPanel.add(button_16);
		
		textFieldCurrentPaid = new JTextField();
		textFieldCurrentPaid.setColumns(10);
		textFieldCurrentPaid.setBounds(363, 448, 124, 20);
		accountsPanel.add(textFieldCurrentPaid);
		
		JLabel label_34 = new JLabel("");
		label_34.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_34.setBounds(644, 457, 105, 20);
		accountsPanel.add(label_34);
		
		textFieldUpdatePaid = new JTextField();
		textFieldUpdatePaid.setColumns(10);
		textFieldUpdatePaid.setBounds(497, 448, 115, 20);
		accountsPanel.add(textFieldUpdatePaid);
		
		JLabel label_35 = new JLabel("AMOUNT PAID");
		label_35.setBorder(new LineBorder(new Color(128, 0, 0)));
		label_35.setBounds(373, 476, 101, 14);
		accountsPanel.add(label_35);
		
		JPanel receiptPanel = new JPanel();
		receiptPanel.setLayout(null);
		layeredPane_1.add(receiptPanel, "name_118353732281874");
		
		JButton btnPrint = new JButton("PRINT");
		btnPrint.setBounds(15, 36, 115, 29);
		receiptPanel.add(btnPrint);
		
		JButton btnClose = new JButton("CLOSE");
		btnClose.setBounds(15, 89, 115, 29);
		receiptPanel.add(btnClose);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(169, 16, 632, 477);
		receiptPanel.add(scrollPane_3);
		
		registerPanel = new JPanel();
		registerPanel.setBackground(Color.decode("#d3d2d4"));
		layeredPane.add(registerPanel, "name_61084217787461");
		
		JLabel lblRegisterPanel = new JLabel("REGISTRATION PAGE");
		lblRegisterPanel.setFont(new Font("American Typewriter", Font.PLAIN, 15));
		lblRegisterPanel.setForeground(Color.decode("#2dad5a"));
		
		JLabel lblDededonMotorsInventory = new JLabel("DE-DEDON MOTORS INVENTORY MANAGEMENT SYSTEM");
		lblDededonMotorsInventory.setForeground(Color.decode("#2dad5a"));
		lblDededonMotorsInventory.setFont(new Font("AppleMyungjo", Font.BOLD, 20));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.decode("#2dad5a")));
		panel_1.setBackground(Color.decode("#d3d2d4"));
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
		lblEmployeeRegistration.setForeground(Color.BLACK);
		
		errorRegisterMessage = new JLabel("");
		errorRegisterMessage.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		errorRegisterMessage.setHorizontalAlignment(SwingConstants.CENTER);
		errorRegisterMessage.setForeground(Color.RED);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setForeground(Color.BLACK);
		
		firstNameField = new JTextField();
		firstNameField.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setForeground(Color.BLACK);
		
		lastNameField = new JTextField();
		lastNameField.setColumns(10);
		
		JLabel lblUserName_1 = new JLabel("User Name");
		lblUserName_1.setForeground(Color.BLACK);
		
		userNameField = new JTextField();
		userNameField.setColumns(10);
		
		JLabel lblEmailAddress = new JLabel("Email Address");
		lblEmailAddress.setForeground(Color.BLACK);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		
		JLabel lblPassword_1 = new JLabel("Password");
		lblPassword_1.setForeground(Color.BLACK);
		
		passwordRegisterField = new JPasswordField();
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setForeground(Color.BLACK);
		
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
		chckbxShowPassword.setForeground(Color.RED);
		
		JButton btnRegister_1 = new JButton("REGISTER");
		btnRegister_1.setForeground(Color.decode("#2dad5a"));
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
