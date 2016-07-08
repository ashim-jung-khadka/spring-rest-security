package com.github.ashim.persistence.common;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ashim.persistence.common.config.PersistenceConfig;
import com.github.ashim.persistence.common.utility.RelationBuilder;
import com.github.ashim.persistence.entity.Customer;
import com.github.ashim.persistence.entity.Item;
import com.github.ashim.persistence.repo.CustomerRepository;
import com.github.ashim.persistence.repo.ItemRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersistenceConfig.class })
public class RelationBuilderTest {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private ItemRepository itemRepo;

	@Test
	public void run() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

			List<Item> items = itemRepo.findAll();
			List<Customer> customers = customerRepo.findAll();

			System.out.println(RelationBuilder.build(items));
			System.out.println(RelationBuilder.build(customers));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run2() {

		List<Customer> customers = customerRepo.findAll();
		List<Item> items = itemRepo.findAll();

		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

		try {
			String result = mapper.writeValueAsString(customers);
			System.out.println(result);

			result = mapper.writeValueAsString(items);
			System.out.println(result);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

}