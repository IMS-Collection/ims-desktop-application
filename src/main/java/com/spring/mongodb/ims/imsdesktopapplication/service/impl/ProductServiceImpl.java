package com.spring.mongodb.ims.imsdesktopapplication.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mongodb.ims.imsdesktopapplication.ImsDesktopApplication;
import com.spring.mongodb.ims.imsdesktopapplication.exceptions.InvalidInputException;
import com.spring.mongodb.ims.imsdesktopapplication.model.Employee;
import com.spring.mongodb.ims.imsdesktopapplication.model.Product;
import com.spring.mongodb.ims.imsdesktopapplication.repository.EmployeeRepository;
import com.spring.mongodb.ims.imsdesktopapplication.repository.ProductRepository;
import com.spring.mongodb.ims.imsdesktopapplication.service.ProductService;
import com.spring.mongodb.ims.imsdesktopapplication.shared.Utils;
import com.spring.mongodb.ims.imsdesktopapplication.shared.dto.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	Utils utils;
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public void callCreateProduct() throws InvalidInputException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * This method creates new instance of {@link Product}
	 * @param productDTO is transfer object object which contains name, price, and quantity of the product
	 */
	@Override
	public void createProduct(ProductDTO productDTO, String employeeId) throws InvalidInputException {
		
		Employee employee = employeeRepository.findByEmployeeId(employeeId);
		boolean loggedIn = false;
		if (employee == null) {
			throw new InvalidInputException("An employee must log in.");
		}
		for (Employee e : ImsDesktopApplication.getCurrentEmployees()) {
			if (e.getUserName().equals(employee.getUserName())) {
				loggedIn = true;
			}
		}
		String error = "";
		if (!loggedIn) {
			error = "An employee must log in before creating a product.";
		} else if (!employee.isManager()) {
			error = "A manager is required to create a product.";
		} else if (productDTO.getName() == null || productDTO.getName().equals("")) {
			error = "The name of a product cannot be empty.";
		} else if (productDTO.getItemPrice() < 0) {
			error = "The price of a product cannot be negative.";
		} else if (productDTO.getItemPrice() == 0) {
			error = "The price of a product cannot be zero";
		} else if (productDTO.getQuantity() <= 0) {
			error = "Quantity of a product cannot be less than one.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		ModelMapper modelMapper = new ModelMapper();
		
		Product newProduct = modelMapper.map(productDTO, Product.class);
		String publicProductId = utils.generateEmployeeId(30);
		newProduct.setProductId(publicProductId);
		
		try {
			productRepository.save(newProduct);
		} catch (Exception e) {
			//TODO: fine-tune the response
			throw new InvalidInputException("Error!, seems the name already exist");
		}
		
		
	}

	@Override
	public void testCreateProduct(String name) throws InvalidInputException {
		
//		Employee employee = employeeRepository.findByEmployeeId(employeeId);
//		boolean loggedIn = false;
//		if (employee == null) {
//			throw new InvalidInputException("An employee must log in.");
//		}
//		for (Employee e : ImsDesktopApplication.getCurrentEmployees()) {
//			if (e.getUserName().equals(employee.getUserName())) {
//				loggedIn = true;
//			}
//		}
//		String error = "";
//		if (!loggedIn) {
//			error = "An employee must log in before creating a product.";
//		} else if (!employee.isManager()) {
//			error = "A manager is required to create a product.";
//		} else if (productDTO.getName() == null || productDTO.getName().equals("")) {
//			error = "The name of a product cannot be empty.";
//		} else if (productDTO.getItemPrice() < 0) {
//			error = "The price of a product cannot be negative.";
//		} else if (productDTO.getItemPrice() == 0) {
//			error = "The price of a product cannot be zero";
//		} else if (productDTO.getQuantity() <= 0) {
//			error = "Quantity of a product cannot be less than one.";
//		}
//		if (error.length() > 0) {
//			throw new InvalidInputException(error);
//		}
//		
//		ModelMapper modelMapper = new ModelMapper();

//		Product newProduct = modelMapper.map(productDTO, Product.class);
		Product newProduct = new Product();
		
		String publicProductId = utils.generateEmployeeId(30);
		newProduct.setProductId(publicProductId);
		newProduct.setName(name);
		newProduct.setQuantity(100);
		newProduct.setItemPrice(10);
		
		try {
			productRepository.save(newProduct);
		} catch (Exception e) {
			//TODO: fine-tune the response
			throw new InvalidInputException("Error!, seems the name already exist");
		}
		
		
	}

	/**
	 * Deletes an instance of a product
	 */
	@Transactional
	@Override
	public void deleteProduct(String name, String employeeId) throws InvalidInputException {
		
		Employee employee = employeeRepository.findByEmployeeId(employeeId);
		boolean loggedIn = false;
		if (employee == null) {
			throw new InvalidInputException("An employee must log in.");
		}
		for (Employee e : ImsDesktopApplication.getCurrentEmployees()) {
			if (e.getUserName().equals(employee.getUserName())) {
				loggedIn = true;
			}
		}
		
		String error = "";
		if (!loggedIn) {
			error = "An employee must log in before deleting a product.";
		} else if (!employee.isManager()) {
			error = "A manager is required to delete a product.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		Product product = productRepository.findByName(name);
		if (product == null) {
			throw new InvalidInputException("The product does not exist.");
		}
		
		//No need to updated corresponding product since a product used
		// in a transaction cannot be deleted
		if (product.getProductTransactions() != null && product.getProductTransactions().size() > 0) {
			throw new InvalidInputException("This product is used in a transaction, cannot be Deleted!");
		}
		productRepository.delete(product);
	}

	/**
	 * updates the details of a given instance of a {@link Product}
	 */
	@Override
	public void updateProduct(String oldName, ProductDTO productDTO, String employeeId) throws InvalidInputException {
		
		Employee employee = employeeRepository.findByEmployeeId(employeeId);
		boolean loggedIn = false;
		if (employee == null) {
			throw new InvalidInputException("An employee must log in.");
		}
		for (Employee e : ImsDesktopApplication.getCurrentEmployees()) {
			if (e.getUserName().equals(employee.getUserName())) {
				loggedIn = true;
			}
		}
		String error = "";
		if (!loggedIn) {
			error = "An employee must log in before updating a product.";
		} else if (!employee.isManager()) {
			error = "A manager is required to update a product.";
		} else if (productDTO.getName() == null || productDTO.getName().equals("")) {
			error = "The name of a product cannot be empty.";
		} else if (productDTO.getItemPrice() < 0) {
			error = "The price of a product cannot be negative.";
		} else if (productDTO.getItemPrice() == 0) {
			error = "The price of a product cannot be zero";
		} else if (productDTO.getQuantity() <= 0) {
			error = "Quantity of a product cannot be less than one.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}

		
		Product product = productRepository.findByName(oldName);
		
		if (product != null) {
			product.setName(productDTO.getName());
			product.setItemPrice(productDTO.getItemPrice());
			product.setQuantity(productDTO.getQuantity());
		}
		
		try {
			productRepository.save(product);
		} catch (Exception e) {
			throw new InvalidInputException("Error!, seems the name already exist or the product does not exist");
		}
		
	}
	
	/**
	 * Get products
	 */
	public List<ProductDTO> getProducts(String employeeId) {
		
		List<ProductDTO> returnValue = new ArrayList<ProductDTO>();
		 
		long startTime = System.currentTimeMillis();
		Employee employee = employeeRepository.findByEmployeeId(employeeId);
		long endTime = System.currentTimeMillis();
		//System.out.println("Find employee by id: " + (endTime - startTime));
		
		
		boolean loggedIn = false;
		if (employee == null) {
			throw new InvalidInputException("An employee must log in.");
		}
		for (Employee e : ImsDesktopApplication.getCurrentEmployees()) {
			if (e.getUserName().equals(employee.getUserName())) {
				loggedIn = true;
			}
		}
		if (!loggedIn) {
			throw new InvalidInputException("Error! an employee must log in..");
		}  
		ModelMapper modelMapper = new ModelMapper();
		startTime = System.currentTimeMillis();
		Iterable<Product> products = productRepository.findAll();
		endTime = System.currentTimeMillis();
		System.out.println("Find all products: " + (endTime - startTime));

		for (Product p : products) {
			returnValue.add(modelMapper.map(p, ProductDTO.class));
		}
		return returnValue;
	}

	/**
	 * Adds item(s) to an existing product
	 * @param name of the product
	 * @param quantity of the product;
	 */
	@Override
	public void addProductItem(String name, int newQuantity, String employeeId) throws InvalidInputException {
		
		Employee employee = employeeRepository.findByEmployeeId(employeeId);
		boolean loggedIn = false;
		if (employee == null) {
			throw new InvalidInputException("An employee must log in.");
		}
		for (Employee e : ImsDesktopApplication.getCurrentEmployees()) {
			if (e.getUserName().equals(employee.getUserName())) {
				loggedIn = true;
			}
		}
		
		String error = "";
		if (!loggedIn) {
			error = "An employee must log in before adding product items.";
		} else if (!employee.isManager()) {
			error = "A manager is required to add product items.";
		} 
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		
		if (name == null || name.length() == 0) {
			error = "The name of a product cannot be empty.";
		}
		if (newQuantity < 0) {
			error = "Quantity of a product cannot be less than zero.";
		}
		if (error.length() > 0) {
			throw new InvalidInputException(error);
		}
		Product product = productRepository.findByName(name);
		if (product != null) {
			product.setQuantity(product.getQuantity() + newQuantity);
			productRepository.save(product);
		} else {
			throw new InvalidInputException("The product does not exist.");
		}
		
			
	}

}
