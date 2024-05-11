package com.books.app.services;

import java.util.List;

import com.books.app.model.Books;
import com.books.app.model.Userprofile;
import com.books.app.pojo.BooksResource;

public interface BooksService {
	public List<BooksResource> getAllBooks(int page, int size, String genre, String author, String title, Boolean availability);

	List<Books> fetchBooksList();

	public BooksResource getBookById(Long id);
}
