package com.demo.bookstore.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.demo.bookstore.model.AuthorRequestModel;
import com.demo.bookstore.service.AuthorService;

/**
 * 
 * Controller for /api/authors endpoint
 *
 */
@RestController
@RequestMapping("/authors")
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<AuthorDto>> getAuthors(
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "limit", required = false) Optional<Integer> limit) {

		List<AuthorDto> authorDtos = null;

		if (page.isEmpty() || limit.isEmpty()) {
			authorDtos = authorService.getAuthors();
		} else {
			Pageable pageable = PageRequest.of(page.get(), limit.get());
			authorDtos = authorService.getAuthors(pageable);
		}

		return ResponseEntity.status(HttpStatus.OK).body(authorDtos);
	}

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AuthorDto> getAuthor(@PathVariable long id) {

		AuthorDto authorDto = authorService.getAuthorById(id);

		if (authorDto == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return ResponseEntity.status(HttpStatus.OK).body(authorDto);

	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<AuthorDto> createAuthor(@Valid @RequestBody AuthorRequestModel authorRequestModel) {

		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

		AuthorDto authorDto = modelMapper.map(authorRequestModel, AuthorDto.class);

		AuthorDto createdAuthorDto = authorService.createAuthor(authorDto);

		return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthorDto);
	}
}
