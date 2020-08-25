package com.spring.mongodb.ims.imsdesktopapplication;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.spring.mongodb.ims.imsdesktopapplication.exceptions.InvalidInputException;
import com.spring.mongodb.ims.imsdesktopapplication.model.Customer;
import com.spring.mongodb.ims.imsdesktopapplication.model.Employee;
import com.spring.mongodb.ims.imsdesktopapplication.service.ProductService;

@SpringBootApplication
public class ImsDesktopApplication extends JFrame{
//	public ImsDesktopApplication() {
//	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5035583466578245836L;
	private static List<Employee> currentEmployees;
	private static JFrame frame;
	
	public static void main(String[] args) {
		init();
		var ctx = new SpringApplicationBuilder(ImsDesktopApplication.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {

            var ex = ctx.getBean(ImsDesktopApplication.class);
            ex.setVisible(true);
        });
	}

//	public static void main(String[] args) {
//		init();
//		SpringApplication.run(ImsDesktopApplication.class, args);
//		System.setProperty("java.awt.headless", "false");
//		
//		/**
//		 * Start application
//		 */
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					frame = new LoginPage();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public static void setCurrentEmployees(List<Employee> currentEmployees) {
		ImsDesktopApplication.currentEmployees = currentEmployees;
	}

	public static List<Employee> getCurrentEmployees() {
		return currentEmployees;
	}

	private static List<Customer> currentCustomers;

	public static void setCurrentCustomers(List<Customer> currentCustomers) {
		ImsDesktopApplication.currentCustomers = currentCustomers;
	}

	public static List<Customer> getCurrentCustomers() {
		return currentCustomers;
	}

	public static void removeCurrentEmployee(String userName) {
		Employee employee = null;
		for (Employee currentEmployee : getCurrentEmployees()) {
			if (currentEmployee.getUserName().equals(userName)) {
				employee = currentEmployee;
			}
		}
		if (employee == null) {
			throw new InvalidInputException("The employee is not currently logged in");
		}
		getCurrentEmployees().remove(employee);
	}

	public static String getCurrentDate() {
		// This is what I have before which uses default time zone
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//		Date date = new Date();
//		return dateFormat.format(date);
		
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();

		// To TimeZone Europe/London
        SimpleDateFormat sdfGhana = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
        TimeZone tzInGhana = TimeZone.getTimeZone("Europe/London");
        sdfGhana.setTimeZone(tzInGhana);
        
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.setTimeZone(tzInGhana);
        
        return sdfGhana.format(calendar.getTime());
	}

	public static void addCurrentCustomer(Customer currenteCustomer) {
		if (currentCustomers == null) {
			init();
			currentCustomers.add(currenteCustomer);
		} else {
			currentCustomers.add(currenteCustomer);
		}
	}

	private static void init() {
		currentEmployees = new ArrayList<Employee>();
		currentCustomers = new ArrayList<Customer>();
	}

	public static void addCurrentEmployee(Employee currenteEmployee) {
		if (currentEmployees == null) {
			init();
			currentEmployees.add(currenteEmployee);
		} else {
			currentEmployees.add(currenteEmployee);
		}

	}
	
	/**
	 * @return the frame
	 */
	public static JFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame the frame to set
	 */
	public static void setFrame(JFrame frame) {
		ImsDesktopApplication.frame = frame;
	}
	
	
	/**** login page  *****/

	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField passwordField;
	private JLabel errorMessage;
	private String error = "";
	
	@Autowired
	ProductService productService;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public ImsDesktopApplication() {
		//setUndecorated(true);
		UIManager.put("OptionPane.messageForeground", Color.green);
		Image iconImg = new ImageIcon(this.getClass().getResource("/motorcyclist-icon.png")).getImage();
		setIconImage(iconImg);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 515);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new LineBorder(Color.WHITE, 4));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		Image motorcycle = new ImageIcon(this.getClass().getResource("/motorcycle.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(motorcycle));
		lblNewLabel.setBounds(31, 63, 302, 274);
		contentPane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 3, true));
		panel.setBounds(358, 92, 569, 327);
		contentPane.add(panel);
		
		JLabel lblUserLogin = new JLabel("User Login");
		Image img = new ImageIcon(this.getClass().getResource("/person-icon.png")).getImage();
		lblUserLogin.setIcon(new ImageIcon(img));
		//validate();
		lblUserLogin.setForeground(Color.WHITE);
		lblUserLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserLogin.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		userName = new JTextField();
		userName.setColumns(10);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUserName.setForeground(Color.WHITE);
		
		passwordField = new JPasswordField();
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel usericon = new JLabel("");
		Image userIcon = new ImageIcon(this.getClass().getResource("/user.png")).getImage();
		usericon.setIcon(new ImageIcon(userIcon));
		
		JLabel passwordIcon = new JLabel("");
		Image password = new ImageIcon(this.getClass().getResource("/password.png")).getImage();
		passwordIcon.setIcon(new ImageIcon(password));
		Image okay = new ImageIcon(this.getClass().getResource("/okay.png")).getImage();
		
		JButton btnLogout = new JButton("LOGOUT");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(ImsDesktopApplication.getFrame(), "Successfully logged out", 
						"Logout status", JOptionPane.INFORMATION_MESSAGE);				
				dispose();
			}
		});
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLogout.setBackground(Color.GRAY);
		btnLogout.setForeground(Color.WHITE);
		Image logout = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
		btnLogout.setIcon(new ImageIcon(logout));
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//clear error
				error = "";
				String name = userName.getText();
				char[] pass = passwordField.getPassword();
				String password = new String(pass);
				
				boolean loggedIn = true;
				
				if (name != null && name.length() > 0) {
					if (password != null && password.length() > 0) {
						try {
							//ImsController.login(name, password);
							productService.testCreateProduct(name);
							
						} catch (InvalidInputException e) {
							loggedIn = false;
							error = e.getMessage();
						}
					} else {
						loggedIn = false;
						error = "You can't log in with empty password";
					}
				} else {
					loggedIn = false;
					error = "You can't log in with empty user name";
				}
				//TODO: review and update
//				if(loggedIn) {
//					/**
//					 * Launch the main page.
//					 */
//					try {
//						ImsPage frame = new ImsPage();
//						frame.setVisible(true);
//						ImsApplication.getFrame().setVisible(false);
//						ImsApplication.setFrame(frame);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
				
				//update visuals
				refreshData();
			}
		});
		btnLogin.setBackground(Color.GRAY);
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 18));
		Image login = new ImageIcon(this.getClass().getResource("/okay.png")).getImage();
		btnLogin.setIcon(new ImageIcon(login));
		
		errorMessage = new JLabel("Error Mesages");
		errorMessage.setForeground(Color.RED);
		errorMessage.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						//TODO: review and update
//						try {
//							RegisterPage frame= new RegisterPage();
//							frame.setVisible(true);
//							ImsApplication.getFrame().setVisible(false);
//							ImsApplication.setFrame(frame);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
					}
				});
			}
		});
		Image register = new ImageIcon(this.getClass().getResource("/register.png")).getImage();
		btnRegister.setIcon(new ImageIcon(register));
		btnRegister.setBackground(Color.GRAY);
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addGap(178)
										.addComponent(lblUserLogin, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE))
									.addGroup(gl_panel.createSequentialGroup()
										.addGap(29)
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(lblPassword, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(lblUserName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGap(52)
										.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
											.addComponent(userName, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE)
											.addGroup(gl_panel.createSequentialGroup()
												.addGap(6)
												.addComponent(btnLogin)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
											.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 268, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
											.addComponent(passwordIcon, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
											.addComponent(usericon, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))))
								.addGap(76))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(errorMessage, GroupLayout.PREFERRED_SIZE, 490, GroupLayout.PREFERRED_SIZE)
								.addGap(50)))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(btnRegister)
							.addGap(127))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblUserLogin, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUserName)
								.addComponent(userName, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
						.addComponent(usericon, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblPassword)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(passwordIcon, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLogout, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addComponent(errorMessage)
					.addGap(18)
					.addComponent(btnRegister)
					.addContainerGap(21, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JLabel lblDedonMotorsInventory = new JLabel("DE-DON MOTORS INVENTORY SYSTEM");
		lblDedonMotorsInventory.setForeground(Color.WHITE);
		lblDedonMotorsInventory.setHorizontalAlignment(SwingConstants.CENTER);
		lblDedonMotorsInventory.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblDedonMotorsInventory.setBounds(403, 16, 488, 60);
		contentPane.add(lblDedonMotorsInventory);
		
		//setUndecorated(true);
		
		refreshData();
	}
	
	private void refreshData() {
		//error
		errorMessage.setText(error);
		
		//clear contents
		userName.setText("");
		passwordField.setText("");
	}
	
	/**** login page   ******/
	
}
