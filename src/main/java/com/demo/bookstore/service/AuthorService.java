package com.demo.bookstore.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.demo.bookstore.dto.AuthorDto;

public interface AuthorService {

	/**
	 * Get the list of Authors
	 * 
	 * @param A {@link Pageable}
	 * @return A list of {@link AuthorDto}
	 */
	List<AuthorDto> getAuthors(Pageable pageable);

	/**
	 * Get the list of Authors
	 * 
	 * @return A list of {@link AuthorDto}
	 */
	List<AuthorDto> getAuthors();

	/**
	 * Get an author with the given id
	 * 
	 * @param A {@link Long} id
	 * @return A {@link AuthorDto}
	 */
	AuthorDto getAuthorById(long id);

	/**
	 * Create an author in the database
	 * 
	 * @param A {@link AuthorDto}
	 * @return A {@link AuthorDto}
	 */
	AuthorDto createAuthor(AuthorDto authorDto);

	/**
	 * Get an author with the given firstname
	 * 
	 * @param A {@link String} name
	 * @return A {@link AuthorDto}
	 */
	List<AuthorDto> findByFirstName(String firstName);

}
