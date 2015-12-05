package com.basab.globalmart.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.basab.globalmart.dao.ProductDao;
import com.basab.globalmart.dto.Product;

@Service
public class ProductDaoImpl implements ProductDao{

	@Autowired
	private NamedParameterJdbcTemplate jdbcNTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String INSERT_SQL = "insert into GMDB.PRODUCT "
			+ " ( "
			+ "    PRODUCT_ID, "
			+ "    PRODUCT_NAME, "
			+ "    PRODUCT_TYPE, "
			+ "    PRODUCT_PRICE, "
			+ " ) "
			+ " values "
			+ " ( "
			+ "    :product_id, "
			+ "    :product_name, "
			+ "    :product_type, "
			+ "    :product_price, "
			+ " ) ";
	

private static final String DELETE_SQL = "delete from GMDB.PRODUCT where PRODUCT_ID =:product_id";

	public Product insert(Product product) {
		if(product==null)
			return null;
		Map<String, Object> params = new HashMap<>(4);
		//TODO: This is for quick id generation for demo purpose - to be improved
		Random random=new Random();
		int randomNumber=Math.abs((random.nextInt(65536)-32768));
		product.setProductId(new Long(randomNumber)); 
		params.put("product_id", product.getProductId());
		params.put("product_name", product.getProductName());
		params.put("product_type", product.getProductType());
		params.put("product_price", product.getProductPrice());
		jdbcNTemplate.update(INSERT_SQL, params);

		return product;
	}
	
	public Product[] retrieveProductList(String productType) {
			//TODO: Improve later using preparedStatement
			String sqlSelect = "select * from GMDB.PRODUCT";
			if(productType!=null && !productType.equalsIgnoreCase("")){
				sqlSelect = sqlSelect + " where PRODUCT_TYPE='"+productType +"'";
			}
				
			List<Product> rows;
			
			rows = jdbcTemplate.query(sqlSelect, new RowMapper<Product>() {

				@Override
				public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
					Product product = new Product();
					
					product.setProductId(rs.getLong("PRODUCT_ID"));
					product.setProductName(rs.getString("PRODUCT_NAME"));
					product.setProductType(rs.getString("PRODUCT_TYPE"));
					product.setProductPrice(rs.getDouble("PRODUCT_PRICE"));

					return product;
				}
			});

		if (null == rows || rows.isEmpty()) {
			return null;
		}
				
		Product[] products = new Product[rows.size()];	
		return rows.toArray(products);
	}
	
	public Long deleteProduct(Long productId) {
		
		Map<String, Object> param = new HashMap<>(1);
		param.put("product_id", productId);
		List<Product> rows;
		
		jdbcNTemplate.update(DELETE_SQL,param);

		return productId;
	}

}
