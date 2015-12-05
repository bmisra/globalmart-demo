package com.basab.globalmart.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.basab.globalmart.dto.Product;
import com.basab.globalmart.service.PricingService;

@Service
public class PricingServiceImpl implements PricingService{

	@Override
	public Double getPrice(Long productId) {
		
		Double targetPrice = null;
		// TODO In production-grade implementation client side loadbalance & discovery service (e.g. Ribbon with Eureka) would be used to communicate
		RestTemplate restTemplate = new RestTemplate();
	    final String retrieveProductServiceURI = "http://localhost:9001/globalmart/retrieveProduct";
	    ResponseEntity<Product[]> retrieveProductResponseEnt = restTemplate.getForEntity(retrieveProductServiceURI, Product[].class);
	    Product[] retrievedProducts = retrieveProductResponseEnt.getBody();
	    for(Product product:retrievedProducts){
	    	if(productId.equals(product.getProductId())){
	    		targetPrice = product.getProductPrice();
	    		break;
	    	}
	    }
		return targetPrice;
	}
}
