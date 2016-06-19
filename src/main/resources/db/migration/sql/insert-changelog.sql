-- liquibase formatted sql

-- -----------------------------------------------------
-- Table product
-- -----------------------------------------------------
-- changeset ashim:ins_product
	INSERT INTO product(id, product_name, product_code, description)
	VALUES (1, "almost me", "AM-123", "almost me");
	
	INSERT INTO product(id, product_name, product_code, description)
	VALUES (2, "float pool", "FP-123", "float pool");
-- rollback TRUNCATE TABLE product;