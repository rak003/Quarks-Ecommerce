package com.quarks.ecommerce.Ecommerce.service;

public interface ReservationService {

	void cancelReservation(Long userId, Long itemId, Integer qnt, Boolean isAll);

}
