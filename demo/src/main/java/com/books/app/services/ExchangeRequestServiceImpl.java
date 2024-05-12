package com.books.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.app.model.ExchangeRequest;
import com.books.app.repository.ExchangeRequestRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExchangeRequestServiceImpl implements ExchangeRequestService{
	
	@Autowired
	ExchangeRequestRepository exchangeRequestRepository;
	
	public void createExchangeRequest(ExchangeRequest exchangeRequest) {
		
		exchangeRequestRepository.save(exchangeRequest);
		
		
	}
	
	public List<ExchangeRequest> getExchangeRequests() {
		
		List<ExchangeRequest> exchangeRequestList = exchangeRequestRepository.getExchangeRequests();
		
		return exchangeRequestList;
	}
	
	public void getExchangeRequestByUsername(ExchangeRequest exchangeRequest) {
		
		exchangeRequestRepository.save(exchangeRequest);
		
		
	}

}
