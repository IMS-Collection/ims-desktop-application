package com.spring.mongodb.ims.imsdesktopapplication.shared.dto;

import com.spring.mongodb.ims.imsdesktopapplication.model.Product;

public class ProductTransactionDTO {
	private double price;
	private int quantity;
	private String pTransactionId;
	private Product product;

	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getpTransactionId() {
		return pTransactionId;
	}

	public void setpTransactionId(String pTransactionId) {
		this.pTransactionId = pTransactionId;
	}

}
