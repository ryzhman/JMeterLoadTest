package com.go2it.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.go2it.model.Item;
import com.go2it.service.ItemService;

/**
 * @author Alex Ryzhkov
 */
@RestController
@RequestMapping(value = "/item")
public class ItemsController {
	@Autowired private ItemService itemService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Item getItemById(@PathVariable String id) {
		return itemService.getItemById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean removeItemById(@PathVariable String id) {
		return itemService.removeItemById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	public Item updateItem(@PathVariable String id, @RequestBody Item newItem) {
		return itemService.updateItem(id, newItem);
	}
}
