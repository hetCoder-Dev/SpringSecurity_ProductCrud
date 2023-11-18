package com.spring.prod.dao;

import java.util.List;

import com.spring.prod.bean.ProductBean;
import com.spring.prod.entity.Product;

public interface ProductDao {
	
	public List<ProductBean> getAllProducts();
	
	public int createProduct(Product product);
	
	public int createProduct(ProductBean product);
	
	public void updateProduct(ProductBean product);
	
	public Product getProductById(int id);

	public List<ProductBean> viewAllProducts();
	
	public void removeProduct(int id);
	
	
}
