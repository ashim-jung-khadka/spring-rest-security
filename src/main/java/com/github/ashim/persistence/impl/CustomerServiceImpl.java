package com.github.ashim.persistence.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.ashim.persistence.common.utility.RelationBuilder;
import com.github.ashim.persistence.entity.Customer;
import com.github.ashim.persistence.repo.CustomerRepository;
import com.github.ashim.persistence.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public List<Customer> findAll() {

		return RelationBuilder.buildCollection(customerRepo.findAll());
	}

	@Override
	public Customer findById(Integer customerId) throws Exception {
		Customer customer = customerRepo.findById(customerId);

		if (customer == null) {
			throw new Exception();
		}

		return RelationBuilder.build(customer);
	}

	@Override
	public Customer insert(Customer customer) {

		return customerRepo.save(customer);
	}

	@Override
	public Customer update(Customer customer) throws Exception {

		findById(customer.getId());

		return customerRepo.save(customer);
	}

	@Override
	public Customer deleteById(Integer customerId) throws Exception {
		Customer customer = findById(customerId);

		customerRepo.delete(customer);

		return customer;
	}

}