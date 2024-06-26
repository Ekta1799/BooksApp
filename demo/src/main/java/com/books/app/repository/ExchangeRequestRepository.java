package com.books.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.books.app.model.ExchangeRequest;

import jakarta.transaction.Transactional;

@Transactional
public interface ExchangeRequestRepository extends JpaRepository<ExchangeRequest, Long> {
    // Add custom query methods if needed
}
