package com.github.ashim.persistence.service;

import java.util.List;

import com.github.ashim.persistence.entity.Customer;

public interface CustomerService {

	public List<Customer> findAll();

	public Customer findById(Integer customerId) throws Exception;

	public Customer insert(Customer customer);

	public Customer update(Customer customer) throws Exception;

	public Customer deleteById(Integer customerId) throws Exception;

}
