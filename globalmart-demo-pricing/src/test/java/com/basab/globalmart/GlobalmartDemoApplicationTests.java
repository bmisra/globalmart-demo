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
		
		//prerequisite addProduct - assuming Product catalogue Service is running
		final String addProductServiceURI = "http://localhost:9001/globalmart/addProduct";
	    Product ProductOne = new Product("Test Product Four","electronic", 15.5);
	    log.info("---------INVOKING ADDPRODUCT 1 of ProductCatalogueService------");
	    String resultAddProductOne = restTemplate.postForObject(addProductServiceURI, ProductOne, String.class);
	    log.info("RESPONSE:"+resultAddProductOne);
	    log.info("---------------------------------------------------------------");
	    
	    //retrieving product
	    final String retrieveProductServiceURI = "http://localhost:9001/globalmart/retrieveProduct?productType=electronic";
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("productType", "electronic");
	    log.info("------INVOKING RETRIEVEPRODUCT------");
	    ResponseEntity<Product[]> retrieveProductResponseEnt = restTemplate.getForEntity(retrieveProductServiceURI, Product[].class);
	    Product[] retrievedProducts = retrieveProductResponseEnt.getBody();
	    log.info("RESPONSE:"+Arrays.asList(retrievedProducts));
	    log.info("------------------------------------");
	    
	    //Testing getPrice
	    Product targetProduct = retrievedProducts[0];
	    final String getPriceURI = "http://localhost:10001/globalmart/getPrice?productId="+targetProduct.getProductId();
	    log.info("----------INVOKING GETPRICE---------");
	    String getPriceResponse = restTemplate.getForObject(getPriceURI, String.class);
	    log.info("RESPONSE:"+getPriceResponse);
	    log.info("------------------------------------");
	    
	}
	

}
