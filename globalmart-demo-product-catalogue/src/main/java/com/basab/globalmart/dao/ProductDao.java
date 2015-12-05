package com.basab.globalmart.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.jdbc.core.RowMapper;

import com.basab.globalmart.dto.Product;

public interface ProductDao {
	
	public Product insert(Product product);

	public Product[] retrieveProductList(String productType);
	
	public Long deleteProduct(Long productId);
}
