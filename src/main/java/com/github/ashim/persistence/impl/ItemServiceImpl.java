package com.github.ashim.persistence.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.ashim.persistence.common.utility.RelationBuilder;
import com.github.ashim.persistence.entity.Item;
import com.github.ashim.persistence.repo.ItemRepository;
import com.github.ashim.persistence.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepo;

	@Override
	public List<Item> findAll() {

		return RelationBuilder.buildCollection(itemRepo.findAll());
	}

	@Override
	public Item findById(Integer itemId) throws Exception {
		Item item = itemRepo.findById(itemId);

		if (item == null) {
			throw new Exception();
		}

		return RelationBuilder.build(item);
	}

	@Override
	public Item insert(Item item) {

		return itemRepo.save(item);
	}

	@Override
	public Item update(Item item) throws Exception {

		findById(item.getId());

		return itemRepo.save(item);
	}

	@Override
	public Item deleteById(Integer itemId) throws Exception {
		Item item = findById(itemId);

		itemRepo.delete(item);

		return item;
	}

}