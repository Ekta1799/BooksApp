package com.books.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.books.app.model.Books;
import com.books.app.pojo.BooksResource;
import com.books.app.repository.BookRepository;
import com.books.app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BooksServiceImpl implements BooksService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;

	public List<BooksResource> getAllBooks(int page, int size, String genre, String author, String title,
			Boolean availability) {

		List<Books> books;
		if (genre != null || author != null || title != null || availability != null) {
			// Search with provided criteria
			books = bookRepository.searchBooks(genre, author, title, availability);
		} else {
			// Fetch all books
			books = bookRepository.findAll();
		}

		List<BooksResource> list = new ArrayList<>();
		for (Books book : books) {
			list.add(convertDTOtoResource(book));
		}

		return list;
	}

	private BooksResource convertDTOtoResource(Books books) {
		BooksResource res = new BooksResource();

		res.setTitle(books.getTitle());
		res.setAuthor(books.getAuthor());
		res.setGenre(books.getGenre());
		res.setCondition(books.getCondition_status());
		res.setAvailability(books.isAvailability());
		
		String username = userRepository.userByUserId(books.getUser_id());
		res.setUsername(username);

		return res;
	}

	public List<BooksResource> getBooksForUsersOtherThanUsername(Long userId) {
		
		List<Books> books = bookRepository.findBooksForUsersOtherThanUsername(userId);

		List<BooksResource> list = new ArrayList<BooksResource>();
		for(Books book : books) {
			list.add(convertDTOtoResource(book));
		}

		return list;
	}

	// Read operation
	@Override
	public List<Books> fetchBooksList() {

		List<Books> list = bookRepository.findAll();
		return list;
	}
	
	public void createBooks(Books book) {
		bookRepository.save(book);
	}

}
