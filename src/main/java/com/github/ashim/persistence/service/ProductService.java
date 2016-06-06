package com.github.ashim.persistence.service;

import java.util.List;

import com.github.ashim.persistence.entity.Product;

public interface ProductService {

	public List<Product> findAll();

	public Product findById(Integer productId) throws Exception;

	public Product insert(Product product);

	public Product update(Product product) throws Exception;

	public Product deleteById(Integer productId) throws Exception;

	public List<Product> findAllBySpec(String search);

}
