package com.quarks.ecommerce.Ecommerce.serviceImpl;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quarks.ecommerce.Ecommerce.entities.Item;
import com.quarks.ecommerce.Ecommerce.entities.Reservation;
import com.quarks.ecommerce.Ecommerce.entities.UserTable;
import com.quarks.ecommerce.Ecommerce.repository.ItemRepository;
import com.quarks.ecommerce.Ecommerce.repository.ReservationRepository;
import com.quarks.ecommerce.Ecommerce.repository.UserRepository;
import com.quarks.ecommerce.Ecommerce.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	private final Lock lock = new ReentrantLock();

	@Override
	public void cancelReservation(Long userId, Long itemId, Integer qnt, Boolean isAll) {
		// 1. Retrieve data first without a lock
		Reservation reservation = reservationRepository.findByUserIdAndItemId(userId, itemId);
		if (qnt > reservation.getQty()) {
			throw new RuntimeException("Cancellation Quantity is greater than requested Quantity");
		}
		Item item = itemRepository.findById(reservation.getItem().getId()).orElseThrow();

		lock.lock();
		try {
			// 2. Perform only the minimum, critical operations under the lock
			if (isAll) {
				item.setAvailableQty(item.getAvailableQty() + reservation.getQty());
				reservationRepository.delete(reservation);
			} else {
				item.setAvailableQty(item.getAvailableQty() + qnt);
				reservation.setQty(reservation.getQty() - qnt);
				reservationRepository.save(reservation);
			}
			itemRepository.save(item);
		} finally {
			lock.unlock();
		}
	}

}
