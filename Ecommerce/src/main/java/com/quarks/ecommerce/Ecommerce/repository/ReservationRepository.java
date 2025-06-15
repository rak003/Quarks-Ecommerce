package com.quarks.ecommerce.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quarks.ecommerce.Ecommerce.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	List<Reservation> findAllByUserId(Long userId);

	Reservation findByUserIdAndItemId(Long userId, Long itemId);
}