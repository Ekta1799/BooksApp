package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "genres")
public class Genres {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
	private Long genre_id;
	
	private String genre_name;

	public Long getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(Long genre_id) {
		this.genre_id = genre_id;
	}

	public String getGenre_name() {
		return genre_name;
	}

	public void setGenre_name(String genre_name) {
		this.genre_name = genre_name;
	}

}
