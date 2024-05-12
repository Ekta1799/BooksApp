package com.books.app.facade;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.books.app.model.Books;
import com.books.app.model.ExchangeRequest;
import com.books.app.model.User;
import com.books.app.pojo.ExchangeRequestResource;
import com.books.app.repository.BookRepository;
import com.books.app.repository.UserRepository;
import com.books.app.services.ExchangeRequestService;

@Component
public class ExchangeRequestFacade {
	
	@Autowired
	ExchangeRequestService exchangeRequestService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private BookRepository bookRepository;

	public void createExchangeRequest(ExchangeRequestResource exchangeRequestResource) {
		
		ExchangeRequest exchangeRequest = new ExchangeRequest();
		
		Optional<User> optionalSenderUser = userRepository.findByUsername(exchangeRequestResource.getSenderUsername());
		User senderUser = optionalSenderUser.get();
		
		Optional<User> optionalRecieverUser = userRepository.findByUsername(exchangeRequestResource.getRecieverUsername());
		User recieverUser = optionalRecieverUser.get();
		
		Books book = bookRepository.findByTitle(exchangeRequestResource.getBook());
		
		exchangeRequest.setSender(senderUser);
		exchangeRequest.setReceiver(recieverUser);
		exchangeRequest.setBook(book);
		exchangeRequest.setDeliveryMethod(exchangeRequestResource.getDeliveryMethod());
		exchangeRequest.setDuration(exchangeRequestResource.getDuration());
		
		exchangeRequestService.createExchangeRequest(exchangeRequest);

	}
	
}
