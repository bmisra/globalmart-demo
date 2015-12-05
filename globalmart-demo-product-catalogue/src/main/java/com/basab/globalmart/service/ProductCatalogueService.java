package com.basab.globalmart.service;

import com.basab.globalmart.dto.Product;

public interface ProductCatalogueService {

	public Product addproduct(Product product);
	
	public Product[] retrieveProduct(String productType);
	
	public Long deleteProduct(Long productId);
	
}
