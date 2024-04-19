package com.example.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.pojo.BooksResource;
import com.example.services.BooksService;

@Component
public class BooksFacade {
	@Autowired
	private BooksService service;

	public List<BooksResource> getBooks(Long genreId, String author, String title,
			Boolean availability) {

		List<BooksResource> list = service.getAllBooks(0, 10, genreId, author, title, availability);

		return list;
	}

	public BooksResource getBookById(Integer id) {

		BooksResource book = service.getBookById(id.toUnsignedLong(0));

		return book;
	}

}
