package com.github.ashim.persistence.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.ashim.persistence.common.spec.SpecBuilder;
import com.github.ashim.persistence.entity.Product;
import com.github.ashim.persistence.repo.ProductRepository;
import com.github.ashim.persistence.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Override
	public List<Product> findAll() {
		return productRepo.findAll();
	}

	@Override
	public Product findById(Integer productId) throws Exception {
		Product product = productRepo.findById(productId);

		if (product == null) {
			throw new Exception();
		}

		return product;
	}

	@Override
	public Product insert(Product product) {

		return productRepo.save(product);
	}

	@Override
	public Product update(Product product) throws Exception {

		findById(product.getId());

		return productRepo.save(product);
	}

	@Override
	public Product deleteById(Integer productId) throws Exception {
		Product product = findById(productId);

		productRepo.delete(product);

		return product;
	}

	@Override
	public List<Product> findAllBySpec(String search) {

		Specification<Product> spec = SpecBuilder.build(search);
		return productRepo.findAll(spec);

	}

}