package com.github.ashim.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.ashim.persistence.entity.Customer;
import com.github.ashim.persistence.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private CustomerService customerService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> getCustomers() {

		LOGGER.info("GET /customers");

		return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	public ResponseEntity<Customer> getCustomer(@PathVariable Integer customerId) throws Exception {

		LOGGER.info("GET /customers");
		LOGGER.debug("customerId :: {}", customerId);

		return new ResponseEntity<>(customerService.findById(customerId), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Customer> insertCustomer(@RequestBody @Valid Customer customer) {

		LOGGER.info("POST /customers ::");
		LOGGER.debug("{}", customer);

		return new ResponseEntity<>(customerService.insert(customer), HttpStatus.OK);
	}

	@RequestMapping(value = "/{customerId}", method = RequestMethod.PUT)
	public ResponseEntity<Customer> updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer)
			throws Exception {

		LOGGER.info("PUT /customers");
		LOGGER.debug("customerId :: {}", customerId);
		LOGGER.debug("customer :: {}", customer);

		customer.setId(customerId);

		return new ResponseEntity<>(customerService.update(customer), HttpStatus.OK);
	}

	@RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE)
	public ResponseEntity<Customer> deleteCustomer(@PathVariable Integer customerId) throws Exception {

		LOGGER.info("/customers");
		LOGGER.debug("customerId :: {}", customerId);

		return new ResponseEntity<>(customerService.deleteById(customerId), HttpStatus.OK);
	}

}