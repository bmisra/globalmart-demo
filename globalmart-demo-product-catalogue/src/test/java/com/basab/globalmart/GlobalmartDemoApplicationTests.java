package com.basab.globalmart;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.basab.globalmart.dto.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GlobalmartDemoApplication.class)
@WebIntegrationTest
public class GlobalmartDemoApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(GlobalmartDemoApplication.class);
	
	@Test
	public void contextLoads() throws InterruptedException{
		
		RestTemplate restTemplate = new RestTemplate();
		
		//Testing addProduct
		final String addProductServiceURI = "http://localhost:9001/globalmart/addProduct";
	    Product ProductOne = new Product("Test Product One","electronic", 11.2);
	    log.info("---------INVOKING ADDPRODUCT 1------");
	    String resultAddProductOne = restTemplate.postForObject(addProductServiceURI, ProductOne, String.class);
	    log.info("RESPONSE:"+resultAddProductOne);
	    
	    Product ProductTwo = new Product("Test Product Two","mechanical", 12.3);
	    log.info("---------INVOKING ADDPRODUCT 2------");
	    String resultAddProductTwo = restTemplate.postForObject(addProductServiceURI, ProductTwo, String.class);
	    log.info("RESPONSE:"+resultAddProductTwo);
	    
	    Product ProductThree = new Product("Test Product Three","electronic", 13.4);
	    log.info("---------INVOKING ADDPRODUCT 3------");
	    String resultAddProductThree = restTemplate.postForObject(addProductServiceURI, ProductThree, String.class);
	    log.info("RESPONSE:"+resultAddProductThree);
	    log.info("------------------------------------");
	    
	    //Testing retrieveProduct
	    final String retrieveProductServiceURI = "http://localhost:9001/globalmart/retrieveProduct?productType=electronic";
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("productType", "electronic");
	    log.info("------INVOKING RETRIEVEPRODUCT------");
	    ResponseEntity<Product[]> retrieveProductResponseEnt = restTemplate.getForEntity(retrieveProductServiceURI, Product[].class);
	    Product[] retrievedProducts = retrieveProductResponseEnt.getBody();
	    log.info("RESPONSE:"+Arrays.asList(retrievedProducts));
	    log.info("------------------------------------");
	    
	    //Testing deleteProduct
	    Product toBeDel = retrievedProducts[1];
		final String deleteProductServiceURI = "http://localhost:9001/globalmart/deleteProduct?productId="+toBeDel.getProductId();
	    log.info("---------INVOKING DELETEPRODUCT-----");
	    String resultdeletedProduct = restTemplate.postForObject(deleteProductServiceURI, "", String.class);
	    log.info("RESPONSE:"+resultdeletedProduct);
	    log.info("------------------------------------");

	}
	

}
