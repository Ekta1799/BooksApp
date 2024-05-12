package com.books.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.books.app.model.ExchangeRequest;

import jakarta.transaction.Transactional;

@Transactional
public interface ExchangeRequestRepository extends JpaRepository<ExchangeRequest, Long> {
    // Add custom query methods if needed
	
	@Query("SELECT e FROM ExchangeRequest e")
     List<ExchangeRequest> getExchangeRequests();
}
