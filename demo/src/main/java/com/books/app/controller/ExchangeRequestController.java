package com.books.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.books.app.facade.ExchangeRequestFacade;
import com.books.app.pojo.BooksResource;
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
