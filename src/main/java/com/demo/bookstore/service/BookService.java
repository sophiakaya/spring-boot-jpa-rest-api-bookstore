package com.demo.bookstore.service;

import java.util.List;

import com.demo.bookstore.dto.BookDto;

public interface BookService {

	/**
	 * Get the list of books
	 * 
	 * @param A {@link Integer} page and a limit
	 * @return A list of {@link BookDto}
	 */
	List<BookDto> getBooks(int page, int limit);

	/**
	 * Get a book with the given id
	 * 
	 * @param A {@link Long} id
	 * @return A {@link BookDto}
	 */
	BookDto getBookById(long id);

	/**
	 * Create a book in the database
	 * 
	 * @param A {@link BookDto}
	 * @return A {@link BookDto}
	 */
	BookDto createBook(BookDto bookDto);

	/**
	 * Get the list of books by author
	 * 
	 * @param A {@link long} author id
	 * @return A list of {@link BookDto}
	 */
	List<BookDto> findByAuthor(long authorId);

}
