package com.quarks.ecommerce.Ecommerce.service;

import java.util.List;

import com.quarks.ecommerce.Ecommerce.entities.Item;
import com.quarks.ecommerce.Ecommerce.entities.Reservation;

public interface ItemService {

	Item addNewItem(String name, int totalQty);

	Reservation reserveItem(Long itemId, Long userId, int qty);

	List<Item> getAllItem();

	List<Item> getAvailableItems();


}
