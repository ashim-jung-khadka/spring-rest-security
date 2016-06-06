package com.github.ashim.test.model;

import org.springframework.test.util.ReflectionTestUtils;

import com.github.ashim.persistence.entity.Product;

public class ProductTest {

	public static final Integer ID = 1;
	public static final String PRODUCT_NAME = "productName";
	public static final String PRODUCT_NAME_UPDATED = "updatedProductName";
	public static final String PRODUCT_CODE = "productCode";
	public static final String PRODUCT_CODE_UPDATED = "updatedProductCode";
	public static final String DESCRIPTION = "description";
	public static final String DESCRIPTION_UPDATED = "updatedDescription";

	public static Product createModel(Boolean withId) {
		Product product = new Product();
		product.setProductName(PRODUCT_NAME);
		product.setProductCode(PRODUCT_CODE);
		product.setDescription(DESCRIPTION);

		if (withId) {
			ReflectionTestUtils.setField(product, "id", ID);
		}

		return product;
	}

	public static Product createUpdatedModel() {

		Product product = new Product();

		ReflectionTestUtils.setField(product, "id", ID);

		product.setProductName(PRODUCT_NAME_UPDATED);
		product.setProductCode(PRODUCT_CODE_UPDATED);
		product.setDescription(DESCRIPTION_UPDATED);

		return product;
	}

}