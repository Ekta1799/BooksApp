package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "books")
public class Books {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    private Long book_id;

	private String title;

	private String author;

	@JoinTable(name = "genres", joinColumns = @JoinColumn(name = "genre_id"), inverseJoinColumns = @JoinColumn(name = "genre_name"))
	private Long genre_id;

	private String condition_status;

	private boolean availability;

	public Long getBook_id() {
		return book_id;
	}

	public void setBook_id(Long book_id) {
		this.book_id = book_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(Long genre_id) {
		this.genre_id = genre_id;
	}

	public String getCondition_status() {
		return condition_status;
	}

	public void setCondition_status(String condition_status) {
		this.condition_status = condition_status;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
}

