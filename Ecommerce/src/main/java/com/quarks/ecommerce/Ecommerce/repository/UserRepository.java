package com.quarks.ecommerce.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quarks.ecommerce.Ecommerce.entities.UserTable;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Long> {
	UserTable findByUsername(String username);
}
