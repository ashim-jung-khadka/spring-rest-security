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

import com.github.ashim.persistence.entity.Item;
import com.github.ashim.persistence.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {

	private Logger LOGGER = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ItemService itemService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Item>> getItems() {

		LOGGER.info("GET /items");

		return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{itemId}", method = RequestMethod.GET)
	public ResponseEntity<Item> getItem(@PathVariable Integer itemId) throws Exception {

		LOGGER.info("GET /items");
		LOGGER.debug("itemId :: {}", itemId);

		return new ResponseEntity<>(itemService.findById(itemId), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Item> insertItem(@RequestBody @Valid Item item) {

		LOGGER.info("POST /items ::");
		LOGGER.debug("{}", item);

		return new ResponseEntity<>(itemService.insert(item), HttpStatus.OK);
	}

	@RequestMapping(value = "/{itemId}", method = RequestMethod.PUT)
	public ResponseEntity<Item> updateItem(@PathVariable Integer itemId, @RequestBody Item item) throws Exception {

		LOGGER.info("PUT /items");
		LOGGER.debug("itemId :: {}", itemId);
		LOGGER.debug("item :: {}", item);

		item.setId(itemId);

		return new ResponseEntity<>(itemService.update(item), HttpStatus.OK);
	}

	@RequestMapping(value = "/{itemId}", method = RequestMethod.DELETE)
	public ResponseEntity<Item> deleteItem(@PathVariable Integer itemId) throws Exception {

		LOGGER.info("/items");
		LOGGER.debug("itemId :: {}", itemId);

		return new ResponseEntity<>(itemService.deleteById(itemId), HttpStatus.OK);
	}

}