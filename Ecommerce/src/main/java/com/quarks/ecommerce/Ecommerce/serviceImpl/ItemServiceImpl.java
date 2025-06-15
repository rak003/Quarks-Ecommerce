package com.quarks.ecommerce.Ecommerce.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quarks.ecommerce.Ecommerce.entities.Item;
import com.quarks.ecommerce.Ecommerce.entities.Reservation;
import com.quarks.ecommerce.Ecommerce.entities.UserTable;
import com.quarks.ecommerce.Ecommerce.repository.ItemRepository;
import com.quarks.ecommerce.Ecommerce.repository.ReservationRepository;
import com.quarks.ecommerce.Ecommerce.repository.UserRepository;
import com.quarks.ecommerce.Ecommerce.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	public Item addNewItem(String name, int qty) {
		Item item = new Item();
		item.setName(name);
		item.setTotalQty(qty);
		item.setAvailableQty(qty);
		return itemRepository.save(item);
	}

	public Reservation reserveItem(Long itemId, Long userId, int qty) {
		Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
		UserTable user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		if (item.getAvailableQty() < qty) {
			throw new RuntimeException("Not enough stock.");
		}

		item.setAvailableQty(item.getAvailableQty() - qty);
		itemRepository.save(item);
		 Reservation reservation = reservationRepository.findByUserIdAndItemId(userId, itemId);
		 
		 if(reservation != null) {
			 reservation.setQty(qty+reservation.getQty());
		 }else {
			 reservation = new Reservation();
				reservation.setItem(item);
				reservation.setUser(user);
				reservation.setQty(qty);
		 }

			return reservationRepository.save(reservation);
	}

	@Override
	public List<Item> getAllItem() {
		// TODO Auto-generated method stub
		return itemRepository.findAll();
	}

	@Override
	public List<Item> getAvailableItems() {
		// TODO Auto-generated method stub
		return itemRepository.findByAvailableQtyNot(0);
	}

}
