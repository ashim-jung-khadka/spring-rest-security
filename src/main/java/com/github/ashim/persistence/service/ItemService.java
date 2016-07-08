package com.github.ashim.persistence.service;

import java.util.List;

import com.github.ashim.persistence.entity.Item;

public interface ItemService {

	public List<Item> findAll();

	public Item findById(Integer itemId) throws Exception;

	public Item insert(Item item);

	public Item update(Item item) throws Exception;

	public Item deleteById(Integer itemId) throws Exception;

}
