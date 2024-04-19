package com.example.services;

import java.util.List;

import com.example.model.Books;
import com.example.pojo.BooksResource;

public interface BooksService {
	public List<BooksResource> getAllBooks(int page, int size, Long genreId, String author, String title, Boolean availability);

	List<Books> fetchBooksList();

	public BooksResource getBookById(Long id);
}