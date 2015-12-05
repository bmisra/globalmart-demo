package com.basab.globalmart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basab.globalmart.dto.Product;
import com.basab.globalmart.service.ProductCatalogueService;

@RestController
@RequestMapping("/globalmart")
public class ProcessReqResController {

	@Autowired
	ProductCatalogueService productCatalogueService;
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addproduct(@RequestBody Product product){
		product = productCatalogueService.addproduct(product);
		return "{\"ProductId\":"+product.getProductId() +"}";
	}
	
	@RequestMapping(value = "/retrieveProduct", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Product[] retrieveProduct(@RequestParam(required=false) String productType){
		return productCatalogueService.retrieveProduct(productType);
	}
	
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.POST, headers = "Accept=application/json")
	public String addproduct(@RequestParam long productId){
		productId = productCatalogueService.deleteProduct(productId);
		return "{\"ProductId\":"+ productId +"}";
	}
}
