package com.spring.mongodb.ims.imsdesktopapplication;

import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import javax.swing.JFrame;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.spring.mongodb.ims.imsdesktopapplication.exceptions.InvalidInputException;
import com.spring.mongodb.ims.imsdesktopapplication.model.Customer;
import com.spring.mongodb.ims.imsdesktopapplication.model.Employee;

@SpringBootApplication
public class ImsDesktopApplication extends JFrame{
	
	Object imsPage;
	public ImsDesktopApplication() {
	}
	
//	use jdialog for register page
//	About about = new About()
//	about.setModal(true)
//	about.steVisible(true)
//	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5035583466578245836L;
	private static List<Employee> currentEmployees;
	private static JFrame frame;
	
	public static void main(String[] args) {
		init();
		var springApp = new SpringApplicationBuilder(ImsDesktopApplication.class)
                .headless(false).run(args);

        EventQueue.invokeLater(() -> {

            var imsPage = springApp.getBean(ImsMainPage.class);
            imsPage.setVisible(true);
        });
	}

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
	
}
