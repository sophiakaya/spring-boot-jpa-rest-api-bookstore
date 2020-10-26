package com.demo.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.bookstore.dto.AuthorDto;
import com.demo.bookstore.dto.BookDto;
import com.demo.bookstore.exception.CustomResourceNotFoundException;
import com.demo.bookstore.model.BookRequestModel;
import com.demo.bookstore.service.AuthorService;
import com.demo.bookstore.service.BookService;

/**
 * 
 * Controller for /api/books endpoint
 *
 */
@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	AuthorService authorService;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<BookDto>> getBooks(
			@RequestParam(value = "page", defaultValue = "0", required = false) int page,
			@RequestParam(value = "limit", defaultValue = "50", required = false) int limit) {

		List<BookDto> bookDtos = bookService.getBooks(page, limit);

		return ResponseEntity.status(HttpStatus.OK).body(bookDtos);
	}

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<BookDto> getBook(@PathVariable long id) {

		BookDto bookDto = bookService.getBookById(id);

		if (bookDto == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return ResponseEntity.status(HttpStatus.OK).body(bookDto);

	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> createBook(@Valid @RequestBody BookRequestModel bookRequestModel) {

		AuthorDto authorDto = authorService.getAuthorById(bookRequestModel.getAuthorId());

		if (authorDto == null)
			return new ResponseEntity<>(new CustomResourceNotFoundException("Author not found"), HttpStatus.NOT_FOUND);

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		BookDto bookDto = modelMapper.map(bookRequestModel, BookDto.class);
		bookDto.setAuthorDto(authorDto);

		BookDto createdBookDto = bookService.createBook(bookDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(createdBookDto);
	}

	@GetMapping(path = "/author/{authorId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<BookDto>> getBooksByAuthor(@PathVariable long authorId) {

		AuthorDto authorDto = authorService.getAuthorById(authorId);

		if (authorDto == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		List<BookDto> bookDtos = bookService.findByAuthor(authorId);

		return ResponseEntity.status(HttpStatus.OK).body(bookDtos);
	}

}
