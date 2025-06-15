package com.quarks.ecommerce.Ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quarks.ecommerce.Ecommerce.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> { 
	
	 List<Item> findByAvailableQtyNot(int availableQty);
	
}