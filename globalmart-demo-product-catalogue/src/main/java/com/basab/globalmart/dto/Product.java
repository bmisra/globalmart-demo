package com.basab.globalmart.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Product {
	
	private Long productId;
	private String productName;
	private String productType;
	private Double productPrice;
	
	public Product() {
		super();
	}
	
	public Product( String productName, String productType,
			Double productPrice) {
		super();
		this.productName = productName;
		this.productType = productType;
		this.productPrice = productPrice;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	//@JsonIgnore
	public Double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName="
				+ productName + ", productType=" + productType
				+ "]";
	}
	
	
}
