package com.spring.mongodb.ims.imsdesktopapplication;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.mongodb.ims.imsdesktopapplication.exceptions.InvalidInputException;
import com.spring.mongodb.ims.imsdesktopapplication.service.CustomerService;
import com.spring.mongodb.ims.imsdesktopapplication.service.EmployeeService;
import com.spring.mongodb.ims.imsdesktopapplication.service.ImsService;
import com.spring.mongodb.ims.imsdesktopapplication.service.ProductService;
import com.spring.mongodb.ims.imsdesktopapplication.service.TransactionService;
import com.spring.mongodb.ims.imsdesktopapplication.shared.dto.CustomerDTO;
import com.spring.mongodb.ims.imsdesktopapplication.shared.dto.EmployeeDTO;
import com.spring.mongodb.ims.imsdesktopapplication.shared.dto.ProductDTO;
import com.spring.mongodb.ims.imsdesktopapplication.shared.dto.ProductTransactionDTO;
import com.spring.mongodb.ims.imsdesktopapplication.shared.dto.TransactionDTO;
import com.spring.mongodb.ims.imsdesktopapplication.shared.dto.TransactionDetail;

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
	private String currentTransactionID;
	private int countRefresh = 1;
	private int productIndex;
	
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
	
	//data elements
	//Products
	private HashMap<Integer, String> products;
	private HashMap<Integer, String> transactionProducts;
		
	//Customers
	private HashMap<Integer, String> customers;
	private HashMap<Integer, String> customerTransactions;
	private HashMap<Integer, String> transactionIds;
	String selectedCustomerUserName;
	
	
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
	private JTextField textFieldTransactionProductQuantity;
	private JTextField textFieldPay;
	private JTextField textFieldCustomerLastName;
	private JTextField textFieldCustomerID;
	private JTextField textFieldUpdateCustomerFirstName;
	private JTextField textFieldUpdateCustomerUN;
	private JTextField textFieldCurrentPaid;
	private JTextField textFieldUpdatePaid;
	private JLabel lblTransaction;
	private JLabel lblAccounts;
	private JLabel lblDashboard;
	private JPanel productsPanel;
	private JPanel dashBoardPanel;
	private JPanel accountsPanel;
	private JPanel receiptPanel;
	private JPanel transactionsPanel;
	private JLayeredPane layeredMainPane;
	private JLabel lblErrorMEssage;
	private JTable tableProducts;
	private JComboBox<String> comboBoxProduct;
	private JTextField textFieldPhoneNumber;
	private JComboBox<String> comboBoxCustomer;
	private JTextField textFieldCustomerFirstName;
	private JTextField textFieldUpdateCustomerLastName;
	private JTextField textFieldUpdateCustomerNumber;
	private JTable tableCustomerTransactions;
	private JLabel lblTotalBalance;
	private JTextField textFieldTranUpdateQuantity;
	private JTable tableTransaction;
	private JLabel lblProductTransaction;
	private JLabel lblItemPrice;
	private JComboBox<String> comboBoxTransactionProduct;
	private JLabel labelTranCustomerName;
	private JLabel labelTranCustomerNumber;
	private JLabel lbllabelTranDate;
	private JLabel labelTranAmount;
	private JLabel labelTranAmountPaid;
	private JLabel labelTranAmountLeft;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ImsMainPage frame = new ImsMainPage();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

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
		btnRegister.setBackground(Color.LIGHT_GRAY);
		
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
					.addContainerGap(320, Short.MAX_VALUE)
					.addGroup(gl_loginPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_loginPanel.createSequentialGroup()
							.addGroup(gl_loginPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLoginPage, GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE))
							.addGap(222))
						.addGroup(Alignment.TRAILING, gl_loginPanel.createSequentialGroup()
							.addComponent(lblManagementSystem, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
							.addGap(306))
						.addGroup(Alignment.TRAILING, gl_loginPanel.createSequentialGroup()
							.addComponent(lblNotHaveAccount)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
							.addGap(341))))
		);
		gl_loginPanel.setVerticalGroup(
			gl_loginPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_loginPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblLoginPage, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblManagementSystem)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_loginPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNotHaveAccount))
					.addContainerGap(165, Short.MAX_VALUE))
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
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnLogin)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnExit))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblUserName)
							.addComponent(lblPassword)
							.addComponent(passwordField)
							.addComponent(userName, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)))
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
		panel_2.setBounds(6, 6, 941, 60);
		imsPagePanel.add(panel_2);
		
		JLabel lblDedonMotorsInventory = new JLabel("De-Don Motors Inventory Management System, In God We Trust");
		lblDedonMotorsInventory.setForeground(Color.WHITE);
		lblDedonMotorsInventory.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblDedonMotorsInventory.setBounds(63, 6, 818, 35);
		panel_2.add(lblDedonMotorsInventory);
		
		lblErrorMEssage = new JLabel("");
		lblErrorMEssage.setForeground(Color.RED);
		lblErrorMEssage.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblErrorMEssage.setBounds(137, 34, 618, 20);
		panel_2.add(lblErrorMEssage);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(85, 107, 47));
		panel_3.setBounds(6, 66, 176, 543);
		imsPagePanel.add(panel_3);
		
		JLabel lblProducts = new JLabel("Products");
		lblProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				error = "";
				layeredMainPane.removeAll();
				layeredMainPane.add(productsPanel);
				layeredMainPane.repaint();
				layeredMainPane.revalidate();
				lblDashboard.setForeground(Color.GREEN);
				lblProducts.setForeground(Color.WHITE);
				lblAccounts.setForeground(Color.GREEN);
				lblTransaction.setForeground(Color.GREEN);
				
				refreshProductTable();
				refreshProductPanel();
			}
		});
		lblProducts.setForeground(Color.GREEN);
		lblProducts.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblProducts.setBackground(new Color(85, 107, 47));
		
		lblDashboard = new JLabel("Dashboard");
		lblDashboard.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				error = "";
				layeredMainPane.removeAll();
				layeredMainPane.add(dashBoardPanel);
				layeredMainPane.repaint();
				layeredMainPane.revalidate();
				lblDashboard.setForeground(Color.WHITE);
				lblProducts.setForeground(Color.GREEN);
				lblAccounts.setForeground(Color.GREEN);
				lblTransaction.setForeground(Color.GREEN);
			}
		});
		lblDashboard.setForeground(Color.WHITE);
		lblDashboard.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblDashboard.setBackground(new Color(85, 107, 47));
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(ImsDesktopApplication.getFrame(), 
						"Do you really want to log out?", "Logout confirmation.", 
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (option == 0) {
					try {
						imsService.logout();
						error = "";
						layeredPane.removeAll();
						layeredPane.add(loginPanel);
						layeredPane.repaint();
						layeredPane.revalidate();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnLogout.setForeground(Color.MAGENTA);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnLogout.setBorder(new LineBorder(new Color(0, 0, 255)));
		
		lblTransaction = new JLabel("Transactions");
		lblTransaction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				error = "";
				layeredMainPane.removeAll();
				layeredMainPane.add(transactionsPanel);
				layeredMainPane.repaint();
				layeredMainPane.revalidate();
				lblDashboard.setForeground(Color.GREEN);
				lblProducts.setForeground(Color.GREEN);
				lblAccounts.setForeground(Color.GREEN);
				lblTransaction.setForeground(Color.white);
				
//				refreshTransactionPanel();
			}
		});
		lblTransaction.setForeground(Color.GREEN);
		lblTransaction.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTransaction.setBackground(new Color(85, 107, 47));
		
		lblAccounts = new JLabel("Accounts");
		lblAccounts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				error = "";
				layeredMainPane.removeAll();
				layeredMainPane.add(accountsPanel);
				layeredMainPane.repaint();
				layeredMainPane.revalidate();
				lblDashboard.setForeground(Color.GREEN);
				lblProducts.setForeground(Color.GREEN);
				lblAccounts.setForeground(Color.WHITE);
				lblTransaction.setForeground(Color.GREEN);
				
				refreshAccountPanel();
				//refreshCustomerTransactionTable();
			}
		});
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
		
		layeredMainPane = new JLayeredPane();
		layeredMainPane.setBounds(183, 66, 764, 543);
		imsPagePanel.add(layeredMainPane);
		layeredMainPane.setLayout(new CardLayout(0, 0));
		
		dashBoardPanel = new JPanel();
		layeredMainPane.add(dashBoardPanel, "name_118134937110699");
		
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
		
		productsPanel = new JPanel();
		layeredMainPane.add(productsPanel, "name_118179738325051");
		
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
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent argo0) {

				error = "";
				float price = 0;
				try {
					price = Float.parseFloat(textFieldProductPrice.getText());
				}
				catch (NumberFormatException e) {
					error = "Price figure needs to be a numerical value! ";
				}
				int quantity = 0;
				try {
					quantity = Integer.parseInt(textFieldProductQuantity.getText());
				}
				catch (NumberFormatException e) {
					error = "Quantity figure needs to be a integer value! ";
				}
				
				String name = textFieldProductName.getText();
				
				error.trim();
				
				if (error.length() == 0) {
					try {
						ProductDTO productDTO = new ProductDTO();
						productDTO.setName(name);
						productDTO.setItemPrice(price);
						productDTO.setQuantity(quantity);
						productService.createProduct(productDTO, ImsDesktopApplication.getCurrentEmployeeId());
//						ImsProductController.callCreateProduct();
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				}
				
				refreshProductPanel();
				refreshProductTable();
			}
		});
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
		
		comboBoxProduct = new JComboBox<String>();
		comboBoxProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				int selectedIndex = comboBoxProduct.getSelectedIndex();
				String productName = products.get(selectedIndex);
				if (productName != null) {
					for (ProductDTO p : productService.getProducts(ImsDesktopApplication.getCurrentEmployeeId())) {
						if (productName.equals(p.getName())) {
							textFieldUpdateProductName.setText(productName);
							textFieldUpdateProductPrice.setText(""+p.getItemPrice());
							textFieldUpdateProductQuantity.setText(""+p.getQuantity());
						}
					}
				}
			}
		});
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
		btnUpdateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				error = "";
				float updatePrice = 0;
				try {
					updatePrice = Float.parseFloat(textFieldUpdateProductPrice.getText());
				}
				catch (NumberFormatException e) {
					error = "Price figure needs to be a numerical value! ";
				}
				int updateQuantity = 0;
				try {
					updateQuantity = Integer.parseInt(textFieldUpdateProductQuantity.getText());
				}
				catch (NumberFormatException e) {
					error = error + "Quantity figure needs to be a integer value! ";
				}
				
				String newName = textFieldUpdateProductName.getText();
				
				try {
					int selectedIndex = comboBoxProduct.getSelectedIndex();
					String oldName = products.get(selectedIndex);
					ProductDTO productDTO = new ProductDTO();
					productDTO.setName(newName);
					productDTO.setItemPrice(updatePrice);
					productDTO.setQuantity(updateQuantity);
					productService.updateProduct(oldName, productDTO, ImsDesktopApplication.getCurrentEmployeeId());
				} catch (InvalidInputException e) {
					error = e.getMessage();
				}
				refreshProductTable();
				refreshProductPanel();
			}
		});
		btnUpdateProduct.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		btnUpdateProduct.setBounds(88, 437, 102, 29);
		panel_6.add(btnUpdateProduct);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent earg0) {
				error = "";
				int selectedIndex = comboBoxProduct.getSelectedIndex();
				String productName = products.get(selectedIndex);
				try {
					productService.deleteProduct(productName, ImsDesktopApplication.getCurrentEmployeeId());
				} catch (InvalidInputException er) {
					error = er.getMessage();
				}
				refreshProductPanel();
				refreshProductTable();
			}
		});
		btnDelete.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		btnDelete.setBounds(199, 437, 89, 29);
		panel_6.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		JLabel label_17 = new JLabel("Click each column to sort the items");
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout gl_productsPanel = new GroupLayout(productsPanel);
		gl_productsPanel.setHorizontalGroup(
			gl_productsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_productsPanel.createSequentialGroup()
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_productsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 365, GroupLayout.PREFERRED_SIZE)
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
		
		tableProducts = new JTable();
		tableProducts.setAutoCreateRowSorter(true);
		tableProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) tableProducts.getModel();
				int selectedRow = tableProducts.getSelectedRow();
				
				String name = model.getValueAt(selectedRow, 0).toString();
				String price = model.getValueAt(selectedRow, 1).toString();
				String quantity = model.getValueAt(selectedRow, 2).toString();
			}
		});
		tableProducts.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Price", "Quantity"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				String.class, Float.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		//scrollPane.setColumnHeaderView(tableProducts);
		scrollPane.setViewportView(tableProducts);
		productsPanel.setLayout(gl_productsPanel);
		
		transactionsPanel = new JPanel();
		transactionsPanel.setLayout(null);
		layeredMainPane.add(transactionsPanel, "name_118242517273668");
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new LineBorder(new Color(0, 0, 255)));
		panel_8.setBounds(0, 0, 253, 537);
		transactionsPanel.add(panel_8);
		
		lblProductTransaction = new JLabel("Product");
		lblProductTransaction.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductTransaction.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblProductTransaction.setBounds(25, 36, 193, 29);
		panel_8.add(lblProductTransaction);
		
		JButton btnUpdateTransaction = new JButton("UPDATE");
		btnUpdateTransaction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				error = "";
				String productName = lblProductTransaction.getText();
				int newQuantity = Integer.parseInt(textFieldTranUpdateQuantity.getText());
				try {
					transactionService.updateQuantityTransaction(ImsDesktopApplication.getCurrentEmployeeId(), 
							productName, newQuantity, currentTransactionID);
				} catch (InvalidInputException e) {
					error = e.getMessage();
				}
				refreshTransactionPanel();
				refreshTransactionDetail();
			}
		});
		btnUpdateTransaction.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnUpdateTransaction.setBounds(50, 107, 80, 29);
		panel_8.add(btnUpdateTransaction);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 148, 321, 10);
		panel_8.add(separator_1);
		
		JLabel label_19 = new JLabel("Add Products for Purchase");
		label_19.setBounds(15, 170, 193, 20);
		panel_8.add(label_19);
		
		JLabel label_20 = new JLabel("Product");
		label_20.setBounds(15, 247, 62, 20);
		panel_8.add(label_20);
		
		comboBoxTransactionProduct = new JComboBox<String>();
		comboBoxTransactionProduct.setBounds(81, 245, 154, 26);
		panel_8.add(comboBoxTransactionProduct);
		
		JLabel label_21 = new JLabel("Quantity");
		label_21.setBounds(15, 279, 62, 20);
		panel_8.add(label_21);
		
		textFieldTransactionProductQuantity = new JTextField();
		textFieldTransactionProductQuantity.setColumns(10);
		textFieldTransactionProductQuantity.setBounds(81, 283, 154, 26);
		panel_8.add(textFieldTransactionProductQuantity);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				error = "";
				int selectedIndex = comboBoxTransactionProduct.getSelectedIndex();
				int quantity = 0; 
				if (selectedIndex < 0) {
					error = "Product needs to be selected for transaction";
				} 
				try {
					quantity = Integer.parseInt(textFieldTransactionProductQuantity.getText());
				}
				catch (NumberFormatException e) {
					error = "Quantity number needs to be a numerical value! ";
				}
				if (error.length() == 0) {
					String productName = transactionProducts.get(selectedIndex);
					int option = JOptionPane.showConfirmDialog(ImsDesktopApplication.getFrame(), 
							"Confirm to add "+productName+"("+quantity+")", 
							"Confirm adding product", JOptionPane.OK_CANCEL_OPTION);
					if (option == 0) {
						String employeeId = ImsDesktopApplication.getCurrentEmployeeId(); 
						try {
							transactionService.addTransactionProduct(employeeId, productName, quantity, currentTransactionID);
						} catch (InvalidInputException e1) {
							error = e1.getMessage();
						}
					}
				} 
				refreshTransactionPanel();
				refreshTransactionDetail();
			}
		});
		btnAdd.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnAdd.setBounds(154, 321, 80, 29);
		panel_8.add(btnAdd);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 363, 253, 10);
		panel_8.add(separator_2);
		
		JButton btnSubmit = new JButton("FINALIZE");
		btnSubmit.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnSubmit.setBounds(120, 420, 115, 29);
		panel_8.add(btnSubmit);
		
		JLabel lblPay = new JLabel("Pay");
		lblPay.setBounds(15, 385, 45, 20);
		panel_8.add(lblPay);
		
		textFieldPay = new JTextField();
		textFieldPay.setColumns(10);
		textFieldPay.setBounds(81, 382, 154, 26);
		panel_8.add(textFieldPay);
		
		JLabel label_23 = new JLabel("");
		label_23.setForeground(Color.BLUE);
		label_23.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_23.setBounds(208, 127, 98, 32);
		panel_8.add(label_23);
		
		JButton btnRemoveProduct = new JButton("REMOVE");
		btnRemoveProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRemoveProduct.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnRemoveProduct.setBounds(141, 107, 91, 29);
		panel_8.add(btnRemoveProduct);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnClear.setBounds(120, 502, 115, 29);
		panel_8.add(btnClear);
		
		JLabel label_24 = new JLabel("");
		label_24.setForeground(Color.GRAY);
		label_24.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_24.setBounds(128, 0, 178, 20);
		panel_8.add(label_24);
		
		JButton btnShowReceipt = new JButton("SHOW RECEIPT");
		btnShowReceipt.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnShowReceipt.setBounds(120, 461, 115, 29);
		panel_8.add(btnShowReceipt);
		
		JLabel lblUpdateTransaction = new JLabel("Update Transaction");
		lblUpdateTransaction.setForeground(new Color(0, 128, 0));
		lblUpdateTransaction.setFont(new Font("Dialog", Font.BOLD, 20));
		lblUpdateTransaction.setBounds(18, 6, 217, 27);
		panel_8.add(lblUpdateTransaction);
		
		JLabel lblNewQuantity = new JLabel("New Quantity");
		lblNewQuantity.setBounds(7, 77, 91, 16);
		panel_8.add(lblNewQuantity);
		
		textFieldTranUpdateQuantity = new JTextField();
		textFieldTranUpdateQuantity.setBounds(97, 77, 140, 26);
		panel_8.add(textFieldTranUpdateQuantity);
		textFieldTranUpdateQuantity.setColumns(10);
		
		lblItemPrice = new JLabel("New label");
		lblItemPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblItemPrice.setForeground(Color.GREEN);
		lblItemPrice.setBounds(97, 217, 105, 16);
		panel_8.add(lblItemPrice);
		
		JScrollPane scrollPaneTransaction = new JScrollPane();
		scrollPaneTransaction.setBounds(265, 154, 479, 383);
		transactionsPanel.add(scrollPaneTransaction);
		
		tableTransaction = new JTable();
		tableTransaction.setAutoCreateRowSorter(true);
		tableTransaction.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) tableTransaction.getModel();
				int selectedRow = tableTransaction.getSelectedRow();
				
				String name = model.getValueAt(selectedRow, 0).toString();
				lblProductTransaction.setText(name);
//				String price = model.getValueAt(selectedRow, 1).toString();
//				String quantity = model.getValueAt(selectedRow, 2).toString();
			}
		});
		tableTransaction.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Name", "Unit Price", "Quantity", "Amount"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				String.class, Double.class, Double.class, Double.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		scrollPaneTransaction.setViewportView(tableTransaction);
		
		JLabel label_25 = new JLabel("");
		label_25.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_25.setBounds(713, 468, 102, 20);
		transactionsPanel.add(label_25);
		
		JLabel lblCustomerName = new JLabel("Customer Name");
		lblCustomerName.setBounds(265, 6, 116, 16);
		transactionsPanel.add(lblCustomerName);
		
		labelTranCustomerName = new JLabel("");
		labelTranCustomerName.setBounds(403, 6, 253, 16);
		transactionsPanel.add(labelTranCustomerName);
		
		JLabel lblPhoneNumber_2 = new JLabel("Phone Number");
		lblPhoneNumber_2.setBounds(265, 34, 116, 16);
		transactionsPanel.add(lblPhoneNumber_2);
		
		labelTranCustomerNumber = new JLabel("");
		labelTranCustomerNumber.setBounds(403, 34, 253, 16);
		transactionsPanel.add(labelTranCustomerNumber);
		
		JLabel lblDate = new JLabel("Transaction Date");
		lblDate.setBounds(265, 55, 116, 16);
		transactionsPanel.add(lblDate);
		
		lbllabelTranDate = new JLabel("");
		lbllabelTranDate.setBounds(403, 55, 253, 16);
		transactionsPanel.add(lbllabelTranDate);
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setBounds(265, 75, 102, 16);
		transactionsPanel.add(lblTotalAmount);
		
		labelTranAmount = new JLabel("");
		labelTranAmount.setBounds(403, 75, 253, 16);
		transactionsPanel.add(labelTranAmount);
		
		JLabel lblAmountPaid = new JLabel("Amount Paid");
		lblAmountPaid.setBounds(265, 103, 102, 16);
		transactionsPanel.add(lblAmountPaid);
		
		labelTranAmountPaid = new JLabel("");
		labelTranAmountPaid.setBounds(403, 103, 253, 16);
		transactionsPanel.add(labelTranAmountPaid);
		
		JLabel lblAmountLeft = new JLabel("Amount Left");
		lblAmountLeft.setBounds(265, 126, 102, 16);
		transactionsPanel.add(lblAmountLeft);
		
		labelTranAmountLeft = new JLabel("");
		labelTranAmountLeft.setBounds(403, 126, 253, 16);
		transactionsPanel.add(labelTranAmountLeft);
		
		accountsPanel = new JPanel();
		accountsPanel.setLayout(null);
		layeredMainPane.add(accountsPanel, "name_118280808053760");
		
		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBorder(new LineBorder(new Color(0, 0, 255)));
		panel_10.setBounds(0, 0, 211, 492);
		accountsPanel.add(panel_10);
		
		JLabel label_26 = new JLabel("Register Customer");
		label_26.setForeground(new Color(0, 128, 0));
		label_26.setFont(new Font("Dialog", Font.BOLD, 20));
		label_26.setBounds(6, 6, 196, 30);
		panel_10.add(label_26);
		
		JLabel lblLastName_1 = new JLabel("Last Name");
		lblLastName_1.setBounds(15, 85, 69, 20);
		panel_10.add(lblLastName_1);
		
		textFieldCustomerLastName = new JTextField();
		textFieldCustomerLastName.setColumns(10);
		textFieldCustomerLastName.setBounds(96, 82, 106, 26);
		panel_10.add(textFieldCustomerLastName);
		
		JLabel lblUserName_3 = new JLabel("User Name");
		lblUserName_3.setBounds(15, 123, 90, 20);
		panel_10.add(lblUserName_3);
		
		textFieldCustomerID = new JTextField();
		textFieldCustomerID.setColumns(10);
		textFieldCustomerID.setBounds(96, 120, 106, 26);
		panel_10.add(textFieldCustomerID);
		
		JButton btnRegisterCustomer = new JButton("REGISTER");
		btnRegisterCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				error = "";
				String userName = textFieldCustomerID.getText();
				String firstName = textFieldCustomerFirstName.getText();
				String lastName = textFieldCustomerLastName.getText();
				String phoneNumber = textFieldPhoneNumber.getText();
				try {
					CustomerDTO customerDTO = new CustomerDTO();
					customerDTO.setFirstName(firstName);
					customerDTO.setLastName(lastName);
					customerDTO.setUserName(userName);
					customerDTO.setPhoneNumber(phoneNumber);
					customerService.createCustomer(customerDTO, ImsDesktopApplication.getCurrentEmployeeId());
				} catch (InvalidInputException e) {
					error = e.getMessage();
				}
				refreshAccountPanel();
			}
		});
		btnRegisterCustomer.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnRegisterCustomer.setBounds(128, 191, 69, 29);
		panel_10.add(btnRegisterCustomer);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBorder(null);
		separator_3.setBackground(Color.BLUE);
		separator_3.setBounds(0, 225, 338, 12);
		panel_10.add(separator_3);
		
		JLabel label_29 = new JLabel("Customer Details");
		label_29.setForeground(new Color(0, 128, 0));
		label_29.setFont(new Font("Dialog", Font.BOLD, 20));
		label_29.setBounds(15, 237, 231, 30);
		panel_10.add(label_29);
		
		comboBoxCustomer = new JComboBox<String>();
		comboBoxCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				error = "";
				int index = comboBoxCustomer.getSelectedIndex();
				selectedCustomerUserName = customers.get(index);
				for (CustomerDTO c : customerService.getCustomers()) {
					if (c.getUserName().equals(selectedCustomerUserName)) {
						textFieldUpdateCustomerFirstName.setText(c.getFirstName());	
						textFieldUpdateCustomerLastName.setText(c.getLastName());	
						textFieldUpdateCustomerUN.setText(c.getUserName());
						textFieldUpdateCustomerNumber.setText(c.getPhoneNumber());
						//refreshCustomerTable();
						refreshCustomerTransactionTable();
						countRefresh++;
						}
				}
				//refreshCustomerPanel();
			}
		});
		comboBoxCustomer.setBounds(96, 279, 106, 26);
		panel_10.add(comboBoxCustomer);
		
		JLabel label_30 = new JLabel("Customer");
		label_30.setBounds(15, 279, 69, 20);
		panel_10.add(label_30);
		
		textFieldUpdateCustomerFirstName = new JTextField();
		textFieldUpdateCustomerFirstName.setColumns(10);
		textFieldUpdateCustomerFirstName.setBounds(96, 317, 106, 26);
		panel_10.add(textFieldUpdateCustomerFirstName);
		
		JLabel lblFirstName_1 = new JLabel("First Name");
		lblFirstName_1.setBounds(15, 320, 69, 20);
		panel_10.add(lblFirstName_1);
		
		textFieldUpdateCustomerUN = new JTextField();
		textFieldUpdateCustomerUN.setColumns(10);
		textFieldUpdateCustomerUN.setBounds(96, 377, 106, 26);
		panel_10.add(textFieldUpdateCustomerUN);
		
		JLabel lblUserName_2 = new JLabel("User Name");
		lblUserName_2.setBounds(15, 380, 101, 20);
		panel_10.add(lblUserName_2);
		

		JButton btnDelete_1 = new JButton("DELETE");
		btnDelete_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				error = "";
				int selectedIndex = comboBoxCustomer.getSelectedIndex();
				String userName = customers.get(selectedIndex);
				try {
					customerService.deleteCustomer(userName, ImsDesktopApplication.getCurrentEmployeeId());
				} catch (InvalidInputException e1) {
					error = e1.getMessage();
				}
				refreshAccountPanel();
			}
		});
		btnDelete_1.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnDelete_1.setBounds(120, 445, 69, 29);
		panel_10.add(btnDelete_1);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				String newUserName = textFieldUpdateCustomerUN.getText();
				String firstName = textFieldUpdateCustomerFirstName.getText();
				String lastName = textFieldUpdateCustomerLastName.getText();
				String phoneNumber = textFieldUpdateCustomerNumber.getText();
				
				int index = comboBoxCustomer.getSelectedIndex();
				String oldUserName = customers.get(index);
				try {
					CustomerDTO customerDTO = new CustomerDTO();
					customerDTO.setFirstName(firstName);
					customerDTO.setLastName(lastName);
					customerDTO.setUserName(newUserName);
					customerDTO.setPhoneNumber(phoneNumber);
					customerService.updateCustomer(customerDTO, ImsDesktopApplication.getCurrentEmployeeId(), oldUserName);
				} catch (InvalidInputException e1) {
					error = e1.getMessage();
				}
				refreshAccountPanel();
			}
		});
		btnUpdate.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnUpdate.setBounds(25, 445, 83, 29);
		panel_10.add(btnUpdate);
		
		JLabel lblPhoneNumber = new JLabel("Phone N0.");
		lblPhoneNumber.setBounds(15, 163, 101, 16);
		panel_10.add(lblPhoneNumber);
		
		textFieldPhoneNumber = new JTextField();
		textFieldPhoneNumber.setBounds(96, 155, 106, 26);
		panel_10.add(textFieldPhoneNumber);
		textFieldPhoneNumber.setColumns(10);
		
		textFieldCustomerFirstName = new JTextField();
		textFieldCustomerFirstName.setBounds(95, 44, 106, 26);
		panel_10.add(textFieldCustomerFirstName);
		textFieldCustomerFirstName.setColumns(10);
		
		JLabel lblFirstname = new JLabel("First Name");
		lblFirstname.setBounds(15, 48, 90, 16);
		panel_10.add(lblFirstname);
		
		JLabel lblLastName_2 = new JLabel("Last Name");
		lblLastName_2.setBounds(15, 352, 90, 16);
		panel_10.add(lblLastName_2);
		
		textFieldUpdateCustomerLastName = new JTextField();
		textFieldUpdateCustomerLastName.setBounds(96, 347, 106, 26);
		panel_10.add(textFieldUpdateCustomerLastName);
		textFieldUpdateCustomerLastName.setColumns(10);
		
		JLabel lblPhoneNumber_1 = new JLabel("Phone N0.");
		lblPhoneNumber_1.setBounds(15, 412, 101, 16);
		panel_10.add(lblPhoneNumber_1);
		
		textFieldUpdateCustomerNumber = new JTextField();
		textFieldUpdateCustomerNumber.setBounds(96, 412, 106, 26);
		panel_10.add(textFieldUpdateCustomerNumber);
		textFieldUpdateCustomerNumber.setColumns(10);
		
		JLabel label_33 = new JLabel("Transactions");
		label_33.setForeground(new Color(0, 128, 0));
		label_33.setFont(new Font("Dialog", Font.BOLD, 20));
		label_33.setBounds(223, 16, 148, 20);
		accountsPanel.add(label_33);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(217, 48, 541, 389);
		accountsPanel.add(scrollPane_2);
		
		tableCustomerTransactions = new JTable();
		scrollPane_2.setColumnHeaderView(tableCustomerTransactions);
		tableCustomerTransactions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				error = "";
				//DefaultTableModel model = (DefaultTableModel) tableCustomerTransactions.getModel();
				int selectedTransactiondRow = tableCustomerTransactions.getSelectedRow();
				// the selected transaction id
				currentTransactionID = transactionIds.get(selectedTransactiondRow);
				System.out.println(currentTransactionID);
//				String employeeId = ImsDesktopApplication.getCurrentEmployeeId();
//				if (selectedCustomerUserName == null) {
//					error = "You have to select a customer first!";
//				}
//				if (error.length() == 0 || error.equals("")) {
//					try {
//						
//						transactionService.getTransactionDetail(employeeId, currentTransactionID, selectedCustomerUserName);
//					} catch (InvalidInputException exc) {
//						error = exc.getMessage();
//					}
//				}
				
				
//				productIndex = Integer.parseInt(model.getValueAt(selectedRow, 0).toString());
//				String amountPaid = model.getValueAt(selectedRow, 3).toString();
//				textFieldCurrentPaid.setText(amountPaid);
			}
		});
		tableCustomerTransactions.setAutoCreateRowSorter(true);
		tableCustomerTransactions.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"DATE", "TOTAL AMOUNT", "AMOUNT PAID", "BALANCE"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -4393767896395429105L;
			Class[] columnTypes = new Class[] {
				Object.class, Object.class, Object.class, Float.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, true, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableCustomerTransactions.getColumnModel().getColumn(3).setResizable(false);
		scrollPane_2.setViewportView(tableCustomerTransactions);
		
		JButton button_16 = new JButton("UPDATE");
		button_16.setBorder(new LineBorder(new Color(128, 0, 0)));
		button_16.setBounds(614, 472, 115, 20);
		accountsPanel.add(button_16);
		
		textFieldCurrentPaid = new JTextField();
		textFieldCurrentPaid.setColumns(10);
		textFieldCurrentPaid.setBounds(467, 449, 124, 20);
		accountsPanel.add(textFieldCurrentPaid);
		
		JLabel label_34 = new JLabel("");
		label_34.setFont(new Font("Tahoma", Font.PLAIN, 20));
		label_34.setBounds(644, 457, 105, 20);
		accountsPanel.add(label_34);
		
		textFieldUpdatePaid = new JTextField();
		textFieldUpdatePaid.setColumns(10);
		textFieldUpdatePaid.setBounds(614, 448, 115, 20);
		accountsPanel.add(textFieldUpdatePaid);
		
		JLabel label_35 = new JLabel("AMOUNT PAID");
		label_35.setBorder(new LineBorder(new Color(128, 0, 0)));
		label_35.setBounds(477, 475, 101, 14);
		accountsPanel.add(label_35);
		
		JButton button = new JButton("+");
		button.setForeground(new Color(0, 128, 0));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				String employeeId = ImsDesktopApplication.getCurrentEmployeeId();
				int index = comboBoxCustomer.getSelectedIndex();
				String customerUserName = customers.get(index);
				try {
					transactionService.createTransaction(employeeId, customerUserName);
				} catch (InvalidInputException exc) {
					error = exc.getMessage();
				}
				refreshCustomerTransactionTable();
			}
		});
		button.setBounds(363, 16, 44, 29);
		accountsPanel.add(button);
		
		JLabel lblTotalAmountLeft = new JLabel("Total Amount Left:");
		lblTotalAmountLeft.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTotalAmountLeft.setForeground(new Color(0, 128, 0));
		lblTotalAmountLeft.setBounds(433, 21, 194, 16);
		accountsPanel.add(lblTotalAmountLeft);
		
		lblTotalBalance = new JLabel("");
		lblTotalBalance.setBounds(641, 20, 108, 16);
		accountsPanel.add(lblTotalBalance);
		
		JButton btnOpen = new JButton("OPEN");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				error = "";
				layeredMainPane.removeAll();
				layeredMainPane.add(transactionsPanel);
				layeredMainPane.repaint();
				layeredMainPane.revalidate();
				lblDashboard.setForeground(Color.GREEN);
				lblProducts.setForeground(Color.GREEN);
				lblAccounts.setForeground(Color.GREEN);
				lblTransaction.setForeground(Color.white);
				
				refreshTransactionPanel();
				refreshTransactionDetail();
			}
		});
		btnOpen.setBounds(223, 449, 70, 29);
		accountsPanel.add(btnOpen);
		
		JButton btnDelete_2 = new JButton("DELETE");
		btnDelete_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				String employeeId = ImsDesktopApplication.getCurrentEmployeeId();
				int selecteTransactiondRow = tableCustomerTransactions.getSelectedRow();
				if (selecteTransactiondRow >= 0) {
					try {
						String transactionID = customerTransactions.get(selecteTransactiondRow);
						transactionService.deleteTransaction(transactionID, employeeId);
					} catch (InvalidInputException exc) {
						error = exc.getMessage();
					}
				} else {
					error = "Please select transaction first.";
				}
				refreshCustomerTransactionTable();
			}
		});
		btnDelete_2.setForeground(Color.RED);
		btnDelete_2.setBounds(290, 449, 81, 29);
		accountsPanel.add(btnDelete_2);
		
		receiptPanel = new JPanel();
		receiptPanel.setLayout(null);
		layeredMainPane.add(receiptPanel, "name_118353732281874");
		
		JButton btnPrint = new JButton("PRINT");
		btnPrint.setBounds(15, 36, 98, 29);
		receiptPanel.add(btnPrint);
		
		JButton btnClose = new JButton("CLOSE");
		btnClose.setBounds(15, 89, 98, 29);
		receiptPanel.add(btnClose);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(126, 6, 632, 477);
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
	
	private void refreshProductPanel() {
		lblErrorMEssage.setText(error);
		
		if (error == null || error.length() == 0) {
			
			//populate product page with data
			//Add Product
			textFieldProductName.setText("");
			textFieldProductPrice.setText("");
			textFieldProductQuantity.setText("");
			
			//Update product
			products = new HashMap<Integer, String>();
			//comboBoxProduct.removeAll();
			int index = 0;
			List<String> names = new ArrayList<String>();
			names.clear();
			comboBoxProduct.removeAllItems();
			String employeeId = ImsDesktopApplication.getCurrentEmployeeId();
			for (ProductDTO p : productService.getProducts(employeeId)) {
				names.add(p.getName());
			}
			Collections.sort(names);
			for (String name : names) {
				products.put(index, name);
				comboBoxProduct.addItem(name);
				index++;
			}
			comboBoxProduct.setSelectedIndex(-1);
			
			textFieldUpdateProductName.setText("");
			textFieldUpdateProductPrice.setText("");
			textFieldUpdateProductQuantity.setText("");
			
		}
	}
	
	private void refreshProductTable() {
		
		if (error == null || error.length() == 0) {
			// some user settings
			DefaultTableModel model = (DefaultTableModel) tableProducts.getModel();
			model.setRowCount(0);
			//set the size and alignment of name column
//			TableColumnModel columnModel = tableProducts.getColumnModel();
//			columnModel.getColumn(0).setPreferredWidth(6);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			//tableProducts.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
			
			//set the alignment of price column
			DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
			leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
			tableProducts.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
			
			//set the alignment of the quantity column
			tableProducts.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
			
			List<ProductDTO> products = productService.getProducts(ImsDesktopApplication.getCurrentEmployeeId());
			for (ProductDTO p : products) {
				model.addRow(new Object[] {p.getName(), p.getItemPrice(), p.getQuantity()});
			}
		}
	}
	
	private void refreshAccountPanel() {
		lblErrorMEssage.setText(error);
		
		if (error == null || error.length() == 0) {
			
			//populate customer page with data
			textFieldCustomerFirstName.setText("");
			textFieldCustomerLastName.setText("");
			textFieldCustomerID.setText("");
			textFieldPhoneNumber.setText("");
			
			//Update customer
			customers = new HashMap<Integer, String>();
			int index = 0;
			List<String> customerIDs = new ArrayList<String>();
			customerIDs.clear();
			comboBoxCustomer.removeAllItems();
			for (CustomerDTO c : customerService.getCustomers()) {
				customerIDs.add(c.getUserName());
			}
			Collections.sort(customerIDs);
			for (String id : customerIDs) {
				customers.put(index, id);
				comboBoxCustomer.addItem(id);
				index++;
			}
			comboBoxCustomer.setSelectedIndex(-1);
			
			textFieldUpdateCustomerFirstName.setText("");
			textFieldUpdateCustomerLastName.setText("");
			textFieldUpdateCustomerUN.setText("");
			textFieldUpdateCustomerNumber.setText("");
			
		}
	}
	
	/**
	 * Refreshes the table which contains the transaction of a customer.
	 */
	private void refreshCustomerTransactionTable() {
		lblErrorMEssage.setText(error);
		
		if (error == null || error.length() == 0) {
			
			DefaultTableModel model = (DefaultTableModel) tableCustomerTransactions.getModel();
			model.setRowCount(0);
			//set the size and alignment of Date column
			//TableColumnModel columnModel = tableCustomerTransactions.getColumnModel();
			//columnModel.getColumn(0).setPreferredWidth(6);
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			
			
			//set the alignment of total amount column
			DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
			leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
			
			tableCustomerTransactions.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
			tableCustomerTransactions.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
			
			//set the alignment of the amount paid column
			tableCustomerTransactions.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
			
			//set the alignment of the balance column
			tableCustomerTransactions.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
			
			float totalBalance = 0.0f;
			transactionIds = new HashMap<Integer, String>();
			String employeeId = ImsDesktopApplication.getCurrentEmployeeId();
			int index = comboBoxCustomer.getSelectedIndex();
			String customerUserName = customers.get(index);
			CustomerDTO customerDTO = customerService.getCustomerTransactions(customerUserName, employeeId);
			int row = 0;
			for (TransactionDTO tT : customerDTO.getTransactions()) {
				String date = tT.getDate().substring(0, 10);
				double balance = tT.getTotalAmount() - tT.getAmountPaid();
				model.addRow(new Object[] {date, tT.getTotalAmount(), tT.getAmountPaid(), balance});
				totalBalance = (float) (totalBalance + balance);
				transactionIds.put(row, tT.getTransactionId());
				row++;
			}
			
			//textFieldCurrentPaid.setText("");
			lblTotalBalance.setText(""+totalBalance);
			//textFieldUpdatePaid.setText("");
		}
		
	}
	
	private void refreshTransactionPanel() {
		lblErrorMEssage.setText(error);
		
		if (error == null || error.length() == 0) {
			
			lblProductTransaction.setText("");
			textFieldTranUpdateQuantity.setText("");
			textFieldTransactionProductQuantity.setText("");
			textFieldPay.setText("");
			lblItemPrice.setText("");
			
			
			//Update transaction
			transactionProducts = new HashMap<Integer, String>();
			int index = 0;
			List<String> names = new ArrayList<String>();
			names.clear();
			comboBoxTransactionProduct.removeAllItems();
			for (ProductDTO p : productService.getProducts(ImsDesktopApplication.getCurrentEmployeeId())) {
				names.add(p.getName());
			}
			Collections.sort(names);
			for (String name : names) {
				transactionProducts.put(index, name);
				comboBoxTransactionProduct.addItem(name);
				index++;
			}
			comboBoxTransactionProduct.setSelectedIndex(-1);
			
		}
	}
	
	private void refreshTransactionDetail() {
		lblErrorMEssage.setText(error);
		if (error == null || error.length() == 0) {
			String employeeId = ImsDesktopApplication.getCurrentEmployeeId();
			TransactionDetail transactionDetail = null;
			DefaultTableModel model = (DefaultTableModel) tableTransaction.getModel();
			model.setRowCount(0);
			if (selectedCustomerUserName != null) {
				boolean exception = false;
				try {
					transactionDetail = transactionService.getTransactionDetail(employeeId, currentTransactionID, selectedCustomerUserName);
				} catch (InvalidInputException exc) {
					error = exc.getMessage();
					lblErrorMEssage.setText(error);
					exception = true;
				}
				if (!exception) {
					if (transactionDetail.getpTransactions() != null) {
						for (ProductTransactionDTO tp : transactionDetail.getpTransactions()) {
							model.addRow(new Object[] {tp.getProductName(), tp.getPrice() / tp.getQuantity(), tp.getQuantity(), tp.getPrice()});
						}
					}
					//update transaction details
					labelTranCustomerName.setText(transactionDetail.getFirstName() + " " + transactionDetail.getLastName());
					labelTranCustomerNumber.setText(transactionDetail.getPhoneNumber());
					lbllabelTranDate.setText(transactionDetail.getDate());
					labelTranAmount.setText(transactionDetail.getTotalAmount() +"");
					labelTranAmountPaid.setText(transactionDetail.getAmountPaid() +"");
					labelTranAmountLeft.setText(transactionDetail.getAmountUnpaid() +"");
				}
				
			}
			
		}
		
	}
}
