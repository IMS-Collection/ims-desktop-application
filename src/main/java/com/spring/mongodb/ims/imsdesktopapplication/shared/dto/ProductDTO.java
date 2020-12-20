package com.spring.mongodb.ims.imsdesktopapplication.shared.dto;

import com.spring.mongodb.ims.imsdesktopapplication.model.ProductKind;

public class ProductDTO {

	private String name;
	private double itemPrice;
	private int quantity;
	private int limit;
	private ProductKind productKind;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public ProductKind getProductKind() {
		return productKind;
	}

	public void setProductKind(ProductKind productKind) {
		this.productKind = productKind;
	}
	
	

}
