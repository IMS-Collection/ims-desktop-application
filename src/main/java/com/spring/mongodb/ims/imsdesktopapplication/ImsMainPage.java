package com.spring.mongodb.ims.imsdesktopapplication;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
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
import com.spring.mongodb.ims.imsdesktopapplication.model.Employee;
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
	private List<ProductDTO> products;
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
	private HashMap<Integer, String> comboBoxProductsMap;
	private HashMap<Integer, String> transactionProducts;
	private HashMap<Integer, ProductDTO> tableProductsMap;
	private List<ProductDTO> currentProducts;
	
	/**
	 * maps product name to its unit price.
	 */
	private HashMap<String, String> comboBoxAddProductMap;
		
	//Customers
	private HashMap<Integer, String> customers;
	private HashMap<Integer, String> customerTransactions;
	private HashMap<Integer, String> transactionIds;
	String selectedCustomerUserName;
	
	// Transactions
	TransactionDetail transactionDetail;
	
	
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
	private JPanel panel_4;
	private JButton btnDashboard;
	private JButton btnProducts;
	private JButton btnAccounts;
	private JLabel lblEmployee;
	private JTextField textFieldLimit;
	private JTextField textFieldUpdateLimit;
	private JTextArea textArea;
	private JPanel panelInvoiceHeader;

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
		// initialize some data variables
		//products = new ArrayList<ProductDTO>();
		currentProducts = new ArrayList<ProductDTO>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 984, 666);
		contentPane = new JPanel();
		contentPane.setBackground(Color.decode("#d3d2d4"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLayeredPane layeredPane = new JLayeredPane();
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(Color.decode("#d3d2d4"));
		
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
					.addGap(292)
					.addGroup(gl_loginPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_loginPanel.createSequentialGroup()
							.addGroup(gl_loginPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_loginPanel.createSequentialGroup()
									.addComponent(panel, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
									.addGap(48))
								.addComponent(lblLoginPage, GroupLayout.PREFERRED_SIZE, 459, GroupLayout.PREFERRED_SIZE))
							.addGap(222))
						.addGroup(gl_loginPanel.createSequentialGroup()
							.addGap(56)
							.addComponent(lblManagementSystem, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
							.addGap(306))
						.addGroup(gl_loginPanel.createSequentialGroup()
							.addGap(44)
							.addComponent(lblNotHaveAccount, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRegister, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
							.addGap(341))))
		);
		gl_loginPanel.setVerticalGroup(
			gl_loginPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_loginPanel.createSequentialGroup()
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
				
				if (!(error.length() > 0)) {
					refreshDashBoard();
				}
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
							.addGap(135)
							.addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblUserName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(35))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(200))
							.addComponent(passwordField)
							.addComponent(userName, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)))
					.addGap(108))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(132)
					.addComponent(lblUserLogin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(176))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(errorMessage, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(47, 79, 79));
		
		lblErrorMEssage = new JLabel("");
		lblErrorMEssage.setForeground(Color.RED);
		lblErrorMEssage.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		btnDashboard = new JButton("Dashboard");
		btnDashboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				layeredMainPane.removeAll();
				layeredMainPane.add(dashBoardPanel);
				layeredMainPane.repaint();
				layeredMainPane.revalidate();
				btnDashboard.setForeground(Color.decode("#0f03fc"));
				btnProducts.setForeground(Color.GREEN);
				btnAccounts.setForeground(Color.GREEN);
				
				refreshDashBoard();
			}
		});
		btnDashboard.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDashboard.setBackground(new Color(85, 107, 47));
		btnDashboard.setForeground(Color.GREEN);
		
		btnProducts = new JButton("Products");
		btnProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				layeredMainPane.removeAll();
				layeredMainPane.add(productsPanel);
				layeredMainPane.repaint();
				layeredMainPane.revalidate();
				btnDashboard.setForeground(Color.GREEN);
				btnProducts.setForeground(Color.decode("#0f03fc"));
				btnAccounts.setForeground(Color.GREEN);
				
				List<ProductDTO> products = productService.getProducts(ImsDesktopApplication.getCurrentEmployeeId());
				refreshProductTable(products);
				refreshProductPanel(products);
				
			}
		});
		btnProducts.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnProducts.setBackground(new Color(85, 107, 47));
		btnProducts.setForeground(Color.GREEN);
		
		btnAccounts = new JButton("Accounts");
		btnAccounts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				layeredMainPane.removeAll();
				layeredMainPane.add(accountsPanel);
				layeredMainPane.repaint();
				layeredMainPane.revalidate();
				btnDashboard.setForeground(Color.GREEN);
				btnProducts.setForeground(Color.GREEN);
				btnAccounts.setForeground(Color.decode("#0f03fc"));
				
				refreshAccountPanel();
				//refreshCustomerTransactionTable();
			}
		});
		btnAccounts.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAccounts.setBackground(new Color(85, 107, 47));
		btnAccounts.setForeground(Color.GREEN);
		
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
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLogout.setBorder(new LineBorder(new Color(0, 0, 255)));
		
		layeredMainPane = new JLayeredPane();
		layeredMainPane.setLayout(new CardLayout(0, 0));
		
		dashBoardPanel = new JPanel();
		layeredMainPane.add(dashBoardPanel, "name_118134937110699");
		
		lblEmployee = new JLabel("");
		lblEmployee.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmployee.setFont(new Font("Dialog", Font.BOLD, 25));
		GroupLayout gl_dashBoardPanel = new GroupLayout(dashBoardPanel);
		gl_dashBoardPanel.setHorizontalGroup(
			gl_dashBoardPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dashBoardPanel.createSequentialGroup()
					.addGap(61)
					.addComponent(lblEmployee, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
					.addGap(263))
		);
		gl_dashBoardPanel.setVerticalGroup(
			gl_dashBoardPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_dashBoardPanel.createSequentialGroup()
					.addGap(101)
					.addComponent(lblEmployee, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(442))
		);
		dashBoardPanel.setLayout(gl_dashBoardPanel);
		
		productsPanel = new JPanel();
		layeredMainPane.add(productsPanel, "name_118179738325051");
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 255)));
		
		JLabel label_8 = new JLabel("Add Product");
		label_8.setForeground(new Color(0, 100, 0));
		label_8.setFont(new Font("Dialog", Font.BOLD, 24));
		
		JLabel label_9 = new JLabel("Name");
		label_9.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JLabel label_10 = new JLabel("Price");
		
		JLabel label_11 = new JLabel("Quantity");
		
		JButton btnAddProduct = new JButton("ADD PRODUCT");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent argo0) {

				error = "";
				double price = 0;
				try {
					price = Double.parseDouble(textFieldProductPrice.getText());
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
				
				int limit = 0;
				try {
					limit = Integer.parseInt(textFieldLimit.getText());
				}
				catch (NumberFormatException e) {
					error = "Product limit needs to be an integer value! ";
				}
				
				String name = textFieldProductName.getText();
				
				error.trim();
				ProductDTO newProductDTO = null;
				if (error.length() == 0) {
					try {
						ProductDTO productDTO = new ProductDTO();
						productDTO.setName(name);
						productDTO.setItemPrice(price);
						productDTO.setQuantity(quantity);
						productDTO.setLimit(limit);
						newProductDTO = productService.createProduct(productDTO, ImsDesktopApplication.getCurrentEmployeeId());
//						ImsProductController.callCreateProduct();
					} catch (InvalidInputException e) {
						error = e.getMessage();
					}
				}
				
				List<ProductDTO> products = productService.getProducts(ImsDesktopApplication.getCurrentEmployeeId());
				refreshProductPanel(products);
				refreshProductTable(products);
				
			}
		});
		btnAddProduct.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		
		textFieldProductQuantity = new JTextField();
		textFieldProductQuantity.setColumns(10);
		textFieldProductQuantity.setBorder(new LineBorder(new Color(0, 0, 255)));
		
		textFieldProductPrice = new JTextField();
		textFieldProductPrice.setColumns(10);
		textFieldProductPrice.setBorder(new LineBorder(new Color(0, 0, 255)));
		
		textFieldProductName = new JTextField();
		textFieldProductName.setColumns(10);
		textFieldProductName.setBorder(new LineBorder(new Color(0, 0, 255)));
		
		JLabel label_12 = new JLabel("Products");
		label_12.setForeground(new Color(0, 128, 0));
		label_12.setFont(new Font("Dialog", Font.BOLD, 24));
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLUE);
		
		comboBoxProduct = new JComboBox<String>();
		comboBoxProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				int selectedIndex = comboBoxProduct.getSelectedIndex();
				String productName = comboBoxProductsMap.get(selectedIndex);
				if (productName != null) {
			
					List<ProductDTO> databaseProducts = productService.getProducts(ImsDesktopApplication.getCurrentEmployeeId());
					for (ProductDTO p : databaseProducts) {
						if (productName.equals(p.getName())) {
							textFieldUpdateProductName.setText(productName);
							textFieldUpdateProductPrice.setText(""+p.getItemPrice());
							textFieldUpdateProductQuantity.setText(""+p.getQuantity());
							textFieldUpdateLimit.setText("" + p.getLimit());
						}
					}
				}
			}
		});
		comboBoxProduct.setBorder(new LineBorder(new Color(0, 0, 255)));
		
		textFieldUpdateProductName = new JTextField();
		textFieldUpdateProductName.setColumns(10);
		textFieldUpdateProductName.setBorder(new LineBorder(new Color(0, 0, 255)));
		
		textFieldUpdateProductPrice = new JTextField();
		textFieldUpdateProductPrice.setColumns(10);
		textFieldUpdateProductPrice.setBorder(new LineBorder(new Color(0, 0, 255)));
		
		textFieldUpdateProductQuantity = new JTextField();
		textFieldUpdateProductQuantity.setColumns(10);
		textFieldUpdateProductQuantity.setBorder(new LineBorder(new Color(0, 0, 255)));
		
		JLabel label_13 = new JLabel("Product");
		
		JLabel label_14 = new JLabel("Name");
		
		JLabel label_15 = new JLabel("Price");
		
		JLabel label_16 = new JLabel("Quantity");
		
		JButton btnUpdateProduct = new JButton("UPDATE");
		btnUpdateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				error = "";
				double updatePrice = 0;
				try {
					updatePrice = Double.parseDouble(textFieldUpdateProductPrice.getText());
				}
				catch (NumberFormatException e) {
					error = "Price figure needs to be an numerical value! ";
				}
				int updateQuantity = 0;
				try {
					updateQuantity = Integer.parseInt(textFieldUpdateProductQuantity.getText());
				}
				catch (NumberFormatException e) {
					error = "Quantity figure needs to be an integer value! ";
				}
				
				int limit = 0;
				try {
					limit = Integer.parseInt(textFieldUpdateLimit.getText());
				}
				catch (NumberFormatException e) {
					error = "The product needs to be an integer value! ";
				}
				
				String newName = textFieldUpdateProductName.getText();
				
				try {
					int selectedIndex = comboBoxProduct.getSelectedIndex();
					String oldName = comboBoxProductsMap.get(selectedIndex);
					ProductDTO productDTO = new ProductDTO();
					productDTO.setName(newName);
					productDTO.setItemPrice(updatePrice);
					productDTO.setQuantity(updateQuantity);
					productDTO.setLimit(limit);
					productService.updateProduct(oldName, productDTO, ImsDesktopApplication.getCurrentEmployeeId());
				} catch (InvalidInputException e) {
					error = e.getMessage();
				}

				List<ProductDTO> products = productService.getProducts(ImsDesktopApplication.getCurrentEmployeeId());
				refreshProductTable(products);
				refreshProductPanel(products);
			}
		});
		btnUpdateProduct.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent earg0) {
				error = "";
				int selectedIndex = comboBoxProduct.getSelectedIndex();
				String productName = comboBoxProductsMap.get(selectedIndex);
				try {
					productService.deleteProduct(productName, ImsDesktopApplication.getCurrentEmployeeId());
				} catch (InvalidInputException er) {
					error = er.getMessage();
				}
				
				List<ProductDTO> products = productService.getProducts(ImsDesktopApplication.getCurrentEmployeeId());
				refreshProductPanel(products);
				refreshProductTable(products);
			}
		});
		btnDelete.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label_17 = new JLabel("Click each column to sort the items");
		label_17.setForeground(new Color(0, 128, 0));
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JButton btnPrintProducts = new JButton("PRINT");
		btnPrintProducts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printTable(tableProducts);
			}
		});
		btnPrintProducts.setBorder(new LineBorder(new Color(128, 0, 0), 1, true));
		GroupLayout gl_productsPanel = new GroupLayout(productsPanel);
		gl_productsPanel.setHorizontalGroup(
			gl_productsPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_productsPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_productsPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_productsPanel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.LEADING, gl_productsPanel.createSequentialGroup()
							.addComponent(label_17, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
							.addGap(162))
						.addGroup(gl_productsPanel.createSequentialGroup()
							.addComponent(btnPrintProducts, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		gl_productsPanel.setVerticalGroup(
			gl_productsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_productsPanel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_productsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_productsPanel.createSequentialGroup()
							.addComponent(label_17, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnPrintProducts, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addGap(5))
						.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 538, GroupLayout.PREFERRED_SIZE))
					.addGap(7))
		);
		
		JLabel lblLimit = new JLabel("Limit");
		
		textFieldLimit = new JTextField();
		textFieldLimit.setBorder(new LineBorder(Color.BLUE));
		textFieldLimit.setColumns(10);
		
		JLabel lblLimit_1 = new JLabel("Limit");
		
		textFieldUpdateLimit = new JTextField();
		textFieldUpdateLimit.setBorder(new LineBorder(Color.BLUE));
		textFieldUpdateLimit.setColumns(10);
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(6)
					.addComponent(lblLimit, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addComponent(textFieldLimit, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(83)
					.addComponent(btnAddProduct, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(94)
					.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE))
				.addComponent(separator, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(13)
					.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(comboBoxProduct, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(13)
					.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(textFieldUpdateProductName, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(13)
					.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(textFieldUpdateProductPrice, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(13)
					.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(textFieldUpdateProductQuantity, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(13)
					.addComponent(lblLimit_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addComponent(textFieldUpdateLimit, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(85)
					.addComponent(btnUpdateProduct, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(196))
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(17)
							.addComponent(textFieldProductQuantity, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
								.addComponent(label_10)
								.addComponent(label_9))
							.addGap(46)
							.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
								.addComponent(textFieldProductName)
								.addComponent(textFieldProductPrice, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(195, Short.MAX_VALUE))
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(107)
					.addComponent(label_8)
					.addContainerGap(228, Short.MAX_VALUE))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_8)
					.addGap(10)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldProductName, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_9))
					.addGap(18)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldProductPrice, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_10))
					.addGap(14)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(2)
							.addComponent(label_11, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(textFieldProductQuantity, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLimit)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(1)
							.addComponent(textFieldLimit, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
					.addGap(8)
					.addComponent(btnAddProduct, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(12)
							.addComponent(label_12, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(3)
							.addComponent(label_13, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(comboBoxProduct, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(3)
							.addComponent(label_14, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(textFieldUpdateProductName, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(3)
							.addComponent(label_15, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(textFieldUpdateProductPrice, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(16)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(6)
							.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
						.addComponent(textFieldUpdateProductQuantity, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(5)
							.addComponent(lblLimit_1))
						.addComponent(textFieldUpdateLimit, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnUpdateProduct, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
		);
		panel_6.setLayout(gl_panel_6);
		
		tableProducts = new JTable();
		tableProducts.setAutoCreateRowSorter(true);
		tableProducts.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) tableProducts.getModel();
				int selectedRow = tableProducts.getSelectedRow();
				
				error = "";
				int selectedProductRow = tableProducts.getSelectedRow();
				// the selected product
				ProductDTO product = tableProductsMap.get(selectedProductRow);
				
				// suspended
				
//				textFieldUpdateProductName.setText(product.getName());
//				textFieldUpdateProductPrice.setText(""+product.getItemPrice());
//				textFieldUpdateProductQuantity.setText(""+product.getQuantity());
//				textFieldUpdateLimit.setText("" + product.getLimit());
				
				String name = model.getValueAt(selectedRow, 0).toString();
				String price = model.getValueAt(selectedRow, 1).toString();
				String quantity = model.getValueAt(selectedRow, 2).toString();
				
//				System.out.println("name: " + name +"\nPrice: " + price +"\nquantity: " + quantity);
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
		layeredMainPane.add(transactionsPanel, "name_118242517273668");
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBorder(new LineBorder(new Color(0, 0, 255)));
		
		lblProductTransaction = new JLabel("Product");
		lblProductTransaction.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductTransaction.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblProductTransaction.setBounds(58, 38, 140, 29);
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
					updateTransactionProduct(productName, newQuantity);
				} catch (InvalidInputException e) {
					error = e.getMessage();
				}
				refreshTransactionPanel();
				refreshTransactionDetail();
			}
		});
		btnUpdateTransaction.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnUpdateTransaction.setBounds(48, 107, 80, 29);
		panel_8.add(btnUpdateTransaction);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 148, 321, 10);
		panel_8.add(separator_1);
		
		JLabel label_19 = new JLabel("Add Products for Purchase");
		label_19.setForeground(new Color(0, 128, 0));
		label_19.setFont(new Font("Dialog", Font.BOLD, 13));
		label_19.setBounds(15, 170, 193, 20);
		panel_8.add(label_19);
		
		comboBoxTransactionProduct = new JComboBox<String>();
		comboBoxTransactionProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				//comboBoxAddProductMap.
				int selectedIndex = comboBoxTransactionProduct.getSelectedIndex();
				String productName = transactionProducts.get(selectedIndex);
				if (productName != null) {
					String unitPrice = comboBoxAddProductMap.get(productName);
					lblItemPrice.setText("Unit Price: " + unitPrice);
				}
			}
		});
		comboBoxTransactionProduct.setBounds(15, 202, 183, 26);
		panel_8.add(comboBoxTransactionProduct);
		
		JLabel label_21 = new JLabel("Quantity");
		label_21.setBounds(15, 268, 62, 20);
		panel_8.add(label_21);
		
		textFieldTransactionProductQuantity = new JTextField();
		textFieldTransactionProductQuantity.setColumns(10);
		textFieldTransactionProductQuantity.setBounds(80, 266, 115, 26);
		panel_8.add(textFieldTransactionProductQuantity);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				error = "";
				
				// the index of the selected product to be added in a transaction
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
					String employeeId = ImsDesktopApplication.getCurrentEmployeeId(); 
					try {
						ProductTransactionDTO newProductTransaction = transactionService.addTransactionProduct(employeeId, productName, quantity, currentTransactionID);
						addNewProductTransaction(newProductTransaction);
					} catch (InvalidInputException e1) {
						error = e1.getMessage();
					}
//					int option = JOptionPane.showConfirmDialog(ImsDesktopApplication.getFrame(), 
//							"Confirm to add "+productName+"("+quantity+")", 
//							"Confirm adding product", JOptionPane.OK_CANCEL_OPTION);
//					if (option == 0) {
//						String employeeId = ImsDesktopApplication.getCurrentEmployeeId(); 
//						try {
//							transactionService.addTransactionProduct(employeeId, productName, quantity, currentTransactionID);
//						} catch (InvalidInputException e1) {
//							error = e1.getMessage();
//						}
//					}
				} 
				refreshTransactionPanel();
				refreshTransactionDetail();
			}
		});
		btnAdd.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnAdd.setBounds(120, 304, 80, 29);
		panel_8.add(btnAdd);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 363, 276, 20);
		panel_8.add(separator_2);
		
		JButton btnSubmit = new JButton("FINALIZE");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				int option = JOptionPane.showConfirmDialog(ImsDesktopApplication.getFrame(), 
						"Do you really want to finalize the transaction?", "Conclude Transaction Confirmation.", 
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (option == 0) {
					try {
						transactionService.finalizeTransaction(currentTransactionID, ImsDesktopApplication.getCurrentEmployeeId());
					} catch (InvalidInputException exc) {
						error = exc.getMessage();
					}
					refreshTransactionPanel();
				}
				
			}
		});
		btnSubmit.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnSubmit.setBounds(80, 420, 115, 29);
		panel_8.add(btnSubmit);
		
		textFieldPay = new JTextField();
		textFieldPay.setColumns(10);
		textFieldPay.setBounds(81, 382, 115, 26);
		panel_8.add(textFieldPay);
		
		JLabel label_23 = new JLabel("");
		label_23.setForeground(Color.BLUE);
		label_23.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_23.setBounds(208, 127, 98, 32);
		panel_8.add(label_23);
		
		JButton btnRemoveProduct = new JButton("REMOVE");
		btnRemoveProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				try {
					String productName = lblProductTransaction.getText();
					transactionService.removeProduct(ImsDesktopApplication.getCurrentEmployeeId(), productName, currentTransactionID);
					removeProductTransaction(productName);
				} catch (InvalidInputException exc) {
					error = exc.getMessage();
				}
				refreshTransactionDetail();
				refreshTransactionPanel();
				if (error.length() == 0) {
					JOptionPane.showMessageDialog(ImsDesktopApplication.getFrame(), "Successfully removed");
				}
			}
		});
		btnRemoveProduct.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnRemoveProduct.setBounds(138, 107, 91, 29);
		panel_8.add(btnRemoveProduct);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnClear.setBounds(80, 502, 115, 29);
		panel_8.add(btnClear);
		
		JButton btnShowReceipt = new JButton("PRINT");
		btnShowReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO add your handling code here:
//			    Toolkit tkp = transactionsPanel.getToolkit();
//			    PrintJob pjp = tkp.getPrintJob(ImsDesktopApplication.getFrame(), null, null);
//			    Graphics g = pjp.getGraphics();
//			    transactionsPanel.print(g);
//			    g.dispose();
//			    pjp.end();
				
				// print the invoice and summary of transaction
			    printComponenet(panelInvoiceHeader);
			    
			    // print details in the table
			    printTable(tableTransaction);
			    
			    //showInvoice();
			}
		});
		btnShowReceipt.setBorder(new LineBorder(new Color(128, 0, 0)));
		btnShowReceipt.setBounds(80, 461, 115, 29);
		panel_8.add(btnShowReceipt);
		
		JLabel lblUpdateTransaction = new JLabel("Update Transaction");
		lblUpdateTransaction.setForeground(new Color(0, 128, 0));
		lblUpdateTransaction.setFont(new Font("Dialog", Font.BOLD, 17));
		lblUpdateTransaction.setBounds(51, 0, 178, 27);
		panel_8.add(lblUpdateTransaction);
		
		JLabel lblNewQuantity = new JLabel("New Quantity");
		lblNewQuantity.setBounds(7, 77, 91, 16);
		panel_8.add(lblNewQuantity);
		
		textFieldTranUpdateQuantity = new JTextField();
		textFieldTranUpdateQuantity.setBounds(96, 77, 112, 26);
		panel_8.add(textFieldTranUpdateQuantity);
		textFieldTranUpdateQuantity.setColumns(10);
		
		lblItemPrice = new JLabel("New label");
		lblItemPrice.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblItemPrice.setForeground(Color.GREEN);
		lblItemPrice.setBounds(15, 240, 178, 16);
		panel_8.add(lblItemPrice);
		
		JButton btnPay = new JButton("PAY");
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				error = "";
				double newAmount = Double.parseDouble((textFieldPay.getText()));
				try {
					transactionService.updateAmountPaidTransaction(newAmount, currentTransactionID, ImsDesktopApplication.getCurrentEmployeeId());
					updateAmountPaid(newAmount);
				} catch (InvalidInputException exc) {
					error = exc.getMessage();
				}
				refreshTransactionDetail();
				refreshTransactionPanel();
			}
		});
		btnPay.setBounds(15, 382, 62, 29);
		panel_8.add(btnPay);
		
		JLabel label_25 = new JLabel("");
		label_25.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		panel_4 = new JPanel();
		
		JScrollPane scrollPaneTransaction = new JScrollPane();
		
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
		
		panelInvoiceHeader = new JPanel();
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneTransaction, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
						.addComponent(panelInvoiceHeader, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addComponent(panelInvoiceHeader, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneTransaction, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE))
		);
		
		JLabel lblDeDonMotors = new JLabel("DE DON MOTORS CO. L.T.D");
		lblDeDonMotors.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeDonMotors.setFont(new Font("Dialog", Font.BOLD, 18));
		lblDeDonMotors.setForeground(Color.BLUE);
		
		JLabel lblInGodWe = new JLabel("IN GOD WE TRUST");
		lblInGodWe.setHorizontalAlignment(SwingConstants.CENTER);
		lblInGodWe.setForeground(Color.MAGENTA);
		lblInGodWe.setFont(new Font("Dialog", Font.ITALIC, 10));
		
		JLabel lblSoleAgeentOf = new JLabel("SOLE AGEENT OF AVATA SPECIAL QUALITY MOTORCYCLE/SPARE PARTS AND DIAMONG TIRES/TUBES");
		lblSoleAgeentOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoleAgeentOf.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		JLabel lblheadOfficeTechiman = new JLabel("(Head Office: Techiman, B/E Along Kintampo Road Before Toll Booth Tuobodom)");
		lblheadOfficeTechiman.setHorizontalAlignment(SwingConstants.CENTER);
		lblheadOfficeTechiman.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		JLabel lblNewLabel = new JLabel("Box 120 TECHIMAN. B.E/R Ghana: Tel: 0243679200/0209380084/0553552147/0247832338");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		JLabel lblEmailDedonmotorsyahoocomdedonmotorsgmailcom = new JLabel("E-mail: dedon.motors@yahoo.com/dedonmotors@gmail.com");
		lblEmailDedonmotorsyahoocomdedonmotorsgmailcom.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmailDedonmotorsyahoocomdedonmotorsgmailcom.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		JLabel lblBranches = new JLabel("Branches");
		lblBranches.setHorizontalAlignment(SwingConstants.CENTER);
		lblBranches.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBranches.setForeground(Color.BLUE);
		
		JLabel lblWaAlongWa = new JLabel("Wa: Along Wa Poly Road");
		lblWaAlongWa.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		JLabel lblTel = new JLabel("Tel: 0244485813/0246017637");
		lblTel.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		JLabel lblKumasiAlabafulaniChief = new JLabel("Kumasi: AlabaFulani Chief House");
		lblKumasiAlabafulaniChief.setHorizontalAlignment(SwingConstants.CENTER);
		lblKumasiAlabafulaniChief.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		JLabel lblTel_1 = new JLabel("Tel: 0551939054");
		lblTel_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		JLabel lblBolga = new JLabel("Bolga");
		lblBolga.setHorizontalAlignment(SwingConstants.CENTER);
		lblBolga.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		JLabel lblTel_2 = new JLabel("Tel: 0209784545/0553818004");
		lblTel_2.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		JLabel lblCustomerName = new JLabel("Customer Name:");
		lblCustomerName.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		labelTranCustomerName = new JLabel("");
		labelTranCustomerName.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		JLabel lblPhoneNumber_2 = new JLabel("Phone Number:");
		lblPhoneNumber_2.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		labelTranCustomerNumber = new JLabel("");
		labelTranCustomerNumber.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		JLabel lblDate = new JLabel("Transaction Date:");
		lblDate.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		lbllabelTranDate = new JLabel("");
		lbllabelTranDate.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		JLabel lblTotalAmount = new JLabel("Total Amount:");
		lblTotalAmount.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		JLabel lblAmountPaid = new JLabel("Amount Paid:");
		lblAmountPaid.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		labelTranAmountPaid = new JLabel("");
		labelTranAmountPaid.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		JLabel lblAmountLeft = new JLabel("Amount Left:");
		lblAmountLeft.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		labelTranAmountLeft = new JLabel("");
		labelTranAmountLeft.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		labelTranAmount = new JLabel("");
		labelTranAmount.setFont(new Font("Dialog", Font.PLAIN, 10));
		GroupLayout gl_panelInvoiceHeader = new GroupLayout(panelInvoiceHeader);
		gl_panelInvoiceHeader.setHorizontalGroup(
			gl_panelInvoiceHeader.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
					.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
							.addGap(121)
							.addComponent(lblDeDonMotors, GroupLayout.PREFERRED_SIZE, 355, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
							.addGap(160)
							.addComponent(lblInGodWe, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
							.addGap(43)
							.addComponent(lblheadOfficeTechiman, GroupLayout.PREFERRED_SIZE, 472, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
							.addGap(20)
							.addComponent(lblSoleAgeentOf, GroupLayout.PREFERRED_SIZE, 568, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
							.addGap(72)
							.addComponent(lblEmailDedonmotorsyahoocomdedonmotorsgmailcom, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(25, Short.MAX_VALUE))
				.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
					.addGap(151)
					.addComponent(lblBranches, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
					.addGap(204))
				.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
					.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblTel, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
							.addGap(22)
							.addComponent(lblWaAlongWa, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
							.addGap(18)
							.addComponent(lblKumasiAlabafulaniChief, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
							.addGap(73)
							.addComponent(lblTel_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTel_2, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBolga, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
					.addGap(28))
				.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
							.addComponent(lblDate, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbllabelTranDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
							.addComponent(lblCustomerName, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelTranCustomerName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
							.addComponent(lblPhoneNumber_2, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelTranCustomerNumber, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.LEADING)
						.addComponent(lblTotalAmount, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
							.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAmountPaid, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAmountLeft, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.LEADING)
								.addComponent(labelTranAmountPaid, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTranAmountLeft, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
								.addComponent(labelTranAmount, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(167, Short.MAX_VALUE))
		);
		gl_panelInvoiceHeader.setVerticalGroup(
			gl_panelInvoiceHeader.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
					.addComponent(lblDeDonMotors)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblInGodWe)
					.addGap(11)
					.addComponent(lblSoleAgeentOf)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblheadOfficeTechiman, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblEmailDedonmotorsyahoocomdedonmotorsgmailcom, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblBranches)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblKumasiAlabafulaniChief)
						.addComponent(lblWaAlongWa)
						.addComponent(lblBolga, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTel)
						.addComponent(lblTel_1)
						.addComponent(lblTel_2))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblCustomerName, GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
						.addComponent(labelTranCustomerName, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.LEADING)
							.addComponent(labelTranAmount, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblTotalAmount)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPhoneNumber_2)
								.addComponent(labelTranCustomerNumber, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panelInvoiceHeader.createSequentialGroup()
								.addComponent(lblAmountPaid)
								.addGap(2)))
						.addComponent(labelTranAmountPaid, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblDate)
							.addComponent(lbllabelTranDate, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelInvoiceHeader.createParallelGroup(Alignment.TRAILING)
							.addComponent(labelTranAmountLeft, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblAmountLeft)))
					.addContainerGap(12, Short.MAX_VALUE))
		);
		panelInvoiceHeader.setLayout(gl_panelInvoiceHeader);
		panel_4.setLayout(gl_panel_4);
		GroupLayout gl_transactionsPanel = new GroupLayout(transactionsPanel);
		gl_transactionsPanel.setHorizontalGroup(
			gl_transactionsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_transactionsPanel.createSequentialGroup()
					.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 637, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_25, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_transactionsPanel.setVerticalGroup(
			gl_transactionsPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_transactionsPanel.createSequentialGroup()
					.addGap(468)
					.addComponent(label_25, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_transactionsPanel.createSequentialGroup()
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
					.addGap(6))
				.addGroup(gl_transactionsPanel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 537, GroupLayout.PREFERRED_SIZE))
		);
		transactionsPanel.setLayout(gl_transactionsPanel);
		
		accountsPanel = new JPanel();
		layeredMainPane.add(accountsPanel, "name_118280808053760");
		
		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBorder(new LineBorder(new Color(0, 0, 255)));
		
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
		textFieldCustomerLastName.setBounds(96, 82, 137, 26);
		panel_10.add(textFieldCustomerLastName);
		
		JLabel lblUserName_3 = new JLabel("User Name");
		lblUserName_3.setBounds(15, 123, 90, 20);
		panel_10.add(lblUserName_3);
		
		textFieldCustomerID = new JTextField();
		textFieldCustomerID.setColumns(10);
		textFieldCustomerID.setBounds(96, 120, 137, 26);
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
		btnRegisterCustomer.setBounds(128, 191, 90, 29);
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
		comboBoxCustomer.setBounds(96, 279, 137, 26);
		panel_10.add(comboBoxCustomer);
		
		JLabel label_30 = new JLabel("Customer");
		label_30.setBounds(15, 279, 69, 20);
		panel_10.add(label_30);
		
		textFieldUpdateCustomerFirstName = new JTextField();
		textFieldUpdateCustomerFirstName.setColumns(10);
		textFieldUpdateCustomerFirstName.setBounds(96, 317, 137, 26);
		panel_10.add(textFieldUpdateCustomerFirstName);
		
		JLabel lblFirstName_1 = new JLabel("First Name");
		lblFirstName_1.setBounds(15, 320, 69, 20);
		panel_10.add(lblFirstName_1);
		
		textFieldUpdateCustomerUN = new JTextField();
		textFieldUpdateCustomerUN.setColumns(10);
		textFieldUpdateCustomerUN.setBounds(96, 377, 137, 26);
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
		btnDelete_1.setBounds(148, 445, 85, 29);
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
		btnUpdate.setBounds(53, 445, 83, 29);
		panel_10.add(btnUpdate);
		
		JLabel lblPhoneNumber = new JLabel("Phone N0.");
		lblPhoneNumber.setBounds(15, 163, 101, 16);
		panel_10.add(lblPhoneNumber);
		
		textFieldPhoneNumber = new JTextField();
		textFieldPhoneNumber.setBounds(96, 155, 137, 26);
		panel_10.add(textFieldPhoneNumber);
		textFieldPhoneNumber.setColumns(10);
		
		textFieldCustomerFirstName = new JTextField();
		textFieldCustomerFirstName.setBounds(95, 44, 138, 26);
		panel_10.add(textFieldCustomerFirstName);
		textFieldCustomerFirstName.setColumns(10);
		
		JLabel lblFirstname = new JLabel("First Name");
		lblFirstname.setBounds(15, 48, 90, 16);
		panel_10.add(lblFirstname);
		
		JLabel lblLastName_2 = new JLabel("Last Name");
		lblLastName_2.setBounds(15, 352, 90, 16);
		panel_10.add(lblLastName_2);
		
		textFieldUpdateCustomerLastName = new JTextField();
		textFieldUpdateCustomerLastName.setBounds(96, 347, 137, 26);
		panel_10.add(textFieldUpdateCustomerLastName);
		textFieldUpdateCustomerLastName.setColumns(10);
		
		JLabel lblPhoneNumber_1 = new JLabel("Phone N0.");
		lblPhoneNumber_1.setBounds(15, 412, 101, 16);
		panel_10.add(lblPhoneNumber_1);
		
		textFieldUpdateCustomerNumber = new JTextField();
		textFieldUpdateCustomerNumber.setBounds(96, 412, 137, 26);
		panel_10.add(textFieldUpdateCustomerNumber);
		textFieldUpdateCustomerNumber.setColumns(10);
		
		JLabel label_33 = new JLabel("Transactions");
		label_33.setForeground(new Color(0, 128, 0));
		label_33.setFont(new Font("Dialog", Font.BOLD, 20));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
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
				false, true, true, true
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		//tableCustomerTransactions.getColumnModel().getColumn(3).setResizable(false);
		scrollPane_2.setViewportView(tableCustomerTransactions);
		
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
		
		JLabel lblTotalAmountLeft = new JLabel("Total Amount Left:");
		lblTotalAmountLeft.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTotalAmountLeft.setForeground(new Color(0, 128, 0));
		
		lblTotalBalance = new JLabel("");
		
		JButton btnOpen = new JButton("OPEN");
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					transactionDetail = transactionService.getTransactionDetail(ImsDesktopApplication.getCurrentEmployeeId(), currentTransactionID, selectedCustomerUserName);
					refreshTransactionPanel();
					refreshTransactionDetail();
				} catch (InvalidInputException exc) {
					error = exc.getMessage();
				}
				
				if (error.length() == 0) {
					layeredMainPane.removeAll();
					layeredMainPane.add(transactionsPanel);
					layeredMainPane.repaint();
					layeredMainPane.revalidate();
					btnDashboard.setForeground(Color.GREEN);
					btnProducts.setForeground(Color.GREEN);
					btnAccounts.setForeground(Color.GREEN);
				}
						
			}
		});
		
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
		GroupLayout gl_accountsPanel = new GroupLayout(accountsPanel);
		gl_accountsPanel.setHorizontalGroup(
			gl_accountsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_accountsPanel.createSequentialGroup()
					.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_accountsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_accountsPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
							.addGroup(gl_accountsPanel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_accountsPanel.createSequentialGroup()
									.addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(btnDelete_2, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addGap(42))
								.addGroup(gl_accountsPanel.createSequentialGroup()
									.addComponent(label_33, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button)
									.addGap(74)
									.addComponent(lblTotalAmountLeft)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblTotalBalance, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
									.addGap(101))))
						.addGroup(gl_accountsPanel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
							.addGap(33))))
		);
		gl_accountsPanel.setVerticalGroup(
			gl_accountsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_accountsPanel.createSequentialGroup()
					.addGroup(gl_accountsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_accountsPanel.createSequentialGroup()
							.addGap(16)
							.addGroup(gl_accountsPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_accountsPanel.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblTotalAmountLeft, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
									.addComponent(label_33, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
									.addComponent(button, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblTotalBalance, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE))
						.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 492, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_accountsPanel.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(btnDelete_2)
						.addComponent(btnOpen, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		accountsPanel.setLayout(gl_accountsPanel);
		
		receiptPanel = new JPanel();
		layeredMainPane.add(receiptPanel, "name_118353732281874");
		
		JButton btnPrint = new JButton("PRINT");
		
		JButton btnClose = new JButton("CLOSE");
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout gl_receiptPanel = new GroupLayout(receiptPanel);
		gl_receiptPanel.setHorizontalGroup(
			gl_receiptPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_receiptPanel.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_receiptPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnPrint, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 805, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_receiptPanel.setVerticalGroup(
			gl_receiptPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_receiptPanel.createSequentialGroup()
					.addGroup(gl_receiptPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_receiptPanel.createSequentialGroup()
							.addGap(36)
							.addComponent(btnPrint, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addGap(24)
							.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_receiptPanel.createSequentialGroup()
							.addGap(6)
							.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 477, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(68, Short.MAX_VALUE))
		);
		
		textArea = new JTextArea();
		scrollPane_3.setViewportView(textArea);
		receiptPanel.setLayout(gl_receiptPanel);
		GroupLayout gl_imsPagePanel = new GroupLayout(imsPagePanel);
		gl_imsPagePanel.setHorizontalGroup(
			gl_imsPagePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_imsPagePanel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_imsPagePanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(layeredMainPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 953, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE))
					.addGap(26))
		);
		gl_imsPagePanel.setVerticalGroup(
			gl_imsPagePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_imsPagePanel.createSequentialGroup()
					.addGap(12)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(layeredMainPane, GroupLayout.PREFERRED_SIZE, 551, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(29)
					.addComponent(btnDashboard)
					.addGap(12)
					.addComponent(btnProducts)
					.addGap(12)
					.addComponent(btnAccounts)
					.addPreferredGap(ComponentPlacement.RELATED, 451, Short.MAX_VALUE)
					.addComponent(btnLogout))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(137)
					.addComponent(lblErrorMEssage, GroupLayout.PREFERRED_SIZE, 618, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDashboard)
						.addComponent(btnProducts)
						.addComponent(btnAccounts)
						.addComponent(btnLogout))
					.addGap(2)
					.addComponent(lblErrorMEssage, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
		);
		panel_2.setLayout(gl_panel_2);
		imsPagePanel.setLayout(gl_imsPagePanel);
		
		registerPanel = new JPanel();
		registerPanel.setBackground(Color.decode("#d3d2d4"));
		
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
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
					.addContainerGap())
		);
		layeredPane.setLayout(new CardLayout(0, 0));
		layeredPane.add(loginPanel, "name_23923162085563");
		layeredPane.add(imsPagePanel, "name_23923199800925");
		layeredPane.add(registerPanel, "name_23923251166128");
		contentPane.setLayout(gl_contentPane);
	}
	
//	/**
//	 * @return the currentProducts
//	 */
//	private List<ProductDTO> getCurrentProducts() {
//		return currentProducts;
//	}
//
//	/**
//	 * @param currentProducts the currentProducts to set
//	 */
//	private void setCurrentProducts(List<ProductDTO> currentProducts) {
//		this.currentProducts = currentProducts;
//	}

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
	
	private void refreshProductPanel(List<ProductDTO> products) {
		lblErrorMEssage.setText(error);
		
		if (error == null || error.length() == 0) {
			
			//populate product page with data
			//Add Product
			textFieldProductName.setText("");
			textFieldProductPrice.setText("");
			textFieldProductQuantity.setText("");
			textFieldLimit.setText("");
			
			//Update product
			comboBoxProductsMap = new HashMap<Integer, String>();
			//comboBoxProduct.removeAll();
			int index = 0;
			List<String> names = new ArrayList<String>();
			names.clear();
			comboBoxProduct.removeAllItems();

			for (ProductDTO p : products) {
				names.add(p.getName());
			}
			Collections.sort(names);
			for (String name : names) {
				comboBoxProductsMap.put(index, name);
				comboBoxProduct.addItem(name);
				index++;
			}
			comboBoxProduct.setSelectedIndex(-1);
			
			textFieldUpdateProductName.setText("");
			textFieldUpdateProductPrice.setText("");
			textFieldUpdateProductQuantity.setText("");
			textFieldUpdateLimit.setText("");
			
		}
	}
	
	private void refreshProductTable(List<ProductDTO> products) {
		
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
			int row = 0;
			tableProductsMap = new HashMap<Integer, ProductDTO>();
			for (ProductDTO p : products) {
				model.addRow(new Object[] {p.getName(), roundOff(p.getItemPrice()), p.getQuantity()});
				tableProductsMap.put(row, p);
				row++;
			}
		}
		
	}
	
	private void printTable(JTable table) {
		try {
		    if (! table.print()) {
		        System.err.println("User cancelled printing");
		    }
		} catch (java.awt.print.PrinterException e) {
		    System.err.format("Cannot print %s%n", e.getMessage());
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
			
			double totalBalance = 0.0f;
			transactionIds = new HashMap<Integer, String>();
			String employeeId = ImsDesktopApplication.getCurrentEmployeeId();
			int index = comboBoxCustomer.getSelectedIndex();
			String customerUserName = customers.get(index);
			CustomerDTO customerDTO = customerService.getCustomerTransactions(customerUserName, employeeId);
			int row = 0;
			for (TransactionDTO tT : customerDTO.getTransactions()) {
				String date = tT.getDate().substring(0, 10);
				double balance = tT.getTotalAmount() - tT.getAmountPaid();
				model.addRow(new Object[] {date, roundOff(tT.getTotalAmount()), roundOff(tT.getAmountPaid()), roundOff(balance)});
				totalBalance = (double) (totalBalance + balance);
				transactionIds.put(row, tT.getTransactionId());
				row++;
			}
			
			//textFieldCurrentPaid.setText("");
			lblTotalBalance.setText("" + roundOff(totalBalance));
			//textFieldUpdatePaid.setText("");
		}
		
	}
	
	private static double roundOff(double value) {
		
		double roundOff = Math.round(value * 100.0) / 100.0;
		
		return roundOff;
	}
	
	private void refreshTransactionPanel() {
		lblErrorMEssage.setText(error);
		
		if (error == null || error.length() == 0) {
			
			lblProductTransaction.setText("");
			lblProductTransaction.setText("");
			textFieldTranUpdateQuantity.setText("");
			textFieldTransactionProductQuantity.setText("");
			textFieldPay.setText("");
			
			
			comboBoxAddProductMap = new HashMap<String, String>();
			
			//Update transaction
			transactionProducts = new HashMap<Integer, String>();
			int index = 0;
			List<String> names = new ArrayList<String>();
			names.clear();
			comboBoxTransactionProduct.removeAllItems();
			List<ProductDTO> databaseProducts = productService.getProducts(ImsDesktopApplication.getCurrentEmployeeId());
			for (ProductDTO p : databaseProducts) {
				names.add(p.getName());
				comboBoxAddProductMap.put(p.getName(), "" + p.getItemPrice());
			}
			Collections.sort(names);
			for (String name : names) {
				transactionProducts.put(index, name);
				comboBoxTransactionProduct.addItem(name);
				index++;
			}
			comboBoxTransactionProduct.setSelectedIndex(-1);
			

			lblItemPrice.setText("");
			
		}
	}
	
	private void refreshTransactionDetail() {
		lblErrorMEssage.setText(error);
		if (error == null || error.length() == 0) {
			String employeeId = ImsDesktopApplication.getCurrentEmployeeId();
			DefaultTableModel model = (DefaultTableModel) tableTransaction.getModel();
			//set the alignment of total amount column
			DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
			leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
			
			tableTransaction.getColumnModel().getColumn(0).setCellRenderer(leftRenderer);
			tableTransaction.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
			tableTransaction.getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
			tableTransaction.getColumnModel().getColumn(3).setCellRenderer(leftRenderer);
			
			model.setRowCount(0);
			if (selectedCustomerUserName != null) {
				
				double totalAmount = 0.0;
				if (transactionDetail.getpTransactions() != null) {
					for (ProductTransactionDTO tp : transactionDetail.getpTransactions()) {
						// unit price = total price / quantity of products
						double unitPrice = tp.getPrice() / tp.getQuantity();
						model.addRow(new Object[] {tp.getProduct().getName(), roundOff(unitPrice), tp.getQuantity(), roundOff(tp.getPrice())});
						totalAmount = totalAmount + tp.getPrice();
					}
				}
				//update transaction details
				labelTranCustomerName.setText(transactionDetail.getFirstName() + " " + transactionDetail.getLastName());
				labelTranCustomerNumber.setText(transactionDetail.getPhoneNumber());
				lbllabelTranDate.setText(transactionDetail.getDate());
				labelTranAmount.setText(roundOff(totalAmount) + "");
				labelTranAmountPaid.setText(roundOff(transactionDetail.getAmountPaid()) +"");
				labelTranAmountLeft.setText(roundOff(totalAmount -  transactionDetail.getAmountPaid()) +"");
				
			}
			
		}
		
	}
	
	public void printComponenet(Component component){
		  PrinterJob pj = PrinterJob.getPrinterJob();
		  pj.setJobName(" Print Component ");

		  pj.setPrintable (new Printable() {    
		    public int print(Graphics pg, PageFormat pf, int pageNum){
		      if (pageNum > 0){
		      return Printable.NO_SUCH_PAGE;
		      }

		      Graphics2D g2 = (Graphics2D) pg;
		      g2.translate(pf.getImageableX(), pf.getImageableY());
		      component.paintAll(g2);
		      return Printable.PAGE_EXISTS;
		    }
		  });
		  if (pj.printDialog() == false)
		  return;

		  try {
		        pj.print();
		  } catch (PrinterException ex) {
		        // handle exception
		  }
		}
	
	private void refreshDashBoard() {
		Employee currentEmployee = ImsDesktopApplication.getCurrentEmployee();
		String employeeFirstName = currentEmployee.getFirstName();
		String employeeLastName = currentEmployee.getLastName();
		lblEmployee.setText(employeeFirstName + " " + employeeLastName); 
	}
	
	private void showInvoice() {
		TransactionDetail transactionDetail = transactionService.getTransactionDetail(
				ImsDesktopApplication.getCurrentEmployeeId(), currentTransactionID, selectedCustomerUserName);
		textArea.setText("");
		System.out.println(textArea.getAlignmentX());
		textArea.setAlignmentX(CENTER_ALIGNMENT);
//		textArea.append("\tDE DON MOTORS CO. L.T.D");
//		textArea.append("IN GOD WE TRUST\n");
//		textArea.append("LET LOVE LEAD\n\n");
		
		textArea.append(" 			DE DON MOTORS CO. L.T.D                                  \r\n"
				      + "			 IN WE TRUST                                          \r\n"
				      + "  	SOLE AGENT OF AVATA SPECIAL QUALITY MOTORCYCLE/SPARE PARTS AND DIAMOND TIRES/TUBES  \r\n"
				      + "   	 (Head Office: Techiman, B/E Along Kintampo Road Before Toll Booth Tuobodom)         \r\n"
				      + "  	Box 120 TECHIMAN B.E/R GHANA: Tel: 0243679200/0209380084/055355214/0247832338       \r\n"
				      + "  	E-mail: dedon.motors@yahoo.com/dedonmotor@gmail.com                                 \r\n"
				      + "  	    			 Branches										   \r\n"
				      + "  	Wa : Along Wa Poly Road (Tel: 0244485813/0246017637)								 \r\n"
				      + "  	Kumasi: AlabaFulani Chief House (Tel: 0551939054)									 \r\n"
				      + "  	Bolga (Tel: 0209784545/0553818004)  												 \r\n"
				      + "			\r\n"
				      + "");
		
		//textArea.setAlignmentY(LEFT_ALIGNMENT);
		textArea.append("Name: "+ transactionDetail.getCustomerName()+"\n");
		textArea.append("Date: "+ transactionDetail.getDate()+"\n\n");
		textArea.append("NO\t");
		textArea.append("NAME\t");
		textArea.append("QUANTITY\t");
		textArea.append("UNIT PRICE\t");
		textArea.append("AMOUNT\n\n");
		int count = 1;
		for (ProductTransactionDTO pTransaction : transactionDetail.getpTransactions()) {
			textArea.append(""+count+"\t");
			textArea.append(pTransaction.getProduct().getName()+"\t");
			textArea.append(""+pTransaction.getQuantity()+"\t");
			double unitPrice = pTransaction.getPrice() / pTransaction.getQuantity();
			textArea.append(""+unitPrice+"\t");
			//textArea.append(""+pTransaction.getPrice() * pTransaction.getQuantity()+"\n\n");
			textArea.append(""+pTransaction.getPrice()+"\n\n");
			count++;	
		}
		//textArea.setAlignmentY(RIGHT_ALIGNMENT);
		//textArea.append("Total Amount : "+receipt.getTotalAmount());
		
		layeredMainPane.removeAll();
		layeredMainPane.add(receiptPanel);
		layeredMainPane.repaint();
		layeredMainPane.revalidate();
		btnDashboard.setForeground(Color.GREEN);
		btnProducts.setForeground(Color.GREEN);
		btnAccounts.setForeground(Color.GREEN);
		
	}
	
	/**
	 * To improve speed, this method updates products in a transaction in the view. This is done after the transaction has been updated
	 * in the remote server.
	 * @param productName
	 * @param newQuantity
	 */
	private void updateTransactionProduct(String productName, int newQuantity) {
		List<ProductTransactionDTO> productDtos = transactionDetail.getpTransactions();
		for (ProductTransactionDTO p : productDtos) {
			if (p.getProduct().getName().equals(productName)) {
				p.setQuantity(newQuantity);
				p.setPrice(newQuantity * p.getProduct().getItemPrice());
				break;
			}
		}
	}
	
	private void removeProductTransaction(String productName) {
		ProductTransactionDTO removeProductTransaction = null;
		for (ProductTransactionDTO p : transactionDetail.getpTransactions()) {
			if (p.getProduct().getName().equals(productName)) {
				removeProductTransaction = p;
				break;
			}
		}
		if (removeProductTransaction != null) {
			transactionDetail.getpTransactions().remove(removeProductTransaction);
		}
	}
	
	private void addNewProductTransaction(ProductTransactionDTO newProductTransaction) {
		transactionDetail.getpTransactions().add(newProductTransaction);
	}
	
	private void updateAmountPaid(double newAmount) {
		transactionDetail.setAmountPaid(transactionDetail.getAmountPaid() + newAmount);
	}

}
