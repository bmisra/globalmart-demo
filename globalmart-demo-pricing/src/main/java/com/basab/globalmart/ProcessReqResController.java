package com.basab.globalmart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.basab.globalmart.dto.Product;
import com.basab.globalmart.service.PricingService;

@RestController
@RequestMapping("/globalmart")
public class ProcessReqResController {

	@Autowired
	PricingService pricingService;
	
	@RequestMapping(value = "/getPrice", method = RequestMethod.GET, headers = "Accept=application/json")
	public String retrieveProduct(@RequestParam long productId){
		Double price =  pricingService.getPrice(productId);
		return "{\"productPrice\":"+ price +"}";
	}
}
