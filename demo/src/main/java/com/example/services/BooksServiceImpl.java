package com.example.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Books;
import com.example.model.Genres;
import com.example.pojo.BooksResource;
import com.example.repository.BookRepository;
import com.example.repository.GenreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BooksServiceImpl implements BooksService {

	private static final Logger logger = LoggerFactory.getLogger(BooksServiceImpl.class);
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private GenreRepository genreRepository;

	public List<BooksResource> getAllBooks(int page, int size, Long genreId, String author, String title, Boolean availability) {
        Map<Long, String> genreMap = getGenres();
        
        List<Books> books;
        if (genreId != null || author != null || title != null || availability != null) {
            // Search with provided criteria
            books = bookRepository.searchBooks(genreId, author, title, availability);
        } else {
            // Fetch all books
            books = bookRepository.findAll();
        }

        List<BooksResource> list = new ArrayList<>();
        for (Books book : books) {
            list.add(convertDTOtoResource(book, genreMap));
        }

        return list;
    }

	private Map<Long, String> getGenres() {
		// Map genre IDs to genre names
        Map<Long, String> genreMap = new HashMap<Long, String>();
        List<Genres> genres = genreRepository.findAll();
        for (Genres genre : genres) {
            genreMap.put(genre.getGenre_id(), genre.getGenre_name());
        }
        return genreMap;
	}
	
	private BooksResource convertDTOtoResource(Books books,Map<Long, String> genreMap) {
		BooksResource res = new BooksResource();
		
		logger.debug("" + genreMap);

		Long genreId = books.getGenre_id(); // Assuming book.getGenreId() returns a Long
		String genreName = genreMap.get(genreId);
		
		logger.debug("" + genreName);
		res.setTitle(books.getTitle());
		res.setAuthor(books.getAuthor());
		res.setGenre(genreName);
		res.setCondition(books.getCondition_status());
		res.setAvailability(books.isAvailability());

		return res;
	}
	
	public BooksResource getBookById(Long id) {
		Map<Long, String> genreMap = getGenres();
		Optional<Books> books = bookRepository.findById(id);
		
		Books book = books.get();
		
		BooksResource res = convertDTOtoResource(book, genreMap);
		
		return res;
	}

	// Read operation 
    @Override public List<Books> fetchBooksList() 
    { 
    	
    	List<Books> list = bookRepository.findAll();
        return list; 
    } 

}
