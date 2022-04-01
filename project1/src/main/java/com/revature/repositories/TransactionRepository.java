package com.revature.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.revature.modals.CustomerTransaction;

public interface TransactionRepository extends JpaRepository<CustomerTransaction,Integer>{
	
	public List<CustomerTransaction> findByUser(@Param("user_id") int user_id);
}
