package com.github.ashim.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.github.ashim.persistence.entity.Product;
import com.github.ashim.persistence.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProducts() {

		LOGGER.info("GET /products");

		return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Product> getProductsForTest() {

		LOGGER.info("GET /products/test");

		return productService.findAll();
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProduct(@PathVariable Integer productId) throws Exception {

		LOGGER.info("GET /products");
		LOGGER.debug("productId :: {}", productId);

		return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Product> insertProduct(@RequestBody Product product) {

		LOGGER.info("POST /products ::");
		LOGGER.debug("{}", product);

		return new ResponseEntity<>(productService.insert(product), HttpStatus.OK);
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@PathVariable Integer productId, @RequestBody Product product)
			throws Exception {

		LOGGER.info("PUT /products");
		LOGGER.debug("productId :: {}", productId);
		LOGGER.debug("product :: {}", product);

		product.setId(productId);

		return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
	}

	@RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Product> deleteProduct(@PathVariable Integer productId) throws Exception {

		LOGGER.info("/products");
		LOGGER.debug("productId :: {}", productId);

		return new ResponseEntity<>(productService.deleteById(productId), HttpStatus.OK);
	}

}