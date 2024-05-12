package com.books.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.books.app.facade.ExchangeRequestFacade;
import com.books.app.pojo.ExchangeRequestResource;
import com.books.app.pojo.MessageResponse;

@RestController
@RequestMapping("/exchangeRequests")
public class ExchangeRequestController {
	
	@Autowired
	ExchangeRequestFacade exchangeRequestFacade;

    @PostMapping
    public ResponseEntity<?> createExchangeRequest(@RequestBody ExchangeRequestResource exchangeRequestResource) {
    	
        exchangeRequestFacade.createExchangeRequest(exchangeRequestResource);
        
        return ResponseEntity.ok(new MessageResponse("Exchange Request is placed!"));
    }

}
