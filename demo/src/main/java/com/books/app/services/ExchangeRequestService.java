package com.books.app.services;

import java.util.List;

import com.books.app.model.ExchangeRequest;

public interface ExchangeRequestService {
	
	public void createExchangeRequest(ExchangeRequest exchangeRequest);
	
	public List<ExchangeRequest> getExchangeRequests();

}
