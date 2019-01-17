package com.go2it.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.go2it.model.Item;

/**
 * @author Alex Ryzhkov
 */
@Service
public class ItemService {
	public Item getItemById(String id) {
		Item item = new Item();
		item.setId(id);
		item.setOwnerId(id + "_own");
		item.setCreationDate(LocalDate.now());
		item.setTitle("Item fetched by id");
		return item;
	}

	public boolean removeItemById(String id) {
		return true;
	}

	public Item updateItem(String id, Item item) {
		return item;
	}
}
