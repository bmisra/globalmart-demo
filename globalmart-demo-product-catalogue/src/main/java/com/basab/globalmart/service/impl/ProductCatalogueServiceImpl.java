package com.basab.globalmart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basab.globalmart.dao.ProductDao;
import com.basab.globalmart.dto.Product;
import com.basab.globalmart.service.ProductCatalogueService;

@Service
public class ProductCatalogueServiceImpl implements ProductCatalogueService{

	@Autowired
	ProductDao productDao;
	//private static 
	public Product addproduct(Product product){
		productDao.insert(product);
		return product;
	}
	
	public Product[] retrieveProduct(String productType){
		return productDao.retrieveProductList(productType);
	}
	
	public Long deleteProduct(Long productId){
		return productDao.deleteProduct(productId);
	}
}
