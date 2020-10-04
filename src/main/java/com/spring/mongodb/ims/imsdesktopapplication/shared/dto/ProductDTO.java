package com.spring.mongodb.ims.imsdesktopapplication.shared.dto;

public class ProductDTO {

	private String name;
	private double itemPrice;
	private int quantity;
	private int limit;

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
	
	

}
